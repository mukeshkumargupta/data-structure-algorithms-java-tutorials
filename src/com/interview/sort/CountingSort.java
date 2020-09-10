package com.interview.sort;

/*
 * Count sort
 * Reference: https://www.youtube.com/watch?v=w9njRpeuVew
 * Must Do
 * Status: Done
 */

public class CountingSort {

    private static int TOTAL = 10;
    //Preferred method
    public void sort(int arr[]) {

        int count[] = new int[TOTAL];

        for (int i = 0; i < arr.length; i++) {
            count[arr[i]]++;
        }
        int c = 0;
        for (int i = 0; i < TOTAL; i++) {
            while (count[i] > 0) {
                arr[c++] = i;
                count[i]--;
            }
        }
    }

    public void sort1(int arr[]) {

        int count[] = new int[TOTAL];
        int output[] = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            count[arr[i]]++;
        }
        
        for(int i=1; i < TOTAL; i++){
            count[i] += count[i-1];
        }
        
        for(int i=0; i <arr.length; i++){
            output[count[arr[i]]-1] = arr[i];
            count[arr[i]]--;
        }
        
        for(int i=0; i < arr.length; i++){
            arr[i] = output[i];
        }
    }

    public static void main(String args[]) {
    	//This is only number varies between 0 and 10 where 0 is included
        int arr[] = { 6, 1, 6, 7, 3, 1, 3, 3, 2, 1, 5, 1, 1, 0, 0, 1 };
        CountingSort cs = new CountingSort();
        cs.sort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }
}
