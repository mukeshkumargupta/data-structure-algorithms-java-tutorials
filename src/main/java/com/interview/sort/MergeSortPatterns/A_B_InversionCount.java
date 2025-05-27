package com.interview.sort.MergeSortPatterns;

import java.util.*;

/*
 * https://www.youtube.com/watch?v=kQ1mJlwW-c0&t=0s
 * Category: Medium, Must Know
 * https://www.geeksforgeeks.org/counting-inversions/
 */

public class A_B_InversionCount {
    int merge (int arr[], int start, int mid, int end) {

        int count = 0;
        List<Integer> list = new ArrayList<>();
        
        int i = start;
        int j = mid +1;
        
        
        while (i <= mid && j <= end) {
            if (arr[i] <= arr[j]) {
                list.add(arr[i]);
                i++;
            } else {
                count += mid -i + 1;//here +1 is for self
                list.add(arr[j]);
                j++;
            }
        }
        while (i <= mid) {
            list.add(arr[i]);
            i++;
        }
        while (j <= end) {
            list.add(arr[j]);
            j++; 
        }
        return count;
    }

    int mergeSort(int arr[], int start, int end) {
        if (start >= end) {
            return 0;
        }
        int count = 0;
        int mid = start + (end - start)/2;
        count += mergeSort(arr, start, mid);
        count += mergeSort(arr, mid+1, end);
        count += merge(arr, start, mid, end);
        return count;
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int[] input = { 1, 20, 6, 4, 5 };
        A_B_InversionCount ic = new A_B_InversionCount();
        System.out.println(ic.mergeSort(input, 0, input.length-1));
    }
    
}
