package com.interview.tree.PartJAdvancedTreeAlgorithms;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Date 10/29/2017
 * @author Mukesh Kumar Gupta
 *
 * Red Black Tree
 *
 * Time complexity
 * Insert - O(logn)
 * Delete - O(logn)
 * Search - O(logn)
 *
 * Does not work for duplicates.
 *
 * References
 * http://pages.cs.wisc.edu/~skrentny/cs367-common/readings/Red-Black-Trees/
 * https://en.wikipedia.org/wiki/Red%E2%80%93black_tree
 */
public class RedBlackTree {

    public enum Color {
        RED,
        BLACK
    }

    public static class TreeNode {
        int val;
        Color color;
        TreeNode left;
        TreeNode right;
        TreeNode parent;
        boolean isNullLeaf;
    }

    private static TreeNode createBlackTreeNode(int val) {
        TreeNode TreeNode = new TreeNode();
        TreeNode.val = val;
        TreeNode.color = Color.BLACK;
        TreeNode.left = createNullLeafTreeNode(TreeNode);
        TreeNode.right = createNullLeafTreeNode(TreeNode);
        return TreeNode;
    }

    private static TreeNode createNullLeafTreeNode(TreeNode parent) {
        TreeNode leaf = new TreeNode();
        leaf.color = Color.BLACK;
        leaf.isNullLeaf = true;
        leaf.parent = parent;
        return leaf;
    }

    private static TreeNode createRedTreeNode(TreeNode parent, int val) {
        TreeNode TreeNode = new TreeNode();
        TreeNode.val = val;
        TreeNode.color = Color.RED;
        TreeNode.parent = parent;
        TreeNode.left = createNullLeafTreeNode(TreeNode);
        TreeNode.right = createNullLeafTreeNode(TreeNode);
        return TreeNode;
    }

    /**
     * Main insert method of red black tree.
     */
    public TreeNode insert(TreeNode root, int val) {
        return insert(null, root, val);
    }

    /**
     * Main delete method of red black tree.
     */
    public TreeNode delete(TreeNode root, int val) {
        AtomicReference<TreeNode> rootReference = new AtomicReference<>();
        delete(root, val, rootReference);
        if(rootReference.get() == null) {
            return root;
        } else {
            return rootReference.get();
        }
    }

    /**
     * Main print method of red black tree.
     */
    public void printRedBlackTree(TreeNode root) {
        printRedBlackTree(root, 0);
    }

    /**
     * Main validate method of red black tree.
     */
    public boolean validateRedBlackTree(TreeNode root) {

        if(root == null) {
            return true;
        }
        //check if root is black
        if(root.color != Color.BLACK) {
            System.out.print("Root is not black");
            return false;
        }
        //Use of AtomicInteger solely because java does not provide any other mutable int wrapper.
        AtomicInteger blackCount = new AtomicInteger(0);
        //make sure black count is same on all path and there is no red red relationship
        return checkBlackTreeNodesCount(root, blackCount, 0) && noRedRedParentChild(root, Color.BLACK);
    }

    private void rightRotate(TreeNode root, boolean changeColor) {
        TreeNode parent = root.parent;
        root.parent = parent.parent;
        if(parent.parent != null) {
            if(parent.parent.right == parent) {
                parent.parent.right = root;
            } else {
                parent.parent.left = root;
            }
        }
        TreeNode right = root.right;
        root.right = parent;
        parent.parent = root;
        parent.left = right;
        if(right != null) {
            right.parent = parent;
        }
        if(changeColor) {
            root.color = Color.BLACK;
            parent.color = Color.RED;
        }
    }

    private void leftRotate(TreeNode root, boolean changeColor) {
        TreeNode parent = root.parent;
        root.parent = parent.parent;
        if(parent.parent != null) {
            if(parent.parent.right == parent) {
                parent.parent.right = root;
            } else {
                parent.parent.left = root;
            }
        }
        TreeNode left = root.left;
        root.left = parent;
        parent.parent = root;
        parent.right = left;
        if(left != null) {
            left.parent = parent;
        }
        if(changeColor) {
            root.color = Color.BLACK;
            parent.color = Color.RED;
        }
    }

