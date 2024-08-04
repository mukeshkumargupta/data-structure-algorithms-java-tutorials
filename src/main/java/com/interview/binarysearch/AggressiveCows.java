package com.interview.binarysearch;

import java.util.*;
/*
 * https://www.youtube.com/watch?v=wSOfYesTBRk
 * category: Medium, Must Do, 
 * https://www.codingninjas.com/codestudio/problems/aggressive-cows_1082559?leftPanelTab=0
 */
public class AggressiveCows {
    public static boolean isPossible(List<Integer> stalls, int n, int cows, int minDist) {
        int cntCows = 1; 
        int lastPlacedCow = stalls.get(0);
        for(int i = 1;i<n;i++) {
            if(stalls.get(i) - lastPlacedCow >= minDist) {
                cntCows++;
                lastPlacedCow = stalls.get(i); 
            }
        }
        if(cntCows >= cows) return true;
        return false; 
    }
    public static int aggressiveCows(ArrayList<Integer> stalls, int k) 
    {
        //    Write your code here.

            //sort(a,a+n); 
        /*
         * TC: NlogN
         * SC O(1)
         * 
         * other approach like start placing with distance 1, then try placing 2, and store result 
         * that will take order on N for trying distance and N for scan so TC is n^2 tha i is bruitforce solution
         */
        int result = Integer.MIN_VALUE;
        Collections.sort(stalls);
        int l = stalls.size();
            
            int low = 1, high = stalls.get(l-1) - stalls.get(0); 
            
            while(low <= high) {
                int mid = (low + high) >> 1; 
                
                if(isPossible(stalls,l,k,mid)) {
                    //store result
                    if (mid > result) {
                      result = mid;  
                    }
                    low = mid + 1;
                    
                }
                else {
                    high = mid - 1; 
                }
            }
            //return high;
        return result;
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
