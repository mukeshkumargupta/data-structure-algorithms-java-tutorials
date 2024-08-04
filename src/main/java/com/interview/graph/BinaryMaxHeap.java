package com.interview.graph;

import java.util.ArrayList;
import java.util.List;

public class BinaryMaxHeap<T> {

    private List<TreeNode> allTreeNodes = new ArrayList<TreeNode>();

    class TreeNode {
        int weight;
        T val;
    }

    public void add(int weight,T val) {

        TreeNode TreeNode = new TreeNode();
        TreeNode.weight = weight;
        TreeNode.val = val;
        allTreeNodes.add(TreeNode);
        int size = allTreeNodes.size();
        int current = size - 1;
        int parentIndex = (current - 1) / 2;

        while (parentIndex >= 0) {
            TreeNode parentTreeNode = allTreeNodes.get(parentIndex);
            TreeNode currentTreeNode = allTreeNodes.get(current);
            if (parentTreeNode.weight < currentTreeNode.weight) {
                swap(parentTreeNode,currentTreeNode);
                current = parentIndex;
                parentIndex = (parentIndex - 1) / 2;
            } else {
                break;
            }
        }

    }

    private void swap(TreeNode TreeNode1,TreeNode TreeNode2){
        int weight = TreeNode1.weight;
        T val = TreeNode1.val;
        
        TreeNode1.val = TreeNode2.val;
        TreeNode1.weight = TreeNode2.weight;
        
        TreeNode2.val = val;
        TreeNode2.weight = weight;
    }
    
    public T max(){
        return allTreeNodes.get(0).val;
    }
    
    public boolean empty(){
        return allTreeNodes.size() == 0;
    }
    
    
    public T extractMap(){
        int size = allTreeNodes.size() -1;
        T max = allTreeNodes.get(0).val;
        int lastTreeNodeWeight = allTreeNodes.get(size).weight;
        allTreeNodes.get(0).weight = lastTreeNodeWeight;
        allTreeNodes.get(0).val = allTreeNodes.get(size).val;
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
            int largerIndex = allTreeNodes.get(left).weight >= allTreeNodes.get(right).weight ? left : right;
            if(allTreeNodes.get(currentIndex).weight < allTreeNodes.get(largerIndex).weight){
                swap(allTreeNodes.get(currentIndex),allTreeNodes.get(largerIndex));
                currentIndex = largerIndex;
            }else{
                break;
            }
        }
        return max;
    }
    
    public void printHeap(){
        for(TreeNode n : allTreeNodes){
            System.out.println(n.weight + " " + n.val);
        }
    }
    
    public static void main(String args[]){
        BinaryMaxHeap<String> heap = new BinaryMaxHeap<String>();
        heap.add(3, "Tushar");
        heap.add(4, "Ani");
        heap.add(8, "Vijay");
        heap.add(10, "Pramila");
        heap.add(5, "Roy");
        heap.add(6, "NTF");
        heap.printHeap();
    }
}
