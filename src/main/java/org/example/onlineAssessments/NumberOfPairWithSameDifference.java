package org.example.onlineAssessments;

import java.util.*;

/**
 *
 * ref:https://leetcode.com/discuss/interview-question/4723156/Amazon-OA
 * ref2: https://leetcode.com/problems/count-number-of-pairs-with-absolute-difference-k/description/
 */

public class NumberOfPairWithSameDifference {

    public static void main(String[] args) {
        NumberOfPairWithSameDifference sol = new NumberOfPairWithSameDifference();
        int[] w1 = new int[]{4, 5, 5, 4, 4, 5, 2, 3};
        int[] w2 = new int[]{1,2,3,4,5,5,6,6};
        int[] w3 = new int[]{1,2,3,4,5,5,5,6,6,6,7,7,8,8};
        int[] w4 = new int[]{-1,-1,0,0,10,12};
        int[] w5 = new int[]{6,5,4,5};

        Set<Integer> s = new HashSet<>();
        s.add(1);
        s.add(2);

        Set<Integer> s2 = new HashSet<>();
        s2.add(1);
        s2.add(2);

        Set<Integer> s3 = new HashSet<>();
        s3.add(2);
        s3.add(3);


        Set<Set<Integer>> setOfSet = new HashSet<>();

        setOfSet.add(s);
        setOfSet.add(s2);
        setOfSet.add(s3);

        int waysOfGroupingParcels = sol.getWaysOfGroupingParcels(new int[]{4, 5, 5, 4, 4, 5, 2, 3}, 1);
        int res1 = sol.getWaysOfGroupingParcels(w3, 1);
        int res2 = sol.getWaysOfGroupingParcels(w4, 1);
        int res3 = sol.getWaysOfGroupingParcels(w5, 1);
//        int res3 = sol.getWaysOfGroupingParcels(new int[]{4, 5, 5, 4, 4, 5, 2, 3}, 1);
        System.out.println(waysOfGroupingParcels);
    }

    public int getWaysOfGroupingParcels(int[] weights, int wt) {
        TreeMap<Integer, Integer> countMap = new TreeMap<>();

        for(int weight : weights) {
            countMap.put(weight, countMap.getOrDefault(weight, 0) + 1);
        }
        int answer = 1;
        for(Map.Entry<Integer, Integer> entry : countMap.entrySet()) {
            int currKey = entry.getKey();
            int currCount = entry.getValue();
            if(countMap.containsKey(currKey - wt) && countMap.get(currKey - wt) > 0) {
                int matchingPairs = countMap.get(currKey - wt);
                int waysOfPairs = getFactorialNumber(matchingPairs);
                answer *= waysOfPairs;
                countMap.put(currKey, currCount - matchingPairs);
                countMap.put(currKey - wt, 0);
            }
        }

        for(int value : countMap.values()) {
            if(value != 0) return 0;
        }
        return answer;
    }

    private int getFactorialNumber(int n) {
        if(n == 1) return 1;
        return n * getFactorialNumber(n - 1);
    }
}
