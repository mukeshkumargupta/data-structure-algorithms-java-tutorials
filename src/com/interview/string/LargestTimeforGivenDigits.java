package com.interview.string;

/*
 * https://leetcode.com/problems/largest-time-for-given-digits/
 * Category: Medium, Google,
 * Related:
 * https://leetcode.com/problems/find-kth-bit-in-nth-binary-string/ Medium
 * https://leetcode.com/problems/replace-all-s-to-avoid-consecutive-repeating-characters/ Easy
 * https://leetcode.com/problems/determine-color-of-a-chessboard-square/ Easy
 * Given an array arr of 4 digits, find the latest 24-hour time that can be made using each digit exactly once.

24-hour times are formatted as "HH:MM", where HH is between 00 and 23, and MM is between 00 and 59. The earliest 24-hour time is 00:00, and the latest is 23:59.

Return the latest 24-hour time in "HH:MM" format.  If no valid time can be made, return an empty string.

 

Example 1:

Input: arr = [1,2,3,4]
Output: "23:41"
Explanation: The valid 24-hour times are "12:34", "12:43", "13:24", "13:42", "14:23", "14:32", "21:34", "21:43", "23:14", and "23:41". Of these times, "23:41" is the latest.
Example 2:

Input: arr = [5,5,5,5]
Output: ""
Explanation: There are no valid 24-hour times as "55:55" is not valid.
Example 3:

Input: arr = [0,0,0,0]
Output: "00:00"
Example 4:

Input: arr = [0,0,1,0]
Output: "10:00"
 

Constraints:

arr.length == 4
0 <= arr[i] <= 9
 */
public class LargestTimeforGivenDigits {
    public String largestTimeFromDigits(int[] arr) {
        /*
         * Runtime: 16 ms, faster than 26.65% of Java online submissions for Largest Time for Given Digits.
Memory Usage: 40.7 MB, less than 5.95% of Java online submissions for Largest Time for Given Digits.
         */
        String result = "";
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 4; k++) {
                    if (i == j || j == k || k ==i) {
                        continue;
                    }
                    
                    String hour = arr[i] + "" + arr[j];
                    String min = arr[k] + "" + arr[6-i-j-k];
                    String hourMin = hour + ":" + min;
                    if (hour.compareTo("24") < 0 && min.compareTo("60")  < 0 && hourMin.compareTo(result) > 0) {
                       result =  hourMin;
                    }
                }
            
            }
            
        }
        return result;
        
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
