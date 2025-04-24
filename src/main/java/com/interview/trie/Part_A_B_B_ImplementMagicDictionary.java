package com.interview.trie;

import java.util.*;

/*
 * https://leetcode.com/problems/implement-magic-dictionary/
 * Category: Medium, Must Do
 * Related: https://leetcode.com/problems/design-hit-counter/ Medium
 * Other emthod: using trie: https://leetcode.com/problems/implement-magic-dictionary/discuss/1503804/Java-Trie%2BRecursion-Solution
 * https://leetcode.com/problems/before-and-after-puzzle/ Medium
 * https://leetcode.com/problems/check-if-a-string-contains-all-binary-codes-of-size-k/ Medium
 * Design a data structure that is initialized with a list of different words. Provided a string, you should determine if you can change exactly one character in this string to match any word in the data structure.

Implement the MagicDictionary class:

MagicDictionary() Initializes the object.
void buildDict(String[] dictionary) Sets the data structure with an array of distinct strings dictionary.
bool search(String searchWord) Returns true if you can change exactly one character in searchWord to match any string in the data structure, otherwise returns false.
 

Example 1:

Input
["MagicDictionary", "buildDict", "search", "search", "search", "search"]
[[], [["hello", "leetcode"]], ["hello"], ["hhllo"], ["hell"], ["leetcoded"]]
Output
[null, null, false, true, false, false]

Explanation
MagicDictionary magicDictionary = new MagicDictionary();
magicDictionary.buildDict(["hello", "leetcode"]);
magicDictionary.search("hello"); // return False
magicDictionary.search("hhllo"); // We can change the second 'h' to 'e' to match "hello" so we return True
magicDictionary.search("hell"); // return False
magicDictionary.search("leetcoded"); // return False
 

Constraints:

1 <= dictionary.length <= 100
1 <= dictionary[i].length <= 100
dictionary[i] consists of only lower-case English letters.
All the strings in dictionary are distinct.
1 <= searchWord.length <= 100
searchWord consists of only lower-case English letters.
buildDict will be called only once before search.
At most 100 calls will be made to search.
 */
public class Part_A_B_B_ImplementMagicDictionary {
    /*
     * Runtime: 31 ms, faster than 81.32% of Java online submissions for Implement Magic Dictionary.
Memory Usage: 39.2 MB, less than 97.25% of Java online submissions for Implement Magic Dictionary
     */
    Set<String> hs;
    public Part_A_B_B_ImplementMagicDictionary() {
        hs = new HashSet<>();
    }
    
    public void buildDict(String[] dictionary) {
        
        for (String word: dictionary) {
            hs.add(word);
        }

        
    }
    
    public boolean search(String searchWord) {
        for (String s : hs) {
            if (s.length() == searchWord.length()) {
                int count = 0;
                
                for (int i = 0; i < s.length(); i++) {
                    if (s.charAt(i) != searchWord.charAt(i)) {
                        count++;
                    }
                }
                if (count ==1) {
                    return true;
                }
                
                
            }
        }
        return false;
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
