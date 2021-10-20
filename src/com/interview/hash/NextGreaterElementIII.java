package com.interview.hash;
import java.util.*;
/*
 * https://leetcode.com/problems/next-greater-element-iii/
 * Category: Medium
 */
public class NextGreaterElementIII {
    public static int nextGreaterElement(int n) {
        List<Integer> list = new ArrayList<>();
        while (n > 0) {
            list.add(n %10);
            n /= 10;
        }
        Collections.reverse(list);
        
        //Find last maxima
        int l = list.size();
        int lastMaximaIndex = 0;
        for (int i = 0; i < l; i++) {
            if ( (i - 1) >= 0 && (i + 1) < l) {//between
                if (list.get(i) > list.get(i-1) && list.get(i) > list.get(i+1)) {
                   lastMaximaIndex = i; 
                }
                
            }
            //Boundry case last maxima'
            if ((i - 1) >= 0 && i == l-1) {
                if (list.get(i) > list.get(i-1)) {
                   lastMaximaIndex = i; 
                }
                
            }
            
        }
        if (lastMaximaIndex == 0) { //means element is sroted
            return -1;
        }
        
        //swap previous element with last maxima if not greater element after maima else swpa with that greater element
        int findNextGreater = lastMaximaIndex + 1;
        int findNextGreaterIndex = lastMaximaIndex;
        for (; findNextGreater < l; findNextGreater++) {
            if (list.get(findNextGreater) > list.get(lastMaximaIndex-1)) {
                findNextGreaterIndex = findNextGreater;
            }
        }
        //swap element
        int temp = list.get(lastMaximaIndex -1);
        list.set(lastMaximaIndex -1, list.get(findNextGreaterIndex));
        list.set(findNextGreaterIndex, temp);
        //form number
        int number = 0;
        for (int i = 0; i < l; i++) {
            number = number*10 + list.get(i);
        }
        return number;
        
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        nextGreaterElement(12);
    }
    
}
