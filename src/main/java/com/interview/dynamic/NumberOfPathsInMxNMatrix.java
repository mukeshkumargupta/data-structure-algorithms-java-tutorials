package com.interview.dynamic;

/**
 * https://leetcode.com/problems/unique-paths/
 * http://www.geeksforgeeks.org/count-possible-paths-top-left-bottom-right-nxm-matrix/
 * Category: Medium
 */
public class NumberOfPathsInMxNMatrix {

    public int countPathsRecursive(int n, int m){
        if(n == 1 || m == 1){
            return 1;
        }
        return countPathsRecursive(n-1, m) + countPathsRecursive(n, m-1);
    }
    
    public int countPaths(int m,int n){
        int[][] T = new int[m][n];
        for(int i=0; i < m; i++){ //fill first row
            T[i][0] = 1;
        }
        
        for(int i=0; i < n; i++){//fill first column
            T[0][i] = 1;
        }
        for(int i=1; i < m; i++){
            for(int j=1; j < n; j++){
                T[i][j] = T[i-1][j] + T[i][j-1];
            }
        }
        return T[m-1][n-1];
    }
    
    public static void main(String args[]){
        NumberOfPathsInMxNMatrix nop = new NumberOfPathsInMxNMatrix();
        System.out.print(nop.countPathsRecursive(3,3));
    }
    
}
