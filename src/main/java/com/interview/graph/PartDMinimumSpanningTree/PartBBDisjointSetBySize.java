package com.interview.graph.PartDMinimumSpanningTree;

import java.util.*;

/*
    Problem: Union-Find (Disjoint Set)
    Category: Data Structure, Graph
    Problem Link: [Union-Find Problems on LeetCode](https://leetcode.com/tag/union-find/)
    YouTube Video Link: https://www.youtube.com/watch?v=aBxjDBC4M1U&list=PLgUwDviBIf0oE3gA41TKO2H5bHpPd7fzn&index=46

    Explanation:
    This implementation uses the Disjoint Set (Union-Find) data structure with two key optimizations:
    1. Path Compression: Helps in flattening the structure of the tree whenever `find` is called, keeping the tree shallow and speeding up future operations.
    2. Union by Size: Attaches the smaller tree under the root of the larger tree to keep the trees balanced, ensuring efficient `union` operations.

    Complexity:
    - Time Complexity: O(α(n)) for both `find` and `union` operations, where α is the inverse Ackermann function. This function grows very slowly, making the operations nearly constant time.
    - Space Complexity: O(n) for storing the parent and size arrays.

    Usage:
    - **Initialization**: Create an instance of `DisjointSet` with the number of elements.
    - **find(u)**: Finds the root representative of the set containing element `u`.
    - **union(u, v)**: Merges the sets containing elements `u` and `v`.
*/

class PartBBDisjointSetBySize {
    private int[] parent;
    private int[] size; // Array to keep track of the size of each set

    /**
     * Initializes the Disjoint Set with 'n' elements.
     * Each element is its own parent initially, and size is 1.
     *
     * @param n Number of elements.
     */
    public PartBBDisjointSetBySize(int n) {
        parent = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1; // Initial size of each set is 1
        }
    }

    /**
     * Finds the representative (root) of the set containing 'u'.
     *
     * @param u Element whose set representative is to be found.
     * @return The root representative of the set.
     */
    public int find(int u) {
        if (parent[u] != u) {
            parent[u] = find(parent[u]);  // Path compression
        }
        return parent[u];
    }

    /**
     * Merges the sets containing 'u' and 'v'.
     * Uses union by size to ensure balanced trees.
     *
     * @param u First element.
     * @param v Second element.
     */
    public void union(int u, int v) {
        int rootU = find(u);
        int rootV = find(v);

        if (rootU != rootV) {
            // Union by size
            if (size[rootU] < size[rootV]) {
                parent[rootU] = rootV;
                size[rootV] += size[rootU];
            } else {
                parent[rootV] = rootU;
                size[rootU] += size[rootV];
            }
        }
    }

    public static void main(String[] args) {
        // Initialize Disjoint Set with 7 elements (0-based index)
        PartBBDisjointSetBySize ds = new PartBBDisjointSetBySize(7);

        // Perform union operations
        ds.union(0, 1);
        ds.union(1, 2);
        ds.union(3, 4);
        ds.union(5, 6);
        ds.union(4, 5);

        // Check if elements 2 and 6 are in the same set
        if (ds.find(2) == ds.find(6)) {
            System.out.println("Same");
        } else {
            System.out.println("Not Same");
        }

        // Perform another union operation
        ds.union(2, 6);

        // Check again if elements 2 and 6 are in the same set
        if (ds.find(2) == ds.find(6)) {
            System.out.println("Same");
        } else {
            System.out.println("Not Same");
        }
    }

