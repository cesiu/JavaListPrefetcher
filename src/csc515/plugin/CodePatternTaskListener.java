/**
 * Responds to events in the compilation pipeline
 * @author {hrhudgin, cesiu}@calpoly.edu
 */

package csc515.plugin;

import com.sun.source.util.JavacTask;
import com.sun.source.util.TaskListener;
import com.sun.source.util.TaskEvent;
import com.sun.source.tree.CompilationUnitTree;

public class CodePatternTaskListener implements TaskListener
{
    private final JavacTask task;

    public CodePatternTaskListener(JavacTask task) {
        this.task = task;
    }

    @Override
    public void started(TaskEvent taskEvent) {
    }

    @Override
    public void finished(TaskEvent taskEvent) {
        if(taskEvent.getKind().equals(TaskEvent.Kind.ANALYZE)){
            CodePatternTreeVisitor visitor
                    = new CodePatternTreeVisitor(task);
            CompilationUnitTree compilationUnit
                    = taskEvent.getCompilationUnit();

            visitor.scan(compilationUnit, null);
        }
    }
}
