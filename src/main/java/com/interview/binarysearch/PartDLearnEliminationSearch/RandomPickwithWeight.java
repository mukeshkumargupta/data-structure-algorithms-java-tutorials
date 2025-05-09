package com.interview.binarysearch.PartDLearnEliminationSearch;

import java.util.*;

/*
 * https://leetcode.com/problems/random-pick-with-weight/
 * https://www.youtube.com/watch?v=3Ky9ZlI95cY
 * Category: Medium, Must Do, Facebook
 * Related: https://leetcode.com/problems/random-pick-index/ Medium
 * https://leetcode.com/problems/random-pick-with-blacklist/ Hard
 * https://leetcode.com/problems/random-point-in-non-overlapping-rectangles/ Medium
 * You are given an array of positive integers w where w[i] describes the weight of ith index (0-indexed).

We need to call the function pickIndex() which randomly returns an integer in the range [0, w.length - 1]. pickIndex() should return the integer proportional to its weight in the w array. For example, for w = [1, 3], the probability of picking the index 0 is 1 / (1 + 3) = 0.25 (i.e 25%) while the probability of picking the index 1 is 3 / (1 + 3) = 0.75 (i.e 75%).

More formally, the probability of picking index i is w[i] / sum(w).

 

Example 1:

Input
["Solution","pickIndex"]
[[[1]],[]]
Output
[null,0]

Explanation
Solution solution = new Solution([1]);
solution.pickIndex(); // return 0. Since there is only one single element on the array the only option is to return the first element.
Example 2:

Input
["Solution","pickIndex","pickIndex","pickIndex","pickIndex","pickIndex"]
[[[1,3]],[],[],[],[],[]]
Output
[null,1,1,1,1,0]

Explanation
Solution solution = new Solution([1, 3]);
solution.pickIndex(); // return 1. It's returning the second element (index = 1) that has probability of 3/4.
solution.pickIndex(); // return 1
solution.pickIndex(); // return 1
solution.pickIndex(); // return 1
solution.pickIndex(); // return 0. It's returning the first element (index = 0) that has probability of 1/4.

Since this is a randomization problem, multiple answers are allowed so the following outputs can be considered correct :
[null,1,1,1,1,0]
[null,1,1,1,1,1]
[null,1,1,1,0,0]
[null,1,1,1,0,1]
[null,1,0,1,0,0]
......
and so on.
 

Constraints:

1 <= w.length <= 10000
1 <= w[i] <= 10^5
pickIndex will be called at most 10000 times.
 */
public class RandomPickwithWeight {
    
    int[] commulativeSum;
    Random r = new Random();
    int max = 0;
    public RandomPickwithWeight(int[] w) {
        
        int l = w.length;
        commulativeSum = new int[l];
        commulativeSum[0] = w[0];
        for (int i = 1; i < l; i++) {
           commulativeSum[i] =  commulativeSum[i-1] + w[i];
        }
        max = commulativeSum[commulativeSum.length-1];

        
    }
    
    public int pickIndex() {
        int num = r.nextInt(max);//start index 0 so add 1, example: max is 10 then value will come 0 to 9 random but we need 1 to 10
        num = num +1;
        int start = 0;
        int end = commulativeSum.length-1;
        
        while (start <= end) {
            int mid = start + (end - start)/2;
            if (commulativeSum[mid] == num) {
                return mid;
            } else if (num > commulativeSum[mid]) {
                start = mid +1;
            } else {
                end = mid -1;
            }
                
        }
        //if not found then return start
        return start;
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
