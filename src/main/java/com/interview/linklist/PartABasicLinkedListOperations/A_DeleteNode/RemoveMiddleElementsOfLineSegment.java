package com.interview.linklist.PartABasicLinkedListOperations.A_DeleteNode;

/**
 * http://www.geeksforgeeks.org/given-linked-list-line-segments-remove-middle-points/
 * @author Mukesh Kumar Gupta
 * Test cases:
 * 0 or more TreeNodes
 * All points are edge of line segments so nothing gets removed
 * One long line so all points in between get removed
 *
 */



public class RemoveMiddleElementsOfLineSegment {
    class Point{
        int x;
        int y;
        Point(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    private static class ListNode {
        int val;
        ListNode next;
        Point obj;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }

    }
    public void remove(ListNode head){
        if(head == null || head.next == null){
            return;
        }
        ListNode curr = head;
        ListNode next = head.next;
        ListNode nextNext = head.next.next;
        while(nextNext != null){
            
            Point pcurr = (Point)curr.obj;
            Point pnext = (Point)nextNext.obj;
            if(pcurr.x == pnext.x || pcurr.y == pnext.y){
                curr.next = nextNext;
                next = nextNext;
                nextNext = nextNext.next;
            }else{
                curr = curr.next;
                next = next.next;
                nextNext = nextNext.next;
            }
            
        }
    }

}
