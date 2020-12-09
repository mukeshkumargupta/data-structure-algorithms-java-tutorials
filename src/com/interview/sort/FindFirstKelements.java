package com.interview.sort;
import java.util.*;
/*
 * 
 * Reference: https://www.geeksforgeeks.org/k-largestor-smallest-elements-in-an-array/
 */

public class FindFirstKelements {
    
    public static void FirstKelements(int arr[], int size, int k) {
        
        // Creating Min Heap for given
        // array with only k elements
        // Create min heap with priority queue
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        
        for (int i = 0; i < k; i++) {
            minHeap.add(arr[i]);
        }
        
        // Loop For each element in array
        // after the kth element
        for (int i = k; i < size; i++) {
            
            // If current element is smaller
            // than minimum ((top element of
            // the minHeap) element, do nothing
            // and continue to next element
            if (minHeap.peek() > arr[i])
                continue;
                
            // Otherwise Change minimum element
            // (top element of the minHeap) to
            // current element by polling out
            // the top element of the minHeap
            else {
                minHeap.poll();
                minHeap.add(arr[i]);
            }
        }
        
        // Now min heap contains k maximum
        // elements, Iterate and print
        Iterator iterator = minHeap.iterator();
        
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
        //Other way of iteration;
        /*for (Integer element : minHeap) {
            System.out.print(element + " ");
        }*/
        
        //Best way to print it will print in order
        /*while(!minHeap.isEmpty()){
            System.out.println(minHeap.poll());
        }*/
    }
    //Find kth min element then max heap make it
    public static void FirstKMinelements(int arr[], int size, int k) {
        
        // Create max heap with priority queue
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a,b) -> b-a);
        for (int i = 0; i < k; i++) {
            maxHeap.add(arr[i]);
        }
        
        // Loop For each element in array
        // after the kth element
        for (int i = k; i < size; i++) {
            
            // If current element is smaller
            // than minimum ((top element of
            // the minHeap) element, do nothing
            // and continue to next element
            if (maxHeap.peek() < arr[i])
                continue;
                
            // Otherwise Change minimum element
            // (top element of the minHeap) to
            // current element by polling out
            // the top element of the minHeap
            else {
                maxHeap.poll();
                maxHeap.add(arr[i]);
            }
        }
        
        //Other way of iteration;
        for (Integer element : maxHeap) {
            System.out.print(element + " ");
        }
    }
    
    // Driver code
    public static void main(String[] args) {
        int arr[] = { 11, 3, 2, 1, 15, 5, 4, 45, 88, 96, 50, 45 };
        
        int size = arr.length;
        
        // Size of Min Heap
        int k = 3;
        System.out.println("Print First K Max element\n");
        FirstKelements(arr, size, k);
        System.out.println("\nPrint First K Min element\n");
        FirstKMinelements(arr, size, k);
    }
    
}
