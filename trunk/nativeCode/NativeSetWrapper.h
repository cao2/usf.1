/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
#include <tr1/unordered_set>
/* Header for class platu_NativeSet_NativeSetWrapper */

#ifndef _Included_platu_NativeSet_NativeSetWrapper
#define _Included_platu_NativeSet_NativeSetWrapper
#ifdef __cplusplus
extern "C" {
#endif
static jsize length = -1;

struct arrayHash
{
    size_t operator()(const jint* array) const
    {
        unsigned h = 0, g;
        int i;

        for(i = 0; i < length; i++)
        {
            h = (h << 4) + array[i];
            g = h & 0xf0000000L;

            if(g!=0)
                h ^= g >> 24;
            h &= ~g;
        }

        return h;
    }
};

struct arrayEq
{
    bool operator()(const jint* array1, const jint* array2) const
    {
        for(int i = 0; i < length; i++)
        {
            if(array1[i] != array2[i])
            {
                return false;
            }
        }

        return true;
    }
};



static std::tr1::unordered_set<const jint*, arrayHash, arrayEq> arrayStorage;

static double memSize = 0;

/*
 * Class:     platu_NativeSet_NativeSetWrapper
 * Method:    containsArray
 * Signature: ([I)Z
 */
JNIEXPORT jboolean JNICALL Java_platu_NativeLib_NativeSetWrapper_containsArray
  (JNIEnv *, jclass, jintArray);

/*
 * Class:     platu_NativeSet_NativeSetWrapper
 * Method:    addArray
 * Signature: ([I)V
 */
JNIEXPORT bool JNICALL Java_platu_NativeLib_NativeSetWrapper_addArray
  (JNIEnv *, jclass, jintArray);

/*
 * Class:     platu_NativeSet_NativeSetWrapper
 * Method:    removeArray
 * Signature: ([I)V
 */
JNIEXPORT void JNICALL Java_platu_NativeLib_NativeSetWrapper_removeArray
  (JNIEnv *, jclass, jintArray);

/*
 * Class:     platu_NativeSet_NativeSetWrapper
 * Method:    flushSet
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_platu_NativeLib_NativeSetWrapper_flushSet
  (JNIEnv *, jclass);

/*
 * Class:     platu_NativeSet_NativeSetWrapper
 * Method:    printSet
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_platu_NativeLib_NativeSetWrapper_printSet
  (JNIEnv *, jclass);

/*
 * Class:     platu_NativeSet_NativeSetWrapper
 * Method:    getSetSize
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_platu_NativeLib_NativeSetWrapper_getSetSize
  (JNIEnv *, jclass);

#ifdef __cplusplus
}
#endif
#endif