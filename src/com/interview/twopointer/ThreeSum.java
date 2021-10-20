package com.interview.twopointer;

import java.util.*;

/*
 * Reference: https://leetcode.com/problems/3sum/
 * Category: Medium, Tricky, Google
 * Derived: Find all triplet whose sum is given sum, less than su,. greater than sum or zero 
 * Given an array of distinct integers. The task is to count all the triplets such that sum of two elements equals the third element.
 */
public class ThreeSum {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        int len = nums.length;
        
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < len - 2; i++) {
            // If find solution for previous one the ignore current
            if (i > 0 && nums[i] == nums[i - 1])
                continue;
            
            int j = i + 1;
            int k = len - 1;
            
            while (j < k) {
                if (nums[i] + nums[j] + nums[k] == 0) {
                    List<Integer> temp = new ArrayList<Integer>();
                    temp.add(nums[i]);
                    temp.add(nums[j]);
                    temp.add(nums[k]);
                    result.add(temp);
                    j++;
                    k--;
                    while (j < len && nums[j] == nums[j - 1]) {
                        j++;
                    }
                    while (k >= 0 && nums[k] == nums[k + 1]) {
                        k--;
                    }
                } else if (nums[i] + nums[j] + nums[k] < 0) {
                    j++;
                } else {
                    k--;
                }
            }
            
        }
        return result;
    }
    /*
     * Given an array of distinct integers. The task is to count all the triplets such that sum of two elements equals the third element.
     * https://practice.geeksforgeeks.org/problems/count-the-triplets4615/1
     * Category: Must Do
     * Example 1:

Input:
N = 4
arr[] = {1, 5, 3, 2}
Output: 2
Explanation: There are 2 triplets: 
1 + 2 = 3 and 3 +2 = 5 
â€‹Example 2:

Input: 
N = 3
arr[] = {2, 3, 4}
Output: 0
Explanation: No such triplet exits
Your Task:  
You don't need to read input or print anything. Your task is to complete the function countTriplet() which takes the array arr[] and N as inputs and returns the triplet count

Expected Time Complexity: O(N2)
Expected Auxiliary Space: O(1)

Constraints:
1 ≤ N ≤ 103
1 ≤ arr[i] ≤ 105
     */
    public static void twoSumEqualThird(String[] args) {
        // code
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        class Point {
            public Point(int x, int y, int z) {
                this.x = x;
                this.y = y;
                this.z = z;
            }
            public int x;
            public int y;
            public int z;
        }

        for (int i = 1; i <= T; i++) {
            ArrayList<Point> outputList = new ArrayList<Point>();
            int output = 0;
            int N = sc.nextInt();
            int[] input = new int[N];
            ArrayList<Integer> list = new ArrayList<Integer>();
            // scan input
            for (int j = 0; j < N; j++) {
                input[j] = sc.nextInt();
                list.add(input[j]);
            }

            Collections.sort(list);
            for (int k = N - 1; k > 1; k--) {
                int l = 0; 
                int r = k-1;
                for ( ; l < r; ) {
                    if (list.get(l) + list.get(r) > list.get(k)) {
                        r = r - 1;
                    } else if (list.get(l) + list.get(r) < list.get(k)) {
                        l = l + 1;
                    } else {
                        outputList.add(new Point(list.get(l), list.get(r), list.get(k)));
                        output = output + 1;
                        l = l + 1;
                        r = r - 1;
                    }

                }
            }
            if (output == 0) {
                System.out.println(-1);
            } else {
                System.out.println(output);
                /*for (int m = 0; m < outputList.size(); m++ ) {
                    System.out.println(outputList.get(m).x + ", " + outputList.get(m).y);   
                }*/

            }
        }
    }
    /*
     * Category: Medium, Triplet
     * Related;
     * https://www.interviewbit.com/problems/pair-with-given-difference/ Easy
     * https://www.interviewbit.com/problems/maximum-ones-after-modification/ Medium
     * https://www.interviewbit.com/problems/merge-two-sorted-lists-ii/ Medium
     * https://leetcode.com/problems/count-good-triplets/ Easy Bad review given
     * https://leetcode.com/problems/count-triplets-that-can-form-two-arrays-of-equal-xor/ Medium
     * https://leetcode.com/problems/increasing-triplet-subsequence/ Medium
     */
    public int threeSumClosest(ArrayList<Integer> A, int B) {
        Collections.sort(A, (a, b) -> {
            return a -b;
        });
        int l = A.size();
        int diff = Integer.MAX_VALUE; 
        int result = Integer.MAX_VALUE;
        for (int i = 0; i < l-2; i++) {
            int j = i+1;
            int k = l-1;
            while (j < k) {
                int sum = A.get(i) + A.get(j) + A.get(k);
                if (sum > B) {
                    k--;
                } else {
                    j++;
                }
                if (Math.abs(sum-B) < diff) {
                    diff = Math.abs(sum-B);
                    result = sum;
                    if (diff ==0) {
                        break;
                    }
                }

            }
            if (diff ==0) {
                break;
            }

        }
        return result;
    }
    
    /**
     * Given an array of n integers nums and a target, find the number of index triplets i, j, k
     * with 0 <= i < j < k < n that satisfy the condition nums[i] + nums[j] + nums[k] < target.
     *
     * https://leetcode.com/problems/3sum-smaller/
     * https://www.youtube.com/watch?v=_A8obVmv6mc 
     * Category: Medium,triplet, Tricky
     * Related: https://leetcode.com/problems/increasing-triplet-subsequence/ Medium
     * https://leetcode.com/problems/count-triplets-that-can-form-two-arrays-of-equal-xor/ Medium
     * https://leetcode.com/problems/count-good-triplets/ Easy
     * Count three sum greater than target, Need to try
     * 
     */
    public int threeSumSmaller(int[] nums, int target) {
        if (nums.length < 3) {
            return 0;
        }
        Arrays.sort(nums);
        int count = 0;
        for (int i = 0; i < nums.length -2; i++) {
            int j = i + 1;
            int k = nums.length - 1;
            while (j < k) {
                if (nums[i] + nums[j] + nums[k] >= target) {
                    k--;
                } else {
                    count += k - j;//Tricky
                    j++;
                }
            }
        }
        return count;
    }
    

}
