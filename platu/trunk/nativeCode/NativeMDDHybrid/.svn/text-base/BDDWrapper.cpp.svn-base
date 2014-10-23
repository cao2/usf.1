#include "BDDWrapper.h"
#include <iostream>
#include <fstream>
#include <cassert>
#include <stdint.h>

//#undef CHECK_AFTER_ADD
//#define CHECK_AFTER_ADD

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

//adds the element to set
//Assumes that the contains check is performed elsewhere
void BDDSetWrap::addElement(int incArray[])
{
    std::vector<uint32_t> natArray(incArray, incArray + depth );
    numUniqueElements++;
    natArraySet.addElement(natArray);
}

void BDDSetWrap::addNode(BDD newNode)
{
    natArraySet.addNode(newNode);
}
//just checks that the argument exists within the set
bool BDDSetWrap::containsElement(int incArray[])
{
    std::vector<uint32_t> natArray(incArray, incArray + depth );
    return natArraySet.containsElement(natArray);
}

//returns the number of unique elements in the set
int BDDSetWrap::getSize()
{
    return numUniqueElements;
}

string BDDSetWrap::getStats()
{
    return "";
}

