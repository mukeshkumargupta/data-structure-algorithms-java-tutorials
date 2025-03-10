package com.interview.dynamic;

import java.util.Arrays;

public class PartGDPOnStrings {
    /*
     * https://leetcode.com/problems/longest-common-subsequence/
     * Category: Medium, Must Do
     * Reference: Takeyouforward
     * Related: https://leetcode.com/problems/delete-operation-for-two-strings/
     * Given two strings text1 and text2, return the length of their longest common subsequence. If there is no common subsequence, return 0.

     A subsequence of a string is a new string generated from the original string with some characters (can be none) deleted without changing the relative order of the remaining characters.

     For example, "ace" is a subsequence of "abcde".
     A common subsequence of two strings is a subsequence that is common to both strings.



     Example 1:

     Input: text1 = "abcde", text2 = "ace"
     Output: 3
     Explanation: The longest common subsequence is "ace" and its length is 3.
     Example 2:

     Input: text1 = "abc", text2 = "abc"
     Output: 3
     Explanation: The longest common subsequence is "abc" and its length is 3.
     Example 3:

     Input: text1 = "abc", text2 = "def"
     Output: 0
     Explanation: There is no such common subsequence, so the result is 0.


     Constraints:

     1 <= text1.length, text2.length <= 1000
     text1 and text2 consist of only lowercase English characters.
     */
    /*
    https://www.youtube.com/watch?v=NPZn9jBrX8U&list=PLgUwDviBIf0qUlt5H_kiKYaNSqJ81PMMY&index=26
    https://www.naukri.com/code360/problems/longest-common-subsequence_624879?source=youtube&campaign=striver_dp_videos&utm_source=youtube&utm_medium=affiliate&utm_campaign=striver_dp_videos
     */
    public static class LongestCommonSubsequence {
        /*
        Time Complexity: O(N*M)

Reason: There are N*M states therefore at max ‘N*M’ new problems will be solved.

Space Complexity: O(N*M) + O(N+M)

Reason: We are using an auxiliary recursion stack space(O(N+M)) (see the recursive tree, in the worst case, we will go till N+M calls at a time) and a 2D array ( O(N*M)).
         */
        // Recursive function to find the length of the Longest Common Subsequence (LCS)
        static int lcsUtil(String s1, String s2, int ind1, int ind2, int[][] dp) {
            // Base case: If either of the strings reaches the end, return 0
            if (ind1 < 0 || ind2 < 0)
                return 0;

            // If the result for this subproblem has already been calculated, return it
            if (dp[ind1][ind2] != -1)
                return dp[ind1][ind2];

            // If the characters at the current indices are the same, increment the LCS length
            if (s1.charAt(ind1) == s2.charAt(ind2))
                return dp[ind1][ind2] = 1 + lcsUtil(s1, s2, ind1 - 1, ind2 - 1, dp);

                // If the characters are different, choose the maximum LCS length by either
                // skipping a character in s1 or skipping a character in s2
            else
                return dp[ind1][ind2] = Math.max(lcsUtil(s1, s2, ind1, ind2 - 1, dp),
                        lcsUtil(s1, s2, ind1 - 1, ind2, dp));
        }

        // Function to find the length of the Longest Common Subsequence (LCS)
        static int lcs(String s1, String s2) {
            int n = s1.length();
            int m = s2.length();

            // Create a 2D array to store results of subproblems
            int dp[][] = new int[n][m];

            // Initialize the dp array with -1 to indicate that subproblems are not solved yet
            for (int rows[] : dp)
                Arrays.fill(rows, -1);

            // Call the recursive function to find the LCS length
            return lcsUtil(s1, s2, n - 1, m - 1, dp);
        }


        // Recursive function to find the length of the Longest Common Subsequence (LCS)
        static int lcsUtilIncreaseIndex(String s1, String s2, int ind1, int ind2, int[][] dp) {
            // Base case: If either of the strings reaches the end, return 0
            if (ind1 == 0 || ind2 == 0)
                return 0;

            // If the result for this subproblem has already been calculated, return it
            if (dp[ind1][ind2] != -1)
                return dp[ind1][ind2];

            // If the characters at the current indices are the same, increment the LCS length
            if (s1.charAt(ind1-1) == s2.charAt(ind2-1))
                return dp[ind1][ind2] = 1 + lcsUtil(s1, s2, ind1 - 1, ind2 - 1, dp);

                // If the characters are different, choose the maximum LCS length by either
                // skipping a character in s1 or skipping a character in s2
            else
                return dp[ind1][ind2] = Math.max(lcsUtil(s1, s2, ind1, ind2 - 1, dp),
                        lcsUtil(s1, s2, ind1 - 1, ind2, dp));
        }

        // Function to find the length of the Longest Common Subsequence (LCS)
        static int lcsIncreaseIndex(String s1, String s2) {
            int n = s1.length();
            int m = s2.length();

            // Create a 2D array to store results of subproblems
            int dp[][] = new int[n+1][m+1];

            // Initialize the dp array with -1 to indicate that subproblems are not solved yet
            for (int rows[] : dp)
                Arrays.fill(rows, -1);

            // Call the recursive function to find the LCS length
            return lcsUtil(s1, s2, n , m , dp);
        }

        /*
                    Complexity Analysis
            Time Complexity: O(N*M)

            Reason: There are two nested loops

            Space Complexity: O(N*M)

            Reason: We are using an external array of size ‘N*M)’. Stack Space is eliminated.
         */

