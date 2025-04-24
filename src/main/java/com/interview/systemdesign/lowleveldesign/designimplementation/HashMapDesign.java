package com.interview.systemdesign.lowleveldesign.designimplementation;

/*
 * Reference: https://leetcode.com/problems/design-hashmap
 * Category: Must Do
 */
public class HashMapDesign {
    /*class TreeNode {
        int key;
        int value;
        TreeNode(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
    int MAX = 150;
    List<TreeNode>[] lookup;


    public DesignHashMap() {
        lookup = new LinkedList[MAX];
    }
    int getHash(int key) {
        return key % MAX;
    }
    
    public void put(int key, int value) {
        int index = getHash(key);
        TreeNode TreeNode = new TreeNode(key, value);
        if (lookup[index] == null) {
            List<TreeNode> list = new LinkedList<>();
            list.add(TreeNode);
            
        } else {
            if (lookup[index].indexOf(TreeNode) == -1) {
                lookup[index].add(TreeNode);
            }
        }
    }
    

    public int get(int key) {

    }
    
    //Removes the mapping of the specified value key if this map contains a mapping for the key 
    public void remove(int key) {
        lookup[key] = -1;
        
    }*/
    int MAX = 1000001;
    int[] lookup = new int[MAX];

    /** Initialize your val structure here. */
    public HashMapDesign() {
        for (int i = 0; i < MAX; i++) {
            lookup[i] = -1;
        }

        
    }
    
    /** value will always be non-negative. */
    public void put(int key, int value) {
        lookup[key] = value;
    }
    
    /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
    public int get(int key) {
        if (lookup[key] != -1) {
            return lookup[key];
        }
        return -1;
    }
    
    /** Removes the mapping of the specified value key if this map contains a mapping for the key */
    public void remove(int key) {
        lookup[key] = -1;
        
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
