package com.interview.tree;

/**
 * http://www.geeksforgeeks.org/construct-tree-from-given-inorder-and-preorder-traversal/
 * Test cases:
 * Empty tree
 * One node tree
 * All left side tree
 * All right side tree
 * Mixed tree
 * Full tree
 * complete tree
 * Must Know
 */
public class ConstructTreeFromInOrderPreOrder {
    
    private int index = 0;
    public Node createTree(int inorder[],int preorder[]){
        Node result =  createTree(inorder,preorder,0,inorder.length-1);
        index = 0;
        return result;
    }
    
    private Node createTree(int inorder[],int preorder[], int start, int end){
        if(start > end){
            return null;
        }
        int i;
        //Search element in inorder
        for(i=start; i <= end; i++){
            if(preorder[index] == inorder[i]){
                break;
            }
        }
        Node node = Node.newNode(preorder[index]);
        index++;
        node.left = createTree(inorder,preorder,start,i-1);
        node.right = createTree(inorder,preorder,i+1,end);
        return node;
    }
    
    public void postOrderTraversal(Node rootNode) {
    	if (rootNode == null) {
    		return;
    	}
    	postOrderTraversal(rootNode.left);
    	postOrderTraversal(rootNode.right);
    	System.out.println(rootNode.data);
    	
    }
    
    public static void main(String []arg) {
    	int[] inorder = {8,3,5,2,2,3,11,12,9,4,6,3,8};
    	int[] preorder = {2, 3, 8, 5, 4,9,2,3,11,12,3,6,8};
    	ConstructTreeFromInOrderPreOrder oConstructTreeFromInOrderPreOrder = new ConstructTreeFromInOrderPreOrder();
    	Node rootNode = oConstructTreeFromInOrderPreOrder.createTree(inorder, preorder);
    	oConstructTreeFromInOrderPreOrder.postOrderTraversal(rootNode);
    	//Output: 8,5,3, 12, 11, 3, 2, 9, 6, 8, 3, 4, 2

    }
}
