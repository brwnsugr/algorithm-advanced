package org.example.leetcode.greedy;

import java.util.PriorityQueue;

/**
 * LeetCode #767. Reorganize String
 * leetcode.com/problems/reorganize-string/
 *
 * TC: O(NlogA) - A is the size of alphabet
 */

public class ReorganizeString {
    public String reorganizeString(String s) {
        int[] charCounts = new int[26];
        for(char c : s.toCharArray()) charCounts[c - 'a']++;
        //set max heap by char counts
        PriorityQueue<CharPair> pq = new PriorityQueue<CharPair>(
                (a, b) -> b.count - a.count
        );

        for(int i = 0; i < 26; i++) {
            if(charCounts[i] > 0) pq.add(new CharPair((char)(i + 'a'), charCounts[i]));
        }

        StringBuilder strBuilder = new StringBuilder();


        while(!pq.isEmpty()) {
            CharPair curr = pq.poll();
            if(strBuilder.length() == 0 || curr.c != strBuilder.charAt(strBuilder.length() - 1)) {
                strBuilder.append(curr.c);
                curr.count--;
                if(curr.count > 0) pq.add(curr);

            } else {
                if(pq.isEmpty()) return "";

                CharPair second = pq.poll();
                strBuilder.append(second.c);
                second.count--;
                if(second.count > 0) pq.add(second);
                pq.add(curr);
            }
        }
        return strBuilder.toString();
    }

    class CharPair {
        int count;
        char c;

        public CharPair(char c, int count) {
            this.count = count;
            this.c = c;
        }
    }

    /**
     * Approach 2: Greedy
     * TC: O(N)
     */
    public String reorganizeString2(String s) {
        int[] counts = new int[26];
        for(char c : s.toCharArray()) {
            counts[c - 'a']++;
        }

        int maxCount = 0;
        int letter = 0;

        for(int i = 0; i < counts.length; i++) {
            if(counts[i] > maxCount) {
                maxCount = counts[i];
                letter = i;
            }
        }

        if(maxCount > (s.length() + 1)/2) return "";

        char[] chars = new char[s.length()];

        int index = 0;

        // put the most freq letter in the chars array first
        while(counts[letter] > 0) {
            chars[index] = (char) (letter + 'a');
            index += 2;
            counts[letter]--;
        }

        // put the rest of letters
        for(int i = 0; i < counts.length; i++) {
            while(counts[i] > 0) {
                if(index >= s.length()) {
                    index = 1;
                }
                chars[index] = (char) (i + 'a');
                index += 2;
                counts[i]--;
            }
        }

        return String.valueOf(chars);
    }


}
