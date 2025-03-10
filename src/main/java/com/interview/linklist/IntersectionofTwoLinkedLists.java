package com.interview.linklist;
/*
 * Reference:https://leetcode.com/problems/intersection-of-two-linked-lists
 * https://www.youtube.com/watch?v=u4FWXfgS8jw
 * Category: Easy
 * Input: intersectVal = 8, listA = [4,1,8,4,5], listB = [5,6,1,8,4,5], skipA = 2, skipB = 3
 * 
 * Related: https://leetcode.com/problems/minimum-index-sum-of-two-lists/ Easy
Output: Intersected at '8'
Explanation: The intersected TreeNode's value is 8 (note that this must not be 0 if the two lists intersect).
From the head of A, it reads as [4,1,8,4,5]. From the head of B, it reads as [5,6,1,8,4,5]. There are 2 TreeNodes before the intersected TreeNode in A; There are 3 TreeNodes before the intersected TreeNode in B.


Given the heads of two singly linked-lists headA and headB, return the node at which the two lists intersect. If the two linked lists have no intersection at all, return null.

For example, the following two linked lists begin to intersect at node c1:


The test cases are generated such that there are no cycles anywhere in the entire linked structure.

Note that the linked lists must retain their original structure after the function returns.

Custom Judge:

The inputs to the judge are given as follows (your program is not given these inputs):

intersectVal - The value of the node where the intersection occurs. This is 0 if there is no intersected node.
listA - The first linked list.
listB - The second linked list.
skipA - The number of nodes to skip ahead in listA (starting from the head) to get to the intersected node.
skipB - The number of nodes to skip ahead in listB (starting from the head) to get to the intersected node.
The judge will then create the linked structure based on these inputs and pass the two heads, headA and headB to your program. If you correctly return the intersected node, then your solution will be accepted.

 

Example 1:


Input: intersectVal = 8, listA = [4,1,8,4,5], listB = [5,6,1,8,4,5], skipA = 2, skipB = 3
Output: Intersected at '8'
Explanation: The intersected node's value is 8 (note that this must not be 0 if the two lists intersect).
From the head of A, it reads as [4,1,8,4,5]. From the head of B, it reads as [5,6,1,8,4,5]. There are 2 nodes before the intersected node in A; There are 3 nodes before the intersected node in B.
Example 2:


Input: intersectVal = 2, listA = [1,9,1,2,4], listB = [3,2,4], skipA = 3, skipB = 1
Output: Intersected at '2'
Explanation: The intersected node's value is 2 (note that this must not be 0 if the two lists intersect).
From the head of A, it reads as [1,9,1,2,4]. From the head of B, it reads as [3,2,4]. There are 3 nodes before the intersected node in A; There are 1 node before the intersected node in B.
Example 3:


Input: intersectVal = 0, listA = [2,6,4], listB = [1,5], skipA = 3, skipB = 2
Output: No intersection
Explanation: From the head of A, it reads as [2,6,4]. From the head of B, it reads as [1,5]. Since the two lists do not intersect, intersectVal must be 0, while skipA and skipB can be arbitrary values.
Explanation: The two lists do not intersect, so return null.
 

Constraints:

The number of nodes of listA is in the m.
The number of nodes of listB is in the n.
0 <= m, n <= 3 * 104
1 <= Node.val <= 105
0 <= skipA <= m
0 <= skipB <= n
intersectVal is 0 if listA and listB do not intersect.
intersectVal == listA[skipA] == listB[skipB] if listA and listB intersect.
 

Follow up: Could you write a solution that runs in O(n) time and use only O(1) memory?
 */
public class IntersectionofTwoLinkedLists {
      public class ListNode {
      int val;
      ListNode next;
      ListNode(int x) {
          val = x;
          next = null;
      }
  }
    private static class Bruitforce {
        /**
         * 1. Brute Force Approach:
         * The brute force approach involves comparing every node from headA with every node from headB.
         * If a match is found, it means the lists intersect at that node.
         *
         * Time Complexity:
         * - The outer loop traverses list A (length m).
         * - The inner loop traverses list B (length n).
         *
         * Overall time complexity:
         * O(m × n), since we compare each node of list A with every node of list B.
         *
         * Space Complexity:
         * O(1), since we only use a few pointers and no extra space for storing nodes.
         */

