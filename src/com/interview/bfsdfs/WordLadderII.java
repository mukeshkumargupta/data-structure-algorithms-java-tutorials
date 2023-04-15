package com.interview.bfsdfs;

import java.util.*;

/*
 * https://www.youtube.com/watch?v=mIZJIuMpI2M&t=1s Good explanation
 * https://leetcode.com/problems/word-ladder-ii/submissions/
 * https://leetcode.com/problems/word-ladder-ii/discuss/1715172/Java-oror-Explained-in-detail-oror-DFS-%2B-BFS
 * Category: Hard, Must Do
 * 
 * A transformation sequence from word beginWord to word endWord using a dictionary wordList is a sequence of words beginWord -> s1 -> s2 -> ... -> sk such that:

Every adjacent pair of words differs by a single letter.
Every si for 1 <= i <= k is in wordList. Note that beginWord does not need to be in wordList.
sk == endWord
Given two words, beginWord and endWord, and a dictionary wordList, return all the shortest transformation sequences from beginWord to endWord, or an empty list if no such sequence exists. Each sequence should be returned as a list of the words [beginWord, s1, s2, ..., sk].

 

Example 1:

Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
Output: [["hit","hot","dot","dog","cog"],["hit","hot","lot","log","cog"]]
Explanation: There are 2 shortest transformation sequences:
"hit" -> "hot" -> "dot" -> "dog" -> "cog"
"hit" -> "hot" -> "lot" -> "log" -> "cog"
Example 2:

Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"]
Output: []
Explanation: The endWord "cog" is not in wordList, therefore there is no valid transformation sequence.
 

Constraints:

1 <= beginWord.length <= 5
endWord.length == beginWord.length
1 <= wordList.length <= 1000
wordList[i].length == beginWord.length
beginWord, endWord, and wordList[i] consist of lowercase English letters.
beginWord != endWord
All the words in wordList are unique.
 */
