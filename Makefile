JAVA_HOME = /Library/Java/JavaVirtualMachines/jdk1.8.0_45.jdk/Contents/Home

EMPTY :=
SPACE := $(EMPTY) $(EMPTY)

CLASSPATH += target/dependency/*
CLASSPATH += target/classes
CLASSPATH += target/test-classes
CLASSPATH += src/test/resource
CLASSPATH += src/main/resource

JAVA_FLAG += -Djava.library.path=./jni

.PHONY: testNone testCompile clean copydependency

clean:
	mv -rf target/*classes

testCompile:
	mvn test-compile

copydependency:
	mvn dependency:copy-dependencies
#mvn exec:java -Dexec.classpathScope=test -Dexec.mainClass=com.diplab.activiti.testprocess.TestNoneMode

testNone: testCompile copydependency
	java -cp $(subst $(SPACE),:,$(CLASSPATH)) \
		com.diplab.activiti.testprocess.TestNoneMode

testRpi:
	java -cp $(subst $(SPACE),:,$(CLASSPATH)) \
		$(JAVA_FLAG) \
		com.diplab.rpi.RpiReceiverTest

testProcess:
	java -cp $(subst $(SPACE),:,$(CLASSPATH)) \
		$(JAVA_FLAG) \
		com.diplab.activiti.testprocess.TestGreaterMode

compile: testCompile
	make -C jni libRpi.jnilib

test:
	@echo 