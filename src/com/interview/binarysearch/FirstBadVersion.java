package com.interview.binarysearch;

/*
 * https://leetcode.com/problems/first-bad-version/
 * Category: Easy, Tricky
 * Related: https://leetcode.com/problems/guess-number-higher-or-lower/ Easy
 * 
 */
public class FirstBadVersion {
    int bst(int start , int end, int result) {
        
        while (start <= end) {
            int mid = start + (end - start)/2; //always use this way otherwise start + end may overflow
            if (isBadVersion(mid))  {
                result = mid;
                end = mid-1;
            } else {
                start = mid + 1;
            }
                
        }
        return result;
        
    }
    public int firstBadVersion(int n) {
        int result = n;
       return bst(1, n, result); 
    }
}
