package com.interview.sliddingwindow;

/*
https://leetcode.com/problems/string-compression/description/?envType=study-plan-v2&envId=leetcode-75
Category: Medium, top75
Related:
https://leetcode.com/problems/count-and-say/ Medium
https://leetcode.com/problems/encode-and-decode-strings/ Medium
https://leetcode.com/problems/design-compressed-string-iterator/ Easy
https://leetcode.com/problems/decompress-run-length-encoded-list/ Easy
https://leetcode.com/problems/string-compression-iii/ Medium
https://leetcode.com/problems/better-compression-of-string/ Medium
Given an array of characters chars, compress it using the following algorithm:

Begin with an empty string s. For each group of consecutive repeating characters in chars:

If the group's length is 1, append the character to s.
Otherwise, append the character followed by the group's length.
The compressed string s should not be returned separately, but instead, be stored in the input character array chars. Note that group lengths that are 10 or longer will be split into multiple characters in chars.

After you are done modifying the input array, return the new length of the array.

You must write an algorithm that uses only constant extra space.



Example 1:

Input: chars = ["a","a","b","b","c","c","c"]
Output: Return 6, and the first 6 characters of the input array should be: ["a","2","b","2","c","3"]
Explanation: The groups are "aa", "bb", and "ccc". This compresses to "a2b2c3".
Example 2:

Input: chars = ["a"]
Output: Return 1, and the first character of the input array should be: ["a"]
Explanation: The only group is "a", which remains uncompressed since it's a single character.
Example 3:

Input: chars = ["a","b","b","b","b","b","b","b","b","b","b","b","b"]
Output: Return 4, and the first 4 characters of the input array should be: ["a","b","1","2"].
Explanation: The groups are "a" and "bbbbbbbbbbbb". This compresses to "ab12".


Constraints:

1 <= chars.length <= 2000
chars[i] is a lowercase English letter, uppercase English letter, digit, or symbol.
 */

public class StringCompression {
    /*
        ⏱ Time Complexity

        Let n be the length of the input array chars.

        - We iterate through the array once with `readIndex` → O(n)
        - For each group of characters, we may write a few digits of count → still O(n) in total

        ✅ Final Time Complexity: O(n)

        🧠 Space Complexity

        - We use a constant number of variables and no extra space proportional to input size.

        ✅ Final Space Complexity: O(1)
    */
    public int compress(char[] chars) {
        int writeIndex = 0; // Index to write compressed characters
        int readIndex = 0;  // Index to read characters

        while (readIndex < chars.length) {
            char currentChar = chars[readIndex];
            int count = 0;

            // Count how many times the current character repeats
            while (readIndex < chars.length && chars[readIndex] == currentChar) {
                readIndex++;
                count++;
            }

            // Write the character
            chars[writeIndex++] = currentChar;

            // If count > 1, write the count digits
            if (count > 1) {
                String countStr = String.valueOf(count);
                for (char digit : countStr.toCharArray()) {
                    chars[writeIndex++] = digit;
                }
            }
        }

        return writeIndex; // New length of the compressed array
    }

/*
📚 Derived Questions:

🔁 In-Place Array Modification
These problems test your ability to manipulate arrays without extra space.

- Remove Duplicates from Sorted Array
  https://leetcode.com/problems/remove-duplicates-from-sorted-array/

- Remove Element
  https://leetcode.com/problems/remove-element/

- Move Zeroes
  https://leetcode.com/problems/move-zeroes/

- Merge Sorted Array
  https://leetcode.com/problems/merge-sorted-array/

- Sort Colors (Dutch National Flag Problem)
  https://leetcode.com/problems/sort-colors/


🔠 String Manipulation / Compression
Problems involving transformation or compression of strings, similar to run-length encoding.

- Encode and Decode Strings (Design Problem)
  https://leetcode.com/problems/encode-and-decode-strings/

- Run-Length Encoding (RLE)
  Custom implementation — compress repeated characters into char+count.

- Decompress Run-Length Encoded List
  https://leetcode.com/problems/decompress-run-length-encoded-list/

- Count and Say
  https://leetcode.com/problems/count-and-say/

- Rearrange or Group Characters
  Group characters in blocks of k
  Group anagram-related problems


✂️ String Splitting & Grouping
Focuses on encoding or grouping logic in strings.

- Split a String in Balanced Strings
  https://leetcode.com/problems/split-a-string-in-balanced-strings/

- Divide a String Into Groups of Size k
  https://leetcode.com/problems/divide-a-string-into-groups-of-size-k/

- Split Message Based on Limit (Tricky)
  https://leetcode.com/problems/split-message-based-on-limit/


🧠 Tricky / Edge-case Focused
Careful handling of indices and logic boundaries.

- Text Justification
  https://leetcode.com/problems/text-justification/

- Rearrange Spaces Between Words
  https://leetcode.com/problems/rearrange-spaces-between-words/

- Remove All Adjacent Duplicates in String II
  https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string-ii/


💡 Bonus: Design Problems

- Custom Compressor / Decompressor
  Design a system that compresses data using RLE, Huffman Coding, etc.

- Google Docs / Text Editor Mini Design
  Simulate features like text justification, grouping, and compression — commonly used in design interviews.
*/
}
