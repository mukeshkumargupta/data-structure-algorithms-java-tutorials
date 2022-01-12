package com.interview.hash;
import java.util.*;
/*
 * https://leetcode.com/problems/next-greater-element-iii/ Solve next permutation question similar to this
 * Category: Medium
 * https://www.youtube.com/watch?v=eAfJJyaAmes&t=657s
 * Related: https://leetcode.com/problems/next-palindrome-using-same-digits/ Hard
 * Given a positive integer n, find the smallest integer which has exactly the same digits existing in the integer n and is greater in value than n. If no such positive integer exists, return -1.

Note that the returned integer should fit in 32-bit integer, if there is a valid answer but it does not fit in 32-bit integer, return -1.

 

Example 1:

Input: n = 12
Output: 21
Example 2:

Input: n = 21
Output: -1
 

Constraints:

1 <= n <= 231 - 1
 */
public class NextGreaterElementIII {
    public int nextGreaterElement(int n) {
        /*
         * Runtime: 0 ms, faster than 100.00% of Java online submissions for Next Greater Element III.
Memory Usage: 35.9 MB, less than 49.74% of Java online submissions for Next Greater Element III.
         */
        List<Integer> list = new ArrayList<>();
        while (n > 0) {
            list.add(n %10);
            n /= 10;
        }
        Collections.reverse(list);
        int[] input = new int[list.size()];
        int l = input.length;
        for (int i = 0; i < l; i++) {
            input[i] = list.get(i);
        }
        
        if (l == 1) {
            return -1;
        }
        int end = l-2;
        //find first dip
        while (end >= 0 && input[end] >= input[end+1]) {
            end--;
        }
        if (end == -1) {
            //System.out.println(-1);
            return -1;
        }
        //if dip found then find just greater element, start from last, because from last it will be increasing order always
        int dipIndex = end;
        end = l-1;
        while(input[end] <= input[dipIndex]) {
            end--;
        }
        //swap
        int temp = input[end];
        input[end] = input[dipIndex];
        input[dipIndex] = temp;
        
        //form number
        //from 0 to dipIndex
        long number = 0;
        for (int i = 0; i <= dipIndex; i++) {
            number = number*10 + input[i];
        }
        //then form from last to dipIndex+1 because we need smaller element
        for (int i = l-1; i > dipIndex; i--) {
            number = number*10 + input[i];
        }
        //if does not fit into int then return -
        if (number > Integer.MAX_VALUE) {
            return -1;
        }
        return (int)number;  
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        NextGreaterElementIII instance = new NextGreaterElementIII();
        instance.nextGreaterElement(12);
    }
    
}
