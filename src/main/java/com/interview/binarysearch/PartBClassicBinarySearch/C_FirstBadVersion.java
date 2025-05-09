package com.interview.binarysearch.PartBClassicBinarySearch;

/*
 * https://leetcode.com/problems/first-bad-version/
 * Category: Easy, Must Do, Facebook
 * Related: https://leetcode.com/problems/guess-number-higher-or-lower/ Easy
 *
 * You are a product manager and currently leading a team to develop a new product.
 * Unfortunately, the latest version of your product fails the quality check.
 * Since each version is developed based on the previous version, all the versions after a bad version are also bad.
 *
 * Suppose you have n versions [1, 2, ..., n] and you want to find out the first bad one,
 * which causes all the following ones to be bad.
 *
 * You are given an API bool isBadVersion(version) which returns whether a version is bad.
 * Implement a function to find the first bad version.
 * You should minimize the number of calls to the API.
 *
 * Example 1:
 *
 * Input: n = 5, bad = 4
 * Output: 4
 * Explanation:
 * call isBadVersion(3) -> false
 * call isBadVersion(5) -> true
 * call isBadVersion(4) -> true
 * Then 4 is the first bad version.
 *
 * Example 2:
 *
 * Input: n = 1, bad = 1
 * Output: 1
 *
 * Constraints:
 *
 * 1 <= bad <= n <= 2^31 - 1
 *
 */
public class C_FirstBadVersion {
    boolean isBadVersion(int number) {//dummy
        return true;
    }
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
