#ifndef BTREE_NODE_H_
#define BTREE_NODE_H_

using namespace std;

#include <vector>
#include <string>
#include <assert.h>
#include <stdint.h>


class BtreeNode;


class BtreeNode {
public:
	
	BtreeNode() {
		this->left = 0;
		this->right = 0;
	}

	void setLeft(BtreeNode* node) { this->left = node; }
	void setRight(BtreeNode* node) { this->right = node; }
	BtreeNode* getLeft()  const {	return this->left; }
	BtreeNode* getRight() const  { return this->right; }
	
	bool	equals(const BtreeNode* other) const;
	
	
private:
	
	BtreeNode* left;
	BtreeNode* right;
	
};



/* BtreeNode hash and equal utilities. */

struct BtreeNodeHash {
	uint64_t operator()(const BtreeNode* node) const {
		uint64_t l64 = (uint64_t) node->getLeft();
		uint64_t r64 = (uint64_t) node->getRight();
		
		return l64 ^ 1398269 + r64;
	}
};

struct BtreeNodeEq {
	bool operator()(const BtreeNode* node1, const BtreeNode* node2) const {
		return node1->equals(node2);
	}
};


#endif

