package com.interview.recursionBacktracking.A_combinationPattern;

import java.util.*;
/*
 * https://leetcode.com/problems/combination-sum-iii/
 * Category: Medium, Google, Tricky
 * https://www.youtube.com/watch?v=rP_K3WJnRR4&list=PL1w8k37X_6L-bCZ3m0FFBZmRv4onE7Zjl&index=34 Best explanation
 * Related: https://leetcode.com/problems/4sum/ Medium
 * https://leetcode.com/problems/split-array-into-consecutive-subsequences/ Medium
 * https://leetcode.com/problems/sum-of-beauty-in-the-array/ Medium
 * Derived question: count of combination which sum is given number, here in this question all list is mentioned.
 * Find all valid combinations of k numbers that sum up to n such that the following conditions are true:

Only numbers 1 through 9 are used.
Each number is used at most once.
Return a list of all possible valid combinations. The list must not contain the same combination twice, and the combinations may be returned in any order.

 

Example 1:

Input: k = 3, n = 7
Output: [[1,2,4]]
Explanation:
1 + 2 + 4 = 7
There are no other valid combinations.
Example 2:

Input: k = 3, n = 9
Output: [[1,2,6],[1,3,5],[2,3,4]]
Explanation:
1 + 2 + 6 = 9
1 + 3 + 5 = 9
2 + 3 + 4 = 9
There are no other valid combinations.
Example 3:

Input: k = 4, n = 1
Output: []
Explanation: There are no valid combinations.
Using 4 different numbers in the range [1,9], the smallest sum we can get is 1+2+3+4 = 10 and since 10 > 1, there are no valid combination.
Example 4:

Input: k = 3, n = 2
Output: []
Explanation: There are no valid combinations.
Example 5:

Input: k = 9, n = 45
Output: [[1,2,3,4,5,6,7,8,9]]
Explanation:
1 + 2 + 3 + 4 + 5 + 6 + 7 + 8 + 9 = 45
There are no other valid combinations.
 

Constraints:

2 <= k <= 9
1 <= n <= 60
 */
public class A_CombinationSumIII {
    void combinationSumUtil( int k, int n, int start, List<Integer> combination, List<List<Integer>> result){
        /*
         * Runtime: 1 ms, faster than 52.18% of Java online submissions for Combination Sum III.
Memory Usage: 38.2 MB, less than 37.98% of Java online submissions for Combination Sum III.
         */
        if(k == combination.size()){
            if(n == 0) { 
                result.add(new ArrayList<Integer>(combination));
            }
            
            return;
        }
        for(int i = start; i <= 9; ++i){
            combination.add(i);
            combinationSumUtil(k, n-i, i+1, combination, result);
            combination.remove(combination.size() - 1);
        }
    }
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> result = new ArrayList();
        List<Integer> combination = new ArrayList();
        combinationSumUtil(k, n, 1, combination, result);
        return result;
    }
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
