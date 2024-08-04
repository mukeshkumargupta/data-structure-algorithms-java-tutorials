package com.interview.recursionBacktracking;

/*
 * https://www.youtube.com/watch?v=kvRjNm4rVBE&list=PLgUwDviBIf0rGlzIn_7rsaR2FQ5e6ZOL9&index=5
 * Category: Easy, Fundamental
 * 
 */
public class L5MultipleRecursionCallsFiboNacci {
    
    int fibonacci(int n) {
        /*
         * TC: 2^n
         */
        if (n <= 1) {
            return n;
        }
        return fibonacci(n-1) + fibonacci(n-2);//here two multiple recursion call, here every recursion is calling two recursion so TC 2^n
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
        L5MultipleRecursionCallsFiboNacci instance = new L5MultipleRecursionCallsFiboNacci();
        System.out.println(instance.fibonacci(4));
        
    }
    
}
