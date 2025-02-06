package com.interview.linklist;

import org.junit.Test;

public class DeleteDuplicateNodesTest {

    @Test
    public void testDifferentCases() {
        DeleteDuplicateTreeNodes deleteDuplicateTreeNodes = new DeleteDuplicateTreeNodes();
        LinkList linkList = new LinkList();
        TreeNode TreeNode = null;
        TreeNode = linkList.addTreeNode(1, TreeNode);
        TreeNode = linkList.addTreeNode(2, TreeNode);
        TreeNode = linkList.addTreeNode(2, TreeNode);
        deleteDuplicateTreeNodes.deleteDuplicates(TreeNode);
    }
}
