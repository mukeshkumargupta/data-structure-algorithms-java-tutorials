package com.interview.recursionBacktracking.D_wordSearchPatterns;
import java.util.*;
/*
https://leetcode.com/problems/word-search-ii/
Category: Hard, Top150, Word Search II | DFS + Map | DFS + TRIE
https://www.youtube.com/watch?v=EmvsBM7o-5k
Related:
https://leetcode.com/problems/word-search/ Medium
https://leetcode.com/problems/unique-paths-iii/ Hard
https://leetcode.com/problems/encrypt-and-decrypt-strings/ Hard

Given an m x n board of characters and a list of strings words, return all words on the board.

Each word must be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.



Example 1:


Input: board = [["o","a","a","n"],["e","t","a","e"],["i","h","k","r"],["i","f","l","v"]], words = ["oath","pea","eat","rain"]
Output: ["eat","oath"]
Example 2:


Input: board = [["a","b"],["c","d"]], words = ["abcb"]
Output: []


Constraints:

m == board.length
n == board[i].length
1 <= m, n <= 12
board[i][j] is a lowercase English letter.
1 <= words.length <= 3 * 104
1 <= words[i].length <= 10
words[i] consists of lowercase English letters.
All the strings of words are unique.
 */
public class WordSearchII {
    /*
        👇 Key Variables:
        W = number of words in the list
        L = average length of a word
        M = number of rows in the board
        N = number of columns in the board
        K = total number of characters across all words (≈ W * L)

        🧠 Time Complexity

        🔹 1. Building the Trie (insert() method)
           For each word of length L, you traverse its characters and insert into the trie.
           Cost per word = O(L)
           Total = O(W * L)
           ✅ So, building the trie takes O(W * L) time

        🔹 2. DFS Traversal on the Board
           We do DFS starting from each cell in the MxN board.

           For each cell:
           In the worst case, the DFS can go up to the length of the longest word, which is L.
           But thanks to the Trie pruning, DFS does not explore unnecessary paths — it stops early if there's no valid child in the trie.

           So the DFS from one cell takes O(4^L) in the worst case without pruning,
           but with pruning it's much faster and bound by the total number of characters in all the words and the shape of the board.

           But to stay conservative in worst-case analysis, we still express it as:
           ✅ DFS per cell: O(4^L)
           ✅ Total DFS over the board: O(M * N * 4^L)

           But this is amortized down to:
           ✅ O(M * N * L) in practice due to the Trie early exits.

        ✅ Total Time Complexity:
           O(W * L)               → building the Trie
         + O(M * N * L)           → DFS with Trie pruning
         = O(W * L + M * N * L)

        📦 Space Complexity

        🔹 1. Trie Space
           Each word of length L adds nodes to the trie — up to 26 possible children per node, but we only insert actual characters.
           ✅ So, Trie space is: O(W * L)

        🔹 2. DFS Recursion Stack
           The depth of the recursion stack = length of the longest word = L.
           ✅ So, recursion stack uses: O(L)

        🔹 3. Result List
           Worst case: all words are found.
           ✅ So result list space = O(W)

        ✅ Total Space Complexity:
           O(W * L)         → Trie
         + O(L)             → DFS recursion
         + O(W)             → Result list
         = O(W * L)
           (The O(L) and O(W) are small compared to O(W * L))

        ✅ Final Answer:
        Time Complexity: O(W * L + M * N * L)
        Space Complexity: O(W * L)
     */
    static class TrieNode {
        char c;
        int endsCount;
        String word;
        TrieNode[] children;

        TrieNode(char c) {
            this.c = c;
            this.endsCount = 0;
            this.word = "";
            this.children = new TrieNode[26];
        }
    }

    TrieNode root = getNode('/');
    int[][] dirs = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};

    private TrieNode getNode(char c) {
        return new TrieNode(c);
    }

    private void insert(String word) {
        TrieNode curr = root;
        for (char ch : word.toCharArray()) {
            int idx = ch - 'a';
            if (curr.children[idx] == null) {
                curr.children[idx] = getNode(ch);
            }
            curr = curr.children[idx];
        }
        curr.endsCount++;
        curr.word = word;
    }

    public List<String> findWords(char[][] board, String[] words) {
        List<String> result = new ArrayList<>();

        for (String word : words) {
            insert(word);
        }

        int rows = board.length;
        int cols = board[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                dfs(board, i, j, rows, cols, root, result);
            }
        }

        return result;
    }

    private void dfs(char[][] board, int i, int j, int r, int c, TrieNode curr, List<String> result) {
        if (!isSafe(i, j, r, c, curr, board)) return;

        char ch = board[i][j];
        curr = curr.children[ch - 'a'];

        if (curr.endsCount > 0) {
            result.add(curr.word);
            curr.endsCount = 0; // avoid duplicates
        }

        board[i][j] = '$'; // mark as visited

        for (int[] dir : dirs) {
            int ni = i + dir[0];
            int nj = j + dir[1];
            dfs(board, ni, nj, r, c, curr, result);
        }

        board[i][j] = ch; // backtrack
    }

    private boolean isSafe(int i, int j, int r, int c, TrieNode curr, char[][] board) {
        return i >= 0 && i < r && j >= 0  && j < c && board[i][j] != '$' && curr.children[board[i][j] - 'a'] != null;
    }

