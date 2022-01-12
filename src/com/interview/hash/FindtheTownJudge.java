package com.interview.hash;

/*
 * https://leetcode.com/problems/find-the-town-judge/
 * Category: Easy, tricky
 * Related:
 * https://leetcode.com/problems/find-the-celebrity/ Medium
 * 
 * In a town, there are n people labeled from 1 to n. There is a rumor that one of these people is secretly the town judge.

If the town judge exists, then:

The town judge trusts nobody.
Everybody (except for the town judge) trusts the town judge.
There is exactly one person that satisfies properties 1 and 2.
You are given an array trust where trust[i] = [ai, bi] representing that the person labeled ai trusts the person labeled bi.

Return the label of the town judge if the town judge exists and can be identified, or return -1 otherwise.

 

Example 1:

Input: n = 2, trust = [[1,2]]
Output: 2
Example 2:

Input: n = 3, trust = [[1,3],[2,3]]
Output: 3
Example 3:

Input: n = 3, trust = [[1,3],[2,3],[3,1]]
Output: -1
Example 4:

Input: n = 3, trust = [[1,2],[2,3]]
Output: -1
 */
public class FindtheTownJudge {
    public int findJudge(int n, int[][] trust) {
        /*
         * Runtime: 4 ms, faster than 47.47% of Java online submissions for Find the Town Judge.
Memory Usage: 81.6 MB, less than 19.66% of Java online submissions for Find the Town Judge.
Try to solve like celebrity problem because it is slow here, this solution is good solution and straight forward
         */
                
        int[] follow = new int [n];
        int[] follower = new int [n];
        
        for (int[] t : trust) {
            follow[t[0]-1]++;
            follower[t[1]-1]++;
        }
        
        
        for (int i = 0; i < n; i++) {
            if (follower[i] == n-1 && follow[i] == 0) {
               return i+1;
            }
        }
        return -1;
        
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
