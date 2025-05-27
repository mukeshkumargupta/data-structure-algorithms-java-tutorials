package com.interview.linklist.PartABasicLinkedListOperations.C_FastSlowPointer;

public class B_C_DetectAndPrintCycle {
    private static class ListNode {
        ListNode next;
        int val;

        ListNode(int val) {
            this.val = val;
        }
    }

    // Detect the cycle in the linked list
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                fast = head; // move fast pointer to head
                while (slow != fast) {
                    slow = slow.next;
                    fast = fast.next;
                }
                return slow; // cycle start node
            }
        }
        return null; // no cycle
    }

    // Print the cycle starting from the detected cycle node using while loop
    private void printCycle(ListNode head) {
        ListNode cycleStart = detectCycle(head);
        if (cycleStart == null) {
            System.out.println("No cycle detected.");
            return;
        }

        // Start printing from the cycle start node
        ListNode current = cycleStart;
        System.out.println("Cycle: ");

        // Traverse the cycle using a while loop
        ListNode firstNode = cycleStart; // to track the start node
        current = current.next; // start from the next node
        System.out.println(firstNode.val); // print first node separately

        while (current != firstNode) {
            System.out.println(current.val);
            current = current.next;
        }
    }

    public static void main(String[] args) {
        // Create a sample linked list with a cycle for testing
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node2; // Creates a cycle

        B_C_DetectAndPrintCycle solution = new B_C_DetectAndPrintCycle();
        solution.printCycle(node1); // This should print the cycle nodes
    }
}