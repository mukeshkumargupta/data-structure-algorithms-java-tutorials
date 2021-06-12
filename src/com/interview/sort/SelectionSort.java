package com.interview.sort;

public class SelectionSort {
    
    static void swap(int[] input, int i, int j) {
        int temp = input[i];
        input[i] = input[j];
        input[j] = temp;
    }
    
    static void selectionSort(int[] input) {
        int N = input.length;
        for (int i = 0; i < N - 1; i++) {
            for (int j = i + 1; j < N; j++) {
                if (input[i] > input[j]) {
                    swap(input, i, j);
                }
            }
        }
    }
    
    static void main(String[] args) {
        int[] input = { 85, 35, 9, 5 };
        selectionSort(input);
        for (int value : input) {
            System.out.println(value);
        }
        
    }
    
}
