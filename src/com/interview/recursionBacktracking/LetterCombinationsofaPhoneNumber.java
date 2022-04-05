package com.interview.recursionBacktracking;

import java.util.*;
/*
 * https://leetcode.com/problems/letter-combinations-of-a-phone-number/
 * Category: Medium, Must Do, Top150
 * Related: https://leetcode.com/problems/binary-watch/ Easy
 * Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent. Return the answer in any order.

A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.



 

Example 1:

Input: digits = "23"
Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]
Example 2:

Input: digits = ""
Output: []
Example 3:

Input: digits = "2"
Output: ["a","b","c"]
 

Constraints:

0 <= digits.length <= 4
digits[i] is a digit in the range ['2', '9'].
Accepted
993,717
Submissions
1,911,614
 */
public class LetterCombinationsofaPhoneNumber {
    public List<String> letterCombinations(String digits) {
        /*
         * Runtime: 1 ms, faster than 79.03% of Java online submissions for Letter Combinations of a Phone Number.
Memory Usage: 39.6 MB, less than 26.41% of Java online submissions for Letter Combinations of a Phone Number.
         */
        List<String> result = new ArrayList<>();
        if (digits == null || digits.length() == 0) {
            return result;
        }
        StringBuilder ds = new StringBuilder();
        
        Map<Character, String> lookup = new HashMap<>();
        lookup.put('2', "abc");
        lookup.put('3', "def");
        lookup.put('4', "ghi");
        lookup.put('5', "jkl");
        lookup.put('6', "mno");
        lookup.put('7', "pqrs");
        lookup.put('8', "tuv");
        lookup.put('9', "wxyz");
        letterCombinationsUtil(digits, 0, lookup, ds, result);
        return result;
        
    }
    private void letterCombinationsUtil(String digits, int start, Map<Character, String> lookup, StringBuilder ds, List<String> result) {
        if (start == digits.length()) {
            //System.out.println(ds.toString());
            result.add(ds.toString());
            return;
        }
        
        String pattern = lookup.get(digits.charAt(start));
        for (int i = 0; i < pattern.length(); i++) {
            ds.append(pattern.charAt(i));
            letterCombinationsUtil(digits, start+1, lookup, ds, result);
            ds.deleteCharAt(ds.length()-1);
        }
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
