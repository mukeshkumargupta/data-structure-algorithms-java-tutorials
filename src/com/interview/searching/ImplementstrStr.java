package com.interview.searching;

/*
 * https://leetcode.com/problems/implement-strstr/
 * Category: Easy, kmp, Must Do
 */
public class ImplementstrStr {
 // totalCharacterLength is the number of characters in the input alphabet 
    static final int totalCharacterLength = 256;

     /* pat -> pattern 
        txt -> text 
        primeNumber -> A prime number 
    */
    public int search(char[] pat, char[] txt, int primeNumber) 
    { 
        int patternLength = pat.length; 
        int textLength = txt.length; 
        int i, j; 
        int patternHashCode = 0; // hash value for pattern 
        int textHashCode = 0; // hash value for txt 
        int power = 1; 
        
        int result = -1;

        // The value of power would be "pow(totalCharacterLength, patternLength-1)%primeNumber" 
        for (i = 0; i < patternLength - 1; i++) 
            power = (power * totalCharacterLength) % primeNumber; 

        // Calculate the hash value of pattern and first 
        // window of text 
        for (i = 0; i < patternLength; i++) 
        { 
            patternHashCode = (totalCharacterLength * patternHashCode + pat[i]) % primeNumber; 
            textHashCode = (totalCharacterLength * textHashCode + txt[i]) % primeNumber; 
        } 

        // Slide the pattern over text one by one 
        for (i = 0; i <= textLength - patternLength; i++) 
        { 

            // Check the hash values of current window of text 
            // and pattern. If the hash values match then only 
            // check for characters on by one 
            if ( patternHashCode == textHashCode ) 
            { 
                /* Check for characters one by one */
                for (j = 0; j < patternLength; j++) 
                { 
                    if (txt[i+j] != pat[j]) 
                        break; 
                } 

                // if patternHashCode == textHashCode and pat[0...patternLength-1] = txt[i, i+1, ...i+patternLength-1] 
                if (j == patternLength) {
                    result = i;
                    break;
                }
            } 

            // Calculate hash value for next window of text: Remove 
            // leading digit, add trailing digit 
            if ( i < textLength-patternLength ) 
            { 
                textHashCode = (totalCharacterLength*(textHashCode - txt[i]*power) + txt[i+patternLength])%primeNumber;//this is multiplied to shift left side because after removal of first char, need to shift left 

                // We might get negative value of textHashCode, converting it 
                // to positive 
                if (textHashCode < 0) 
                textHashCode = (textHashCode + primeNumber); 
            }
        }
        return result;
    }  
    public int strStr(String haystack, String needle) {//runtime 71%
        if (needle.length() ==0) {
            return 0;
        }

        
        if (needle.length() > haystack.length()) {
            return -1;
        }
        return search(needle.toCharArray(), haystack.toCharArray(), 101) ;
    } 
}
