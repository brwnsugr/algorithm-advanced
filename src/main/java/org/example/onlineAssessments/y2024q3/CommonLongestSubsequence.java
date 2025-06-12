package org.example.onlineAssessments.y2024q3;

import java.util.Arrays;

/**
 * ref: https://leetcode.com/discuss/interview-question/5738176/Amazon-Online-Assessment-Aug-2024
 *
 * get longest subseq
 */

public class CommonLongestSubsequence {

    public int getCommonLongestLength(int[] arr1, int[] arr2) {
        int n = arr1.length;

        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        boolean validSubsequence = false; // Flag to check if any valid subsequence exists

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (arr1[i] < arr1[j] && arr2[i] < arr2[j]) {
                    dp[i] = Math.max(dp[i], 1 + dp[j]);
                    validSubsequence = true; // Set flag to true if a valid subsequence is found
                }
            }
        }

        int maxElement = Arrays.stream(dp).max().getAsInt();
        int inc = validSubsequence ? maxElement : 0; // Return 0 if no valid subsequence is found

        validSubsequence = false;
        int[] dp2 = new int[n];
        Arrays.fill(dp2, 1);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (arr1[i] > arr1[j] && arr2[i] > arr2[j]) {
                    dp2[i] = Math.max(dp2[i], 1 + dp2[j]);
                    validSubsequence = true; // Set flag to true if a valid subsequence is found
                }
            }
        }

        maxElement = Arrays.stream(dp2).max().getAsInt();
        int dec = validSubsequence ? maxElement : 0;

        return Math.max(inc, dec);
    }

    public static void main(String[] args) {
        CommonLongestSubsequence cc = new CommonLongestSubsequence();
        int[] arr1 = {1,3,2,4};
        int[] arr2 = {2,3,1,5};

        int[] arr12 = {4,3,2,1};
        int[] arr22 = {4,3,2,0};

        int[] arr13 = {4,5,3,1,2,0};
        int[] arr23 = {2,1,3,1,5,0};
        int commonLongestLength = cc.getCommonLongestLength(arr13, arr23);
        System.out.println(commonLongestLength);
    }
}
