package com.interview.array;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Date 10/19/2017
 * @author Mukesh Kumar Gupta
 * https://www.youtube.com/watch?v=AjE4x3Yh9xc&t=10s
 * Category: Easy, Tricky, Top150
 * Note: here value list is asked but it can ask index as well
 *
 * Given a sorted integer array without duplicates, return the summary of its ranges.
 * For example, given [0,1,2,4,5,7], return ["0->2","4->5","7"].
 *
 * Solution -
 * Just check if num[i] + 1 != num[i + 1]. If its not equal means you need to add previous range to result
 * and start a new range.
 * 
 * Time complexity O(n)
 *
 * https://leetcode.com/problems/summary-ranges/
 */
public class SummaryRanges {
    public List<String> summaryRanges(int[] nums) {
        List<String> result = new ArrayList();
        int len = nums.length;
        if(len == 0) return result;
        int start = nums[0];
        for(int i = 0; i < len; ++i){
            if(i == len-1 || nums[i]+1 != nums[i+1]){
                if(nums[i] != start) result.add(start + "->" + nums[i]);
                else result.add(start + "");

                if(i != len-1) start = nums[i+1];
            }
        }
        return result;
    }
    /*
    Return Index Ranges Instead of Value Ranges
     */
    private static class DerivedQuestion {
        public List<String> summaryRanges(int[] nums) {
            List<String> result = new ArrayList<>();
            int len = nums.length;
            if (len == 0) return result;

            int startIndex = 0;

            for (int i = 0; i < len; ++i) {
                // Check if the current number is the last in a range
                if (i == len - 1 || nums[i] + 1 != nums[i + 1]) {
                    if (startIndex != i) {
                        result.add(startIndex + "->" + i);
                    } else {
                        result.add(startIndex + "");
                    }

                    // Prepare for next range
                    if (i != len - 1) startIndex = i + 1;
                }
            }

            return result;
        }
    }
}
