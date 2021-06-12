package com.companies.appliedmaterial;

public class PrintPyramid {
    
    public static int printUpperPyramidSum(int[] input, int C) {
        int sum = 0;
        int l1 = input.length;
        int R = l1/C;
        int start = 0;
        int end = l1;
        for (int i = 0; i < R; i++) {
            start = i;
            end = C - i;
            for (int j = start; j< end; j++) {
                //Get actual index 
                int index = i * C + j;
                sum += input[index];
            }
            
        }
        return sum;
        
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int[] input = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
        int sum = printUpperPyramidSum(input, 5);
    }
    
}
