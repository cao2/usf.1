#ifndef BINARYTREE_H_
#define BINARYTREE_H_

using namespace std;

#include <vector>
#include <string>
#include <tr1/unordered_set>
#include <sys/resource.h>
#include "BtreeNode.h"


typedef tr1::unordered_set<BtreeNode*, BtreeNodeHash, BtreeNodeEq> BtreeNodeSet;


class BinaryTree {
public:

	BinaryTree() { this->rootCount = 0;
        addTime = 0;
        containsTime = 0;
        memUse = sizeof(BinaryTree);}
	~BinaryTree() {}

	/*	Add the int vector into this BinaryTree.
		IdxArray must include even number of integers. */
	BtreeNode*	add(const vector<int>& IdxArray);

	/*	Check if the given int vector is contained in this BinaryTree.
		IdxArray must include even number of integers. */
	bool		contains(const vector<int>& IdxArray);

	string stats();

	/*	Return the total number of nodes currently used.	*/
	uint32_t	nodeCount() { return this->terminalTbl.size() + this->nodeTbl.size(); }

	/*	Return the number of vectors currently stored.	*/
	uint32_t	elementCount() { return this->rootCount; }

	long getMemSize()
    {
        struct rusage mytiming;
        getrusage(RUSAGE_SELF, &mytiming);

        long memUse = mytiming.ru_maxrss/1024;
    }


private:
	BtreeNode*	getNode();
	BtreeNode*	addNode(BtreeNode* node);
	BtreeNode*	addTerminal(BtreeNode* node);


private:

	/* Tables for storing terminal and the non-terminal nodes. */
	BtreeNodeSet terminalTbl;
	BtreeNodeSet nodeTbl;

	/* Table of unused nodes that can be recycled. */
	vector<BtreeNode*> deadNodeTbl;

	/* The number of root nodes. */
	uint32_t rootCount;
	double addTime, containsTime;
	long memUse;
};

#endif
