package org.example.compression;

public class HuffmanNode {
    public char c;
    public int frequency;

    public HuffmanNode left = null;
    public HuffmanNode right = right = null;

    public HuffmanNode(char c, int frequency){
        this.c = c;
        this.frequency = frequency;
    }
}
