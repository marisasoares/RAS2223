make:
	javac $(shell find . -name "*.java") -d ./bin
run:
	java -cp ./bin Main
doc:
	javadoc $(shell find . -name "*.java") -d ./doc