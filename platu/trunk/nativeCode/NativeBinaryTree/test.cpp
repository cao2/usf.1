using namespace std;

#include <iostream>
#include <vector>
#include "BinaryTree.h"
#include <stdio.h>


int main(int argc, char** argv)
{
	BinaryTree btree;

	vector<int> vec1;
	vec1.push_back(-1);
	vec1.push_back(2);
	vec1.push_back(5);
	vec1.push_back(6);
	vec1.push_back(-7);
	vec1.push_back(9);

	vector<int> vec2;
	vec2.push_back(-4);



	btree.add(vec1);
	bool existing = btree.contains(vec1);
	if(existing)
		printf("vec1 exists\n");
	else {
		printf("vec1 does not exist\n");
	}

	btree.add(vec2);
	existing = btree.contains(vec2);
	if(existing)
		printf("vec2 exists\n");
	else {
		printf("vec2 does not exist\n");
	}


	vector<int> vec3;
	vec3.push_back(0);
	vec3.push_back(2);
	vec3.push_back(5);
	vec3.push_back(6);
	vec3.push_back(-7);
	vec3.push_back(9);
//	vec3.push_back(10);

	existing = btree.contains(vec3);
	if(existing)
		printf("vec3 exists\n");
	else {
		printf("vec3 does not exist\n");
	}

	printf("Total number of vectors stored = %d\n", btree.elementCount());

	printf("Size of node = %d\n", (int) sizeof(BtreeNode));
}
