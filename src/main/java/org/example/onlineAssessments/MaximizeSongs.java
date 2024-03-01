package org.example.onlineAssessments;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * You are given a number of songs in an album array, album size array and an genre array.
 *
 * No. of songs : {8,4,5,6,7,9}
 * AlbumSize(in KBs): {100,150,55,75,71,15}
 * Genre: {"pop","jazz", "country" , "Latin", "Electro" , "jazz"}
 * Maximum Capacity: 300
 *
 * Your goal is to maximize the total number of songs within the maximum capacity such that there is no repeated genre
 * (You can select only one album from one particular genre).
 *
 * ref: https://leetcode.com/discuss/interview-question/4326333/Amazon-OA
 */
public class MaximizeSongs {

    public static void main(String[] args) {
        int[] songs1 = new int[]{8,4,5,6,7,9};
        int[] albumSize1 = new int[]{100,150,55,75,71,15};
        String[] genres1 = new String[]{"pop", "jazz", "country", "Latin", "Electro", "jazz"};
        int maxCapacity1 = 320;

        MaximizeSongs maximizeSongs = new MaximizeSongs();
        int maximumSongs = maximizeSongs.getMaximumSongs(songs1, albumSize1, genres1, maxCapacity1);
        System.out.println(maximumSongs);
    }

    public int getMaximumSongs(int[] noOfSongs, int[] albumSizes, String[] genres, int maxCapacity) {
        // dp[n][k] -> for n genres, and current capacity k -> maximum Songs
        Map<String, List<Album>> genreAlbumMap = new HashMap<>();



        for(int i = 0; i < noOfSongs.length; i++) {
            if(!genreAlbumMap.containsKey(genres[i])) genreAlbumMap.put(genres[i], new ArrayList<>());
            genreAlbumMap.get(genres[i]).add(new Album(noOfSongs[i], albumSizes[i]));
        }

        List<List<Album>> albumsList = new ArrayList<>();
        for(List<Album> albums : genreAlbumMap.values()) {
            albumsList.add(albums);
        }

        int n = albumsList.size();

        int[][] dp = new int[n + 1][maxCapacity + 1]; // dp(i,j) = i albums, and currentCapacity -> max songs

        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= maxCapacity; j++) {
                List<Album> albums = albumsList.get(i - 1);
                dp[i][j] = dp[i-1][j]; // transition as long as j is same, we can make transition
                for(int k = 0; k < albums.size(); k++) {
                    Album currAlbum = albums.get(k);
                    if(j >= currAlbum.size) {
                        dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - currAlbum.size] + currAlbum.songs);
                    }
                }
            }
        }

        return dp[n][maxCapacity];
    }

    class Album {
        int songs;
        int size;

        public Album(int songs, int size) {
            this.songs = songs;
            this.size = size;
        }
    }
}
