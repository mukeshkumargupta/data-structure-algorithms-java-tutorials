package com.interview.recursionBacktracking;

import java.util.*;
/*
 * https://leetcode.com/problems/iterator-for-combination/
 * Category: Must Do, Medium, Google
 * Related:
 * https://leetcode.com/problems/compare-version-numbers/ Medium
 * https://leetcode.com/problems/change-minimum-characters-to-satisfy-one-of-three-conditions/ Medium
 * https://leetcode.com/problems/minimum-number-of-swaps-to-make-the-string-balanced/ Medium
 * Design the CombinationIterator class:

CombinationIterator(string characters, int combinationLength) Initializes the object with a string characters of sorted distinct lowercase English letters and a number combinationLength as arguments.
next() Returns the next combination of length combinationLength in lexicographical order.
hasNext() Returns true if and only if there exists a next combination.
 

Example 1:

Input
["CombinationIterator", "next", "hasNext", "next", "hasNext", "next", "hasNext"]
[["abc", 2], [], [], [], [], [], []]
Output
[null, "ab", true, "ac", true, "bc", false]

Explanation
CombinationIterator itr = new CombinationIterator("abc", 2);
itr.next();    // return "ab"
itr.hasNext(); // return True
itr.next();    // return "ac"
itr.hasNext(); // return True
itr.next();    // return "bc"
itr.hasNext(); // return False
 

Constraints:

1 <= combinationLength <= characters.length <= 15
All the characters of characters are unique.
At most 104 calls will be made to next and hasNext.
It's guaranteed that all calls of the function next are valid.
 */
public class IteratorforCombination {
    /*
     * Runtime: 14 ms, faster than 81.38% of Java online submissions for Iterator for Combination.
Memory Usage: 41.3 MB, less than 48.18% of Java online submissions for Iterator for Combination.
     */
    String s;
    Queue<String> q;
    int k;
    void getCombination(int start, int length, StringBuilder txt) {
        if (length == 0) {
            q.add(txt.toString());
            return;
        }
        
        for (int i = start; i <= s.length() -length; i++) {
            txt.append(s.charAt(i));
            getCombination(i+1, length-1, txt);
            txt.deleteCharAt(txt.length()-1);
        }
        
    }

    public CombinationIterator(String characters, int combinationLength) {
        s = characters;
        k = combinationLength;
        q = new LinkedList<>();
        StringBuilder txt = new StringBuilder();
        getCombination(0, combinationLength, txt);
    }
    
    public String next() {
        return q.poll();
        
    }
    
    public boolean hasNext() {
        return !q.isEmpty();
        
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
