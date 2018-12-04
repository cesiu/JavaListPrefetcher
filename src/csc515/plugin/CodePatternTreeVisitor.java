/**
 * Searchs for method invocation nodes in the Java compiler's AST
 * @author {hrhudgin, cesiu}@calpoly.edu
 */

package csc515.plugin;

import javax.lang.model.element.Name;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;

import com.sun.source.tree.*;
import com.sun.source.tree.Tree.Kind;
import com.sun.source.util.JavacTask;
import com.sun.source.util.SourcePositions;
import com.sun.source.util.Trees;
import com.sun.source.util.TreePath;
import com.sun.source.util.TreePathScanner;

public class CodePatternTreeVisitor extends TreePathScanner<Void, Void>
{
    // Bridges Compiler API, Annotation Processing API and Tree API
    private final Trees trees;
    private final Types types;
    // Offsets of AST nodes in source file
    private final SourcePositions sourcePositions;

    // The components of the method for which we will search
    private final TypeMirror targetObject;
    private final Name targetMethod;

    // The current stage in the compilation pipeline
    private CompilationUnitTree compilationUnit;

    public CodePatternTreeVisitor(
            JavacTask task, String targetObjectName, String targetMethodName) {
        types = task.getTypes();
        trees = Trees.instance(task);
        sourcePositions = trees.getSourcePositions();

        // Create the object and method elements for which to search.
        Elements elements = task.getElements();
        targetObject = elements.getTypeElement(targetObjectName).asType();
        targetMethod = elements.getName(targetMethodName);
    }

    @Override
    public Void visitCompilationUnit(CompilationUnitTree tree, Void p) {
        // Save the compilation unit for reference later.
        compilationUnit = tree;

        return super.visitCompilationUnit(tree, p);
    }

    @Override
    public Void visitMethodInvocation(MethodInvocationTree node, Void p) {
        if (isCallOnPath(new TreePath(getCurrentPath(), node))) {
            System.out.println("Found invocation \"" + node + "\" on line " +
                               getLineNumber(node) + " of " +
                               compilationUnit.getSourceFile().getName());
            // TODO: Add peek call.
        }

        return super.visitMethodInvocation(node, p);
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
     * Determines whether or not a call to the desired method is at the end of
     *  a given AST path.
     */
    private boolean isCallOnPath(TreePath path) {
        if (path.getLeaf().getKind().equals(Kind.METHOD_INVOCATION)) {
            MethodInvocationTree invocation =
                    (MethodInvocationTree)path.getLeaf();

            // Get the method identifier.
            ExpressionTree methodSelect = invocation.getMethodSelect();

            // We limit ourselves to conventional member-based method calls;
            //  it's a fairly safe assumption in Java for our purposes.
            if (methodSelect.getKind().equals(Kind.MEMBER_SELECT)) {
                MemberSelectTree memberSelect = (MemberSelectTree)methodSelect;

                // Get the type of the dot operator's LHS.
                TypeMirror objectType = trees.getTypeMirror(
                        new TreePath(path, memberSelect.getExpression()));

                // Get the name of the dot operator's RHS.
                Name methodName = memberSelect.getIdentifier();

                // 1) Check that the object is an instance of LinkedList.
                // 2) Check that the method's name is "poll".
                return types.isAssignable(types.erasure(objectType),
                                          types.erasure(targetObject))
                       && methodName == targetMethod;
            }
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
