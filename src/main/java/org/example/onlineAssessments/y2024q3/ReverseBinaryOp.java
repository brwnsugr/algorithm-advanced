package org.example.onlineAssessments.y2024q3;

public class ReverseBinaryOp {

    public int findMinimumOps(String s) {

        StringBuilder reverseBuilder = new StringBuilder(s);
        String reverseS = reverseBuilder.reverse().toString();

        int longestPrefixLen = getLongestPrefixSub(s, reverseS);


        return s.length() - longestPrefixLen;
    }

    private int getLongestPrefixSub(String a, String r) {


        // Lengths of strings A and B
        int n = a.length();
        int m = r.length();

        // Create a DP array
        int[][] dp = new int[n + 1][m + 1];
        int longestPrefixLength = 0;

        // Fill DP array
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (a.charAt(i - 1) == r.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    longestPrefixLength = Math.max(longestPrefixLength, dp[i][j]);
                } else {
                    dp[i][j] = 0; // Reset if characters don't match
                }
            }
        }

        return longestPrefixLength;
    }

    public static void main(String[] args) {
        ReverseBinaryOp sol = new ReverseBinaryOp();

        int minimumOps = sol.findMinimumOps("10");
        // 11000
        // 00111


    }
}
