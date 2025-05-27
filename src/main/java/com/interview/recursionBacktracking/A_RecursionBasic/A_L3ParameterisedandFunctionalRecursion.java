package com.interview.recursionBacktracking.A_RecursionBasic;

/*
 * https://www.youtube.com/watch?v=69ZCDFy-OUo&list=PLgUwDviBIf0rGlzIn_7rsaR2FQ5e6ZOL9&index=3
 * Category: Fundamental
 */
public class A_L3ParameterisedandFunctionalRecursion {
    public void ParameterizedSum(int sum, int i) {
        if (i < 1) {
            System.out.println(sum);
            return;
        }
        ParameterizedSum(sum + i, i-1);
    }
    
    public int FunctionalRecursionSum(int i) {
        if (i == 1) {
            return 1;
        }
        return i + FunctionalRecursionSum(i-1);
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        A_L3ParameterisedandFunctionalRecursion instance = new A_L3ParameterisedandFunctionalRecursion();
        instance.ParameterizedSum(0, 6);
        System.out.println(instance.FunctionalRecursionSum(6));
        
    }
    
}
