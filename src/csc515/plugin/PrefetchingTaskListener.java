/**
 * Responds to events in the compilation pipeline
 * @author {hrhudgin, cesiu}@calpoly.edu
 */

package csc515.plugin;

import java.util.List;

import com.sun.source.tree.*;
import com.sun.source.util.JavacTask;
import com.sun.source.util.TaskListener;
import com.sun.source.util.TaskEvent;
import com.sun.source.tree.CompilationUnitTree;

import com.sun.tools.javac.tree.*;
import com.sun.tools.javac.tree.JCTree.*;
import com.sun.tools.javac.api.BasicJavacTask;
import com.sun.tools.javac.code.Symbol;
import com.sun.tools.javac.code.Type;
import com.sun.tools.javac.code.TypeTag;
import com.sun.tools.javac.util.Context;
import com.sun.tools.javac.util.Names;
import com.sun.tools.javac.util.ListBuffer;

public class PrefetchingTaskListener implements TaskListener
{
    private JavacTask task;
    private Context context;
    private TreeMaker treeMaker;
    private Names symbolTable;

    public PrefetchingTaskListener(JavacTask task) {
        this.task = task;
        this.context = ((BasicJavacTask)task).getContext();
        this.treeMaker = TreeMaker.instance(context);
        this.symbolTable = Names.instance(context);
    }

    @Override
    public void started(TaskEvent taskEvent) {
        // No-op
    }

    @Override
    public void finished(TaskEvent taskEvent) {
        if(taskEvent.getKind().equals(TaskEvent.Kind.ANALYZE)){
            InvocationSearchVisitor visitor = null;
            CompilationUnitTree compilationUnit
                    = taskEvent.getCompilationUnit();

            // Prefetch linked lists:
            visitor = new InvocationSearchVisitor(
                    task, "java.util.LinkedList", "poll",
                    this::linkedListCallback);
            visitor.scan(compilationUnit, null);

            // Prefetch array lists:
            visitor = new InvocationSearchVisitor(
                    task, "java.util.ArrayList", "get",
                    this::arrayListCallback);
            visitor.scan(compilationUnit, null);
        }
    }

    /**
     * Processes method invocations of LinkedList.poll().
     */
    public JCStatement linkedListCallback(MethodInvocationTree targetExpr) {
        // Extract the invoked expression.
        MemberSelectTree memberSelect =
                (MemberSelectTree)targetExpr.getMethodSelect();

        // Create a member access of the "peek" method.
        JCFieldAccess accessExpr = treeMaker.Select(
                (JCExpression)memberSelect.getExpression(),
                symbolTable.fromString("peek"));
        accessExpr.type = ((JCFieldAccess)memberSelect).type;

        // Change the internal symbol appropriately.
        accessExpr.sym = ((JCFieldAccess)memberSelect).sym.clone(
                ((JCFieldAccess)memberSelect).sym.owner);
        accessExpr.sym.name = symbolTable.fromString("peek");

        // Call it...
        JCMethodInvocation callExpr = treeMaker.App(accessExpr);

        // ...and make that a standalone statement.
        return treeMaker.Exec(callExpr);
    }

    /**
     * Processes method invocations of ArrayList.get().
     * NOTE: This assumes that the list is properly padded -- we will assume
     *       that there is always something to prefetch, with no index checks.
     */
    public JCStatement arrayListCallback(MethodInvocationTree targetExpr) {
        // Extract the invoked expression.
        MemberSelectTree memberSelect =
                (MemberSelectTree)targetExpr.getMethodSelect();

        // Create the incremented index.
        JCLiteral intOneExpr = treeMaker.Literal(TypeTag.INT, 1);
        JCExpression oldIndexExpr =
                (JCExpression)targetExpr.getArguments().get(0);
        JCBinary newIndexExpr = treeMaker.Binary(
                Tag.PLUS, oldIndexExpr, intOneExpr);
        newIndexExpr.type = oldIndexExpr.type;
        intOneExpr.type = oldIndexExpr.type;

        // The TreeMaker doesn't set types correctly. Just fake it -- I'm a
        //  perfect programmer; we don't need type checking.
        ListBuffer rawArgTypes = new ListBuffer();
        rawArgTypes.append(oldIndexExpr.type);
        rawArgTypes.append(intOneExpr.type);
        Type.MethodType fakeType = new Type.MethodType(
                rawArgTypes.toList(), newIndexExpr.type,
                com.sun.tools.javac.util.List.nil(), null);
        newIndexExpr.operator = new Symbol.OperatorSymbol(
                null, fakeType, 0x60, ((JCFieldAccess)memberSelect).sym.owner);

        // Call "get" with the incremented index...
        ListBuffer rawArgs = new ListBuffer();
        rawArgs.append(newIndexExpr);
        JCMethodInvocation callExpr = treeMaker.App(
                (JCExpression)memberSelect, rawArgs.toList());

        // ...and make that a standalone statement.
        return treeMaker.Exec(callExpr);
    }
}
