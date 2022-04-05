package com.interview.recursionBacktracking;

import java.util.*;

/*
 * https://www.youtube.com/watch?v=AxNNVECce8c&list=PLgUwDviBIf0rGlzIn_7rsaR2FQ5e6ZOL9&index=6
 * Category: Fundamental
 */

public class L6RecursiononSubsequencesPrintingSubsequences {
    
    void printSubsequence(int start, int l, int[] input, List<Integer> ds) {
        /*TC:
         * 2^N * N, here N is length, *N is for printing
         * SC: O(N)
         */
        if (start == l) {
            if (ds.size() > 0) {
                for (int elm: ds) {
                    System.out.println(elm);
                }
                System.out.println("new line");
            } else {
                System.out.println("{}");
            }
            return;

        }
        ds.add(input[start]);//take
        printSubsequence(start+1, l, input, ds);
        ds.remove(ds.size()-1);//not take
        printSubsequence(start+1, l, input, ds);
    }
    //take 
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        List<Integer> ds = new ArrayList<>();
        int[] input = {1,2,3};
        L6RecursiononSubsequencesPrintingSubsequences instance = new L6RecursiononSubsequencesPrintingSubsequences();
        instance.printSubsequence(0, input.length, input, ds);
        
    }
    
}
