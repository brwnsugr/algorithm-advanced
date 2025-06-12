package org.example.onlineAssessments.smartn;

import java.util.*;

public class MaximalNetwork {

    public int maximalNetworkRank(int[] A, int[] B) {
        int n = A.length;
        Map<Integer, Set<Integer>> adjMap = new HashMap<>();

        for(int i = 0; i < n; i++) {
            adjMap.computeIfAbsent(A[i], k-> new HashSet<>()).add(B[i]);
            adjMap.computeIfAbsent(B[i], k-> new HashSet<>()).add(A[i]);
        }

        int maxRank = 0;

        for(int i = 0; i < n; i++) {
            for(int j = i + 1; j < n; j++) {
                int currRank = 0;
                if(adjMap.getOrDefault(i, Collections.emptySet()).contains(j)) {
                    currRank = adjMap.getOrDefault(i, Collections.emptySet()).size()
                            + adjMap.getOrDefault(j, Collections.emptySet()).size() - 1;
                }

                else {
                    currRank = Math.max(adjMap.getOrDefault(i, Collections.emptySet()).size(),
                            adjMap.getOrDefault(j, Collections.emptySet()).size());
                }

                maxRank = Math.max(currRank, maxRank);
            }
        }

        return maxRank;
    }


    public static void main(String[] args) {
        MaximalNetwork solution = new MaximalNetwork();

        int[] A = new int[]{0,1,2,3,4};
        int[] B = new int[]{1,0,1,1,4};
        int ans = solution.maximalNetworkRank(A, B);
        System.out.println(ans);
    }
}
