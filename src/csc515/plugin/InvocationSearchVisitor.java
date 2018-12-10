/**
 * Searches for method invocation nodes in the Java compiler's AST
 * @author {hrhudgin, cesiu}@calpoly.edu
 */

package csc515.plugin;

import java.util.List;
import java.util.function.Function;

import javax.lang.model.element.Name;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;

import com.sun.source.tree.*;
import com.sun.source.util.JavacTask;
import com.sun.source.util.SourcePositions;
import com.sun.source.util.Trees;
import com.sun.source.util.TreePath;
import com.sun.source.util.TreePathScanner;

import com.sun.tools.javac.tree.*;
import com.sun.tools.javac.tree.JCTree.*;
import com.sun.tools.javac.util.ListBuffer;

public class InvocationSearchVisitor extends TreePathScanner<Void, Void>
{
    // Bridges Compiler API, Annotation Processing API and Tree API
    private Trees trees;
    private Types types;
    // Offsets of AST nodes in source file
    private SourcePositions sourcePositions;

    // The components of the method for which we will search
    private String targetObjectName;
    private String targetMethodName;
    private TypeMirror targetObject;

    // The current stage in the compilation pipeline
    private CompilationUnitTree compilationUnit;
    // A visitor for searching basic blocks
    private BasicBlockSearchVisitor blockVisitor;
    // A callback for processing discovered invocations
    private Function<MethodInvocationTree, JCStatement> callback;

    public InvocationSearchVisitor(
            JavacTask task, String targetObjectName,
            String targetMethodName, String newMethodName,
            Function<MethodInvocationTree, JCStatement> callback) {
        Elements elements = task.getElements();

        // Save the context of compilation.
        trees = Trees.instance(task);
        types = task.getTypes();
        sourcePositions = trees.getSourcePositions();

        // Create the object and method elements for which to search.
        this.targetObjectName = targetObjectName;
        this.targetMethodName = targetMethodName;
        targetObject = elements.getTypeElement(targetObjectName).asType();

        // Create the basic block search visitor.
        blockVisitor = new BasicBlockSearchVisitor(
                trees, types, targetObject,
                elements.getName(targetMethodName));

        this.callback = callback;
    }

    @Override
    public Void visitCompilationUnit(CompilationUnitTree tree, Void p) {
        // Save the compilation unit for reference later.
        compilationUnit = tree;

        return super.visitCompilationUnit(tree, p);
    }

    @Override
    public Void visitBlock(BlockTree block, Void p) {
        // Run through all the statements in each basic block.
        for (int i = 0; i < block.getStatements().size(); i++) {
            StatementTree stmt = block.getStatements().get(i);

            // Attempt to find the invocation within this immediate block.
            MethodInvocationTree targetExpr = blockVisitor.scan(
                    new TreePath(getCurrentPath(), stmt), null);

            if (targetExpr != null) {
                // Start with a mutable copy of the statement list.
                JCBlock rawBlock = (JCBlock)block;
                ListBuffer rawStmts = new ListBuffer();
                for (int j = 0; j < i + 1; j++) {
                    rawStmts.append(block.getStatements().get(j));
                }

                // Add in the result of the callback.
                rawStmts.append(callback.apply(targetExpr));

                // Add in the rest of the original statements.
                for (int j = i + 1; j < block.getStatements().size(); j++) {
                    rawStmts.append(block.getStatements().get(j));
                }

                // Put the new list back into the block node.
                rawBlock.stats = rawStmts.toList();
                break;
            }
        }

        return super.visitBlock(block, p);
    }

    /**
     * Finds the starting line number of the source represented by a given AST.
     */
    private long getLineNumber(Tree tree) {
        if (compilationUnit == null) {
            return -1;
        }

        LineMap lineMap = compilationUnit.getLineMap();
        if (lineMap == null) {
            return -1;
        }
        else {
            return lineMap.getLineNumber(
                    sourcePositions.getStartPosition(compilationUnit, tree));
        }
    }
}
