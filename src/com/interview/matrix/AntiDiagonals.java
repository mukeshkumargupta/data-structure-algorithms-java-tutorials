package com.interview.matrix;

import java.util.*;
/*
 * https://www.interviewbit.com/problems/anti-diagonals/
 * Category: Easy, Tricky, Not running code
 */
public class AntiDiagonals {
    public ArrayList<ArrayList<Integer>> diagonal(ArrayList<ArrayList<Integer>> A) {
        int R = A.size();
        int C = A.get(0).size();


        int total = R + C -1;
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        for (int ind = 0; ind < total; ind++) {
            for (int j = 0; j < C; j++) {
                ArrayList<Integer> columns = new ArrayList<>();
                int i = 0;
                while ( i < R && j >= 0) {
                    columns.add(A.get(i).get(j));
                    i++;
                    j--;
                }
                result.add(columns);

            }

            for (int i = 1; i < R; i++) {
                ArrayList<Integer> columns = new ArrayList<>();
                int j = C - 1;
                while ( i < R && j >= 0) {
                    columns.add(A.get(i).get(j));
                    i++;
                    j--;
                }
                result.add(columns);
            }
        }
        return result;
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        //test with input:
        /*1 2 3
        4 5 6
        7 8 9
        o/p
        Return the following:
[ 
  [1],
  [2, 4],
  [3, 5, 7],
  [6, 8],
  [9]
]
*/
        
    }
    
}
