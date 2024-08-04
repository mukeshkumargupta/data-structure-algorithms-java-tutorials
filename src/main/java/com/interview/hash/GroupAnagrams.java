package com.interview.hash;

import java.util.*;
/*
 * https://leetcode.com/problems/group-anagrams/
 * https://www.youtube.com/watch?v=0I6IL3TnIZs
 * 
 * Category: Medium, Must Do, Google, Top150
 * Related: https://leetcode.com/problems/group-shifted-strings/ Locked
 * Given an array of strings strs, group the anagrams together. You can return the answer in any order.

An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.

 

Example 1:

Input: strs = ["eat","tea","tan","ate","nat","bat"]
Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
Example 2:

Input: strs = [""]
Output: [[""]]
Example 3:

Input: strs = ["a"]
Output: [["a"]]
 

Constraints:

1 <= strs.length <= 104
0 <= strs[i].length <= 100
strs[i] consists of lowercase English letters.
 */
/*
Approaches
To solve the problem of grouping anagrams, we need to understand that anagrams are words or phrases formed by rearranging the letters of another word or phrase. Thus, all anagrams of a word will have the same characters in the same frequency.

Let's go through the thought process from a brute-force approach to an optimal solution.

Brute-Force Approach
A brute-force approach would involve comparing every string with every other string to check if they are anagrams. This involves:

Generating all possible permutations of each string.
Checking if any other string matches any of these permutations.
This approach is highly inefficient because generating permutations is factorial in time complexity (O(n!)). Given the constraints (up to 10^4 strings, each up to 100 characters long), this is impractical.

Improved Approach: Sorting
A more efficient approach is to use sorting. The idea is that two anagrams, when sorted, will result in the same string. For example, "eat" and "tea" both become "aet" when sorted.

Steps:

Sort each string.
Use a hash map to group strings that, when sorted, are identical.
This approach is much more efficient. Sorting each string takes O(k log k), where k is the length of the string. Given n strings, the total complexity becomes O(n k log k).

Optimal Approach: Character Count
Another optimal approach involves using character counts. Instead of sorting, we can count the frequency of each character in the string. Two strings are anagrams if their character counts are identical.

Steps:

Count the frequency of each character in each string.
Use a hash map to group strings with identical character counts.
This avoids the overhead of sorting and can be done in linear time relative to the length of the string.

Final Solution
Here’s the code implementation using the character count method:

Explanation
Initialization:

A map to store the grouped anagrams. The key is a string representation of the character count, and the value is a list of strings that match that count.
Character Count:

For each string, count the occurrences of each character using an array of size 26 (since there are 26 lowercase English letters).
Convert this count array to a string key. This ensures that strings with the same character counts will have identical keys.
Grouping:

Use the map to group strings with the same character count key.
Output:

Convert the map values to a list of lists.
This solution is efficient and handles the constraints well, making it suitable for large inputs

The # character is used as a delimiter in the string representation of the character count array. This ensures that the counts are uniquely identifiable when converting the array to a string. Without a delimiter, certain combinations of counts could produce ambiguous keys. Let's break down the importance and use of #.

Problem with Ambiguous Keys
Consider the case where two different character counts produce the same string key without a delimiter. For example:

Suppose the count array is [12, 3, 0, 0, ..., 0] (representing 12 'a's, 3 'b's, and 0 of other characters).
Another count array is [1, 23, 0, 0, ..., 0] (representing 1 'a', 23 'b's, and 0 of other characters).
If we simply concatenate the counts into a string, both arrays would produce the string "123000...0", which is ambiguous.

Solution with Delimiter
By using a delimiter like #, we can ensure that each count is distinctly separated, thus avoiding ambiguity:

The first count array [12, 3, 0, 0, ..., 0] would produce the string "#12#3#0#0#...#0".
The second count array [1, 23, 0, 0, ..., 0] would produce the string "#1#23#0#0#...#0".
These two strings are now clearly distinguishable from each other.
 */
public class GroupAnagrams {
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null || strs.length == 0) return new ArrayList<>();

        Map<String, List<String>> map = new HashMap<>();
        for (String s : strs) {
            int[] count = new int[26];  // Since the problem states that strs[i] consists of lowercase English letters
            for (char c : s.toCharArray()) {
                count[c - 'a']++;
            }
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 26; i++) {
                sb.append('#');
                sb.append(count[i]);
            }
            String key = sb.toString();
            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<>());
            }
            map.get(key).add(s);
        }
        return new ArrayList<>(map.values());
    }

    public static void main(String[] args) {
        GroupAnagrams ga = new GroupAnagrams();

        String[] strs1 = {"eat","tea","tan","ate","nat","bat"};
        System.out.println(ga.groupAnagrams(strs1));

        String[] strs2 = {""};
        System.out.println(ga.groupAnagrams(strs2));

        String[] strs3 = {"a"};
        System.out.println(ga.groupAnagrams(strs3));
    }
}
