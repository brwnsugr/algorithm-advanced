package org.example.onlineAssessments;

import java.util.PriorityQueue;

/**
 * for "hello" costs are [0, 0, 0, 1, 0] corresponding to each index, for second l, cost add by 1
 * for string "abc" the costs are [0, 0, 0] corresponding to each index
 *
 * ref: https://leetcode.com/company/amazon/discuss/4555446/Amazon-2024-SWE-OA-getMinCostData
 */
public class MinCostData {


    /**
     * need to refactor
     *
     */

    public static void main(String[] args) {
        MinCostData minCostData = new MinCostData();
        String minCostData1 = minCostData.getMinCostData("abcdefghijklmnopqrstuvwxyz??");
        System.out.println(minCostData1);

    }
    public String getMinCostData(String source) {
        int[] count = new int[26];
        for (int i=0; i<source.length(); i++) {
            if (source.charAt(i) != '?') {
                count[source.charAt(i) - 'a']++;
            }
        }

        PriorityQueue<Character> queue = new PriorityQueue<>(
                (a,b) -> count[a - 'a'] != count[b - 'a'] ? Integer.compare(count[a - 'a'], count[b - 'a']) : Character.compare(a, b) // lower case, not upper. compare count, then compare chars
        );
        for (char c='a'; c<='z'; c++) {
            queue.add(c);
        }
        StringBuilder res = new StringBuilder();
        for (int i=0; i<source.length(); i++) {
            char c = source.charAt(i);
            if (c == '?') {
                c = queue.poll();
                res.append(c);
                count[c - 'a']++;
                queue.add(c);
            } else {
                res.append(c);
            }
        }

        return res.toString();
    }

}
