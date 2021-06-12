package com.interview.recursion;

public class PermutationSequence {
    boolean isKthPermutationFound = false;
    int kthPermuationNumber = 0;
    int count = 0;
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
        
    }
    private void permutateUtil(int[] nums, int l , int r, int k) {
        if (l == r-1) {
           count++;
            if (count == k) {
                for (int num : nums) {
                    kthPermuationNumber = kthPermuationNumber*10 + num;
                }
                isKthPermutationFound = true;
            }
            return;
        }
        if (!isKthPermutationFound) {
            for (int i = l; i < r; i++) {
                /*if (isKthPermutationFound) {
                    break;
                }*/
                swap(nums, l, i);
                permutateUtil(nums, l+1, r, k);
                swap(nums, l, i);
            }
            
        }
        
    }
    public String getPermutation(int n, int k) {
        int[] nums = new int[n];
        for (int i =1; i <= n; i++ ) {
            nums[i-1] = i;
        }
        permutateUtil(nums, 0, n, k);

        String result = "" + kthPermuationNumber;
        return result;
        
    }
    public static void main(String[] args) {
        PermutationSequence ps = new PermutationSequence();
        ps.getPermutation(3, 5);
        
    }
    
}
