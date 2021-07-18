package com.interview.systemdesign;

import java.util.*;
/**
 * Date 05/08/2020
 * @author Mukesh Kumar Gupta
 * Reference: https://leetcode.com/problems/insert-delete-getrandom-o1/
 * Need to try: https://leetcode.com/problems/insert-delete-getrandom-o1-duplicates-allowed/
 * https://www.youtube.com/watch?v=O6QsZO0tupM
 * Reference: 1. https://www.geeksforgeeks.org/design-a-data-structure-that-supports-insert-delete-getrandom-in-o1-with-duplicates/?ref=rp
 * Reference: 2. https://www.geeksforgeeks.org/design-a-data-structure-that-supports-insert-delete-search-and-getrandom-in-constant-time/?ref=rp
 * Category: Medium, Must Do
 * Company: Google, Amazon, Facebook
 */


public class InsertDeleteGetRandomO1 {
    List<Integer> arrayList;
    Map<Integer, Integer> map;

    /** Initialize your data structure here. */
    public InsertDeleteGetRandomO1() {
        arrayList = new ArrayList<>();
        map = new HashMap<>();
        
    }
    
    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if (map.containsKey(val)) {
            return false;
        }
        
        arrayList.add(val);
        map.put(val, arrayList.size()-1);
        return true;
        
    }
    
    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if (!map.containsKey(val)) {
            return false;
        }
        int index = map.get(val);
        int size = arrayList.size();
        int lastValue = arrayList.get(size-1);
        if (index != size-1) {
            Collections.swap(arrayList, index, size-1);
            map.remove(val);
            arrayList.remove(size-1);

            //Update map for last swap element 
            map.put(lastValue, index);
            
        } else {
            map.remove(val);
            arrayList.remove(index);
        }

        return true;
    }
    
    /** Get a random element from the set. */
    public int getRandom() {
        Random rn = new Random();
        int index = rn.nextInt(arrayList.size());//Give 0 to 1 index
        return arrayList.get(index);
        
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
