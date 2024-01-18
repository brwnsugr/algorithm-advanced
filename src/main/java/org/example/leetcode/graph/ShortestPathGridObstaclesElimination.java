package org.example.leetcode.graph;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class ShortestPathGridObstaclesElimination {

    /**
     * LeetCode #1293. Shortest Path in a Grid with Obstacles Elimination
     * TC: O(m*n*k) - m is the number of rows, n is the number of columns, k is the number of obstacles
     * why M*N*K? Because we can visit each cell at most K times. so bounded by M*N*K
     * Key things to note:
     * visit state is 3D array where we keep track of the number of obstacles we have eliminated
     */
    private static int[][] DIRECTIONS = new int[][]{
            {-1, 0}, {0, -1}, {1, 0}, {0, 1}
    };

    public int shortestPath(int[][] grid, int k) {
        int rows = grid.length;
        int cols = grid[0].length;

        if(k >= rows + cols - 2) return rows + cols - 2;

        boolean[][][] visited = new boolean[rows][cols][k+1];

        Queue<int[]> q = new LinkedList<>();

        q.add(new int[]{0, 0, k, 0});

        visited[0][0][k] = true;

        while(!q.isEmpty()) {
            int[] curr = q.poll();
            int currR = curr[0];
            int currC = curr[1];
            int currK = curr[2];
            int dist = curr[3];

            if(currR == rows - 1 && currC == cols - 1) return dist;
            for(int[] direction : DIRECTIONS) {
                int nextR = currR + direction[0];
                int nextC = currC + direction[1];

                if(nextR >= 0 && nextR < rows
                        && nextC >= 0 && nextC < cols ) {
                    int newK = currK - grid[nextR][nextC];
                    if(newK >= 0 && !visited[nextR][nextC][newK]) {
                        visited[nextR][nextC][newK] = true;
                        q.add(new int[]{nextR, nextC, newK, dist + 1});
                    }
                }
            }
        }

        return -1;
    }


    /**
     * More optimized solution using priority queue
     * TC: O(m*n*log(m*n)) - m is the number of rows, n is the number of columns
     * why more optimized? Because we are using priority queue to get the next cell with the least number of obstacles
     * why pq sort by dist?
     */
    public int shortestPathPQ(int[][] grid, int k) {
        int rows = grid.length;
        int cols = grid[0].length;

        if(k >= rows + cols - 2) return rows + cols - 2;

        boolean[][][] visited = new boolean[rows][cols][k+1];

        PriorityQueue<int[]> q = new PriorityQueue<>(
                (a,b) -> a[3] - b[3]
        );

        q.add(new int[]{0, 0, k, 0});

        visited[0][0][k] = true;

        while(!q.isEmpty()) {
            int[] curr = q.poll();
            int currR = curr[0];
            int currC = curr[1];
            int currK = curr[2];
            int dist = curr[3];

            int remainDist = currK - dist;

            if(currR == rows - 1 && currC == cols - 1) return dist;
            for(int[] direction : DIRECTIONS) {
                int nextR = currR + direction[0];
                int nextC = currC + direction[1];

                if(nextR >= 0 && nextR < rows
                        && nextC >= 0 && nextC < cols ) {
                    int newK = currK - grid[nextR][nextC];
                    if(newK >= 0 && !visited[nextR][nextC][newK]) {
                        visited[nextR][nextC][newK] = true;
                        q.add(new int[]{nextR, nextC, newK, dist + 1});
                    }
                }
            }
        }

        return -1;
    }
}
