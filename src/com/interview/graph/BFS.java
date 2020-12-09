package com.interview.graph;

import java.util.*;

//Reference: https://practice.geeksforgeeks.org/problems/bfs-traversal-of-graph/1
//Category: Must DO
//In this question not asking to print all node while asking print only grom starting index so trying all node is not mandatory

public class BFS {
	static void bfsUtil(int s, ArrayList<ArrayList<Integer>> list, boolean vis[], int nov) {
		// add your code here
		Queue<Integer> que = new LinkedList<Integer>();

		que.add(s);
		vis[s] = true;
		while (!que.isEmpty()) {
			int top = que.poll();
			System.out.print(top + " ");
			// Put all its adgecent in queue
			ArrayList<Integer> adjList = list.get(top);
			for (int i = 0; i < adjList.size(); i++) {
				int item = adjList.get(i);
				if (!vis[item]) {
					que.add(item);
					vis[item] = true;
				}
			}
		}
	}

	static void bfs(int s, ArrayList<ArrayList<Integer>> list, boolean vis[], int nov) {
		// try with this first if still left try with others
		bfsUtil(s, list, vis, 0);
		for (int i = 0; i < list.size(); i++) {
			if (!vis[i]) {
				bfsUtil(i, list, vis, 0);
			}

		}
	}

	public static void main(String[] args) {

		ArrayList<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>();
		//make adjence list like 
		//5 4 T1 5 node 4 edge
		//0 1 0 2 0 3 2 4
		//3 2
		//0 1 0 2
		//so for t1
		//adj will be like for 0 it will be list like 1, 2, 3
		//for 2, it will be 4 like that you have to give input

		boolean[] vis = new boolean[5];
		bfs(2, list, vis, 0);

	}
}
