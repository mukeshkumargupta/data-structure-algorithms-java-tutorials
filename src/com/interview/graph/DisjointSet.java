package com.interview.graph;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Mukesh Kumar Gupta
 * Date 06/20/2017
 *  
 * Video link - https://youtu.be/ID00PMy0-vE
 *  
 * Disjoint sets using path compression and union by rank
 * Supports 3 operations
 * 1) makeSet
 * 2) union
 * 3) findSet
 * 
 * For m operations and total n elements time complexity is O(m*f(n)) where f(n) is 
 * very slowly growing function. For most cases f(n) <= 4 so effectively
 * total time will be O(m). Proof in Coreman book.
 * Tag: https://leetcode.com/tag/union-find/
 * Done
 */
public class DisjointSet {

    private Map<Long, TreeNode> map = new HashMap<>();

    class TreeNode {
        long val;
        TreeNode parent;
        int rank;
    }

    /**
     * Create a set with only one element.
     */
    public void makeSet(long val) {
        TreeNode TreeNode = new TreeNode();
        TreeNode.val = val;
        TreeNode.parent = TreeNode;
        TreeNode.rank = 0;
        map.put(val, TreeNode);
    }

    /**
     * Combines two sets together to one.
     * Does union by rank
     *
     * @return true if val1 and val2 are in different set before union else false.
     */
    public boolean union(long val1, long val2) {
        TreeNode TreeNode1 = map.get(val1);
        TreeNode TreeNode2 = map.get(val2);

        TreeNode parent1 = findSet(TreeNode1);
        TreeNode parent2 = findSet(TreeNode2);

        //if they are part of same set do nothing
        if (parent1.val == parent2.val) {
            return false;
        }

        //else whoever's rank is higher becomes parent of other
        if (parent1.rank >= parent2.rank) {
            //increment rank only if both sets have same rank
            parent1.rank = (parent1.rank == parent2.rank) ? parent1.rank + 1 : parent1.rank;
            parent2.parent = parent1;
        } else {
            parent1.parent = parent2;
        }
        return true;
    }

    /**
     * Finds the representative of this set
     */
    public long findSet(long val) {
        return findSet(map.get(val)).val;
    }

    /**
     * Find the representative recursively and does path
     * compression as well.
     */
    private TreeNode findSet(TreeNode TreeNode) {
        TreeNode parent = TreeNode.parent;
        if (parent == TreeNode) {
            return parent;
        }
        TreeNode.parent = findSet(TreeNode.parent);
        return TreeNode.parent;
    }

    public static void main(String args[]) {
        DisjointSet ds = new DisjointSet();
        ds.makeSet(1);
        ds.makeSet(2);
        ds.makeSet(3);
        ds.makeSet(4);
        ds.makeSet(5);
        ds.makeSet(6);
        ds.makeSet(7);

        ds.union(1, 2);
        ds.union(2, 3);
        ds.union(4, 5);
        ds.union(6, 7);
        ds.union(5, 6);
        ds.union(3, 7);

        System.out.println(ds.findSet(1));
        System.out.println(ds.findSet(2));
        System.out.println(ds.findSet(3));
        System.out.println(ds.findSet(4));
        System.out.println(ds.findSet(5));
        System.out.println(ds.findSet(6));
        System.out.println(ds.findSet(7));
    }
}