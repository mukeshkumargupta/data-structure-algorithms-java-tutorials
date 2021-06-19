package com.interview.string;

/*
 * Reference: https://leetcode.com/problems/longest-common-prefix/
 * Video: https://www.youtube.com/watch?v=fhyIORFDD0k
 * Category: Easy
 * 
 */

public class LongestCommonPrefix {
    
    // A Function to find the string having the minimum
// length and returns that length
private int findMinLength(String arr[], int n)
{
    int min = arr[0].length();

    for (int i = 1; i < n; i++)
    {
        if (arr[i].length() < min)
        {
            min = arr[i].length();
        }
    }

    return (min);
}

// A Function that returns the longest common prefix
// from the array of strings
 String longestCommonPrefix(String arr[]) //Working
{
     int n = arr.length;
     if (n == 0) {
         return "";
         
     }
    int minlen = findMinLength(arr, n);

    String result = ""; // Our resultant string
    char current; // The current character

    for (int i = 0; i < minlen; i++)
    {
        // Current character (must be same
        // in all strings to be a part of
        // result)
        current = arr[0].charAt(i);

        for (int j = 1; j < n; j++)
        {
            if (arr[j].charAt(i) != current)
            {
                return result;
            }
        }

        // Append to result
        result += (current);
    }

    return (result);
}
  
    public static void main(String[] args) {
        LongestCommonPrefix lcp = new LongestCommonPrefix();
        String[] input = {"flower","flow","floor"};
        lcp.longestCommonPrefix(input);
        
    }
    
}
