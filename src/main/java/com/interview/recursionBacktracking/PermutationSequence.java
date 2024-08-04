package com.interview.recursionBacktracking;

import java.util.*;

/*
 * https://leetcode.com/problems/permutation-sequence/
 * Category: Hard, Tricky
 * https://www.youtube.com/watch?v=wT7gcXLYoao
 * 
 * Related: https://leetcode.com/problems/reconstruct-original-digits-from-english/ Medium
 * https://leetcode.com/problems/k-th-symbol-in-grammar/ Medium
 * https://leetcode.com/problems/check-if-it-is-a-straight-line/ Easy
 * 
 * The set [1, 2, 3, ..., n] contains a total of n! unique permutations.

By listing and labeling all of the permutations in order, we get the following sequence for n = 3:

"123"
"132"
"213"
"231"
"312"
"321"
Given n and k, return the kth permutation sequence.

 

Example 1:

Input: n = 3, k = 3
Output: "213"
Example 2:

Input: n = 4, k = 9
Output: "2314"
Example 3:

Input: n = 3, k = 1
Output: "123"
 */
public class PermutationSequence {
    public String getPermutation(int n, int k) {
        int fact = 1;
        List<Integer> list = new ArrayList<>();
        int i = 1;
        for (; i < n; i++) {
            fact = fact*i;
            list.add(i);
        }
        list.add(i);
        StringBuilder ans = new StringBuilder();
        
        k = k-1;
        while (true) {
            ans.append(list.get(k/fact));
            list.remove(k/fact);
            int size = list.size();
            if (size ==0) {
                break;
            }
            k = k % fact;
            fact = fact/size;
        }
        return ans.toString();
        
    }
    
}
