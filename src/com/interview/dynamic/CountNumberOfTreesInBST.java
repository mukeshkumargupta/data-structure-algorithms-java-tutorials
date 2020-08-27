package com.interview.dynamic;

/**
 * http://www.geeksforgeeks.org/program-nth-catalan-number/ i.e. number of valid paranthesis for given n. same logic as below
 * Derived question: Print all valid paranthesis, Saurabh school explain beautifully.
 * Count number of binary search tree created for array of size n
 * Reference: https://www.youtube.com/watch?v=YDf982Lb84o
 * Category: Medium
 */
public class CountNumberOfTreesInBST {

    //Leave this solution
    int countTreesRec(int numKeys) {
        if (numKeys <=1) {
            return(1);
        }
        else {
            int sum = 0;
            int left, right, root;
            for (root=1; root<=numKeys; root++) {
                left = countTreesRec(root - 1);
                right = countTreesRec(numKeys - root);
                sum += left*right;
            }
            return(sum);
        }
    }
    
    //More effective
    public int countTrees(int n){
        int T[] = new int[n+1];
        T[0] = 1;
        T[1] = 1;
        //See how formula is constructed in given reference video
        //Start calculating from 2 onwards, 
        for(int i=2; i <= n; i++){
            for(int j=0; j <i; j++){
                T[i] += T[j]*T[i-j-1];
            }
        }
        return T[n];
    }
    public static void main(String args[]){
        CountNumberOfTreesInBST cnt = new CountNumberOfTreesInBST();
        System.out.println(cnt.countTrees(3));
        System.out.println(cnt.countTrees(4));
    }
}
