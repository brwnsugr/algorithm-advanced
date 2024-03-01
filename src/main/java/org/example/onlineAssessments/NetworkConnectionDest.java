package org.example.onlineAssessments;

import java.util.Arrays;

/**
 * An AWS client has brought servers and databases from data centers in different parts of the world for their application.
 * For simplicity, let's assume all the servers and data centers are located on a 1-dimensional line.
 *
 * You have been given the task of optimizing the network connection.
 * Each data center must be connected to a server.
 * The positions of n data centers and n servers are given in the form of arrays.
 * Any particular data center, center[i], can deliver to any particular server destination, destination[j].
 * The lag is defined distance between a data center at location x and a server destination at location y is |x - y|,
 * i.e., the absolute difference between x and y. Determine the minimum lag to establish the entire network.
 *
 * EXAMPLE
 * There are n = 3 connections, the positions of data centers, center = [1, 2, 2], and the positions of the server destinations, destination = [5, 2, 4].
 *
 * The most efficient deliveries are:
 *
 * The center at location 1 makes the first connection to the server at location 2.
 * The center at location 2 makes the second connection to the server at location 4.
 * The center at location 2 makes the third connection to the server at location 5.
 *
 * ref: https://csoahelp.com/2024/02/05/amazon-oline-accessment-record/
 */
public class NetworkConnectionDest {

    public int getMinimumTotalLags(int n, int[] centers, int[] destinations) {
        // c = [1,2,100], d = [1,100,101]
        // 100 + 100+ 3
        //  1 + 101 + 101
        Arrays.sort(centers);
        Arrays.sort(destinations);

        int answer = 0;

        for(int i = 0; i < n; i++) {
            int dist = Math.abs(centers[i] - destinations[i]);
            answer += dist;
        }

        return answer;
    }

    public static void main(String[] args) {
        int[] centers = new int[]{1,2,2};
        int[] destinations = new int[]{5,2,4};

        NetworkConnectionDest networkConnectionDest = new NetworkConnectionDest();
        int minimumTotalLags = networkConnectionDest.getMinimumTotalLags(3, centers, destinations);

        System.out.println(minimumTotalLags);

    }


}
