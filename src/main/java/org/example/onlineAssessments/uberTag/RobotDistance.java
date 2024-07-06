package org.example.onlineAssessments.uberTag;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class RobotDistance {

    private static int[][] DIRECTIONS = new int[][]{
            {-1,0},{0,-1},{1,0},{0,1}
    };

    private int ROWS;
    private int COLS;

    public int[] solution(char[][] board, int[] dist) {
        ROWS = board.length;
        COLS = board[0].length;
        Map<String, Integer> leftDistMap = new HashMap<>();
        Map<String, Integer> topDistMap = new HashMap<>();
        Map<String, Integer> rightDistMap = new HashMap<>();
        Map<String, Integer> bottomDistMap = new HashMap<>();
        int[] topRowBlocks = new int[COLS];
        Arrays.fill(topRowBlocks, -1);

        for(int r = 0; r < ROWS; r++){
            int leftColBlock = -1;
            for(int c = 0; c < COLS; c++) {
                if(board[r][c] == 'X') {
                    String key = new String(r + "," + c);
                    leftDistMap.put(key, leftColBlock + c);
                    topDistMap.put(key, r - topRowBlocks[c]);
                }
                else if(board[r][c] == 'O') {
                    leftColBlock = c;
                    topRowBlocks[c] = r;
                }
            }
        }
        int[] bottomRowBlocks = new int[COLS];
        for(int r = ROWS - 1; r >= 0; r--) {
            int rightRowBlock = ROWS;
            for(int c = COLS - 1; c >= 0; c--) {
                if(board[r][c]== 'X') {
                    String key = new String(r + "," + c);
                    rightDistMap.put(key, ROWS - r);
                    bottomDistMap.put(key, r - topRowBlocks[c]);
                }
            }
        }

        for(String key : leftDistMap.keySet()) {
            if(leftDistMap.get(key) == dist[0]
            && topDistMap.get(key) == dist[1]
            && rightDistMap.get(key) == dist[2]
            && bottomDistMap.get(key) == dist[3]) {
                String[] split = key.split(",");
                int r = Integer.parseInt(split[0]);
                int c = Integer.parseInt(split[1]);
                return new int[]{r, c};
            }
        }


        return new int[]{1,1};
    }

    public static void main(String[] args) {

        char[][] matrix = {
                {'O','E','E','E','X'},
                {'E','O','X','X','X'},
                {'E','E','E','E','E'},
                {'X','E','O','E','E'},
                {'X','E','X','E','X'}
        };
        int[] query = {2, 2, 4, 1};

        RobotDistance robotDistance = new RobotDistance();
        int[] solution = robotDistance.solution(
                matrix, query
        );

        System.out.println("dd");
    }
}


