package com.interview.array;

import java.util.*;
/*
 * Reference: https://leetcode.com/problems/unique-number-of-occurrences/
 * Category: Easy
 */
public class UniqueNumberofOccurrences {
    public boolean uniqueOccurrences(int[] arr) {
        int len = arr.length;
        Map<Integer, Integer> mapval = new LinkedHashMap<>();
        for (int i = 0; i < len ; i++) {
            Integer key = arr[i];
            if (mapval.containsKey(key)) {
               mapval.put(key, mapval.get(key)+1);
            } else {
                mapval.put(key, 1);  
            }
        }
        Map<Integer, Integer> mapvalDuplicateDetails = new LinkedHashMap<>();
        for (int key : mapval.keySet()) {
            Integer frq = mapval.get(key);
            if (mapvalDuplicateDetails.containsKey(frq)) {
               return false;
            } else {
                mapvalDuplicateDetails.put(frq, 1);  
            }
        }
        return true;
        
        
    }
}
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
