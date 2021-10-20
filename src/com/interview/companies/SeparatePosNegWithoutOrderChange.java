package com.interview.companies;

/*
 * Other variant, make negative first then positive
 * https://www.geeksforgeeks.org/rearrange-positive-and-negative-numbers/
 * Variant : https://leetcode.com/problems/sort-array-by-parity/  (seperate by odd and even
 *All prime number first
 *all odd index first then even index number no order change
 *
 *appliedmaterial
 */
public class SeparatePosNegWithoutOrderChange {
    static int findPositiveIndex(int[] input, int start) {
        for (; start < input.length; start++) {
            if (input[start] > 0) {
                return start;
            }
        }
        return -1;
        
    }
    static void Move(int[] input, int start, int FoundIndex) {
        int temp = input[start];
        input[start] = input[FoundIndex];
        //Now do movement
        for (int i = FoundIndex ; i > start; i--) {
            if (i-1 > start) {
                input[i] = input[i-1];
            }
        }
        input[start+1] = temp;
        
    }
    public static void orderChange(int[] input) {
        int l1 = input.length;
        for (int i = 0; i < l1; i++) {
          //find next non negative and swap
            if (input[i] < 0) {
                int positiveIndex = findPositiveIndex(input, i+1);
                if (positiveIndex < 0) {
                    return ;
                } else {//Move
                    Move(input, i, positiveIndex);
                }
            }
            

            
        }
    }
    
    public static void main(String[] args) {
        int[] input =  {5, -4, 3, -2, 6, -11, 12, -8, 9};
        orderChange(input);
        System.out.println(input);
    }
    
}
