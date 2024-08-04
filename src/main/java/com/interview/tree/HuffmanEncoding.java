package com.interview.tree;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Comparator;

/**
 * http://www.geeksforgeeks.org/greedy-algorithms-set-3-huffman-coding/
 * http://www.geeksforgeeks.org/greedy-algorithms-set-3-huffman-coding-set-2/
 */
class HuffmanTreeNode{
    int freq;
    char input;
    HuffmanTreeNode left;
    HuffmanTreeNode right;
}

class HuffmanTreeNodeComparator implements Comparator<HuffmanTreeNode>{

    @Override
    public int compare(HuffmanTreeNode o1, HuffmanTreeNode o2) {
        if(o1.freq < o2.freq){
            return -1;
        }else{
            return 1;
        }
    }
    
}

public class HuffmanEncoding {

    public Map<Character,String> huffman(char[] input, int freq[]){
        HuffmanTreeNodeComparator comparator = new HuffmanTreeNodeComparator();
        PriorityQueue<HuffmanTreeNode> heap = new PriorityQueue<HuffmanTreeNode>(input.length,comparator);
        for(int i=0; i < input.length; i++){
            HuffmanTreeNode TreeNode = new HuffmanTreeNode();
            TreeNode.freq = freq[i];
            TreeNode.input = input[i];
            heap.offer(TreeNode);
        }
    
        while(heap.size() > 1){
            HuffmanTreeNode TreeNode1 = heap.poll();
            HuffmanTreeNode TreeNode2 = heap.poll();
            HuffmanTreeNode TreeNode = new HuffmanTreeNode();
            TreeNode.left = TreeNode1;
            TreeNode.right = TreeNode2;
            TreeNode.freq = TreeNode1.freq + TreeNode2.freq;
            heap.offer(TreeNode);
        }
        
        Map<Character,String> map = new HashMap<Character,String>();
        StringBuffer buff = new StringBuffer();
        createCode(heap.poll(),map,buff);
        return map;
        
    }
    
    public void createCode(HuffmanTreeNode TreeNode,Map<Character,String> map,StringBuffer buff){
        if(TreeNode == null){
            return;
        }
        
        if(TreeNode.left == null && TreeNode.right == null){
            map.put(TreeNode.input,buff.toString());
            return;
        }
        buff.append("0");
        createCode(TreeNode.left,map,buff);
        buff.deleteCharAt(buff.length()-1);
        buff.append("1");
        createCode(TreeNode.right,map,buff);
        buff.deleteCharAt(buff.length()-1);
    }
    
    
    public static void main(String args[]){
        HuffmanEncoding he = new HuffmanEncoding();
        char input[] = {'a','b','c','d','e','f'};
        int freq[] = {5,9,12,13,16,45};
        Map<Character,String> code = he.huffman(input, freq);
        System.out.println(code);
    }
    
}
