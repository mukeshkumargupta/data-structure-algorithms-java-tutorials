package com.interview.stackqueue;

/*
 * 
 * https://www.youtube.com/watch?v=RCE2L0Zk7xE
 * Category: Medium, Tricky, VVVImp
 * Given an array of n integers nums, a 132 pattern is a subsequence of three integers nums[i], nums[j] and nums[k] such that i < j < k and nums[i] < nums[k] < nums[j].

Return true if there is a 132 pattern in nums, otherwise, return false.

 

Example 1:

Input: nums = [1,2,3,4]
Output: false
Explanation: There is no 132 pattern in the sequence.
Example 2:

Input: nums = [3,1,4,2]
Output: true
Explanation: There is a 132 pattern in the sequence: [1, 4, 2].
Example 3:

Input: nums = [-1,3,2,0]
Output: true
Explanation: There are three 132 patterns in the sequence: [-1, 3, 2], [-1, 3, 0] and [-1, 2, 0].
 

Constraints:

n == nums.length
1 <= n <= 2 * 105
-109 <= nums[i] <= 109
 */
public class OneTwoThreePattern {
    public boolean find132pattern(int[] nums) {
        /*
         * Some test case failin, try again
         * TC: O(N)
         * SC(O(N)
         */
        Stack<Integer> st = new Stack<>();
        int l = nums.length;
        int[] min = new int[l];
        min[0] = nums[0];
        for (int i = 1; i < l; i++) {
            min[i] = Math.min(min[i-1], min[i] );
            
        }
        for (int j = l-1; j >=0; j--) {
                    
            while (st.size() > 0 && st.peek() <= min[j]) {//Only interested in greater element
                st.pop();
            }
            if (st.size() > 0 && st.peek() < nums[j] ) {
                return true;
            }
            st.push(nums[j]);

        }
        return false;
        
        
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
