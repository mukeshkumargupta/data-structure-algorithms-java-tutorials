package com.interview.searching;

/**
 * Date 03/04/2017
 * @author Mukesh Kumar Gupta
 *
 * How to append minimum numbers of characters in front of string to make it a palindrome.
 *
 * Idea is to create a new string which is original ttring + $ + reverse of original string
 * Get value of suffix which is also prefix using KMP.
 * This part of string is good. Rest needs to be copied in the front.
 *
 * Time complexity is O(n)
 * Space complexity is O(n)
 * https://www.youtube.com/watch?v=c4akpqTwE5g&t=240s
 *
 * https://leetcode.com/problems/shortest-palindrome/
 * Category: Hard, kmp, Must Do
 * You are given a string s. You can convert s to a palindrome by adding characters in front of it.

Return the shortest palindrome you can find by performing this transformation.

 

Example 1:

Input: s = "aacecaaa"
Output: "aaacecaaa"
Example 2:

Input: s = "abcd"
Output: "dcbabcd"
 

Constraints:

0 <= s.length <= 5 * 104
s consists of lowercase English letters only.
 */
public class ShortestPalindrome {
    private int computeTemporaryArray(char pattern[]){
        
        int [] lps = new int[pattern.length];
        int j =0;
        for(int i=1; i < pattern.length;){
            if(pattern[i] == pattern[j]){
                lps[i] = j + 1;
                j++;
                i++;
            }else{
                if(j != 0){
                    j = lps[j-1];
                }else{
                    lps[i] =0;
                    i++;
                }
            }
        }
        return lps[pattern.length-1]; 
    }
    public String shortestPalindrome(String s) {
        if (s.length() ==0) {
           return ""; 
        }
        StringBuilder sb = new StringBuilder();
        String reverseSting = sb.append(s).reverse().toString();
        String doubleString = s + "$" + reverseSting;//To avoid longer preflix suffix than actual length like aaaaaaaaaaa
        char[] input = doubleString.toCharArray();
        int max = computeTemporaryArray(input);
        int l = s.length();
        int extractLength = l-max;
        //System.out.println(extractLength);
        return reverseSting.substring(0, extractLength) + s;
    }

 }