        // Function to find the length of the Longest Common Subsequence (LCS)
        static int lcsTabulation(String s1, String s2) {
            int n = s1.length();
            int m = s2.length();

            // Create a 2D array to store results of subproblems
            int dp[][] = new int[n + 1][m + 1];

            // Initialize the dp array with -1 to indicate that subproblems are not solved yet
            for (int rows[] : dp)
                Arrays.fill(rows, -1);

            // Initialize the first row and first column with zeros since LCS with an empty string is zero
            for (int i = 0; i <= n; i++) {
                dp[i][0] = 0;
            }
            for (int i = 0; i <= m; i++) {
                dp[0][i] = 0;
            }

            // Fill the dp array using dynamic programming
            for (int ind1 = 1; ind1 <= n; ind1++) {
                for (int ind2 = 1; ind2 <= m; ind2++) {
                    // If the characters at the current indices are the same, increment the LCS length
                    if (s1.charAt(ind1 - 1) == s2.charAt(ind2 - 1))
                        dp[ind1][ind2] = 1 + dp[ind1 - 1][ind2 - 1];
                        // If the characters are different, choose the maximum LCS length by either
                        // excluding a character in s1 or excluding a character in s2
                    else
                        dp[ind1][ind2] = Math.max(dp[ind1 - 1][ind2], dp[ind1][ind2 - 1]);
                }
            }

            return dp[n][m]; // Return the length of the Longest Common Subsequence (LCS)
        }

        static int lcsSpaceOptimization(String s1, String s2) {
            int n = s1.length();
            int m = s2.length();

            // Create a 2D array to store results of subproblems
            int[] prev = new int [m + 1];;
            int[] cur = new int [m + 1];

            // Fill the dp array using dynamic programming
            for (int ind1 = 1; ind1 <= n; ind1++) {
                for (int ind2 = 1; ind2 <= m; ind2++) {
                    // If the characters at the current indices are the same, increment the LCS length
                    if (s1.charAt(ind1 - 1) == s2.charAt(ind2 - 1))
                        cur[ind2] = 1 + prev[ind2 - 1];
                        // If the characters are different, choose the maximum LCS length by either
                        // excluding a character in s1 or excluding a character in s2
                    else
                        cur[ind2] = Math.max(prev[ind2], cur[ind2 - 1]);
                }
                prev = cur.clone();
            }

            return prev[m]; // Return the length of the Longest Common Subsequence (LCS)
        }

        /*
        https://www.youtube.com/watch?v=-zI4mrF2Pb4&list=PLgUwDviBIf0qUlt5H_kiKYaNSqJ81PMMY&index=27
         */
        public static class PrintLongestCommonSubsequence {
            static void lcs(String s1, String s2) {

                int n=s1.length();
                int m=s2.length();

                int dp[][]=new int[n+1][m+1];
                for(int i=0;i<=n;i++){
                    dp[i][0] = 0;
                }
                for(int i=0;i<=m;i++){
                    dp[0][i] = 0;
                }

                for(int ind1=1;ind1<=n;ind1++){
                    for(int ind2=1;ind2<=m;ind2++){
                        if(s1.charAt(ind1-1)==s2.charAt(ind2-1))
                            dp[ind1][ind2] = 1 + dp[ind1-1][ind2-1];
                        else
                            dp[ind1][ind2] = 0 + Math.max(dp[ind1-1][ind2],dp[ind1][ind2-1]);
                    }
                }

                int len=dp[n][m];
                int i=n;
                int j=m;

                int index = len-1;
                String ans="";
                StringBuilder ss= new StringBuilder(s1);
                while(i>0 && j>0){
                    if(s1.charAt(i-1) == s2.charAt(j-1)){
                        ans = ss.charAt(i-1) + ans;
                        index--;
                        i--;
                        j--;
                    }
                    else if(ss.charAt(i-1)>s2.charAt(j-1)){
                        i--;
                    }
                    else j--;
                }
                System.out.println(ans);
            }

            public static void main(String args[]) {

                String s1= "abcde";
                String s2= "bdgek";

                System.out.print("The Longest Common Subsequence is ");
                lcs(s1,s2);
            }
        }

        public static void main(String args[]) {
            String s1 = "acd";
            String s2 = "ced";

            // Call the lcs function and print the result
            System.out.println("The Length of Longest Common Subsequence is " + lcs(s1, s2));
        }
    }

