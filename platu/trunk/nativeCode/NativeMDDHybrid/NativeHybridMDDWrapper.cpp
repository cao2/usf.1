#include "NativeHybridMDDWrapper.h"
#include "../cudd-2.4.2/include/cuddObj.hh"
#include "NatArraySetEncodingByBdd.hpp"

#include <tr1/unordered_map>
#include <vector>
#include <assert.h>
#include <sstream>
#include <ctime>
#include <sys/resource.h>

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
    string stringAllStats();

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
  output << "Memory used by CUDD Package: " << bddMgr.ReadMemoryInUse() << endl;
  return output.str();
}

class MDD
{
    public:
        typedef struct MDDNode
        {
            //int myValue;
            std::tr1::unordered_map<int, MDDNode*> myChildren;
        } MDDNode;

        MDD(int maxDepth, bool isHybrid, std::vector<uint32_t> numBitsCol) : natArraySet(maxDepth, numBitsCol), cache(maxDepth)
        {
            this->maxDepth = maxDepth;
            numElements = 0;
            numberOfNodes = 0;
            hybrid = isHybrid;
            myNode = myMgr.getZero();

            addTime = 0;
            containsTime = 0;
            flushTime = 0;

        }

        ~MDD()
        {

        };
        void addNumber(int[]);
        bool contains(int[]);
        int size();
        string getStats();
        long getMemSize();

    private:
        uint32_t numElements;
        BDD flush(std::tr1::unordered_map<int, MDDNode*>* mapToFlush, int level);
        BDD toCube(int number, int level);
        BDD toCube(int array[]);

        MDDNode* nextPointer();

        MDDNode headNode;
        MDDNode terminalNode;
        std::vector<MDDNode*> unusedNodes;
        int numberOfNodes;
        int maxDepth;

        bool hybrid;

        CuddWrap myMgr;
        BDD myNode;

        NatArraySetEncodedByBool<CuddWrap,BDD> natArraySet;
        std::vector< std::tr1::unordered_map<int,BDD> > cache;
        long addTime, containsTime, flushTime;

};

void MDD::addNumber(int numToAdd[])
{
    struct rusage myTiming;
    getrusage(RUSAGE_SELF, &myTiming);
    double start = myTiming.ru_utime.tv_sec;

    if(hybrid)
    {
        MDDNode* curNode = &headNode;
        //std::cout << headNode.myValue << std::endl;
        int level = 0;
        bool elementFound = false;

        while(true)
        {
            if(curNode->myChildren.find(numToAdd[level]) != curNode->myChildren.end())
            {
                //std::cout << "Found element " << numToAdd[level] << std::endl;
                curNode = curNode->myChildren[numToAdd[level]];
                elementFound = true;
                //break;
            }

            if(!elementFound)
                break;

            elementFound = false;
            level++;
            if(level == maxDepth)
            {
                getrusage(RUSAGE_SELF, &myTiming);
                double end = myTiming.ru_utime.tv_sec;
                addTime += end-start;
                return;
            }

        }

        while(level < maxDepth)
        {
            //std::cout << "Adding element " << numToAdd[level] << std::endl;
            //std::cout << "Cur node value " << curNode->myValue << std::endl;
            if(level == maxDepth - 1)
            {
                curNode->myChildren[numToAdd[level]] = &terminalNode;
                level++;
            }

            else
            {
                MDDNode* nextNode = nextPointer();

                curNode->myChildren[numToAdd[level]] = nextNode;
                assert(curNode->myChildren.size() > 0);
                level++;
                curNode = nextNode;
            }

        }

        numElements++;

        if(numElements % 100000 == 0)
        {
            myNode = myNode + flush(&(headNode.myChildren), 0);

            // std::cout<< "Flush finished" << std::endl;
            assert(this->toCube(numToAdd) == myNode * toCube(numToAdd));
            //assert(this->size() > 0);
        }
    }

    else
    {
        std::vector<uint32_t> natArray(numToAdd, numToAdd + maxDepth );
        natArraySet.addElement(natArray);
        numElements++;
    }

    getrusage(RUSAGE_SELF, &myTiming);
    double end = myTiming.ru_utime.tv_sec;
    addTime += end-start;
}


bool MDD::contains(int numToCheck[])
{
    struct rusage myTiming;
    getrusage(RUSAGE_SELF, &myTiming);
    double start = myTiming.ru_utime.tv_sec;

    std::vector<uint32_t> natArray(numToCheck, numToCheck + maxDepth );

    if(!hybrid){

        getrusage(RUSAGE_SELF, &myTiming);
        double end = myTiming.ru_utime.tv_sec;
        containsTime += end-start;
        return natArraySet.containsElement(natArray);
        }

    else if(hybrid)
    {
        MDDNode* curNode = &headNode;
        int level = 0;
        bool elementFound = false;
        while(true)
        {
            //std::cout << "Children of current node:" <<  curNode->myChildren.size() << std::endl;
            //std::cout << "Value of the current node:" << curNode->myValue << std::endl;
            if(curNode->myChildren.find(numToCheck[level]) != curNode->myChildren.end())
            {
                //std::cout << "Found element " << numToAdd[level] << std::endl;
                curNode = curNode->myChildren[numToCheck[level]];
                elementFound = true;
                //break;
            }

            if(!elementFound)
                break;

            elementFound = false;
            level++;
            if(level == maxDepth){
                std::time_t finish = std::time(NULL);
                containsTime += difftime(finish, start);
                return true;}
        }

        BDD cube = toCube(numToCheck);
        getrusage(RUSAGE_SELF, &myTiming);
        double end = myTiming.ru_utime.tv_sec;
        containsTime += end-start;
        return cube == cube * myNode;
    }

    getrusage(RUSAGE_SELF, &myTiming);
    double end = myTiming.ru_utime.tv_sec;
    containsTime += end-start;
    return false;
}

