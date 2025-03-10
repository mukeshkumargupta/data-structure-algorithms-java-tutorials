package com.interview.array;

/*
 * https://www.youtube.com/watch?v=OTkoJOP6m6w&t=381s
 * https://www.youtube.com/watch?v=5nMGY4VUoRY
 * Category: Easy, Must Do
 * https://leetcode.com/problems/set-mismatch/
 * 
 * http://www.geeksforgeeks.org/find-a-repeating-and-a-missing-number/
 * Related:
 * https://leetcode.com/problems/split-array-with-equal-sum/ Hard VVImp Locked, Must Do
 * https://leetcode.com/problems/min-cost-climbing-stairs/ Easy VVImp
 * https://leetcode.com/problems/correct-a-binary-tree/ Medium, Locked VImp
 * 
 */
public class RepeatingAndMissingNumber {

    public int[] findErrorNums(int[] nums) {
        /*
         * TC: O(N) + O(N)
         * SC: O(1)
         */
        int[] result = new int[2];
        int l = nums.length;
        for (int i = 0; i < l; i++) {
            int val = Math.abs(nums[i]);
            if (nums[val-1] > 0) {
                nums[val-1] = nums[val-1]*-1;
            } else {
                result[0] = val;
            }
            
        }
        
        for (int i = 0; i < l; i++) {//Tricky
            if (nums[i] < 0) {
                nums[i] = nums[i]*-1;
            } else {
                result[1] = i + 1;
            }
            
        }
        return result;
    }

    private static class Optilized {
        public int[] findErrorNums(int[] nums) {
        /*
         * TC: O(N)
         * SC: O(1)
         * Runtime: 1 ms, faster than 100.00% of Java online submissions for Set Mismatch.
Memory Usage: 43.4 MB, less than 94.02% of Java online submissions for Set Mismatch.
         */
            long len = nums.length;
            long  S = (len * (len+1) ) /2;
            long  P = (len * (len +1) *(2*len +1) )/6;
            long missingNumber=0, repeating=0;

            for(int i=0;i<len; i++){
                S -= (long )nums[i];
                P -= (long)nums[i]*( long)nums[i];
            }

            missingNumber = (S + P/S)/2;

            repeating = missingNumber - S;

            int[] result = new int[2];
            result[0] = (int)repeating;
            result[1] = (int)missingNumber;

            return result;
        }

        public static void main(String args[]){
            Optilized rmn = new Optilized();
            int input[] = {3,1,2,4,6,8,2,7};
            System.out.println(rmn.findErrorNums(input));
        }
    }
    
    

}
