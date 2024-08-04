package com.interview.tree;

import java.util.*;
/*
 * https://www.youtube.com/watch?v=2r5wLmQfD6g&t=38s
 * Category: Medium, Must Do, bfs, similar to find all node at k distance
 * Practice link: https://www.youtube.com/redirect?event=video_description&redir_token=QUFFLUhqbTVSVE1uOGZUaUdFbW90N2plY2oxY1BqOThoZ3xBQ3Jtc0tuYmE3SHI5YlJ2V0RhSG1KNWhLck9lSU9LQ2pNTXJtYlhMajd3TlhzTG90VXdmbW82cHFWaGtNTHRISUVhRXZCdUluRFE3ZW9fd3NUNkVBZFFwVk42TmZyR3BnREZraUtQSDRIR1B1b1NXSTFCbWdyRQ&q=https%3A%2F%2Fbit.ly%2F3z3YEJY
 */

class BinaryTreeNode<T> {
    T data;
    BinaryTreeNode<T> left;
    BinaryTreeNode<T> right;
    
    public BinaryTreeNode(T data) {
        this.data = data;
    }
}
public class TimeToBurnATreeJava {
    private static BinaryTreeNode<Integer> bfsToMapParents(BinaryTreeNode<Integer> root,
            HashMap<BinaryTreeNode<Integer>, BinaryTreeNode<Integer>> mpp, int start) {
        Queue<BinaryTreeNode<Integer>> q = new LinkedList<>();
        q.add(root);
        BinaryTreeNode<Integer> res = new BinaryTreeNode<>(-1);
        while (!q.isEmpty()) {
            BinaryTreeNode<Integer> node = q.remove();
            if (node.data == start)
                res = node;
            if (node.left != null) {
                mpp.put(node.left, node);
                q.add(node.left);
            }
            if (node.right != null) {
                mpp.put(node.right, node);
                q.add(node.right);
            }
        }
        return res;
    }
    
    private static int findMaxDistance(HashMap<BinaryTreeNode<Integer>, BinaryTreeNode<Integer>> mpp,
            BinaryTreeNode<Integer> target) {
        Queue<BinaryTreeNode<Integer>> q = new LinkedList<>();
        q.add(target);
        Set<BinaryTreeNode<Integer>> vis = new HashSet<>();
        vis.add(target);
        int timeToburn = 0;
        
        while (!q.isEmpty()) {
            int sz = q.size();
            //boolean isBurnItsNeighbour = false;
            
            for (int i = 0; i < sz; i++) {
                BinaryTreeNode<Integer> node = q.remove();
                if (node.left != null && !vis.contains(node.left)) {
                    //isBurnItsNeighbour = true;
                    vis.add(node.left);
                    q.add(node.left);
                }
                if (node.right != null && !vis.contains(node.right)) {
                    //isBurnItsNeighbour = true;
                    vis.add(node.right);
                    q.add(node.right);
                }
                
                if (mpp.get(node) != null && !vis.contains(mpp.get(node))) {
                    //isBurnItsNeighbour = true;
                    vis.add(mpp.get(node));
                    q.add(mpp.get(node));
                }
            }
            //if (isBurnItsNeighbour)
                //timeToburn++;
            //Online optimization
            if (!q.isEmpty()) {//This check is inspired by rotten orange
                timeToburn++;
            }
        }
        return timeToburn;
    }
    
    public static int timeToBurnTree(BinaryTreeNode<Integer> root, int start) {
        HashMap<BinaryTreeNode<Integer>, BinaryTreeNode<Integer>> mpp = new HashMap<>();
        mpp.put(root , null);
        BinaryTreeNode<Integer> target = bfsToMapParents(root, mpp, start);
        int timeToburn = findMaxDistance(mpp, target);
        return timeToburn;
    }
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
