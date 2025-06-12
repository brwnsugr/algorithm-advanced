package org.example.onlineAssessments.y2024q3;

import java.util.Arrays;
import java.util.Collections;

/**
 * https://leetcode.com/discuss/interview-question/5657213/Amazon-OA
 * Need to efficiently distribute a collection of n computer games among k different children. Each game is characterized by its size, denoted by gameSize[i] for 1 ≤ i ≤ n.
 *
 * To facilitate the distribution process, the coordinator opts to utilize pen drives, ordering k pen drives with identical storage capacities. Each child can receive a maximum of 2 games, and every child must receive at least one game, also no game should be left unassigned.
 *
 * Considering the impracticality of transferring large game files over the internet, the strategy involves determining the minimum storage capacity required for the pen drives. A pen drive can only store games if the sum of their sizes does not exceed the pen drive's storage capacity.
 *
 * What is the minimum storage capacity of pen drives that you must order to be able to give these games to the children?
 *
 * Ex:
 * n = 4
 * gameSize[] = [9, 2, 4, 6]
 * k = 3
 */

public class GameDist {
    public int getMinimumSizeOfGame(int[] gameSize, int k){
        int n = gameSize.length;

        Integer[] gameSizeDesc = Arrays.stream(gameSize).boxed().toArray(Integer[]::new);
        Arrays.sort(gameSizeDesc, Collections.reverseOrder());

        int[] res = new int[k];

        // distribute the first k elements one by one
        for(int i = 0; i < k; i++) {
            res[i] = gameSizeDesc[i];
        }

        // distribute remaining elements
        int j = k - 1;

        for (int i = k; i < gameSizeDesc.length; i++) {
            res[j] += gameSizeDesc[i];
            j--;
            if (j < 0) {
                j = k - 1;
            }
        }

        // Find the maximum value in the result array
        int ans = 0;
        for (int i = 0; i < k; i++) {
            ans = Math.max(ans, res[i]);
        }

        return ans;
    }

    public static void main(String[] args) {
        GameDist gameDist = new GameDist();

        int minimumSizeOfGame = gameDist.getMinimumSizeOfGame(new int[]{9, 2, 4, 6}, 3);


        System.out.println(minimumSizeOfGame);
    }
}
