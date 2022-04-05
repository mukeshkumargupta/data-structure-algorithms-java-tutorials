package com.interview.dynamic;
import java.util.*;

/*
 * https://www.youtube.com/watch?v=HddgLcq9Efs
 * Category: medium, VImp, Tricky
 */
public class MinimumOperationstoReduceXtoZero {
    public int minOperationsUtil(int[] nums, int x, int start, int end, int count) {
        /*
         * TC: n ^N
         * without Memoization
         * 
10 / 93 test cases passed.
         */
        if(x==0)    //Sum found
            return count;
        if(x<0 || start>end)   //Out of bounds
            return (int) 1e6;
        String key = "" + start + "*"+ end+ "*"+x; //Form Key, this is state like 2, 3, val 9
        //if(mem.count(key))  //Check if already calculated
           // return mem[key];
        
        int l = minOperationsUtil(nums,x-nums[start],start+1,end,count+1);  //Include left element
        int r = minOperationsUtil(nums,x-nums[end],start,end-1,count+1); //Include right element
        return Math.min(l,r);
        //return mem[key] = Math.min(l,r);
        
    }
    public int minOperations(int[] nums, int x) {
        
        int ans = minOperationsUtil(nums, x, 0, nums.length-1, 0);
        return ans==1e6?-1:ans;
    }
    Map<String, Integer> map;
    public int minOperationsUtil(int[] nums, int x, int start, int end, int count) {
        /*
         * TC: polinomila
         *  Memoization because there is some overlapping in state
         *  
64 / 93 test cases passed.
         */
        if(x==0)    //Sum found
            return count;
        if(x<0 || start>end)   //Out of bounds
            return (int) 1e6;
        String key = "" + start + "*"+ end+ "*"+x; //Form Key, this is state like 2, 3, val 9
        if(map.containsKey(key))  //Check if already calculated
            return map.get(key);
        
        int l = minOperationsUtil(nums,x-nums[start],start+1,end,count+1);  //Include left element
        int r = minOperationsUtil(nums,x-nums[end],start,end-1,count+1); //Include right element
        int val = Math.min(l,r);
        map.put(key, val);
        return val;
        
    }
    
    //Try to convert in tabulation this problem

    
    public int minOperationsMemoization(int[] nums, int x) {
        map = new HashMap<>();
        int ans = minOperationsUtil(nums, x, 0, nums.length-1, 0);
        return ans==1e6?-1:ans;
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
