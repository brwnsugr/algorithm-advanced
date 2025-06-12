package org.example.onlineAssessments.y2024q3;

public class StringOperation {
    public int minOperations(String A, String B) {
        int common = lcs(A, B);
        return A.length() + B.length() - 2 * common;
    }

    private int lcs(String A, String B) {
        int m = A.length();
        int n = B.length();
        int maxLength = 0;

        for (int i = -(n - 1); i < m; i++) {
            int cnt = 0;
            for (int j = 0; j < n; j++) {
                if ((i + j) < 0) {
                    continue;
                } else if ((i + j) > m - 1) {
                    break;
                } else if (A.charAt(i + j) == B.charAt(j)) {
                    cnt++;
                    maxLength = Math.max(maxLength, cnt);
                } else {
                    cnt = 0;
                }
            }
        }
        return maxLength;
    }

    public static void main(String[] args) {
        StringOperation solution = new StringOperation();
        String A = "abcee";
        String B = "efgee";
        System.out.println(solution.minOperations(A, B)); // Output will be the minimum operations
    }
}
