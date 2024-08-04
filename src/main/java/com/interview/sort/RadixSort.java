package com.interview.sort;

/**
 * 
 * 
 * @author Mukesh Kumar Gupta
 * Rfrence:https://www.youtube.com/watch?v=m-2jU13HuBU
 *
 */

public class RadixSort {

    private void countSort(int arr[],int exp){
        
        int[] count = new int[10];
        int[] output = new int[arr.length];
        
        for(int i=0; i < arr.length; i++){
            count[(arr[i]/exp)%10]++;
        }
        
        for(int i=1; i < count.length; i++){
            count[i] += count[i-1];
        }
        
        for(int i=arr.length-1; i >= 0; i--){
            output[count[(arr[i]/exp)%10]-1] = arr[i];
            count[(arr[i]/exp)%10]--;
        }
        
        for(int i=0; i < arr.length; i++){
            arr[i] = output[i];
        }
    }
    
    private int max(int arr[]){
        int max = arr[0];
        for(int i=1; i < arr.length; i++){
            if(max < arr[i]){
                max = arr[i];
            }
        }
        return max;
    }
    
    public void radixSort(int arr[]){
        
        int max = max(arr);
        for(int exp = 1; exp <= max; exp *= 10){
            countSort(arr,exp);
        }
    }
    
    
    static void swap(int[] input, int i, int j) {
        int temp = input[i];
        input[i] = input[j];
        input[j] = temp;
    }
    
    
    static int findMin(int[] input, int start, int end) {
        int min = input[start];
        start++;
        for (; start < end ; start++) {
            if(input[start] < min) {
                min = input[start];
            }
        }
        return min;
    }
    
    
    
    static void insertionSort(int[] input) {
        int N = input.length;
        for (int i = 0; i < N -1; i++) {
            //Find  min
            int min = findMin(input, i, N);
            //Then move element
            input[i] = min;
            //move then start from next element
            if (i + 2 < N) {
                input[i+1] = input[i+2];
            }
        }
    }
    
    public static void main(String args[]){
        /*int arr[] = {101,10,11,66,94,26,125};
        RadixSort rs = new RadixSort();
        rs.radixSort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }*/
        
        /*int[] input = { 90, -1, 85, 35, 9, 5, -2 };
        selectionSort(input);
        for (int value : input) {
            System.out.println(value);
        }*/
        int[] insertionSortInput = { 90, -1, 85, 35, 9, 5, -2 };
        insertionSort(insertionSortInput);
        for (int value : insertionSortInput) {
            System.out.println(value);
        }

    }
}
