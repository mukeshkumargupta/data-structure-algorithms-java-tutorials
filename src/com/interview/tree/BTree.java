package com.interview.tree;

/**
 * http://www.geeksforgeeks.org/b-tree-set-1-insert-2/
 * http://www.geeksforgeeks.org/b-tree-set-1-introduction-2/
 */
public class BTree {
    private BTreeNode root = null;
    private static int T = 2;
    public void insert(int val){
        if(root == null){
            root = BTreeNode.newTreeNode(val);
            return;
        }
        SplitResult sr = insert(root,val);
        if(sr != null){
            BTreeNode newRoot = BTreeNode.newTreeNode();
            newRoot.n = 1;
            newRoot.isLeaf = false;
            newRoot.keys[0] = sr.c;
            newRoot.child[0] = sr.r1;
            newRoot.child[1] = sr.r2;
            root = newRoot;
        }
    }
    
    public boolean search(int val){
        return search(root,val);
    }
    
    public boolean search(BTreeNode root, int val){
        int i =0;
        while(i < root.n && root.keys[i] < val){
            i++;
        }
        if(i < root.n && root.keys[i] == val){
            return true;
        }
        if(root.isLeaf){
            return false;
        }
        return search(root.child[i],val);
    }
    
    private SplitResult insert(BTreeNode root,int val){
        if(root.isLeaf){
            if(!root.isFull()){
                root.insertKey(val, null, null);
                return null;
            }else{
                SplitResult sr = splitTreeNode(root,val,null,null);
                return sr;
            }
        }else{
            int i=0;
            for(; i < root.n; i++){
                if(val <= root.keys[i]){
                    SplitResult sr = insert(root.child[i],val);
                    if(sr == null){
                        return null;
                    }else{
                        if(!root.isFull()){
                            root.insertKey(sr.c, sr.r1, sr.r2);
                            return null;
                        }else{
                            SplitResult sr1 = splitTreeNode(root,sr.c,sr.r1,sr.r2);
                            return sr1;
                        }
                    }
                }
            }
            if(i == root.n){
                SplitResult sr = insert(root.child[i],val);
                if(sr == null){
                    return null;
                }else{
                    if(!root.isFull()){
                        root.insertKey(sr.c, sr.r1, sr.r2);
                        return null;
                    }else{
                        SplitResult sr1 = splitTreeNode(root,sr.c,sr.r1,sr.r2);
                        return sr1;
                    }
                }
            }
        }
        return null;
    }
    
    private SplitResult splitTreeNode(BTreeNode TreeNode,int val, BTreeNode nr1, BTreeNode nr2){
        int c = TreeNode.keys[TreeNode.n/2];
        BTreeNode r1 = BTreeNode.newTreeNode();
        BTreeNode r2 = BTreeNode.newTreeNode();
        r1.n = TreeNode.n/2;
        r2.n = TreeNode.n - TreeNode.n/2-1;
        if(!TreeNode.isLeaf){
            r1.isLeaf = false;
            r2.isLeaf = false;
        }
        int i=0;
        for(; i < TreeNode.n/2; i++){
            r1.keys[i] = TreeNode.keys[i];
            r1.child[i] = TreeNode.child[i];
        }
        r1.child[i] = TreeNode.child[i];
        i = TreeNode.n/2 + 1;
        int j=0;
        for(;i < TreeNode.n; i++,j++){
            r2.keys[j] = TreeNode.keys[i];
            r2.child[j] = TreeNode.child[i];
        }
        r2.child[j] = TreeNode.child[i];
        if(val < c){
            r1.insertKey(val, nr1, nr2);
        }else{
            r2.insertKey(val, nr1, nr2);
        }
        SplitResult sr = new SplitResult();
        sr.c = c;
        sr.r1 = r1;
        sr.r2 = r2;
        return sr;
    }
    
    class SplitResult{
        BTreeNode r1;
        BTreeNode r2;
        int c;
    }
    
    public void traverse(){
        traverse(root);
    }
    
    private void traverse(BTreeNode root){
        for(int i=0; i < root.n; i++){
            if(!root.isLeaf){
                traverse(root.child[i]);
            }
            System.out.print(root.keys[i] + " ");
        }
        if(!root.isLeaf){
            traverse(root.child[root.n]);
        }
    }
    
    static class BTreeNode{
        int n ;
        BTreeNode[] child = new BTreeNode[2*T];
        int keys[] = new int[2*T-1];
        boolean isLeaf;
        
        public void insertKey(int val,BTreeNode r1,BTreeNode r2){
            int i = n-1;
            while(i >=0 && val < keys[i]){
                keys[i+1] = keys[i];
                i--;
            }
            keys[i+1] = val;
            int j = n;
            while(j > i+1){
                child[j+1] = child[j];
                j--;
            }
            child[j] = r1;
            child[j+1] = r2;
            n++;
        }
        
        public static BTreeNode newTreeNode(int val){
            BTreeNode TreeNode = new BTreeNode();
            TreeNode.keys[0] = val;
            TreeNode.isLeaf = true;
            TreeNode.n = 1;
            return TreeNode;
        }
        
        public static BTreeNode newTreeNode(){
            BTreeNode TreeNode = new BTreeNode();
            TreeNode.isLeaf = true;
            TreeNode.n = 0;
            return TreeNode;
        }
        
        public boolean isFull(){
            return 2*T - 1 == n;
        }
    }
    
    public static void main(String args[]){
        BTree bTree = new BTree();
        bTree.insert(5);
        bTree.insert(4);
        bTree.insert(3);
        bTree.insert(2);
        bTree.insert(1);
        bTree.insert(6);
        bTree.insert(11);
        bTree.insert(13);
        bTree.insert(8);
        bTree.insert(7);
        bTree.insert(10);
        bTree.insert(9);
        bTree.insert(28);
        bTree.insert(22);
        bTree.insert(12);
        bTree.insert(18);
        bTree.insert(16);
        bTree.traverse();
        System.out.print(bTree.search(28));
        System.out.print(bTree.search(11));
        System.out.print(bTree.search(5));
        System.out.print(bTree.search(21));
        System.out.print(bTree.search(3));
        System.out.print(bTree.search(4));
        System.out.print(bTree.search(14));
    }
}