        public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
            // Traverse list A
            ListNode p1 = headA;
            while (p1 != null) {
                // Traverse list B for each node in list A
                ListNode p2 = headB;
                while (p2 != null) {
                    if (p1 == p2) { // If they meet, this is the intersection node
                        return p1;
                    }
                    p2 = p2.next;
                }
                p1 = p1.next;
            }
            return null; // No intersection found
        }
    }

    /**
     * 2. Optimized Approach:
     * The optimized solution involves leveraging the lengths of the two linked lists to find the intersection in a more efficient way.
     *
     * Steps in Optimized Approach:
     * 1. Find the length of both lists.
     * 2. Align the two pointers: Move the pointer of the longer list by the difference in lengths,
     *    so both pointers are at the same distance from the end.
     * 3. Traverse both lists: Traverse both lists simultaneously. The first node where the pointers meet
     *    will be the intersection node.
     *
     * Time Complexity:
     * - Finding the length of both lists:
     *   O(m + n), where m and n are the lengths of the two lists.
     * - Aligning the pointers:
     *   O(|m - n|), where |m - n| is the absolute difference in lengths.
     * - Traversing both lists:
     *   O(m + n) as we traverse both lists in parallel.
     *
     * Overall time complexity:
     * O(m + n), which is significantly better than the brute force approach.
     *
     * Space Complexity:
     * O(1), because we only use a few pointers for traversal and no extra space is required.
     */
    private static class Better {
        public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
            // Step 1: Find the lengths of both lists
            int lenA = 0, lenB = 0;
            ListNode pA = headA, pB = headB;

            while (pA != null) {
                lenA++;
                pA = pA.next;
            }

            while (pB != null) {
                lenB++;
                pB = pB.next;
            }

            // Step 2: Reset pointers
            pA = headA;
            pB = headB;

            // Step 3: Align both pointers to the same distance from the end
            if (lenA > lenB) {
                for (int i = 0; i < lenA - lenB; i++) {
                    pA = pA.next;
                }
            } else {
                for (int i = 0; i < lenB - lenA; i++) {
                    pB = pB.next;
                }
            }

            // Step 4: Traverse both lists and find the intersection node
            while (pA != null && pB != null) {
                if (pA == pB) {
                    return pA; // Intersection node found
                }
                pA = pA.next;
                pB = pB.next;
            }

            return null; // No intersection found
        }
    }

    /**
     * 3. Further Optimized (Space-saving) Solution:
     * Another space-efficient approach is to avoid calculating the length of both lists.
     * Instead, we can traverse both lists simultaneously and handle the case of the
     * difference in length using the concept of "cycling" through both lists.
     * If one pointer reaches the end of its list, it is redirected to the head of the other list,
     * essentially simulating an equal-length traversal.
     *
     * Time Complexity:
     * This solution also takes O(m + n) time, as we go through both lists twice (in the worst case),
     * but it's more space-efficient.
     *
     * Space Complexity:
     * O(1) since we’re not using any extra space, aside from the pointers.
     *
     * Summary of Approaches:
     * Approach             | Time Complexity     | Space Complexity  | Explanation
     * -------------------- | ------------------- | ----------------- | -----------------------------------------------------------
     * Brute Force          | O(m * n)            | O(1)              | Compare each node of list A with every node in list B.
     * Optimized by Length  | O(m + n)            | O(1)              | Use length difference to align the lists before traversing.
     * Optimized by Cycling | O(m + n)            | O(1)              | Cycle through both lists and check for intersection.
     *
     * The optimized approaches improve the performance by reducing the number of comparisons and operations,
     * making them suitable for large input sizes.
     *
     * Dry Run (Tabular View):
     * -----------------------------------------------------------
     * Iteration | pA (List A) | pB (List B) | Explanation
     * -----------------------------------------------------------
     * 0         | 1           | 10          | Start at head of List A and List B. Both pointers are at the head nodes.
     * 1         | 2           | 11          | pA moves to node 2, pB moves to node 11. They are not equal.
     * 2         | 3           | 12          | pA moves to node 3, pB moves to node 12. They are not equal.
     * 3         | 4           | 7           | pA moves to node 4, pB moves to node 7. They are not equal.
     * 4         | 5           | 8           | pA moves to node 5, pB moves to node 8. They are not equal.
     * 5         | 6           | 9           | pA moves to node 6, pB moves to node 9. They are not equal.
     * 6         | 7           | null        | pA moves to node 7. pB reaches the end of List B, so pB is redirected to List A.
     * 7         | 8           | 1           | pA moves to node 8, pB is now at node 1 (head of List A).
     * 8         | 9           | 2           | pA moves to node 9, pB moves to node 2. They are not equal.
     * 9         | null        | 3           | pA reaches the end of List A. pA is redirected to List B. pB moves to node 3.
     * 10        | 10          | 4           | pA is now at node 10 (head of List B). pB moves to node 4.
     * 11        | 11          | 5           | pA moves to node 11, pB moves to node 5. They are not equal.
     * 12        | 12          | 6           | pA moves to node 12, pB moves to node 6. They are not equal.
     * 13        | 7           | 7           | pA moves to node 7, pB moves to node 7. They are equal, so we found the intersection node.
     */
    private static class Optimal {
        public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
            ListNode pA = headA;
            ListNode pB = headB;

            // Traverse both lists. If they intersect, they will meet at the intersection node.
            // Otherwise, both will become null at the same time after one full cycle.
            while (pA != pB) {
                pA = (pA == null) ? headB : pA.next; // If pA reaches the end of list A, start at headB
                pB = (pB == null) ? headA : pB.next; // If pB reaches the end of list B, start at headA
            }

            return pA; // If there is no intersection, pA and pB will both be null
        }
    }
}
