package org.example.onlineAssessments.y2024q3;

import java.util.Arrays;

public class AmazonGameTournament {

    public int solution(int[] a, int[] b, int[] c){
        int n = a.length;
        int[][] player = new int[n][3];
        int max0 = 0, max1 = 0;

        for (int i = 0; i < n; i++) {
            player[i][0] = a[i];
            player[i][1] = b[i];
            player[i][2] = c[i];
            Arrays.sort(player[i]);
            max0 = Math.max(max0, player[i][0]);
            max1 = Math.max(max1, player[i][1]);
        }

        int ans = 0;
        for (int i = 0; i < n; i++)
            if (player[i][2] > max1 && player[i][1] > max0)
                ans++;

        return ans;
    }

    public static void main(String[] args) {
        AmazonGameTournament agt = new AmazonGameTournament();
        int[] a = {1, 2, 3};
        int[] b = {4, 5, 6};
        int[] c = {7, 8, 9};

        int[] a2 = {9,4,2};
        int[] b2 = {5,12,10};
        int[] c2 = {11,3,13};
        int solution = agt.solution(a2, b2, c2);
        System.out.println(solution);
    }
}
