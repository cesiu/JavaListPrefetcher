/**
 * Visits nodes in the Java compiler's AST
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
import com.sun.source.util.TreePath;
import com.sun.source.util.TreePathScanner;
import com.sun.source.util.Trees;

public class CodePatternTreeVisitor extends TreePathScanner<Void, Void>
{
    // Offsets of AST nodes in source file
    private final SourcePositions sourcePositions;
    // Bridges Compiler api, Annotation Processing API and Tree API
    private final Trees trees;

    private final Types types;
    private final TypeMirror llObject;
    private final Name llMethod;

    private CompilationUnitTree curCompUnit;

    CodePatternTreeVisitor(JavacTask task) {
        types = task.getTypes();
        trees = Trees.instance(task);
        sourcePositions = trees.getSourcePositions();

        // Create the object and method elements for which to search.
        Elements elements = task.getElements();
        llObject = elements.getTypeElement("java.util.LinkedList").asType();
        llMethod = elements.getName("poll");
    }

    @Override
    public Void visitCompilationUnit(CompilationUnitTree tree, Void p) {
        // Save the compilation unit for reference later.
        curCompUnit = tree;
        return super.visitCompilationUnit(tree, p);
    }

    @Override
    public Void visitMethodInvocation(MethodInvocationTree node, Void p) {
        if (isCallOnMap(new TreePath(getCurrentPath(), node))) {
            System.out.println("Found invocation \"" + node + "\" on line " +
                               getLineNumber(node) + " of " +
                               curCompUnit.getSourceFile().getName());
        }

        return super.visitMethodInvocation(node, p);
    }

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

    private boolean isCallOnMap(TreePath path) {
        if (path.getLeaf().getKind().equals(Kind.METHOD_INVOCATION)) {
            MethodInvocationTree invocation
                    = (MethodInvocationTree)path.getLeaf();

            // Extract the identifier and receiver.
            ExpressionTree methodSelect = invocation.getMethodSelect();

            if (methodSelect.getKind().equals(Kind.MEMBER_SELECT)) {
                // Extract receiver type.
                MemberSelectTree mst = (MemberSelectTree)methodSelect;
                ExpressionTree expr = mst.getExpression();
                TypeMirror type = trees.getTypeMirror(new TreePath(path, expr));

                // Extract method name.
                Name name = mst.getIdentifier();

                // 1) Check if the receiver’s type is LinkedList.
                // 2) Check if the method's name is "poll".
                return types.isAssignable(types.erasure(type),
                                          types.erasure(llObject))
                       && name == llMethod;
            }
        }

        return false;
    }

    private long getLineNumber(Tree tree) {
        LineMap lineMap = curCompUnit.getLineMap();

        if (lineMap == null) {
            return -1;
        }
        else {
            return lineMap.getLineNumber(
                    sourcePositions.getStartPosition(curCompUnit, tree));
        }
    }
}
