package com.interview.recursionBacktracking.A_RecursionBasic;

public class A_L1IntroductiontoRecursion {
    public void f() {//Example of 
        
        System.out.println("1");
        f();
    }
    int count = 0;
    public void fun() {
        if (count == 6) {//Base case
            return;
        }
        
        System.out.println(count);
        count++;
        fun();//total size function awaited in stack to be completed to stack space is O(6)
        //Rather that writing each time function, you can visualize by drying recursion tree
        
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        A_L1IntroductiontoRecursion instance = new A_L1IntroductiontoRecursion();
        instance.f();
        instance.fun();
        
    }
    
}
