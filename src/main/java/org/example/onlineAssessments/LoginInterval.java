package org.example.onlineAssessments;

import java.util.*;

/**
 * Given lots of intervals of login and logout of users, find maximum rush happened.
 * example:
 * login: [1,5,5]
 * logout: [5,10,5]
 *
 * maximum rush happened at 5 and it happened thrice. So answer is 3
 *
 * login: [4,10]
 * logout: [8,20]
 * maximum rush is 1 in those two interval. Hence answer is 16 = 8-4+1 + 20-10+1
 *
 * I was able to solve it by bruteforce. Couldn't find any answer on internet including LC, which provides exactly correct solution to this.
 *
 * In simple terms maxRush*(length of final intervals which might or might not overlap)
 *
 * Needs to be done in O(nlogn) to pass all test case.
 *
 * To reduce complexity from (n square) I had tried with stacks too but only 7/15 test cases passed.
 *
 * ref: https://leetcode.com/discuss/interview-question/4709758/Amazon-OA-SDE2
 */
public class LoginInterval {

    public int getMaximumRush(int[] login, int[] logout) {
        TreeMap<Double, Integer> line = new TreeMap<>();
        int n = login.length;
        for(int i = 0; i < n; i++) {
            line.put((double) login[i], line.getOrDefault((double) login[i], 0) + 1);
            line.put(logout[i] + 0.5, line.getOrDefault(logout[i] + 0.5, 0) - 1);
        }


        int maxRush = 0;
        int answer = 0;
        int count = 0;
        int start = 0;
        for(Map.Entry<Double, Integer> entry : line.entrySet()) {
            count += entry.getValue();
            if(count > maxRush) {
                maxRush = count;
            }
        }
        int startIdx = 0;
        int currCount = 0;

        for(Map.Entry<Double, Integer> entry: line.entrySet()) {
            if(entry.getValue() > 0) startIdx = entry.getKey().intValue();
            else if(currCount == maxRush) {
                answer += maxRush *(Math.ceil(entry.getKey())- startIdx);
            }
            currCount += entry.getValue();
        }

        return answer;
    }

    public static void main(String[] args) {
        LoginInterval sol = new LoginInterval();
        int maximumRush = sol.getMaximumRush(new int[]{1, 5, 5}, new int[]{5, 10, 5});
        int maximumRush2 = sol.getMaximumRush(new int[]{4, 10}, new int[]{8, 20});
        int maximumRush3 = sol.getMaximumRush(new int[]{1,4}, new int[]{3,6});
        int maximumRush4 = sol.getMaximumRush(new int[]{1,2,1,10, 13}, new int[]{1,2,1, 19, 20});
        int maximumRush5 = sol.getMaximumRush(new int[]{1,3,5}, new int[]{2,4,6});

        System.out.println(maximumRush3);
        int[] login = {1, 5, 5};
        int[] logout = {5, 10, 5};
//        System.out.println(findMaximumRush(login, logout));
    }

}
