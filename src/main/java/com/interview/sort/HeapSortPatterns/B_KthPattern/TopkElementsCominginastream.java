package com.interview.sort.HeapSortPatterns.B_KthPattern;

import java.util.*;
/*
  TopkElementsCominginastream
 * Derived https://leetcode.com/problems/top-k-frequent-elements/discuss/628413/solution-to-get-top-k-elements-coming-in-a-streamjava-instead-of-knowing-array-earlier
 * Category: Medium, Tricky
 * 
 * Explanation: Let's assume we have a stream of arrays, and the following assumption still holds true that k will always be within the range [1,unique number of elements in the array].

Lets's take the following operations and K=2
a) Add 1
b) Add 1
c) Add 2
d) Find top 2 elements
e) Add 3
f) Find top 2 elements
g) Add 2
h) Find top 2 elements

For operation a, b and c, we add the values in heap - it's a min heap, so heap would have "1" and "2" element.
Also, priority of heap is the frequency of each element.
So presentInHeap map: [1 : 2, 2:1]
1:2 -> means "1" is added and its frequency is 2
2:1 -> means "2" is added and its frequency is 1
For operation d - we can print top 2 element from the heap
For operation e- "3" is added in the map but 2 will be popped out since the heap size which becomes 3 now exceeds k=2
So now, we will delete "2" from the main heap but maintain the notInHeap map with popped value
notInHeap map: [2 :1] , it means that when 2 was popped out from main heap, its frequency so far encountered is 1.
For operation f - Top 2 elements would be "1" and "3"
For operation g - Add "2", since 2 is not there in the heap, hence it add the element in the heap, by getting the frequency from notInHeap map

presentInHeap.put(element,notInHeap.getOrDefault(element,0) + 1);
This gives the final frequency as 2 for "2" value.
So now heap has total three elements:
1 with frequency 2
2 with frequency 2
3 with frequency 1

So now, "3" gets popped out from main heap and pushed in notInHeap map

For operation h: find top 2 elements from the heap which is "1" and "2".

I hope it explains the approach :)

One minor correction : It seems we are adding the entire nums at once, but we can change and call this function as we are getting elements in a stream. That is whenever we get any input, then call the addInHeap method and call the getTopKElementsFromHeap to find the top K elements at any point of time in a stream.
 * 
 */
public class TopkElementsCominginastream {
        private Map<Integer, Integer> frequencyMap; // To store frequency of elements
        private PriorityQueue<Element> minHeap; // Min-Heap to track top K frequent elements
        private int k; // The number of top frequent elements to track

        // Custom class to represent a number and its frequency
        static class Element {
            int value;
            int frequency;

            Element(int value, int frequency) {
                this.value = value;
                this.frequency = frequency;
            }
        }

        // Constructor to initialize with stream and K
        public TopkElementsCominginastream(int[] stream, int k) {
            this.k = k;
            this.frequencyMap = new HashMap<>();
            this.minHeap = new PriorityQueue<>((a, b) -> a.frequency - b.frequency); // Min-Heap based on frequency

            // Process the stream to fill the frequency map and heap
            for (int num : stream) {
                add(num); // Add each number from stream
            }
        }

        // Method to add a number to the frequency map and update the heap
        public void add(int num) {
            // Update the frequency of the number
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);

            // Create a new Element with the updated frequency
            Element newElement = new Element(num, frequencyMap.get(num));

            // Add the element to the heap
            minHeap.offer(newElement);

            // If heap exceeds size k, remove the element with the lowest frequency
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }

        // Method to return the top K frequent elements
        public List<Integer> getTopK() {
            List<Integer> result = new ArrayList<>();

            // Extract elements from the heap and sort by frequency
            List<Element> heapList = new ArrayList<>(minHeap);
            heapList.sort((a, b) -> b.frequency - a.frequency); // Sort in descending order of frequency

            // Add sorted elements to the result list
            for (Element entry : heapList) {
                result.add(entry.value);
            }

            return result;
        }

        public void main(String[] args) {
            // Example stream and k value
            int[] stream = {1, 1, 2, 3, 2, 1};
            int k = 2;

            // Create the tracker and process the stream
            TopkElementsCominginastream tracker = new TopkElementsCominginastream(stream, k);

            // Print the top K frequent elements after initial stream processing
            System.out.println("Top " + k + " Frequent Elements: " + tracker.getTopK()); // Output: [1, 2]

            // Add more elements and print updated top K
            tracker.add(3);
            tracker.add(3);
            System.out.println("Updated Top " + k + " Frequent Elements: " + tracker.getTopK()); // Output: [3, 1]
        }
    
}
