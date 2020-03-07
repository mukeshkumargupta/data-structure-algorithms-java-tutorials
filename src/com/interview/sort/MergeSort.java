package com.interview.sort;

/**
 * http://en.wikipedia.org/wiki/Merge_sort
 * Test cases
 * 1 element
 * 2 element
 * negative numbers
 * already sorted
 * reverse sorted
 * Reference: https://www.youtube.com/watch?v=jeaxzxErLKk&list=PLTZbNwgO5ebpTHdT7ylOTO2dX00QoDh4q&index=2
 */
public class MergeSort {

    public void sort(int input[]){
        sort(input, 0, input.length-1);
    }
    
    private void sort(int input[], int low, int high){
        if(low >= high){
            return;
        }
        
        int middle = (low + high)/2;
        sort(input, low, middle);
        sort(input, middle+1, high);
        sortedMerge(input,low, high);
   }
    //Practice with this code
    private void sortedMerge(int input[], int low, int high){
        int middle = (low+high)/2;
        int temp[] = new int[high-low+1];
        int i = low;
        int j = middle+1;
        int r = 0;
        while(i <= middle && j <= high){
            if(input[i] <= input[j]){
                temp[r++] = input[i++];
            }else{
                temp[r++] = input[j++];
            }
        }
        while(i <= middle){
            temp[r++] = input[i++];
        }
        
        while(j <= high){
            temp[r++] = input[j++];
        }
        i = low;
        //Imp make it in given array
        for(int k=0; k < temp.length;){
            input[i++] = temp[k++];
        }
    }
    
    //From saurabh school
    //Not working code
    private void sortedMerge1(int A[], int p, int q, int r){
        int l1 = q-p+1;
        int l2 = r-q; //(r-(q+1)) + 1
        int[] left = new int[l1];
        int[] right = new int[l2];
        for (int i = 0; i < l1; i++ ) {
        	left[i] = A[p+i];
        }
        
        for (int j = 0; j < l2; j++ ) {
        	right[j] = A[q+1+j];
        }
        int i =0, j=0;
        for (int k =p; k <= r; k++) {
        	if(left[i] <= right[j]) {
        		A[k] = left[i]; i++;
        	} else {
        		A[k] = right[j]; j++;
        	}
        }

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
        MergeSort ms = new MergeSort();
        ms.sort(input1);
        ms.sort(input2);
        ms.sort(input3);
        ms.sort(input4);
        
        ms.printArray(input1);
        ms.printArray(input2);
        ms.printArray(input3);
        ms.printArray(input4);
    }
}
