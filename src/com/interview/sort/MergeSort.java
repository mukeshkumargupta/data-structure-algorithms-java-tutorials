package com.interview.sort;

/**
 * http://en.wikipedia.org/wiki/Merge_sort
 * Test cases
 * 1 element
 * 2 element
 * negative numbers
 * already sorted
 * reverse sorted
 * Reference: https://www.youtube.com/watch?v=TzeBrDU-JaY
 * Status: done
 */
public class MergeSort {
 // Function to Merge Arrays L and R into A. 
 // lefCount = number of elements in L
 // rightCount = number of elements in R. 
 void Merge(int[]A,int[] L,int leftCount,int[]R,int rightCount) {
     int i,j,k;

     // i - to mark the index of left aubarray (L)
     // j - to mark the index of right sub-raay (R)
     // k - to mark the index of merged subarray (A)
     i = 0; j = 0; k =0;

     while(i<leftCount && j< rightCount) {
         if(L[i]  < R[j]) A[k++] = L[i++];
         else A[k++] = R[j++];
     }
     while(i < leftCount) A[k++] = L[i++];
     while(j < rightCount) A[k++] = R[j++];
 }

 // Recursive function to sort an array of integers. 
 private void sort(int[] A,int n) {
     int mid,i;
     if(n < 2) return; // base condition. If the array has less than two element, do nothing. 

     mid = n/2;  // find the mid index. 

     // create left and right subarrays
     // mid elements (from index 0 till mid-1) should be part of left sub-array 
     // and (n-mid) elements (from mid to n-1) will be part of right sub-array 
     int[] L = new int[mid];
     int[] R = new int[n- mid];
     
     for(i = 0;i<mid;i++) L[i] = A[i]; // creating left subarray
     for(i = mid;i<n;i++) R[i-mid] = A[i]; // creating right subarray

     sort(L,mid);  // sorting the left subarray
     sort(R,n-mid);  // sorting the right subarray
     Merge(A,L,mid,R,n-mid);  // Merging L and R into A as sorted list.
 }
    
    public void printArray(int input[]){
        for(int i : input){
            System.out.print(i + " ");
        }
        System.out.println();
    }
    
    public static void main(String args[]){
        int input1[] = {1};
        int input2[] = {4,2};
        int input3[] = {6,2,9};
        int input4[] = {6,-1,10,4,11,14,19,12,18};
        int input5[] = {6,-1,10,10,4,11,14,10,19,12,18};//duplicate
        MergeSort ms = new MergeSort();
        ms.sort(input1, input1.length);
        ms.sort(input2,input2.length);
        ms.sort(input3,input3.length);
        ms.sort(input4, input4.length);
        ms.sort(input5, input4.length);
        
        
        
        ms.printArray(input1);
        ms.printArray(input2);
        ms.printArray(input3);
        ms.printArray(input4);
        ms.printArray(input5);
    }
}
