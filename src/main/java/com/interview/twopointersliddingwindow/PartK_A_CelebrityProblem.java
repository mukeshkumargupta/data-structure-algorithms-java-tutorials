package com.interview.twopointersliddingwindow;

/*
https://www.youtube.com/watch?v=cEadsbTeze4&list=PLgUwDviBIf0pOd5zvVVSzgpo6BaCpHT9c&index=17
 */
public class PartK_A_CelebrityProblem {
    /*
    Sure! Let’s discuss the Celebrity Problem using three different approaches: Brute Force, Optimal, and an additional Stack method. This will provide a comprehensive understanding of how to solve the problem.

Problem Recap:
You need to find a celebrity in a group of n people based on the following conditions:

A celebrity knows no one.
Everyone knows the celebrity.
Example:
Given a relationship matrix M, where M[i][j] = 1 means person i knows person j, and M[i][j] = 0 means they do not know each other.

1. Brute Force Approach
In the brute force method, we check each person to see if they meet the criteria for being a celebrity.

Complexity:
Time Complexity:
𝑂
(
𝑛
2
)
O(n
2
 )
Space Complexity:
𝑂
(
1
)
O(1)

     */

    public static class CelebrityProblemBruitforce {
        private boolean knows(int a, int b) {
            // Simulated knows function
            return false; // Dummy implementation
        }

        public int findCelebrity(int n) {
            for (int i = 0; i < n; i++) {
                boolean knowsSomeone = false;
                for (int j = 0; j < n; j++) {
                    if (i != j) {
                        if (knows(i, j)) {
                            knowsSomeone = true; // i knows someone
                            break;
                        }
                    }
                }
                if (!knowsSomeone) { // i does not know anyone
                    boolean knowsMe = true;
                    for (int j = 0; j < n; j++) {
                        if (i != j && !knows(j, i)) {
                            knowsMe = false; // Someone doesn't know i
                            break;
                        }
                    }
                    if (knowsMe) {
                        return i; // i is the celebrity
                    }
                }
            }
            return -1; // No celebrity found
        }
    }

    /*
    Problem Statement Recap
    You need to find a celebrity in a group of n people based on the following conditions:

    A celebrity knows no one.
    Everyone knows the celebrity.
    Two-Pointer Approach
    The idea is to use two pointers to narrow down to a potential celebrity. We start with two pointers at the beginning and end of the list of people. We will move the pointers towards each other based on the knowledge relationship until we have one candidate left. After identifying the candidate, we will verify whether this person is indeed a celebrity.

    Two-Pointer Solution
    Here is the implementation of the two-pointer approach:

    Explanation
    Candidate Identification:

    We use two pointers, left and right.
    If left knows right, then left cannot be a celebrity, and we move the left pointer one step to the right.
    If left does not know right, then right cannot be a celebrity, and we move the right pointer one step to the left.
    This process continues until the two pointers converge, resulting in a single candidate for the celebrity.
    Verification:

    After identifying the candidate, we verify if this candidate meets the celebrity conditions: they should not know anyone, and everyone should know them.
    If the candidate fails any of these checks, we return -1 to indicate that no celebrity exists.
    Complexity
    Time Complexity:
    𝑂
    (
    𝑛
    )
    O(n): The candidate identification and verification each take linear time.
    Space Complexity:
    𝑂
    (
    1
    )
    O(1): We use constant space.
    This two-pointer approach is efficient and straightforward, making it suitable for the Celebrity Problem while adhering to the required conditions.
     */
    public class CelebrityProblemOptimal {
        // Simulated knows function (you'll replace this with the actual implementation)
        private boolean knows(int a, int b) {
            // Replace with actual implementation
            return false; // Dummy implementation
        }

        public int findCelebrity(int n) {
            int left = 0;
            int right = n - 1;

            // Step 1: Find a candidate for the celebrity
            while (left < right) {
                if (knows(left, right)) {
                    // If left knows right, left cannot be a celebrity
                    left++;
                } else {
                    // If left does not know right, right cannot be a celebrity
                    right--;
                }
            }

            // After the loop, left should point to the potential celebrity candidate
            int candidate = left;

            // Step 2: Verify the candidate
            for (int i = 0; i < n; i++) {
                if (i != candidate) {
                    if (knows(candidate, i) || !knows(i, candidate)) {
                        return -1; // candidate cannot be celebrity
                    }
                }
            }
            return candidate; // candidate is the celebrity
        }
    }

}