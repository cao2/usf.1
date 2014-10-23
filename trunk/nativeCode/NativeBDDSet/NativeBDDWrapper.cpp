#include "NativeBDDWrapper.h"
#include "NatArraySetEncodingByBdd.hpp"
#include <iostream>
#include <fstream>
#include <cassert>
#include <stdint.h>
#include "../cudd-2.4.2/include/cuddObj.hh"

#include <sstream>
#include <string>

//#undef CHECK_AFTER_ADD
#define CHECK_AFTER_ADD

class CuddWrap
{
  public:
    CuddWrap() : bddMgr()
    {
      bddMgr.AutodynEnable(CUDD_REORDER_SIFT);
      //bddMgr.AutodynDisable();
    }

    ~CuddWrap()
    {
      printAllStats();
    }

    BDD getIthVar(uint32_t i) { return bddMgr.bddVar(i); }

    BDD getZero() { return bddMgr.bddZero(); }
    BDD getOne() { return bddMgr.bddOne(); }

    void printAllStats();
    std::string stringAllStats();
  private:
    Cudd bddMgr;
};

void CuddWrap::printAllStats()
{
  std::cerr << "Peak number of nodes: " << bddMgr.ReadPeakNodeCount() << std::endl;
  std::cerr << "Number of reorders: " << bddMgr.ReadReorderings() << std::endl;
  std::cerr << "Time spent on reorders (ms): " << bddMgr.ReadReorderingTime() << std::endl;
  std::cerr << "Number of GCs: " << bddMgr.ReadGarbageCollections() << std::endl;
  std::cerr << "Time spent on GC (ms): " << bddMgr.ReadGarbageCollectionTime() << std::endl;
}

std::string CuddWrap::stringAllStats()
{
  std::stringstream output;
  output << "Peak number of nodes: " << bddMgr.ReadPeakNodeCount() << std::endl;
  output << "Number of reorders: " << bddMgr.ReadReorderings() << std::endl;
  output << "Time spent on reorders (ms): " << bddMgr.ReadReorderingTime() << std::endl;
  output << "Number of GCs: " << bddMgr.ReadGarbageCollections() << std::endl;
  output << "Time spent on GC (ms): " << bddMgr.ReadGarbageCollectionTime() << std::endl;
  return output.str();
}

class BDDSetWrap
{
    public:
        BDDSetWrap(uint32_t size, std::vector<uint32_t> bits) : natArraySet(size, bits)
        {
            numUniqueElements = 0;
        }

        ~BDDSetWrap()
        {
            natArraySet.printAllStats();
        }

        void addElement(std::vector<uint32_t>& natArray);
        bool containsElement(std::vector<uint32_t>& natArray);
        int getSize();
        string getStats();

    private:
        NatArraySetEncodedByBool<CuddWrap,BDD> natArraySet;
        uint32_t numUniqueElements;
};

//adds the element to set
//Assumes that the contains check is performed elsewhere
void BDDSetWrap::addElement(std::vector<uint32_t>& natArray)
{
    numUniqueElements++;
    natArraySet.addElement(natArray);
}

//just checks that the argument exists within the set
bool BDDSetWrap::containsElement(std::vector<uint32_t> &natArray)
{
    return natArraySet.containsElement(natArray);
}

//returns the number of unique elements in the set
int BDDSetWrap::getSize()
{
    return numUniqueElements;
}

std::string BDDSetWrap::getStats()
{
    return natArraySet.stringAllStats();
}

static BDDSetWrap *myBDDSet = NULL;

/*
 * Class:     platu_NativeLib_NativeBDDWrapper
 * Method:    add
 * Signature: ([I)I
 */
JNIEXPORT jint JNICALL Java_platu_NativeLib_NativeBDDWrapper_add(JNIEnv *env, jobject obj, jintArray jArray)
{
    //Rewrite to work with jint array, not vector
    int arraySize = env->GetArrayLength(jArray);

    jint buffer[arraySize];

    env->GetIntArrayRegion(jArray, 0, arraySize, buffer);

    //std::vector<uint32_t> natArray(buffer);
    std::vector<uint32_t> natArray(buffer, buffer + sizeof(buffer) / sizeof(jint) );


    myBDDSet->addElement(natArray);
    return -1;
}

/*
 * Class:     platu_NativeLib_NativeBDDWrapper
 * Method:    contains
 * Signature: ([I)Z
 */
JNIEXPORT jboolean JNICALL Java_platu_NativeLib_NativeBDDWrapper_contains(JNIEnv *env, jobject obj, jintArray jArray)
{
    //Converts that java array to a vector
    //and calls the contains fucntion for the wrapped
    //NatSetEncodeBDD template
    int arraySize = env->GetArrayLength(jArray);

    jint buffer[arraySize];

    env->GetIntArrayRegion(jArray, 0, arraySize, buffer);

    std::vector<uint32_t> natArray(buffer, buffer + sizeof(buffer) / sizeof(jint) );

    return myBDDSet->containsElement(natArray);
}

/*
 * Class:     platu_NativeLib_NativeBDDWrapper
 * Method:    size
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_platu_NativeLib_NativeBDDWrapper_size(JNIEnv *env, jobject obj)
{
    //Somewhat incomplete
    return myBDDSet->getSize();
}

/*
 * Class:     platu_NativeLib_NativeBDDWrapper
 * Method:    stats
 * Signature: ()Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_platu_NativeLib_NativeBDDWrapper_stats(JNIEnv *env, jobject obj)
{
    return env->NewStringUTF(myBDDSet->getStats().c_str());
}


/*
 * Class:     platu_NativeLib_NativeBDDWrapper
 * Method:    initBDDSet
 * Signature: (I[I)V
 */
JNIEXPORT void JNICALL Java_platu_NativeLib_NativeBDDWrapper_initBDDSet
  (JNIEnv *env, jobject obj, jint size, jintArray bits)
{
    if (myBDDSet != NULL)
        myBDDSet->~BDDSetWrap();

    jint buffer[size];

    env->GetIntArrayRegion(bits, 0, size, buffer);

    std::vector<uint32_t> bitsVec( (int*) buffer, ((int*) buffer) + size);



    myBDDSet = new BDDSetWrap(size, bitsVec);
}
