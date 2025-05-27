package com.interview.tree.PartCATreeProperties;


/*
 * Children Sum Property in Binary Tree
 * https://www.youtube.com/watch?v=fnmisPM6cVo
 * Each node you can only increment , you can not decrement
 * Category: Fundamental, Medium, Must Do
 * https://www.codingninjas.com/codestudio/problems/childrensumproperty_790723?source=youtube&amp;campaign=Striver_Tree_Videos&amp;utm_source=youtube&amp;utm_medium=affiliate&amp;utm_campaign=Striver_Tree_Videos
 * 
 * Problem Statement
Given a binary tree of nodes 'N', you need to modify the value of its nodes, such that the tree holds the Children sum property.
A binary tree is said to follow the children sum property if, for every node of that tree, the value of that node is equal to the sum of the value(s) of all of its children nodes( left child and the right child).
Note :
 1. You can only increment the value of the nodes, in other words, the modified value must be at least equal to the original value of that node.
 2. You can not change the structure of the original binary tree.
 3. A binary tree is a tree in which each node has at most two children.      
 4. You can assume the value can be 0 for a NULL node and there can also be an empty tree.
Input Format :
The first line contains a single integer 'T' representing the number of test cases. 

The first and the only line of each test case will contain the values of the nodes of the tree in the level order form ( -1 for NULL node) Refer to the example for further clarification.
Example : 
Consider the binary tree :

The input of the tree depicted in the image above will be like : 

1
2 3
4 -1 5 6
-1 7 -1 -1 -1 -1
-1 -1

Explanation :
Level 1 :
The root node of the tree is 1

Level 2 :
Left child of 1 = 2
Right child of 1 = 3

Level 3 :
Left child of 2 = 4
Right child of 2 = null (-1)
Left child of 3 = 5
Right child of 3 = 6

Level 4 :
Left child of 4 = null (-1)
Right child of 4 = 7
Left child of 5 = null (-1)
Right child of 5 = null (-1)
Left child of 6 = null (-1)
Right child of 6 = null (-1)

Level 5 :
Left child of 7 = null (-1)
Right child of 7 = null (-1)

The first not-null node (of the previous level) is treated as the parent of the first two nodes of the current level. The second not-null node (of the previous level) is treated as the parent node for the next two nodes of the current level and so on.

The input ends when all nodes at the last level are null (-1).
##### Note : The above format was just to provide clarity on how the input is formed for a given tree. The sequence will be put together in a single line separated by a single space. Hence, for the above-depicted tree, the input will be given as:

1 2 3 4 -1 5 6 -1 7 -1 -1 -1 -1 -1 -1
Output Format :
For each test case, you just need to update the given tree in-place. If the updated tree satisfies all the conditions, the output will be shown as �Valid�, else the output will be �Invalid�.

The output of each test case will be printed in a separate line.
Note :
You do not need to print anything, it has already been taken care of. Just implement the given function.
Constraints :
1 <= T <= 10^2
0 <= N <= 10^2
1 <= node.Value <= 10^6

Time Limit : 1 sec
Sample Input 1 :
1
2 35 10 2 3 5 2 -1 -1 -1 -1 -1 -1 -1 -1 
Sample Output 1 :
Valid ( One of the possible answers is : 45 35 10 32 3 8 2 -1 -1 -1 -1 -1, thus if the user modifies the given tree like this, the output printed will be valid).
Explanation For Sample Input 1 :
The tree can be represented as follows :

The value at the root node is 2 which is less than the sum of its children�s values (35 + 10). So we simply increase the value at the root node to 45. The node with value  = 35 has children with values 2 and 3 so their sum i.s 2 + 3 = 5 < 35. As we can't decrement any value, so instead we have to increase the sum of children and thus we update 35's children (2 and 3) as 30 and 5 so that 30 + 5 = 35 and the same is done for the children of the node with value = 10.
The final tree looks like this :

Note that there can be many other valid solutions.
Sample Input 2 :
1
10 5 5 -1 -1 -1 -1
Sample Output 2 :
Valid
 * 
 */
public class A_H_ChildrenSumPropertyinBinaryTree {

    // Binary tree node definition
    static class BinaryTreeNode<T> {
        T data;
        BinaryTreeNode<T> left, right;

        BinaryTreeNode(T data) {
            this.data = data;
        }
    }

    // Function to change tree to satisfy children sum property
    public static void changeTree(BinaryTreeNode<Integer> root) {
        if (root == null) return;

        int child = 0;
        if (root.left != null) {
            child += root.left.data;
        }
        if (root.right != null) {
            child += root.right.data;
        }

        if (child >= root.data) {
            root.data = child;
        } else {
            if (root.left != null) root.left.data = root.data;
            else if (root.right != null) root.right.data = root.data;
        }

        changeTree(root.left);
        changeTree(root.right);

        int tot = 0;
        if (root.left != null) tot += root.left.data;
        if (root.right != null) tot += root.right.data;

        if (root.left != null || root.right != null) root.data = tot; // internal node update
    }

    // Utility to print Inorder traversal
    public static void printInorder(BinaryTreeNode<Integer> root) {
        if (root == null) return;
        printInorder(root.left);
        System.out.print(root.data + " ");
        printInorder(root.right);
    }

    public static void main(String[] args) {
        /*
              50
             /  \
           7     2
          / \   /
         3  5  1
        */
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(50);
        root.left = new BinaryTreeNode<>(7);
        root.right = new BinaryTreeNode<>(2);
        root.left.left = new BinaryTreeNode<>(3);
        root.left.right = new BinaryTreeNode<>(5);
        root.right.left = new BinaryTreeNode<>(1);

        System.out.println("Before change:");
        printInorder(root);
        changeTree(root);
        System.out.println("\nAfter change:");
        printInorder(root);
    }
}
