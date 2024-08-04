package com.interview.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Date 04/06/2017
 * @author Mukesh Kumar Gupta
 *
 * val structure to support following operations
 * extracMin - O(logn)
 * addToHeap - O(logn)
 * containsKey - O(1)
 * decreaseKey - O(logn)
 * getKeyWeight - O(1)
 *
 * It is a combination of binary heap and hash map
 *
 */
public class BinaryMinHeap<T> {

    private List<TreeNode> allTreeNodes = new ArrayList<>();
    private Map<T,Integer> TreeNodePosition = new HashMap<>();
        
    public class TreeNode {
        int weight;
        T key;
    }

    /**
     * Checks where the key exists in heap or not
     */
    public boolean containsval(T key){
        return TreeNodePosition.containsKey(key);
    }

    /**
     * Add key and its weight to they heap
     */
    public void add(int weight,T key) {
        TreeNode TreeNode = new TreeNode();
        TreeNode.weight = weight;
        TreeNode.key = key;
        allTreeNodes.add(TreeNode);
        int size = allTreeNodes.size();
        int current = size - 1;
        int parentIndex = (current - 1) / 2;
        TreeNodePosition.put(TreeNode.key, current);

        while (parentIndex >= 0) {
            TreeNode parentTreeNode = allTreeNodes.get(parentIndex);
            TreeNode currentTreeNode = allTreeNodes.get(current);
            if (parentTreeNode.weight > currentTreeNode.weight) {
                swap(parentTreeNode,currentTreeNode);
                updatePositionMap(parentTreeNode.key,currentTreeNode.key,parentIndex,current);
                current = parentIndex;
                parentIndex = (parentIndex - 1) / 2;
            } else {
                break;
            }
        }
    }

    /**
     * Get the heap min without extracting the key
     */
    public T min(){
        return allTreeNodes.get(0).key;
    }

    /**
     * Checks with heap is empty or not
     */
    public boolean empty(){
        return allTreeNodes.size() == 0;
    }

    /**
     * Decreases the weight of given key to newWeight
     */
    public void decrease(T val, int newWeight){
        Integer position = TreeNodePosition.get(val);
        allTreeNodes.get(position).weight = newWeight;
        int parent = (position -1 )/2;
        while(parent >= 0){
            if(allTreeNodes.get(parent).weight > allTreeNodes.get(position).weight){
                swap(allTreeNodes.get(parent), allTreeNodes.get(position));
                updatePositionMap(allTreeNodes.get(parent).key,allTreeNodes.get(position).key,parent,position);
                position = parent;
                parent = (parent-1)/2;
            }else{
                break;
            }
        }
    }

    /**
     * Get the weight of given key
     */
    public Integer getWeight(T key) {
        Integer position = TreeNodePosition.get(key);
        if( position == null ) {
            return null;
        } else {
            return allTreeNodes.get(position).weight;
        }
    }

    /**
     * Returns the min TreeNode of the heap
     */
    public TreeNode extractMinTreeNode() {
        int size = allTreeNodes.size() -1;
        TreeNode minTreeNode = new TreeNode();
        minTreeNode.key = allTreeNodes.get(0).key;
        minTreeNode.weight = allTreeNodes.get(0).weight;

        int lastTreeNodeWeight = allTreeNodes.get(size).weight;
        allTreeNodes.get(0).weight = lastTreeNodeWeight;
        allTreeNodes.get(0).key = allTreeNodes.get(size).key;
        TreeNodePosition.remove(minTreeNode.key);
        TreeNodePosition.remove(allTreeNodes.get(0));
        TreeNodePosition.put(allTreeNodes.get(0).key, 0);
        allTreeNodes.remove(size);

        int currentIndex = 0;
        size--;
        while(true){
            int left = 2*currentIndex + 1;
            int right = 2*currentIndex + 2;
            if(left > size){
                break;
            }
            if(right > size){
                right = left;
            }
            int smallerIndex = allTreeNodes.get(left).weight <= allTreeNodes.get(right).weight ? left : right;
            if(allTreeNodes.get(currentIndex).weight > allTreeNodes.get(smallerIndex).weight){
                swap(allTreeNodes.get(currentIndex), allTreeNodes.get(smallerIndex));
                updatePositionMap(allTreeNodes.get(currentIndex).key,allTreeNodes.get(smallerIndex).key,currentIndex,smallerIndex);
                currentIndex = smallerIndex;
            }else{
                break;
            }
        }
        return minTreeNode;
    }
    /**
     * Extract min value key from the heap
     */
    public T extractMin(){
        TreeNode TreeNode = extractMinTreeNode();
        return TreeNode.key;
    }

    private void printPositionMap(){
        System.out.println(TreeNodePosition);
    }

    private void swap(TreeNode TreeNode1,TreeNode TreeNode2){
        int weight = TreeNode1.weight;
        T val = TreeNode1.key;
        
        TreeNode1.key = TreeNode2.key;
        TreeNode1.weight = TreeNode2.weight;
        
        TreeNode2.key = val;
        TreeNode2.weight = weight;
    }

    private void updatePositionMap(T val1, T val2, int pos1, int pos2){
        TreeNodePosition.remove(val1);
        TreeNodePosition.remove(val2);
        TreeNodePosition.put(val1, pos1);
        TreeNodePosition.put(val2, pos2);
    }
    
    public void printHeap(){
        for(TreeNode n : allTreeNodes){
            System.out.println(n.weight + " " + n.key);
        }
    }
    
    public static void main(String args[]){
        BinaryMinHeap<String> heap = new BinaryMinHeap<String>();
        heap.add(3, "Tushar");
        heap.add(4, "Ani");
        heap.add(8, "Vijay");
        heap.add(10, "Pramila");
        heap.add(5, "Roy");
        heap.add(6, "NTF");
        heap.add(2,"AFR");
        heap.decrease("Pramila", 1);
        heap.printHeap();
        heap.printPositionMap();
    }
}
