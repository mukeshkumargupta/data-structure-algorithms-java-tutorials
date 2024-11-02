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
    /*
     * Approach 1: Brute Force
     *
     * Steps:
     * - For each word, generate all possible anagram pairs by sorting each word and checking if it matches other words.
     * - Use a nested loop to compare each word with every other word in the list. Identify anagrams by comparing sorted versions of the words.
     *
     * Complexity Analysis:
     * - Time Complexity: O(N^2 * M log M), where N is the number of strings and M is the average length of the strings.
     *   This complexity arises because each word is compared with every other word, and each comparison involves sorting.
     * - Space Complexity: O(N * M), for storing the results and auxiliary data.
     *
     * Explanation:
     * - This approach is inefficient because it involves comparing each word with every other word in the list, leading to a high time complexity.
     * - As a result, it is impractical for large inputs due to the quadratic nature of the comparisons and the additional sorting overhead.
     */

    public static class BruteForceGroupAnagrams {
        public List<List<String>> groupAnagrams(String[] strs) {
            List<List<String>> result = new ArrayList<>();
            boolean[] visited = new boolean[strs.length];

            for (int i = 0; i < strs.length; i++) {
                if (visited[i]) continue;
                List<String> group = new ArrayList<>();
                String sortedWord = sortString(strs[i]);
                group.add(strs[i]);
                visited[i] = true;

                for (int j = i + 1; j < strs.length; j++) {
                    if (!visited[j] && sortedWord.equals(sortString(strs[j]))) {
                        group.add(strs[j]);
                        visited[j] = true;
                    }
                }
                result.add(group);
            }
            return result;
        }

        private String sortString(String s) {
            char[] chars = s.toCharArray();
            Arrays.sort(chars);
            return new String(chars);
        }

        public static void main(String[] args) {
            BruteForceGroupAnagrams ga = new BruteForceGroupAnagrams();

            String[] strs1 = {"eat","tea","tan","ate","nat","bat"};
            System.out.println(ga.groupAnagrams(strs1));

            String[] strs2 = {""};
            System.out.println(ga.groupAnagrams(strs2));

            String[] strs3 = {"a"};
            System.out.println(ga.groupAnagrams(strs3));
        }
    }

    /*
     * Approach 2: Improved Solution with HashMap and Sorted Strings as Keys
     *
     * Steps:
     * - Sort each word's characters and use the sorted word as a key in a hashmap.
     * - Group all strings with the same sorted characters under the same key.
     * - Return the grouped anagrams by converting the hashmap values to a list of lists.
     *
     * Complexity Analysis:
     * - Time Complexity: O(N * M log M), where N is the number of strings and M is the length of each string.
     *   Sorting each string takes O(M log M) time.
     * - Space Complexity: O(N * M), due to storing strings in the hashmap.
     *
     * Explanation:
     * - This approach is significantly faster than the brute force solution. Sorting each string and using it as a key
     *   enables effective grouping of anagrams. However, sorting each string incurs an O(M log M) cost, indicating that
     *   there is room for further optimization.
     *
     * Summary:
     * | Approach            | Time Complexity     | Space Complexity    | Notes                                      |
     * |---------------------|---------------------|---------------------|--------------------------------------------|
     * | Brute Force         | O(N^2 * M log M)    | O(N * M)            | Inefficient for large inputs.              |
     * | HashMap with Sort   | O(N * M log M)      | O(N * M)            | Sorting-based solution with moderate speed.|
     * | Frequency Count     | O(N * M)            | O(N * M)            | Most efficient for large inputs.           |
     *
     * - Using sorted strings as keys is a good balance between simplicity and efficiency, though there are faster methods.
     */

    public static class HashMapGroupAnagrams {
        public List<List<String>> groupAnagrams(String[] strs) {
            Map<String, List<String>> map = new HashMap<>();

            for (String str : strs) {
                String sortedStr = sortString(str); // Sorting to use as a key
                map.computeIfAbsent(sortedStr, k -> new ArrayList<>()).add(str);
            }

            return new ArrayList<>(map.values());
        }

        private String sortString(String s) {
            char[] chars = s.toCharArray();
            Arrays.sort(chars);
            return new String(chars);
        }
    }

    /*
     * Approach 3: Optimized Solution with Character Frequency Count as Keys
     *
     * Steps:
     * - Use a frequency count of characters to uniquely represent each anagram group.
     * - For each word, create a frequency count array of size 26 (one for each lowercase letter) and use it as a key.
     * - Convert this count array to a string (or another unique format) to serve as the key.
     * - Use a HashMap to group anagrams under the same key.
     *
     * Complexity Analysis:
     * - Time Complexity: O(N * M), where N is the number of strings and M is the maximum length of each string.
     *   This is because we are counting characters instead of sorting.
     * - Space Complexity: O(N * M), due to the space required for storing grouped anagrams.
     *
     * Explanation:
     * - By using a frequency count array as the key, this approach avoids the O(M log M) cost of sorting each string,
     *   making it optimal for large datasets.
     *
     * Summary:
     * | Approach            | Time Complexity     | Space Complexity    | Notes                                      |
     * |---------------------|---------------------|---------------------|--------------------------------------------|
     * | Brute Force         | O(N^2 * M log M)    | O(N * M)            | Inefficient for large inputs.              |
     * | HashMap with Sort   | O(N * M log M)      | O(N * M)            | Sorting can be further optimized.          |
     * | Frequency Count     | O(N * M)            | O(N * M)            | Most efficient for large inputs.           |
     *
     * - The frequency count approach is the most optimized for this problem, as it avoids sorting and provides a
     *   clear and efficient way to identify anagram groups.
     */

    public class FrequencyCountGroupAnagrams {
        public List<List<String>> groupAnagrams(String[] strs) {
            Map<String, List<String>> map = new HashMap<>();

            for (String str : strs) {
                int[] charCount = new int[26]; // Count array for each letter
                for (char c : str.toCharArray()) {
                    charCount[c - 'a']++;
                }

                // Convert count array to a unique string key
                StringBuilder keyBuilder = new StringBuilder();
                for (int count : charCount) {
                    keyBuilder.append("#").append(count);
                }
                String key = keyBuilder.toString();

                // Add the original string to the corresponding anagram group
                map.computeIfAbsent(key, k -> new ArrayList<>()).add(str);
            }

            return new ArrayList<>(map.values());
        }
    }


}
