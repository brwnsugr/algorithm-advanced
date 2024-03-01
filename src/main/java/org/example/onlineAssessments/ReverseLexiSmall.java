package org.example.onlineAssessments;

public class ReverseLexiSmall {
    /**
     * Q1: Return the number of ways to reverse any substring of length k such that the resulting string is lexicographically smaller than the original string:
     * Example:
     *
     * s="amazon"
     * k=3
     * Consider all substrings of length k =3. These are the possible ways to perform the given operation:
     *
     * reverse the substring "ama"
     * reverese the substring "maz"
     * reverse the substring "azo"
     * reverse the substring "zon"
     * only one way is possible so the answer is 1
     *
     * ref: https://leetcode.com/company/amazon/discuss/4660709/Amazon-OA-recent-questions-Sharing-for-the-community-less3
     */

    public int numberOfWayReverseLexicographicallySmall(String s, int k) {
        int answer = 0;

        for(int i = 0; i < s.length() - k + 1; i++) {
            int left = i;
            int right = left + k - 1;
            while(left < right) {
                if(s.charAt(right) == s.charAt(left)){}
                else if(s.charAt(right) < s.charAt(left)) {
                    answer++;
                    break;
                }
                else {
                    break;
                }
                left++;
                right--;
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        ReverseLexiSmall sol = new ReverseLexiSmall();
        int i = sol.numberOfWayReverseLexicographicallySmall("amazon", 3);
        System.out.println(i);
    }
}
