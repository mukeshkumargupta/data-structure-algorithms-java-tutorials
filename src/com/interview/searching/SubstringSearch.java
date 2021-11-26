package com.interview.searching;

/**
 * Date 09/22/2017
 * @author Mukesh Kumar Gupta
 * 
 * Do pattern matching using KMP algorithm
 * 
 * Runtime complexity - O(m + n) where m is length of text and n is length of pattern
 * Space complexity - O(n)
 * Building prefix sub array: https://www.youtube.com/watch?v=KG44VoDtsAA&list=PLIA-9QRQ0RqHAzWyAsAn4m-1UlENJG1Cg&j=4
 * KMP Search: https://www.youtube.com/watch?v=GTJr8OvyEVQ&list=PLIA-9QRQ0RqHAzWyAsAn4m-1UlENJG1Cg&j=5
 * Category: Must Do
 */
public class SubstringSearch {
    
    /**
     * Compute temporary array to maintain size of suffix which is same as prefix
     * Time/space complexity is O(size of pattern)
     */
    private int[] computeLPSArray(char pattern[]){
        int [] lps = new int[pattern.length];//longest prefix substring which has same suffix
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
        return lps;
    }
    
    /**
     * KMP algorithm of pattern matching.
     */
    public boolean KMP( char [] pattern, char []text){
        
        int lps[] = computeLPSArray(pattern);
        int i=0;
        int j=0;
        while(i < text.length && j < pattern.length){
            if(text[i] == pattern[j]){
                i++;
                j++;
            }else{
                if(j!=0){
                    j = lps[j-1];
                }else{
                    i++;
                }
            }
        }
        if(j == pattern.length){
            return true;
        }
        return false;
    }
        
    public static void main(String args[]){
        
        String str = "abcxabcdabcdabcy";
        String subString = "abcdabcy";
        SubstringSearch ss = new SubstringSearch();
        boolean result = ss.KMP(subString.toCharArray(), str.toCharArray());
        System.out.println(str.contains(subString));//Mukesh
        
    }
}
