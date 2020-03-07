package com.interview.sort;

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
 */
public class HeapSort {

    public void sort(int arr[]){
        for(int i=1; i < arr.length; i++){
            heapAdd(arr, i);
        }
        
        for(int i = arr.length-1; i > 0 ; i--){
            swap(arr, 0, i);
            heapify(arr, i-1);
        }
    }
    
    private void heapify(int arr[], int end){
        int i = 0;
        int leftIndex;
        int rightIndex;
        while(i <= end){
            leftIndex = 2*i + 1;
            //Check condition
            if(leftIndex > end){
                break;
            }
            rightIndex = 2*i + 2;
            //Check condition
            if(rightIndex > end){
                rightIndex = leftIndex;
            }
            if(arr[i]  >= Math.max(arr[leftIndex], arr[rightIndex])){
                break;
            }
            //Why we are putting two times swap why can we not swap with max element
            //See saurabh video
            if(arr[leftIndex] >= arr[rightIndex]){
                swap(arr, i, leftIndex);
                i = leftIndex;
            }else{
                swap(arr, i, rightIndex);
                i = rightIndex;
            }
        }
    }
    
    //From saurabh school video
    private void heapify1(int arr[], int end){

    }
    
    private void swap(int arr[], int x, int y){
        int temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;
    }
    
    private void heapAdd(int arr[], int end){
        int i = end;
        while(i > 0){
            if(arr[i] > arr[(i-1)/2]){
                swap(arr, i, (i-1)/2);
                i = (i - 1)/2;
            }else{
                break;
            }
        }
    }
    
    public static void main(String args[]){
        HeapSort hs = new HeapSort();
        int arr[] = {-1,5,8,2,-6,-8,11,5};
        hs.sort(arr);
        for(int a : arr){
            System.out.println(a);
        }
    }
}
