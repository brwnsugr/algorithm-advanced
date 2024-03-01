package org.example.onlineAssessments;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 *The string user_history = "ceccca" and k = 3
 * The only product that appears 3 times or more in the original string is 'c'.
 * The substrings where the product 'c' appears 3 or more times are
 * ---- case1
 * user_history(string): ceccca, k = 3
 *
 * possible substring -> answer = 7
 * cecc -> count: 3
 * ceccc -> count: 4
 * ceccca -> count: 4
 * eccc -> count: 3
 * eccca -> count: 3
 * ccc -> count: 3
 * ccca -> count: 3
 *
 * So, the value of the k-repetitiveness feature is 7
 *
 * ---- case2
 * user_history(string): acaab, k = 3
 *
 * possible substring -> answer = 2
 * acaa
 * acaab
 *
 * ---- case3
 * user_history(string): accad, k = 2
 *
 * possible substring -> answer = 6
 * acc
 * acca
 * accad
 * cc
 * cca
 * ccad
 *
 * constrains
 * 1 <= length of user_history <= 5 * 10^4
 * 1 <= k <= 5*10^4
 * The string user_history only contains lowercase latin letters, ascii(a-z)
 *
 * ref: https://leetcode.com/discuss/interview-question/4664752/Amazon-OA
 */
public class KRepeatFeatures {

    public int getKRepValFromBack(String userHistory, int k) {
        int n = userHistory.length();
        int earliestIndexOfK = n;

        int answer = 0;
        Deque<Integer>[] charDeque = new Deque[26];
        for(int i = 0; i < charDeque.length; i++) {
            charDeque[i] = new ArrayDeque<>();
        }

        for(int i = n - 1; i >= 0; i--) {
            char c = userHistory.charAt(i);
            charDeque[c - 'a'].addFirst(i);
            if(charDeque[c-'a'].size() == k) {
                int currCharLastIdx = charDeque[c-'a'].pollLast();
                earliestIndexOfK = Math.min(currCharLastIdx, earliestIndexOfK);
            }
            answer += n - earliestIndexOfK;
        }

        return answer;
    }

    public static void main(String[] args) {
        KRepeatFeatures sol = new KRepeatFeatures();
        int answer = sol.getKRepValFromBack("ceccca", 3);
        System.out.println(answer);
    }
}