    public static class LongestCommonSubstring {
        /*
            Time Complexity: O(N*M)

            Reason: There are two nested loops

            Space Complexity: O(N*M)

            Reason: We are using an external array of size ‘N*M)’. Stack Space is eliminated.
         */
        // Function to find the length of the Longest Common Substring (LCS)
        static int lcs(String s1, String s2) {
            int n = s1.length();
            int m = s2.length();

            // Create a 2D array to store LCS lengths
            int[][] dp = new int[n + 1][m + 1];
            int ans = 0; // Initialize a variable to store the maximum LCS length

            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= m; j++) {
                    // If the characters at the current indices are the same, extend the LCS
                    if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                        int val = 1 + dp[i - 1][j - 1];
                        dp[i][j] = val;
                        ans = Math.max(ans, val); // Update the maximum LCS length
                    } else {
                        dp[i][j] = 0; // Reset LCS length if characters don't match
                    }
                }
            }

            return ans; // Return the length of the Longest Common Substring (LCS)
        }

        /*
            Time Complexity: O(N*M)

            Reason: There are two nested loops.

            Space Complexity: O(M)

            Reason: We are using an external array of size ‘M+1’ to store only two rows.
         */
        // Function to find the length of the Longest Common Substring (LCS)
        static int lcsSpaceOptimized(String s1, String s2) {
            int n = s1.length();
            int m = s2.length();

            // Create arrays to store LCS lengths
            int prev[] = new int[m + 1];
            int cur[] = new int[m + 1];

            int ans = 0; // Initialize a variable to store the maximum LCS length

            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= m; j++) {
                    // If the characters at the current indices are the same, extend the LCS
                    if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                        int val = 1 + prev[j - 1];
                        cur[j] = val;
                        ans = Math.max(ans, val); // Update the maximum LCS length
                    } else {
                        cur[j] = 0; // Reset LCS length if characters don't match
                    }
                }
                // Update the 'prev' array to the values of 'cur' for the next iteration
                prev = cur.clone();
            }

            return ans; // Return the length of the Longest Common Substring (LCS)
        }


        public static void main(String args[]) {
            String s1 = "abcjklp";
            String s2 = "acjkp";

            // Call the lcs function and print the result
            System.out.println("The Length of Longest Common Substring is " + lcs(s1, s2));
        }
    }


    /*
     * Date 08/01/2017
     * @author Mukesh Kumar Gupta
     * https://www.youtube.com/watch?v=6i_T5kkfv4A&list=PLgUwDviBIf0qUlt5H_kiKYaNSqJ81PMMY&index=29
     * https://leetcode.com/problems/longest-palindromic-subsequence/
     * Category: Medium, Must Do
     * Derived Question: DP 29. Minimum Insertions to Make String Palindrome
     *
     * Given a string find longest palindromic subsequence in this string.
     * Related: https://leetcode.com/problems/palindromic-substrings/ Medium
     * https://leetcode.com/problems/count-different-palindromic-subsequences/ Hard
     * https://leetcode.com/problems/longest-palindromic-subsequence-ii/ Medium
     * https://leetcode.com/problems/maximize-palindrome-length-from-subsequences/ Hard
     * https://leetcode.com/problems/maximum-product-of-the-length-of-two-palindromic-subsequences/ Medium
     *
     * Time complexity - O(n2)
     * Space complexity - O(n2
     *
     * Youtube link - https://youtu.be/_nCsPn7_OgI
     *
     * References
     * http://www.geeksforgeeks.org/dynamic-programming-set-12-longest-palindromic-subsequence/
     * Given a string s, find the longest palindromic subsequence's length in s.

    A subsequence is a sequence that can be derived from another sequence by deleting some or no elements without changing the order of the remaining elements.



    Example 1:

    Input: s = "bbbab"
    Output: 4
    Explanation: One possible longest palindromic subsequence is "bbbb".
    Example 2:

    Input: s = "cbbd"
    Output: 2
    Explanation: One possible longest palindromic subsequence is "bb".
     */

    /*


        Complexity Analysis
        Time Complexity: O(N*N)

        Reason: There are two nested loops

        Space Complexity: O(N*N)

        Reason: We are using an external array of size ‘(N*N)’. Stack Space is eliminated.
     */
    public static class LongestPalindromicSubsequence {
        // Function to find the length of the Longest Common Subsequence (LCS)
        static int lcs(String s1, String s2) {
            int n = s1.length();
            int m = s2.length();

            // Create a 2D array to store the LCS lengths
            int dp[][] = new int[n + 1][m + 1];

            // Initialize the dp array with -1
            for (int rows[] : dp)
                Arrays.fill(rows, -1);

            // Initialize the first row and first column with 0
            for (int i = 0; i <= n; i++) {
                dp[i][0] = 0;
            }
            for (int i = 0; i <= m; i++) {
                dp[0][i] = 0;
            }

            // Fill the dp array using a bottom-up approach
            for (int ind1 = 1; ind1 <= n; ind1++) {
                for (int ind2 = 1; ind2 <= m; ind2++) {
                    if (s1.charAt(ind1 - 1) == s2.charAt(ind2 - 1))
                        dp[ind1][ind2] = 1 + dp[ind1 - 1][ind2 - 1];
                    else
                        dp[ind1][ind2] = Math.max(dp[ind1 - 1][ind2], dp[ind1][ind2 - 1]);
                }
            }

            return dp[n][m];
        }

        // Function to find the length of the Longest Palindromic Subsequence
        static int longestPalindromeSubsequence(String s) {
            // Create a reversed version of the input string
            String reversed = new StringBuilder(s).reverse().toString();

            // Calculate the LCS of the original string and its reverse
            return lcs(s, reversed);
        }

        public static void main(String args[]) {
            String s = "bbabcbcab";

            System.out.print("The Length of Longest Palindromic Subsequence is ");
            System.out.println(longestPalindromeSubsequence(s));
        }
    }
    /*
    https://www.youtube.com/watch?v=xPBLEj41rFU&list=PLgUwDviBIf0qUlt5H_kiKYaNSqJ81PMMY&index=30
        Complexity Analysis
        Time Complexity: O(N*N)

        Reason: There are two nested loops

        Space Complexity: O(N*N)

        Reason: We are using an external array of size (N*N). Stack Space is eliminated.
     */

    public static class MinimumInsertionsToMakeStringPalindrome {
        // Function to find the length of the Longest Common Subsequence (LCS)
        static int lcs(String s1, String s2) {
            int n = s1.length();
            int m = s2.length();

            // Create a 2D array to store the LCS lengths
            int dp[][] = new int[n + 1][m + 1];

            // Initialize the dp array with -1
            for (int rows[] : dp)
                Arrays.fill(rows, -1);

            // Initialize the first row and first column with 0
            for (int i = 0; i <= n; i++) {
                dp[i][0] = 0;
            }
            for (int i = 0; i <= m; i++) {
                dp[0][i] = 0;
            }

            // Fill the dp array using a bottom-up approach
            for (int ind1 = 1; ind1 <= n; ind1++) {
                for (int ind2 = 1; ind2 <= m; ind2++) {
                    if (s1.charAt(ind1 - 1) == s2.charAt(ind2 - 1))
                        dp[ind1][ind2] = 1 + dp[ind1 - 1][ind2 - 1];
                    else
                        dp[ind1][ind2] = Math.max(dp[ind1 - 1][ind2], dp[ind1][ind2 - 1]);
                }
            }

            return dp[n][m];
        }

        // Function to find the length of the Longest Palindromic Subsequence
        static int longestPalindromeSubsequence(String s) {
            // Create a reversed version of the input string
            String reversed = new StringBuilder(s).reverse().toString();

            // Calculate the LCS of the original string and its reverse
            return lcs(s, reversed);
        }

        // Function to find the minimum insertions required to make the string palindrome
        static int minInsertion(String s) {
            int n = s.length();
            int k = longestPalindromeSubsequence(s);

            // The minimum insertions required is the difference between the string length and its
            // Longest Palindromic Subsequence length
            return n - k;
        }

        public static void main(String args[]) {
            String s = "abcaa";
            System.out.println("The Minimum insertions required to make the string palindrome: " + minInsertion(s));
        }
    }

    /*

    https://www.youtube.com/watch?v=yMnH0jrir0Q&list=PLgUwDviBIf0qUlt5H_kiKYaNSqJ81PMMY&index=31
    Both  problem is same only wording is different
    https://leetcode.com/problems/delete-operation-for-two-strings/submissions/1374304008/
    https://www.naukri.com/code360/problems/minimum-number-of-deletions-and-insertions_4244510?leftPanelTabValue=SUBMISSION
    Complexity Analysis
    Time Complexity: O(N*M)

    Reason: There are two nested loops

    Space Complexity: O(N*M)

    Reason: We are using an external array of size (N*M). Stack Space is eliminated.
     */

    public static class MinimumInsertionsDeletionstoConvertString {
        // Function to find the length of the Longest Common Subsequence (LCS)
        static int lcs(String s1, String s2) {
            int n = s1.length();
            int m = s2.length();

            // Create a 2D array to store the LCS lengths
            int dp[][] = new int[n + 1][m + 1];

            // Initialize the dp array with -1
            for (int rows[] : dp)
                Arrays.fill(rows, -1);

            // Initialize the first row and first column with 0
            for (int i = 0; i <= n; i++) {
                dp[i][0] = 0;
            }
            for (int i = 0; i <= m; i++) {
                dp[0][i] = 0;
            }

            // Fill the dp array using a bottom-up approach
            for (int ind1 = 1; ind1 <= n; ind1++) {
                for (int ind2 = 1; ind2 <= m; ind2++) {
                    if (s1.charAt(ind1 - 1) == s2.charAt(ind2 - 1))
                        dp[ind1][ind2] = 1 + dp[ind1 - 1][ind2 - 1];
                    else
                        dp[ind1][ind2] = Math.max(dp[ind1 - 1][ind2], dp[ind1][ind2 - 1]);
                }
            }

            return dp[n][m];
        }

        // Function to find the minimum operations required to convert str1 to str2
        static int canYouMake(String str1, String str2) {
            int n = str1.length();
            int m = str2.length();

            // Find the length of the LCS between str1 and str2
            int k = lcs(str1, str2);

            // The minimum operations required is the sum of the lengths of str1 and str2 minus twice the length of LCS
            return (n - k) + (m - k);
        }

        public static void main(String args[]) {
            String str1 = "abcd";
            String str2 = "anc";
            System.out.println("The Minimum operations required to convert str1 to str2: "
                    + canYouMake(str1, str2));
        }
    }

    /*
        https://www.youtube.com/watch?v=xElxAuBcvsU&list=PLgUwDviBIf0qUlt5H_kiKYaNSqJ81PMMY&index=32
        https://leetcode.com/problems/shortest-common-supersequence/description/

        https://www.naukri.com/code360/problems/shortest-supersequence_4244493

        Related:
        https://leetcode.com/problems/shortest-string-that-contains-three-strings/description/
        The Longest Common Supersequence is bgruoote

        Time Complexity: O(N*M)

        Reason: There are two nested loops

        Space Complexity: O(N*M)

        Reason: We are using an external array of size (N*M).
     */

    public static class ShortestCommonSupersequence {
        static String shortestSupersequence(String s1, String s2){

            int n = s1.length();
            int m = s2.length();

            int[][] dp =new int[n+1][m+1];
            for (int i = 0; i <= n; i++) {
                dp[i][0] = 0;
            }
            for (int i = 0; i <= m; i++) {
                dp[0][i] = 0;
            }

            for (int ind1 = 1; ind1 <= n; ind1++) {
                for (int ind2 = 1; ind2 <= m; ind2++) {
                    if (s1.charAt(ind1 - 1) == s2.charAt(ind2 - 1))
                        dp[ind1][ind2] = 1 + dp[ind1 - 1][ind2 - 1];
                    else
                        dp[ind1][ind2] = 0 + Math.max(dp[ind1 - 1][ind2], dp[ind1][ind2 - 1]);
                }
            }

            int len = dp[n][m];
            int i = n;
            int j = m;

            int index = len - 1;
            String ans = "";

            while (i > 0 && j > 0) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    ans += s1.charAt(i-1);
                    index--;
                    i--;
                    j--;
                } else if (dp[i - 1][j] > dp[i][j - 1]) {
                    ans += s1.charAt(i-1);
                    i--;
                } else {
                    ans += s2.charAt(j-1);
                    j--;
                }
            }

            //Adding Remaing Characters - Only one of the below two while loops will run

            while(i>0){
                ans += s1.charAt(i-1);
                i--;
            }
            while(j>0){
                ans += s2.charAt(j-1);
                j--;
            }

            String ans2=new StringBuilder(ans).reverse().toString();

            return ans2;
        }

        public static void main(String args[]) {

            String s1 = "brute";
            String s2 = "groot";

            System.out.println("The Longest Common Supersequence is "+shortestSupersequence(s1,s2));
        }
    }

    /*
        https://www.youtube.com/watch?v=nVG7eTiD2bY&list=PLgUwDviBIf0qUlt5H_kiKYaNSqJ81PMMY&index=34
        Category: Hard, VVImp
        https://leetcode.com/problems/distinct-subsequences/
        https://www.youtube.com/redirect?event=video_description&redir_token=QUFFLUhqa1VFUWJQTWtrSENVZHJQTWcyNVNPSzRoek1zZ3xBQ3Jtc0trcmpXS1hrb2xNZjdQUlJDXzhhNEN5RFdXTzBra0Y2MkVrcFlqWlFvc09NOEJBQmJBaUlUWkFmMks3S1M3VDd0VmtnU2V2VkY5VlVLenEwR3JEdFIwalEtLXJpR2RmYVo0MGQteGpBTnhiOVJwWkZDbw&q=https%3A%2F%2Fbit.ly%2F3nZNxy7&v=nVG7eTiD2bY
     */
    public static class DistinctSubsequences {
        /*
            Complexity Analysis
            Time Complexity: O(N*M)

            Reason: There are N*M states therefore at max ‘N*M’ new problems will be solved.

            Space Complexity: O(N*M) + O(N+M)

            Reason: We are using a recursion stack space(O(N+M)) and a 2D array ( O(N*M)).
         */
        static int prime = (int) (Math.pow(10, 9) + 7);

        // Function to count the number of distinct subsequences of s1 that are equal to s2
        static int countUtil(String s1, String s2, int ind1, int ind2, int[][] dp) {
            // If we have exhausted s2, there's one valid subsequence (empty string) in s1.
            if (ind2 < 0)
                return 1;
            // If we have exhausted s1 but not s2, there are no valid subsequences.
            if (ind1 < 0)
                return 0;

            // If the result is already computed, return it.
            if (dp[ind1][ind2] != -1)
                return dp[ind1][ind2];

            // If the characters at the current positions match, we can either leave one character from s1
            // or continue to the next character in s1 while staying at the same character in s2.
            if (s1.charAt(ind1) == s2.charAt(ind2)) {
                int leaveOne = countUtil(s1, s2, ind1 - 1, ind2 - 1, dp);
                int stay = countUtil(s1, s2, ind1 - 1, ind2, dp);

                // Add the two possibilities and take modulo prime to avoid integer overflow.
                return dp[ind1][ind2] = (leaveOne + stay) % prime;
            } else {
                // If the characters don't match, we can only continue to the next character in s1.
                return dp[ind1][ind2] = countUtil(s1, s2, ind1 - 1, ind2, dp);
            }
        }

        // Function to calculate the count of distinct subsequences of s1 equal to s2
        static int subsequenceCounting(String s1, String s2, int lt, int ls) {
            // Initialize a DP array to store intermediate results
            int dp[][] = new int[lt][ls];
            for (int rows[] : dp)
                Arrays.fill(rows, -1);

            // Call the recursive helper function to compute the count
            return countUtil(s1, s2, lt - 1, ls - 1, dp);
        }


        static int countUtilMoveIndexByOne(String s1, String s2, int ind1, int ind2, int[][] dp) {
            // If we have exhausted s2, there's one valid subsequence (empty string) in s1.
            if (ind2 == 0)
                return 1;
            // If we have exhausted s1 but not s2, there are no valid subsequences.
            if (ind1 == 0)
                return 0;

            // If the result is already computed, return it.
            if (dp[ind1][ind2] != -1)
                return dp[ind1][ind2];

            // If the characters at the current positions match, we can either leave one character from s1
            // or continue to the next character in s1 while staying at the same character in s2.
            if (s1.charAt(ind1-1) == s2.charAt(ind2-1)) {
                int leaveOne = countUtilMoveIndexByOne(s1, s2, ind1 - 1, ind2 - 1, dp);
                int stay = countUtilMoveIndexByOne(s1, s2, ind1 - 1, ind2, dp);

                // Add the two possibilities and take modulo prime to avoid integer overflow.
                return dp[ind1][ind2] = (leaveOne + stay) % prime;
            } else {
                // If the characters don't match, we can only continue to the next character in s1.
                return dp[ind1][ind2] = countUtil(s1, s2, ind1 - 1, ind2, dp);
            }
        }
        public static int distinctSubsequencesMoveIndexByOne(String str, String sub) {
            int lt = str.length();
            int ls = sub.length();
            // Initialize a DP array to store intermediate results
            int dp[][] = new int[lt+1][ls+1];
            for (int rows[] : dp)
                Arrays.fill(rows, -1);

            // Call the recursive helper function to compute the count
            return countUtil(str, sub, lt, ls, dp);
        }
        /*
            Time Complexity: O(N*M)

            Reason: There are two nested loops

            Space Complexity: O(N*M)

            Reason: We are using an external array of size ‘N*M’. Stack Space is eliminated.
         */

        // Function to calculate the count of distinct subsequences of s1 equal to s2
        static int subsequenceCountingTabulation(String s1, String s2, int n, int m) {
            // Create a 2D array to store the counts of subsequences
            int dp[][] = new int[n + 1][m + 1];

            // Initialize the first column with 1 because there's one empty subsequence in any string.
            for (int i = 0; i < n + 1; i++) {
                dp[i][0] = 1;
            }

            // Initialize the first row (except dp[0][0]) with 0 because there's no way to form s2 from an empty string.
            for (int i = 1; i < m + 1; i++) {
                dp[0][i] = 0;
            }

            // Fill the dp array using a bottom-up approach
            for (int i = 1; i < n + 1; i++) {
                for (int j = 1; j < m + 1; j++) {
                    if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                        // If the characters match, we can either include this character in the subsequence
                        // or exclude it. So, we add the counts from both possibilities.
                        dp[i][j] = (dp[i - 1][j - 1] + dp[i - 1][j]) % prime;
                    } else {
                        // If the characters don't match, we can only exclude this character.
                        dp[i][j] = dp[i - 1][j];
                    }
                }
            }

            return dp[n][m];
        }

        /*
            Complexity Analysis
            Time Complexity: O(N*M)

            Reason: There are two nested loops.

            Space Complexity: O(M)

            Reason: We are using an external array of size ‘M+1’ to store only one row.
         */
        // Function to calculate the count of distinct subsequences of s1 equal to s2
        static int subsequenceCountingSpaceOptimized(String s1, String s2, int n, int m) {
            // Create an array to store the counts of subsequences
            int[] prev = new int[m + 1];

            // Initialize the first element to 1 because there's one empty subsequence in any string.
            prev[0] = 1;

            // Fill the prev array using a bottom-up approach
            for (int i = 1; i < n + 1; i++) {
                for (int j = m; j >= 1; j--) { // Reverse direction for updating

                    if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                        // If the characters match, we can either include this character in the subsequence
                        // or exclude it. So, we add the counts from both possibilities.
                        prev[j] = (prev[j - 1] + prev[j]) % prime;
                    } else {
                        // If the characters don't match, we can only exclude this character.
                        prev[j] = prev[j]; // This statement is not necessary, as it doesn't change the value.
                    }
                }
            }

            return prev[m];
        }


        public static void main(String args[]) {
            String s1 = "babgbag";
            String s2 = "bag";

            System.out.println("The Count of Distinct Subsequences is " +
                    subsequenceCounting(s1, s2, s1.length(), s2.length()));
        }
    }

    /*
        https://www.youtube.com/watch?v=fJaKO8FbDdo&list=PLgUwDviBIf0qUlt5H_kiKYaNSqJ81PMMY&index=34
     */

    /*
     * Date 07/07/2017
     * @author Mukesh Kumar Gupta
     * Reference: https://leetcode.com/problems/edit-distance/submissions/
     * Category: Hard, Must Do, Google, Facebook
     * Related:
     * https://leetcode.com/problems/one-edit-distance/ Medium
     * https://leetcode.com/problems/delete-operation-for-two-strings/ Medium
     * https://leetcode.com/problems/minimum-ascii-delete-sum-for-two-strings/ Medium
     * https://leetcode.com/problems/uncrossed-lines/ Medium
     *
     * Given two strings how many minimum edits(update, delete or add) is needed to convert one string to another
     *
     * Time complexity is O(m*n)
     * Space complexity is O(m*n)
     *
     * References:
     * http://www.geeksforgeeks.org/dynamic-programming-set-5-edit-distance/
     * https://en.wikipedia.org/wiki/Edit_distance
     */

    public static class EditDistance {
        /*
            Complexity Analysis
            Time Complexity: O(N*M)

            Reason: There are N*M states therefore at max ‘N*M’ new problems will be solved.

            Space Complexity: O(N*M) + O(N+M)

            Reason: We are using a recursion stack space(O(N+M)) and a 2D array ( O(N*M)).
         */
        // Function to calculate the minimum edit distance between two strings
        static int editDistanceUtil(String S1, String S2, int i, int j, int[][] dp) {
            // Base cases
            if (i < 0)
                return j + 1;
            if (j < 0)
                return i + 1;

            // If the result is already computed, return it
            if (dp[i][j] != -1)
                return dp[i][j];

            // If the characters at the current positions match, no edit is needed
            if (S1.charAt(i) == S2.charAt(j))
                return dp[i][j] = 0 + editDistanceUtil(S1, S2, i - 1, j - 1, dp);

            else
                return dp[i][j] = 1 + Math.min(editDistanceUtil(S1, S2, i - 1, j - 1, dp), //1. Replace the character in S1 with the character in S2.
                        Math.min(editDistanceUtil(S1, S2, i - 1, j, dp), //2. Delete the character in S1.
                                editDistanceUtil(S1, S2, i, j - 1, dp))); //3. Insert the character from S2 into S1.
        }

        static int editDistance(String S1, String S2) {
            int n = S1.length();
            int m = S2.length();

            int[][] dp = new int[n][m];
            for (int row[] : dp)
                Arrays.fill(row, -1);

            // Call the recursive helper function
            return editDistanceUtil(S1, S2, n - 1, m - 1, dp);
        }


        static int editDistanceUtilMovedIndexByOne(String S1, String S2, int i, int j, int[][] dp) {
            if (i == 0)
                return j;
            if (j == 0)
                return i;

            // If the result is already computed, return it
            if (dp[i][j] != -1)
                return dp[i][j];

            // If the characters at the current positions match, no edit is needed
            if (S1.charAt(i-1) == S2.charAt(j-1))
                return dp[i][j] = 0 + editDistanceUtilMovedIndexByOne(S1, S2, i - 1, j - 1, dp);

            else
                return dp[i][j] = 1 + Math.min(editDistanceUtilMovedIndexByOne(S1, S2, i - 1, j - 1, dp), //1. Replace the character in S1 with the character in S2.
                        Math.min(editDistanceUtilMovedIndexByOne(S1, S2, i - 1, j, dp), //2. Delete the character in S1.
                                editDistanceUtilMovedIndexByOne(S1, S2, i, j - 1, dp))); //3. Insert the character from S2 into S1.into S1.
        }
        public int minDistanceMovedIndexByOne(String word1, String word2) {
            String S1 = word1;
            String S2 = word2;
            int n = S1.length();
            int m = S2.length();

            int[][] dp = new int[n+1][m+1];
            for (int row[] : dp)
                Arrays.fill(row, -1);

            // Call the recursive helper function
            return editDistanceUtilMovedIndexByOne(S1, S2, n , m, dp);
        }

        /*
            Complexity Analysis
            Time Complexity: O(N*M)

            Reason: There are two nested loops

            Space Complexity: O(N*M)

            Reason: We are using an external array of size ‘N*M’. Stack Space is eliminated.
         */

        // Function to calculate the minimum edit distance between two strings
        static int editDistanceTabulation(String S1, String S2) {
            int n = S1.length();
            int m = S2.length();

            // Create a 2D array to store the minimum edit distances
            int[][] dp = new int[n + 1][m + 1];

            // Initialize the first row and column with their respective indices
            for (int i = 0; i <= n; i++) {
                dp[i][0] = i;
            }
            for (int j = 0; j <= m; j++) {
                dp[0][j] = j;
            }

            // Fill the dp array using a bottom-up approach
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= m; j++) {
                    if (S1.charAt(i - 1) == S2.charAt(j - 1)) {
                        // If the characters match, no edit is needed, so take the value from the diagonal.
                        dp[i][j] = dp[i - 1][j - 1];
                    } else {
                        // If the characters don't match, take the minimum of three possibilities:
                        // 1. Replace the character in S1 with the character in S2 (diagonal).
                        // 2. Delete the character in S1 (left).
                        // 3. Insert the character from S2 into S1 (up).
                        dp[i][j] = 1 + Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1]));
                    }
                }
            }

            return dp[n][m];
        }

        /*
            Complexity Analysis
            Time Complexity: O(N*M)

            Reason: There are two nested loops.

            Space Complexity: O(M)

            Reason: We are using an external array of size ‘M+1’ to store two rows.
         */

        // Function to calculate the minimum edit distance between two strings
        static int editDistanceSpaceOptimized(String S1, String S2) {
            int n = S1.length();
            int m = S2.length();

            // Create two arrays to store the previous and current rows of minimum edit distances
            int[] prev = new int[m + 1];
            int[] cur = new int[m + 1];

            // Initialize the first row with their respective indices
            for (int j = 0; j <= m; j++) {
                prev[j] = j;
            }

            // Fill the cur array using a bottom-up approach
            for (int i = 1; i <= n; i++) {
                cur[0] = i;
                for (int j = 1; j <= m; j++) {
                    if (S1.charAt(i - 1) == S2.charAt(j - 1)) {
                        // If the characters match, no edit is needed, so take the value from the diagonal.
                        cur[j] = prev[j - 1];
                    } else {
                        // If the characters don't match, take the minimum of three possibilities:
                        // 1. Replace the character in S1 with the character in S2 (diagonal).
                        // 2. Delete the character in S1 (left).
                        // 3. Insert the character from S2 into S1 (up).
                        cur[j] = 1 + Math.min(prev[j - 1], Math.min(prev[j], cur[j - 1]));
                    }
                }
                // Update prev array to store the current values
                prev = cur.clone();
            }

            return cur[m];
        }

        public static void main(String args[]) {
            String s1 = "horse";
            String s2 = "ros";

            System.out.println("The minimum number of operations required is: " +
                    editDistance(s1, s2));
        }
    }


    /*
    https://www.youtube.com/watch?v=ZmlQ3vgAOMo&list=PLgUwDviBIf0qUlt5H_kiKYaNSqJ81PMMY&index=35
     */
    /*
     * Date 02/11/2017
     * @author Mukesh Kumar Gupta
     *
     * Wild car matching with ? and *
     *
     * Reference
     * https://leetcode.com/problems/wildcard-matching/
     * https://www.youtube.com/watch?v=3ZDZ-N0EPV0
     * Category: Hard, Must Do, Top150
     * https://leetcode.com/problems/basic-calculator/ Hard, Basic calculat has three version, try all, VVImp similar to https://leetcode.com/problems/evaluate-reverse-polish-notation/
     * https://leetcode.com/problems/find-k-length-substrings-with-no-repeated-characters/ Medium, Locked, It looks similar to sliding window, just explore in future
     * https://leetcode.com/problems/check-if-a-parentheses-string-can-be-valid/ Medium, VVImp
     * Related: https://leetcode.com/problems/lemonade-change/ Easy
     * https://leetcode.com/problems/minimum-swaps-to-make-strings-equal/ Medium
     * https://leetcode.com/problems/latest-time-by-replacing-hidden-digits/ Easy
     *
     * Given an input string (s) and a pattern (p), implement wildcard pattern matching with support for '?' and '*' where:

    '?' Matches any single character.
    '*' Matches any sequence of characters (including the empty sequence).
    The matching should cover the entire input string (not partial).



    Example 1:

    Input: s = "aa", p = "a"
    Output: false
    Explanation: "a" does not match the entire string "aa".
    Example 2:

    Input: s = "aa", p = "*"
    Output: true
    Explanation: '*' matches any sequence.
    Example 3:

    Input: s = "cb", p = "?a"
    Output: false
    Explanation: '?' matches 'c', but the second letter is 'a', which does not match 'b'.
    Example 4:

    Input: s = "adceb", p = "*a*b"
    Output: true
    Explanation: The first '*' matches the empty sequence, while the second '*' matches the substring "dce".
    Example 5:

    Input: s = "acdcb", p = "a*c?b"
    Output: false


    Constraints:

    0 <= s.length, p.length <= 2000
    s contains only lowercase English letters.
    p contains only lowercase English letters, '?' or '*'.
     *
     */
    public static class WildcardMatching {
        /*
            Complexity Analysis
            Time Complexity: O(N*M)

            Reason: There are N*M states therefore at max ‘N*M’ new problems will be solved.

            Space Complexity: O(N*M) + O(N+M)

            Reason: We are using a recursion stack space(O(N+M)) and a 2D array ( O(N*M)).
         */
        // Helper function to check if all characters from index 0 to i in S1 are '*'
        static boolean isAllStars(String S1, int i) {
            for (int j = 0; j <= i; j++) {
                if (S1.charAt(j) != '*')
                    return false;
            }
            return true;
        }

        // Recursive function to perform wildcard pattern matching
        static int wildcardMatchingUtil(String S1, String S2, int i, int j, int[][] dp) {
            // Base Cases
            if (i < 0 && j < 0)
                return 1; // Both strings are empty, and the pattern matches.
            if (i < 0 && j >= 0)
                return 0; // S1 is empty, but there are characters left in S2.
            if (j < 0 && i >= 0)
                return isAllStars(S1, i) ? 1 : 0; // S2 is empty, check if remaining characters in S1 are all '*'.

            // If the result is already computed, return it.
            if (dp[i][j] != -1) return dp[i][j];

            // If the characters match or S1 has a '?', continue matching the rest of the strings.
            if (S1.charAt(i) == S2.charAt(j) || S1.charAt(i) == '?')
                return dp[i][j] = wildcardMatchingUtil(S1, S2, i - 1, j - 1, dp);

            else {
                if (S1.charAt(i) == '*') {
                    // Two possibilities when encountering '*':
                    // 1. '*' matches one or more characters in S2.
                    // 2. '*' matches zero characters in S2.
                    return dp[i][j] = (wildcardMatchingUtil(S1, S2, i - 1, j, dp) == 1 || wildcardMatchingUtil(S1, S2, i, j - 1, dp) == 1) ? 1 : 0;
                } else {
                    // Characters don't match, and S1[i] is not '*'.
                    return 0;
                }
            }
        }

        // Main function to check if S1 matches the wildcard pattern S2
        static int wildcardMatching(String S1, String S2) {
            int n = S1.length();
            int m = S2.length();

            int dp[][] = new int[n][m];
            for (int row[]: dp)
                Arrays.fill(row, -1);

            // Call the recursive helper function
            return wildcardMatchingUtil(S1, S2, n - 1, m - 1, dp);
        }

        static boolean isAllStarsMoveOneIndexRight(String S1, int i) {
            for (int j = 1; j <= i; j++) {
                if (S1.charAt(j-1) != '*')
                    return false;
            }
            return true;
        }


        // Recursive function to perform wildcard pattern matching
        static int wildcardMatchingUtilMoveOneIndexRight(String S1, String S2, int i, int j, int[][] dp) {
            // Base Cases
            if (i == 0 && j == 0)
                return 1; // Both strings are empty, and the pattern matches.
            if (i == 0 && j > 0)
                return 0; // S1 is empty, but there are characters left in S2.
            if (j == 0 && i > 0)
                return isAllStarsMoveOneIndexRight(S1, i) ? 1 : 0; // S2 is empty, check if remaining characters in S1 are all '*'.

            // If the result is already computed, return it.
            if (dp[i][j] != -1) return dp[i][j];

            // If the characters match or S1 has a '?', continue matching the rest of the strings.
            if (S1.charAt(i-1) == S2.charAt(j-1) || S1.charAt(i-1) == '?')
                return dp[i][j] = wildcardMatchingUtilMoveOneIndexRight(S1, S2, i - 1, j - 1, dp);

            else {
                if (S1.charAt(i-1) == '*') {
                    // Two possibilities when encountering '*':
                    // 1. '*' matches one or more characters in S2.
                    // 2. '*' matches zero characters in S2.
                    return dp[i][j] = (wildcardMatchingUtilMoveOneIndexRight(S1, S2, i - 1, j, dp) == 1 || wildcardMatchingUtilMoveOneIndexRight(S1, S2, i, j - 1, dp) == 1) ? 1 : 0;
                } else {
                    // Characters don't match, and S1[i] is not '*'.
                    return 0;
                }
            }
        }

        // Main function to check if S1 matches the wildcard pattern S2
        static int wildcardMatchingMoveOneIndexRight(String S1, String S2) {
            int n = S1.length();
            int m = S2.length();

            int dp[][] = new int[n+1][m+1];
            for (int row[]: dp)
                Arrays.fill(row, -1);

            // Call the recursive helper function
            return wildcardMatchingUtilMoveOneIndexRight(S1, S2, n, m, dp);
        }

        public boolean isMatch(String s, String p) {
            return wildcardMatchingMoveOneIndexRight(p, s) == 1? true: false;


        }


        /*
            Complexity Analysis
            Time Complexity: O(N*M)

            Reason: There are two nested loops

            Space Complexity: O(N*M)

            Reason: We are using an external array of size ‘N*M’. Stack Space is eliminated.
         */
        // Function to perform wildcard pattern matching
        static boolean wildcardMatchingTabulation(String S1, String S2) {
            int n = S1.length();
            int m = S2.length();

            // Create a 2D array to store the matching results
            boolean dp[][] = new boolean[n + 1][m + 1];
            dp[0][0] = true;

            // Initialize the first row and column based on wildcard '*' in S1
            for (int j = 1; j <= m; j++) {
                dp[0][j] = false;
            }
            for (int i = 1; i <= n; i++) {
                dp[i][0] = isAllStars(S1, i);
            }

            // Fill the dp array using a bottom-up approach
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= m; j++) {
                    if (S1.charAt(i - 1) == S2.charAt(j - 1) || S1.charAt(i - 1) == '?') {
                        dp[i][j] = dp[i - 1][j - 1]; // Characters match or '?' is encountered.
                    } else {
                        if (S1.charAt(i - 1) == '*') {
                            dp[i][j] = dp[i - 1][j] || dp[i][j - 1]; // '*' matches one or more characters.
                        } else {
                            dp[i][j] = false; // Characters don't match, and S1[i-1] is not '*'.
                        }
                    }
                }
            }

            return dp[n][m]; // The final result indicates whether S1 matches S2.
        }

        /*
            Complexity Analysis
            Time Complexity: O(N*M)

            Reason: There are two nested loops.

            Space Complexity: O(M)

            Reason: We are using an external array of size ‘M+1’ to store two rows.
         */
        // Function to perform wildcard pattern matching
        static boolean wildcardMatchingSpcaeOptimized(String S1, String S2) {
            int n = S1.length();
            int m = S2.length();

            // Create two boolean arrays to store the matching results for the current and previous rows
            boolean[] prev = new boolean[m + 1];
            boolean[] cur = new boolean[m + 1];

            // Initialize the first element of prev as true
            prev[0] = true;

            // Iterate through S1 and S2 to fill the cur array
            for (int i = 1; i <= n; i++) {
                // Initialize the first element of cur based on whether S1 contains '*'
                cur[0] = isAllStars(S1, i);
                for (int j = 1; j <= m; j++) {
                    if (S1.charAt(i - 1) == S2.charAt(j - 1) || S1.charAt(i - 1) == '?') {
                        cur[j] = prev[j - 1]; // Characters match or '?' is encountered.
                    } else {
                        if (S1.charAt(i - 1) == '*') {
                            cur[j] = prev[j] || cur[j - 1]; // '*' matches one or more characters.
                        } else {
                            cur[j] = false; // Characters don't match, and S1[i-1] is not '*'.
                        }
                    }
                }
                // Update prev array to store the current values
                prev = cur.clone();
            }

            return prev[m]; // The final result indicates whether S1 matches S2.
        }


        public static void main(String args[]) {
            String S1 = "ab*cd";
            String S2 = "abdefcd";

            if (wildcardMatching(S1, S2) == 1)
                System.out.println("String S1 and S2 do match");
            else
                System.out.println("String S1 and S2 do not match");
        }
    }
}
