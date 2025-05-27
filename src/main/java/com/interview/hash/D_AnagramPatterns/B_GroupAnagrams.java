package com.interview.hash.D_AnagramPatterns;

import java.util.*;
/*
 * https://leetcode.com/problems/group-anagrams/
 * 🎥 Video: https://www.youtube.com/watch?v=0I6IL3TnIZs
 *
 * Category: Medium, Must Do, Google, Top150
 * Related:
 * 🔗 https://leetcode.com/problems/group-shifted-strings/ (Locked)
 * 🔗 https://leetcode.com/problems/valid-anagram/ (Easy)
 * 🔗 https://leetcode.com/problems/find-resultant-array-after-removing-anagrams/ (Easy)
 * 🔗 https://leetcode.com/problems/count-anagrams/ (Hard)
 *
 * 📝 Problem Statement:
 * Given an array of strings `strs`, group the anagrams together. You can return the answer in any order.
 *
 * An Anagram is a word formed by rearranging the letters of another word using all original letters exactly once.
 *
 * 🧠 Intuition:
 * All anagrams share the same character frequency. So, strings with identical character frequencies belong to the same group.
 *
 * ✅ Constraints:
 * 1 <= strs.length <= 10^4
 * 0 <= strs[i].length <= 100
 * strs[i] consists of lowercase English letters.
 *
 * 🧪 Example 1:
 * Input: strs = ["eat","tea","tan","ate","nat","bat"]
 * Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
 *
 * 🧪 Example 2:
 * Input: strs = [""]
 * Output: [[""]]
 *
 * 🧪 Example 3:
 * Input: strs = ["a"]
 * Output: [["a"]]
 */

/*
 * ✅ Optimal Approach: Character Count Hashing
 * Time Complexity: O(N * K), where N is number of strings, and K is max length of a string
 * Space Complexity: O(N * K) to store the result and keys
 */

public class B_GroupAnagrams {
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
                int[] freq = new int[26];
                for (char c : str.toCharArray()) {
                    freq[c - 'a']++;
                }

                // Convert frequency array to string key like "1#0#0#0#..." to avoid ambiguity
                StringBuilder sb = new StringBuilder();
                for (int f : freq) {
                    sb.append(f).append('#');
                }
                String key = sb.toString();

                map.computeIfAbsent(key, k -> new ArrayList<>()).add(str);
            }

            return new ArrayList<>(map.values());
        }
    }


}
