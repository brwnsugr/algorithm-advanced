package org.example.leetcode.graph;

import java.util.*;

public class CourseScheduleII {
/**
 * LeetCode #210. Course Schedule II
 *
 * There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.
 *
 * For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
 * Return the ordering of courses you should take to finish all courses. If there are many valid answers, return any of them. If it is impossible to finish all courses, return an empty array.
 *
 *
 *
 * Example 1:
 *
 * Input: numCourses = 2, prerequisites = [[1,0]]
 * Output: [0,1]
 * Explanation: There are a total of 2 courses to take. To take course 1 you should have finished course 0. So the correct course order is [0,1].
 * Example 2:
 *
 * Input: numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
 * Output: [0,2,1,3]
 * Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0.
 * So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3].
 *
 */

    /**
     * Using Queue and Topological Sort
     * TC: O(V+E) - V is the number of vertices and E is the number of edges
     * SC: O(V+E)*
     */
    public int[] findOrderTopologicalSort(int numCourses, int[][] prerequisites) {
        List<Integer>[] adjList = new ArrayList[numCourses];
        for(int i = 0; i < numCourses; i++) adjList[i] = new ArrayList<>();

        int[] indegrees = new int[numCourses];
        for(int[] pre : prerequisites) {
            int from = pre[0];
            int to = pre[1];
            adjList[from].add(to);
            indegrees[to]++;
        }

        Queue<Integer> q = new LinkedList<>();
        for(int i = 0; i < indegrees.length; i++) {
            if(indegrees[i] == 0) q.add(i);
        }

        int[] ans = new int[numCourses];
        Set<Integer> visited = new HashSet<>();
        int pos = 0;
        while(!q.isEmpty()) {
            int curr = q.remove();
            ans[pos++] = curr;
            visited.add(curr);
            for(int next : adjList[curr]) {
                indegrees[next]--;
                if(indegrees[next] == 0) q.add(next);
            }
        }

        for(int i = 0; i < numCourses/2; i++) {
            int tmp = ans[i];
            ans[i] = ans[numCourses - 1 - i];
            ans[numCourses - 1 - i] = tmp;
        }

        return visited.size() == numCourses ? ans : new int[]{};
    }

    private static int WHITE = 1;
    private static int GRAY = 2;
    private static int BLACK = 3;

    boolean foundCycle;
    Map<Integer, Integer> colorMap;
    Map<Integer, List<Integer>> adjList;
    List<Integer> topologicalOrder;


    public int[] findOrderDfs(int numCourses, int[][] prerequisites) {
        this.foundCycle = false;
        this.colorMap = new HashMap<>();
        this.adjList = new HashMap<>();
        this.topologicalOrder = new ArrayList<>();

        for(int i = 0; i < numCourses; i++) this.colorMap.put(i, WHITE);

        for(int[] pre : prerequisites) {
            int from = pre[1];
            int to = pre[0];
            if(!adjList.containsKey(from)) adjList.put(from, new ArrayList<>());
            adjList.get(from).add(to);
        }

        for(int i = 0; i < numCourses; i++) {
            if(this.colorMap.get(i) == WHITE) dfs(i);
        }

        if(this.foundCycle) return new int[]{};

        int[] ans = new int[numCourses];
        for(int i = 0; i < numCourses; i++) {
            ans[i] = this.topologicalOrder.get(numCourses - i - 1);
        }
        return ans;
    }

    private void dfs(int node) {
        if(this.foundCycle) return;

        // start the recursion
        this.colorMap.put(node, GRAY);

        for(int next : this.adjList.getOrDefault(node, new ArrayList<Integer>())) {
            if(this.colorMap.get(next) == WHITE) {
                dfs(next);
            }
            else if(this.colorMap.get(next) == GRAY) {
                // if we encounter the GRAY, it detects it has cycle
                this.foundCycle = true;
            }
        }

        this.colorMap.put(node, BLACK);
        this.topologicalOrder.add(node);
    }


}
