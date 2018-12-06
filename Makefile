# TODO

all:
	cd src && \
	javac --add-exports jdk.compiler/com.sun.tools.javac.api=ALL-UNNAMED \
	      --add-exports jdk.compiler/com.sun.tools.javac.tree=ALL-UNNAMED \
	      --add-exports jdk.compiler/com.sun.tools.javac.util=ALL-UNNAMED \
	      csc515/plugin/*.java && \
	jar -cf ../build/csc515-plugin.jar csc515 META-INF
