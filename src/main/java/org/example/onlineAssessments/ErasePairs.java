package org.example.onlineAssessments;

import java.util.*;

/**
 * You are given a string S. In one move you can erase from S a pair of identical letters. Find the shortest possible string that can be created this way. If there are many such strings, choose the alphabetically (lexicographically) smallest one. Note that there is no limit to the number of moves.
 *
 * Write a function:
 *
 * class Solution { public String solution(String S); }
 *
 * that, given a string S of length N, returns the shortest string (or the first alphabetically, in the case of a draw) created by erasing pairs of identical letters from S.
 *
 * Examples:
 *
 * For S = "CBCAAXA" you can make, for example, two moves:
 * first erase a pair of letters "C": "CBCAAXA" ➝ "BAAXA";
 * then erase a pair of letters "A": "BAAXA" ➝ "BAX".
 * Thus the string "BAX" is created. There is no way to create a shorter string. The other string of length 3 that can be created is "BXA", but "BAX" is the first alphabetically. The function should return "BAX".
 *
 * For S = "ZYXZYZY", two moves can be made:
 * first erase a pair of letters "Y": "ZYXZYZY" ➝ "ZXZYZ";
 * then erase a pair of letters "Z": "ZXZYZ" ➝ "XYZ".
 * The other strings of length 3 that can be created are "ZYX", "YXZ", "XZY" and "ZXY", but "XYZ" is alphabetically the first, so the function should return "XYZ".
 *
 * For S = "ABCBACDDAA" all five pairs of identical letters can be erased. The function should return "" (empty string).
 *
 * For S = "AKFKFMOGKFB" the function should return "AFKMOGB".
 *
 * ref: https://leetcode.com/company/amazon/discuss/4370863/Amazon-or-OA-or-Erase-Pairs
 */
public class ErasePairs {

    public String getRemovedPairsString(String s) {
        Stack<Character> st = new Stack<>();

        Map<Character, Integer> countMap = new HashMap<>();
        Map<Character, Integer> lastPosMap = new HashMap<>();
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            countMap.put(c, countMap.getOrDefault(c, 0) + 1);
            lastPosMap.put(c, i);
        }

        Set<Character> seen = new HashSet<>();

        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(seen.contains(c)) continue;

            if(countMap.get(c) % 2 == 1) {
                while(!st.isEmpty() && st.peek() > c && lastPosMap.get(st.peek()) > i) {
                    char removedCh = st.pop();
                    seen.remove(removedCh);
                }
                st.add(c);
                seen.add(c);
            }
        }


        StringBuilder strBuilder = new StringBuilder();
        while(!st.isEmpty()) strBuilder.append(st.pop());
        return strBuilder.reverse().toString();
    }

    public static void main(String[] args) {
        ErasePairs sol = new ErasePairs();
        String ans = sol.getRemovedPairsString("CBCAAXA");
        System.out.println(ans);
    }
}
