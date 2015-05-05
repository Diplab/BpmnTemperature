# Define a virtual path for .class in the bin directory
vpath %.class CLASS_PATH = ../target/classes  ../target/test-classes

CLASS += com.diplab.activiti.smoke.RpiSmokeReceiver
CLASS += com.diplab.activiti.temperature.RpiTemperatureReceiver

# $* matches the target filename without the extension
rpi.h: com/diplab/activiti/smoke/RpiSmokeReceiver.class
	javah -cp ../target/classes:../target/test-classes \
		-o rpi.h \
		$(CLASS)

# Mac
#-I/System/Library/Frameworks/JavaVM.framework/Headers \

libRpi.jnilib: rpi.h rpi.c
	gcc -o libRpi.jnilib -lc -shared \
		-I $(JAVA_HOME)/include \
		-I $(JAVA_HOME)/include/darwin/ \
		rpi.c

libRpi.so:	rpi.h rpi.c
	# Linux
	gcc -o libRpi.so -lc -shared -fPIC \
		-I $(JAVA_HOME)/include \
		-I $(JAVA_HOME)/include/linux/ \
		rpi.c



