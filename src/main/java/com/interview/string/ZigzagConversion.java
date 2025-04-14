package com.interview.string;

/*
🔗 Problem Link:
https://leetcode.com/problems/zigzag-conversion/description/

📺 Video:
https://www.youtube.com/watch?v=OO_wrniY8Ts

🗂️ Category:
Medium, Google, FAANG, Tricky

🔷 Problem Description:
The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this:
(Use a fixed-width font for better visualization)

P   A   H   N
A P L S I I G
Y   I   R

Then read line by line: "PAHNAPLSIIGYIR"

Write a method that converts a given string into this zigzag pattern based on the number of rows and returns the result string.

📌 Method Signature:
String convert(String s, int numRows);

🔢 Example 1:
Input: s = "PAYPALISHIRING", numRows = 3
Output: "PAHNAPLSIIGYIR"

🔢 Example 2:
Input: s = "PAYPALISHIRING", numRows = 4
Output: "PINALSIGYAHRPI"

Explanation:
P     I    N
A   L S  I G
Y A   H R
P     I

🔢 Example 3:
Input: s = "A", numRows = 1
Output: "A"

🔒 Constraints:
- 1 <= s.length <= 1000
- s consists of English letters (lower-case and upper-case), ',' and '.'
- 1 <= numRows <= 1000

━━━━━━━━━━━━━━━━━━━━━━
📘 Derived Questions
━━━━━━━━━━━━━━━━━━━━━━

1. ✅ Zigzag Conversion – Print Zigzag Pattern
📌 Task: Print the string in a visual zigzag pattern (not just return the final output).

Example:
Input: s = "PAYPALISHIRING", numRows = 4
Visual Output:
P     I    N
A   L S  I G
Y A   H R
P     I

---

2. ✅ Zigzag Conversion – Fill into 2D Matrix
📌 Task: Return a char[][] matrix where the zigzag pattern is filled. Fill unused cells with '.'.

Example:
Input: s = "PAYPALISHIRING", numRows = 3
Output:
[
 ['P', '.', '.', 'A', '.', '.', 'H', '.', '.', 'N'],
 ['A', '.', 'P', 'L', '.', 'S', 'I', '.', 'I', 'G'],
 ['Y', '.', '.', '.', 'I', '.', '.', '.', 'R', '.']
]

---

3. ✅ Reverse Zigzag – Decode Back to Original
📌 Task: Given the zigzag converted string and number of rows, reconstruct the original string.

Example:
Input: converted = "PAHNAPLSIIGYIR", numRows = 3
Output: "PAYPALISHIRING"

---

4. ✅ Zigzag Path Index Tracking
📌 Task: Instead of returning the zigzag string, return the indices from the original string in the order they appear in the zigzag-converted string.

Example:
Input: s = "PAYPALISHIRING", numRows = 3
Output: [0, 4, 8, 12, 1, 3, 5, 7, 9, 11, 2, 6, 10, 13]

---

5. ✅ Zigzag Kth Character (Efficient Access)
📌 Task: Find the k-th character in the final converted string without generating the entire result.

Example:
Input: s = "PAYPALISHIRING", numRows = 3, k = 5
Output: 'P' (5th character in "PAHNAPLSIIGYIR")

---

6. ✅ Circular Zigzag Conversion
📌 Task: Modify the zigzag traversal to follow a circular path. Like rows in a circle, and bounce between them.

Example:
Input: s = "ABCDEFGHIJK", numRows = 3
Output:
Row 0: A     D     G     J
Row 1: B  C  E  F  H  I  K

---

7. ✅ Diagonal Zigzag in Matrix (Leetcode 498 Style)
📌 Task: Fill a matrix in a zigzag diagonal pattern.

Example:
Input: s = "PAYPALISHIRING", rows = 3, cols = 5
Zigzag diagonals:
P
A Y
P A L
I S
H I
R I
N G
(May need adjustment for matrix bounds)

---

8. ✅ Grouped Zigzag by Cycles
📌 Task: Return the Zigzag string grouped by cycles.

Example:
Input: s = "PAYPALISHIRING", numRows = 3
Output: [["P","A","H","N"], ["A","P","L","S","I","I","G"], ["Y","I","R"]]

━━━━━━━━━━━━━━━━━━━━━━
*/
public class ZigzagConversion {
    /*
        🔁 Step-by-Step Walkthrough

        Input: "PAYPALISHIRING", numRows = 3

        ➤ We create 3 rows (StringBuilders):
            Row 0: ""
            Row 1: ""
            Row 2: ""

        ➤ Traverse each character in the input string:
            - Start appending to Row 0, then Row 1, then Row 2 (go down).
            - At Row 2, reverse direction and go up (Row 1, then Row 0), then reverse again.
            - Repeat this zigzag traversal pattern.

        ➤ Character placement:
            Row 0: P   A   H   N
            Row 1: A P L S I I G
            Row 2: Y   I   R

        ➤ Final step:
            Combine all rows:
            Result = Row0 + Row1 + Row2 = "PAHNAPLSIIGYIR"

        ⏱️ Time Complexity:
        ➤ O(n), where n = s.length()
            - Each character is processed once.
            - Final concatenation of all StringBuilders is also linear.

        📦 Space Complexity:
        ➤ O(n)
            - Total space used by the StringBuilder array to hold all characters.
    */
    public String convert(String s, int numRows) {
        if (numRows == 1 || s.length() <= numRows) {
            return s;
        }

        // Use StringBuilder for efficient appending
        StringBuilder[] rows = new StringBuilder[numRows];
        for (int i = 0; i < numRows; i++) {
            rows[i] = new StringBuilder();
        }

        int i = 0;
        int len = s.length();

        while (i < len) {
            // Go down vertically
            for (int row = 0; row < numRows && i < len; row++) {
                rows[row].append(s.charAt(i++));
            }

            // Go up diagonally (excluding top and bottom rows)
            for (int row = numRows - 2; row > 0 && i < len; row--) {
                rows[row].append(s.charAt(i++));
            }
        }

        // Combine all rows
        StringBuilder result = new StringBuilder();
        for (StringBuilder row : rows) {
            result.append(row);
        }

        return result.toString();
    }
}
