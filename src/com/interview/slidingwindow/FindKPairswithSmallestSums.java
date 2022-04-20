package com.interview.slidingwindow;

/*
 * TLE coming
 * https://leetcode.com/problems/find-k-pairs-with-smallest-sums/
 * Category: Medium, Must Do
 * https://leetcode.com/problems/find-k-pairs-with-smallest-sums/
 * You are given two integer arrays nums1 and nums2 sorted in ascending order and an integer k.

Define a pair (u, v) which consists of one element from the first array and one element from the second array.

Return the k pairs (u1, v1), (u2, v2), ..., (uk, vk) with the smallest sums.

 

Example 1:

Input: nums1 = [1,7,11], nums2 = [2,4,6], k = 3
Output: [[1,2],[1,4],[1,6]]
Explanation: The first 3 pairs are returned from the sequence: [1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]
Example 2:

Input: nums1 = [1,1,2], nums2 = [1,2,3], k = 2
Output: [[1,1],[1,1]]
Explanation: The first 2 pairs are returned from the sequence: [1,1],[1,1],[1,2],[2,1],[1,2],[2,2],[1,3],[1,3],[2,3]
Example 3:

Input: nums1 = [1,2], nums2 = [3], k = 3
Output: [[1,3],[2,3]]
Explanation: All possible pairs are returned from the sequence: [1,3],[2,3]
 

Constraints:

1 <= nums1.length, nums2.length <= 105
-109 <= nums1[i], nums2[i] <= 109
nums1 and nums2 both are sorted in ascending order.
1 <= k <= 104
Accepted
166,730
Submissions
429,317
 */
public class FindKPairswithSmallestSums {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        /*
         * TLE coming so solve in better way
         */
        int l1 = nums1.length;
        int l2 = nums2.length;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)-> {
            return b[0] + b[1]  - (a[0] + a[1]); //max heap
        });
        for (int i = 0; i < l1; i++) {
            for (int j = 0; j <l2; j++) {
                int size = pq.size();
                if (size == k) {
                    int[] peak = pq.peek();
                    if (!pq.isEmpty() && peak[0] + peak[1] > nums1[i] + nums2[j]) {
                        pq.remove();
                        pq.add(new int[]{nums1[i], nums2[j]});
                    }
                    
                } else {
                    pq.add(new int[]{nums1[i], nums2[j]});
                }
                
            }
        }
        int size = pq.size();
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0 ; i < size && i <k ; i++) {
            int[] elm = pq.remove();
            List<Integer> temp = new ArrayList<>();
            for (int j = 0; j < 2; j++) {
                temp.add(elm[j]);
            }
            result.add(temp);
        }
        return result;
        
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
