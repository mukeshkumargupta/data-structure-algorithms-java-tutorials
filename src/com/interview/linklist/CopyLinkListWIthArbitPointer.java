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
 * https://www.youtube.com/watch?v=VNf6VynfpdM
 * Category: Must Do, Medium
 * Related: https://leetcode.com/problems/clone-binary-tree-with-random-pointer/ Medium
 * https://leetcode.com/problems/clone-n-ary-tree/ Medium
 * 
 * A linked list of length n is given such that each node contains an additional random pointer, which could point to any node in the list, or null.

Construct a deep copy of the list. The deep copy should consist of exactly n brand new nodes, where each new node has its value set to the value of its corresponding original node. Both the next and random pointer of the new nodes should point to new nodes in the copied list such that the pointers in the original list and copied list represent the same list state. None of the pointers in the new list should point to nodes in the original list.

For example, if there are two nodes X and Y in the original list, where X.random --> Y, then for the corresponding two nodes x and y in the copied list, x.random --> y.

Return the head of the copied linked list.

The linked list is represented in the input/output as a list of n nodes. Each node is represented as a pair of [val, random_index] where:

val: an integer representing Node.val
random_index: the index of the node (range from 0 to n-1) that the random pointer points to, or null if it does not point to any node.
Your code will only be given the head of the original linked list.

 

Example 1:


Input: head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
Output: [[7,null],[13,0],[11,4],[10,2],[1,0]]
Example 2:


Input: head = [[1,1],[2,1]]
Output: [[1,1],[2,1]]
Example 3:



Input: head = [[3,null],[3,0],[3,null]]
Output: [[3,null],[3,0],[3,null]]
Example 4:

Input: head = []
Output: []
Explanation: The given linked list is empty (null pointer), so return null.
 

Constraints:

0 <= n <= 1000
-10000 <= Node.val <= 10000
Node.random is null or is pointing to some node in the linked list.
Accepted
647,482
Submissions
1,454,972
 */
public class CopyLinkListWIthArbitPointer {

    static class RandomListNode {
        int label;
        RandomListNode next, random;
        RandomListNode(int x) { this.label = x; }
    }

    void printNode (Node head) {
        Node current = head;
        while (current != null) {
            System.out.println(current.val);
            if (current.next != null) {
                System.out.println("next-> " + current.next.val);
            }
            if (current.random != null) {
                System.out.println("random-> " + current.random.val);
            }
            current = current.next;

        }
        
    }
    public Node copyRandomList1(Node head) {
        /*
         * Runtime: 0 ms, faster than 100.00% of Java online submissions for Copy List with Random Pointer.
Memory Usage: 39 MB, less than 58.96% of Java online submissions for Copy List with Random Pointer.
 * Time complexity is O(n)
 * Space complexity is O(N)
         */
        Map<Node, Node> mapping = new HashMap<>();
        if (head == null) {
            return head;
        }
        Node current = head;
        Node nextNodeTemp = null;
        Node prevOfNewNode = null;
        Node copiedNodeHead = null;
        while(current != null) {
            //Create new node
            Node newNode = new Node(current.val);
            if (prevOfNewNode != null) {//is any previous node
                prevOfNewNode.next = newNode; //make next link
            } else {
                copiedNodeHead = newNode;
            }
            nextNodeTemp = current.next; //backup
            //current.next = newNode; //Noe if you cant modify then take map for mapping
            mapping.put(current, newNode);
            newNode.random = current;
            prevOfNewNode = newNode;
            
            //Go to next node
            current = nextNodeTemp;
        }
        prevOfNewNode.next = null;
        
        //Now setup random pointer for new node
        Node currentNewNode = copiedNodeHead;
        while (currentNewNode != null) {
            Node random = currentNewNode.random.random;
            if (random != null) {
               //currentNewNode.random =  random.next; //Noe if you cant modify then take map for mapping
                currentNewNode.random = mapping.get(random);
            } else {
               currentNewNode.random =  null; 
            }
            
            currentNewNode = currentNewNode.next;
        }
        //
        //printNode(copiedNodeHead);

        
        return copiedNodeHead;
        
    }

    public static void main(String args[]){

        CopyLinkListWIthArbitPointer cll = new CopyLinkListWIthArbitPointer();

        RandomListNode randomListNode = new RandomListNode(-1);
        RandomListNode randomListNode1 = new RandomListNode(4);
        RandomListNode randomListNode2 = new RandomListNode(8);
        RandomListNode randomListNode3 = new RandomListNode(-3);
        RandomListNode randomListNode4 = new RandomListNode(7);
        randomListNode.next = randomListNode1;
        randomListNode1.next = randomListNode2;
        randomListNode2.next = randomListNode3;
        randomListNode3.next = randomListNode4;

        randomListNode.random = randomListNode1;
        randomListNode2.random = randomListNode3;
        randomListNode1.random = randomListNode;
        cll.copyRandomList(randomListNode);
    }
}
