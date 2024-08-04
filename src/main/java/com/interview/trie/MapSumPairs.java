package com.interview.trie;

import java.util.*;
/*
 * https://leetcode.com/problems/map-sum-pairs/
 * Category: Medium, Must Do
 * https://www.youtube.com/watch?v=Z7dkugscqLA
 * Related: https://leetcode.com/problems/implement-magic-dictionary/ Medium
 * https://leetcode.com/problems/synonymous-sentences/ Medium
 * https://leetcode.com/problems/smallest-string-with-swaps/ Medium
 * 
 * Design a map that allows you to do the following:

Maps a string key to a given value.
Returns the sum of the values that have a key with a prefix equal to a given string.
Implement the MapSum class:

MapSum() Initializes the MapSum object.
void insert(String key, int val) Inserts the key-val pair into the map. If the key already existed, the original key-value pair will be overridden to the new one.
int sum(string prefix) Returns the sum of all the pairs' value whose key starts with the prefix.
 

Example 1:

Input
["MapSum", "insert", "sum", "insert", "sum"]
[[], ["apple", 3], ["ap"], ["app", 2], ["ap"]]
Output
[null, null, 3, null, 5]

Explanation
MapSum mapSum = new MapSum();
mapSum.insert("apple", 3);  
mapSum.sum("ap");           // return 3 (apple = 3)
mapSum.insert("app", 2);    
mapSum.sum("ap");           // return 5 (apple + app = 3 + 2 = 5)
 

Constraints:

1 <= key.length, prefix.length <= 50
key and prefix consist of only lowercase English letters.
1 <= val <= 1000
At most 50 calls will be made to insert and sum.
 */
public class MapSumPairs {
    /*
     * Runtime: 11 ms, faster than 97.97% of Java online submissions for Map Sum Pairs.
Memory Usage: 38.9 MB, less than 81.56% of Java online submissions for Map Sum Pairs.
     */
 // Alphabet size (# of symbols) 
    final int ALPHABET_SIZE  = 26;

     // trie TreeNode 
     class TrieTreeNode 
     { 
         TrieTreeNode[] children; 
         int score; 
         TrieTreeNode() {
             children = new TrieTreeNode[ALPHABET_SIZE];
             score = 0;
         }
     };
        Map<String, Integer> map;
        TrieTreeNode root;
        public MapSum() {
            map = new HashMap<>();
            root = new TrieTreeNode();
            
        }

        
        public void insert(String key, int val) {
            int deltaScore = val - map.getOrDefault(key, 0);
            map.put(key, val);//Maintain lookup for each key
            
            TrieTreeNode current = root;
            for (int i = 0; i < key.length(); i++) {
                TrieTreeNode node = current.children[key.charAt(i) - 'a'];
                if (node == null) {
                    node = new TrieTreeNode();
                    current.children[key.charAt(i) - 'a'] = node;//add it
                }
                node.score += deltaScore;
                current = node;
                
            }
            
        }
        
        public int sum(String prefix) {
            TrieTreeNode current = root;
            for (int i = 0; i < prefix.length(); i++) {
                TrieTreeNode node = current.children[prefix.charAt(i) - 'a'];
                if (node == null) {
                    return 0;
                }
                current = node; 
            }
            return current.score;
            
        }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
