package com.dsalgo.binarytree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * http://dsalgo.openjudge.cn/dsmoochw5/1/
 *
 * Author: Frank Han (trendhanfeng@gmail.com)
 * Date: 14-4-13
 */

public class Operation {

    public static class Node {
        public int value;
        public Node leftChild;
        public Node rightChild;
        public Node parent;

        public Node(int value, Node parent) {
            this.value = value;
            this.parent = parent;
            this.leftChild = null;
            this.rightChild = null;
        }
    }

    public static class Tree {
        public Node root;

        public Tree() {
            root = new Node(0, null);
        }

        private Node getNodeByValue(int value) {
            return this.getNodeByValue(root, value);
        }

        private Node getNodeByValue(Node currentNode, int value) {
            if (currentNode.value == value) {
                return currentNode;
            }
            if (currentNode.leftChild != null) {
                Node leftResult = this.getNodeByValue(currentNode.leftChild, value);
                if (leftResult != null) {
                    return leftResult;
                }
            }
            if (currentNode.rightChild != null) {
                Node rightResult = this.getNodeByValue(currentNode.rightChild, value);
                if (rightResult != null) {
                    return rightResult;
                }
            }
            return null;
        }

        public void addNode(int value, int leftValue, int rightValue) {
            if (leftValue == -1 && rightValue == -1) {
                return;
            }

            Node node = this.getNodeByValue(value);
            if (node == null) {
                System.out.println("Error, no node with value:" + value);
                return;
            }

            if (leftValue != -1) {
                node.leftChild = new Node(leftValue, node);
            }
            if (rightValue != -1) {
                node.rightChild = new Node(rightValue, node);
            }
        }

        public void switchNode(int value1, int value2) {
            if (value1  == value2) {
                return;
            }

            Node node1 = this.getNodeByValue(value1);
            Node node2 = this.getNodeByValue(value2);

            Node parent1 = node1.parent;
            Node parent2 = node2.parent;

            boolean isNode1LeftChild = node1 == parent1.leftChild;
            boolean isNode2LeftChild = node2 == parent2.leftChild;

            if (isNode1LeftChild) {
                parent1.leftChild = node2;
            } else {
                parent1.rightChild = node2;
            }
            // BUG3: forget to change the parent
            node2.parent = parent1;

            if (isNode2LeftChild) {
                parent2.leftChild = node1;
            } else {
                parent2.rightChild = node1;
            }
            node1.parent = parent2;
        }

        public int getLeftValue(int currentValue) {
            Node currentNode = this.getNodeByValue(currentValue);
            return this.getLeftValue(currentNode);
        }

        private int getLeftValue(Node currentNode) {
            while (currentNode.leftChild != null) {
                currentNode = currentNode.leftChild;
            }
            return currentNode.value;
        }
    }


    public static void main (String args[]) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int round = Integer.parseInt(reader.readLine());

        for (int i = 0; i < round; i++) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            int nodeCount = Integer.parseInt(tokenizer.nextToken());
            int operationCount = Integer.parseInt(tokenizer.nextToken());

            Tree tree = new Tree();
            int[][] input = new int[nodeCount][2];
            // BUG2: omit the situation the input value are not in order
            for (int j = 0; j < nodeCount; j++) {
                String line = reader.readLine();
                tokenizer = new StringTokenizer(line);
                int value = Integer.parseInt(tokenizer.nextToken());
                input[value][0] = Integer.parseInt(tokenizer.nextToken());
                input[value][1] = Integer.parseInt(tokenizer.nextToken());
            }
            for (int j = 0; j < nodeCount; j++) {
                tree.addNode(j, input[j][0], input[j][1]);
            }


            for (int j = 0; j < operationCount; j++) {
                String line = reader.readLine();
                tokenizer = new StringTokenizer(line);

                if ("1".equals(tokenizer.nextToken())) {
                    tree.switchNode(Integer.parseInt(tokenizer.nextToken()),
                            Integer.parseInt(tokenizer.nextToken()));
                } else {
                    // BUG1: "2".equals(tokenizer.nextToken()) retrieve an extra token
                    System.out.println(tree.getLeftValue(Integer.parseInt(tokenizer.nextToken())));
                }
            }
        }
    }
}
