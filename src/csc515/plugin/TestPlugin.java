/**
 * Entry point for the plugin.
 * @author {hrhudgin, cesiu}@calpoly.edu
 */

package csc515.plugin;

import com.sun.source.util.Plugin;
import com.sun.source.util.JavacTask;
import com.sun.source.util.TaskListener;
import com.sun.source.util.TaskEvent;

public class TestPlugin implements Plugin
{
    @Override
    public String getName() {
        // NOTE: This is the name that will be used in the "-Xplugin:<name>"
        //       command line argument.
        return "TestPlugin";
    }

    @Override
    public void init(JavacTask task, String... args) {
        System.err.println("Now THIS is podracing!\n");

        task.addTaskListener(new TaskListener() {
            @Override
            public void started(TaskEvent e) {}

            @Override
            public void finished(TaskEvent e) {
                if (e.getKind() == TaskEvent.Kind.PARSE) {
                    System.err.println(
                            "Finished parsing " + e.getSourceFile() + ":\n" +
                            e.getCompilationUnit());
                }
            }
        });
    }
}