    private Optional<TreeNode> findSiblingTreeNode(TreeNode root) {
        TreeNode parent = root.parent;
        if(isLeftChild(root)) {
            return Optional.ofNullable(parent.right.isNullLeaf ? null : parent.right);
        } else {
            return Optional.ofNullable(parent.left.isNullLeaf ? null : parent.left);
        }
    }

    private boolean isLeftChild(TreeNode root) {
        TreeNode parent = root.parent;
        if(parent.left == root) {
            return true;
        } else {
            return false;
        }
    }

    private TreeNode insert(TreeNode parent, TreeNode root, int val) {
        if(root  == null || root.isNullLeaf) {
            //if parent is not null means tree is not empty
            //so create a red leaf TreeNode
            if(parent != null) {
                return createRedTreeNode(parent, val);
            } else { //otherwise create a black root TreeNode if tree is empty
                return createBlackTreeNode(val);
            }
        }

        //duplicate insertion is not allowed for this tree.
        if(root.val == val) {
            throw new IllegalArgumentException("Duplicate date " + val);
        }
        //if we go on left side then isLeft will be true
        //if we go on right side then isLeft will be false.
        boolean isLeft;
        if(root.val > val) {
            TreeNode left = insert(root, root.left, val);
            //if left becomes root parent means rotation
            //happened at lower level. So just return left
            //so that TreeNodes at upper level can set their
            //child correctly
            if(left == root.parent) {
                return left;
            }
            //set the left child returned to be left of root TreeNode
            root.left = left;
            //set isLeft to be true
            isLeft = true;
        } else {
            TreeNode right = insert(root, root.right, val);
            //if right becomes root parent means rotation
            //happened at lower level. So just return right
            //so that TreeNodes at upper level can set their
            //child correctly
            if(right == root.parent) {
                return right;
            }
            //set the right child returned to be right of root TreeNode
            root.right = right;
            //set isRight to be true
            isLeft = false;
        }

        if(isLeft) {
            //if we went to left side check to see Red-Red conflict
            //between root and its left child
            if(root.color == Color.RED && root.left.color == Color.RED) {
                //get the sibling of root. It is returning optional means
                //sibling could be empty
                Optional<TreeNode> sibling = findSiblingTreeNode(root);
                //if sibling is empty or of BLACK color
                if(!sibling.isPresent() || sibling.get().color == Color.BLACK) {
                    //check if root is left child of its parent
                    if(isLeftChild(root)) {
                        //this creates left left situation. So do one right rotate
                        rightRotate(root, true);
                    } else {
                        //this creates left-right situation so do one right rotate followed
                        //by left rotate

                        //do right rotation without change in color. So sending false.
                        //when right rotation is done root becomes right child of its left
                        //child. So make root = root.parent because its left child before rotation
                        //is new root of this subtree.
                        rightRotate(root.left, false);
                        //after rotation root should be root's parent
                        root = root.parent;
                        //then do left rotate with change of color
                        leftRotate(root, true);
                    }

                } else {
                    //we have sibling color as RED. So change color of root
                    //and its sibling to Black. And then change color of their
                    //parent to red if their parent is not a root.
                    root.color = Color.BLACK;
                    sibling.get().color = Color.BLACK;
                    //if parent's parent is not null means parent is not root.
                    //so change its color to RED.
                    if(root.parent.parent != null) {
                        root.parent.color = Color.RED;
                    }
                }
            }
        } else {
            //this is mirror case of above. So same comments as above.
            if(root.color == Color.RED && root.right.color == Color.RED) {
                Optional<TreeNode> sibling = findSiblingTreeNode(root);
                if(!sibling.isPresent() || sibling.get().color == Color.BLACK) {
                    if(!isLeftChild(root)) {
                        leftRotate(root, true);
                    } else {
                        leftRotate(root.right, false);
                        root = root.parent;
                        rightRotate(root, true);
                    }
                } else {
                    root.color = Color.BLACK;
                    sibling.get().color = Color.BLACK;
                    if(root.parent.parent != null) {
                        root.parent.color = Color.RED;
                    }
                }
            }
        }
        return root;
    }

