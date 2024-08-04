package com.interview.dynamic;

import java.util.*;

/*
 * https://leetcode.com/problems/word-break/submissions/
 * https://www.youtube.com/watch?v=th4OnoGasMU
 * Category: Medium, Tricky, VVImp
 * Related: https://leetcode.com/problems/word-break-ii/ Hard
 * 
 * Given a string s and a dictionary of strings wordDict, return true if s can be segmented into a space-separated sequence of one or more dictionary words.

Note that the same word in the dictionary may be reused multiple times in the segmentation.

 

Example 1:

Input: s = "leetcode", wordDict = ["leet","code"]
Output: true
Explanation: Return true because "leetcode" can be segmented as "leet code".
Example 2:

Input: s = "applepenapple", wordDict = ["apple","pen"]
Output: true
Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
Note that you are allowed to reuse a dictionary word.
Example 3:

Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
Output: false
 

Constraints:

1 <= s.length <= 300
1 <= wordDict.length <= 1000
1 <= wordDict[i].length <= 20
s and wordDict[i] consist of only lowercase English letters.
All the strings of wordDict are unique.
Accepted
992,361
Submissions
2,251,710
 */
        
public class WordBreak {
    Boolean[] dp; //Here Boolean object is taken to ensure default value is null, if set then either false or true
    Set<String> myset;
    boolean wordBreakUtil(String s,int pos){
        if(pos==s.length())   return true;
        
        if(dp[pos] != null)  return dp[pos];
        
        String temp="";
        for(int i=pos;i<s.length();i++) {
            temp = s.substring(pos,i+1); //i+1 because we take one extra
            if(myset.contains(temp) && wordBreakUtil(s,i+1))
                return dp[pos] = true;
        }
        
        return dp[pos] = false;
    }
    boolean wordBreak(String s, List<String> wordDict) {
        //Memoization techanics
        myset = new HashSet<>(wordDict);
        
        int l = s.length();
        dp = new Boolean[l];
        return wordBreakUtil(s,0);
    }
    public boolean wordBreak(String s, List<String> wordDict) {
        /*
         * TC: N2 * N here n2 to fill matrix and to fill each cell might required n partition in worst case
         * so total is N3
         * Runtime: 9 ms, faster than 50.26% of Java online submissions for Word Break.
Memory Usage: 42.7 MB, less than 47.61% of Java online submissions for Word Break.
         */
        int l = s.length();
        int R = l;
        int C = l;
        boolean[][] dp = new boolean[R][C];
        
        Set<String> myset = new HashSet<>(wordDict);
        /*for(String word: wordDict)
            myset.add(word);*/
        
        for(int length=1;length<=l;++length) //Window Size
        {
            int start = 0;
            int end = start + length -1; //window end
            while(end<l) //Sliding Window
            {
                String temp = s.substring(start,end+1); //take one extra
                if(myset.contains(temp))
                    dp[start][end] = true;
                else
                {
                    boolean flag = false;
                    for(int i=start;i<end;++i) //full partition is already done here in if condition so condition should be i < end (exclude)
                        if(dp[start][i] && dp[i+1][end])
                        {
                            flag = true;
                            break;
                        }
                    dp[start][end] = flag;
                }
                start += 1;
                end += 1;
            }
        }
        return dp[0][R-1];
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
