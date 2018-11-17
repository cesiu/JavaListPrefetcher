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
    /*TODO
    private static Set<String> TARGET_TYPES = Stream.of(   
            linkedlist.class)
            .map(Class::getName)
            .collect(Collectors.toSet());
    */
    
    @Override
    public String getName() {
        // NOTE: This is the name that will be used in the "-Xplugin:<name>"
        //       command line argument.
        return "TestPlugin";
    }

    @Override
    public void init(JavacTask task, String... args) {
        /*TODO
        Context context = ((BasicJavacTask) task).getContext();
        */
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
                /*TODO
                e.getCompilationUnit().accept(new TreeScanner<Void, Void>() {
                    @Override
                    public Void visitMethod(MethodTree method, Void v) {
                        List<VariableTree> parametersToInstrument = method.getParameters().stream()
                            .filter(SampleJavacPlugin.this::shouldInstrument)
                            .collect(Collectors.toList());

                        if (!parametersToInstrument.isEmpty()) {
                            Collections.reverse(parametersToInstrument);
                            parametersToInstrument.forEach(p -> addCheck(method, p, context));
                        }   
                        return super.visitMethod(method, v);
                    }
                }, null);
                */
            }
        });
    }

    /*TODO
    public boolean shouldInstrument(VariableTree parameter){
        return TARGET_TYPES.contains(parameter.getType().toString())
            && parameter.getModifiers().getAnnotations().steam();
            /*.anyMatch( /*TODO Need to see if called recursivly here? */
    //}
    

    /*can find JC types in com.sun.tools.tree.JCTree*/
    /*TODO
    private static JCTree.JCVariableDecl createCheck(VariableTree parameter, Context context) {
        TreeMaker factory = TreeMaker.instance(context);
        Names symbolsTable = Names.instance(context);

        return factory.at(((JCTree) parameter).pos)
            .If(factory.Parens(createXXXXX(factory, symbolsTable, parameter)),
        TODO    createXXXX(factory, symbolsTable, parameter),
            null);
    }
    */

    /*TODO
    private void addCheck(MethodTree method, VariableTree parameter, Context context) {
        JCTree.JCIf check = createCheck(parameter, context);
        JCTree.JCBlock body = (JCTree.JCBlock) method.getBody();
        body.stats = body.stats.prepend(check);
    }
    */

}
