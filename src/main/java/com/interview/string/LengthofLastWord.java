package com.interview.string;

/*
 * Related Problems:
 *
 * https://www.youtube.com/watch?v=E8r1N8wNR0A&list=PL1w8k37X_6L-bCZ3m0FFBZmRv4onE7Zjl&index=37
 * Category: Easy, Google, Tricky
 *
 * https://leetcode.com/problems/length-of-last-word/ (Easy)
 * https://leetcode.com/problems/interleaving-string/ (Medium)
 * https://leetcode.com/problems/encode-number/ (Medium)
 * https://leetcode.com/problems/palindrome-partitioning-iii/ (Medium)
 *
 * Problem Statement:
 * Given a string `s` consisting of words separated by spaces, return the length of the last word in the string.
 *
 * A word is a maximal substring consisting of non-space characters only.
 *
 * Example 1:
 * Input: s = "Hello World"
 * Output: 5
 * Explanation: The last word is "World" with length 5.
 *
 * Example 2:
 * Input: s = "   fly me   to   the moon  "
 * Output: 4
 * Explanation: The last word is "moon" with length 4.
 */
public class LengthofLastWord {
    
    public int lengthOfLastWord(String s) {
        /*
         * Runtime: 0 ms, faster than 100.00% of Java online submissions for Length of Last Word.
Memory Usage: 38.9 MB, less than 32.64% of Java online submissions for Length of Last Word.
         */
        int count=0;
        int n =s.length();
        for(int i =n-1;i>=0;i--){
            if(s.charAt(i) == ' '){
               if(count >0) break;
            }
            else{
                count++;
            }
        }
        return count;
    }
    
    public int lengthOfLastWordM2(String s) {
        /*
         * 53% runtime
         */
        s = s.replace("//+", " ");
        if (s.equals(" ")) {
           return 0; 
        }
        String[] strings = s.split(" ");
        int length = strings.length;
        if (length > 0) {
            return strings[length-1].length();
        } else {
            return 0;
        }
        
        
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
