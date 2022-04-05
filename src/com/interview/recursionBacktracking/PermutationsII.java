package com.interview.recursionBacktracking;

import java.util.*;
/*
 * https://leetcode.com/problems/permutations-ii/
 * 
 * Category: Medium, Must Do, Top150
 * Related:
 * https://leetcode.com/problems/number-of-squareful-arrays/ Hard
 * https://leetcode.com/problems/palindrome-permutation-ii/ Medium
 * Given a collection of numbers, nums, that might contain duplicates, return all possible unique permutations in any order.

 Other approach like duplicate element and apply tricky same as combination trick after sort but will fail, when you do dry run, so only hashing will help here

Example 1:

Input: nums = [1,1,2]
Output:
[[1,1,2],
 [1,2,1],
 [2,1,1]]
Example 2:

Input: nums = [1,2,3]
Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 

Constraints:

1 <= nums.length <= 8
-10 <= nums[i] <= 10
 */
public class PermutationsII {
    List<List<Integer>> result = new ArrayList<>();
Set<String> set = new HashSet<>();
private void swap(int[] nums, int i, int j) {
    int temp = nums[i];
    nums[i] = nums[j];
    nums[j] = temp;
    
}

private void permuteUtil(int[] nums, int start, int end) {
if (start == end) {
    List<Integer> list = new ArrayList<>();
    for (int num : nums) {
        list.add(num);
    }
    result.add(list);
    
    return;
}
Set<Integer> set = new HashSet<>();
for (int i = start; i <= end; i++) {
    if (set.contains(nums[i])) {
       continue; 
    }
    set.add(nums[i]);
    swap(nums, start, i);
    permuteUtil(nums,start+1, end);
    swap(nums, start, i);
}

}
public List<List<Integer>> permuteUnique(int[] nums) {
    /*
     * Runtime: 1 ms, faster than 99.46% of Java online submissions for Permutations II.
Memory Usage: 39.6 MB, less than 83.97% of Java online submissions for Permutations II.
https://www.youtube.com/watch?v=is_T6uzlTyg
     */
permuteUtil(nums, 0, nums.length -1);
return result;


}

private void permuteUtilSlow(int[] nums, int start, int end) {
    if (start == end) {
        List<Integer> list = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (int num : nums) {
            sb.append(num);
            list.add(num);
        }
        if (!set.contains(sb.toString())) {
           result.add(list); 
            set.add(sb.toString());
        }
        
        return;
    }
    for (int i = start; i <= end; i++) {
        swap(nums, start, i);
        permuteUtilSlow(nums,start+1, end);
        swap(nums, start, i);
    }
    
}
public List<List<Integer>> permuteUniqueSlow(int[] nums) {
    /*
     * Runtime: 41 ms, faster than 15.16% of Java online submissions for Permutations II.
Memory Usage: 39.7 MB, less than 74.02% of Java online submissions for Permutations II.
Bruitforce try to optimise by removing set
     */
    permuteUtil(nums, 0, nums.length-1);
    return result;
    
    
}
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
