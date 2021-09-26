#Used with the code from exercise 3

all : clean createClasses runTest run

createClasses : clean
	javac -cp lib/junit-4.12.jar:lib/hamcrest-core-1.3.jar src/*/java/*.java -d build

runTest : clean createClasses 
	java -cp ./build:lib/junit-4.12.jar:lib/hamcrest-core-1.3.jar org.junit.runner.JUnitCore EdgeConnectorTest

run : clean createClasses
	java -cp ./build RunEdgeConvert

clean : 
	rm -f build/*.class