package com.interview.tree;

/*
 * https://leetcode.com/problems/step-by-step-directions-from-a-binary-tree-node-to-another/
 * Category: Medium, same as knight tour problem, bfs, after build parent lookup
 * This can be solved as dfs as well same apttern as all path see code of DFS + backtracking
 * 2096. Step-By-Step Directions From a Binary Tree Node to Another
Medium

790

58

Add to List

Share
You are given the root of a binary tree with n nodes. Each node is uniquely assigned a value from 1 to n. You are also given an integer startValue representing the value of the start node s, and a different integer destValue representing the value of the destination node t.

Find the shortest path starting from node s and ending at node t. Generate step-by-step directions of such path as a string consisting of only the uppercase letters 'L', 'R', and 'U'. Each letter indicates a specific direction:

'L' means to go from a node to its left child node.
'R' means to go from a node to its right child node.
'U' means to go from a node to its parent node.
Return the step-by-step directions of the shortest path from node s to node t.

 

Example 1:


Input: root = [5,1,2,3,null,6,4], startValue = 3, destValue = 6
Output: "UURL"
Explanation: The shortest path is: 3 → 1 → 5 → 2 → 6.
Example 2:


Input: root = [2,1], startValue = 2, destValue = 1
Output: "L"
Explanation: The shortest path is: 2 → 1.
 

Constraints:

The number of nodes in the tree is n.
2 <= n <= 105
1 <= Node.val <= n
All the values in the tree are unique.
1 <= startValue, destValue <= n
startValue != destValue
Accepted
29,413
Submissions
61,002
 */
public class StepByStepDirectionsFromaBinaryTreeNodetoAnother {
    class NodeDetails {
        TreeNode node;
        String path;
        NodeDetails(TreeNode node , String path ) {
            this.node = node;
            this.path = path;
        }
    }
    public String getDirections(TreeNode root, int start, int end) {
        //startVal = start;
        Map<TreeNode, TreeNode> parentsLookup = new HashMap<>();
        TreeNode[] startNode = new TreeNode[1];
        buildParentLookup(parentsLookup, root, null, startNode, start);
        Queue<NodeDetails> q = new LinkedList<>();
        q.add(new NodeDetails(startNode[0], ""));
        Set<Integer> visited = new HashSet<>();
        StringBuilder result = new StringBuilder();
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i=0;i<size;i++) {
                
                NodeDetails curNodeNetails = q.remove();
                TreeNode curNode = curNodeNetails.node;
                String curPath = curNodeNetails.path;

                visited.add(curNode.val);
                if (curNode.val == end) {
                    //return curStr;
                    return curPath;
                }
                if (parentsLookup.get(curNode) != null && !visited.contains(parentsLookup.get(curNode).val)) {
                    q.add(new NodeDetails(parentsLookup.get(curNode), curPath + "U"));
                }
                if (curNode.left != null && !visited.contains(curNode.left.val)) {
                    q.add(new NodeDetails(curNode.left, curPath + "L"));
                }
                if (curNode.right != null && !visited.contains(curNode.right.val)) {
                     q.add(new NodeDetails(curNode.right, curPath + "R"));
                }
            }
        }
        return "";
    }
    
    public void buildParentLookup(Map<TreeNode, TreeNode> parentsLookup, TreeNode root, TreeNode par, TreeNode[] startNode, int startVal) {
        if (root.val == startVal) {
            startNode[0] = root;
        }
        parentsLookup.put(root, par);
        //DFS
        if (root.left != null) {
            buildParentLookup(parentsLookup, root.left, root, startNode, startVal);
        }
        if (root.right != null) {
            buildParentLookup(parentsLookup, root.right, root, startNode, startVal);
        }        
    }       
}
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
