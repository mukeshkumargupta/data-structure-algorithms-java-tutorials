package com.interview.bst;

/*
 * https://www.youtube.com/watch?v=FiFiNvM29ps
 * https://leetcode.com/problems/insert-into-a-binary-search-tree/description/
 * Category: Easy, Fundamental, Must Do
 * Problem Statement
You have been given a root node of the binary search tree and a positive integer value. You need to perform an insertion operation i.e. inserting a new node with the given value in the given binary search tree such that the resultant tree is also a binary search tree. If there can be more than one possible tree, then you can return any.
Note :
A binary search tree is a binary tree data structure, with the following properties :
    a. The left subtree of any node contains nodes with the value less than the node�s value.
    b. The right subtree of any node contains nodes with the value equal to or greater than the node�s value.
    c. Right, and left subtrees are also binary search trees.
It is guaranteed that,
    a. All nodes in the given tree are distinct positive integers.
    b. The given BST does not contain any node with a given integer value.
Example, below the tree, is a binary search tree.

Below the tree is not a BST as node �2� is less than node �3� but �2� is the right child of �3�, and node �6� is greater than node �5� but it is in the left subtree of node �5�.

Input Format :
The first line contains an integer 'T' which denotes the number of test cases or queries to be run.

The first line of each test case contains elements of the tree in the level order form. The line consists of values of nodes separated by a single space. In case a node is null, we take -1 in its place.

The second line of each test case contains a positive integer value �val�, denoting the value of the node that is to be inserted in the given BST.

For example, the level order input for the tree depicted in the below image.

50  
13 72  
3 25 66 -1  
-1 -1 -1 -1 -1 -1  
Explanation :
Level 1 :
The root node of the tree is 50

Level 2 :
Left child of 50 = 13
Right child of 50 = 72

Level 3 :
Left child of 13 = 3
Right child of 13 = 25
Left child of 72 = 66
Right child of 72 =  �Null�


Level 4 :
All children are �Null�

The first not-null node(of the previous level) is treated as the parent of the first two nodes of the current level. The second not-null node (of the previous level) is treated as the parent node for the next two nodes of the current level and so on.
The input ends when all nodes at the last level are null(-1).
Note :
The above format was just to provide clarity on how the input is formed for a given tree. 

The sequence will be put together in a single line separated by a single space. 

Hence, for the above-depicted tree, the input will be given as:
50 13 72 3 25 66 -1 -1 -1 -1 -1 -1 -1
Output Format :
For each test case, the output will be �1� if you have returned the correct answer, else it will be �0�.

The output of each test case will be printed in a separate line.
Note :
You do not need to input or print anything, and it has already been taken care of. Just implement the given function.
Constraints :
1 <= T <= 5
0 <= N <= 3000
1 <= data <= 10 ^ 9
1 <= val <= 10 ^ 9

Where �N� is the total number of nodes in the given binary tree, �data� is the value of the nodes of the given binary tree, and �val� represents the value of node that is to be inserted in the given tree.

For a single test case, all given �data� and �val� are distinct from each other.

Time Limit: 1sec
Sample Input 1 :
2
1 -1 3 -1 -1 
4
-1
50
Sample Output 1 :
1
1
Explanation Of Sample Input 1 :
For the first test case, the given tree is.

One possible output tree is

  The given tree in the second test case is �Null� i.e. it doesn�t contain any node. Only one possible output tree is

Sample Input 2:
1
10 2 12 1 3 -1 13 -1 -1 -1 -1 -1 -1
11
Sample Output 2 :
1
Explanation Of Sample Input 2 :
For the first test case, the given tree is

The output tree can be.

Another Possible answer can be.


 */
public class InsertagivenNodeinBinarySearchTree {
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if(root == null) return new TreeNode(val);
        TreeNode cur = root;
        while(true) {
            if(cur.val <= val) {
                if(cur.right != null) cur = cur.right;
                else {
                    cur.right = new TreeNode(val);
                    break;
                }
            } else {
                if(cur.left != null) cur = cur.left;
                else {
                    cur.left = new TreeNode(val);
                    break;
                }
            }
        }
        return root;
    }
    
    /*Recursive
     * Think how search work in bst
     */
    
    public static TreeNode<Integer> insertionInBST(TreeNode<Integer> root, int val) {
        //    Write your code here
        if (root == null) {
            return new TreeNode(val);
        }
         if (val > root.val) {
             root.right = insertionInBST(root.right, val);
         } else {
             root.left = insertionInBST(root.left, val);
         }
        return root;
        
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
