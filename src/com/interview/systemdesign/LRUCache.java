package com.interview.systemdesign;

import java.util.*;
/*
 * Reference: https://leetcode.com/problems/lru-cache/
 * Video: https://www.youtube.com/watch?v=iEmActx7dYc
 * Category: Medium, Must Do
 */
class LRUCache {
    int capacity;
    Map<Integer, Integer> linkedHashMap;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.linkedHashMap = new LinkedHashMap<>();
        
    }
    
    public int get(int key) {
        if (linkedHashMap.containsKey(key)) {
            int value = linkedHashMap.get(key);
            linkedHashMap.remove(key); //May in middle so remove it and  
            linkedHashMap.put(key, value);//Put in last insertion order maintained
            return value;
        }
        return -1;
        
    }
    
    public void put(int key, int value) {
        if (linkedHashMap.containsKey(key)) {
            linkedHashMap.remove(key); 
            
        } else {
            if (linkedHashMap.size() == capacity) {
                //First key will be which is inserted first which we need to remove
                for (int keyElm : linkedHashMap.keySet())  {
                    linkedHashMap.remove(keyElm);
                    break;
                }
                
            }
            
        }
        linkedHashMap.put(key, value);
        
    }
}
