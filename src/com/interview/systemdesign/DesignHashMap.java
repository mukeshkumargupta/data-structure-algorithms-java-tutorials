package com.interview.systemdesign;
import java.util.*;
/*
 * Reference: https://leetcode.com/problems/design-hashmap
 */
public class DesignHashMap {
    /*class Node {
        int key;
        int value;
        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
    int MAX = 150;
    List<Node>[] lookup;


    public DesignHashMap() {
        lookup = new LinkedList[MAX];
    }
    int getHash(int key) {
        return key % MAX;
    }
    
    public void put(int key, int value) {
        int index = getHash(key);
        Node node = new Node(key, value);
        if (lookup[index] == null) {
            List<Node> list = new LinkedList<>();
            list.add(node);
            
        } else {
            if (lookup[index].indexOf(node) == -1) {
                lookup[index].add(node);
            }
        }
    }
    

    public int get(int key) {

    }
    
    //Removes the mapping of the specified value key if this map contains a mapping for the key 
    public void remove(int key) {
        lookup[key] = -1;
        
    }*/
    int MAX = 1000001;
    int[] lookup = new int[MAX];

    /** Initialize your data structure here. */
    public DesignHashMap() {
        for (int i = 0; i < MAX; i++) {
            lookup[i] = -1;
        }

        
    }
    
    /** value will always be non-negative. */
    public void put(int key, int value) {
        lookup[key] = value;
    }
    
    /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
    public int get(int key) {
        if (lookup[key] != -1) {
            return lookup[key];
        }
        return -1;
    }
    
    /** Removes the mapping of the specified value key if this map contains a mapping for the key */
    public void remove(int key) {
        lookup[key] = -1;
        
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
