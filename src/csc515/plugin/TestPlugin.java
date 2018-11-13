/**
 * Entry point for the plugin.
 * @author {hrhudgin, cesiu}@calpoly.edu
 */

package csc515.plugin;

import com.sun.source.util.Plugin;
import com.sun.source.util.JavacTask;

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
        System.err.println("Now THIS is podracing!");
    }
}
