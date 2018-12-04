/**
 * Searches for method invocation nodes in the Java compiler's AST
 * @author {hrhudgin, cesiu}@calpoly.edu
 */

package csc515.plugin;

import javax.lang.model.element.Name;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;

import com.sun.source.tree.Tree;
import com.sun.source.tree.Tree.Kind;
import com.sun.source.tree.ExpressionTree;
import com.sun.source.tree.StatementTree;
import com.sun.source.tree.BlockTree;
import com.sun.source.tree.MethodInvocationTree;
import com.sun.source.tree.CompilationUnitTree;
import com.sun.source.tree.LineMap;
import com.sun.source.util.JavacTask;
import com.sun.source.util.SourcePositions;
import com.sun.source.util.Trees;
import com.sun.source.util.TreePath;
import com.sun.source.util.TreePathScanner;

public class InvocationSearchVisitor extends TreePathScanner<Void, Void>
{
    // Bridges Compiler API, Annotation Processing API and Tree API
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
            ExpressionTree targetExpr = blockVisitor.scan(
                    new TreePath(getCurrentPath(), stmt), null);

            if (targetExpr != null) {
                System.out.println(
                 "Found \"" + targetObjectName + "." + targetMethodName +
                 "\" in \"" + stmt + "\" on line " + getLineNumber(stmt) +
                 " of \"" + compilationUnit.getSourceFile().getName());
            }
        }

        return super.visitBlock(block, p);
    }

    /**
     * TODO
     */
    private boolean isRecursive(TreePath path) {
        if (path.getLeaf().getKind().equals(Kind.METHOD_INVOCATION)) {
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
