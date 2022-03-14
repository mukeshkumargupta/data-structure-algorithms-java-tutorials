package com.interview.hash;

import java.util.*;
/*
 * Reference: https://leetcode.com/problems/lru-cache/
 * Video: https://www.youtube.com/watch?v=iEmActx7dYc
 * Related: https://leetcode.com/problems/lfu-cache/ Hard VVImp
 * https://leetcode.com/problems/design-in-memory-file-system/ Hard Locked
 * https://leetcode.com/problems/design-compressed-string-iterator/ Easy Locked
 * https://leetcode.com/problems/design-most-recently-used-queue/  Medium Locked
 * Category: Medium, Must Do, Fundamental
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
    
    /*
     * Practice p1
     * Runtime: 95 ms, faster than 47.26% of Java online submissions for LRU Cache.
Memory Usage: 134.5 MB, less than 45.29% of Java online submissions for LRU Cache.
     */
    
    int capacity;
    Map<Integer, Integer> map;
    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new LinkedHashMap<>();
    }
    
    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1 ;
        } else {
            int val = map.get(key);
            map.remove(key);
            map.put(key, val);
            return val;
        }
    }
    
    public void put(int key, int value) {
        if (map.containsKey(key)) {
            map.remove(key);
            map.put(key, value);
        } else {
            int size = map.size();
            if (size == capacity) {
                for (int firstKey : map.keySet()) {
                    map.remove(firstKey);
                    break;
                }
                
            } 
            map.put(key, value);
                
        }
        
    }
}
