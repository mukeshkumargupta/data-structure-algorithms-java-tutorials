package com.interview.linklist;

class TreeNodeRef{
    TreeNode TreeNode;
    public void next(){
        TreeNode = TreeNode.next;
    }
}

class TreeNode{
    int val;
    TreeNode next;
    TreeNode before;
    TreeNode child;
    Object obj;
    
    public static TreeNode newTreeNode(int val, Object... obj){
        TreeNode n = new TreeNode();
        n.val = val;
        n.next = null;
        n.before = null;
        if(obj.length > 0)
        {    
            n.obj = obj[0];
        }
        return n;
    }
}

public class LinkList {
    
    public TreeNode addTreeNode(int val, TreeNode head, Object... obj){
        TreeNode temp = head;
        TreeNode n = null;
        if(obj.length > 0){
            n = TreeNode.newTreeNode(val, obj[0]);
        }else{
            n = TreeNode.newTreeNode(val);
        }
        if(head == null){
            return n;
        }
        
        while(head.next != null){
            head = head.next;
        }
        
        head.next = n;
        return temp;
    }
    
    public TreeNode addAtFront(TreeNode TreeNode, TreeNode head){
        if(head == null){
            return TreeNode;
        }
        TreeNode.next = head;
        return TreeNode;
    }
    
    public TreeNode reverse(TreeNode head){
        TreeNode front = null;
        TreeNode middle = head;
        TreeNode end = null;
        while(middle != null){
            end = middle.next;
            middle.next = front;
            front = middle;
            middle = end;
        }
        return front;
    }

    public TreeNode reverseRecursiveEasy(TreeNode head){
        if(head == null || head.next == null){
            return head;
        }
        
        TreeNode reversedList = reverseRecursiveEasy(head.next);
        head.next.next = head;
        head.next = null;
        return reversedList;
    }
    
    public void reverseRecursive(TreeNodeRef headRef){
        if(headRef.TreeNode == null){
            return;
        }
        TreeNode first = headRef.TreeNode;
        TreeNode last = headRef.TreeNode.next;
        if(last == null){
            return;
        }
        TreeNodeRef lastHeadRef = new TreeNodeRef();
        lastHeadRef.TreeNode = last;
        reverseRecursive(lastHeadRef);
        first.next.next = first;
        first.next = null;
        headRef.TreeNode = lastHeadRef.TreeNode;
    }
    
    public TreeNode addAtFront(int val, TreeNode head){
        TreeNode TreeNode = TreeNode.newTreeNode(val);
        return addAtFront(TreeNode,head);
    }
    
    public void printList(TreeNode head){
        while(head != null){
            System.out.println(head.val);
            head = head.next;
        }
    }
    
    public TreeNode find(TreeNode head, int val){
        while(head != null){
            if(head.val == val){
                return head;
            }
            head = head.next;
        }
        return null;
    }
    
    
    public int size(TreeNode head){
        int size =0;
        while(head != null){
            size++;
            head = head.next;
        }
        return size;
    }
    public static void main(String args[]){
        LinkList ll = new LinkList();
        TreeNode head = null;
        head = ll.addTreeNode(1, head);
        head = ll.addTreeNode(2, head);
        head = ll.addTreeNode(3, head);
        head = ll.addTreeNode(4, head);
        head = ll.addTreeNode(5, head);
        head = ll.addTreeNode(6, head);
        ll.printList(head);
        
    //  TreeNodeRef headRef = new TreeNodeRef();
    //  headRef.TreeNode = head;
    //  ll.reverseRecursive(headRef);
    //  head = headRef.TreeNode;
    //  ll.printList(head);
        System.out.println();
        head = ll.reverseRecursiveEasy(head);
        ll.printList(head);
    }
}
     
        static int Add (int a,int b){
            int result (a+b);
             return result;
        }
        static int Sub
        
        
        
        