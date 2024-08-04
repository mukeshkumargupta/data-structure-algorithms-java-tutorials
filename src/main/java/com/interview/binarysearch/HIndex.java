package com.interview.binarysearch;
import java.util.*;
/*
 * https://leetcode.com/problems/h-index/
 * Category: Medium, Tricky
 * Related: https://leetcode.com/problems/find-lucky-integer-in-an-array/ Easy
 * https://leetcode.com/problems/minimum-speed-to-arrive-on-time/ Medium
 * https://leetcode.com/problems/find-a-peak-element-ii/ Medium
 * Given an array of integers citations where citations[i] is the number of citations a researcher received for their ith paper, return compute the researcher's h-index.

According to the definition of h-index on Wikipedia: A scientist has an index h if h of their n papers have at least h citations each, and the other n − h papers have no more than h citations each.

If there are several possible values for h, the maximum one is taken as the h-index.

 

Example 1:

Input: citations = [3,0,6,1,5]
Output: 3
Explanation: [3,0,6,1,5] means the researcher has 5 papers in total and each of them had received 3, 0, 6, 1, 5 citations respectively.
Since the researcher has 3 papers with at least 3 citations each and the remaining two with no more than 3 citations each, their h-index is 3.
Example 2:

Input: citations = [1,3,1]
Output: 1
 */

public class HIndex {
    public int hIndex(int[] citations) {//100% runtime
        Arrays.sort(citations);
        int l = citations.length;
        int start=0,end=l-1,mid;
        
        while(start<=end)
        {
            mid = start+(end-start)/2;
            if(citations[mid]==(l-mid))
                return citations[mid];
            else if(citations[mid]>(l-mid))
                end = mid-1;
            else
                start = mid+1;
        }
        return l-start;
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
