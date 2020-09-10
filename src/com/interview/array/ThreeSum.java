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
                            output.add(temp);
                        }
                    }
                }
            }
            return output;

        }
        public List<List<Integer>> threeSum_M1(int[] nums) {
            Arrays.sort(nums);
            int len = nums.length;
            List<List<Integer>> result = new ArrayList<>();
            for (int i = 0; i < len ; i++) {
                int j = i+1;
                int k = len-1;

                while (j < k) {
                    if (nums[j] == nums[j+1]) {
                        j++;
                        continue;
                    }
                    if (nums[k] == nums[k-1]) {
                        k--;
                        continue;
                    }
                    if (nums[i] + nums[j] + nums[k] == 0) {
                        List<Integer> temp = new ArrayList<Integer>();
                        temp.add(nums[i]);
                        temp.add(nums[j]);
                        temp.add(nums[k]); 
                        result.add(temp);
                        j++;
                        k--;
                    } else if (nums[i] > nums[j] + nums[k]) {
                        j++; 
                    } else {
                        k--; 
                    }
                }
                
            }
            return result;
        }
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        ThreeSum ts = new ThreeSum();
        int[] input = {-1, 0, 1, 2, -1, -4};
        List<List<Integer>> result = ts.threeSum_M1(input);
        for (List<Integer> array: result) {
            for (int element: array) {
                System.out.println(element) ;
            }
            System.out.println("\n");
        }
        
        
    }
}
