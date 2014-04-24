package com.dsalgo.binarytree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * http://dsalgo.openjudge.cn/binarytree/2/
 *
 * Author: Frank Han (trendhanfeng@gmail.com)
 * Date: 14-4-34
 */
public class HuffmanCodingTree {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }

        public Node(int value, Node left, Node right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

    public static class HuffmanTree implements Comparable<HuffmanTree>{
        public Node root;
        public int value;

        public HuffmanTree(int value) {
            root = new Node(value);
            this.value = value;
        }

        public HuffmanTree(HuffmanTree tree1, HuffmanTree tree2) {
            root = new Node(0, tree1.root, tree2.root);
            this.value = tree1.value + tree2.value;
        }

        public int getPathLength() {
            return this.getPathLength(0, this.root);
        }

        private int getPathLength(int distance, Node currentNode) {
            if (currentNode.left == null) {
                return distance * currentNode.value;
            } else {
                return this.getPathLength(distance+1, currentNode.left) +
                       this.getPathLength(distance+1, currentNode.right);
            }
        }

        @Override
        // ERROR: compare the sum of value in this tree, but not the path length!
        public int compareTo(HuffmanTree o) {
            if (this.value < o.value) {
                return -1;
            } else if (this.value == o.value) {
                return 0;
            } else {
                return 1;
            }
        }
    }


    public static void buildTree(int[] values) {
        PriorityQueue<HuffmanTree> queue = new PriorityQueue<HuffmanTree>(values.length);
        for (int value : values) {
            queue.add(new HuffmanTree(value));
        }
        while (queue.size()!=1) {

            HuffmanTree tree1 = queue.poll();
            HuffmanTree tree2 = queue.poll();
            queue.add(new HuffmanTree(tree1, tree2));
        }
        System.out.println(queue.poll().getPathLength());
    }

    public static void main (String args[]) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int round = Integer.parseInt(reader.readLine());

        for (int i = 0; i < round; i++) {
            int nodeCount = Integer.parseInt(reader.readLine());
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            int[] leaves = new int[nodeCount];
            for (int j = 0; j < nodeCount; j++)
                leaves[j] = Integer.parseInt(tokenizer.nextToken());
            buildTree(leaves);
        }
    }
}
