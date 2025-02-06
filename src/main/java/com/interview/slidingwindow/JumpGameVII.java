package com.interview.slidingwindow;

import java.util.LinkedList;
import java.util.Queue;

/*
 * https://leetcode.com/problems/jump-game-vii/
 * https://www.youtube.com/watch?v=bZxWLuiqHes&t=212s
 * Category: medium, Fundamental, Tricky
 * Related:
 * https://leetcode.com/problems/robot-bounded-in-circle/ Medium
 * https://leetcode.com/problems/maximum-score-words-formed-by-letters/ Hard
 * https://leetcode.com/problems/maximum-twin-sum-of-a-linked-list/ Medium
 * https://leetcode.com/problems/valid-word-abbreviation/ eASY Locked
 * https://leetcode.com/problems/guess-the-word/ Hard Bad
 * https://leetcode.com/problems/shuffle-string/ Easy Imp
 * 
 * 
 */
public class JumpGameVII {
    boolean canReach(String s, int minJump, int maxJump) {
        /*
         * BFS Approach:https://www.youtube.com/watch?v=sewjwLuOoHI
         * TC: O(N)
         * SC: O(k)
         * Runtime: 28 ms, faster than 34.23% of Java online submissions for Jump Game VII.
Memory Usage: 67 MB, less than 21.34% of Java online submissions for Jump Game VII.
         */
        Queue<Integer> q = new LinkedList<>();
        q.add(0);
        int l = s.length();
        int reach = 0;
        
        if(s.charAt(l-1)!='0')return false;//if in last no zero then not possible so this is precheck
        
        while(!q.isEmpty()){
                int cur = q.remove();
                
                if(cur == l-1)return true;
                int i;
                for( i= Math.max(cur+minJump, reach); i<=Math.min(cur+maxJump, l-1); i++){
                    
                    if(s.charAt(i)=='0'){
                        if (i == l-1) {
                            return true;
                        }
                         q.add(i);
                    }
                }
                reach = Math.min(cur+maxJump+1, l-1);
            
        }
        return false;
    }
    
    
    public boolean canReach(String s, int minJump, int maxJump) {
        /*
         * Runtime: 19 ms, faster than 47.43% of Java online submissions for Jump Game VII.
Memory Usage: 54.6 MB, less than 26.15% of Java online submissions for Jump Game VII.
         * TC: O(N)
         * SC: O(N)
         */
        int l = s.length();
        boolean[] dp = new boolean[l];
        char[] ch = s.toCharArray();
        
        dp[0] = ch[0]=='0';
        
        int reachable = 0;
        
        for(int i = 1;i<l;i++){
            
            if(i>=minJump){
                reachable += dp[i-minJump]? 1 : 0;
            }
            
            if(i>maxJump){
                reachable -= dp[i-maxJump-1]? 1 : 0;
            }
            
            dp[i] = reachable>0 && ch[i] == '0';
        }
        
        return dp[l-1];
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
