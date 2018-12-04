/**
 * Entry point for the plugin
 * @author {hrhudgin, cesiu}@calpoly.edu
 */

package csc515.plugin;

import com.sun.source.util.Plugin;
import com.sun.source.util.JavacTask;

public class PrefetchingPlugin implements Plugin
{
    @Override
    public void init(JavacTask task, String[] args) {
        task.addTaskListener(new PrefetchingTaskListener(task));
    }

    @Override
    public String getName() {
        return "PrefetchingPlugin";
    }
}
