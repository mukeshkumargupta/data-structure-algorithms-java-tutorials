package com.interview.tree;

import com.interview.TestUtil;
import com.interview.bst.PartDSuccessorPredecessor.C_KClosestValueInBinaryTree;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class KClosestValueInBinaryTreeTest {

    @Test
    public void testDifferentCases() {
        TreeNode root = null;
        BinaryTree bt = new BinaryTree();
        root = bt.addTreeNode(10, root);
        root = bt.addTreeNode(20, root);
        root = bt.addTreeNode(30, root);
        root = bt.addTreeNode(0, root);
        root = bt.addTreeNode(6, root);
        root = bt.addTreeNode(-6, root);
        root = bt.addTreeNode(16, root);
        root = bt.addTreeNode(19, root);

        C_KClosestValueInBinaryTree kClosestValueInBinaryTree = new C_KClosestValueInBinaryTree();
        List<Integer> result = kClosestValueInBinaryTree.closestKValues(root, 18, 2);
        TestUtil testUtil = new TestUtil();
        testUtil.compareList(Arrays.asList(19, 20), result);

        result = kClosestValueInBinaryTree.closestKValues(root, 18, 4);
        testUtil.compareList(Arrays.asList(19, 20, 16, 10), result);
    }
}
