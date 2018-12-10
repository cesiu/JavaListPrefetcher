/**
 * Responds to events in the compilation pipeline
 * @author {hrhudgin, cesiu}@calpoly.edu
 */

package csc515.plugin;

import com.sun.source.tree.*;
import com.sun.source.util.JavacTask;
import com.sun.source.util.TaskListener;
import com.sun.source.util.TaskEvent;
import com.sun.source.tree.CompilationUnitTree;

import com.sun.tools.javac.tree.*;
import com.sun.tools.javac.tree.JCTree.*;
import com.sun.tools.javac.api.BasicJavacTask;
import com.sun.tools.javac.util.Context;
import com.sun.tools.javac.util.Names;

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
        // TODO: No-op?
    }

    @Override
    public void finished(TaskEvent taskEvent) {
        if(taskEvent.getKind().equals(TaskEvent.Kind.ANALYZE)){
            InvocationSearchVisitor visitor = new InvocationSearchVisitor(
                    task, "java.util.LinkedList", "poll", "peek",
                    this::linkedListCallback);
            CompilationUnitTree compilationUnit
                    = taskEvent.getCompilationUnit();

            visitor.scan(compilationUnit, null);
        }
    }

    /**
     * Processes method invocations of LinkedList.poll().
     */
    public JCStatement linkedListCallback(MethodInvocationTree targetExpr) {
        // Extract the invoked expression...
        MemberSelectTree memberSelect =
                (MemberSelectTree)targetExpr.getMethodSelect();

        // ...create a member access of the peek method...
        JCFieldAccess accessExpr = treeMaker.Select(
                (JCExpression)memberSelect.getExpression(),
                symbolTable.fromString("peek"));
        accessExpr.type = ((JCFieldAccess)memberSelect).type;

        // ...change the internal symbol appropriately...
        accessExpr.sym = ((JCFieldAccess)memberSelect).sym.clone(
                ((JCFieldAccess)memberSelect).sym.owner);
        accessExpr.sym.name = symbolTable.fromString("peek");

        // ...call it...
        JCMethodInvocation callExpr = treeMaker.App(accessExpr);

        // ...and make that a standalone statement.
        return treeMaker.Exec(callExpr);
    }
}
