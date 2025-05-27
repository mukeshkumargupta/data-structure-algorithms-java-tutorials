package com.interview.sort.CustomSortingPatterns;

import java.util.*;

/**
 * http://www.geeksforgeeks.org/sort-elements-by-frequency/
 */
public class D_SortArrayByFrequence {
    public static class Element {
        int value, frequency;

        Element(int value, int frequency) {
            this.value = value;
            this.frequency = frequency;
        }
    }

    public static int[] sortByFrequency(int[] nums) {
        Map<Integer, Integer> freqMap = new HashMap<>();

        // Count frequency of each number
        for (int num : nums) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }

        // Use a max-heap sorted by frequency (descending), then by value (ascending)
        PriorityQueue<Element> maxHeap = new PriorityQueue<>((a, b) ->
                b.frequency == a.frequency ? Integer.compare(a.value, b.value) : Integer.compare(b.frequency, a.frequency)
        );

        // Insert elements into maxHeap
        for (Map.Entry<Integer, Integer> entry : freqMap.entrySet()) {
            maxHeap.offer(new Element(entry.getKey(), entry.getValue()));
        }

        // Build the sorted result
        int[] result = new int[nums.length];
        int index = 0;

        while (!maxHeap.isEmpty()) {
            Element elem = maxHeap.poll();
            for (int i = 0; i < elem.frequency; i++) {
                result[index++] = elem.value;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums = {4, 5, 6, 5, 4, 3};
        System.out.println(Arrays.toString(sortByFrequency(nums)));
    }
    
}
