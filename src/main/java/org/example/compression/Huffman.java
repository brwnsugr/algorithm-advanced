package org.example.compression;

import java.util.PriorityQueue;

public class Huffman {

    public final int CHARACTER_LIMIT = 256;
    public StringBuilder header = new StringBuilder();

    public int[] createFrequencyTable(char[] text){
        int[] frequencies = new int[CHARACTER_LIMIT];
        for(int i = 0; i < text.length; i++) {
            frequencies[text[i]] = frequencies[text[i]] + 1;
        }
        return frequencies;
    }

    // Create PriorityQueue
    public PriorityQueue<HuffmanNode> createPriorityQueue(int[] frequencies){
        header = new StringBuilder();
        header.append((char) 1);
        PriorityQueue<HuffmanNode> pq = new PriorityQueue<HuffmanNode>(1, new FrequencyComparator());
        for(int i = 0; i < frequencies.length; i++) {
            if(frequencies[i] > 0) {
                pq.add(new HuffmanNode((char) i, frequencies[i]));
                header.append(":").append((char) i).append(frequencies[i]);
            }
        }
        header.append((char) 2);
        return pq;
    }

    // Make huffman Tree
    public HuffmanNode createHuffmanTree(PriorityQueue<HuffmanNode> pq) {
        HuffmanNode root = null;
        while(pq.size() > 0) {
            root = pullLeastUsedAsNode(pq);
            if(pq.size() > 0) pq.add(root);
        }
        return root;
    }

    public String compress(char[] text) {
        int[] frequencies = createFrequencyTable(text);
        PriorityQueue<HuffmanNode> pq = createPriorityQueue(frequencies);
        HuffmanNode root = createHuffmanTree(pq);
        String compressedStr = header.toString() + encodeString(text, root);
        return compressedStr;
    }

    public char[] decompress(char[] encodedText) {
        if (encodedText[0] != (char) 1) return null;
        int[] frequencies = parseHeaderAsFrequency(encodedText);
        PriorityQueue<HuffmanNode> queue = createPriorityQueue(frequencies);
        HuffmanNode root = createHuffmanTree(queue);
        String decompressed = decodeString(encodedText, root);
        return decompressed.toCharArray();
    }

    public String encodeString(char[] text, HuffmanNode root) {
        StringBuilder strBuilder = new StringBuilder();
        String array[] = new String[CHARACTER_LIMIT];
        generateBites(array, root, strBuilder);
        for(int i = 0; i < text.length; i++) {
            strBuilder.append(array[text[i]]);
        }
        return strBuilder.toString();
    }

    private String decodeString(char[] text, HuffmanNode root) {
        StringBuilder s = new StringBuilder();
        HuffmanNode currentNode = root;
        for (int i=header.length(); i<text.length; i++) {
            if (text[i] - '0' == 0) currentNode = currentNode.left;
            else if (text[i] - '0' == 1) currentNode = currentNode.right;
            if (isLeaf(currentNode)) {
                s.append(currentNode.c);
                currentNode = root;
            }
        }
        return s.toString();
    }


    private boolean isLeaf(HuffmanNode node) {
        if(node == null) return false;
        return node.left == null && node.right == null;
    }


    public void generateBites(String[] array, HuffmanNode root, StringBuilder strBuilder) {
        if(root.c == '-') {
            strBuilder.append("0");
            generateBites(array, root.left, strBuilder);
            strBuilder.append("1");
            generateBites(array, root.right, strBuilder);
            if(strBuilder.length() > 0) strBuilder.deleteCharAt(strBuilder.length() - 1);
        } else {
            System.out.println(root.c + " - " + strBuilder.toString());
            array[root.c] = strBuilder.toString();
            strBuilder.deleteCharAt(strBuilder.length() - 1);
        }
    }



    // Pull the two least used from the queue
    public HuffmanNode pullLeastUsedAsNode(PriorityQueue<HuffmanNode> pq) {
        HuffmanNode node1 = pq.poll();
        HuffmanNode node2 = pq.poll();
        HuffmanNode root = new HuffmanNode('-', node1.frequency + node2.frequency);
        root.left = node1;
        root.right = node2;
        return root;
    }

    /**
     * /u0001:a2:b3:c1:d1/u00021010000110111
     * -> parse the header part (a2:b3:c1:d1) by its frequency
     * @param text
     * @return
     */

    public int[] parseHeaderAsFrequency(char[] text) {
        int[] frequencies = new int[CHARACTER_LIMIT];
        int i=0;
        for(; i<text.length && text[i] != (char) 2; i++) {
            header.append(text[i]);
            if (text[i] == ':') {
                i++;
                header.append(text[i]);
                int f=0;
                int m=1;
                int j = i +1;
                for(; j<text.length && text[j] != (char) 2 && text[j] != ':'; j++) {
                    f = (f * m) + (text[j] - '0');
                    if (f != 0) m = 10;
                    header.append(text[i] - '0');
                }
                frequencies[text[i]] = f;
                i = j -1;
            }
        }
        return frequencies;
    }


}
