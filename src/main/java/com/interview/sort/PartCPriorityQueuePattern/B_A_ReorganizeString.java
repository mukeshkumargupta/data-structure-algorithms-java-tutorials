package com.interview.sort.PartCPriorityQueuePattern;

import java.util.*;

/*
https://leetcode.com/problems/reorganize-string/description/
Category: Medium, FAANG
Related:
https://leetcode.com/problems/rearrange-string-k-distance-apart/description/ Medium Locked
https://leetcode.com/problems/longest-happy-string/description/ Medium
767. Reorganize String
Medium
Topics
Companies
Hint
Given a string s, rearrange the characters of s so that any two adjacent characters are not the same.

Return any possible rearrangement of s or return "" if not possible.



Example 1:

Input: s = "aab"
Output: "aba"
Example 2:

Input: s = "aaab"
Output: ""


Constraints:

1 <= s.length <= 500
s consists of lowercase English letters.
 */
public class B_A_ReorganizeString {
    /*
    1. **Brute Force Approach (Nested Loops)**

    **Approach**:
    - Generate all permutations of the string.
    - Check each permutation to see if any adjacent characters are the same.
    - Return the first valid permutation.

    ---

    **Time Complexity**:
    - **Time Complexity**: O(N!), because generating all permutations of a string of length N takes O(N!) time.
    - **Space Complexity**: O(N), for storing all permutations.

    ---

    **Dry Run**:

    For the input `"aaab"`, the brute force solution would:

    - **Generate all permutations**:
      - ["aaab", "aaba", "abaa", "baaa"]

    - **Check if any permutation has adjacent characters that are the same**:
      - In the case of `"aaab"`, adjacent 'a's are present, so it is not valid.
      - Continue checking all permutations until we find a valid one, if any.

    This method is simple but inefficient.
    */
    private static class BruitForce {
        public String reorganizeString(String S) {
            List<String> permutations = new ArrayList<>();
            generatePermutations(S, 0, permutations);

            for (String perm : permutations) {
                if (isValid(perm)) {
                    return perm;
                }
            }
            return "";
        }

        // Generate all permutations of the string
        private void generatePermutations(String S, int index, List<String> permutations) {
            if (index == S.length()) {
                permutations.add(S);
                return;
            }

            for (int i = index; i < S.length(); i++) {
                S = swap(S, index, i);
                generatePermutations(S, index + 1, permutations);
                S = swap(S, index, i); // backtrack
            }
        }

        // Swap characters in the string
        private String swap(String S, int i, int j) {
            char[] chars = S.toCharArray();
            char temp = chars[i];
            chars[i] = chars[j];
            chars[j] = temp;
            return new String(chars);
        }

        // Check if a string is valid (no adjacent characters are the same)
        private boolean isValid(String S) {
            for (int i = 1; i < S.length(); i++) {
                if (S.charAt(i) == S.charAt(i - 1)) {
                    return false;
                }
            }
            return true;
        }
    }

/*
    Explanation:

    1. **Frequency Count**:
       - We first count the frequency of each character in the string.

    2. **Max-Heap**:
       - We use a max-heap (priority queue) to always pick the character with the highest frequency.

    3. **Greedy Construction**:
       - We greedily append two characters from the heap to the result string and update their frequencies.
       - We ensure no adjacent characters are the same.

    4. **Final Check**:
       - If the last character can’t be placed without breaking the constraint, we return an empty string.

    Time Complexity:
    - **Time Complexity**: O(N log K), where `N` is the length of the string and `K` is the number of distinct characters (at most 26 for lowercase letters).
    - **Space Complexity**: O(K), for storing character frequencies.

    ---

    ### Dry Run of the Better Approach (Using HashMap and Priority Queue)

    **Input**: `"aabbbc"`

    ---

    **Step 1**: Count frequency of characters using a `HashMap`

    | Character | Frequency |
    |-----------|-----------|
    | 'a'       | 2         |
    | 'b'       | 3         |
    | 'c'       | 1         |

    ---

    **Step 2**: Add characters to the `PriorityQueue` (max-heap) based on frequency.

    After adding characters to the `PriorityQueue`, the characters are sorted by frequency:

    | Character | Frequency |
    |-----------|-----------|
    | 'b'       | 3         |
    | 'a'       | 2         |
    | 'c'       | 1         |

    Priority queue order: `['b', 'a', 'c']`

    ---

    **Step 3**: Build the result string by greedily selecting two characters at a time.

    **First Iteration**:
    - Poll `first = 'b'` (highest frequency) and `second = 'a'`.
    - Append them to the result: `"ba"`.
    - Decrease their frequencies:
      - `'b'` frequency becomes 2 (`'b'` is added back to the heap).
      - `'a'` frequency becomes 1 (`'a'` is added back to the heap).

    **Result so far**: `"ba"`

    | Character | Frequency |
    |-----------|-----------|
    | 'b'       | 2         |
    | 'a'       | 1         |
    | 'c'       | 1         |

    Priority queue order: `['b', 'a', 'c']`

    ---

    **Second Iteration**:
    - Poll `first = 'b'` (highest frequency) and `second = 'a'`.
    - Append them to the result: `"baba"`.
    - Decrease their frequencies:
      - `'b'` frequency becomes 1 (`'b'` is added back to the heap).
      - `'a'` frequency becomes 0 (it is not added back).

    **Result so far**: `"baba"`

    | Character | Frequency |
    |-----------|-----------|
    | 'b'       | 1         |
    | 'c'       | 1         |

    Priority queue order: `['b', 'c']`

    ---

    **Third Iteration**:
    - Poll `first = 'b'` (highest frequency) and `second = 'c'`.
    - Append them to the result: `"bababc"`.
    - Decrease their frequencies:
      - `'b'` frequency becomes 0 (it is not added back).
      - `'c'` frequency becomes 0 (it is not added back).

    **Result so far**: `"bababac"`

    ---

    ### Final Result: `"bababac"`

    Since the last character 'c' was added without violating any constraints, we return `"bababac"`, which is a valid rearranged string.
*/

