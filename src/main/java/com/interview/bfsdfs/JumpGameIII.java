package com.interview.bfsdfs;

/*
 * https://leetcode.com/problems/jump-game-iii/
 * https://www.youtube.com/watch?v=7Cz91Uj0VCU
 * Category: Medium, Must Do, Fundamental
 * Related: https://leetcode.com/problems/jump-game-vii/ 
 * https://leetcode.com/problems/find-permutation/ Medium Locked
 * https://leetcode.com/problems/minimum-absolute-sum-difference/ Medium VImp
 * https://leetcode.com/problems/nearest-exit-from-entrance-in-maze/ Medium VVimp
 * https://leetcode.com/problems/insufficient-nodes-in-root-to-leaf-paths/ Medium Bad
 * https://leetcode.com/problems/delete-leaves-with-a-given-value/ Medium
 * https://leetcode.com/problems/maximum-good-people-based-on-statements/ Hard
 * Other realted question: Find minimun no of jum required to reach destination, just count level in bfs, you will get answer
 * This question can be enhanced for any number of jump by giving some formula, in this question only left or right jump is given only.
 * 
 * Do All version of jump game
Given an array of non-negative integers arr, you are initially positioned at start index of the array. When you are at index i, you can jump to i + arr[i] or i - arr[i], check if you can reach to any index with value 0.

Notice that you can not jump outside of the array at any time.

 

Example 1:

Input: arr = [4,2,3,0,3,1,2], start = 5
Output: true
Explanation: 
All possible ways to reach at index 3 with value 0 are: 
index 5 -> index 4 -> index 1 -> index 3 
index 5 -> index 6 -> index 4 -> index 1 -> index 3 
Example 2:

Input: arr = [4,2,3,0,3,1,2], start = 0
Output: true 
Explanation: 
One possible way to reach at index 3 with value 0 is: 
index 0 -> index 4 -> index 1 -> index 3
Example 3:

Input: arr = [3,0,2,1,2], start = 2
Output: false
Explanation: There is no way to reach at index 1 with value 0.
 

Constraints:

1 <= arr.length <= 5 * 104
0 <= arr[i] < arr.length
0 <= start < arr.length
 */
public class JumpGameIII {
    public boolean canReach(int[] arr, int start) {
        /*
         * Runtime: 7 ms, faster than 43.87% of Java online submissions for Jump Game III.
Memory Usage: 61.1 MB, less than 28.12% of Java online submissions for Jump Game III.
This can be further optimized without using visited array because our criteria is 0 so if using same array we can toggle value to negative
in case of zero it will not effect whether it is positive or negative
         */
        int l = arr.length;
        Queue<Integer> q = new LinkedList<>();
        
        if (arr[start] == 0) {
            return true;
        }
        q.add(start);
        boolean[] visited = new boolean[l];
        visited[start] = true;
        
        while(!q.isEmpty()) {
            int size = q.size();
            
            for (int i = 0; i < size; i++) {
                int currentIndex = q.remove();
                if (arr[currentIndex] == 0) {
                  return true;  
                }
                int leftIndex = currentIndex - arr[currentIndex];
                if (leftIndex >= 0 && !visited[leftIndex]) {
                    q.add(leftIndex);
                    visited[leftIndex] = true;
                }
                
                int rightIndex = currentIndex + arr[currentIndex];
                if (rightIndex < l && !visited[rightIndex]) {
                    q.add(rightIndex);
                    visited[rightIndex] = true;
                }
                
            }
            
        }
        return false;
        
    }
    
    public boolean canReach(int[] arr, int start) {
        /*
         * Runtime: 5 ms, faster than 64.92% of Java online submissions for Jump Game III.
Memory Usage: 49.9 MB, less than 88.19% of Java online submissions for Jump Game III.
         */
        int l = arr.length;
        Queue<Integer> q = new LinkedList<>();
        
        if (arr[start] == 0) {
            return true;
        }
        q.add(start);
        
        while(!q.isEmpty()) {
            int size = q.size();
            
            for (int i = 0; i < size; i++) {
                int currentIndex = q.remove();
                
                //already visited
                 if (arr[currentIndex] < 0) {
                   continue;  
                 }
                if (arr[currentIndex] == 0) {
                  return true;  
                }
                
                int leftIndex = currentIndex - arr[currentIndex];
                if (leftIndex >= 0) {
                    q.add(leftIndex);
                }
                
                int rightIndex = currentIndex + arr[currentIndex];
                if (rightIndex < l) {
                    q.add(rightIndex);
                }
                arr[currentIndex] = -arr[currentIndex];
                
            }
            
        }
        return false;
    }
    
    
    
    //Using bfs
    public boolean canReachBfs(int[] arr, int start) {
        /*
         * T.C : O(n)
S.C : O(n)
Runtime: 6 ms, faster than 51.99% of Java online submissions for Jump Game III.
Memory Usage: 49.8 MB, less than 88.54% of Java online submissions for Jump Game III.
         */
        int l = arr.length; 
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        
        while (!q.isEmpty()) {
            int curr = q.remove();
            
            if (arr[curr] == 0) return true;   // reached the target index
            
            // reached this index again, so not possible from this index but might be possible to reach from other direction, so check again in the queue
            if (arr[curr]<0) continue;   
            
            if (curr + arr[curr] < l)
                q.add(curr + arr[curr]);
            if (curr - arr[curr] >= 0)
                q.add(curr - arr[curr]);
            
            arr[curr] = -arr[curr];   // to distinguish between index coming first time or again
        }
        
        return false;
    }
    
    boolean[] visited;
    public boolean recursiveCheck(int[] arr, int index) {//Easy to understand
        /*
         * Runtime: 3 ms, faster than 66.43% of Java online submissions for Jump Game III.
Memory Usage: 56.4 MB, less than 19.52% of Java online submissions for Jump Game III
         */
        if(index<0 || index>=arr.length) return false;
        if(arr[index] == 0) return true;
        if(visited[index]) return false;
        visited[index] = true;
        return recursiveCheck(arr, index+arr[index]) || recursiveCheck(arr, index-arr[index]);
    }
    
    public boolean canReachDfs(int[] arr, int start) {
        visited = new boolean[arr.length];
        return recursiveCheck(arr, start);
    }
    
    public boolean canReachDfs2(int[] arr, int start) {//Not easy to understand
        
        if(start<0 || start>=arr.length || arr[start]<0) return false;   // terminating conditions
        
        if(arr[start]==0){   //reached the target
            return true;
        }
        
        arr[start] = -arr[start];
        
        return canReach(arr,start+arr[start])||canReach(arr,start-arr[start]);   //checking in both direction
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
