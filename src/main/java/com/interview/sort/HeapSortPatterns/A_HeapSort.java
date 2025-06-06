package com.interview.sort.HeapSortPatterns;

/**
 * Date 04/03/2017
 * @author Mukesh Kumar Gupta
 * 
 * Heap Sort
 * Given an numsay sort it using heap sort
 * 
 * Solution :
 * First convert the original numsay to create the heap out of the numsay
 * Then move the max element to last position and do heapify to recreate the heap
 * with rest of the numsay element. Repeat this process
 * 
 * Time complexity
 * O(nlogn)
 * 
 * Test cases
 * Null numsay
 * 1 element numsay
 * 2 element numsay
 * sorted numsay
 * reverse sorted numsay
 * 
 * Reference: https://www.geeksforgeeks.org/heap-sort/
 * Saurabh School: https://www.youtube.com/watch?v=5GrJwDggoas
 * Leetcode: https://leetcode.com/problems/sort-an-array
 * Status: Done code taken from geeksfromgeeks
 * Category: Medium, Must Do, Tricky, Fundamental
 * Related: https://leetcode.com/problems/kth-largest-element-in-an-array/discuss/254069/quick-sort-partition Medium
 * https://leetcode.com/problems/k-closest-points-to-origin/ Medium
 * Related: https://www.youtube.com/watch?v=VORIA407dB4 K Closest Points to Origin | Leetcode take mi  heak of size k and distance is crteria for sorting array sud have x, y and distance
 * https://leetcode.com/problems/top-k-frequent-elements/ Medium
 */
public class A_HeapSort {
    
    public int[] sortArray(int[] nums) {//runtime 53%
        int n = nums.length;
 
        // Build heap (rearrange array)
        for (int i = n / 2 - 1; i >= 0; i--)//why n/2-1 because after it there is only leaf TreeNode
            heapify(nums, n, i);
 
        // One by one extract an element from heap
        for (int i = n - 1; i > 0; i--) {
            // Move current root to end
            int temp = nums[0];
            nums[0] = nums[i];
            nums[i] = temp;
 
            // call max heapify on the reduced heap
            heapify(nums, i, 0);//I think here it should be i-1, It working after i-1, No i i is correct because that is lenngth so calling for next length that is now n-1
        }
        return nums;
        
    }
    
    // To heapify a subtree rooted with TreeNode i which is
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
}
