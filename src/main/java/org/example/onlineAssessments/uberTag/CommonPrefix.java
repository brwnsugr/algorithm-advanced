package org.example.onlineAssessments.uberTag;

import java.util.HashMap;
import java.util.Map;

public class CommonPrefix {

    /**
     * Question 4:
     * Given a list of strings - String[] input, find how many pairs of strings have a prefix in common. input[i] and input[j] is a pair if they have a prefix in common and i < j.
     *
     * eg: s1 = ["leet", "codechef"], s2= ["leetcode","code","chef"]; ans = 2 ( pair 1 - "leet", "leetcode" and pair 2 - "codechef", "code")
     *
     */

    public static void main(String[] args) {
        String[] s1 = new String[]{"leet", "codechef"};
        String[] s2 = new String[]{"leetcode","code","chef"};
        CommonPrefix commonPrefix = new CommonPrefix();
        int numberOfPrefixPair = commonPrefix.getNumberOfPrefixPair(s1, s2);
        System.out.println(numberOfPrefixPair);
    }

    public int getNumberOfPrefixPair(String[] s1, String[] s2) {
        TrieNode root1 = new TrieNode();
        TrieNode root2 = new TrieNode();

        for(String s : s1) {
            TrieNode curr1 = root1;
            for(char c : s.toCharArray()) {
                if(!curr1.children.containsKey(c)) curr1.children.put(c, new TrieNode());
                curr1 = curr1.children.get(c);
            }
            curr1.word = s;
        }

        for(String s : s2) {
            TrieNode curr2 = root2;
            for(char c : s.toCharArray()) {
                if(!curr2.children.containsKey(c)) curr2.children.put(c, new TrieNode());
                curr2 = curr2.children.get(c);
            }
            curr2.word = s;
        }
        int ans = 0;
        for(String s : s1) {
            if(isPrefix(s, root2)) {
                System.out.println(s);
                ans++;
            }
        }

        for(String s : s2) {
            if(isPrefix(s, root1)) {
                System.out.println(s);
                ans++;
            }
        }

        return ans;
    }

    private boolean isPrefix(String input, TrieNode root) {
        TrieNode curr = root;

        for(char c : input.toCharArray()) {
            if(!curr.children.containsKey(c)) return false;
            else {
                curr = curr.children.get(c);
            }
        }

        return true;
    }


}

class TrieNode {
    Map<Character, TrieNode> children;
    String word;

    public TrieNode(){
        this.children = new HashMap<>();
        this.word = null;
    }
}
