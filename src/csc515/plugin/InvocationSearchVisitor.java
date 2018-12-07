/**
 * Searches for method invocation nodes in the Java compiler's AST
 * @author {hrhudgin, cesiu}@calpoly.edu
 */

package csc515.plugin;

import java.util.List;

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
import com.sun.tools.javac.api.BasicJavacTask;
import com.sun.tools.javac.util.Context;
import com.sun.tools.javac.util.ListBuffer;

public class InvocationSearchVisitor extends TreePathScanner<Void, Void>
{
    // Bridges Compiler API, Annotation Processing API and Tree API
    private final Context context;
    private final Trees trees;
    private final Types types;
    // Offsets of AST nodes in source file
    private final SourcePositions sourcePositions;

    // The components of the method for which we will search
    private final String targetObjectName;
    private final String targetMethodName;
    private final TypeMirror targetObject;
    private final Name targetMethod;

    // The current stage in the compilation pipeline
    private CompilationUnitTree compilationUnit;
    // A visitor for searching basic blocks
    private BasicBlockSearchVisitor blockVisitor;

    public InvocationSearchVisitor(
            JavacTask task, String targetObjectName, String targetMethodName) {
        trees = Trees.instance(task);
        types = task.getTypes();
        sourcePositions = trees.getSourcePositions();
        this.context = ((BasicJavacTask)task).getContext();

        // Create the object and method elements for which to search.
        this.targetObjectName = targetObjectName;
        this.targetMethodName = targetMethodName;
        Elements elements = task.getElements();
        targetObject = elements.getTypeElement(targetObjectName).asType();
        targetMethod = elements.getName(targetMethodName);

        // Create the basic block search visitor.
        blockVisitor = new BasicBlockSearchVisitor(
                trees, types, targetObject, targetMethod);
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
        for (int idx = 0; idx < block.getStatements().size(); idx++) {
            StatementTree stmt = block.getStatements().get(idx);

            // Attempt to find the invocation within this immediate block.
            MethodInvocationTree targetExpr = blockVisitor.scan(
                    new TreePath(getCurrentPath(), stmt), null);

            if (targetExpr != null) {
                System.out.println(
                 "Found \"" + targetObjectName + "." + targetMethodName +
                 "\" in \"" + stmt + "\" on line " + getLineNumber(stmt) +
                 " of \"" + compilationUnit.getSourceFile().getName());

                // TODO: Add a peek as statement "idx + 1" of this block. The
                //       list on which to peek is the object of "targetExpr".
                System.out.println(block);
                TreeMaker treeMaker = TreeMaker.instance(context);

                // Get a mutable copy of the statement list.
                JCBlock rawBlock = (JCBlock)block;
                ListBuffer rawStmts = new ListBuffer();

                // TODO: This just duplicates the poll.
                for (int jdx = 0; jdx < idx + 1; jdx++) {
                    rawStmts.append(block.getStatements().get(jdx));
                }
                for (int jdx = idx; jdx < block.getStatements().size(); jdx++) {
                    rawStmts.append(block.getStatements().get(jdx));
                }

                // Put the new list back into the block node.
                rawBlock.stats = rawStmts.toList();
                System.out.println(block);
                break;
            }
        }

        return super.visitBlock(block, p);
    }

    /**
     * TODO
     */
    private boolean isRecursive(TreePath path) {
        if (path.getLeaf().getKind().equals(Tree.Kind.METHOD_INVOCATION)) {
            MethodInvocationTree invocation =
                    (MethodInvocationTree)path.getLeaf();

            // Extract the identifier and receiver.
            ExpressionTree methodSelect = invocation.getMethodSelect();

            // TODO:
            // x =  methodInvocation.getName??????
            // ListOfPastCalls.add(x);
            // If(x is in ListOfPastCalls) Return True;
        }

        return false;
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
