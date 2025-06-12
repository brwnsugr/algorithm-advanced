package org.example.onlineAssessments.y2024q3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TwoWareHouse {
    public int getMinimumSumDist(int[] dist) {
        int answer = 0;

        Arrays.sort(dist);

        int n = dist.length;
        int minVal = dist[0];
        int maxVal = dist[dist.length - 1];

        // Cluster the dist centers
        List<Integer> firstCluster = new ArrayList<>();
        List<Integer> secondCluster = new ArrayList<>();

        for(int i = 0; i < n; i++) {
            int minValDist = Math.abs(dist[i] - minVal);
            int maxValDist = Math.abs(dist[i] - maxVal);

            if(minValDist < maxValDist) {
                firstCluster.add(dist[i]);
            }
            else{
                secondCluster.add(dist[i]);
            }
        }

        if(firstCluster.isEmpty()) {
            firstCluster.add(dist[0]);
        }
        if(secondCluster.isEmpty()) {
            secondCluster.add(dist[dist.length - 1]);
        }

        int med1 = firstCluster.get(firstCluster.size() / 2);
        int med2 = secondCluster.get(secondCluster.size() / 2);

        for(int i = 0; i < n; i++) {
            answer += Math.min(Math.abs(dist[i] - med1), Math.abs(dist[i] - med2));
        }

        return answer;
    }

    public static void main(String[] args) {
        TwoWareHouse tw = new TwoWareHouse();
        int minimumSumDist = tw.getMinimumSumDist(new int[]{1,100,101,102,103,104});
        System.out.println(minimumSumDist);
    }

}
