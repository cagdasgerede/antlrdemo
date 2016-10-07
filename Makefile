ANTLR4=java -jar /usr/local/lib/antlr-4.5.3-complete.jar
GRUN=java -cp "$$CLASSPATH:./output" org.antlr.v4.gui.TestRig 

all: Hello.class

HelloParser.java: grammar/Hello.g4
	mkdir -p antlrgenerated
	cp grammar/Hello.g4 antlrgenerated/
	$(ANTLR4) antlrgenerated/Hello.g4

Hello.class: HelloParser.java src/Hello.java src/HelloWalker.java
	mkdir -p output
	javac src/Hello*.java -sourcepath antlrgenerated/ -d output/

test: Hello.class
	java -cp "$$CLASSPATH:./output" Hello test.txt

clean:
	if [ -d "output" ]; then rm -r output; fi
	if [ -d "antlrgenerated" ]; then rm -r antlrgenerated; fi

testgui: Hello.class
	$(GRUN) Hello r -gui test.txt 
