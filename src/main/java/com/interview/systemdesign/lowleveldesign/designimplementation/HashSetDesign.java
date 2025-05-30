package com.interview.systemdesign.lowleveldesign.designimplementation;

import java.util.*;
/*
 * Reference: https://leetcode.com/problems/design-hashset/
 * https://www.youtube.com/watch?v=U79BoHTcCYw&list=PL1w8k37X_6L-bCZ3m0FFBZmRv4onE7Zjl&index=17
 * Category: Easy, Google, Must Do
 * Related: https://leetcode.com/problems/design-skiplist/  Hard
 */
public class HashSetDesign {
    int MAX = 150;
    List<Integer>[] lookup;

    /** Initialize your val structure here. */
    public HashSetDesign() {
        lookup = new LinkedList[MAX];
        
    }
    
    private int hashFunction(int key) {
        return key % MAX;
        
    }
    
    public void add(int key) {
        int index = hashFunction(key);
        if (lookup[index] == null) {
            List<Integer> list = new LinkedList<>();
            list.add(key);
            lookup[index] = list;
        } else if (lookup[index].indexOf(key) == -1) {
            lookup[index].add(key);
        }
    }
    
    public void remove(int key) {
        int index = hashFunction(key);
        if (lookup[index] == null || lookup[index].indexOf(key) == -1) {
            return;
        } else {
            lookup[index].remove(lookup[index].indexOf(key));
        }
        
    }
    
    /** Returns true if this set contains the specified element */
    public boolean contains(int key) {
        int index = hashFunction(key);
        if (lookup[index] == null || lookup[index].indexOf(key) == -1) {
            return false;
        }
        return true;
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