/*
    Problem: Union-Find (Disjoint Set)
    Category: Data Structure, Graph
    Problem Link: [Union-Find Problems on LeetCode](https://leetcode.com/tag/union-find/)
    YouTube Video Link: https://www.youtube.com/watch?v=ZGr5nX-Gi6Y&list=PLgUwDviBIf0oE3gA41TKO2H5bHpPd7fzn&index=48

    Explanation:
    This implementation uses the Disjoint Set (Union-Find) data structure with two key optimizations:
    1. Path Compression: Helps in flattening the structure of the tree whenever `find` is called, keeping the tree shallow and speeding up future operations.
    2. Union by Size: Attaches the smaller tree under the root of the larger tree to keep the trees balanced, ensuring efficient `union` operations.

    Complexity:
    - Time Complexity: O(α(n)) for both `find` and `union` operations, where α is the inverse Ackermann function. This function grows very slowly, making the operations nearly constant time.
    - Space Complexity: O(n) for storing the parent and size arrays.

    Usage:
    - **Initialization**: Create an instance of `DisjointSet` with the number of elements.
    - **find(u)**: Finds the root representative of the set containing element `u`.
    - **union(u, v)**: Merges the sets containing elements `u` and `v`.
*/

    static class NumberofProvincesDisjointSet {
        private int[] parent;
        private int[] size; // Array to keep track of the size of each set

        /**
         * Initializes the Disjoint Set with 'n' elements.
         * Each element is its own parent initially, and size is 1.
         *
         * @param n Number of elements.
         */
        public NumberofProvincesDisjointSet(int n) {
            parent = new int[n];
            size = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1; // Initial size of each set is 1
            }
        }

        /**
         * Finds the representative (root) of the set containing 'u'.
         *
         * @param u Element whose set representative is to be found.
         * @return The root representative of the set.
         */
        public int find(int u) {
            if (parent[u] != u) {
                parent[u] = find(parent[u]);  // Path compression
            }
            return parent[u];
        }

        /**
         * Merges the sets containing 'u' and 'v'.
         * Uses union by size to ensure balanced trees.
         *
         * @param u First element.
         * @param v Second element.
         */
        public void union(int u, int v) {
            int rootU = find(u);
            int rootV = find(v);

            if (rootU != rootV) {
                // Union by size
                if (size[rootU] < size[rootV]) {
                    parent[rootU] = rootV;
                    size[rootV] += size[rootU];
                } else {
                    parent[rootV] = rootU;
                    size[rootU] += size[rootV];
                }
            }
        }

        public void main(String[] args) {
            // Initialize Disjoint Set with 7 elements (0-based index)
            NumberofProvincesDisjointSet ds = new NumberofProvincesDisjointSet(7);

            // Perform union operations
            ds.union(0, 1);
            ds.union(1, 2);
            ds.union(3, 4);
            ds.union(5, 6);
            ds.union(4, 5);

            // Check if elements 2 and 6 are in the same set
            if (ds.find(2) == ds.find(6)) {
                System.out.println("Same");
            } else {
                System.out.println("Not Same");
            }

            // Perform another union operation
            ds.union(2, 6);

            // Check again if elements 2 and 6 are in the same set
            if (ds.find(2) == ds.find(6)) {
                System.out.println("Same");
            } else {
                System.out.println("Not Same");
            }
        }
    }


