package com.interview.sort;

import java.util.*;
/*
 * https://leetcode.com/problems/sort-characters-by-frequency/
 * Category: Medium, Must Do, Google
 * Related: https://leetcode.com/problems/sort-array-by-increasing-frequency/ Easy
 * Given a string s, sort it in decreasing order based on the frequency of the characters. The frequency of a character is the number of times it appears in the string.

Return the sorted string. If there are multiple answers, return any of them.

 

Example 1:

Input: s = "tree"
Output: "eert"
Explanation: 'e' appears twice while 'r' and 't' both appear once.
So 'e' must appear before both 'r' and 't'. Therefore "eetr" is also a valid answer.
Example 2:

Input: s = "cccaaa"
Output: "aaaccc"
Explanation: Both 'c' and 'a' appear three times, so both "cccaaa" and "aaaccc" are valid answers.
Note that "cacaca" is incorrect, as the same characters must be together.
Example 3:

Input: s = "Aabb"
Output: "bbAa"
Explanation: "bbaA" is also a valid answer, but "Aabb" is incorrect.
Note that 'A' and 'a' are treated as two different characters.
 

Constraints:

1 <= s.length <= 5 * 105
s consists of uppercase and lowercase English letters and digits.
 */
public class SortCharactersByFrequency {
    class Point {
        Character ch;
        int fr;
    }

    public String frequencySort(String s) {
        Map<Character, Integer> lookup = new HashMap<>();
        
        int l = s.length();
        for (int i = 0; i< l ; i++) {
            if (!lookup.containsKey(s.charAt(i))) {
                lookup.put(s.charAt(i), 1);
            } else {
               lookup.put(s.charAt(i), lookup.get(s.charAt(i))+1); 
            }
            
        }
        
        Queue<Point> pq = new PriorityQueue<>((p1, p2) -> {
            return p2.fr - p1.fr;
            
        });
        for (Character ch : lookup.keySet() ) {
            int fr = lookup.get(ch);
            Point p = new Point();
            p.ch = ch;
            p.fr = fr;
            pq.add(p);
            
        }
        
        int count = 0;
        StringBuilder sb = new StringBuilder();
        while (!pq.isEmpty()) {
            Character ch = pq.remove().ch;
            int c = lookup.get(ch);
            while (c-- > 0) {
                sb.append(ch);
            }
        }
        return sb.toString();
        
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
    //Twillio
    public static List<String> groupTransactions(List<String> transactions) {
        // Write your code here
        class Point {
            String word;
            int fr;
        }
        Map<String, Integer> lookup = new HashMap<>();;
        for (String word : transactions) {
            if (!lookup.containsKey(word)) {
                lookup.put(word, 1);
            } else {
               lookup.put(word, lookup.get(word)+1); 
            }
            
        }
        
        Queue<Point> pq = new PriorityQueue<>((p1, p2) -> {
            if (p2.fr == p1.fr) {
                return p1.word.compareTo(p2.word);
            } else {
                return p2.fr - p1.fr;
            }
            
            
        });
        for (String word : lookup.keySet() ) {
            int fr = lookup.get(word);
            Point p = new Point();
            p.word = word;
            p.fr = fr;
            pq.add(p);
            
        }
        
        List<String> result = new ArrayList<>();
        while (!pq.isEmpty()) {
            Point p = pq.remove();
            String word = p.word;
            int fr = p.fr;
            String encode = word + " " + fr;
            result.add(encode);
        }
        return result;

    }
    
}
