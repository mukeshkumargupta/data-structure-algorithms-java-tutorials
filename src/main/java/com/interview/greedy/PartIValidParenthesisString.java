package com.interview.greedy;

/*
https://www.youtube.com/watch?v=cHT6sG_hUZI&list=PLgUwDviBIf0rF1w2Koyh78zafB0cz7tea&index=11
https://leetcode.com/problems/valid-parenthesis-string/description/
Category: Medium, VVImp (Optimization question)
Related:
https://leetcode.com/problems/special-binary-string/description/ Hard
https://leetcode.com/problems/check-if-a-parentheses-string-can-be-valid/description/ Medium
 */
public class PartIValidParenthesisString {
    /*

        Problem Statement
        The string consists of three types of characters: '(', ')', and '*'. We need to determine if the string is valid:

        '(' represents an opening parenthesis.
        ')' represents a closing parenthesis.
        '*' can represent either an opening parenthesis '(', a closing parenthesis ')', or an empty string ''.
        Goal
        Return true if the string is valid, otherwise return false.

        Step 1: Recursive Approach
        We can solve this problem using recursion by considering each possibility of * (either as (, ) or empty ""). Here's how we would implement the recursive solution:

        Recursive Code
        Explanation:
        Base Case: If we have processed the entire string, the string is valid if openCount == 0 (i.e., all opened parentheses are matched).
        If the current character is '(', we increase the openCount.
        If it is ')', we decrease the openCount.
        If it is '*', we branch into three recursive calls, treating '*' as (, ), or empty.
        This approach has a time complexity of
        𝑂
        (
        3
        𝑛
        )
        O(3
        n
         ) because each '*' leads to three recursive branches. ( 3 to power n)
     */

    public boolean checkValidString(String s) {
        return isValidRecursive(s, 0, 0);
    }

    private boolean isValidRecursive(String s, int index, int openCount) {
        // Base case: If we have processed all characters
        if (index == s.length()) {
            return openCount == 0;
        }

        // If openCount becomes negative, it means there are more ')' than '('
        if (openCount < 0) {
            return false;
        }

        char currentChar = s.charAt(index);

        // Recur for the next character based on the current character
        if (currentChar == '(') {
            // Increase openCount for '('
            return isValidRecursive(s, index + 1, openCount + 1);
        } else if (currentChar == ')') {
            // Decrease openCount for ')'
            return isValidRecursive(s, index + 1, openCount - 1);
        } else {
            // If currentChar is '*', try all three possibilities: '(', ')', or empty
            return isValidRecursive(s, index + 1, openCount + 1) ||   // '*' becomes '('
                    isValidRecursive(s, index + 1, openCount - 1) ||   // '*' becomes ')'
                    isValidRecursive(s, index + 1, openCount);         // '*' becomes ''
        }
    }
    /*
    Step 2: Memoization (Top-down DP)
    To optimize the recursive solution, we can store results of overlapping subproblems in a 2D array (memoization table). Here's how we do it:

    Explanation:
    Memoization Table: We store the result for each pair of index and openCount to avoid redundant calculations.
    If a subproblem has already been computed, we return the stored result.
    This approach improves the time complexity to
    𝑂
    (
    𝑛
    2
    )
    O(n
    2
     ), where n is the length of the string.
     */


    public boolean checkValidStringMemoization(String s) {
        // Use memoization to store results
        Boolean[][] memo = new Boolean[s.length()][s.length() + 1]; // memo[index][openCount]
        return isValidMemoization(s, 0, 0, memo);
    }

