package org.example.leetcode.AmznTag;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * @ref: https://leetcode.com/problems/race-car/
 */
public class RaceCar_818 {
    public int racecar(int target) {

        /**
         * TC is O(N) where target is N
         * for accelerating, it takse O(logN) we only go through 2 -> 4 -> 8
         * for reverse, it takes at most N. So, finally we get O(N)
         */

        Queue<int[]> q = new LinkedList<>();
        Set<int[]> visited = new HashSet<>();
        int steps = 0;
        int position = 0;
        int speed = 0;

        q.add(new int[]{0, 0, 1});

        while(!q.isEmpty()) {
            int[] curr = q.poll();
            steps = curr[0];
            position = curr[1];
            speed = curr[2];

            if(position == target) {
                return steps;
            }
            // already visited, skip that
            else if(visited.contains(new int[]{position, speed})) continue;
            else {
                visited.add(new int[]{position, speed});
                // accelerate
                q.add(new int[]{steps + 1, position + speed, speed * 2});

                // reverse
                if((position + speed > target && speed > 0) || (position + speed < target && speed < 0)) {
                    q.add(new int[]{steps + 1, position, speed > 0 ? -1 : 1});
                }
            }
        }
        return steps;
    }
}
