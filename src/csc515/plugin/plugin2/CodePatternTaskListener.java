import com.sun.*;
import com.sun.source.util.JavacTask;
import com.sun.source.util.TaskListener;
import com.sun.source.util.TaskEvent;
import com.sun.source.tree.CompilationUnitTree;
import com.sun.source.*;
public class CodePatternTaskListener implements TaskListener {
    private final CodePatternTreeVisitor visitor;

    CodePatternTaskListener(JavacTask task) {
        visitor = new CodePatternTreeVisitor(task);
    }
    @Override
    public void finished(TaskEvent taskEvent) {
        if(taskEvent.getKind() == TaskEvent.Kind.ANALYZE){
            if(taskEvent.getKind().equals(TaskEvent.Kind.ANALYZE)){
                CompilationUnitTree compilationUnit = taskEvent.getCompilationUnit();
                //new CodePatternTreeVisitor((JavacTask)taskEvent).scan(compilationUnit, null);
                visitor.scan(compilationUnit, null);
            }
        }
    }

    @Override
    public void started(TaskEvent taskEvent) {

    }
}
