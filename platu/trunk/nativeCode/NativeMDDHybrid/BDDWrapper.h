#include "NatArraySetEncodingByBdd.hpp"
#include "cudd-2.4.2/include/cuddObj.hh"
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

class BDDSetWrap
{
    public:
        BDDSetWrap(uint32_t size) : natArraySet(size)
        {
            numUniqueElements = 0;
            depth = size;
        }

        ~BDDSetWrap()
        {
            natArraySet.stringAllStats();
        }

        void addElement(int incArray[]);
        bool containsElement(int incArray[]);
        int getSize();
        string getStats();

        void addNode(BDD newNode);

    private:
        NatArraySetEncodedByBool<CuddWrap,BDD> natArraySet;
        uint32_t numUniqueElements;
        uint32_t depth;
};
