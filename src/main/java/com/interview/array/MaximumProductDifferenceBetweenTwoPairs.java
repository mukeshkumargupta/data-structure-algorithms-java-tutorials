package com.interview.array;

/*
 * https://leetcode.com/problems/maximum-product-difference-between-two-pairs/
 * Category: Easy, tricky
 * Related, maximum product diff of two pair, min product diff of two pair(Try it in order n)
 */
public class MaximumProductDifferenceBetweenTwoPairs {
    public int maxProductDifference(int[] nums) { //runtime 91%
        int l = nums.length;
        int[] point = new int[4];
        point[0] = Integer.MIN_VALUE;
        point[1] = Integer.MIN_VALUE;
        point[2] = Integer.MAX_VALUE;
        point[3] = Integer.MAX_VALUE;
        for (int i = 0; i < l; i++) {
            if (nums[i] < point[3]) {//smallest
                point[2] = point[3];//backup
                point[3] =  nums[i];
            } else if (nums[i] < point[2])  {
                point[2] = nums[i];
            }
            
            if (nums[i] > point[0]) {//greatest
                point[1] = point[0]; //backup
                point[0] =  nums[i];
            } else if (nums[i] > point[1]) {
                point[1] = nums[i];
            }
            
        }
        
        /*for (int i =0; i < 4;i++) {
            System.out.println(point[i]);
        }*/
        return point[0]*point[1] - point[2]*point[3];
        
        
        
        
    }
}
