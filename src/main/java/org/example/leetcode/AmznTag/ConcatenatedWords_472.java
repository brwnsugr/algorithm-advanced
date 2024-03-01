package org.example.leetcode.AmznTag;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 472. Concatenated Words
 * https://leetcode.com/problems/concatenated-words/
 *
 * TC: O(N * M^3) where N is the number of words and M is the length of the word.(because substring takes O(M))
 */
public class ConcatenatedWords_472 {

    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        //
        Set<String> dictionary = new HashSet<>();
        for(String word : words) dictionary.add(word);

        List<String> answer = new ArrayList<>();
        for(String word : words) {
            int len = word.length();
            boolean[] dp = new boolean[len + 1];
            dp[0] = true; // for init
            for(int i = 1; i <= len; i++) {
                // if i is last, we have to have at least 2 words.
                //.          j    i
                // word = (xxx)xxxx]xxxxxx
                for(int j = i == len ? 1 : 0; !dp[i] && j < i; j++) {
                    dp[i] = dp[j] && dictionary.contains(word.substring(j, i));
                }
            }
            if(dp[len]) {
                answer.add(word);
            }
        }
        return answer;
    }
}
