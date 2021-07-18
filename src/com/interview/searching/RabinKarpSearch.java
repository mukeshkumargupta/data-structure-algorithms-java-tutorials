package com.interview.searching;

/**
 * Date 09/25/2017
 * @author Mukesh Kumar Gupta
 *
 * Rabin Karp algorithms for substring matching.
 * https://www.youtube.com/watch?v=BQ9E-2umSWc
 * Category: Medium, Must Do
 * Status: Done
 */
public class RabinKarpSearch {

 // d is the number of characters in the input alphabet 
    static final int d = 256;

     /* pat -> pattern 
        txt -> text 
        q -> A prime number 
    */
    public int search(char[] pat, char[] txt, int q) 
    { 
        int M = pat.length; 
        int N = txt.length; 
        int i, j; 
        int p = 0; // hash value for pattern 
        int t = 0; // hash value for txt 
        int h = 1; 
        
        int result = -1;;

        // The value of h would be "pow(d, M-1)%q" 
        for (i = 0; i < M - 1; i++) 
            h = (h * d) % q; 

        // Calculate the hash value of pattern and first 
        // window of text 
        for (i = 0; i < M; i++) 
        { 
            p = (d * p + pat[i]) % q; 
            t = (d * t + txt[i]) % q; 
        } 

        // Slide the pattern over text one by one 
        for (i = 0; i <= N - M; i++) 
        { 

            // Check the hash values of current window of text 
            // and pattern. If the hash values match then only 
            // check for characters on by one 
            if ( p == t ) 
            { 
                /* Check for characters one by one */
                for (j = 0; j < M; j++) 
                { 
                    if (txt[i+j] != pat[j]) 
                        break; 
                } 

                // if p == t and pat[0...M-1] = txt[i, i+1, ...i+M-1] 
                if (j == M) {
                    result = i;
                    break;
                }
            } 

            // Calculate hash value for next window of text: Remove 
            // leading digit, add trailing digit 
            if ( i < N-M ) 
            { 
                t = (d*(t - txt[i]*h) + txt[i+M])%q; 

                // We might get negative value of t, converting it 
                // to positive 
                if (t < 0) 
                t = (t + q); 
            }
        }
        return result;
    } 

    
    public static void main(String args[]){
        String txt = "GEEKS FOR GEEKS"; 
        String pat = "GEEK"; 
        int q = 101; // A prime number 
        RabinKarpSearch rks = new RabinKarpSearch();
        int index = rks.search(pat.toCharArray(), txt.toCharArray(), q); 
        System.out.println(index);
    }
}
