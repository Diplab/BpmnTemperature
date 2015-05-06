#JAVA_HOME = /Library/Java/JavaVirtualMachines/jdk1.8.0_45.jdk/Contents/Home
JAVA_HOME = /usr/lib/jvm/jdk-8-oracle-arm-vfp-hflt
EMPTY :=
SPACE := $(EMPTY) $(EMPTY)

CLASSPATH += target/dependency/*
CLASSPATH += target/classes
CLASSPATH += target/test-classes
CLASSPATH += src/test/resource
CLASSPATH += src/main/resource

JAVA_FLAG += -Djava.library.path=./jni

.PHONY: clean environment testRpi testNone testProcess

clean:
	rm -rf target/*classes target/libRpi.*

environment_Mac:
	mvn test-compile
	mvn dependency:copy-dependencies
	make -C jni libRpi.jnilib

environment_RPi:
	mvn test-compile
	mvn dependency:copy-dependencies
	make -C jni libRpi.jnilib
	make -C jni libRpi.so
	
#mvn exec:java -Dexec.classpathScope=test -Dexec.mainClass=com.diplab.activiti.testprocess.TestNoneMode

testNone: 
	java -cp $(subst $(SPACE),:,$(CLASSPATH)) \
		$(JAVA_FLAG) \
		com.diplab.activiti.testprocess.TestNoneMode

testRpi: 
	java -cp $(subst $(SPACE),:,$(CLASSPATH)) \
		$(JAVA_FLAG) \
		com.diplab.rpi.RpiReceiverTest

testProcess: 
	java -cp $(subst $(SPACE),:,$(CLASSPATH)) \
		$(JAVA_FLAG) \
		com.diplab.activiti.testprocess.TestGreaterMode

test:
	@echo 
