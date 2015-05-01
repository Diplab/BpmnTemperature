.PHONY: testNone

testNone:
	mvn exec:java -Dexec.classpathScope=test -Dexec.mainClass=com.diplab.activiti.testprocess.TestNoneMode