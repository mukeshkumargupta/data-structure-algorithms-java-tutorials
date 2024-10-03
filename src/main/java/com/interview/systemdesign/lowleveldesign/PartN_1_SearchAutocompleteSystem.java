package com.interview.systemdesign.lowleveldesign;

import java.util.*;

/*
    This is a complete solution for a Search Autocomplete System using a Trie in Java.
    The system efficiently stores and retrieves query suggestions as users type, leveraging the Trie data structure for fast lookups.

    Key Components:
    - Trie: Represents the data structure used to store search terms.
    - TrieNode: Represents each character in the Trie, holding child nodes.
    - SearchQuery: Manages user input and provides autocomplete suggestions.

    Explanation of Components:

    1. TrieNode:
       - Each node contains a map of child nodes (`children`), a flag (`isEndOfWord`) that indicates the end of a word, and a frequency counter (`frequency`) to rank suggestions.

    2. Trie:
       - `insert`: Adds a query to the Trie and updates its frequency each time the query is inserted.
       - `getSuggestions`: Retrieves suggestions by finding the node corresponding to the input prefix and performing depth-first search (DFS) to find all valid words that start with that prefix.

    3. SearchQuery:
       - Provides a simple interface for adding queries to the Trie and retrieving autocomplete suggestions based on user input.

    4. SearchAutocompleteSystem (Main class):
       - Demonstrates the system by adding several queries and fetching top suggestions for a given prefix (e.g., "ap").

    Features:
    - **Prefix Search**: Efficiently searches for all words starting with a given prefix.
    - **Frequency-based Ranking**: Suggestions are ranked based on how often they were inserted into the Trie.
    - **Case Insensitivity**: Converts queries to lowercase for case-insensitive matching.

    Example Output:
    Autocomplete suggestions for 'ap': [apple, app, application]

    This implementation can be optimized further for larger datasets, and additional features such as caching popular queries or incorporating machine learning models for ranking suggestions can be added for advanced use cases.
*/
// Node class representing each character in the Trie
class TrieNode {
    Map<Character, TrieNode> children; // Child nodes
    boolean isEndOfWord;               // Indicates the end of a query
    int frequency;                     // Frequency or popularity of the word

    public TrieNode() {
        children = new HashMap<>();
        isEndOfWord = false;
        frequency = 0;
    }
}

// Trie class to store search queries and return suggestions
class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    // Inserts a query into the Trie and tracks its frequency
    public void insert(String query) {
        TrieNode currentNode = root;
        for (char ch : query.toCharArray()) {
            currentNode = currentNode.children.computeIfAbsent(ch, c -> new TrieNode());
        }
        currentNode.isEndOfWord = true;
        currentNode.frequency++; // Increment frequency each time the query is inserted
    }

    // Finds the node where the prefix ends
    private TrieNode searchPrefix(String prefix) {
        TrieNode currentNode = root;
        for (char ch : prefix.toCharArray()) {
            if (!currentNode.children.containsKey(ch)) {
                return null; // If prefix is not found
            }
            currentNode = currentNode.children.get(ch);
        }
        return currentNode;
    }

    // Returns the top N autocomplete suggestions for a given prefix
    public List<String> getSuggestions(String prefix, int topN) {
        TrieNode node = searchPrefix(prefix);
        if (node == null) {
            return new ArrayList<>(); // No suggestions if prefix not found
        }
        List<String> suggestions = new ArrayList<>();
        dfsWithPrefix(node, prefix, suggestions);
        // Sort the suggestions based on frequency and return the top N results
        suggestions.sort((a, b) -> {
            TrieNode nodeA = searchPrefix(a);
            TrieNode nodeB = searchPrefix(b);
            return nodeB.frequency - nodeA.frequency;
        });
        return suggestions.size() > topN ? suggestions.subList(0, topN) : suggestions;
    }

    // Depth-first search (DFS) to collect all suggestions with a given prefix
    private void dfsWithPrefix(TrieNode node, String prefix, List<String> result) {
        if (node.isEndOfWord) {
            result.add(prefix); // Add complete words to the result
        }
        for (Map.Entry<Character, TrieNode> entry : node.children.entrySet()) {
            dfsWithPrefix(entry.getValue(), prefix + entry.getKey(), result);
        }
    }
}

// SearchQuery class for managing user input and returning suggestions
class SearchQuery {
    private Trie trie;

    public SearchQuery() {
        trie = new Trie();
    }

    // Adds a search query to the Trie
    public void addQuery(String query) {
        trie.insert(query.toLowerCase()); // Lowercase to ensure case-insensitivity
    }

    // Retrieves the top N autocomplete suggestions based on the input prefix
    public List<String> getAutocompleteSuggestions(String prefix, int topN) {
        return trie.getSuggestions(prefix.toLowerCase(), topN); // Return top N suggestions
    }
}

// Main class to test the Search Autocomplete System
public class PartNSearchAutocompleteSystem {
    public static void main(String[] args) {
        SearchQuery searchQuery = new SearchQuery();

        // Add queries to the system
        searchQuery.addQuery("apple");
        searchQuery.addQuery("app");
        searchQuery.addQuery("application");
        searchQuery.addQuery("apricot");
        searchQuery.addQuery("banana");
        searchQuery.addQuery("bat");
        searchQuery.addQuery("ball");

        // Get autocomplete suggestions
        String input = "ap";
        int topN = 3;
        List<String> suggestions = searchQuery.getAutocompleteSuggestions(input, topN);

        // Output suggestions
        System.out.println("Autocomplete suggestions for '" + input + "': " + suggestions);
    }
}
