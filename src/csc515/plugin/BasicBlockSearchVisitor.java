/**
 * Searches for method invocation nodes in a single basic block
 * @author {hrhudgin, cesiu}@calpoly.edu
 */

package csc515.plugin;

import javax.lang.model.element.Name;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Types;

import com.sun.source.tree.*;
import com.sun.source.util.JavacTask;
import com.sun.source.util.Trees;
import com.sun.source.util.TreePath;
import com.sun.source.util.TreePathScanner;

public class BasicBlockSearchVisitor
        extends TreePathScanner<MethodInvocationTree, Void>
{
    // Bridges Compiler API, Annotation Processing API and Tree API
    private final Trees trees;
    private final Types types;

    // The components of the method for which we will search
    private final TypeMirror targetObject;
    private final Name targetMethod;

    public BasicBlockSearchVisitor(
            Trees trees, Types types,
            TypeMirror targetObject, Name targetMethod) {
        this.trees = trees;
        this.types = types;
        this.targetObject = targetObject;
        this.targetMethod = targetMethod;
    }

    @Override
    public MethodInvocationTree reduce(
            MethodInvocationTree r1, MethodInvocationTree r2) {
        return r1 == null ? r2 : r1;
    }

    @Override
    public MethodInvocationTree visitBlock(
            BlockTree block, Void p) {
        // Avoid searching nested blocks.
        return null;
    }

    @Override
    public MethodInvocationTree visitMethodInvocation(
            MethodInvocationTree invocation, Void p) {
        // Get the method identifier.
        ExpressionTree methodSelect = invocation.getMethodSelect();

        // We limit ourselves to conventional member-based method calls;
        //  it's a fairly safe assumption in Java for our purposes.
        if (methodSelect.getKind().equals(Tree.Kind.MEMBER_SELECT)) {
            MemberSelectTree memberSelect = (MemberSelectTree)methodSelect;

            // Get the type of the dot operator's LHS.
            TypeMirror objectType =trees.getTypeMirror(
                    new TreePath(getCurrentPath(),
                                 memberSelect.getExpression()));

            // Get the name of the dot operator's RHS.
            Name methodName = memberSelect.getIdentifier();

            // Check that this is an occurrence of the desired invocation.
            if (types.isAssignable(types.erasure(objectType),
                                      types.erasure(targetObject))
                    && methodName == targetMethod) {
                return invocation;
            }
        }

        return super.visitMethodInvocation(invocation, p);
    }
}
