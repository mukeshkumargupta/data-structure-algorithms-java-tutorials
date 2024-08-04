package com.interview.sort;

/*
 * https://leetcode.com/problems/task-scheduler/
 * Category: Medium, Facebook, Tricky
 * https://leetcode.com/problems/rearrange-string-k-distance-apart/ Hard
 * https://leetcode.com/problems/reorganize-string/ Medium
 * https://leetcode.com/problems/maximum-number-of-weeks-for-which-you-can-work/ Medium
 * Given a characters array tasks, representing the tasks a CPU needs to do, where each letter represents a different task. Tasks could be done in any order. Each task is done in one unit of time. For each unit of time, the CPU could complete either one task or just be idle.

However, there is a non-negative integer n that represents the cooldown period between two same tasks (the same letter in the array), that is that there must be at least n units of time between any two same tasks.

Return the least number of units of times that the CPU will take to finish all the given tasks.

 

Example 1:

Input: tasks = ["A","A","A","B","B","B"], n = 2
Output: 8
Explanation: 
A -> B -> idle -> A -> B -> idle -> A -> B
There is at least 2 units of time between any two same tasks.
Example 2:

Input: tasks = ["A","A","A","B","B","B"], n = 0
Output: 6
Explanation: On this case any permutation of size 6 would work since n = 0.
["A","A","A","B","B","B"]
["A","B","A","B","A","B"]
["B","B","B","A","A","A"]
...
And so on.
Example 3:

Input: tasks = ["A","A","A","A","A","A","B","C","D","E","F","G"], n = 2
Output: 16
Explanation: 
One possible solution is
A -> B -> C -> A -> D -> E -> A -> F -> G -> A -> idle -> idle -> A -> idle -> idle -> A
 

Constraints:

1 <= task.length <= 104
tasks[i] is upper-case English letter.
The integer n is in the range [0, 100].
Accepted
301,897
Submissions
563,243
Seen this question in a real interview before?

Yes

No

Problems

 */
public class TaskScheduler {
    public int leastInterval(char[] tasks, int n) {
        /*
         * Runtime: 53 ms, faster than 13.67% of Java online submissions for Task Scheduler.
Memory Usage: 72 MB, less than 5.01% of Java online submissions for Task Scheduler.
         */
        
        Map<Character, Integer> count = new HashMap<>();
        for (Character t : tasks) {
            count.put(t, count.getOrDefault(t, 0) + 1);
        }
        
        Queue<Integer> pq = new PriorityQueue<>((a, b) -> {
            return b -a;
        });
        
        for (Character key : count.keySet()) {
            //System.out.println(key);
            pq.add(count.get(key));
        }
        
        int result = 0;
        
        while (!pq.isEmpty()) {
            int time = 0;
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < n+1; i++) {//why n+1
                if (!pq.isEmpty()) {
                    list.add(pq.remove() -1);
                    time++;
                }
            }
            
            for (int c : list) {
                if (c > 0 ) pq.add(c);
            }
            result += !pq.isEmpty() ?  n + 1 : time;
        }
        return result;

        
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
