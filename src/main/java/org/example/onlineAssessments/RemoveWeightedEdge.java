package org.example.onlineAssessments;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * https://leetcode.com/discuss/interview-question/4326333/Amazon-OA
 */
public class RemoveWeightedEdge {

    public int getMaxWeightSum(int[] fromList, int[] toList, int[] weightList) {
        // Disjoint Set & minimum Spanning Tree (Sort by its weight)

        return 1;
    }

    private static class Edge implements Comparable<Edge> {
        int src, dest, weight;

        public Edge(int src, int dest, int weight) {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge other) {
            return this.weight - other.weight;
        }
    }

    private static class DisjointSet {
        int[] parent, rank;

        public DisjointSet(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        public int find(int i) {
            if (parent[i] != i) {
                parent[i] = find(parent[i]);
            }
            return parent[i];
        }

        public void union(int x, int y) {
            int xRoot = find(x), yRoot = find(y);

            if (xRoot == yRoot) return;

            if (rank[xRoot] < rank[yRoot])
                parent[xRoot] = yRoot;
            else if (rank[yRoot] < rank[xRoot])
                parent[yRoot] = xRoot;
            else {
                parent[yRoot] = xRoot;
                rank[xRoot]++;
            }
        }
    }

    public static int maxWeightRemoval(int g_nodes, int g_edges, int[][] connections) {
        List<Edge> edges = new ArrayList<>();
        for (int[] connection : connections) {

            edges.add(new Edge(connection[0] - 1, connection[1] - 1, connection[2]));
        }

        Collections.sort(edges);

        DisjointSet ds = new DisjointSet(g_nodes);
        int totalWeight = 0, mstWeight = 0;

        for (Edge edge : edges) {
            totalWeight += edge.weight;
            if (ds.find(edge.src) != ds.find(edge.dest)) {
                ds.union(edge.src, edge.dest);
                mstWeight += edge.weight;
            }
        }

        return totalWeight - mstWeight;
    }
}