/*
✅ Master List of Derived Problems from Word Search II

1. Word Search I (Leetcode 79)
🔸Problem: Check if a given word exists in the board by moving horizontally or vertically to adjacent cells.

🧾Input:
board = [
  ['A','B','C','E'],
  ['S','F','C','S'],
  ['A','D','E','E']
]
word = "ABCCED"

✅Output: true

---

2. Word Search II (Leetcode 212)
🔸Problem: Find all words from a given dictionary that can be formed on the board.

🧾Input:
board = [
  ['o','a','a','n'],
  ['e','t','a','e'],
  ['i','h','k','r'],
  ['i','f','l','v']
]
words = ["oath","pea","eat","rain"]

✅Output: ["eat","oath"]

---

3. Boggle Solver (8 Directions)
🔸Problem: Like Word Search II but allows 8 directions including diagonals.

🧾Input:
board = [
  ['G','I','Z'],
  ['U','E','K'],
  ['Q','S','E']
]
dictionary = ["GEEKS", "FOR", "QUIZ", "GO"]

✅Output: ["GEEKS", "QUIZ"]

---

4. Auto-Complete Using Trie
🔸Problem: Return all words in dictionary that start with the given prefix.

🧾Input:
dictionary = ["apple", "app", "apex", "banana"]
prefix = "ap"

✅Output: ["apple", "app", "apex"]

---

5. Word Ladder I (Leetcode 127)
🔸Problem: Return length of shortest transformation from beginWord to endWord.

🧾Input:
beginWord = "hit"
endWord = "cog"
wordList = ["hot","dot","dog","lot","log","cog"]

✅Output: 5  // ["hit","hot","dot","dog","cog"]

---

6. Word Ladder II (Leetcode 126)
🔸Problem: Return all shortest transformation sequences from beginWord to endWord.

🧾Input:
Same as above

✅Output:
[
  ["hit","hot","dot","dog","cog"],
  ["hit","hot","lot","log","cog"]
]

---

7. Find Longest Word from Board
🔸Problem: Return longest word from dictionary that can be formed from board.

🧾Input:
board = [['a','b'],['c','d']]
words = ["abc", "abd", "abcd"]

✅Output: "abcd"

---

8. Count Valid Dictionary Words from Board
🔸Problem: Count how many words from dictionary can be formed.

🧾Input:
Same as above

✅Output: 3

---

9. Word Search with Diagonal and Knight Moves
🔸Problem: Check if words can be formed allowing diagonal or knight-style moves.

🧾Input:
board = [['a','b'],['c','d']]
words = ["abcd", "cad"]

✅Output: Depends on allowed moves

---

10. Word Search with Reuse Allowed
🔸Problem: Letters can be reused multiple times for same word.

🧾Input:
board = [['a','b'],['c','a']]
words = ["aaa", "aba"]

✅Output: ["aaa", "aba"]

---

11. Blocked Cells in Word Search
🔸Problem: Some cells cannot be used (denoted by '#').

🧾Input:
board = [['a','#'],['b','c']]
words = ["abc", "ab"]

✅Output: ["ab"]

---

12. Word Search with Frequency Constraint
🔸Problem: Return only words that appear more than once on the board.

🧾Input:
board = [['a','b','a'],['a','b','a']]
words = ["aba"]

✅Output: ["aba"] // Only if multiple paths exist

---

13. Scrabble Helper
🔸Problem: Can words be formed from a given rack of letters (not a board)?

🧾Input:
rack = ['a', 'e', 'l', 'p', 'p']
dictionary = ["apple", "ape", "peal", "lap"]

✅Output: ["apple", "ape", "peal", "lap"]

---

14. Crossword Fitting
🔸Problem: Fit all given words into grid template with + for walls and - for fillable spaces.

🧾Input:
grid = [
  ['+','-','+'],
  ['+','-','+'],
  ['+','-','+']
]
words = ["cat", "dog"]

✅Output: Grid filled with words in columns/rows as per rules

---

15. Word Break (Leetcode 139)
🔸Problem: Can a string be segmented into words from dictionary?

🧾Input:
s = "leetcode"
wordDict = ["leet","code"]

✅Output: true

---

16. Max Score Word from Board
🔸Problem: Return word from board with highest score using provided map.

🧾Input:
board = [['a','b'],['c','d']]
words = ["abc", "abd"]
score = {"abc": 5, "abd": 10}

✅Output: "abd"

---

17. Word Maze Path Tracking
🔸Problem: Return list of coordinates that form the word from the board.

🧾Input:
board = [['a','b'],['c','d']]
word = "abc"

✅Output: [[[0,0],[0,1],[1,1]]]

---

18. Form Word from Board Letters Only (no adjacency)
🔸Problem: Can word be formed using letters on board without adjacency?

🧾Input:
board = [['a','b'],['c','d']]
word = "bad"

✅Output: true

*/
}
