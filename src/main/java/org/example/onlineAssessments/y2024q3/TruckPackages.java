package org.example.onlineAssessments.y2024q3;

import java.util.Arrays;

public class TruckPackages {

    public int[] getDistributedTruck(int[] trucks, int distribute) {
        int maxTruck = Arrays.stream(trucks).max().getAsInt();
        int n = trucks.length;

        // mark up to current Max
        for(int i = 0; i < n; i++) {
            if(maxTruck == trucks[i]) continue;
            int diff = maxTruck - trucks[i];

            if(distribute > diff) {
                trucks[i] += diff;
                distribute -= diff;
            }
            else {
                trucks[i] += distribute;
                distribute = 0;
            }
        }

        // if remaining dist exists.
        if(distribute > 0) {
            int r = distribute % n;
            int even = distribute / n;

            for(int i = 0; i < n; i++) {
                if(r > 0) {
                    trucks[i] += (even + 1);
                    r--;
                }
                else {
                    trucks[i] += even;
                }
            }
        }

        return trucks;
    }

    public static void main(String[] args) {
        TruckPackages tp = new TruckPackages();
        int[] distributedTruck = tp.getDistributedTruck(new int[]{2, 3, 4, 5, 6}, 10);
    }
}