    /**
     * Using atomicreference because java does not provide mutable wrapper. Its like
     * double pointer in C.
     */
    private void delete(TreeNode root, int val, AtomicReference<TreeNode> rootReference) {
        if(root == null || root.isNullLeaf) {
            return;
        }
        if(root.val == val) {
            //if TreeNode to be deleted has 0 or 1 null children then we have
            //deleteOneChild use case as discussed in video.
            if(root.right.isNullLeaf || root.left.isNullLeaf) {
                deleteOneChild(root, rootReference);
            } else {
                //otherwise look for the inorder successor in right subtree.
                //replace inorder successor val at root val.
                //then delete inorder successor which should have 0 or 1 not null child.
                TreeNode inorderSuccessor = findSmallest(root.right);
                root.val = inorderSuccessor.val;
                delete(root.right, inorderSuccessor.val, rootReference);
            }
        }
        //search for the TreeNode to be deleted.
        if(root.val < val) {
            delete(root.right, val, rootReference);
        } else {
            delete(root.left, val, rootReference);
        }
    }

    private TreeNode findSmallest(TreeNode root) {
        TreeNode prev = null;
        while(root != null && !root.isNullLeaf) {
            prev = root;
            root = root.left;
        }
        return prev != null ? prev : root;
    }

    /**
     * Assumption that TreeNode to be deleted has either 0 or 1 non leaf child
     */
    private void deleteOneChild(TreeNode TreeNodeToBeDelete, AtomicReference<TreeNode> rootReference) {
        TreeNode child = TreeNodeToBeDelete.right.isNullLeaf ? TreeNodeToBeDelete.left : TreeNodeToBeDelete.right;
        //replace TreeNode with either one not null child if it exists or null child.
        replaceTreeNode(TreeNodeToBeDelete, child, rootReference);
        //if the TreeNode to be deleted is BLACK. See if it has one red child.
        if(TreeNodeToBeDelete.color == Color.BLACK) {
            //if it has one red child then change color of that child to be Black.
            if(child.color == Color.RED) {
                child.color = Color.BLACK;
            } else {
                //otherwise we have double black situation.
                deleteCase1(child, rootReference);
            }
        }
    }


    /**
     * If double black TreeNode becomes root then we are done. Turning it into
     * single black TreeNode just reduces one black in every path.
     */
    private void deleteCase1(TreeNode doubleBlackTreeNode, AtomicReference<TreeNode> rootReference) {
        if(doubleBlackTreeNode.parent == null) {
            rootReference.set(doubleBlackTreeNode);
            return;
        }
        deleteCase2(doubleBlackTreeNode, rootReference);
    }

    /**
     * If sibling is red and parent and sibling's children are black then rotate it
     * so that sibling becomes black. Double black TreeNode is still double black so we need
     * further processing.
     */
    private void deleteCase2(TreeNode doubleBlackTreeNode, AtomicReference<TreeNode> rootReference) {
        TreeNode siblingTreeNode = findSiblingTreeNode(doubleBlackTreeNode).get();
        if(siblingTreeNode.color == Color.RED) {
            if(isLeftChild(siblingTreeNode)) {
                rightRotate(siblingTreeNode, true);
            } else {
                leftRotate(siblingTreeNode, true);
            }
            if(siblingTreeNode.parent == null) {
                rootReference.set(siblingTreeNode);
            }
        }
        deleteCase3(doubleBlackTreeNode, rootReference);
    }

    /**
     * If sibling, sibling's children and parent are all black then turn sibling into red.
     * This reduces black TreeNode for both the paths from parent. Now parent is new double black
     * TreeNode which needs further processing by going back to case1.
     */
    private void deleteCase3(TreeNode doubleBlackTreeNode, AtomicReference<TreeNode> rootReference) {

        TreeNode siblingTreeNode = findSiblingTreeNode(doubleBlackTreeNode).get();

        if(doubleBlackTreeNode.parent.color == Color.BLACK && siblingTreeNode.color == Color.BLACK && siblingTreeNode.left.color == Color.BLACK
                && siblingTreeNode.right.color == Color.BLACK) {
            siblingTreeNode.color = Color.RED;
            deleteCase1(doubleBlackTreeNode.parent, rootReference);
        } else {
            deleteCase4(doubleBlackTreeNode, rootReference);
        }
    }

