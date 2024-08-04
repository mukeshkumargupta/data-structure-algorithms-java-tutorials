package com.interview.recursionBacktracking;

/*
 * Category: Fundamental
 * https://www.youtube.com/watch?v=un6PLygfXrA&list=PLgUwDviBIf0rGlzIn_7rsaR2FQ5e6ZOL9&index=2
 * Question: print 1 to n, n to 1 and 1 to n and n to n by backtracking
 */
public class L2ProblemsonRecursion {
    void print1ToN(int i , int N) {
        if (i > N) {
            return;
        }
        System.out.println(i);
        print1ToN(i+1, N);
    }
    
    void printNTo1(int i , int N) {
        if (i < 1) {
            return;
        }
        System.out.println(i);
        printNTo1(i-1, N);
    }
    
    void printNTo1Backtracking(int i , int N) {
        if (i > N) {
            return;
        }
        
        printNTo1Backtracking(i+1, N);
        System.out.println(i);
    }
    
    void print1ToNBacktracking(int i , int N) {
        if (i < 1) {
            return;
        }
        print1ToNBacktracking(i-1, N);
        System.out.println(i);
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        L2ProblemsonRecursion instance = new L2ProblemsonRecursion();
        instance.print1ToN(1, 6);
        instance.printNTo1(6, 6);
        instance.printNTo1Backtracking(1, 6);
        instance.print1ToNBacktracking(6, 6);
        
        
    }
    
}
