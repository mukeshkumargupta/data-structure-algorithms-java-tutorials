package com.interview.mathematical;

import java.util.*;

/*
 * https://www.youtube.com/watch?v=AS3Hcla3Hm0&list=PLIA-9QRQ0RqHEJBbNYo3KjeDzoc8bnkai&index=3
 * https://leetcode.com/problems/leftmost-column-with-at-least-a-one/
 * Category: Medium, Tricky
 */
public class LeftmostColumnwithatLeastaOne {
    int leftMostComumWithAtleastOne(BinaryMatrix bm) {
        List<Integer> dim = bm.dimenstions();
        int R = dim.get(0);
        int C = dim.get(1);
        int result = -1;
        int i = 0; 
        int j = C -1;
        while ( i < R && j >= 0 ) {
            if (bm.get(i, j) == 1) {
                j--;
                result = j;
            } else {
                i++;
            }

        }
        return result;
        
        
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
