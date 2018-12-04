# TODO

all:
	cd src && \
	javac csc515/plugin/*.java && \
	jar -cf ../build/csc515-plugin.jar csc515 META-INF
