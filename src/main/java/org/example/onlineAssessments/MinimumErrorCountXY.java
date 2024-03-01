package org.example.onlineAssessments;

import java.util.Arrays;

/**
 *
 * The inputs are a string, integer x and integer y.
 *
 * string is made up of 0, 1 and !, each ! can be either 0 or 1
 *
 * Every subsequence of 01 in the string can produce error x
 *
 * Every subsequence of 10 in the string can produce error y
 *
 * 0<=len(string)<=50000, 0<=x<=50000, 0<=y<=50000
 *
 * Return the minimum error count modulo 10^9.
 *
 * Example:
 *
 * string=01!!, x=2, y=3, there're 4 cases:
 *
 * 0100 => errorCount is 2 + 3*2 = 8
 *
 * 0101 => errorCount is 2*3+3 = 9
 *
 * 0110 => errorCount is 2*2+2*3=10
 *
 * 0111 => errorCount is 2*3=6
 *
 * so the result is 6
 *
 * Example 2:
 *
 * string=!!!!, x=2, y=5
 *
 * we can replace all ! to 0 or 1, so there will be no 01 or 10 in the string, the result is 0.
 *
 * ref: https://www.reddit.com/r/leetcode/comments/19b2h09/really_hard_problem_in_amazon_oa/
 */
public class MinimumErrorCountXY {

    /**
     *   idea: dp
     * dp[i][j] minimum count of i, j where i is current position from the left. j is # of 1s until i.
     * goal: max of dp[s.length()][j] for all possible j
     *
     * Case1) if ith char is 1 -> dp[i][j] = dp[i-1][j-1] + (i - j) * x
     * ->  (i - j is number of left zeros) we have to find all 0..1(i)   ..0.1(i) all 01 with 1 at i.
     *
     * Case2) if ith char is 0 -> dp[i][j] = dp[i-1][j] + j * y
     * -> j is number of 1 left from i. so we can find all 1..0(i) ..1.0(i) all 10 with 0 at i.
     *
     * Case3) if ith char is ! -> dp[i][j] = Math.min(dp[i-1][j-1] + (i-j) * x, dp[i-1][j] + j * y)
     * minimum above two cases.
     *
     */

    public int getMinimumErrorCount(String s, int x, int y) {
        if(s.length() == 0) return 0;
        int[][] dp = new int[s.length() + 1][s.length() + 1];
        for (int[] row : dp) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        dp[0][0] = 0;

        for(int i = 1; i <= s.length(); i++) {
            char curr = s.charAt(i - 1);
            if(curr == '0' || curr == '!') {
                for(int j = 0; j < i; j++) {
                    if(dp[i - 1][j] < Integer.MAX_VALUE) {
                        dp[i][j] = Math.min(dp[i][j], dp[i-1][j] + j * y);
                    }
                }
            }

            if(curr == '1' || curr == '!') {
                for(int j = 1; j <= i; j++) {
                    if(dp[i-1][j-1] < Integer.MAX_VALUE) {
                        dp[i][j] = Math.min(dp[i][j], dp[i-1][j-1] + (i-j) * x);
                    }
                }
            }
        }

        int ans = Integer.MAX_VALUE;
        for(int j = 0; j <= s.length(); j++) {
            ans = Math.min(ans, dp[s.length()][j]);
        }

        return ans;
    }

    public static void main(String[] args) {
        MinimumErrorCountXY minimumErrorCountXY = new MinimumErrorCountXY();

        String s1 = "01!!";
        int x1 = 2;
        int y1 = 3;
        String s2 = "!!!";
        int x2 = 2;
        int y2 = 5;
        String s3 = "10";
        int minimumErrorCount = minimumErrorCountXY.getMinimumErrorCount(s1, x1, y1);
        int minimumErrorCount2 = minimumErrorCountXY.getMinimumErrorCount(s2, x2, y2);
        System.out.println(minimumErrorCount);
        System.out.println(minimumErrorCount2);
    }
}
