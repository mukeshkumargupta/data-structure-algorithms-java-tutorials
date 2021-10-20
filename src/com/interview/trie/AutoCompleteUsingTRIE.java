package com.interview.trie;

public class AutoCompleteUsingTRIE {


 // Alphabet size (# of symbols) 
    final int ALPHABET_SIZE  = 26;

 // trie TreeNode 
 class TrieTreeNode 
 { 
     TrieTreeNode[] children; 
     // isWordEnd is true if the TreeNode represents 
     // end of a word 
     boolean isWordEnd; 
     TrieTreeNode() {
         children = new TrieTreeNode[ALPHABET_SIZE];
         isWordEnd= false;
     }
 }; 


 // If not present, inserts key into trie. If the 
 // key is prefix of trie TreeNode, just marks leaf TreeNode 
void insert(TrieTreeNode root, String key) 
 { 
     TrieTreeNode pCrawl = root; 

     for (int level = 0; level < key.length(); level++) 
     { 
         int index = key.charAt(level) - 'a'; 
         if (pCrawl.children[index] != null) 
             pCrawl.children[index] = new TrieTreeNode(); 

         pCrawl = pCrawl.children[index]; 
     } 

     // mark last TreeNode as leaf 
     pCrawl.isWordEnd = true; 
 } 

 // Returns true if key presents in trie, else false 
 boolean search(TrieTreeNode root, String key) 
 { 
     int length = key.length(); 
     TrieTreeNode pCrawl = root; 
     for (int level = 0; level < length; level++) 
     { 
         int index = key.charAt(level) - 'a'; ; 

         if (pCrawl.children[index] == null) 
             return false; 

         pCrawl = pCrawl.children[index]; 
     } 

     return (pCrawl != null && pCrawl.isWordEnd); 
 } 

 // Returns 0 if current TreeNode has a child 
 // If all children are NULL, return 1. 
 boolean isLastTreeNode(TrieTreeNode root) 
 { 
     for (int i = 0; i < ALPHABET_SIZE; i++) 
         if (root.children[i] != null) 
             return false; 
     return  true; 
 } 

 // Recursive function to print auto-suggestions for given 
 // TreeNode. 
 void suggestionsRec(TrieTreeNode root, String currPrefix) 
 { 
     // found a string in Trie with the given prefix 
     if (root.isWordEnd) 
     { 
         System.out.println(currPrefix); 
     } 

     // All children struct TreeNode pointers are NULL 
     if (isLastTreeNode(root)) 
         return; 

     for (int i = 0; i < ALPHABET_SIZE; i++) 
     { 
         if (root.children[i] != null) 
         { 
             // append current character to currPrefix string 
             currPrefix.push_back(97 + i); 

             // recur over the rest 
             suggestionsRec(root.children[i], currPrefix); 
         } 
     } 
 } 
// print suggestions for given query prefix. 
 int printAutoSuggestions(TrieTreeNode root,  String query) 
 { 
     TrieTreeNode pCrawl = root; 

     // Check if prefix is present and find the 
     // the TreeNode (of last level) with last character 
     // of given string. 
     int level; 
     int n = query.length(); 
     for (level = 0; level < n; level++) 
     { 
         int index = query.charAt(level) -'a'; 

         // no string in the Trie has this prefix 
         if (pCrawl.children[index] == null) 
             return 0; 

         pCrawl = pCrawl.children[index]; 
     } 

     // If prefix is present as a word. 
     boolean isWord = (pCrawl.isWordEnd == true); 

     // If prefix is last TreeNode of tree (has no 
     // children) 
     boolean isLast = isLastTreeNode(pCrawl); 

     // If prefix is present as a word, but 
     // there is no subtree below the last 
     // matching TreeNode. 
     if (isWord && isLast) 
     { 
         System.out.println(query);
         return -1; 
     } 

     // If there are are TreeNodes below last 
     // matching character. 
     if (!isLast) 
     { 
         String prefix = query; 
         suggestionsRec(pCrawl, prefix); 
         return 1; 
     }
    return n; 
 } 
 void run() {
     TrieTreeNode root = new TrieTreeNode();
     insert(root, "hello"); 
     insert(root, "dog"); 
     insert(root, "hell"); 
     insert(root, "cat"); 
     insert(root, "a"); 
     insert(root, "hel"); 
     insert(root, "help"); 
     insert(root, "helps"); 
     insert(root, "helping"); 
     int comp = printAutoSuggestions(root, "hel"); 

     if (comp == -1) 
         System.out.println( "No other strings found with this prefix\n"); 

     else if (comp == 0) 
         System.out.println( "No string found with this prefix\n"); 

     
 }
 
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        AutoCompleteUsingTRIE instance = new AutoCompleteUsingTRIE();
        instance.run();
    }
}