    /**
     * If sibling color is black, parent color is red and sibling's children color is black then swap color b/w sibling
     * and parent. This increases one black TreeNode on double black TreeNode path but does not affect black TreeNode count on
     * sibling path. We are done if we hit this situation.
     */
    private void deleteCase4(TreeNode doubleBlackTreeNode, AtomicReference<TreeNode> rootReference) {
        TreeNode siblingTreeNode = findSiblingTreeNode(doubleBlackTreeNode).get();
        if(doubleBlackTreeNode.parent.color == Color.RED && siblingTreeNode.color == Color.BLACK && siblingTreeNode.left.color == Color.BLACK
        && siblingTreeNode.right.color == Color.BLACK) {
            siblingTreeNode.color = Color.RED;
            doubleBlackTreeNode.parent.color = Color.BLACK;
            return;
        } else {
            deleteCase5(doubleBlackTreeNode, rootReference);
        }
    }

    /**
     * If sibling is black, double black TreeNode is left child of its parent, siblings right child is black
     * and sibling's left child is red then do a right rotation at siblings left child and swap colors.
     * This converts it to delete case6. It will also have a mirror case.
     */
    private void deleteCase5(TreeNode doubleBlackTreeNode, AtomicReference<TreeNode> rootReference) {
        TreeNode siblingTreeNode = findSiblingTreeNode(doubleBlackTreeNode).get();
        if(siblingTreeNode.color == Color.BLACK) {
            if (isLeftChild(doubleBlackTreeNode) && siblingTreeNode.right.color == Color.BLACK && siblingTreeNode.left.color == Color.RED) {
                rightRotate(siblingTreeNode.left, true);
            } else if (!isLeftChild(doubleBlackTreeNode) && siblingTreeNode.left.color == Color.BLACK && siblingTreeNode.right.color == Color.RED) {
                leftRotate(siblingTreeNode.right, true);
            }
        }
        deleteCase6(doubleBlackTreeNode, rootReference);
    }

    /**
     * If sibling is black, double black TreeNode is left child of its parent, sibling left child is black and sibling's right child is
     * red, sibling takes its parent color, parent color becomes black, sibling's right child becomes black and then do
     * left rotation at sibling without any further change in color. This removes double black and we are done. This
     * also has a mirror condition.
     */
    private void deleteCase6(TreeNode doubleBlackTreeNode, AtomicReference<TreeNode> rootReference) {
        TreeNode siblingTreeNode = findSiblingTreeNode(doubleBlackTreeNode).get();
        siblingTreeNode.color = siblingTreeNode.parent.color;
        siblingTreeNode.parent.color = Color.BLACK;
        if(isLeftChild(doubleBlackTreeNode)) {
            siblingTreeNode.right.color = Color.BLACK;
            leftRotate(siblingTreeNode, false);
        } else {
            siblingTreeNode.left.color = Color.BLACK;
            rightRotate(siblingTreeNode, false);
        }
        if(siblingTreeNode.parent == null) {
            rootReference.set(siblingTreeNode);
        }
    }

    private void replaceTreeNode(TreeNode root, TreeNode child, AtomicReference<TreeNode> rootReference) {
        child.parent = root.parent;
        if(root.parent == null) {
            rootReference.set(child);
        }
        else {
            if(isLeftChild(root)) {
                root.parent.left = child;
            } else {
                root.parent.right = child;
            }
        }
    }

    private void printRedBlackTree(TreeNode root, int space) {
        if(root == null || root.isNullLeaf) {
            return;
        }
        printRedBlackTree(root.right, space + 5);
        for(int i=0; i < space; i++) {
            System.out.print(" ");
        }
        System.out.println(root.val + " " + (root.color == Color.BLACK ? "B" : "R"));
        printRedBlackTree(root.left, space + 5);
    }

