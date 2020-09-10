package com.interview.dynamic;

/**
 * http://www.geeksforgeeks.org/dynamic-programming-set-15-longest-bitonic-subsequence/
 * Reference: https://www.youtube.com/watch?v=TWHytKnOPaQ
 * Derived question: Find what the elements rather finding only max count, i think take one extra array and keep that index info 
 * for which max is updated.
 * Company: 
 * Category: 
 * Status: Done
 */
public class BitonicSequence {

    public int longestSequence(int arr[]){
        int lis[] = new int[arr.length];
        int lds[] = new int[arr.length];
        for(int i=0; i < arr.length; i++){
            lis[i] = 1;
            lds[i] = 1;
        }
        //Try from left
        for(int i=1 ; i < arr.length; i++){
            for(int j=0; j < i ; j++){
                if(arr[i] > arr[j]){
                    lis[i] = Math.max(lis[i], lis[j] + 1);
                }
            }
        }
        //Try from right
        for(int i = arr.length-2; i >=0 ; i--){
            for(int j = arr.length-1; j > i; j--){
                if(arr[i] > arr[j]){
                    lds[i] = Math.max(lds[i], lds[j] + 1);
                }
            }
        }
        int max = 0;
        //Make sum of two array and reduce by one from each sum, and also find max
        for(int i=0; i < arr.length; i++){
            if(max < lis[i] + lds[i]-1){
                max = lis[i] + lds[i] -1;
            }
        }
        return max;
    }
    
    public static void main(String args[]){
        BitonicSequence bs = new BitonicSequence();
        int[] arr = {1,4,3,7,2,1,8,11,13,0};
        int r = bs.longestSequence(arr);
        System.out.print(r);
    
    }
}
