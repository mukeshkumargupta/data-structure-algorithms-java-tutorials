package com.interview.dynamic;

import java.util.List;

/**
 * Date 07/07/2017
 * @author Mukesh Kumar Gupta
 * Reference: https://leetcode.com/problems/edit-distance/submissions/
 * Category: Hard, Must Do, Tricky
 *
 * Given two strings how many minimum edits(update, delete or add) is needed to convert one string to another
 *
 * Time complexity is O(m*n)
 * Space complexity is O(m*n)
 *
 * References:
 * http://www.geeksforgeeks.org/dynamic-programming-set-5-edit-distance/
 * https://en.wikipedia.org/wiki/Edit_distance
 */
public class EditDistance {
    public int minDistance(String word1, String word2) {
        

        int l1 = word1.length(); // Make it column side
        int l2 = word2.length(); //Make it row side
        int[][]T = new int[l2+1][l1+1];
        T[0][0] = 0;
        
        for ( int i =1; i <= l1 ; i++) { //fill first row
            T[0][i] = i;
        }
        
        for (int i = 1; i <=  l2; i++) {//fill first column
            T[i][0] = i;
        }
        
        for (int i = 0; i < l2 ; i++) {
            for (int j = 0; j < l1; j++ ) {
                if (word2.charAt(i) == word1.charAt(j)) {
                    T[i+1][j+1] = T[i][j];
                } else {
                    T[i+1][j+1] = Math.min(Math.min(T[i][j], T[i+1][j]) , T[i][j+1]) +1;
                }
            }
        }
        return T[l2][l1];
        
    }
    

}
