package com.interview.matrix;

/*
 * https://leetcode.com/problems/diagonal-traverse/
 * https://www.youtube.com/watch?v=-FEeaf3ufIE&list=PLIA-9QRQ0RqHEJBbNYo3KjeDzoc8bnkai&index=12
 * Category: Medium, Tricky
 * Related:
 * https://leetcode.com/problems/relative-ranks/ Easy
 * https://leetcode.com/problems/cinema-seat-allocation/ Medium
 * https://leetcode.com/problems/check-if-word-can-be-placed-in-crossword/ Medium
 * Given an m x n matrix mat, return an array of all the elements of the array in a diagonal order.
 * 
 * Derived Question: make down then print diagonal

 

Example 1:


Input: mat = [[1,2,3],[4,5,6],[7,8,9]]
Output: [1,2,4,7,5,3,6,8,9]
Example 2:

Input: mat = [[1,2],[3,4]]
Output: [1,2,3,4]
 

Constraints:

m == mat.length
n == mat[i].length
1 <= m, n <= 104
1 <= m * n <= 104
-105 <= mat[i][j] <= 105
 */
public class DiagonalTraverse {
    public int[] findDiagonalOrder(int[][] mat) {//runtime 97% here right taken then dowon
        int R = mat.length;
        int C = mat[0].length;
        int[] result = new int[R*C];
        int l = result.length;
        int r =0; int c = 0;
        for (int i = 0; i < l ; i++) {
            //System.out.println(r  + " , " + c);
            result[i] = mat[r][c];
            if ( (r + c) % 2 == 0) {//Note: if r+c is even then go up, then it will hit r == 0 or c == c-1
                if(c == C -1) {
                   r++; 
                } else if (r == 0) {
                    c++;
                } else {//Tricky go up if even: remember it
                    r--;
                    c++;
                }
                
            } else {
                if( r == R -1) {
                    c++;
                } else if (c ==0) {
                    r++;
                } else {
                    r++;
                    c--;
                }
                
            }
            
        }
        return result;
        
    }
    
    
    public int[] findDiagonalOrderReverse(int[][] mat) {//runtime 97% here right taken then dowon
        int R = mat.length;
        int C = mat[0].length;
        int[] result = new int[R*C];
        int l = result.length;
        int r =0; int c = 0;
        for (int i = 0; i < l ; i++) {
            //System.out.println(r  + " , " + c);
            result[i] = mat[r][c];
            if ( (r + c) % 2 != 0) {//Note: Just change the sign
                if(c == C -1) {
                   r++; 
                } else if (r == 0) {
                    c++;
                } else {
                    r--;
                    c++;
                }
                
            } else {
                if( r == R -1) {
                    c++;
                } else if (c ==0) {
                    r++;
                } else {
                    r++;
                    c--;
                }
                
            }
            
        }
        return result;
        
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