    private boolean noRedRedParentChild(TreeNode root, Color parentColor) {
        if(root == null) {
            return true;
        }
        if(root.color == Color.RED && parentColor == Color.RED) {
            return false;
        }

        return noRedRedParentChild(root.left, root.color) && noRedRedParentChild(root.right, root.color);
    }

    private boolean checkBlackTreeNodesCount(TreeNode root, AtomicInteger blackCount, int currentCount) {

        if(root.color == Color.BLACK) {
            currentCount++;
        }

        if(root.left == null && root.right == null) {
            if(blackCount.get() == 0) {
                blackCount.set(currentCount);
                return true;
            } else {
                return currentCount == blackCount.get();
            }
        }
        return checkBlackTreeNodesCount(root.left, blackCount, currentCount) && checkBlackTreeNodesCount(root.right, blackCount, currentCount);
    }

    public static void main(String args[]) {
        TreeNode root = null;
        RedBlackTree redBlackTree = new RedBlackTree();

        root = redBlackTree.insert(root, 10);
        root = redBlackTree.insert(root, 15);
        root = redBlackTree.insert(root, -10);
        root = redBlackTree.insert(root, 20);
        root = redBlackTree.insert(root, 30);
        root = redBlackTree.insert(root, 40);
        root = redBlackTree.insert(root, 50);
        root = redBlackTree.insert(root, -15);
        root = redBlackTree.insert(root, 25);
        root = redBlackTree.insert(root, 17);
        root = redBlackTree.insert(root, 21);
        root = redBlackTree.insert(root, 24);
        root = redBlackTree.insert(root, 28);
        root = redBlackTree.insert(root, 34);
        root = redBlackTree.insert(root, 32);
        root = redBlackTree.insert(root, 26);
        root = redBlackTree.insert(root, 35);
        root = redBlackTree.insert(root, 19);
        redBlackTree.printRedBlackTree(root);

        root = redBlackTree.delete(root, 50);
        System.out.println(redBlackTree.validateRedBlackTree(root));
        root = redBlackTree.delete(root, 40);
        System.out.println(redBlackTree.validateRedBlackTree(root));
        root = redBlackTree.delete(root, -10);
        System.out.println(redBlackTree.validateRedBlackTree(root));
        root = redBlackTree.delete(root, 15);
        System.out.println(redBlackTree.validateRedBlackTree(root));
        root = redBlackTree.delete(root, 17);
        System.out.println(redBlackTree.validateRedBlackTree(root));
        root = redBlackTree.delete(root, 24);
        System.out.println(redBlackTree.validateRedBlackTree(root));
        root = redBlackTree.delete(root, 21);
        System.out.println(redBlackTree.validateRedBlackTree(root));
        root = redBlackTree.delete(root, 32);
        System.out.println(redBlackTree.validateRedBlackTree(root));
        root = redBlackTree.delete(root, 26);
        System.out.println(redBlackTree.validateRedBlackTree(root));
        root = redBlackTree.delete(root, 19);
        System.out.println(redBlackTree.validateRedBlackTree(root));
        root = redBlackTree.delete(root, 25);
        System.out.println(redBlackTree.validateRedBlackTree(root));
        root = redBlackTree.delete(root, 17);
        System.out.println(redBlackTree.validateRedBlackTree(root));
        root = redBlackTree.delete(root, -15);
        System.out.println(redBlackTree.validateRedBlackTree(root));
        root = redBlackTree.delete(root, 20);
        System.out.println(redBlackTree.validateRedBlackTree(root));
        root = redBlackTree.delete(root, 35);
        System.out.println(redBlackTree.validateRedBlackTree(root));
        root = redBlackTree.delete(root, 34);
        System.out.println(redBlackTree.validateRedBlackTree(root));
        root = redBlackTree.delete(root, 30);
        System.out.println(redBlackTree.validateRedBlackTree(root));
        root = redBlackTree.delete(root, 28);
        System.out.println(redBlackTree.validateRedBlackTree(root));
        root = redBlackTree.delete(root, 10);
        System.out.println(redBlackTree.validateRedBlackTree(root));
    }
}
