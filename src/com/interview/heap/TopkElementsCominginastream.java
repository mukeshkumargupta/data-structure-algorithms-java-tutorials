package com.interview.heap;

import java.util.*;
/*
 * https://leetcode.com/problems/top-k-frequent-elements/discuss/628413/solution-to-get-top-k-elements-coming-in-a-streamjava-instead-of-knowing-array-earlier
 * Category: Hard, Tricky
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

    public int[] topKFrequent(int[] nums, int k) {
        
        Map<Integer,Integer> presentInHeap = new HashMap<>();
        Map<Integer,Integer> notInHeap = new HashMap<>();
        
        PriorityQueue<Integer> heap = new PriorityQueue<>(
            (a,b) -> presentInHeap.getOrDefault(a,0) - presentInHeap.getOrDefault(b,0) );
        
        for(int i=0;i<nums.length;i++){
             addInHeap(presentInHeap,notInHeap,heap, k,nums[i]);
        }
        return getTopKElementsFromHeap(heap);
    }
    
   
    public void addInHeap(Map<Integer,Integer> presentInHeap,
                          Map<Integer,Integer> notInHeap,
                          PriorityQueue<Integer> heap,
                          int k,
                          int element){
        
        if(presentInHeap.containsKey(element)){
            presentInHeap.put(element,presentInHeap.get(element)+1);
            heap.remove(element);
            heap.add(element);
        }else{
            presentInHeap.put(element,notInHeap.getOrDefault(element,0) + 1);
            heap.add(element);
            if(heap.size() > k){
                int poppedElement = heap.poll();
                notInHeap.put(poppedElement,presentInHeap.get(poppedElement));
                presentInHeap.remove(poppedElement);
            }
        }
    }
    
    public int[] getTopKElementsFromHeap(PriorityQueue<Integer> heap){
        int i=0;
        int topKElements[] = new int[heap.size()];
        for(Integer element: heap){
            topKElements[i++] = element;
        }
        return topKElements;
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
