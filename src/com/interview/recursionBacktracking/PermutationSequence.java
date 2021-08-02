package com.interview.recursionBacktracking;

import java.util.*;

/*
 * https://leetcode.com/problems/permutation-sequence/
 * Category: Hard, Tricky, Must Do
 * Related: https://leetcode.com/problems/reconstruct-original-digits-from-english/ Medium
 * https://leetcode.com/problems/k-th-symbol-in-grammar/ Medium
 * https://leetcode.com/problems/check-if-it-is-a-straight-line/ Easy
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
