package com.interview.dynamic;

import java.util.*;

public class PartIDPOnLISubsequence {
    /*
        https://www.youtube.com/watch?v=ekcwMsSIzVc&list=PLgUwDviBIf0qUlt5H_kiKYaNSqJ81PMMY&index=42
        Category: Medium
        https://leetcode.com/problems/longest-increasing-subsequence/
        /**
 * Date 05/02/2017
 * @author Mukesh Kumar Gupta
 *
 * Youtube link - https://youtu.be/CE2b_-XfVDk
 * Leetcode: https://leetcode.com/problems/longest-increasing-subsequence/
 * Category: Medium, Must Do
 *
 * Find a subsequence in given array in which the subsequence's elements are
 * in sorted order, lowest to highest, and in which the subsequence is as long as possible
 *
 * Solution :
 * Dynamic Programming is used to solve this question. DP equation is
 * if(arr[i] > arr[j]) { dp[i] = max(dp[i], dp[j] + 1 }
 *
 * Time complexity is O(n^2).
 * Space complexity is O(n)
 *
 * Reference
 * http://en.wikipedia.org/wiki/Longest_increasing_subsequence
 * http://www.geeksforgeeks.org/dynamic-programming-set-3-longest-increasing-subsequence/
 * Category: Must Do, Medium
 * If you want to track one more what are the sequences then take one more array and store from where it came
 * Given an integer array nums, return the length of the longest strictly increasing subsequence.

A subsequence is a sequence that can be derived from an array by deleting some or no elements without changing the order of the remaining elements. For example, [3,6,2,7] is a subsequence of the array [0,3,1,6,2,2,7].



Example 1:

Input: nums = [10,9,2,5,3,7,101,18]
Output: 4
Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
Example 2:

Input: nums = [0,1,0,3,2,3]
Output: 4
Example 3:

Input: nums = [7,7,7,7,7,7,7]
Output: 1


Constraints:

1 <= nums.length <= 2500
-104 <= nums[i] <= 104


Follow up: Can you come up with an algorithm that runs in O(n log(n)) time complexity?
 */
    public static class LongestIncreasingSubsequence {
        /*
        Time Complexity: O(N*N)

        Reason: There are N*N states therefore at max ‘N*N’ new problems will be solved.

        Space Complexity: O(N*N) + O(N)

        Reason: We are using an auxiliary recursion stack space(O(N)) (see the recursive tree, in the worst case we will go till N calls at a time) and a 2D array ( O(N*N+1)).
         */
        // Function to find the length of the longest increasing subsequence
        static int getAns(int arr[], int n, int ind, int prev_index, int[][] dp) {
            // Base condition
            if (ind == n) {
                return 0;
            }

            if (dp[ind][prev_index + 1] != -1) {
                return dp[ind][prev_index + 1];
            }

            int notTake = 0 + getAns(arr, n, ind + 1, prev_index, dp);

            int take = 0;

            if (prev_index == -1 || arr[ind] > arr[prev_index]) {
                take = 1 + getAns(arr, n, ind + 1, ind, dp);
            }

            dp[ind][prev_index + 1] = Math.max(notTake, take);

            return dp[ind][prev_index + 1];
        }

        // Function to find the length of the longest increasing subsequence
        static int longestIncreasingSubsequence(int arr[], int n) {
            int dp[][] = new int[n][n + 1];

            // Initialize dp array with -1 to mark states as not calculated yet
            for (int row[] : dp) {
                Arrays.fill(row, -1);
            }

            return getAns(arr, n, 0, -1, dp);
        }

        /*
        The length of the longest increasing subsequence is 4

        Time Complexity: O(N*N)

        Reason: There are two nested loops

        Space Complexity: O(N*N)

        Reason: We are using an external array of size ‘(N+1)*(N+1)’. Stack Space is eliminated.
         */
        static int longestIncreasingSubsequenceTabulation(int arr[], int n){

            int dp[][]=new int[n+1][n+1];

            for(int ind = n-1; ind>=0; ind --){
                for (int prev_index = ind-1; prev_index >=-1; prev_index --){

                    int notTake = 0 + dp[ind+1][prev_index +1];

                    int take = 0;

                    if(prev_index == -1 || arr[ind] > arr[prev_index]){

                        take = 1 + dp[ind+1][ind+1];
                    }

                    dp[ind][prev_index+1] = Math.max(notTake,take);

                }
            }

            return dp[0][0];
        }

