package com.interview.trie;
import java.util.*;

/*
 * https://leetcode.com/problems/stream-of-characters/
 * https://www.youtube.com/watch?v=Y37WA4advWw&list=PL1w8k37X_6L-bCZ3m0FFBZmRv4onE7Zjl&index=25
 * Category: Hard, Must Do, Google
 * Related:
 * https://leetcode.com/problems/array-partition-i/ Easy
 * https://leetcode.com/problems/minimum-window-subsequence/ Hard
 * https://leetcode.com/problems/maximum-difference-between-increasing-elements/ Easy
 * Design an algorithm that accepts a stream of characters and checks if a suffix of these characters is a string of a given array of strings words.

For example, if words = ["abc", "xyz"] and the stream added the four characters (one by one) 'a', 'x', 'y', and 'z', your algorithm should detect that the suffix "xyz" of the characters "axyz" matches "xyz" from words.

Implement the StreamChecker class:

StreamChecker(String[] words) Initializes the object with the strings array words.
boolean query(char letter) Accepts a new character from the stream and returns true if any non-empty suffix from the stream forms a word that is in words.
 

Example 1:

Input
["StreamChecker", "query", "query", "query", "query", "query", "query", "query", "query", "query", "query", "query", "query"]
[[["cd", "f", "kl"]], ["a"], ["b"], ["c"], ["d"], ["e"], ["f"], ["g"], ["h"], ["i"], ["j"], ["k"], ["l"]]
Output
[null, false, false, false, true, false, true, false, false, false, false, false, true]

Explanation
StreamChecker streamChecker = new StreamChecker(["cd", "f", "kl"]);
streamChecker.query("a"); // return False
streamChecker.query("b"); // return False
streamChecker.query("c"); // return False
streamChecker.query("d"); // return True, because 'cd' is in the wordlist
streamChecker.query("e"); // return False
streamChecker.query("f"); // return True, because 'f' is in the wordlist
streamChecker.query("g"); // return False
streamChecker.query("h"); // return False
streamChecker.query("i"); // return False
streamChecker.query("j"); // return False
streamChecker.query("k"); // return False
streamChecker.query("l"); // return True, because 'kl' is in the wordlist
 

Constraints:

1 <= words.length <= 2000
1 <= words[i].length <= 2000
words[i] consists of lowercase English letters.
letter is a lowercase English letter.
At most 4 * 104 calls will be made to query.

 */
public class Part_A_F_StreamofCharacters {
    class StreamChecker {
        /*
         * Runtime: 113 ms, faster than 64.26% of Java online submissions for Stream of Characters.
Memory Usage: 75.7 MB, less than 48.43% of Java online submissions for Stream of Characters.
         */
        private class TrieTreeNode {
            Map<Character, TrieTreeNode> children;
            boolean endOfWord;
            public TrieTreeNode() {
                children = new HashMap<>();
                endOfWord = false;
            }
        }
        List<Character> list;
        private final TrieTreeNode root;

        
        public StreamChecker(String[] words) {
            list = new ArrayList<>();
            root = new TrieTreeNode();
            for (String word : words) {
                insert(word);
            }
        }
        
        /**
         * Iterative implementation of insert into trie
         */
        public void insert(String word) {
            TrieTreeNode current = root;
            for (int i = word.length() -1; i >= 0; i--) {
                char ch = word.charAt(i);
                TrieTreeNode TreeNode = current.children.get(ch);
                if (TreeNode == null) {
                    TreeNode = new TrieTreeNode();
                    current.children.put(ch, TreeNode);
                }
                current = TreeNode;
            }
            //mark the current TreeNodes endOfWord as true
            current.endOfWord = true;
        }
        
        /**
         * Iterative implementation of search into trie.
         */
        public boolean search(List<Character> list) {
            TrieTreeNode current = root;
            for (int i = list.size() - 1; i >= 0 ; i--) {
                char ch = list.get(i);

                TrieTreeNode TreeNode = current.children.get(ch);
                //if TreeNode does not exist for given char then return false
                if (TreeNode == null) {
                    return false;
                }
                if (TreeNode.endOfWord) {//no need to look for other combination
                    return TreeNode.endOfWord;
                }
                current = TreeNode;
            }
          //return true of current's endOfWord is true else return false.
            return TreeNode.endOfWord;
        }
        
        public boolean query(char letter) {
            if (list.size() == 2000) {
                list.remove(0);
            }
            list.add(letter);
            return search(list);
        }
    }

    /**
     * Your StreamChecker object will be instantiated and called as such:
     * StreamChecker obj = new StreamChecker(words);
     * boolean param_1 = obj.query(letter);
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
