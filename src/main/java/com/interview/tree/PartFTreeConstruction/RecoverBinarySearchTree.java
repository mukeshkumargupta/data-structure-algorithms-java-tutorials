package com.interview.tree.PartFTreeConstruction;

import com.interview.tree.TreeNode;

/*
 * https://leetcode.com/problems/recover-binary-search-tree/submissions/
 * https://www.youtube.com/watch?v=ZWGW7FminDM
 * Category: Medium, Tricky
 * https://leetcode.com/problems/concatenated-words/ Hard
 * https://leetcode.com/problems/split-bst/ Medium
 * https://leetcode.com/problems/sum-of-distances-in-tree/ Hard
 * https://leetcode.com/problems/find-mode-in-binary-search-tree/ Easy Imp
 * https://leetcode.com/problems/swim-in-rising-water/ Hard VVImp
 * https://leetcode.com/problems/find-all-groups-of-farmland/ Medium VImp
 */
public class RecoverBinarySearchTree {
    //Bruitforce like, take any order traversal then sort array, then do inorder and place value one by one
    //So sortign will take NlogN TC so that is not good solution
    private void inorderTraversal(TreeNode root, List<TreeNode> inorder) {
        if (root == null) {
            return;
        }
        inorderTraversal(root.left, inorder);
        inorder.add(root);
        inorderTraversal(root.right, inorder);
        
    }
    public void recoverTree(TreeNode root) {//runtime 86.23
        /*
         * 
         * This is application of array in which two element is swapped
         * TC: O(2N)
         * 
         * SC: O(N) auxaliry space, incase of moris inorder O(1)
         */
        List<TreeNode> inorder = new ArrayList<>();
        inorderTraversal(root, inorder);
        
        class Point{
            int i1;
            int i2;
        }
        
        Point p1 = null;
        Point p2 = null;
            
        int size = inorder.size();
        for (int i = 0; i < size; i++) {
            if (i != size-1) {
               if (inorder.get(i).val > inorder.get(i+1).val) {
                   if (p1 == null) {
                        p1 = new Point();
                        p1.i1 = i;
                        p1.i2 = i+1; 
                       //System.out.println(p1.i1 + " -> " + p1.i2);
                   } else {
                        p2 = new Point();
                        p2.i1 = i;
                        p2.i2 = i+1;
                       //System.out.println(p2.i1 + " -> " + p2.i2);
                   }

               } 
            }
        }
         if (p1 != null && p2 != null) {
             TreeNode temp1 = inorder.get(p1.i1);
             TreeNode temp2 = inorder.get(p2.i2);
             int temp = temp1.val;
             temp1.val = temp2.val;
             temp2.val = temp;
             

         } else {
             TreeNode temp1 = inorder.get(p1.i1);
             TreeNode temp2 = inorder.get(p1.i2);
             int temp = temp1.val;
             temp1.val = temp2.val;
             temp2.val = temp; 
         }

        
        
    }
    
    private TreeNode first;
    private TreeNode prev;
    private TreeNode firstNext;
    private TreeNode last; 
    private void inorder(TreeNode root) {
        if(root == null) return; 
        
        inorder(root.left);
        
        if (prev != null && (root.val < prev.val))
        {
           
            // If this is first violation, mark these two nodes as
            // 'first' and 'middle'
            if ( first == null )
            {
                first = prev;
                firstNext = root;
            }
 
            // If this is second violation, mark this node as last
            else
                last = root;
        }
 
        // Mark this node as previous
        prev = root;
        inorder(root.right); 
    }
    public void recoverTreeOptimized(TreeNode root) {
        first = firstNext = last = null; 
        inorder(root);
        if(first!=null && last!=null) {//if both are not adjusant
            int t = first.val;
            first.val = last.val;
            last.val = t; 
        }
        else if(first!=null && firstNext!=null)  {
            int t = first.val;
            first.val = firstNext.val;
            firstNext.val = t; 
        }
    }
}
