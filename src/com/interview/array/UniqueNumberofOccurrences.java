package com.interview.array;

import java.util.*;
/*
 * Reference: https://leetcode.com/problems/unique-number-of-occurrences/
 * Category: Easy
 */
public class UniqueNumberofOccurrences {
    public boolean uniqueOccurrences(int[] arr) {
        int len = arr.length;
        Map<Integer, Integer> mapData = new LinkedHashMap<>();
        for (int i = 0; i < len ; i++) {
            Integer key = arr[i];
            if (mapData.containsKey(key)) {
               mapData.put(key, mapData.get(key)+1);
            } else {
                mapData.put(key, 1);  
            }
        }
        Map<Integer, Integer> mapDataDuplicateDetails = new LinkedHashMap<>();
        for (int key : mapData.keySet()) {
            Integer frq = mapData.get(key);
            if (mapDataDuplicateDetails.containsKey(frq)) {
               return false;
            } else {
                mapDataDuplicateDetails.put(frq, 1);  
            }
        }
        return true;
        
        
    }
}
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
