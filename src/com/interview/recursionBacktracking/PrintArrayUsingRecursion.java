package com.interview.recursionBacktracking;

/*
 * https://www.youtube.com/watch?v=gMRB0TnVhAQ&list=PLIA-9QRQ0RqHYFNJc6zVT1_sJz0qCU9b0&index=11
 * Category: Easy,
 */
public class PrintArrayUsingRecursion {
    
    private static void print(int[] input,int start, int end) {
        if (start == end) {
            return;
        }
        System.out.println(input[start]);
        print(input, start+1, end);
    }
    
    private static void printReverse(int[] input,int end) {
        if (end == -1) {
            return;
        }
        System.out.println(input[end]);
        printReverse(input, end-1);
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int[] input = {1,5,8,9};
        printReverse(input, input.length-1);
        
    }
    
}
