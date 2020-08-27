package com.interview.array;
import java.util.*;

public class ThreeSum {
        public List<List<Integer>> threeSum(int[] nums) {
            List<List<Integer>> output = new ArrayList<>();
            int len = nums.length;
            Arrays.sort(nums);
            
            for (int i= 0; i < len ; i++) {
                if (nums[i] == nums[i+1]) {
                    i++;
                    continue;
                }
                
                for (int j=0 , k = len-1; j < len && k >= 0; j++, k--) {
                    if (i != j && i != k) {
                        while (nums[j] == nums[j+1] ) {
                            j++;
                        }
                        while (nums[k] == nums[k-1] ) {
                            k--;
                        }
                        if (nums[i] + nums[j] + nums[k] ==  0) {
                            //Collect valid case
                            List<Integer> temp = new ArrayList<>();
                            temp.add(nums[i]);
                            temp.add(nums[j]);
                            temp.add(nums[k]);
                        }
                    }
                }
            }
            return output;

        }
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
}
