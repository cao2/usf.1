#include <iostream>
#include <sstream>
#include <ctime>
#include <sys/resource.h>
#include "NativeSetWrapper.h"

using namespace std;

class NativeHashStorage
{

    public:
            NativeHashStorage(int l)
            {
               length = l;
               addTime = 0;
               containsTime = 0;
            }
            int getSize();
            int addArray(jint*);
            bool contains(jint*);
            long getMemSize();
            string getStats();
            static int length;
    private:
            int numElements;
            long addTime, containsTime;


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
    std::tr1::unordered_set<const jint*, arrayHash, arrayEq> arrayStorage;

};

int NativeHashStorage::length = 0;

int NativeHashStorage::getSize()
{
    return numElements;
}

int NativeHashStorage::addArray(jint* newArray)
{
    struct rusage myTiming;
    getrusage(RUSAGE_SELF, &myTiming);
    double start = myTiming.ru_utime.tv_sec;

    arrayStorage.insert(newArray);
    numElements++;

    getrusage(RUSAGE_SELF, &myTiming);
    double end = myTiming.ru_utime.tv_sec;

    addTime += end-start;
    return this->getSize();
}

bool NativeHashStorage::contains(jint* findArray)
{
    struct rusage myTiming;
    getrusage(RUSAGE_SELF, &myTiming);
    double start = myTiming.ru_utime.tv_sec;

    if(arrayStorage.find(findArray) == arrayStorage.end())
    {
        //Array not found within the set
        //delete buffer;
        getrusage(RUSAGE_SELF, &myTiming);
        std::time_t end = myTiming.ru_utime.tv_sec;
        containsTime += end-start;
        return false;
    }
    //Array is found within the set
    //delete buffer;
    getrusage(RUSAGE_SELF, &myTiming);
    std::time_t end = myTiming.ru_utime.tv_sec;
    containsTime += end-start;
    return true;
}

long NativeHashStorage::getMemSize()
{
    struct rusage mytiming;
    getrusage(RUSAGE_SELF, &mytiming);

    long memUse = mytiming.ru_maxrss/1024;
}

string NativeHashStorage::getStats()
{


    std::stringstream output;

    output << "Elements found: " << numElements << endl;
    output << "Memory Used (MB): " << getMemSize() << endl;
    output << "Seconds in Add function: " << addTime << endl;
    output << "Seconds in Contains funciton: " << containsTime << endl;

    return output.str();
}



static NativeHashStorage* hashStore = NULL;

JNIEXPORT jboolean JNICALL Java_platu_NativeLib_NativeSetWrapper_contains
  (JNIEnv *env, jobject obj, jintArray findMe)
{
    int length = env->GetArrayLength(findMe);

	//jint *buffer = new jint[length];
	jint buffer[length];

    env->GetIntArrayRegion(findMe, 0, length, buffer);

    return hashStore->contains(buffer);
}

JNIEXPORT jint JNICALL Java_platu_NativeLib_NativeSetWrapper_add
  (JNIEnv *env, jobject obj, jintArray addMe)
  {
    int length = env->GetArrayLength(addMe);

    if(hashStore == NULL)
        hashStore = new NativeHashStorage(length);

    jint *buffer = new jint[length];

    env->GetIntArrayRegion(addMe, 0, length, buffer);

   return hashStore->addArray(buffer);
}

JNIEXPORT jint JNICALL Java_platu_NativeLib_NativeSetWrapper_size
  (JNIEnv *env, jobject obj)
  {
    if(hashStore == NULL)
        return 0;
    return hashStore->getSize();
  }


JNIEXPORT jstring JNICALL Java_platu_NativeLib_NativeSetWrapper_stats
  (JNIEnv *env, jobject obj)
  {
    return env->NewStringUTF(hashStore->getStats().c_str());
  }
