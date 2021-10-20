package com.interview.searching;

/*
 * Reference:https://leetcode.com/problems/longest-happy-prefix/
 * Category: Hard, Prefix Suffix
 */
public class LongestHappyPrefix {
    private String computeTemporaryArray(char pattern[]){
        
        int [] lps = new int[pattern.length];
        int index =0;
        for(int i=1; i < pattern.length;){
            if(pattern[i] == pattern[index]){
                lps[i] = index + 1;
                index++;
                i++;
            }else{
                if(index != 0){
                    index = lps[index-1];
                }else{
                    lps[i] =0;
                    i++;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        int max = lps[pattern.length-1]; 
        int maxIndex = pattern.length-1;
        while (max > 0) {
            sb.append(pattern[maxIndex--]);
            max--;
        }
        return sb.reverse().toString();
    }
    public String longestPrefix(String s) {
        return computeTemporaryArray(s.toCharArray());
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