        /*
        Output:

        The length of the longest increasing subsequence is 4

        Time Complexity: O(N*N)

        Reason: There are two nested loops.

        Space Complexity: O(N)

        Reason: We are only using two rows of size n.
         */
        static int longestIncreasingSubsequenceSpcaeOptimized(int arr[], int n){

            int next[]=new int[n+1];
            int cur[]=new int[n+1];

            for(int ind = n-1; ind>=0; ind --){
                for (int prev_index = ind-1; prev_index >=-1; prev_index --){

                    int notTake = 0 + next[prev_index +1];

                    int take = 0;

                    if(prev_index == -1 || arr[ind] > arr[prev_index]){

                        take = 1 + next[ind+1];
                    }

                    cur[prev_index+1] = Math.max(notTake,take);
                }
                next = cur.clone();
            }

            return cur[0];
        }

        /*
        Output:

        The length of the longest increasing subsequence is 4

        Time Complexity: O(N*N)
        https://youtu.be/CE2b_-XfVDk  Most Easy

        Reason: There are two nested loops.

        Space Complexity: O(N)

        Reason: We are only using two rows of size ‘N’.
         */

        static int longestIncreasingSubsequenceCustommAlgorithms(int arr[], int n){

            int dp[]=new int[n];
            Arrays.fill(dp,1);

            for(int i=0; i<=n-1; i++){
                for(int prev_index = 0; prev_index <=i-1; prev_index ++){

                    if(arr[prev_index]<arr[i]){
                        dp[i] = Math.max(dp[i], 1 + dp[prev_index]);
                    }
                }
            }

            int ans = -1;

            for(int i=0; i<=n-1; i++){
                ans = Math.max(ans, dp[i]);
            }

            return ans;
        }

        /*
        https://www.naukri.com/code360/problems/printing-longest-increasing-subsequence_8360670
        Time Complexity: O(N*N)

Reason: There are two nested loops.

Space Complexity: O(N)

Reason: We are only using two rows of size ‘N’.
         */
        static int longestIncreasingSubsequencePrinting(int arr[], int n){

            int[] dp=new int[n];
            Arrays.fill(dp,1);
            int[] hash=new int[n];
            Arrays.fill(hash,1);

            for(int i=0; i<=n-1; i++){

                hash[i] = i; // initializing with current index
                for(int prev_index = 0; prev_index <=i-1; prev_index ++){

                    if(arr[prev_index]<arr[i] && 1 + dp[prev_index] > dp[i]){
                        dp[i] = 1 + dp[prev_index];
                        hash[i] = prev_index;
                    }
                }
            }

            int ans = -1;
            int lastIndex =-1;

            for(int i=0; i<=n-1; i++){
                if(dp[i]> ans){
                    ans = dp[i];
                    lastIndex = i;
                }
            }

            ArrayList<Integer> temp=new ArrayList<>();
            temp.add(arr[lastIndex]);

            while(hash[lastIndex] != lastIndex){ // till not reach the initialization value
                lastIndex = hash[lastIndex];
                temp.add(arr[lastIndex]);
            }

            // reverse the array

            System.out.print("The subsequence elements are ");

            for(int i=temp.size()-1; i>=0; i--){
                System.out.print(temp.get(i)+" ");
            }
            System.out.println();

            return ans;
        }

        public static void main(String args[]) {
            int arr[] = {10, 9, 2, 5, 3, 7, 101, 18};

            int n = arr.length;

            System.out.println("The length of the longest increasing subsequence is " + longestIncreasingSubsequence(arr, n));
        }
    }

