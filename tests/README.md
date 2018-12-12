To register the plugin with the compiler, all tests should be compiled with:

```
javac -cp ../../build/csc515-plugin.jar:. -Xplugin:PrefetchingPlugin ...
```

Many of them recurse on large lists, so they should then be run with ample stack space:

```
java -Xss256m ...
```
