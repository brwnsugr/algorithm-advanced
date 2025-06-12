package org.example.compression;

/**
 * ref: https://leetcode.com/discuss/interview-question/5798402/Amazon-OA-questions
 *
 * An ml model has a certain array of neurons,
 * in each odd generation a neuron can be increased by 1,
 * in even generations it can be increased by 2.
 * Return min generations to make all neurons equal.
 *
 * Eg: {1,1,2,4}
 * → generation1 {2,1,2,4}
 * → generation2 {4,1,2,4}
 * → generation3 {4,2,2,4}
 * → generation4 {4,4,2,4}
 * → generation5 (no change)
 * → generation6 {4,4,4,4}
 */
public class MinGeneration {

    public int getMinGeneration(int[] neurons) {
        int maxNeuron = getMaxNeuron(neurons);

        int need1 = 0;
        int need2 = 0;

        for(int n : neurons) {
            int diff = maxNeuron - n;
            need1 += diff % 2;
            need2 += diff / 2;
        }

        int pairs = Math.min(need1, need2);
        int minGens = 0;
        need1 -= pairs;
        need2 -= pairs;
        minGens += 2 * pairs;

        if(need2 > 0) {
            int remains = need2 * 2;
            minGens += (remains / 3) + remains % 3;
        }
        else if(need1 > 0) {
            minGens += 1;
            need1--;
            minGens += (need1 * 2);
        }

        return minGens;
    }

    private int getMaxNeuron(int[] neurons) {
        int maxNeuron = 0;
        for(int n : neurons) {
            maxNeuron = Math.max(maxNeuron, n);
        }
        return maxNeuron;
    }

    public static void main(String[] args) {
        MinGeneration minGen = new MinGeneration();

        int maxNeuron = minGen.getMinGeneration(new int[]{1, 1, 2, 4});

        System.out.println(maxNeuron);
    }


}