    private static class Better {
        public String reorganizeString(String S) {
            Map<Character, Integer> freqMap = new HashMap<>();

            // Count the frequency of each character
            for (char c : S.toCharArray()) {
                freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
            }

            // Create a max heap based on frequency
            PriorityQueue<Character> maxHeap = new PriorityQueue<>((a, b) -> freqMap.get(b) - freqMap.get(a));

            // Add all characters to the max heap
            for (char c : freqMap.keySet()) {
                maxHeap.offer(c);
            }

            StringBuilder result = new StringBuilder();

            // Greedily build the result
            while (maxHeap.size() > 1) {
                char first = maxHeap.poll();
                char second = maxHeap.poll();

                result.append(first);
                result.append(second);

                // Decrease the frequency and put them back if they still have occurrences
                if (freqMap.get(first) > 1) {
                    freqMap.put(first, freqMap.get(first) - 1);
                    maxHeap.offer(first);
                }
                if (freqMap.get(second) > 1) {
                    freqMap.put(second, freqMap.get(second) - 1);
                    maxHeap.offer(second);
                }
            }

            // Handle the last character if any
            if (!maxHeap.isEmpty()) {
                char last = maxHeap.poll();
                if (freqMap.get(last) > 1) {
                    return ""; // Not possible to reorganize
                }
                result.append(last);
            }

            return result.toString();
        }
    }

    /*
    Explanation:

    - We count the frequency of each character using an array of size 26 (since only lowercase English letters are involved).
    - We use a priority queue (max-heap) to always pick the character with the highest frequency.
    - We greedily construct the result string, making sure no two adjacent characters are the same.
    - If the remaining character can't be placed without violating the condition, we return an empty string.

    ---

    **Time Complexity**:
    - **Time Complexity**: O(N log 26), which simplifies to O(N), where N is the length of the string.
    - **Space Complexity**: O(1) since the array size is fixed at 26.

    ---

    **Comparison of Time Complexity**:

    | Approach        | Time Complexity | Space Complexity |
    |-----------------|-----------------|------------------|
    | **Brute Force** | O(N!)           | O(N)             |
    | **Better Approach** | O(N log K)  | O(K)             |
    | **Optimal Approach** | O(N)       | O(1)             |

    ---

    The **Optimal Approach** is the most efficient, especially when the string has a large length, because it reduces the time complexity to linear O(N) and uses constant space O(1).
    */
    private static class Optimized {
        public String reorganizeString(String S) {
            int[] count = new int[26];

            // Count frequency of each character
            for (char c : S.toCharArray()) {
                count[c - 'a']++;
            }

            // Max heap to store characters by their frequencies
            PriorityQueue<Character> maxHeap = new PriorityQueue<>((a, b) -> count[b - 'a'] - count[a - 'a']);

            for (char c = 'a'; c <= 'z'; c++) {
                if (count[c - 'a'] > 0) {
                    maxHeap.offer(c);
                }
            }

            StringBuilder result = new StringBuilder();

            while (maxHeap.size() > 1) {
                char first = maxHeap.poll();
                char second = maxHeap.poll();

                result.append(first);
                result.append(second);

                // Decrease the count and reinsert into the heap if necessary
                if (--count[first - 'a'] > 0) {
                    maxHeap.offer(first);
                }
                if (--count[second - 'a'] > 0) {
                    maxHeap.offer(second);
                }
            }

            // Handle the last character if any
            if (!maxHeap.isEmpty()) {
                char last = maxHeap.poll();
                if (count[last - 'a'] > 1) {
                    return ""; // It's not possible to reorganize
                }
                result.append(last);
            }

            return result.toString();
        }

    }
}
