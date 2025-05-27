package com.interview.stackqueue.C_NGEPatterns;

import java.util.*;
/*
 * https://leetcode.com/problems/daily-temperatures/
 * https://www.youtube.com/watch?v=WGm4Kj3lhRI
 * Category: Medium, Top100, Tricky, Google, Facebook, FAANG
 * Related:
 * https://leetcode.com/problems/longest-word-in-dictionary-through-deleting/ Medium
 * https://leetcode.com/problems/longest-word-in-dictionary/ Medium
 * https://leetcode.com/problems/next-greater-element-i/ done
 * https://leetcode.com/problems/next-greater-element-ii/ done
 * https://leetcode.com/problems/next-greater-element-iii/ solved nextpermutation is application of it
 * https://leetcode.com/problems/score-of-parentheses/ Medium Hint solve using stack, not tried
 * https://leetcode.com/problems/find-the-most-competitive-subsequence/ Medium
 * Given an array of integers temperatures represents the daily temperatures, return an array answer
 *  such that answer[i] is the number of days you have to wait after the ith day to get a warmer temperature.
 *  If there is no future day for which this is possible, keep answer[i] == 0 instead.

 

Example 1:

Input: temperatures = [73,74,75,71,69,72,76,73]
Output: [1,1,4,2,1,1,0,0]
Example 2:

Input: temperatures = [30,40,50,60]
Output: [1,1,1,0]
Example 3:

Input: temperatures = [30,60,90]
Output: [1,1,0]
 

Constraints:

1 <= temperatures.length <= 105
30 <= temperatures[i] <= 100
 */
public class PartF_B_A_DailyTemperatures {
    
    public int[] dailyTemperatures(int[] temperatures) {
        /*
         * Runtime: 36 ms, faster than 57.88% of Java online submissions for Daily Temperatures.
Memory Usage: 50 MB, less than 47.99% of Java online submissions for Daily Temperatures.
         */
        class Pair {
            int index;
            int val;
            Pair(int index, int val) {
                this.index = index;
                this.val = val;
                
            }
        }
        int l = temperatures.length;
        int[] result = new int[l];
        Stack<Pair> st = new Stack<>();
        for (int i = l-1; i >= 0; i--) {
            while (!st.isEmpty() && st.peek().val <= temperatures[i]) {
                st.pop();
            }
            if (st.isEmpty()) {
                result[i] = 0;
            } else {
                result[i] = st.peek().index - i;
            }
            st.push(new Pair(i, temperatures[i]));
            
        }
        return result;
        
        
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
