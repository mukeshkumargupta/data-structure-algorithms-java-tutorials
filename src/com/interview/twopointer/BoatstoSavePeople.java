package com.interview.twopointer;

/*
 * https://leetcode.com/problems/boats-to-save-people/
 * Category: Medium, Tricky, Greedy, two pointer
 * Related:
 * Here only max two people allowed in boat, but if more that two allowed which is under limit then try to solve it
 * https://leetcode.com/problems/evaluate-reverse-polish-notation/ Medium
 * https://leetcode.com/problems/push-dominoes/ Medium
 * https://leetcode.com/problems/ways-to-make-a-fair-array/ Medium
 * You are given an array people where people[i] is the weight of the ith person, and an infinite number of boats where each boat can carry a maximum weight of limit. Each boat carries at most two people at the same time, provided the sum of the weight of those people is at most limit.

Return the minimum number of boats to carry every given person.

 

Example 1:

Input: people = [1,2], limit = 3
Output: 1
Explanation: 1 boat (1, 2)
Example 2:

Input: people = [3,2,2,1], limit = 3
Output: 3
Explanation: 3 boats (1, 2), (2) and (3)
Example 3:

Input: people = [3,5,3,4], limit = 5
Output: 4
Explanation: 4 boats (3), (3), (4), (5)
 

Constraints:

1 <= people.length <= 5 * 104
1 <= people[i] <= limit <= 3 * 104
 *  
 */
public class BoatstoSavePeople {
    public int numRescueBoats(int[] people, int limit) {
        /*
         * Runtime: 14 ms, faster than 91.22% of Java online submissions for Boats to Save People.
Memory Usage: 47.8 MB, less than 88.29% of Java online submissions for Boats to Save People.
         */
        Arrays.sort(people);
        int l = 0;
        int r = people.length -1;
        
        int minBoat = 0;
        while (l <= r) {
            if (people[l] + people[r] <= limit) {
                l++;
            }
            
            r--;
            minBoat++; 
        }
        return minBoat;
        
    }
    
    public int numRescueBoatsPractice1(int[] people, int limit) {
        int l = people.length;
        int count = 0;
        int left = 0; int right = l-1;
        Arrays.sort(people);
        
        while (left <= right) {
            //System.out.println(left + " " + right);
            if (left != right && people[right] + people[left] <= limit) {
               left++; 
            }
            
            right--;
            count++;
        }
        return count;
        
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}