/*
    Problem: Number of Operations to Make Network Connected
    Category: Graph, Union-Find
    Problem Link: [Number of Operations to Make Network Connected on LeetCode](https://leetcode.com/problems/number-of-operations-to-make-network-connected/)
    YouTube Video Link: https://www.youtube.com/watch?v=FYrl7iz9_ZU&list=PLgUwDviBIf0oE3gA41TKO2H5bHpPd7fzn&index=49

    Explanation:
    This problem involves determining the minimum number of operations required to connect all nodes in a network (graph) where nodes are connected by edges. The goal is to make the network fully connected by connecting all disconnected components.
    The Union-Find (Disjoint Set Union) data structure is used to efficiently manage and merge disjoint sets and determine the number of operations needed.

    Complexity:
    - Time Complexity: O(n + e * α(n)), where n is the number of nodes and e is the number of edges. The α(n) is the inverse Ackermann function, which grows very slowly.
    - Space Complexity: O(n) for storing the parent and size arrays.

    Usage:
    - **Initialization**: Create an instance of `DisjointSet` with the number of nodes.
    - **find(u)**: Finds the root representative of the set containing element `u`.
    - **union(u, v)**: Merges the sets containing elements `u` and `v`.
    - **makeNetworkConnected(n, connections)**: Computes the minimum number of operations to connect all nodes.
*/

    public static class NumberofOperationstoMakeNetworkConnected {
        private int[] parent;
        private int[] size;

        /**
         * Initializes the Disjoint Set with 'n' elements.
         * Each element is its own parent initially, and size is 1.
         *
         * @param n Number of elements.
         */
        public NumberofOperationstoMakeNetworkConnected(int n) {
            parent = new int[n];
            size = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1; // Initial size of each set is 1
            }
        }

        /**
         * Finds the representative (root) of the set containing 'u'.
         *
         * @param u Element whose set representative is to be found.
         * @return The root representative of the set.
         */
        public int find(int u) {
            if (parent[u] != u) {
                parent[u] = find(parent[u]);  // Path compression
            }
            return parent[u];
        }

        /**
         * Merges the sets containing 'u' and 'v'.
         * Uses union by size to ensure balanced trees.
         *
         * @param u First element.
         * @param v Second element.
         */
        public void union(int u, int v) {
            int rootU = find(u);
            int rootV = find(v);

            if (rootU != rootV) {
                // Union by size
                if (size[rootU] < size[rootV]) {
                    parent[rootU] = rootV;
                    size[rootV] += size[rootU];
                } else {
                    parent[rootV] = rootU;
                    size[rootU] += size[rootV];
                }
            }
        }

        /**
         * Determines the minimum number of operations required to connect all nodes in the network.
         *
         * @param n Number of nodes.
         * @param connections List of edges (connections) between nodes.
         * @return Minimum number of operations needed to connect all nodes, or -1 if it's not possible.
         */
        public static int makeNetworkConnected(int n, int[][] connections) {
            if (connections.length < n - 1) {
                return -1; // Not enough connections to connect all nodes
            }

            NumberofOperationstoMakeNetworkConnected ds = new NumberofOperationstoMakeNetworkConnected(n);

            // Process each connection and perform union operations
            for (int[] connection : connections) {
                ds.union(connection[0], connection[1]);
            }

            // Count the number of unique connected components
            Set<Integer> uniqueComponents = new HashSet<>();
            for (int i = 0; i < n; i++) {
                uniqueComponents.add(ds.find(i));
            }

            // Number of operations needed is the number of unique components minus one
            return uniqueComponents.size() - 1;
        }

        public static void main(String[] args) {
            // Example input
            int n = 6;
            int[][] connections = {
                    {0, 1},
                    {0, 2},
                    {0, 3},
                    {1, 4},
                    {2, 5}
            };

            // Find the minimum number of operations to connect all nodes
            int minOperations = makeNetworkConnected(n, connections);
            System.out.println("Minimum number of operations: " + minOperations);
        }
    }

    /*
        Problem: LeetCode 721 - Accounts Merge
        Problem Category: Disjoint Set (Union-Find), Facebook, FAANG
        Problem Link: https://leetcode.com/problems/accounts-merge/
        YouTube Explanation: NeetCode - Accounts Merge
        Video: https://www.youtube.com/watch?v=FMwpt_aQOGw&list=PLgUwDviBIf0oE3gA41TKO2H5bHpPd7fzn&index=50

        Problem Description:
        Given a list of accounts where each account corresponds to a person, and each account contains the person's name followed by their emails,
        the task is to merge accounts that have at least one common email into a single account. The result should be a list of accounts where
        each account is sorted in lexicographical order (excluding the first element, which is the name).

        Detailed Explanation:
        Disjoint Set (Union-Find) Approach:
        - Treat each account as a node in the graph.
        - Connect accounts (nodes) that share at least one common email using union operations.
        - Finally, collect and group emails that belong to the same connected component (i.e., have the same root in the Disjoint Set).

        Steps:
        1. Use a DisjointSetBySize data structure to manage union-find operations.
        2. Map each email to the index of the account it appears in.
        3. For each account, union the current account index with the account index already mapped to each email.
        4. After processing all emails, find the representative parent (root) for each email and group them together.
        5. Sort the emails lexicographically and attach the original name to the beginning of the list.

        Time and Space Complexity:
        - Time Complexity: O(N * logN * α(N) + M * logM), where N is the number of accounts and M is the total number of emails across all accounts.
          The term α(N) refers to the inverse Ackermann function, which is almost constant for all practical purposes.
        - Space Complexity: O(N + M), due to the space used for the parent and size arrays, and the hashmap storing the emails.
    */
    public static class Account {
        static List<List<String>> accountsMerge(List<List<String>> accounts) {
            int n = accounts.size();
            PartBBDisjointSetBySize ds = new PartBBDisjointSetBySize(n);
            Map<String, Integer> mapMailNode = new HashMap<>();

            for (int i = 0; i < n; i++) {
                for (int j = 1; j < accounts.get(i).size(); j++) {
                    String mail = accounts.get(i).get(j);
                    if (!mapMailNode.containsKey(mail)) {
                        mapMailNode.put(mail, i);
                    } else {
                        ds.union(i, mapMailNode.get(mail));
                    }
                }
            }

            // Create a list to hold merged emails
            List<String>[] mergedMail = new List[n];
            for (int i = 0; i < n; i++) {
                mergedMail[i] = new ArrayList<>();
            }

            // Group emails by their root parent
            for (Map.Entry<String, Integer> entry : mapMailNode.entrySet()) {
                String mail = entry.getKey();
                int root = ds.find(entry.getValue());
                mergedMail[root].add(mail);
            }

            // Prepare the result list
            List<List<String>> result = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                if (mergedMail[i].size() == 0) continue;
                Collections.sort(mergedMail[i]);
                List<String> account = new ArrayList<>();
                account.add(accounts.get(i).get(0));
                account.addAll(mergedMail[i]);
                result.add(account);
            }

            return result;
        }

        public static void main(String[] args) {
            List<List<String>> accounts = new ArrayList<>() {
                {
                    add(new ArrayList<>(Arrays.asList("John", "j1@com", "j2@com", "j3@com")));
                    add(new ArrayList<>(Arrays.asList("John", "j4@com")));
                    add(new ArrayList<>(Arrays.asList("Raj", "r1@com", "r2@com")));
                    add(new ArrayList<>(Arrays.asList("John", "j1@com", "j5@com")));
                    add(new ArrayList<>(Arrays.asList("Raj", "r2@com", "r3@com")));
                    add(new ArrayList<>(Arrays.asList("Mary", "m1@com")));
                }
            };

            Account obj = new Account();
            List<List<String>> result = obj.accountsMerge(accounts);

            for (List<String> account : result) {
                System.out.print(account.get(0) + ": ");
                for (int j = 1; j < account.size(); j++) {
                    System.out.print(account.get(j) + " ");
                }
                System.out.println();
            }
        }
    }

}

