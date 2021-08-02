package com.interview.linklist;

/*
 * http://www.geeksforgeeks.org/maximum-sum-linked-list-two-sorted-linked-lists-common-TreeNodes/
 * Test cases
 * Test that chains never meet
 * Test that chain meets only once
 * Test that chain meets multipe times
 * Test that one chain ends where it meets chain 2
 */
public class MergeForLargestSum {

    TreeNode maxChain(TreeNode head1, TreeNode head2){
        if(head1 == null){
            return head2;
        }
        if(head2 == null){
            return head1;
        }
        TreeNode curr1 = head1;
        TreeNode curr2 = head2;
        int sum1 = 0;
        int sum2 = 0;
        TreeNode result = null;
        TreeNode prev = null;
        while(curr1!= null && curr2 != null){
            if(curr1.val == curr2.val){
                sum1 += curr1.val;
                sum2 += curr2.val;
                if(sum1 <= sum2){
                    if(result == null){
                        result = head2;
                        prev = curr2;
                    }else{
                        prev.next = head2.next;
                        prev = curr2;
                    }
                }else{
                    if(result == null){
                        result = head1;
                        prev = curr1;
                    }else{
                        prev.next = head1.next;
                        prev = curr1;
                    }
                }
                head1 = curr1;
                head2 = curr2;
                sum1 = 0;
                sum2 = 0;
                curr1 = curr1.next;
                curr2 = curr2.next;
            }
            else if(curr1.val < curr2.val){
                sum1 += curr1.val;
                curr1 = curr1.next;
            }
            else{
                sum2 += curr2.val;
                curr2 = curr2.next;
            }
        }
        while(curr1 != null){
            sum1+= curr1.val;
            curr1 = curr1.next;
        }
        while(curr2 != null){
            sum2 += curr2.val;
            curr2 = curr2.next;
        }
        if(result != null){
        if(sum1 <= sum2){
            prev.next = head2.next;
        }else{
            prev.next = head1.next;
        }
        }else{
            if(sum1 <= sum2){
                result = head2;
            }else{
                result = head1;
            }
        }
        return result;
    }
    
    public static void main(String args[]){
        LinkList ll = new LinkList();
        TreeNode head1 = null;
        head1 = ll.addTreeNode(1, head1);
        head1 = ll.addTreeNode(3, head1);
        head1 = ll.addTreeNode(30, head1);
        head1 = ll.addTreeNode(90, head1);
        head1 = ll.addTreeNode(120, head1);
        head1 = ll.addTreeNode(240, head1);
        head1 = ll.addTreeNode(243, head1);
        head1 = ll.addTreeNode(251, head1);
        head1 = ll.addTreeNode(511, head1);
        TreeNode head2 = null;
        head2 = ll.addTreeNode(0, head2);
        head2 = ll.addTreeNode(3, head2);
        head2 = ll.addTreeNode(12, head2);
        head2 = ll.addTreeNode(32, head2);
        head2 = ll.addTreeNode(90, head2);
        head2 = ll.addTreeNode(125, head2);
        head2 = ll.addTreeNode(240, head2);
        head2 = ll.addTreeNode(249, head2);
        head2 = ll.addTreeNode(251, head2);
        head2 = ll.addTreeNode(260, head2);
        
        MergeForLargestSum mls = new MergeForLargestSum();
        TreeNode result = mls.maxChain(head1, head2);
        ll.printList(result);
    }
}
