package com.interview.bst;

import com.sun.source.tree.Tree;

/*
 * Problem Link:
 * https://www.codingninjas.com/codestudio/problems/floor-from-bst_920457?source=youtube&campaign=Striver_Tree_Videos&utm_source=youtube&utm_medium=affiliate&utm_campaign=Striver_Tree_Videos&leftPanelTab=0
 *
 * Video Explanation:
 * https://www.youtube.com/watch?v=xm_W1ub-K-w
 *
 * Category: Easy, Tricky
 *
 * Problem Statement:
 * You are given a BST (Binary Search Tree) with N number of nodes and a value X.
 * Your task is to find the greatest value node of the BST which is smaller than or equal to X.
 *
 * Note: X is not smaller than the smallest node of BST.
 *
 * Example:
 *
 *        10
 *       /  \
 *      5    15
 *     / \
 *    2   6
 *
 * For X = 7, the greatest value node of the BST which is smaller than or equal to 7 is 6.
 *
 * Input Format:
 * - The first line of input contains an integer T, denoting the number of test cases.
 * - Each test case consists of:
 *   - A single line containing nodes in level-order form (separated by space).
 *   - If any node does not have a left or right child, take -1 in its place.
 *   - The second and last line of each test case contains integer X.
 *
 * Example Input:
 * 10
 * 5 15
 * 2 6 -1 -1
 * -1 -1 -1 -1
 *
 * Explanation:
 * Level 1: Root node = 10
 * Level 2: Left child = 5, Right child = 15
 * Level 3: Left child of 5 = 2, Right child of 5 = 6
 *
 * The first non-null node of the previous level is treated as the parent of the first two nodes
 * of the current level, and so on.
 *
 * Example Input (Single Line Format):
 * 10 5 15 2 6 -1 -1 -1 -1 -1 -1
 *
 * Output Format:
 * - For each test case, print a single integer M, denoting the greatest value node of the BST
 *   which is smaller than or equal to X.
 *
 * Constraints:
 * 1 <= T <= 5
 * 1 <= N <= 5000
 * 1 <= nodeVal[i] <= 10^9
 *
 * Time Limit: 1 sec.
 *
 * Sample Input 1:
 * 2
 * 10 5 15 2 6 -1 -1 -1 -1 -1 -1
 * 7
 * 2 1 3 -1 -1 -1 -1
 * 2
 *
 * Sample Output 1:
 * 6
 * 2
 *
 * Explanation:
 * - In the first test case, the greatest value node <= 7 is 6.
 * - In the second test case, the greatest value node <= 2 is 2.
 *
 * Sample Input 2:
 * 2
 * 5 3 10 2 4 6 15 -1 -1 -1 -1 -1 -1 -1 -1
 * 15
 * 5 3 10 2 4 6 15 -1 -1 -1 -1 -1 -1 -1 -1
 * 8
 *
 * Sample Output 2:
 * 15
 * 6
 */
public class FloorinBST {
    private static class TreeNode<T> {
        int data;
        TreeNode<T> left;
        TreeNode<T> right;
         TreeNode(int data) {
             this.data = data;

         }
    }
    public static int floorInBST(TreeNode<Integer> root, int X) {
        //    Write your code here.
        int result = -1;
        while (root != null) {
            if (root.data == X) {
                return X;
            } else if (X > root.data) {
                result = root.data;//probable ans
                root = root.right;
                
            } else {
                root = root.left;
            }
            
        }
        return result;
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
