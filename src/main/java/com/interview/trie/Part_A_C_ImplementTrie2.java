package com.interview.trie;

/*
https://www.youtube.com/watch?v=K5pcpkEMCN0&list=PLgUwDviBIf0pcIDCZnxhv0LkHf5KzG9zp&index=2
Category: Medium, Must Do
Complete all playlist of trie from here
https://www.youtube.com/watch?v=AWnBa91lThI&list=PLgUwDviBIf0pcIDCZnxhv0LkHf5KzG9zp&index=3
https://www.naukri.com/code360/problems/implement-trie_1387095
 */
public class Part_A_C_ImplementTrie2 {
    // Define a struct for
    // each node in the trie
    private static class TrieNode {
        // Array to store
        // links to child nodes
        TrieNode[] childrens;
        // Counter for number of
        // words that end at this node
        int cntEndWith;
        // Counter for number of words
        // that have this node as a prefix
        int cntPrefix;

        // Constructor for Node
        TrieNode() {
            childrens = new TrieNode[26];
            cntEndWith = 0;
            cntPrefix = 0;
        }
    }

    // Pointer to the
    // root node of the trie
    private final TrieNode root = new TrieNode();


    // Function to insert
    // a word into the trie
    void insert(String word) {
        // Start from the root node
        TrieNode current = root;
        // Iterate over each
        // character in the word
        for (int i = 0; i < word.length(); i++) {
            // If the character is
            // not already in the trie
            char ch = word.charAt(i);
            TrieNode node = current.childrens[ch - 'a'];
            if (node == null) {
                node = new TrieNode();
                current.childrens[ch - 'a'] = node;
            }
            // Move to the child node
            // corresponding to the character
            current = node;
            // Increment the prefix
            // count for the node
            current.cntPrefix++;
        }
        // Increment the end count
        // for the last node of the word
        current.cntEndWith++;
    }

    // Function to count the number
    // of words equal to a given word
    int countWordsEqualTo(String word) {
        // Start from the root node
        TrieNode current = root;
        // Iterate over each character in the word
        for (int i = 0; i < word.length(); i++) {
            // If the character is found in the trie
            char ch = word.charAt(i);
            TrieNode node = current.childrens[ch - 'a'];
            if (node == null) {
                return 0;
            }
            current = node;
        }
        // Return the count of
        // words ending at the node
        return current.cntEndWith;
    }

    // Function to count the number of
    // words starting with a given prefix
    int countWordsStartingWith(String word) {
        // Start from the root node
        TrieNode current = root;
        // Iterate over each character in the prefix
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            TrieNode node = current.childrens[ch - 'a'];
            if (node == null) {
                return 0;
            }
            current = node;
        }
        // Return the count of
        // words with the prefix
        return current.cntPrefix;
    }

    // Function to erase a
    // word from the trie
    void erase(String word) {
        // Start from the root node
        TrieNode current = root;
        // Iterate over each
        // character in the word
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            TrieNode node = current.childrens[ch - 'a'];
            if (node != null) {
                current.cntPrefix--;
            } else {
                return;
            }
            current = node;
        }
        // Decrement the end count
        // for the last node of the word
        current.cntEndWith--;
    }

    public static void main(String[] args) {
        Part_A_C_ImplementTrie2 trie = new Part_A_C_ImplementTrie2();
        trie.insert("apple");
        trie.insert("app");
        System.out.println("Inserting strings 'apple', 'app' into Trie");
        System.out.print("Count Words Equal to 'apple': ");
        System.out.println(trie.countWordsEqualTo("apple"));
        System.out.print("Count Words Starting With 'app': ");
        System.out.println(trie.countWordsStartingWith("app"));
        System.out.println("Erasing word 'app' from trie");
        trie.erase("app");
        System.out.print("Count Words Equal to 'apple': ");
        System.out.println(trie.countWordsEqualTo("apple"));
        System.out.print("Count Words Starting With 'apple': ");
        System.out.println(trie.countWordsStartingWith("app"));
    }
}


