package com.interview.recursionBacktracking;

/*
 * https://www.youtube.com/watch?v=p8SDPaX1wgw&list=PLIA-9QRQ0RqHYFNJc6zVT1_sJz0qCU9b0&index=5
 * Category : medium, Must Do
 */
public class Combination {
    void print(char[] input, boolean[] bitSets) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bitSets.length; i++) {
            if (bitSets[i] == true) {
                sb.append(input[i]);
            }
        }
        System.out.println(sb.toString());
        
    }
    private void combination(char[] input, int start, int end, boolean[] bitSets) {
        if (start == end) {
            bitSets[start] = false;
            print(input, bitSets);
            bitSets[start] = true;
            print(input, bitSets);
            return;
            
        }
        bitSets[start] = false;
        combination(input, start+1, end, bitSets);
        bitSets[start] = true;
        combination(input, start+1, end, bitSets);
    }
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Combination pac = new Combination();
        String input = "ABC";
        boolean[] bitSets = new boolean[input.length()];
        pac.combination(input.toCharArray(), 0, input.length() -1 , bitSets);
        
    }
    
}
