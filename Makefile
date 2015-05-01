.PHONY: testNone testCompile clean

clean:
	mvn clean

testCompile:
	mvn test-compile

testNone: testCompile
	mvn exec:java -Dexec.classpathScope=test -Dexec.mainClass=com.diplab.activiti.testprocess.TestNoneMode