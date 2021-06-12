package com.kids.basic;

public class Function {
    
    static int Add(int a,int b) {
        int result = a+b;
        return result;
    }
       
    static int Add1(int a,int b) {
        int result = a+b;
        return result; 
      } 
    static int Add2(int a,int b) {
        int result=a+b;
         return result;
    }
    static int Add3(int a,int b) {
          int result = a+b;
           return result;
      }
    static int add4(int a,int b) {
        int result = a+b;
         return result;
    }
    static int Add5(int a,int b) {
        int result = a+b;
         return result;
    }
    static int Sub(int a ,int b) {
        int result = a-b;
        return result;
    }
    static int sub1 (int a,int b) {
        int result = a-b;
         return result;
    }
    static int sub2 (int a,int b) {
        int result = a-b;
         return result;
    }
    static int sub3 (int a,int b) {
        int result = a-b;
         return result;
    }
    static int Sub4 (int a,int b) {
        int result = a+b;
         return result;
    }
    static int Sub5 (int a,int b) {
        int result = a-b;
         return result;
    }
    static int Mul(int a ,int b) {
        int result = a*b;
        return result;
    } 
     
    static int mul1 (int a,int b) {
        int result =a*b;
        return result;
    
    }
    static int Mul2(int a ,int b) {
        int result = a*b;
        return result;
    } 
    static int Mul3(int  a,int b) {
        int result = a*b;
         return result;
    }
    static int Mul4(int a,int b) {
        int result = a*b;
         return result;
    }
    static int Mul5(int a,int b) {
        int result = a*b;
         return result;
    }
    static int Div(int a ,int b) {
        int result = a/b;
         return result;
    }
    
    static int Div1(int a ,int b) {
        int result = a/b;
         return result;  
    }
    static int Div2(int a ,int b) {
        int result = a/b;
         return result;  
    }
    static int Div3(int a,int b) {
        int result = a/b;
         return result;
    }
    static int Div4 (int a, int b) {
        int result = a/b;
         return result;
    }
    static int Div5 (int a ,int b) {
        int result = a/b;
         return result;
    
    
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
    static void swap1(int[] input, int i, int j) {
        int temp = input[i];
        input[i] = input[j];
        input[j] = temp;
    }
    static void selectionSort1(int[] input) {
        int N = input.length;
        for (int i = 0; i < N - 1; i++) {
            for (int j = i + 1; j < N; j++) {
                if (input[i] > input[j]) {
                    swap1(input, i, j);
               }
            }
        }
    }
    static void swap2(int[] input, int i, int j) {
        int temp = input[i];
        input[i] = input[j];
        input[j] = temp;
    }
    static void selectionSort2(int[] input) {
        int N = input.length;
        for (int i = 0; i < N - 1; i++) {
            for (int j = i + 1; j < N; j++) {
                if (intput[i] > input [j]) {
                    swap2(input, i, j);
                }
            }
        }
    }

    public static void main(String[] args) {
        int reultadd = Add(12524,15853);
        System.out.println(reultadd);
        int reultSub = Sub(10,5);
        System.out.println(reultSub);
        int reultMul = Mul(10,5);
        System.out.println(reultMul);
        int reultDiv = Div(10,5);
        System.out.println(reultDiv);
        
        int result=9;
        for(int i=3;i<5;i++) {
                result=result*result+i*2;
        } 
        System.out.print(result)
 