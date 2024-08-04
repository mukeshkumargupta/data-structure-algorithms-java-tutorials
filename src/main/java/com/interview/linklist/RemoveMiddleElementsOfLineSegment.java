package com.interview.linklist;

/**
 * http://www.geeksforgeeks.org/given-linked-list-line-segments-remove-middle-points/
 * @author Mukesh Kumar Gupta
 * Test cases:
 * 0 or more TreeNodes
 * All points are edge of line segments so nothing gets removed
 * One long line so all points in between get removed
 *
 */

class Point{
    int x;
    int y;
    Point(int x, int y){
        this.x = x;
        this.y = y;
    }
}

public class RemoveMiddleElementsOfLineSegment {

    public void remove(TreeNode head){
        if(head == null || head.next == null){
            return;
        }
        TreeNode curr = head;
        TreeNode next = head.next;
        TreeNode nextNext = head.next.next;
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
    
    public static void main(String args[]){
        TreeNode head1 = null;
      
      LinkList ll = new LinkList();
      head1 = ll.addTreeNode(1, head1, new Point(0,10));
      head1 = ll.addTreeNode(2, head1, new Point(1,10));
      head1 = ll.addTreeNode(3, head1, new Point(5,10));
      head1 = ll.addTreeNode(4, head1, new Point(7,10));
      head1 = ll.addTreeNode(5, head1, new Point(7,5));
      head1 = ll.addTreeNode(6, head1, new Point(20,5));
      head1 = ll.addTreeNode(7, head1, new Point(40,5));
      head1 = ll.addTreeNode(8, head1, new Point(40,8));
           
      RemoveMiddleElementsOfLineSegment rme = new RemoveMiddleElementsOfLineSegment();
      rme.remove(head1);
      ll.printList(head1);
    }
}
