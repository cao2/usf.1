#include <vector>
#include <stdio.h>
#include <iostream>
#include <sstream>


#include "NativeBinaryTree.h"
#include "BinaryTree.h"


static BinaryTree bTree;

JNIEXPORT jint JNICALL Java_platu_NativeLib_NativeBinaryTree_add
  (JNIEnv *env, jobject obj, jintArray newArray)
  {
    int arraySize = env->GetArrayLength(newArray);
    jint buffer[arraySize];

    env->GetIntArrayRegion(newArray, 0, arraySize, buffer);

    std::vector<int> arrayVec( (int*) buffer, ((int*) buffer) + arraySize);

    bTree.add(arrayVec);
  }

/*
 * Class:     platu_NativeLib_NativeBinaryTree
 * Method:    contains
 * Signature: ([I)Z
 */
JNIEXPORT jboolean JNICALL Java_platu_NativeLib_NativeBinaryTree_contains
  (JNIEnv *env, jobject obj, jintArray containsArray)
  {
    int arraySize = env->GetArrayLength(containsArray);
    jint buffer[arraySize];

    env->GetIntArrayRegion(containsArray, 0, arraySize, buffer);

    std::vector<int> arrayVec( (int*) buffer, ((int*) buffer) + arraySize);

    return bTree.contains(arrayVec);
  }

/*
 * Class:     platu_NativeLib_NativeBinaryTree
 * Method:    size
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_platu_NativeLib_NativeBinaryTree_size
  (JNIEnv *env, jobject obj)
  {
    return bTree.nodeCount();
  }

/*
 * Class:     platu_NativeLib_NativeBinaryTree
 * Method:    stats
 * Signature: ()Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_platu_NativeLib_NativeBinaryTree_stats
  (JNIEnv *env, jobject obj)
  {
    std::stringstream ss;//create a stringstream
    ss <<  "Number of nodes: " << bTree.nodeCount() << std::endl;//add number to the stream
    ss << bTree.stats() << std::endl;
    return env->NewStringUTF(ss.str().c_str());
  }
