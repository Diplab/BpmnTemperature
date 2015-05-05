#include <stdio.h>
#include <stdlib.h>
#include "rpi.h"
#include <time.h>

JNIEXPORT jdouble JNICALL Java_com_diplab_activiti_smoke_RpiSmokeReceiver_getSmokePpm (JNIEnv* env, jclass clazz)
{
	srand(time(NULL));
	return (rand()%100)+200;
}

JNIEXPORT jdouble JNICALL Java_com_diplab_activiti_temperature_RpiTemperatureReceiver_getTemperatureCelsius(JNIEnv* env, jclass clazz){
	srand(time(NULL));
	return (rand()%20)+10;
}
