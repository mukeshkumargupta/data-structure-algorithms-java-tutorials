package com.interview.tree;

/*This is a function problem.You only need to complete the function given below*/
/* A Binary Tree node
class Node
{
    int data;
    Node left, right;
    Node(int item)
    {
        data = item;
        left = right = null;
    }
}
 */
//Reference: https://practice.geeksforgeeks.org/problems/height-of-binary-tree/1
//Category: Must Do
public class HeightOfTree {
	int height(Node node) {
		if (node == null) {
			return 0;
		}
		int leftHeight = height(node.left);
		int rightHeight = height(node.right);
		if (leftHeight > rightHeight) {
			return 1 + leftHeight;
		} else {
			return 1 + rightHeight;
		}
	}

}
