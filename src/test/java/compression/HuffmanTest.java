package compression;

import org.example.compression.Huffman;
import org.example.compression.HuffmanNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.PriorityQueue;

public class HuffmanTest {

    Huffman h;

    @BeforeEach
    public void init(){
        h = new Huffman();
    }

    @Test
    public void createFrequencyTableTest(){
        int[] freq = h.createFrequencyTable("aabbbcd".toCharArray());
        Assertions.assertEquals(h.CHARACTER_LIMIT, 256);
        Assertions.assertEquals(2, freq[97]);
        Assertions.assertEquals(3, freq[98]);
        Assertions.assertEquals(1, freq[99]);
        Assertions.assertEquals(1, freq[100]);
    }

    @Test
    public void createPriorityQueueTest(){
        int[] freq = h.createFrequencyTable("aabbbcd".toCharArray());
        PriorityQueue<HuffmanNode> pq = h.createPriorityQueue(freq);

        // c = 1 d = 1 a = 2 b = 3
        Assertions.assertEquals('c', pq.peek().c);
        Assertions.assertEquals(1, pq.poll().frequency);

        Assertions.assertEquals('d', pq.peek().c);
        Assertions.assertEquals(1, pq.poll().frequency);

        Assertions.assertEquals('a', pq.peek().c);
        Assertions.assertEquals(2, pq.poll().frequency);

        Assertions.assertEquals('b', pq.peek().c);
        Assertions.assertEquals(3, pq.poll().frequency);

        Assertions.assertEquals(0, pq.size());

        Assertions.assertEquals("\u0001:a2:b3:c1:d1\u0002", h.header.toString());
    }

    @Test
    public void pullLeastUsedAsNodeTest() {
        int[] freq = h.createFrequencyTable("aabbbcd".toCharArray());
        PriorityQueue<HuffmanNode> pq = h.createPriorityQueue(freq);

        HuffmanNode root = h.pullLeastUsedAsNode(pq);

        Assertions.assertEquals('-', root.c);
        Assertions.assertEquals(2, root.frequency);

        Assertions.assertEquals('c', root.left.c);
        Assertions.assertEquals(1, root.left.frequency);
        Assertions.assertNull(root.left.left);
        Assertions.assertNull(root.left.right);

        Assertions.assertEquals('d', root.right.c);
        Assertions.assertEquals(1, root.right.frequency);
        Assertions.assertNull(root.right.left);
        Assertions.assertNull(root.right.right);
    }

    @Test
    public void createHuffmanTreeTest(){

    }

    @Test
    public void compressTest(){
//        System.out.println(h.compress("aabbcd".toCharArray()));
        String compress = h.compress("aabbcd".toCharArray());
        System.out.println(compress);
        char[] decompress = h.decompress(compress.toCharArray());
        System.out.println(decompress);
    }

}
