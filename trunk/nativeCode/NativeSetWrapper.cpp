#include <iostream>
#include "NativeSetWrapper.h"

using namespace std;

JNIEXPORT jboolean JNICALL Java_platu_NativeLib_NativeSetWrapper_containsArray
  (JNIEnv *env, jclass cls, jintArray array)
{
    if(length == -1)
    {
        length = env->GetArrayLength(array);
    }

	//jint *buffer = new jint[length];
	jint buffer[length];
	
    env->GetIntArrayRegion(array, 0, length, buffer);

    if(arrayStorage.find(buffer) == arrayStorage.end())
    {
        //Array not found within the set
        //delete buffer;
        return false;
    }
    //Array is found within the set
    //delete buffer;
    return true;
}

JNIEXPORT bool JNICALL Java_platu_NativeLib_NativeSetWrapper_addArray
  (JNIEnv *env, jclass cls, jintArray array)
{
    if(length == -1)
    {
        length = env->GetArrayLength(array);
    }

    jint *buffer = new jint[length];
	
    //cout << "function call happens";

    env->GetIntArrayRegion(array, 0, length, buffer);

    //std::tr1::unordered_set<const jint*, arrayHash, arrayEq>::iterator iter = arrayStorage.insert(buffer);
	if((arrayStorage.insert(buffer)).first == arrayStorage.end())
    {
        //Array not found within the set
        delete buffer;
        exit(0);
    }
	
	memSize += sizeof(jint) * length;
	return true;
}

JNIEXPORT void JNICALL Java_platu_NativeLib_NativeSetWrapper_removeArray
  (JNIEnv *env, jclass cls, jintArray array)
{
    if(length == -1)
    {
        length = env->GetArrayLength(array);
    }

    jint *buffer = new jint[length];

    env->GetIntArrayRegion(array, 0, length, buffer);

    arrayStorage.erase(buffer);

    delete buffer;
}

JNIEXPORT void JNICALL Java_platu_NativeLib_NativeSetWrapper_flushSet
  (JNIEnv *, jclass)
{
    arrayStorage.clear();
	cout << memSize << endl;
	memSize = 0;
}


JNIEXPORT void JNICALL Java_platu_NativeLib_NativeSetWrapper_printSet
  (JNIEnv *, jclass)
{
    tr1::unordered_set<const jint*, arrayHash, arrayEq>::iterator iter = arrayStorage.begin();
    //cout << "Printing all elements" << endl;
    while(iter != arrayStorage.end())
    {
        for(int i = 0; i < length; i++)
        {
            cout << (*iter)[i] << " ";
        }

        cout << endl;
        iter++;
    }
}

/*
 * Class:     platu_NativeSet_NativeSetWrapper
 * Method:    getSetSize
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_platu_NativeLib_NativeSetWrapper_getSetSize
  (JNIEnv *env, jclass cls)
{
    int size = 0;

    size = arrayStorage.size();

    return size;
}
