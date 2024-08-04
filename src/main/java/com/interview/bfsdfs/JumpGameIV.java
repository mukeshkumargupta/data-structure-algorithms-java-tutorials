package com.interview.bfsdfs;

import java.util.*;

/*
 * https://leetcode.com/problems/jump-game-iv/
 * Category: Hard, Must Do, Fundamental
 * https://www.youtube.com/watch?v=JYbU8RH1OSQ
 * Related: https://leetcode.com/problems/jump-game-vii/
 * https://leetcode.com/problems/longest-uncommon-subsequence-ii/ Medium Bad
 * https://leetcode.com/problems/search-suggestions-system/ VVImp
 * https://leetcode.com/problems/minimum-swaps-to-group-all-1s-together-ii/ Medium VVImp
 * Given an array of integers arr, you are initially positioned at the first index of the array.
 * Related question: It can asked min jump required to reach at given index, however in this question it is asked for last index

In one step you can jump from index i to index:

i + 1 where: i + 1 < arr.length.
i - 1 where: i - 1 >= 0.
j where: arr[i] == arr[j] and i != j.
Return the minimum number of steps to reach the last index of the array.

Notice that you can not jump outside of the array at any time.

 

Example 1:

Input: arr = [100,-23,-23,404,100,23,23,23,3,404]
Output: 3
Explanation: You need three jumps from index 0 --> 4 --> 3 --> 9. Note that index 9 is the last index of the array.
Example 2:

Input: arr = [7]
Output: 0
Explanation: Start index is the last index. You do not need to jump.
Example 3:

Input: arr = [7,6,9,6,9,6,9,7]
Output: 1
Explanation: You can jump directly from index 0 to index 7 which is last index of the array.
 

Constraints:

1 <= arr.length <= 5 * 104
-108 <= arr[i] <= 108
 */
public class JumpGameIV {
    public int minJumpsSlow(int[] arr) {//Slow but can solve this problem
        //This approach is little slow because for equal value you are iteration again from 0 to l-1 to check it is equal better you build list of having same value
        int l = arr.length; 
        Queue<Integer> q = new LinkedList<>();
        q.add(0);
        boolean[] visisted = new boolean[l];
        visisted[0] = true;
        
        int level = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int curr = q.remove();

                if (curr == l-1) return level;   // reached the target index


                if (curr + 1 < l && !visisted[curr + 1]) {
                    if (curr + 1 == l-1) {
                        return level+1;
                    }
                    q.add(curr + 1);
                    visisted[curr + 1] = true;
                }

                if (curr - 1 >= 0 && !visisted[curr - 1]) {
                    q.add(curr - 1);
                    visisted[curr - 1] = true;
                    
                }
                   
                for (int j = 0; j < l; j++) {
                    if (curr != j) {
                        if (arr[curr] == arr[j] &&  !visisted[j]) {
                            q.add(j);
                            visisted[j] = true;
                        }
                    }
                    
                }
                
            }
            level++;
        }
        
        return -1;
        
    }
    
    public int minJumps(int[] arr) {//Just replica of previous method but optimization is done
            /*
             * Runtime: 71 ms, faster than 84.84% of Java online submissions for Jump Game IV.
Memory Usage: 100.5 MB, less than 73.34% of Java online submissions for Jump Game IV.
             */
            int l = arr.length; 
            Queue<Integer> q = new LinkedList<>();
            q.add(0);
            boolean[] visisted = new boolean[l];
            visisted[0] = true;
            Map<Integer, List<Integer> > map = new HashMap<>();
            for (int i = 0; i < l; i++) {
                //map.computeIfAbsent(arr[i], (key) -> new ArrayList<>()).add(i);
                map.computeIfAbsent(arr[i], (key)->new ArrayList<Integer>()).add(i);
            }
            
            int level = 0;
            while (!q.isEmpty()) {
                int size = q.size();
                for (int i = 0; i < size; i++) {
                    int curr = q.remove();

                    if (curr == l-1) return level;   // reached the target index


                    if (curr + 1 < l && !visisted[curr + 1]) {
                        if (curr + 1 == l-1) {
                            return level+1;
                        }
                        q.add(curr + 1);
                        visisted[curr + 1] = true;
                    }

                    if (curr - 1 >= 0 && !visisted[curr - 1]) {
                        q.add(curr - 1);
                        visisted[curr - 1] = true;
                        
                    }
                    List<Integer> temp = map.getOrDefault(arr[curr], null);
                    //for (int j = 0; j < l; j++) {
                    if (temp == null) {
                        continue;
                    }
                    for (int j : temp) {
                        if (!visisted[j]) {
                            q.add(j);
                            visisted[j] = true;
                        }
                        
                    }
                   map.remove(arr[curr]);
                    
                }
                level++;
            }
            
            return -1;
        }
    public int minJumps(int[] arr) {//Working
        /*
         * Runtime: 47 ms, faster than 97.84% of Java online submissions for Jump Game IV.
Memory Usage: 57.9 MB, less than 84.48% of Java online submissions for Jump Game IV.
         */
        Map<Integer, List<Integer>> map = new HashMap<>();
        Queue<Integer> queue = new LinkedList<>();
        int l = arr.length;
        
        for(int i = 0; i < l; i ++){
            //ComputeIfAbsentIs slow
            map.computeIfAbsent(arr[i], (key)->new ArrayList<Integer>()).add(i);
            /*List<Integer> list = map.getOrDefault(arr[i], new ArrayList<>());
            list.add(i);
            map.put(arr[i], list);*/
        }
        
        int[] dist = new int[l];
        Arrays.fill(dist, Integer.MAX_VALUE);
        
        queue.add(0);
        dist[0] = 0;
        while(! queue.isEmpty()){
           int index = queue.remove(); 
            int step = dist[index];
            if(index == l - 1)
                return step;
            
            if(index + 1 < l && dist[index + 1] == Integer.MAX_VALUE){
                if (index + 1 == l-1) {
                    return step + 1;
                }
                queue.add(index + 1);
                dist[index + 1] = step + 1;
            }
            
            if(index - 1 >= 0 && dist[index - 1] == Integer.MAX_VALUE)
            {  
                queue.add(index - 1);
                dist[index - 1] = step + 1;
            }
            
            List<Integer> temp = map.getOrDefault(arr[index], new ArrayList<>());
            for(int i = 0; i < temp.size(); i ++){
                int index1 = temp.get(i); 
                if(dist[index1] == Integer.MAX_VALUE){
                    queue.add(index1);
                    dist[index1] = step + 1;
                }
            }
            
            map.remove(arr[index]);
        }
        
        return -1;
    }
    

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        JumpGameIV instance = new JumpGameIV();
        instance.minJumpsTrying(new int[] {100,-23,-23,404,100,23,23,23,3,404});
        
    }
    
}
