package com.interview.graph;

import java.util.Set;
import java.util.Stack;
import java.util.HashSet;
import java.util.Deque;
import java.util.ArrayDeque;

/**
 * Date 08/20/2017
 * @author Mukesh Kumar Gupta
 *
 * Given a directed acyclic graph, do a topological sort on this graph.
 * Reference: https://www.youtube.com/watch?v=ddTC4Zovtbc
 *
 * Do DFS by keeping visited. Put the vertex which are completely explored into a stack.
 * Pop from stack to get sorted order.
 *
 * Space and time complexity is O(n).
 */
public class TopologicalSort<T> {

    /**
     * Main method to be invoked to do topological sorting.
     */
    public Deque<Vertex<T>> topSort(Graph<T> graph) {
        Deque<Vertex<T>> stack = new ArrayDeque<>();
        Set<Vertex<T>> visited = new HashSet<>();
        for (Vertex<T> vertex : graph.getAllVertex()) {
            if (visited.contains(vertex)) {
                continue;
            }
            topSortUtil(vertex,stack,visited);
        }
        return stack;
    }

    private void topSortUtil(Vertex<T> vertex, Deque<Vertex<T>> stack,
            Set<Vertex<T>> visited) {
        visited.add(vertex);
        for(Vertex<T> childVertex : vertex.getAdjacentVertexes()){
            if(visited.contains(childVertex)){
                continue;
            }
            topSortUtil(childVertex,stack,visited);
        }
        stack.offerFirst(vertex);
    }
    
    /**
     * Main method to be invoked to do topological sorting.
     */
    public Stack<Vertex<T>> topSort_V1(Graph<T> graph) {
        Stack<Vertex<T>> stack = new Stack<>();
        Set<Vertex<T>> visited = new HashSet<>();
        for (Vertex<T> vertex : graph.getAllVertex()) {
            if (visited.contains(vertex)) {
                continue;
            }
            topSortUtil_V1(vertex,stack,visited);
        }
        return stack;
    }

    private void topSortUtil_V1(Vertex<T> vertex, Stack<Vertex<T>> stack,
            Set<Vertex<T>> visited) {
        visited.add(vertex);
        for(Vertex<T> childVertex : vertex.getAdjacentVertexes()){
            if(visited.contains(childVertex)){
                continue;
            }
            topSortUtil_V1(childVertex,stack,visited);
        }
        stack.push(vertex);
    }
    
    public static void main(String args[]){
        Graph<Integer> graph = new Graph<>(true);
        graph.addEdge(1, 3);
        graph.addEdge(1, 2);
        graph.addEdge(3, 4);
        graph.addEdge(5, 6);
        graph.addEdge(6, 3);
        graph.addEdge(3, 8);
        graph.addEdge(8, 11);
        
        TopologicalSort<Integer> sort = new TopologicalSort<Integer>();
        Deque<Vertex<Integer>> result = sort.topSort(graph);
        while(!result.isEmpty()){
            System.out.println(result.poll());
        }
        System.out.println("\n");
        //V1 version
        Stack<Vertex<Integer>> result1 = sort.topSort_V1(graph);
        while(!result1.isEmpty()){
            System.out.println(result1.pop());
        }
    }
}
