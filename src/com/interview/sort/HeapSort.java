package com.interview.sort;

import com.companies.facebook.FindBadVersion;

/**
 * Date 04/03/2017
 * @author Mukesh Kumar Gupta
 * 
 * Heap Sort
 * Given an array sort it using heap sort
 * 
 * Solution :
 * First convert the original array to create the heap out of the array
 * Then move the max element to last position and do heapify to recreate the heap
 * with rest of the array element. Repeat this process
 * 
 * Time complexity
 * O(nlogn)
 * 
 * Test cases
 * Null array
 * 1 element array
 * 2 element array
 * sorted array
 * reverse sorted array
 * 
 * Reference: https://www.geeksforgeeks.org/heap-sort/
 * Saurabh School: https://www.youtube.com/watch?v=5GrJwDggoas
 * Leetcode: https://leetcode.com/problems/sort-an-array
 * Status: Done code taken from geeksfromgeeks
 */
public class HeapSort {
    
    public void sort(int arr[])
    {
        int n = arr.length; 
        // Build heap (rearrange array)
        for (int i = n / 2 - 1; i >= 0; i--)//why n/2-1 because after it there is only leaf node
            heapify(arr, n, i);
 
        // One by one extract an element from heap
        for (int i = n - 1; i > 0; i--) { 
            // Move current root to end
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
 
            // call max heapify on the reduced heap
            heapify(arr, i, 0); //I think here it should be i-1, It working after i-1
        }
    }
 
    // To heapify a subtree rooted with node i which is
    // an index in arr[]. n is size of heap
    void heapify(int arr[], int n, int i)
    {
        int largest = i; // Initialize largest as root
        int l = 2 * i + 1; // left = 2*i + 1
        int r = 2 * i + 2; // right = 2*i + 2
 
        // If left child is larger than root
        if (l < n && arr[l] > arr[largest])
            largest = l;
 
        // If right child is larger than largest so far
        if (r < n && arr[r] > arr[largest])
            largest = r;
 
        // If largest is not root
        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;
 
            // Recursively heapify the affected sub-tree
            heapify(arr, n -1, largest);
        }
    }
 
    /* A utility function to print array of size n */
    static void printArray(int arr[])
    {
        int n = arr.length;
        for (int i = 0; i < n; ++i)
            System.out.print(arr[i] + " ");
        System.out.println();
    }
    
    public double calculateDistance(double x1, double y1, double x2, double y2, double p) {
        double distance = 0;
        double xDiff = x1 - x2;
        if (xDiff < 0 ) {
            xDiff = xDiff*-1;
            
        }
        double yDiff = y1 - y2;
        if (yDiff < 0 ) {
            yDiff = yDiff*-1;
            
        }
        double ecDist = Math.pow(xDiff, p) + Math.pow(yDiff, p);
        distance = Math.pow(ecDist, 1/p);
        return distance;
    }
 
    // Driver code
    public static void main(String args[])
    {
        HeapSort tc = new HeapSort();
        System.out.println(tc.calculateDistance(1, 1, 2, 2, 1));
        
        /*//int arr[] = { 12, 11, 13, 5, 6, 7 };
        int arr[] = {-1,5,8,2,-6,-8,11,5};
 
        HeapSort ob = new HeapSort();
        ob.sort(arr);
 
        System.out.println("Sorted array is");
        printArray(arr);*/
    }
}