    /*
     * https://leetcode.com/problems/largest-divisible-subset/
     * https://www.youtube.com/watch?v=gDuZwBW9VvM
     * Category: Medium, lis, Must Do
     * Related: https://leetcode.com/problems/number-of-corner-rectangles/ Medium Locked
     * https://leetcode.com/problems/find-all-good-strings/ Hard VImp
     * https://leetcode.com/problems/count-fertile-pyramids-in-a-land/ Hard VVImp
     * Related:
     * 368. Largest Divisible Subset
    Medium

    3193

    150

    Add to List

    Share
    Given a set of distinct positive integers nums, return the largest subset answer such that every pair (answer[i], answer[j]) of elements in this subset satisfies:

    answer[i] % answer[j] == 0, or
    answer[j] % answer[i] == 0
    If there are multiple solutions, return any of them.



    Example 1:

    Input: nums = [1,2,3]
    Output: [1,2]
    Explanation: [1,3] is also accepted.
    Example 2:

    Input: nums = [1,2,4,8]
    Output: [1,2,4,8]


    Constraints:

    1 <= nums.length <= 1000
    1 <= nums[i] <= 2 * 109
    All the integers in nums are unique.
    Accepted
    150,474
    Submissions
    370,949
     */
    public static class LongestDivisibleSubset {
        static List<Integer> divisibleSet(int arr[]){

            Arrays.sort(arr);
            int n = arr.length;
            int[] dp=new int[n];
            Arrays.fill(dp,1);
            int[] hash=new int[n];
            Arrays.fill(hash,1);

            for(int i=0; i<=n-1; i++){

                hash[i] = i; // initializing with current index
                for(int prev_index = 0; prev_index <=i-1; prev_index ++){

                    if(arr[i] %  arr[prev_index] == 0 && 1 + dp[prev_index] > dp[i]){
                        dp[i] = 1 + dp[prev_index];
                        hash[i] = prev_index;
                    }
                }
            }

            int ans = -1;
            int lastIndex =-1;

            for(int i=0; i<=n-1; i++){
                if(dp[i]> ans){
                    ans = dp[i];
                    lastIndex = i;
                }
            }

            List<Integer> temp=new ArrayList<>();
            temp.add(arr[lastIndex]);

            while(hash[lastIndex] != lastIndex){ // till not reach the initialization value
                lastIndex = hash[lastIndex];
                temp.add(arr[lastIndex]);
            }

            Collections.reverse(temp);
            return temp;

        }
    }

    /*
     *
 * https://leetcode.com/problems/longest-string-chain/
 * Category: Medium, Tricky, lis
 * Refer this solution as well dfs approach
 * https://leetcode.com/problems/longest-string-chain/discuss/1986391/Java-beats-93-DFS-with-memo
 * Related:
 * https://leetcode.com/problems/fraction-to-recurring-decimal/ Medium Bad
 * https://leetcode.com/problems/largest-sum-of-averages/ Medium VVImp
 * https://leetcode.com/problems/sum-of-special-evenly-spaced-elements-in-array/ Hard, Locked
 * https://leetcode.com/problems/read-n-characters-given-read4-ii-call-multiple-times/ Hard
 * https://leetcode.com/problems/number-of-ways-of-cutting-a-pizza/ Hard
 * https://leetcode.com/problems/robot-return-to-origin/ Easy
 *
 * 1048. Longest String Chain
Medium

3364

165

Add to List

Share
You are given an array of words where each word consists of lowercase English letters.

wordA is a predecessor of wordB if and only if we can insert exactly one letter anywhere in wordA without changing the order of the other characters to make it equal to wordB.

For example, "abc" is a predecessor of "abac", while "cba" is not a predecessor of "bcad".
A word chain is a sequence of words [word1, word2, ..., wordk] with k >= 1, where word1 is a predecessor of word2, word2 is a predecessor of word3, and so on. A single word is trivially a word chain with k == 1.

Return the length of the longest possible word chain with words chosen from the given list of words.



Example 1:

Input: words = ["a","b","ba","bca","bda","bdca"]
Output: 4
Explanation: One of the longest word chains is ["a","ba","bda","bdca"].
Example 2:

Input: words = ["xbc","pcxbcf","xb","cxbc","pcxbc"]
Output: 5
Explanation: All the words can be put in a word chain ["xb", "xbc", "cxbc", "pcxbc", "pcxbcf"].
Example 3:

Input: words = ["abcd","dbqca"]
Output: 1
Explanation: The trivial word chain ["abcd"] is one of the longest word chains.
["abcd","dbqca"] is not a valid word chain because the ordering of the letters is changed.


Constraints:

1 <= words.length <= 1000
1 <= words[i].length <= 16
words[i] only consists of lowercase English letters.
     */
    public static class LongestStringChain {

        // Function to compare two strings and check if they form a valid chain
        static boolean compare(String s1, String s2) {
            if (s1.length() != s2.length() + 1) {
                return false;
            }

            int first = 0;
            int second = 0;

            while (first < s1.length()) {
                if (second < s2.length() && s1.charAt(first) == s2.charAt(second)) {
                    first++;
                    second++;
                } else {
                    first++;
                }
            }

            return first == s1.length() && second == s2.length();
        }

