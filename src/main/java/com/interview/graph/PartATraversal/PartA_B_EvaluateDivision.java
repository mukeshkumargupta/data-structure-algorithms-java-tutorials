package com.interview.graph.PartATraversal;

import java.util.*;

/*
https://leetcode.com/problems/evaluate-division/description/?envType=study-plan-v2&envId=top-interview-150
https://www.youtube.com/watch?v=3SVy5uUKZsM
Category: Medium, Must Do, top150, tricky
Related:
https://leetcode.com/problems/check-for-contradictions-in-equations/ Hard
https://leetcode.com/problems/maximize-amount-after-two-days-of-conversions/ Medium
You are given an array of variable pairs equations and an array of real numbers values, where equations[i] = [Ai, Bi] and values[i] represent the equation Ai / Bi = values[i]. Each Ai or Bi is a string that represents a single variable.

You are also given some queries, where queries[j] = [Cj, Dj] represents the jth query where you must find the answer for Cj / Dj = ?.

Return the answers to all queries. If a single answer cannot be determined, return -1.0.

Note: The input is always valid. You may assume that evaluating the queries will not result in division by zero and that there is no contradiction.

Note: The variables that do not occur in the list of equations are undefined, so the answer cannot be determined for them.



Example 1:

Input: equations = [["a","b"],["b","c"]], values = [2.0,3.0], queries = [["a","c"],["b","a"],["a","e"],["a","a"],["x","x"]]
Output: [6.00000,0.50000,-1.00000,1.00000,-1.00000]
Explanation:
Given: a / b = 2.0, b / c = 3.0
queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ?
return: [6.0, 0.5, -1.0, 1.0, -1.0 ]
note: x is undefined => -1.0
Example 2:

Input: equations = [["a","b"],["b","c"],["bc","cd"]], values = [1.5,2.5,5.0], queries = [["a","c"],["c","b"],["bc","cd"],["cd","bc"]]
Output: [3.75000,0.40000,5.00000,0.20000]
Example 3:

Input: equations = [["a","b"]], values = [0.5], queries = [["a","b"],["b","a"],["a","c"],["x","y"]]
Output: [0.50000,2.00000,-1.00000,-1.00000]


Constraints:

1 <= equations.length <= 20
equations[i].length == 2
1 <= Ai.length, Bi.length <= 5
values.length == equations.length
0.0 < values[i] <= 20.0
1 <= queries.length <= 20
queries[i].length == 2
1 <= Cj.length, Dj.length <= 5
Ai, Bi, Cj, Dj consist of lower case English letters and digits.
 */
public class PartA_B_EvaluateDivision {
    // Build graph: variable → list of neighbors with their weights
    private Map<String, List<Pair>> buildGraph(List<List<String>> equations, double[] values) {
        Map<String, List<Pair>> graph = new HashMap<>();

        for (int i = 0; i < equations.size(); i++) {
            String u = equations.get(i).get(0);
            String v = equations.get(i).get(1);
            double weight = values[i];

            graph.putIfAbsent(u, new ArrayList<>());
            graph.putIfAbsent(v, new ArrayList<>());
            graph.get(u).add(new Pair(v, weight));
            graph.get(v).add(new Pair(u, 1.0 / weight));
        }

        return graph;
    }

    // DFS to evaluate value from u to v
    private double dfs(String u, String v, Set<String> visited, Map<String, List<Pair>> graph) {
        if (!graph.containsKey(u) || !graph.containsKey(v)) return -1.0;//undefined case ie any one of them not there
        if (u.equals(v)) return 1.0;// here u reaches v finally

        visited.add(u);

        for (Pair neighbor : graph.get(u)) {//Think like a tree having mutiple branches, this how it go deep and then return the value, remember recursion
            if (!visited.contains(neighbor.node)) {
                double product = dfs(neighbor.node, v, visited, graph);
                if (product != -1.0) {
                    return neighbor.weight * product;
                }
            }
        }

        return -1.0;
    }

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Map<String, List<Pair>> graph = buildGraph(equations, values);
        double[] result = new double[queries.size()];

        for (int i = 0; i < queries.size(); i++) {
            String u = queries.get(i).get(0);
            String v = queries.get(i).get(1);
            Set<String> visited = new HashSet<>();
            result[i] = dfs(u, v, visited, graph);
        }

        return result;
    }

    static class Pair {
        String node;
        double weight;

        public Pair(String node, double weight) {
            this.node = node;
            this.weight = weight;
        }
    }

    public static void main(String[] args) {
        PartA_B_EvaluateDivision solver = new PartA_B_EvaluateDivision();

        List<List<String>> equations = Arrays.asList(
                Arrays.asList("a", "b"),
                Arrays.asList("b", "c")
        );
        double[] values = {2.0, 3.0};

        List<List<String>> queries = Arrays.asList(
                Arrays.asList("a", "c"), // 6.0
                Arrays.asList("b", "a"), // 0.5
                Arrays.asList("a", "e"), // -1.0
                Arrays.asList("a", "a"), // 1.0
                Arrays.asList("x", "x")  // -1.0
        );

        System.out.println(Arrays.toString(solver.calcEquation(equations, values, queries)));
        // Output: [6.0, 0.5, -1.0, 1.0, -1.0]
    }
}
