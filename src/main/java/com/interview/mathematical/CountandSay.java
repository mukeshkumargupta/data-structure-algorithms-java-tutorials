package com.interview.mathematical;
/*
 * Problem Link: https://leetcode.com/problems/count-and-say/
 * Difficulty: Medium | Leetcode Top150
 * Video Explanation: https://www.youtube.com/watch?v=1YUqtoT9YoE&t=42s
 *
 * Related Problems:
 * - Encode and Decode Strings (https://leetcode.com/problems/encode-and-decode-strings/) - Medium
 * - String Compression (https://leetcode.com/problems/string-compression/) - Medium
 *
 * Problem Description:
 * The "Count and Say" sequence is a sequence of digit strings defined recursively:
 * - countAndSay(1) = "1"
 * - countAndSay(n) is the way you would "say" the digit string from countAndSay(n-1),
 *   which is then converted into a different digit string.
 *
 * To "say" a digit string, group identical contiguous characters, then for each group,
 * say the number of characters followed by the character itself. Concatenate these results
 * to form the new digit string.
 *
 * Example for Digit String "3322251":
 *   - "3322251" would be said as "two 3's, three 2's, one 5, one 1" -> "23321511"
 *
 * Task:
 * Given a positive integer n, return the nth term of the count-and-say sequence.
 *
 * Examples:
 * Example 1:
 * Input: n = 1
 * Output: "1"
 * Explanation: Base case.
 *
 * Example 2:
 * Input: n = 4
 * Output: "1211"
 * Explanation:
 *   - countAndSay(1) = "1"
 *   - countAndSay(2) = say "1" = one 1 = "11"
 *   - countAndSay(3) = say "11" = two 1's = "21"
 *   - countAndSay(4) = say "21" = one 2 + one 1 = "12" + "11" = "1211"
 *
 * Constraints:
 * - 1 <= n <= 30
 *
 * Solution Summary:
 * - This problem is solved by iteratively generating the "Count and Say" sequence up to the nth term.
 * - Starting from the base case, we generate each term by counting contiguous groups of characters in the previous term.
 *
 * Complexity Analysis:
 * - Time Complexity: O(N * M), where N is the value of `n` and M is the maximum length of a term.
 * - Space Complexity: O(M), where M is the space needed to store each term.
 */

public class CountandSay {
    /*
     * To solve the "Count and Say" problem, we need to generate the sequence iteratively,
     * describing each term based on the previous one. Here's a breakdown of the approach
     * and solution in Java, along with its time complexity.
     *
     * Solution Approach:
     * 1. Base Case: Start with countAndSay(1) = "1". This will serve as the initial string,
     *    and each subsequent term will be derived from it.
     *
     * 2. Iterate Up to nth Term:
     *    - For each term from 2 to n, process the previous term by grouping contiguous
     *      identical characters and "saying" how many there are, followed by the character itself.
     *
     * 3. Build the Next Term:
     *    - Use a loop to scan the current string.
     *    - Count each group of consecutive identical characters and then append the count
     *      and character to a new string for the next term.
     *
     * 4. Update the Term:
     *    - Replace the current term with the newly built term after each iteration until
     *      reaching n.
     *
     * Explanation of the Code:
     * - StringBuilder: Used to construct the next term efficiently, as it allows appending
     *   characters with lower overhead compared to using a regular string.
     * - Looping through Characters: For each character in the current term, we count
     *   consecutive characters and then append the count and character to nextTerm.
     * - Final Append: After the inner loop, the last character group is appended, as it
     *   won’t be followed by a different character to trigger an append within the loop.
     *
     * Example Walkthrough:
     * For n = 4:
     * - countAndSay(1) = "1"
     * - countAndSay(2): Say "1" → "11"
     * - countAndSay(3): Say "11" → "21"
     * - countAndSay(4): Say "21" → "1211"
     *
     * Complexity Analysis:
     * - Time Complexity: O(N × M), where N is the input n and M is the maximum length
     *   of any term in the sequence (grows roughly exponentially).
     * - Space Complexity: O(M) for the space needed to store each term in nextTerm.
     *
     * This approach ensures we generate each term efficiently and in a clean manner,
     * suitable for both small and large values of n.
     */

    public String countAndSay(int n) {
        // Start with the base case
        String result = "1";

        // Iterate to build the sequence up to the nth term
        for (int i = 2; i <= n; i++) {
            StringBuilder nextTerm = new StringBuilder();
            int count = 1;

            // Process the current term to generate the next term
            for (int j = 1; j < result.length(); j++) {
                if (result.charAt(j) == result.charAt(j - 1)) {
                    count++;
                } else {
                    nextTerm.append(count).append(result.charAt(j - 1));
                    count = 1;  // Reset count for the new character
                }
            }

            // Append the last group
            nextTerm.append(count).append(result.charAt(result.length() - 1));

            // Update the result for the next iteration
            result = nextTerm.toString();
        }

        return result;
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