        // Function to find the length of the longest string chain
        static int longestStrChain(List<String> arr) {
            int n = arr.size();

            // Sort the array by string length
            Collections.sort(arr, (a, b) -> {
                return a.length() - b.length();
            });

            int[] dp = new int[n];
            Arrays.fill(dp, 1);

            int maxi = 1;

            for (int i = 0; i < n; i++) {
                for (int prevIndex = 0; prevIndex < i; prevIndex++) {
                    if (compare(arr.get(i), arr.get(prevIndex)) && 1 + dp[prevIndex] > dp[i]) {
                        dp[i] = 1 + dp[prevIndex];
                    }
                }

                if (dp[i] > maxi) {
                    maxi = dp[i];
                }
            }

            return maxi;
        }

        public static void main(String[] args) {
            List<String> words = Arrays.asList("a", "b", "ba", "bca", "bda", "bdca");

            System.out.println("The length of the longest string chain is: " + longestStrChain(words));
        }
    }

    /*
     * http://www.geeksforgeeks.org/dynamic-programming-set-15-longest-bitonic-subsequence/
     * https://www.youtube.com/watch?v=y4vN0WNdrlg&list=PLgUwDviBIf0qUlt5H_kiKYaNSqJ81PMMY&index=47
     *
     * This is derived question from bitonic subsequence
     * https://leetcode.com/problems/minimum-number-of-removals-to-make-mountain-array/solutions/1166577/total-length-length-of-longest-bitonic-sequence/
     * Reference: https://www.youtube.com/watch?v=TWHytKnOPaQ
     * Derived question: Find what the elements rather finding only max count, i think take one extra array and keep that index info
     * for which max is updated.
     * Company:
     * Category: Easy
     * Status: Done
     */
    public static class LongestBitonicSubsequence {

        /*
            The length of the longest bitonic subsequence is 6

            Time Complexity: O(N*N)

            Reason: There are two nested loops that are run twice.

            Space Complexity: O(N)

            Reason: We are only using two rows of size n.
         */
        // Function to find the length of the longest bitonic subsequence
        static int longestBitonicSequence(int[] arr, int n) {
            // Arrays to store lengths of increasing and decreasing subsequences
            int[] dp1 = new int[n];
            int[] dp2 = new int[n];

            // Initialize both arrays with 1, as each element itself is a subsequence of length 1
            Arrays.fill(dp1, 1);
            Arrays.fill(dp2, 1);

            // Calculate the lengths of increasing subsequences
            for (int i = 0; i < n; i++) {
                for (int prevIndex = 0; prevIndex < i; prevIndex++) {
                    if (arr[prevIndex] < arr[i]) {
                        dp1[i] = Math.max(dp1[i], 1 + dp1[prevIndex]);
                    }
                }
            }

            // Reverse the direction of nested loops and calculate the lengths of decreasing subsequences
            for (int i = n - 1; i >= 0; i--) {
                for (int prevIndex = n - 1; prevIndex > i; prevIndex--) {
                    if (arr[prevIndex] < arr[i]) {
                        dp2[i] = Math.max(dp2[i], 1 + dp2[prevIndex]);
                    }
                }
            }

            int maxi = -1;

            // Calculate the length of the longest bitonic subsequence
            for (int i = 0; i < n; i++) {
                maxi = Math.max(maxi, dp1[i] + dp2[i] - 1);
            }

            return maxi;
        }

        public static void main(String[] args) {
            int arr[] = {1, 11, 2, 10, 4, 5, 2, 1};
            int n = arr.length;

            System.out.println("The length of the longest bitonic subsequence is " +
                    longestBitonicSequence(arr, n));
        }
    }

    public static class NumberofLongestIncreasingSubsequences {
        static int findNumberOfLIS(int[] arr){

            int n = arr.length;

            int[] dp= new int[n];
            int[] ct= new int[n];

            Arrays.fill(dp,1);
            Arrays.fill(ct,1);

            int maxi = 1;

            for(int i=0; i<=n-1; i++){
                for(int prev_index = 0; prev_index <=i-1; prev_index ++){

                    if(arr[prev_index]<arr[i] && dp[prev_index]+1>dp[i]){
                        dp[i] = dp[prev_index]+1;
                        //inherit
                        ct[i] = ct[prev_index];
                    }
                    else if(arr[prev_index]<arr[i] && dp[prev_index]+1==dp[i]){
                        //increase the count
                        ct[i] = ct[i] + ct[prev_index];
                    }
                }
                maxi = Math.max(maxi,dp[i]);
            }

            int nos =0;

            for(int i=0; i<=n-1; i++){
                if(dp[i]==maxi) nos+=ct[i];
            }

            return nos;
        }

        public static void main(String args[]) {

            int[] arr = {1,5,4,3,2,6,7,2};

            System.out.println("The count of LIS is "+findNumberOfLIS(arr));

        }
    }
}
