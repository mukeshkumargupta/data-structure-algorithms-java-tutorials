package com.interview.hash;

import java.util.*;
/*
 * Reference: https://leetcode.com/problems/relative-sort-array/
 * Category: Easy, Tricky
 * Given two arrays arr1 and arr2, the elements of arr2 are distinct, and all elements in arr2 are also in arr1.
   Better solution is here
   https://www.youtube.com/watch?v=oHGXHrXxAgo
   
Sort the elements of arr1 such that the relative ordering of items in arr1 are the same as in arr2.  Elements that don't appear in arr2 should be placed at the end of arr1 in ascending order.

Related: https://leetcode.com/problems/bomb-enemy/ Medium
https://leetcode.com/problems/escape-a-large-maze/ Hard
https://leetcode.com/problems/longest-arithmetic-subsequence-of-given-difference/ Medium
 

Example 1:

Input: arr1 = [2,3,1,3,2,4,6,7,9,2,19], arr2 = [2,1,4,3,9,6]
Output: [2,2,2,1,4,3,3,9,6,7,19]
Hint use hash table and remove element if it is consumed
 

Constraints:

1 <= arr1.length, arr2.length <= 1000
0 <= arr1[i], arr2[i] <= 1000
All the elements of arr2 are distinct.
Each arr2[i] is in arr1.
 */
public class RelativeSortArray {
    public int[] relativeSortArray1(int[] arr1, int[] arr2) { //runtime 28.88 find better solution
        int len2 = arr2.length;
        int len1 = arr1.length;
        int start = 0;
        for (int i = 0; i < len2; i++) {

            for (int j = 0; j < len1; j++) {
                if (arr1[j] == arr2[i]) {
                    //System.out.println("2 arra" + arr2[i]);
                    //move it 
                    int temp = arr1[start];
                    arr1[start] = arr1[j];
                    //System.out.println(arr1[start] );
                    start++;
                    arr1[j] = temp;
                } 
                
                
            }
        }
        //after array 2 exhasted
        List<Integer> list = new ArrayList<>();
        for (int j = start ; j < len1; j++) {
            list.add(arr1[j]);
        }
        //sort list
        Collections.sort(list, (a, b) -> {
            return a -b;
        });
        for (int elm : list) {
            arr1[start] = elm;
            start++;
            
        }
        return arr1;
        
    }
    
    //Easy to undertsand
    public int[] relativeSortArray(int[] arr1, int[] arr2) {//Runtime: 6 ms, faster than 17.35% of Java online submissions for Relative Sort Array.
        // HashMap for storing frequency of elements.
        HashMap<Integer, Integer> map = new HashMap<>();
        // Storing frequency of elements of arr1.
        for(int i = 0;i < arr1.length;i++) {
            if(map.containsKey(arr1[i])) {
                map.put(arr1[i], map.get(arr1[i]) + 1);
            } else {
                map.put(arr1[i], 1);
            }
        }

        // This index variable will be used as the index of result array for storing the                result elements.
        int index = 0;
        // Storing the elements of arr1 according to arr2, with their number of occurrence              in arr1.
        for(int i = 0;i < arr2.length;i++) {
            for(int j = 0;j < map.get(arr2[i]);j++) {
                arr1[index++] = arr2[i];
            }
            map.remove(arr2[i]);
        }

        // Storing the remaining elements of map into a list.
        List<Integer> remainingElement = new ArrayList<Integer>();
        for(int i : map.keySet()) {
            for(int j = 0;j < map.get(i);j++) {
                remainingElement.add(i);
            }
        }

        // Sorting the remaining element list.
        Collections.sort(remainingElement);
        // Moving the remaining sorted elements from the list into the result array. 
        for(int i : remainingElement) {
            arr1[index++] = i;
        }

        // Returning the result array.
        // Using arr1 as the result array, because result array will be of same size and no            extra space will be used.
        return arr1;
    }
}