int MDD::size()
{
    return numElements;
}

long MDD::getMemSize()
{
    struct rusage mytiming;
    getrusage(RUSAGE_SELF, &mytiming);

    long memUse = mytiming.ru_maxrss/1024;
}

string MDD::getStats()
{
    std::stringstream ss;//create a stringstream
    ss <<  "Number of nodes: " << numberOfNodes << std::endl;
    ss << "Seconds spent in Contains: " << containsTime << std::endl;
    ss << "Seconds spent in Add: " <<  (addTime - flushTime) <<std::endl;
    ss << "Seconds spent in Flush: " << flushTime << std::endl;
    ss << "Memory Usage: " << getMemSize() << " MB" << std::endl;
    return ss.str();
}

//#TODO: needs to be rewritten with a hashmap
BDD MDD::flush(std::tr1::unordered_map<int, MDDNode*>* mapToFlush, int level)
{
    struct rusage myTiming;
    getrusage(RUSAGE_SELF, &myTiming);
    double start = myTiming.ru_utime.tv_sec;

    BDD myBool = myMgr.getZero();
    //std::cout << "Flushing diagram..." << std::endl;
    //call for all children
    for(std::tr1::unordered_map<int, MDDNode*>::iterator it = mapToFlush->begin(); it != mapToFlush->end(); ++it)
    {
        BDD temp = flush(&(it->second->myChildren), level+1);
        BDD thisNode = toCube(it->first, level);
        BDD returnVal = thisNode * temp;

        myBool = myBool + returnVal;
        if((unsigned long) (it->second) != (unsigned long) &terminalNode)
        {
            unusedNodes.push_back(it->second);
        }
    }

    if(mapToFlush->size() == 0)
        myBool = myMgr.getOne();
    //clear this hashmap
    //std::cout << "Clearing hashmap..." << std::endl;
    mapToFlush->clear();

    getrusage(RUSAGE_SELF, &myTiming);
    double end = myTiming.ru_utime.tv_sec;
    flushTime += end-start;
    return myBool;
}

BDD MDD::toCube(int array[])
{
    BDD thisCube = myMgr.getOne();
    for(int i = 0; i < maxDepth; i++)
    {
        thisCube = thisCube * toCube(array[i], i);
    }
    return thisCube;
}

BDD MDD::toCube(int number, int level)
{
    BDD thisCube = myMgr.getOne();
    int base = 32 * level;
    std::tr1::unordered_map<int,BDD>::const_iterator iter = cache[level].find(number);

    if(iter != cache[level].end())
    {
        thisCube =thisCube * iter->second;
    }

    else
    {
        int numberShift = number;
        for(int j = 0; j < 32; ++j)
        {
            BDD v = (numberShift & 1) ?
                            myMgr.getIthVar(base + j) :
                            !myMgr.getIthVar(base + j);
            thisCube = thisCube * v;
            numberShift >>= 1;
        }

        cache[level][number] = thisCube;
    }
    return thisCube;
}

MDD::MDDNode* MDD::nextPointer()
{
    if(unusedNodes.size() == 0)
    {
        numberOfNodes++;
        return new MDDNode();
    }

    MDDNode* output = unusedNodes.back();
    unusedNodes.pop_back();
    output->myChildren.clear();
    return output;
}


static MDD* myMDDStructure;

/*
 * Class:     platu_NativeLib_NativeHybridMDDWrapper
 * Method:    add
 * Signature: ([I)I
 */
JNIEXPORT jint JNICALL Java_platu_NativeLib_NativeHybridMDDWrapper_add
  (JNIEnv *env, jobject obj, jintArray numArray)
{
    int arraySize = env->GetArrayLength(numArray);

    jint buffer[arraySize];

    env->GetIntArrayRegion(numArray, 0, arraySize, buffer);

    myMDDStructure->addNumber(buffer);

    return -1;
}

/*
 * Class:     platu_NativeLib_NativeHybridMDDWrapper
 * Method:    contains
 * Signature: ([I)Z
 */
JNIEXPORT jboolean JNICALL Java_platu_NativeLib_NativeHybridMDDWrapper_contains
  (JNIEnv *env, jobject obj, jintArray numArray)
{
    int arraySize = env->GetArrayLength(numArray);

    jint buffer[arraySize];

    env->GetIntArrayRegion(numArray, 0, arraySize, buffer);

    return myMDDStructure->contains(buffer);
}

/*
 * Class:     platu_NativeLib_NativeHybridMDDWrapper
 * Method:    size
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_platu_NativeLib_NativeHybridMDDWrapper_size
  (JNIEnv *env, jobject obj)
{
    return myMDDStructure->size();
}

/*
 * Class:     platu_NativeLib_NativeHybridMDDWrapper
 * Method:    stats
 * Signature: ()Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_platu_NativeLib_NativeHybridMDDWrapper_stats
  (JNIEnv *env, jobject obj)
{
    return env->NewStringUTF(myMDDStructure->getStats().c_str());
}

JNIEXPORT void JNICALL Java_platu_NativeLib_NativeHybridMDDWrapper_init
  (JNIEnv *env, jobject obj, jint depth, jboolean hybrid, jintArray bits)
{
    if (myMDDStructure != NULL)
        myMDDStructure->~MDD();

    jint buffer[depth];

    env->GetIntArrayRegion(bits, 0, depth, buffer);

    std::vector<uint32_t> bitsVec( (int*) buffer, ((int*) buffer) + depth);

    myMDDStructure = new MDD(depth, hybrid, bitsVec);
}

