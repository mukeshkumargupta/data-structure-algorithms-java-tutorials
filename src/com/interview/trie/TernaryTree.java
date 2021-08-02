package com.interview.trie;

/**
 * http://www.geeksforgeeks.org/ternary-search-tree/
 */
public class TernaryTree {

    private TreeNode root = null;
    
    class TreeNode{
        char val;
        boolean isLeaf;
        TreeNode left, right, eq;
    }
    
    public void insert(String val){
        TreeNode root = insert(this.root,val,0);
        this.root = root;
    }
    
    public boolean search(String val){
        return search(root,val,0);
    }
    
    private boolean search(TreeNode root,String val,int pos){
        if(pos == val.length()){
            return true;
        }
        if(root == null){
            return false;
        }
        if(root.val == val.charAt(pos)){
            boolean result = search(root.eq,val,pos+1);
            if(pos == val.length() -1){
                return result && root.isLeaf;
            }
            return result;
        }else if(root.val < val.charAt(pos)){
            return search(root.right,val,pos);
        }else{
            return search(root.left,val,pos);
        }
    }
    private TreeNode insert(TreeNode root,String val,int pos){
        if(pos == val.length()){
            return root;
        }
        if(root == null){
            root = new TreeNode();
            root.val = val.charAt(pos);
            root.eq = insert(root.eq,val,pos+1);
            if(pos == (val.length()-1)){
                root.isLeaf = true;
            }
        }else{
            if(root.val == val.charAt(pos)){
                root.eq = insert(root.eq,val,pos+1);
                if(pos == (val.length()-1)){
                    root.isLeaf = true;
                }
            }
            else if(root.val < val.charAt(pos)){
                root.right = insert(root.right,val,pos);
            }else{
                root.left = insert(root.left,val,pos);
            }
        }
        return root;
    }
    
    public static void main(String args[]){
        TernaryTree tt = new TernaryTree();
        tt.insert("cute");
        tt.insert("as");
        tt.insert("at");
        tt.insert("cut");
        tt.insert("cup");
        tt.insert("time");
        tt.insert("tax");
        tt.insert("bat");
        System.out.println(tt.search("cute"));
        System.out.println(tt.search("cut"));
        System.out.println(tt.search("tax"));
        System.out.println(tt.search("as"));
        System.out.println(tt.search("abat"));
        
    }
}
