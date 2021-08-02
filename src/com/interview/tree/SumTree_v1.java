package com.interview.tree;

public class SumTree_v1 {

	private int sumTree(TreeNode root) {
		if (root == null) {
			return 0;
		}
		
		return root.val + sumTree(root.left) + sumTree(root.right);
	}

	private boolean isSumTree(TreeNode root) {
		//Important to know whther it shoud return true or falue,
		//Scenareo 4 and having chaild as 4 so root null should return true.
		if (root == null) {
			return true;
		}


		//Base case if given root is leaf TreeNode.
		if (root.left == null && root.right == null) {
			return true;
		}
		
		int leftSum = sumTree(root.left);
		int rightSum = sumTree(root.right);
		if (((leftSum + rightSum) == root.val) && isSumTree(root.left) && isSumTree(root.right)) {
			return true;
		}
		return false;

	}

	public static void main(String args[]) {
		ConstructTreeFromInOrderPreOrder ctf = new ConstructTreeFromInOrderPreOrder();
		//int inorder[] = { 4, 10, 6, 46, 11, 13, 2 };
		//int preorder[] = { 46, 10, 4, 6, 13, 11, 2 };
		int inorder[] = { 8};
		int preorder[] = { 8};
		TreeNode root = ctf.createTree(inorder, preorder);
		SumTree_v1 st = new SumTree_v1();
		System.out.println(st.isSumTree(root));
	}

}
