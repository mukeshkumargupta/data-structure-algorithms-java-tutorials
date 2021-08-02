package com.interview.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *  Date 11/01/2017
 *  @author Tushar
 *
 *  Encode and decode binary tree using succinct encoding technique
 *
 * References - http://www.geeksforgeeks.org/succinct-encoding-of-binary-tree/
 * https://en.wikipedia.org/wiki/Binary_tree#Succinct_encodings
 */
public class SuccinctTree {

    public static class Result {
        List<Integer> binaryRep = new ArrayList<>();
        List<Integer> actualval = new ArrayList<>();
    }

    public Result encode(TreeNode root) {
        Result r = new Result();
        encode(root, r);
        return r;
    }

    private void encode(TreeNode root, Result r) {
        if(root == null) {
            r.binaryRep.add(0);
            return;
        }
        r.actualval.add(root.val);
        r.binaryRep.add(1);

        encode(root.left, r);
        encode(root.right, r);
    }

    public TreeNode decode(Result r) {
        AtomicInteger x = new AtomicInteger(0);
        AtomicInteger y = new AtomicInteger(0);
        return decode(r, x, y);
    }

    private TreeNode decode(Result r, AtomicInteger x, AtomicInteger y) {
        if(r.binaryRep.get(x.get()) == 0) {
            x.getAndIncrement();
            return null;
        }

        TreeNode root = new TreeNode();
        root.val = r.actualval.get(y.getAndIncrement());
        x.getAndIncrement();
        root.left = decode(r, x, y);
        root.right = decode(r, x, y);
        return root;
    }

    public static void main(String args[]) {
        TreeNode root = null;
        BinaryTree bt = new BinaryTree();
        root = bt.addTreeNode(10, root);
        root = bt.addTreeNode(-10, root);
        root = bt.addTreeNode(20, root);
        root = bt.addTreeNode(15, root);
        root = bt.addTreeNode(-7, root);
        root = bt.addTreeNode(22, root);
        root = bt.addTreeNode(-4, root);
        root = bt.addTreeNode(12, root);
        System.out.println("Before decoding");
        TreeTraversals tt = new TreeTraversals();
        tt.inOrder(root);
        System.out.println();
        tt.preOrder(root);
        System.out.println();
        SuccinctTree st = new SuccinctTree();
        Result r = st.encode(root);
        root = st.decode(r);
        System.out.println("After decoding");
        tt.inOrder(root);
        System.out.println();
        tt.preOrder(root);
    }

}
