package com.interview.matrix;
import java.util.*;
    
/*
 * https://leetcode.com/problems/diagonal-traverse-ii/
 * Category: Medium, Tricky
 * Related: https://leetcode.com/problems/ones-and-zeroes/ Medium
 * https://leetcode.com/problems/first-unique-number/ Medium
 * https://leetcode.com/problems/find-the-middle-index-in-array/ Easy
 * Derived question: Reverse is also done using API: findDiagonalOrderReverse
 * Given a list of lists of integers, nums, return all elements of nums in diagonal order as shown in the below images.
 

Example 1:



Input: nums = [[1,2,3],[4,5,6],[7,8,9]]
Output: [1,4,2,7,5,3,8,6,9]
Example 2:



Input: nums = [[1,2,3,4,5],[6,7],[8],[9,10,11],[12,13,14,15,16]]
Output: [1,6,2,8,7,3,9,4,12,10,5,13,11,14,15,16]
Example 3:

Input: nums = [[1,2,3],[4],[5,6,7],[8],[9,10,11]]
Output: [1,4,2,5,3,8,6,9,7,10,11]
Example 4:

Input: nums = [[1,2,3,4,5,6]]
Output: [1,2,3,4,5,6]
 

Constraints:

1 <= nums.length <= 10^5
1 <= nums[i].length <= 10^5
1 <= nums[i][j] <= 10^9
There at most 10^5 elements in nums.
 */
public class DiagonalTraverseII {
    public int[] findDiagonalOrder(List<List<Integer>> nums) {//50% runtime, little looks better but both are good approach
        int R = nums.size();
        /*
        00 01 02 03 04
        10 11
        20 
        30 31 32
        40 41 42 43 44 
        
        map of i + j - > diagnols
        as i + j means diagnols
        stack is used to reverse print values
        */
        HashMap<Integer, Stack<Integer>> map = new LinkedHashMap();
        int totalCount = 0;
        for(int i = 0; i < R; i++) {
            List<Integer> childList = nums.get(i);
            for(int j = 0; j < childList.size(); j++) {
                totalCount++;
                Stack s = null;
                if (!map.containsKey(i + j) ){
                    map.put(i + j, new Stack());
                }
                map.get(i + j).add(childList.get(j));
            }
        }
        int[] ans = new int[totalCount];
        int ind = 0;
        
        for(int key: map.keySet()) {
            Stack<Integer> s = map.get(key);
            while(s != null && !s.isEmpty()) {
                ans[ind++] = s.pop();
            }
        }

        return ans;
    }
    public int[] findDiagonalOrderM2(List<List<Integer>> nums) {//Runtime: 47 ms, faster than 41.72% of Java online submissions for Diagonal Traverse II.
        int R = nums.size();

        List<int[]> result = new ArrayList<>();
        int totalElement = 0;
        for (int i = 0; i < R; i++) {
            List<Integer> childList = nums.get(i);
            for (int j = 0; j <  childList.size(); j++) {
                int[] arr = new int [3]; //first store r+c, second store r and thrid store value, first sort for r+c then sort by r
                arr[0] = i+j;
                arr[1] = i;
                arr[2] = childList.get(j);
                result.add(arr);
                totalElement++;
            }
            
        }
        //now sort first by r+c then by r
        Collections.sort(result, (a, b) -> {
            if (a[0] == b[0] ) {//if r+c same then sort by r in decreasing order
                return b[1] - a[1];
            } else {
                return a[0]  - b[0]; //increasing order
            }
        });
        //now fill the valie
        int[] output = new int[totalElement];
        int ind = 0;
        for (int[] arr : result) {
            output[ind++] = arr[2];
        }
        return output;

        
    }
    
    public int[] findDiagonalOrderReverse(List<List<Integer>> nums) {//50% runtime, little looks better but both are good approach
        int R = nums.size();
        /*
        00 01 02 03 04
        10 11
        20 
        30 31 32
        40 41 42 43 44 
        
        map of i + j - > diagnols
        as i + j means diagnols
        stack is used to reverse print values
        */
        HashMap<Integer, List<Integer>> map = new LinkedHashMap();
        int totalCount = 0;
        for(int i = 0; i < R; i++) {
            List<Integer> childList = nums.get(i);
            for(int j = 0; j < childList.size(); j++) {
                totalCount++;
                List s = null;
                if (!map.containsKey(i + j) ){
                    map.put(i + j, new ArrayList<>());
                }
                map.get(i + j).add(childList.get(j));
            }
        }
        int[] ans = new int[totalCount];
        int ind = 0;
        
        for(int key: map.keySet()) {
            List<Integer> s = map.get(key);
            for (int elm: s) {
               ans[ind++] = elm;
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
