package org.example.onlineAssessments.uberTag;

import java.util.*;

/**
 * Given a list of words and a matrix with characters, find in how many ways can we form the given list of words using the characters from the matrix. you can move left and down in the matrix from a starting point and see if a word can be formed using that sequence. You can only switch your direction once in the sequence.
 *
 * eg:
 * matrix =
 * [w, o, a , k]
 * [r , r, a, m]
 * [e, d, e, r]
 *
 * String[] words = {"word","order","worder"} ; ans = 2
 * "Worder" is not part of the ans because you'd need to change the direction twice in the sequence.
 *
 */
public class WordLadderOneDirection {

    public static void main(String[] args) {
        WordLadderOneDirection wordLadder = new WordLadderOneDirection();
        char[][] board1 = new char[][]{
                {'w','o','a','k'},{'r','r','a','m'},{'e','d','e','r'}
        };

        char[][] board2 = new char[][]{
                {'w','o','a','k'},{'r','r','a','m'},{'e','d','e','r'}
        };
        String[] words = new String[]{"word","order","worder"};
        String[] words2 = new String[]{"rram", "wred","order", "worder"};
        int ans = wordLadder.getMatchingWords(board1, words2);

        System.out.println(ans);
    }


    char[][] _board = null;
    List<String> result = new ArrayList<>();
    private int ROWS;
    private int COLS;
    private int answer;
    private static int[][] DIRECTIONS = new int[][]{
            {0,1},{1,0},{0,-1},{-1,0}
    };

    public int getMatchingWords(char[][] matrix, String[] words) {
        this.ROWS = matrix.length;
        this.COLS = matrix[0].length;
        this.answer = 0;

        TrieNode root = new TrieNode();
        for(String word : words) {
            TrieNode curr = root;
            for(char c : word.toCharArray()) {
                if(curr.children.containsKey(c)) {
                    curr = curr.children.get(c);
                }
                else {
                    TrieNode newNode = new TrieNode();
                    curr.children.put(c, newNode);
                    curr = newNode;
                }
            }
            curr.word = word;
        }

        this._board = matrix;
        // Start Backtracking for each cell as starting point
        for(int row = 0; row < this.ROWS; row++) {
            for(int col = 0; col < this.COLS; col++) {
                if(root.children.containsKey(matrix[row][col])) {
                    backTrack(row, col, root, 0, null);
                }
            }
        }
//        return this.result;

        return answer;
    }

    private void backTrack(int row, int col, TrieNode parent, int directionCount, int[] prevDir) {
        if(directionCount == 2) return;
        char currChar = this._board[row][col];
        TrieNode currNode = parent.children.get(currChar);

        if(currNode.word != null) {
            this.result.add(currNode.word);
            this.answer++;
            currNode.word = null;
        }

        this._board[row][col] = '#';

        for(int[] dir : DIRECTIONS) {
            int nextR = row + dir[0];
            int nextC = col + dir[1];
            if(nextR >= 0 && nextR < this.ROWS
                    && nextC >= 0 && nextC < this.COLS
                    && currNode.children.containsKey(this._board[nextR][nextC])) {
                int[] currDir = dir;
                if(prevDir != currDir)  backTrack(nextR, nextC, currNode, directionCount + 1, currDir);
                else backTrack(nextR, nextC, currNode, directionCount, currDir);
            }
        }
        this._board[row][col] = currChar;
        if(currNode.children.isEmpty()) {
            parent.children.remove(currChar);
        }
    }

    class TrieNode {
        Map<Character, TrieNode> children = new HashMap<>();
        String word = null;
        public TrieNode() {}
    }
}



