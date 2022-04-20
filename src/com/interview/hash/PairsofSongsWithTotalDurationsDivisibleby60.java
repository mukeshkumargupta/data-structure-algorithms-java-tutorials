package com.interview.hash;

/*
 * https://leetcode.com/problems/pairs-of-songs-with-total-durations-divisible-by-60/
 * Category: Medium, Tricky
 * https://www.youtube.com/watch?v=toYgBIaUdfM
 * 
 * 1010. Pairs of Songs With Total Durations Divisible by 60
Medium

3060

118

Add to List

Share
You are given a list of songs where the ith song has a duration of time[i] seconds.

Return the number of pairs of songs for which their total duration in seconds is divisible by 60. Formally, we want the number of indices i, j such that i < j with (time[i] + time[j]) % 60 == 0.

 

Example 1:

Input: time = [30,20,150,100,40]
Output: 3
Explanation: Three pairs have a total duration divisible by 60:
(time[0] = 30, time[2] = 150): total duration 180
(time[1] = 20, time[3] = 100): total duration 120
(time[1] = 20, time[4] = 40): total duration 60
Example 2:

Input: time = [60,60,60]
Output: 3
Explanation: All three pairs have a total duration of 120, which is divisible by 60.
 

Constraints:

1 <= time.length <= 6 * 104
1 <= time[i] <= 500
Accepted
188,537
Submissions
351,135
 * 
 */
public class PairsofSongsWithTotalDurationsDivisibleby60 {
    public int numPairsDivisibleBy60(int[] time) {
        /*
         * TC o(N)
         * SC: O(K)
         */
        int[] count = new int[60];
        int res = 0;
        for(int i:time) count[i%60]++;
        
        //count the time from 1-29 & 29-59
        for(int i =1;i<30;i++) res+=count[i]*count[60-i];
        
        //edge case
        //How many pair: (n-1) + (n-2)+ ... + (n-n)
        //count the time 0 
        for(int i =1;i<count[0];i++) res+=count[0]-i;
        //count the time 30 
        for(int i =1;i<count[30];i++) res+=count[30]-i;
        
        return res;
        
    }
    
    /*
     * Below code is failing because of multiplication
     */
    public int numPairsDivisibleBy60(int[] time) {
        int[] frq = new int[60];
        
        for (int t : time) {
            frq[t%60] += 1;//count frequency of each remainder
        }
        //R1 + R2 == 60 =0 is valid case where r1 and 2 is different
        
        //0 and 1 is having special case and total count will be N*N-1/2 formula, 0 and half must have more than pain not single element
        int count = 0;
        count += (frq[0]* (frq[0]-1))/2;
        count += (frq[30]* (frq[30]-1))/2;
        //System.out.println(frq[0]);
        
        //take mirror image so 60/2 = 30, 0 and 30 is already covered
        // 2, 58 is covered then 58, 2 should not be covered
        for (int i = 1; i <= 29; i++) {
            count += frq[i] * frq[60-i];
        }
        
        return count;
        
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
