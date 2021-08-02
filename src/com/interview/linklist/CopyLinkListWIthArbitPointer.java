package com.interview.linklist;

/**
 * Date 03/24/2017
 * @author Mukesh Kumar Gupta
 *
 * A linked list is given such that each TreeNode contains an additional random pointer which could point
 * to any TreeNode in the list or null. Return a deep copy of the list.
 *
 * Time complexity is O(n)
 * Space complexity is O(1)
 *
 * https://leetcode.com/problems/copy-list-with-random-pointer/
 */
public class CopyLinkListWIthArbitPointer {

    static class RandomListTreeNode {
        int label;
        RandomListTreeNode next, random;
        RandomListTreeNode(int x) { this.label = x; }
    }

    public RandomListTreeNode copyRandomList(RandomListTreeNode head) {
        if (head == null) {
            return null;
        }

        RandomListTreeNode current = head;
        while (current != null) {
            RandomListTreeNode newTreeNode = new RandomListTreeNode(current.label);
            newTreeNode.next = current.next;
            newTreeNode.random = current.random;
            current.next = newTreeNode;
            current = newTreeNode.next;
        }

        current = head;
        while (current != null) {
            RandomListTreeNode next = current.next;
            if (next.random != null) {
                next.random = next.random.next;
            }
            current = current.next.next;
        }

        current = head;
        RandomListTreeNode dummy = new RandomListTreeNode(0);
        RandomListTreeNode newCurrent = dummy;
        while (current != null) {
            newCurrent.next = current.next;
            newCurrent = newCurrent.next;
            current.next = current.next.next;
            current = current.next;
        }

        return dummy.next;
    }

    public static void main(String args[]){

        CopyLinkListWIthArbitPointer cll = new CopyLinkListWIthArbitPointer();

        RandomListTreeNode randomListTreeNode = new RandomListTreeNode(-1);
        RandomListTreeNode randomListTreeNode1 = new RandomListTreeNode(4);
        RandomListTreeNode randomListTreeNode2 = new RandomListTreeNode(8);
        RandomListTreeNode randomListTreeNode3 = new RandomListTreeNode(-3);
        RandomListTreeNode randomListTreeNode4 = new RandomListTreeNode(7);
        randomListTreeNode.next = randomListTreeNode1;
        randomListTreeNode1.next = randomListTreeNode2;
        randomListTreeNode2.next = randomListTreeNode3;
        randomListTreeNode3.next = randomListTreeNode4;

        randomListTreeNode.random = randomListTreeNode1;
        randomListTreeNode2.random = randomListTreeNode3;
        randomListTreeNode1.random = randomListTreeNode;
        cll.copyRandomList(randomListTreeNode);
    }
}
