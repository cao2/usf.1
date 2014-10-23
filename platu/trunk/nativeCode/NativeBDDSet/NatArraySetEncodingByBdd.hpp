#ifndef NATARRAYSETENCBYBOOL_HHH_
#define NATARRAYSETENCBYBOOL_HHH_

#include <stdint.h>
#include <vector>
#include <cassert>
#include <iostream>
#include <tr1/unordered_map>
#include <cstdio>

#include <climits>
#include <sstream>
#include <string>
template <typename BoolMgr, typename BoolNode> class NatArraySetEncodedByBool
{
  public:
    NatArraySetEncodedByBool(uint32_t numEntries, std::vector<uint32_t>& numBitsCol)
      : mgr(), setVec(), encodingVars(numEntries), cache(numEntries)
    {
      init(numBitsCol);
    }
    ~NatArraySetEncodedByBool()
    {
      std::cerr << "NatArraySetEncodedByBdd Stats: " << std::endl;
      std::cerr << "  Number of sets: " << setVec.size() << std::endl;
    }

    void addElement(std::vector<uint32_t>& natArray);
    bool containsElement(std::vector<uint32_t>& natArray);

    void printAllStats() { mgr.printAllStats(); }
    std::string stringAllStats() {return mgr.stringAllStats(); }

  private:
    BoolMgr mgr;
    std::vector<BoolNode> setVec;
    std::vector< std::vector<BoolNode> > encodingVars;
    std::vector< std::tr1::unordered_map<uint32_t,BoolNode> > cache;
    static const uint32_t maxLen = 100;
    static const int32_t nodeThreshold = 10000;

    void init(std::vector<uint32_t>& numBitsCol);
    uint32_t getNumBits(uint32_t num);
    void redoSort(uint32_t i);
    BoolNode toCube(std::vector<uint32_t>& natArray);
};

template <typename BoolMgr, typename BoolNode>
  void NatArraySetEncodedByBool<BoolMgr, BoolNode>
  ::redoSort(uint32_t i)
{
  int nodeCount = setVec[i].nodeCount();
  if( i > 0 && nodeCount < setVec[i-1].nodeCount() )
  {
    BoolNode temp = setVec[i];
    setVec[i] = setVec[i-1];
    setVec[i-1] = temp;

    uint32_t j = i-1;
    while( j > 0 ) {
      if( nodeCount < setVec[j-1].nodeCount() ) {
        BoolNode temp = setVec[j];
        setVec[j] = setVec[j-1];
        setVec[j-1] = temp;
        --j;
      } else {
        return;
      }
    }
  } else if( i < setVec.size()-1 && nodeCount > setVec[i+1].nodeCount() ) {
    BoolNode temp = setVec[i];
    setVec[i] = setVec[i+1];
    setVec[i+1] = temp;

    uint32_t j = i-1;
    while( j < setVec.size()-1 ) {
      if( nodeCount < setVec[j+1].nodeCount() ) {
        BoolNode temp = setVec[j];
        setVec[j] = setVec[j+1];
        setVec[j+1] = temp;
        ++j;
      } else {
        return;
      }
    }
  }
}

template <typename BoolMgr, typename BoolNode>
  void NatArraySetEncodedByBool<BoolMgr, BoolNode>
  ::addElement(std::vector<uint32_t>& natArray)
{
  BoolNode cube = toCube(natArray);

  for(uint32_t i = 0; i < setVec.size(); ++i)
  {
    BoolNode temp = setVec[i] + cube;
    if( temp == setVec[i] ) {
      return;
    } else if( temp.nodeCount() > nodeThreshold ) {
      continue;
    } else {
      setVec[i] = temp;
      redoSort(i);
      return;
    }
  }
  if( setVec.size() < maxLen ) {
    setVec.push_back(cube);
    redoSort(setVec.size()-1);
    return;
  } else {
    std::cout << "Size failure" << std::endl;
//    exit(0);
  }
}

template <typename BoolMgr, typename BoolNode>
  bool NatArraySetEncodedByBool<BoolMgr, BoolNode>
  ::containsElement(std::vector<uint32_t>& natArray)
{
  BoolNode cube = toCube(natArray);

  for(uint32_t i = 0; i < setVec.size(); ++i)
  {
    BoolNode temp = cube * setVec[i];
    if( temp == cube ) {
      return true;
    }
  }
  return false;
}

// ***********************************************************************
//    Private Methods
// ***********************************************************************

template <typename BoolMgr, typename BoolNode>
  void NatArraySetEncodedByBool<BoolMgr, BoolNode>
    ::init(std::vector<uint32_t>& numBitsCol)
{
  setVec.push_back(mgr.getZero());

  int var_index = 0;
  for(uint32_t i = 0; i < encodingVars.size(); ++i)
  {
    uint32_t numBits = numBitsCol[i];
    std::cout << "Col " << i << " num of bits: " << numBits << std::endl;
    while(numBits != 0) {
      encodingVars[i].push_back(mgr.getIthVar(var_index));
      ++var_index;
      --numBits;
    }
  }
}

template <typename BoolMgr, typename BoolNode>
  uint32_t NatArraySetEncodedByBool<BoolMgr, BoolNode>
  ::getNumBits(uint32_t num)
{
  uint32_t numBits = 0;
  while( num != 0 ) {
    ++numBits;
    num >>= 1;
  }
  return (numBits > 0) ? numBits : 1;
}

template <typename BoolMgr, typename BoolNode>
  BoolNode NatArraySetEncodedByBool<BoolMgr, BoolNode>
  ::toCube(std::vector<uint32_t>& natArray)
{
  BoolNode cube = mgr.getOne();
  assert( natArray.size() == encodingVars.size() );

  for(uint32_t i = 0; i < natArray.size(); ++i) {
    uint32_t nat = natArray[i];
    BoolNode cubei = mgr.getOne();
    typename std::tr1::unordered_map<uint32_t,BoolNode>::const_iterator iter = cache[i].find(nat);

    if( iter != cache[i].end() ) {
      cubei = iter->second;
    } else {
      uint32_t nat_shift = nat;
      for(uint32_t j = 0; j < encodingVars[i].size(); ++j) {
        BoolNode v = (nat_shift & 1) ?
                     encodingVars[i][j] :
                     !(encodingVars[i][j]);
        cubei = cubei * v;
        nat_shift >>= 1;
      }
      cache[i][nat] = cubei;
      assert( nat_shift == 0 );
    }
    cube = cube * cubei;
  }
  return cube;
}

#endif
