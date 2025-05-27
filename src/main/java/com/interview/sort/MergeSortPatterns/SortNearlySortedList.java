package com.interview.sort.MergeSortPatterns;

/**
 * Given a linklist which has individual sorted componenets sort the entire linst
 * e.g 1-3-6-8-4-5-10-7-9
 * Here 1,2,6,8 are sorted, 4,5,10 are sorted and 7,9 are sorted
 * Test case
 * null TreeNode
 * 1 TreeNode
 * 2 sorted TreeNodes
 * 2 reverse sorted TreeNodes
 * 3 reverse sorted TreeNodes
 * 4 TreeNodes 2 each sorted among themselves
 */
public class SortNearlySortedList {

    public TreeNode sort(TreeNode head){
        TreeNode result = null;
        TreeNode start = head;
        while(head != null && head.next != null){
            if(head.val < head.next.val){
                head = head.next;
            }else{
                TreeNode temp = head.next;
                head.next = null;
                result = mergeSort(result,start);
                head = temp;
                start = temp;
            }
        }
        result = mergeSort(result,start);
        return result;
    }
    
    private TreeNode mergeSort(TreeNode head1,TreeNode head2){
        if(head1 == null){
            return head2;
        }
        if(head2 == null){
            return head1;
        }
        if(head1.val <= head2.val){
            head1.next = mergeSort(head1.next,head2);
            return head1;
        }else{
            head2.next = mergeSort(head1,head2.next);
            return head2;
        }
    }
    
    public static void main(String args[]){
        LinkList ll = new LinkList();
        TreeNode head = null;
        head = ll.addTreeNode(1, head);
        head = ll.addTreeNode(2, head);
        head = ll.addTreeNode(3, head);
        head = ll.addTreeNode(7, head);
        head = ll.addTreeNode(5, head);
        head = ll.addTreeNode(6, head);
        head = ll.addTreeNode(13, head);
        head = ll.addTreeNode(11, head);
        head = ll.addTreeNode(12, head);
        
        SortNearlySortedList sns = new SortNearlySortedList();
        head = sns.sort(head);
        ll.printList(head);
    }
}
