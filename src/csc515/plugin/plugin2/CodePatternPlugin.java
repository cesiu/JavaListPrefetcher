import com.sun.*;
//package csc515.plugin;
import com.sun.source.util.JavacTask;
import com.sun.source.util.TaskListener;
import com.sun.source.util.TaskEvent;
import com.sun.source.*;

import com.sun.source.util.Plugin;
public class CodePatternPlugin implements Plugin{
    @Override
    public void init(JavacTask task, String[] args) {
        System.out.println("Running!");
        task.addTaskListener(new CodePatternTaskListener(task));
    }

    @Override
    public String getName() {
        return "CodePatternPlugin";
    }
}
