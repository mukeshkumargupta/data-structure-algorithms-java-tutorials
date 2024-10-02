package com.interview.systemdesign;

import java.util.*;
/**
 * Date 05/08/2020
 * @author Mukesh Kumar Gupta
 * Reference: https://leetcode.com/problems/insert-delete-getrandom-o1/
 * Need to try: https://leetcode.com/problems/insert-delete-getrandom-o1-duplicates-allowed/
 * https://www.youtube.com/watch?v=O6QsZO0tupM
 * Reference: 1. https://www.geeksforgeeks.org/design-a-val-structure-that-supports-insert-delete-getrandom-in-o1-with-duplicates/?ref=rp
 * Reference: 2. https://www.geeksforgeeks.org/design-a-val-structure-that-supports-insert-delete-search-and-getrandom-in-constant-time/?ref=rp
 * Category: Medium, Must Do
 * Company: Google, Amazon, Facebook
 * Implement the RandomizedSet class:

RandomizedSet() Initializes the RandomizedSet object.
bool insert(int val) Inserts an item val into the set if not present. Returns true if the item was not present, false otherwise.
bool remove(int val) Removes an item val from the set if present. Returns true if the item was present, false otherwise.
int getRandom() Returns a random element from the current set of elements (it's guaranteed that at least one element exists when this method is called). Each element must have the same probability of being returned.
You must implement the functions of the class such that each function works in average O(1) time complexity.

 

Example 1:

Input
["RandomizedSet", "insert", "remove", "insert", "getRandom", "remove", "insert", "getRandom"]
[[], [1], [2], [2], [], [1], [2], []]
Output
[null, true, false, true, 2, true, false, 2]

Explanation
RandomizedSet randomizedSet = new RandomizedSet();
randomizedSet.insert(1); // Inserts 1 to the set. Returns true as 1 was inserted successfully.
randomizedSet.remove(2); // Returns false as 2 does not exist in the set.
randomizedSet.insert(2); // Inserts 2 to the set, returns true. Set now contains [1,2].
randomizedSet.getRandom(); // getRandom() should return either 1 or 2 randomly.
randomizedSet.remove(1); // Removes 1 from the set, returns true. Set now contains [2].
randomizedSet.insert(2); // 2 was already in the set, so return false.
randomizedSet.getRandom(); // Since 2 is the only number in the set, getRandom() will always return 2.
 

Constraints:

-231 <= val <= 231 - 1
At most 2 * 105 calls will be made to insert, remove, and getRandom.
There will be at least one element in the data structure when getRandom is called.
 */


public class InsertDeleteGetRandomO1 {
    private List<Integer> arrayList;           // List to store elements
    private Map<Integer, Integer> map;         // Map to store value and its index in the list
    private Random random;                      // Random object for generating random numbers

    /** Initialize your data structure here. */
    public InsertDeleteGetRandomO1() {
        arrayList = new ArrayList<>();
        map = new HashMap<>();
        random = new Random();
    }

    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if (map.containsKey(val)) {
            return false; // Value already exists
        }
        arrayList.add(val);                          // Add value to list
        map.put(val, arrayList.size() - 1);        // Map value to its index
        return true;
    }

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if (!map.containsKey(val)) {
            return false; // Value does not exist
        }

        int index = map.get(val);                   // Get the index of the value to remove
        int lastValue = arrayList.get(arrayList.size() - 1); // Get the last value

        // Swap the value to be removed with the last value
        arrayList.set(index, lastValue);
        map.put(lastValue, index);                  // Update the map for the last value

        arrayList.remove(arrayList.size() - 1);    // Remove the last element
        map.remove(val);                             // Remove the value from the map

        return true;
    }

    /** Get a random element from the set. */
    public int getRandom() {
        int index = random.nextInt(arrayList.size()); // Generate random index
        return arrayList.get(index);                   // Return the element at that index
    }

    public static void main(String[] args) {
        // Test the RandomizedSet
        InsertDeleteGetRandomO1 randomizedSet = new InsertDeleteGetRandomO1();
        System.out.println(randomizedSet.insert(1)); // Returns true
        System.out.println(randomizedSet.remove(2)); // Returns false
        System.out.println(randomizedSet.insert(2)); // Returns true
        System.out.println(randomizedSet.getRandom()); // Returns either 1 or 2
        System.out.println(randomizedSet.remove(1)); // Returns true
        System.out.println(randomizedSet.insert(2)); // Returns false
        System.out.println(randomizedSet.getRandom()); // Returns 2
    }
}
