package com.interview.matrix;

/*
 * https://leetcode.com/problems/sort-the-matrix-diagonally/
 * Reference: https://www.youtube.com/watch?v=JBqUl7avtG8&list=PLIA-9QRQ0RqHEJBbNYo3KjeDzoc8bnkai&index=14&t=181s
 * Category: Medium, Must Know
 * Related: https://leetcode.com/problems/search-in-a-sorted-array-of-unknown-size/ Medium
 * https://leetcode.com/problems/custom-sort-string/ Medium
 * https://leetcode.com/problems/minimum-cost-to-merge-stones/ Hard
 * 
 * A matrix diagonal is a diagonal line of cells starting from some cell in either the topmost row or leftmost column and going in the bottom-right direction until reaching the matrix's end. For example, the matrix diagonal starting from mat[2][0], where mat is a 6 x 3 matrix, includes cells mat[2][0], mat[3][1], and mat[4][2].

Given an m x n matrix mat of integers, sort each matrix diagonal in ascending order and return the resulting matrix.

 

Example 1:


Input: mat = [[3,3,1,1],[2,2,1,2],[1,1,1,2]]
Output: [[1,1,1,1],[1,2,2,2],[1,2,3,3]]
Example 2:

Input: mat = [[11,25,66,1,69,7],[23,55,17,45,15,52],[75,31,36,44,58,8],[22,27,33,25,68,4],[84,28,14,11,5,50]]
Output: [[5,17,4,1,52,7],[11,11,25,45,8,69],[14,23,25,44,58,15],[22,27,31,36,50,66],[84,28,75,33,55,68]]
 

Constraints:

m == mat.length
n == mat[i].length
1 <= m, n <= 100
1 <= mat[i][j] <= 100
 */
public class SorttheMatrixDiagonally {
    public int[][] diagonalSort(int[][] mat) {
        int R = mat.length;
        int C = mat[0].length;
        

        //for first row
        for (int c = 0; c < C; c++) {
            int i = 0;
            int j = c;
            int[] countSort = new int [100];
            while (i < R && j < C) {
                countSort[mat[i][j]-1]++;
                i++;
                j++;
            }
            
            i = 0;
            j = c;
            int ind = 0;
            //Now place in sorted order
            while (i < R && j < C) {
                while (countSort[ind]-- > 0) {
                   mat[i][j] = ind + 1;
                    i++;
                    j++;
                }
                ind++;
            }
        }
        
        //for first row
        for (int r = 1; r < R; r++) {
            int i = r;
            int j = 0;
            int[] countSort = new int [100];
            while (i < R && j < C) {
                countSort[mat[i][j]-1]++;
                i++;
                j++;
            }
            
            i = r;
            j = 0;
            int ind = 0;
            //Now place in sorted order
            while (i < R && j < C) {
                while (countSort[ind]-- > 0) {
                   mat[i][j] = ind + 1;
                    i++;
                    j++;
                }
                ind++;
            }
        }
        return mat;
        
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int[][] input = {{3,3,1,1},{2,2,1,2},{1,1,1,2}};
        SorttheMatrixDiagonally smd = new SorttheMatrixDiagonally();
        smd.diagonalSort(input);
    }
    
}