public class WordLadderII {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> res = new ArrayList<>(); // to store result
        Map<String, List<String>> graph = new HashMap<>(); // key -> word, value -> List of neighbors of 'word'
        Set<String> dict = new HashSet<>(wordList);
        buildGraph(beginWord, endWord, dict, graph); // BFS step tp form the graph
        shortestPath(beginWord, endWord, graph, res, new ArrayList<>()); // DFS step to find res
        return res;
    }
    
    // build graph - BFS
    // TC : O(N * W*26 + N * (N-1))
    private void buildGraph(String begin, String end, Set<String> dict, Map<String, List<String>> graph) {
        Queue<String> q = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        Set<String> toBeVisited = new HashSet<>(); // contains word at a level which needs to be executed at next level
        
        boolean foundEnd = false;
        
        q.add(begin);
        toBeVisited.add(begin);
        
        while(!q.isEmpty()) {
            visited.addAll(toBeVisited);
            toBeVisited.clear();
            
            int size = q.size();
            
            // TC : O(N)
            for(int i = 0; i < size; i++) {
                String currWord = q.poll();
                
                // TC : O(W * 26)
                List<String> currWordNeighbors = getNeighbor(currWord, dict); // find all the neighboring words (with exactly 1 char different than the currWord)
                
                // TC : O(N - 1)
                for(String neighbor : currWordNeighbors) { // we need to do BFS for all the neighboring words
                    if(end.equals(neighbor)) foundEnd = true;
                    if(!visited.contains(neighbor)) { // if neighbor has not been visited yet
                        graph.putIfAbsent(currWord, new ArrayList<>());
                        graph.get(currWord).add(neighbor);
                    }
                    if(!visited.contains(neighbor) && !toBeVisited.contains(neighbor)) {
                        q.add(neighbor);
                        toBeVisited.add(neighbor);
                    }
                }
            }
            
            if(foundEnd) break; // we do not need to find any more neighbors of other words since we know we can find the path from existing words in graph so break out of while loop
        }
    }
    
    // find the shortest path to endWord - DFS
    // TC : O(N) -> N = wordList.length
    // SC : O(N) -> recursion stack
    private void shortestPath(String currWord, String endWord, Map<String, List<String>> graph, List<List<String>> res, ArrayList<String> temp) {
        temp.add(currWord);
        
        if(currWord.equals(endWord)) {
            res.add(new ArrayList<>(temp));
        } else if(graph.containsKey(currWord)) { // only if currWord exists in the map other wise it will be removed from temp
            for(String neighbor : graph.get(currWord)) {
                shortestPath(neighbor, endWord, graph, res, temp);
            }
        }
        
        temp.remove(temp.size() - 1);
    }
    
    // TC : O(W * 26)
    // SC : O(W) -> W = word.length
    private List<String> getNeighbor(String word, Set<String> dict) {
        List<String> res = new ArrayList<>();
        char[] charWord = word.toCharArray();
        
        int size = charWord.length;
        
        for(int i = 0; i < size; i++) {
            for(char k = 'a'; k <= 'z'; k++) {
                if(charWord[i] == k) continue;
                char ch = charWord[i];
                charWord[i] = k;
                String newWord = String.valueOf(charWord);
                if(dict.contains(newWord)) {
                    res.add(newWord);
                }
                charWord[i] = ch;
            }
        }
        return res;
    }
    
    List<List<String>>  ans = new ArrayList<>();    //Stores all possible paths
    void DFS(String beginWord, String endWord, Map<String, Set<String>> adj, List<String> path) {
        path.add(beginWord);  //Push current word
        if(beginWord.equals(endWord))
        {
            ans.add(new ArrayList<>(path));
            path.remove(path.size()-1);
            return;
        }
        if (adj.containsKey(beginWord)) {
           for(String x : adj.get(beginWord)) {
               DFS(x, endWord, adj, path); 
           }
                
        }
        
        
        path.remove(path.size()-1);    //Pop current word to Backtrack
    }
    //TODO: to see what went wrong
    public List<List<String>> findLaddersGettingTLE(String beginWord, String endWord, List<String> wordList) {
        Map<String, Set<String>> adj = new HashMap<>();   //Adjacency List
        //unordered_set<string> dict(wordList.begin(),wordList.end()); 
        Set<String> dict = new HashSet(wordList);//Insert WordList in SET
        
        //STEP-1: Find min-depth using BFS
        Queue<String> Q = new LinkedList<>();    //For BFS traversal
        Q.add(beginWord);  //Push start node (beginWord)
        Map<String, Integer> visited = new HashMap<>(); //Key->String (Node)...Value->Level (Depth of traversal)
        visited.put(beginWord, 0); //Start node will always be at level 0
        while(!Q.isEmpty())
        {
            String curr = Q.remove();
            char[] temp = curr.toCharArray();
            for(int i = 0; i < curr.length(); ++i)    //For all characters
            {
                for(char x = 'a'; x <= 'z'; ++x)    //Try all possible 26 letters
                {
                    if(temp[i] == x)    //Skip if letter is same as in original word
                        continue;

                    temp[i] = x; 
                    String newWord = new String(temp);
                    if(dict.contains(newWord))    //Check if new word is present in wordList
                    {
                        if(visited.getOrDefault(newWord, 0) == 0)    //check if the new word was already visited
                        {
                            visited.put(newWord, visited.get(curr)  + 1);
                            Q.add(newWord);
                            adj.computeIfAbsent(curr, (key) -> new HashSet<String>() ).add(newWord);
                            //adj[curr].insert(temp);
                        } 
                        else if(visited.get(newWord) == visited.get(curr) + 1) //If already visited and new word is the child (We should always move down)
                            adj.computeIfAbsent(curr, (key) -> new HashSet<String>() ).add(newWord);
                    }
                }
                temp[i] = curr.charAt(i);  //Revert back temp to curr
            }
        }
        //STEP-2: Find all possible paths at min-depth using DFS
        List<String> path = new ArrayList<>();
        DFS(beginWord, endWord, adj, path); //Find all possible paths with min-depth
        return ans; 
        
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
