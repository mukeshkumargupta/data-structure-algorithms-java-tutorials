package com.interview.recursionBacktracking;

/*
 * https://www.youtube.com/watch?v=twuC1F6gLI8&list=PLgUwDviBIf0rGlzIn_7rsaR2FQ5e6ZOL9&index=4
 * Category: Fundamental
 */
public class L4ProblemsonFunctionalRecursionReverseArray {
    
    void reverArrayRecursion(int[] input, int start, int end) {
        
        if (start < end) {
            //Swap
            int temp = input[start];
            input[start] = input[end];
            input[end] = temp;
            reverArrayRecursion(input, start+1, end -1);
        }
        
    }
    
    void reverArrayRecursionOneArgument(int[] input, int start, int l) {
        
        if (start < l/2) {
            //Swap
            int temp = input[start];
            input[start] = input[l-start-1];
            input[l-start-1] = temp;
            reverArrayRecursionOneArgument(input, start+1, l);
        }
        
    }
    
    boolean checkPalindrome(char[] input, int start, int end) {
        if (start >= end) {
            return true;
        }
        
        //check
        if (input[start] == input[end]) {
            return checkPalindrome(input, start+1, end -1);
        }
        return false;
    }
    
    boolean checkPalindromeBetterCode(char[] input, int start, int end) {
        if (start >= end) {
            return true;
        }
        
        //check
        return input[start] == input[end] && checkPalindromeBetterCode(input, start+1, end -1);
    }
    
    boolean checkPalindromeOneMoreWayByAddingBaseCase(char[] input, int start, int end) {
        //Here two base case
        if (start >= end) {
            return true;
        }
        
        if (input[start] != input[end]) {
            return false;
        }
        
        //check
        return checkPalindromeOneMoreWayByAddingBaseCase(input, start+1, end -1);
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        L4ProblemsonFunctionalRecursionReverseArray instance = new L4ProblemsonFunctionalRecursionReverseArray();
        /*int[] input = {1,2,3,4,5};
        instance.reverArrayRecursion(input, 0, 4);
        for(int elm: input) {
            System.out.println(elm);
        }
        
        int[] input1 = {1,2,3,4,5};
        instance.reverArrayRecursionOneArgument(input1, 0, 5);
        for(int elm: input1) {
            System.out.println(elm);
        }*/
        String name = "MADAM";
        char[] palindromeArray = name.toCharArray();
        System.out.println(instance.checkPalindrome(palindromeArray, 0, name.length()-1));
        
        name = "MADSM";
        palindromeArray = name.toCharArray();
        System.out.println(instance.checkPalindromeBetterCode(palindromeArray, 0, name.length()-1));
        System.out.println(instance.checkPalindromeOneMoreWayByAddingBaseCase(palindromeArray, 0, name.length()-1));

        
        
        
    }
    
}
