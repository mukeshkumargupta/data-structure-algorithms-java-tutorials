package com.interview.sort.HeapSortPatterns.B_KthPattern;

/*
https://leetcode.com/problems/top-k-frequent-elements/submissions/1523036428/
Category: Medium
Related:
https://leetcode.com/problems/word-frequency/ Medium
https://leetcode.com/problems/kth-largest-element-in-an-array/ Medium
https://leetcode.com/problems/sort-characters-by-frequency/ Medium
https://leetcode.com/problems/split-array-into-consecutive-subsequences/ Medium
https://leetcode.com/problems/top-k-frequent-words/ Medium
https://leetcode.com/problems/k-closest-points-to-origin/ Medium
https://leetcode.com/problems/sort-features-by-popularity/ Medium Locked
https://leetcode.com/problems/sender-with-largest-word-count/ Medium
https://leetcode.com/problems/most-frequent-even-element/ Easy
https://leetcode.com/problems/linked-list-frequency/ Medium Locked




    Explanation:
    Custom Element Class:

    The Element class is used to store both the value (the number) and its frequency. This provides a structured and clean way to represent the elements in the priority queue.
    Frequency Map:

    A Map<Integer, Integer> is used to store the frequency of each element in the input array.
    Priority Queue:

    A Min-Heap (PriorityQueue) is used to store the elements based on their frequency in ascending order.
    The PriorityQueue is sorted by frequency, so the element with the smallest frequency will be at the root of the heap.
    Heap Size Control:

    We maintain the size of the heap to be at most k. If the size exceeds k, the element with the smallest frequency (i.e., the root of the heap) is removed.
    Result Extraction:

    The heap contains the k most frequent elements, but they are in increasing order of frequency. Therefore, after extracting the elements from the heap, we reverse the list to get them in decreasing order of frequency.
    Time Complexity:

    Building the frequency map: O(n), where n is the number of elements in the input array.
    Building the heap: For each of the m unique elements in the array, the insertion operation in the heap takes O(log k) time. So, inserting all elements into the heap will take O(m log k).
    Result extraction: Extracting elements from the heap takes O(k log k), but we can also ignore this since it's just the heap extraction process which is bounded by k.
    Overall time complexity: O(n + m log k), where n is the size of the input array and m is the number of unique elements.

    Space Complexity:

    Frequency Map: O(m), where m is the number of unique elements.
    Heap: O(k), as the heap stores at most k elements.
    Overall space complexity: O(m + k).

    Example Walkthrough:
    Input: nums = [1, 1, 1, 2, 2, 3], k = 2
    Frequency map after processing: {1=3, 2=2, 3=1}
    The heap will first hold the elements in increasing order of frequency: [(3,1), (2,2), (1,3)].
    We limit the size of the heap to k = 2, so we remove the element with frequency 1, resulting in [(2, 2), (1, 3)].
    After extracting elements from the heap and reversing the result, the output will be [1, 2].
    Output:
    css
    Copy
    Edit
    Top 2 Frequent Elements: [1, 2]
    This solution effectively handles the problem with an optimized approach using a min-heap, making it both efficient and clean.
 */
import java.util.*;

public class TopKFrequentElements {
    // Custom class to represent an element and its frequency
    static class Element {
        int value;
        int frequency;

        Element(int value, int frequency) {
            this.value = value;
            this.frequency = frequency;
        }
    }

    public List<Integer> topKFrequent(int[] nums, int k) {
        // Map to store the frequency of each element in the array
        Map<Integer, Integer> frequencyMap = new HashMap<>();

        // Populate the frequency map
        for (int num : nums) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }

        // Min-Heap to store the top k frequent elements
        PriorityQueue<Element> minHeap = new PriorityQueue<>((a, b) -> a.frequency - b.frequency);

        // Add elements to the heap
        for (Map.Entry<Integer, Integer> entry : frequencyMap.entrySet()) {
            Element element = new Element(entry.getKey(), entry.getValue());

            if (minHeap.size() < k) {
                minHeap.offer(element); // Safe to add directly
            } else if (entry.getValue() > minHeap.peek().frequency) {
                minHeap.poll();              // Remove least frequent
                minHeap.offer(element);      // Add current one
            }
        }

        // Extract the top k frequent elements from the heap
        List<Integer> result = new ArrayList<>();
        while (!minHeap.isEmpty()) {
            result.add(minHeap.poll().value);
        }

        // The elements are in increasing order of frequency, so reverse the list to get decreasing order
        Collections.reverse(result);
        return result;
    }

    public static void main(String[] args) {
        TopKFrequentElements solution = new TopKFrequentElements();
        int[] nums = {1,1,1,2,2,3};
        int k = 2;

        List<Integer> result = solution.topKFrequent(nums, k);
        System.out.println("Top " + k + " Frequent Elements: " + result);  // Output: [1, 2]
    }
}
