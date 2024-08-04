package com.interview.recursionBacktracking;

public class RecursionPrint {
    
    private static void recursionPrint1toN(int start , int end) {
        if (start == end+1) {
            return;
        }
        System.out.println(start);
        int a = start+1;
        //System.out.println("I am calling to: " + a);
        recursionPrint1toN(start+1, end);
        //System.out.println("I have Called by: " + a);
        
    }
    
    private static void recursionPrintReverse(int start , int end) {
        if (start == end+1) {
            return;
        }
        recursionPrintReverse(start+1, end);
        System.out.println(start);//Stack unwinding
        
    }
    
    /*private static int recursionPrintNto1(int start , int end) {
        if (start == end+1) {
            return start;
        }
        int data = recursionPrintNto1(start+1, end);
        System.out.println(data);
        return data;
    }*/
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        recursionPrint1toN(1, 5);
        recursionPrintReverse(1, 5);
    }
    
}
