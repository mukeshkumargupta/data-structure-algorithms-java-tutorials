package com.interview.graph;

/*
 * https://leetcode.com/problems/all-paths-from-source-to-target/
 * Category: Medium, Must Do, dfs, all path, same as all path in tree from root to leaf
 * Related: https://leetcode.com/problems/number-of-ways-to-arrive-at-destination/
 * 
 * 797. All Paths From Source to Target
Medium

3987

115

Add to List

Share
Given a directed acyclic graph (DAG) of n nodes labeled from 0 to n - 1, find all possible paths from node 0 to node n - 1 and return them in any order.

The graph is given as follows: graph[i] is a list of all nodes you can visit from node i (i.e., there is a directed edge from node i to node graph[i][j]).

 

Example 1:


Input: graph = [[1,2],[3],[3],[]]
Output: [[0,1,3],[0,2,3]]
Explanation: There are two paths: 0 -> 1 -> 3 and 0 -> 2 -> 3.
Example 2:


Input: graph = [[4,3,1],[3,2,4],[3],[4],[]]
Output: [[0,4],[0,3,4],[0,1,3,4],[0,1,2,3,4],[0,1,4]]
 

Constraints:

n == graph.length
2 <= n <= 15
0 <= graph[i][j] < n
graph[i][j] != i (i.e., there will be no self-loops).
All the elements of graph[i] are unique.
The input graph is guaranteed to be a DAG.
 */
public class AllPathsFromSourcetoTarget {
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        /*
         * There are various method has been mentionjed to solve this question
         * Reference: https://leetcode.com/problems/all-paths-from-source-to-target/discuss/1956846/C%2B%2B-Solutions-DFS-or-BFS-or-Bactracking
         * Runtime: 1 ms, faster than 99.85% of Java online submissions for All Paths From Source to Target.
Memory Usage: 44.7 MB, less than 81.15% of Java online submissions for All Paths From Source to Target.
         */
        List<List<Integer>> result = new LinkedList<>();
          List<Integer> path = new ArrayList<>();
          path.add(0);
          DFS(0,graph.length -1,path,result,graph);
          return result;
      }
      
      private void DFS(int node, int target, List<Integer> path, List<List<Integer>> result, int[][] graph) {
         /*
          * //TC : O(2 pow N.N)
//SC : O(2 pow N.N) 
          */
          if (node == target) {
              result.add(new ArrayList(path));
              return;
          }
          
          for (int child : graph[node]) {
              path.add(child);
              DFS(child,target,path,result,graph);
              path.remove(path.size() - 1);//backtrack
          }
      }
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        /* this solution is exactly find all path from root to lead in tree, but it is not best, 
         * Runtime: 3 ms, faster than 83.18% of Java online submissions for All Paths From Source to Target.
Memory Usage: 46 MB, less than 80.09% of Java online submissions for All Paths From Source to Target.
         */
        int l = graph.length;
        List<List<Integer>> result = new LinkedList<>();
        List<Integer> path = new LinkedList<>();
        findPath(graph, 0, path, l-1, result);
        return result;
    }
    
    private void findPath(int[][] graph, int node, List<Integer> path, int destination, List<List<Integer>> result) {

        
        // base case
        if(node == destination) {
            path.add(node);
            result.add(new LinkedList<>(path)); //!!!!!!!!!! Java
            
            // for this question, we do not have to return; 
            // if we want to return, we need to add "path.remove(path.size() - 1);", to remove the sink node, and maintain rightness
            path.remove(path.size() - 1);
            return;
        }
        
        // writing backtracking in this way will not miss the source node
        path.add(node);
        
        for(int child: graph[node]) {
            findPath(graph, child, path, destination, result);
        }
        path.remove(path.size() - 1);
    }
}
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    }
    
}
