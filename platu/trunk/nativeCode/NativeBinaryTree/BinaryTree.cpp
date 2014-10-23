#include <iostream>
#include <ctime>
#include <sstream>

#include "BinaryTree.h"

BtreeNode* BinaryTree::add(const vector<int>& IdxArray)
{
    struct rusage myTiming;
    getrusage(RUSAGE_SELF, &myTiming);
    double start = myTiming.ru_utime.tv_sec;

	uint32_t size = IdxArray.size();
	uint32_t iter = 0;
	vector<BtreeNode*> nodeVec;

	/*	Build terminal nodes for IdxArray.	*/
	while (iter < size) {
		BtreeNode *left;
		BtreeNode *right;

		int l = IdxArray[iter];
		iter++;
		int r = IdxArray[iter];
		iter++;

		left = (BtreeNode*) l;
		right = (BtreeNode*) r;

		BtreeNode* terminal = this->getNode();
		terminal->setLeft(left);
		terminal->setRight(right);
		terminal = this->addTerminal(terminal);
		nodeVec.push_back(terminal);
 	}

	/*	Iteratively build nodes until a single node is left.	*/
	while (nodeVec.size() > 1) {
		vector<BtreeNode*> newNodeVec;

		while (nodeVec.size() > 1) {
			BtreeNode* n1 = nodeVec.back();
			nodeVec.pop_back();
			BtreeNode* n2 = nodeVec.back();
			nodeVec.pop_back();

			BtreeNode* node = this->getNode();
			node->setLeft(n1);
			node->setRight(n2);
			node = this->addNode(node);
			newNodeVec.push_back(node);
		}

		if (nodeVec.size() == 1) {
			BtreeNode* node = nodeVec.back();
			newNodeVec.push_back(node);
		}

		nodeVec = newNodeVec;

	}

	assert(nodeVec.size() == 1);

	this->rootCount++;
	getrusage(RUSAGE_SELF, &myTiming);
    double end = myTiming.ru_utime.tv_sec;
	addTime += end-start;
	return nodeVec.back();
}


bool BinaryTree::contains(const vector<int>& IdxArray)
{
    struct rusage myTiming;
    getrusage(RUSAGE_SELF, &myTiming);
    double start = myTiming.ru_utime.tv_sec;

	bool newNode = false;
	uint32_t size = IdxArray.size();
	uint32_t iter = 0;
	vector<BtreeNode*> nodeVec;

	/*	Build terminal nodes for IdxArray.	*/
	while (iter < size) {
		BtreeNode *left;
		BtreeNode *right;

		int l = IdxArray[iter];
		iter++;
		int r = IdxArray[iter];
		iter++;

		left = (BtreeNode*) l;
		right = (BtreeNode*) r;

		BtreeNode* terminal = this->getNode();
		terminal->setLeft(left);
		terminal->setRight(right);
		BtreeNode* terminalCached = this->addTerminal(terminal);
		if (terminal==terminalCached) {
            getrusage(RUSAGE_SELF, &myTiming);
            double end = myTiming.ru_utime.tv_sec;
			containsTime += end-start;
			return false;
		}
		nodeVec.push_back(terminalCached);
 	}

	/*	Iteratively build nodes until a single node is left.	*/
	while (nodeVec.size() > 1) {
		vector<BtreeNode*> newNodeVec;

		while (nodeVec.size() > 1) {
			BtreeNode* n1 = nodeVec.back();
			nodeVec.pop_back();
			BtreeNode* n2 = nodeVec.back();
			nodeVec.pop_back();

			BtreeNode* node = this->getNode();
			node->setLeft(n1);
			node->setRight(n2);
			BtreeNode* nodeCached = this->addNode(node);
			if (node == nodeCached) {
                getrusage(RUSAGE_SELF, &myTiming);
                double end = myTiming.ru_utime.tv_sec;
                containsTime += end-start;
				return false;
			}
			newNodeVec.push_back(nodeCached);
		}

		if (nodeVec.size() == 1) {
			BtreeNode* node = nodeVec.back();
			newNodeVec.push_back(node);
		}

		nodeVec = newNodeVec;
	}

    getrusage(RUSAGE_SELF, &myTiming);
    double end = myTiming.ru_utime.tv_sec;
    containsTime += end-start;
	return true;
}


BtreeNode* BinaryTree::getNode()
{
	if (this->deadNodeTbl.empty()==true) {
	    memUse += sizeof(BtreeNode) + sizeof(BtreeNode*);
		return new BtreeNode();
	}
	BtreeNode* node = this->deadNodeTbl.back();
	this->deadNodeTbl.pop_back();
	return node;
}

BtreeNode* BinaryTree::addNode(BtreeNode* node)
{
	BtreeNodeSet::iterator node_iter = this->nodeTbl.find(node);
	if (node_iter != this->nodeTbl.end()) {
		this->deadNodeTbl.push_back(node);
		return *node_iter;
	}
	this->nodeTbl.insert(node);
	return node;
}


BtreeNode* BinaryTree::addTerminal(BtreeNode* terminal)
{
	BtreeNodeSet::iterator terminal_iter = this->terminalTbl.find(terminal);
	if (terminal_iter != this->terminalTbl.end()) {
		this->deadNodeTbl.push_back(terminal);
		return *terminal_iter;
	}
	this->terminalTbl.insert(terminal);
	return terminal;
}

string BinaryTree::stats()
{
    std::stringstream ss;
    ss << "Seconds spent in Add: " << addTime << endl;
    ss << "Seconds spent in Contains: " << containsTime <<endl;
    ss << "Memory Used: " << getMemSize() << " MB" << endl;
    return ss.str();
}