    private boolean isValidMemoization(String s, int index, int openCount, Boolean[][] memo) {
        int n = s.length();

        // Base case: If we've processed all characters, openCount should be 0
        if (index == n) {
            return openCount == 0;
        }

        // If openCount is negative or exceeds n, it's invalid
        if (openCount < 0 || openCount > n) {
            return false;
        }

        // Check memo table for previously computed result
        if (memo[index][openCount] != null) {
            return memo[index][openCount];
        }

        char currentChar = s.charAt(index);

        boolean result = false;
        if (currentChar == '(') {
            // Increment openCount for '('
            result = isValidMemoization(s, index + 1, openCount + 1, memo);
        } else if (currentChar == ')') {
            // Decrement openCount for ')'
            result = isValidMemoization(s, index + 1, openCount - 1, memo);
        } else {
            // If currentChar is '*', try all three possibilities: '(', ')', or empty
            result = isValidMemoization(s, index + 1, openCount + 1, memo) ||  // '*' as '('
                    isValidMemoization(s, index + 1, openCount - 1, memo) ||  // '*' as ')'
                    isValidMemoization(s, index + 1, openCount, memo);        // '*' as ''
        }

        // Store result in memo table
        memo[index][openCount] = result;
        return result;
    }

    /*
    Step 3: Optimal Solution (Greedy Approach)
        We can solve this problem in
        𝑂
        (
        𝑛
        )
        O(n) time using a greedy approach by keeping track of the possible range of open parentheses.

        We maintain two variables: low and high:
        low: The lowest possible number of open parentheses.
        high: The highest possible number of open parentheses.
        For each character in the string:

        If it is '(', both low and high increase by 1.
        If it is ')', both low and high decrease by 1.
        If it is '*', low decreases by 1 (assuming '*' is ')'), and high increases by 1 (assuming '*' is '(').
        At any point, if high becomes negative, we return false. At the end, low must be 0 for the string to be valid.

        Explanation:
            Greedy: low tracks the minimum possible open parentheses, and high tracks the maximum. We ensure that the range of possible open parentheses is valid throughout the traversal.
            The time complexity is
            𝑂
            (
            𝑛
            )
            O(n) because we only traverse the string once.

Thank you for the clarification! The question is about solving the "Valid Parenthesis String" problem (LeetCode 678), which involves checking whether a given string containing parentheses (, ), and * is valid.

Problem Statement
The string consists of three types of characters: '(', ')', and '*'. We need to determine if the string is valid:

'(' represents an opening parenthesis.
')' represents a closing parenthesis.
'*' can represent either an opening parenthesis '(', a closing parenthesis ')', or an empty string ''.
Goal
Return true if the string is valid, otherwise return false.

Step 1: Recursive Approach
We can solve this problem using recursion by considering each possibility of * (either as (, ) or empty ""). Here's how we would implement the recursive solution:

Recursive Code
java
Copy code
public class Solution {
    public boolean checkValidString(String s) {
        return isValid(s, 0, 0);
    }

    private boolean isValid(String s, int index, int openCount) {
        // Base case: If we have processed all characters
        if (index == s.length()) {
            return openCount == 0;
        }

        // If openCount becomes negative, it means there are more ')' than '('
        if (openCount < 0) {
            return false;
        }

        char currentChar = s.charAt(index);

        // Recur for the next character based on the current character
        if (currentChar == '(') {
            // Increase openCount for '('
            return isValid(s, index + 1, openCount + 1);
        } else if (currentChar == ')') {
            // Decrease openCount for ')'
            return isValid(s, index + 1, openCount - 1);
        } else {
            // If currentChar is '*', try all three possibilities: '(', ')', or empty
            return isValid(s, index + 1, openCount + 1) ||   // '*' becomes '('
                   isValid(s, index + 1, openCount - 1) ||   // '*' becomes ')'
                   isValid(s, index + 1, openCount);         // '*' becomes ''
        }
    }
}
Explanation:
Base Case: If we have processed the entire string, the string is valid if openCount == 0 (i.e., all opened parentheses are matched).
If the current character is '(', we increase the openCount.
If it is ')', we decrease the openCount.
If it is '*', we branch into three recursive calls, treating '*' as (, ), or empty.
This approach has a time complexity of
𝑂
(
3
𝑛
)
O(3
n
 ) because each '*' leads to three recursive branches.

Step 2: Memoization (Top-down DP)
To optimize the recursive solution, we can store results of overlapping subproblems in a 2D array (memoization table). Here's how we do it:

java
Copy code
public class Solution {
    public boolean checkValidString(String s) {
        int[][] memo = new int[s.length()][s.length() + 1];  // memo[i][openCount]
        return isValid(s, 0, 0, memo);
    }

    private boolean isValid(String s, int index, int openCount, int[][] memo) {
        // Base case
        if (index == s.length()) {
            return openCount == 0;
        }

        // If openCount becomes negative, return false
        if (openCount < 0) {
            return false;
        }

        // Check memoization table
        if (memo[index][openCount] != 0) {
            return memo[index][openCount] == 1;
        }

        char currentChar = s.charAt(index);

        boolean result;
        if (currentChar == '(') {
            result = isValid(s, index + 1, openCount + 1, memo);
        } else if (currentChar == ')') {
            result = isValid(s, index + 1, openCount - 1, memo);
        } else {
            result = isValid(s, index + 1, openCount + 1, memo) ||    // '*' becomes '('
                     isValid(s, index + 1, openCount - 1, memo) ||    // '*' becomes ')'
                     isValid(s, index + 1, openCount, memo);          // '*' becomes ''
        }

        // Store result in memo table
        memo[index][openCount] = result ? 1 : -1;
        return result;
    }
}
Explanation:
Memoization Table: We store the result for each pair of index and openCount to avoid redundant calculations.
If a subproblem has already been computed, we return the stored result.
This approach improves the time complexity to
𝑂
(
𝑛
2
)
O(n
2
 ), where n is the length of the string.

Step 3: Optimal Solution (Greedy Approach)
We can solve this problem in
𝑂
(
𝑛
)
O(n) time using a greedy approach by keeping track of the possible range of open parentheses.

We maintain two variables: low and high:
low: The lowest possible number of open parentheses.
high: The highest possible number of open parentheses.
For each character in the string:

If it is '(', both low and high increase by 1.
If it is ')', both low and high decrease by 1.
If it is '*', low decreases by 1 (assuming '*' is ')'), and high increases by 1 (assuming '*' is '(').
At any point, if high becomes negative, we return false. At the end, low must be 0 for the string to be valid.

Optimal Code
java
Copy code
public class Solution {
    public boolean checkValidString(String s) {
        int low = 0, high = 0;

        for (char c : s.toCharArray()) {
            if (c == '(') {
                low++;
                high++;
            } else if (c == ')') {
                low = Math.max(low - 1, 0);  // low can't go below 0
                high--;
            } else {  // c == '*'
                low = Math.max(low - 1, 0);  // '*' as ')'
                high++;                      // '*' as '('
            }

            if (high < 0) {
                return false;  // too many closing parentheses
            }
        }

        return low == 0;
    }
}
Explanation:
Greedy: low tracks the minimum possible open parentheses, and high tracks the maximum. We ensure that the range of possible open parentheses is valid throughout the traversal.
The time complexity is
𝑂
(
𝑛
)
O(n) because we only traverse the string once.
Conclusion
Recursive Approach: Brute force with
𝑂
(
3
𝑛
)
O(3
n
 ) time complexity.
Memoization: Optimized recursive approach with
𝑂
(
𝑛
2
)
O(n
2
 ) time complexity.
Greedy Approach: Optimal
𝑂
(
𝑛
)
O(n) time solution using a range-based greedy method.
This step-by-step breakdown leads to an efficient solution while maintaining clarity about how the problem can be optimized at each stage.

     */

    public boolean checkValidStringOptimal(String s) {
        int low = 0, high = 0;

        for (char c : s.toCharArray()) {
            if (c == '(') {
                low++;
                high++;
            } else if (c == ')') {
                low = Math.max(low - 1, 0);  // low can't go below 0
                high--;
            } else {  // c == '*'
                low = Math.max(low - 1, 0);  // '*' as ')'
                high++;                      // '*' as '('
            }

            if (high < 0) {
                return false;  // too many closing parentheses
            }
        }

        return low == 0;
    }
}
