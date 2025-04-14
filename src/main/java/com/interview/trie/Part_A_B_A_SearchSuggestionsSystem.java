package com.interview.trie;
import java.util.*;
/*
https://leetcode.com/problems/search-suggestions-system/description/?envType=study-plan-v2&envId=leetcode-75
Category: Medium, top75, Facebook, Google
You are given an array of strings products and a string searchWord.

Design a system that suggests at most three product names from products after each character of searchWord is typed. Suggested products should have common prefix with searchWord. If there are more than three products with a common prefix return the three lexicographically minimums products.

Return a list of lists of the suggested products after each character of searchWord is typed.



Example 1:

Input: products = ["mobile","mouse","moneypot","monitor","mousepad"], searchWord = "mouse"
Output: [["mobile","moneypot","monitor"],["mobile","moneypot","monitor"],["mouse","mousepad"],["mouse","mousepad"],["mouse","mousepad"]]
Explanation: products sorted lexicographically = ["mobile","moneypot","monitor","mouse","mousepad"].
After typing m and mo all products match and we show user ["mobile","moneypot","monitor"].
After typing mou, mous and mouse the system suggests ["mouse","mousepad"].
Example 2:

Input: products = ["havana"], searchWord = "havana"
Output: [["havana"],["havana"],["havana"],["havana"],["havana"],["havana"]]
Explanation: The only word "havana" will be always suggested while typing the search word.


Constraints:

1 <= products.length <= 1000
1 <= products[i].length <= 3000
1 <= sum(products[i].length) <= 2 * 104
All the strings of products are unique.
products[i] consists of lowercase English letters.
1 <= searchWord.length <= 1000
searchWord consists of lowercase English letters.
 */
public class Part_A_B_A_SearchSuggestionsSystem {
    /*
        💡 Why Use Trie?
        Using a Trie helps:

        - Efficiently store and search for prefixes
        - Quickly retrieve suggestions for each prefix
        - Automatically sort lexicographically (with TreeMap or sorted list of children)

        ✅ Approach: Trie with DFS to Collect Suggestions
        - Insert all products into a Trie. While inserting each product, maintain a list of suggestions (max 3) at every Trie node.
        - Search the Trie prefix by prefix for each character of the search word.
        - At each node, retrieve the suggestions stored during the insert.

        ⏱ Time Complexity
        Operation            Time
        -------------------- -------------------------
        Sorting products     O(n log n)
        Trie insert          O(n * L)
        Searching prefixes   O(m)

        Where:
        - n = number of products
        - L = average length of product
        - m = length of search word

        ✅ Total: O(n log n + n * L + m)

        📦 Space Complexity
        Resource      Space
        ------------- -------------------------
        Trie nodes    O(n * L)
        Suggestions   O(n) for storing top 3 only
        Result        O(m * 3)
     */
    private static class TrieNode {
        TrieNode[] children;
        List<String> suggestions;

        TrieNode() {
            children = new TrieNode[26];
            suggestions = new ArrayList<>();
        }

    }
    final TrieNode root = new TrieNode();
    void insert(String product) {
        TrieNode current = root;
        for (char ch: product.toCharArray()) {
            TrieNode node = current.children[ch - 'a'];
            if (node == null) {
                node = new TrieNode();
            }
            current.children[ch - 'a'] = node;
            current = node;
            if (node.suggestions.size() < 3) {
                node.suggestions.add(product);
            }
        }
    }

    List<List<String>> search(String searchWord) {
        TrieNode current = root;
        List<List<String>> suggestions = new ArrayList<>();
        for (char ch: searchWord.toCharArray()) {
            TrieNode node = null;
            if (current != null) {
                node = current.children[ch - 'a'];
            }
            if (node == null) {
                suggestions.add(new ArrayList<>());
            } else {
                suggestions.add(new ArrayList<>(node.suggestions));
            }
            current = node;


        }
        return suggestions;
    }

    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        Arrays.sort(products);
        for (String product: products) {
            insert(product);
        }
        return search(searchWord);


    }
}
