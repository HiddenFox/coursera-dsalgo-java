package com.dsalgo.binarytree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * http://dsalgo.openjudge.cn/binarytree/8/
 *
 * Author: Frank Han (trendhanfeng@gmail.com)
 * Date: 14-4-13
 */
public class TreeRecovery {

    public static class Node {
        public char value;
        public Node left;
        public Node right;

        public Node(char value, Node left, Node right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

    public static class Tree {
        public Node root;

        public void build(String preorder, String inorder) {
            if (preorder.length() != inorder.length()) {
                return;
            }
            if (preorder.length() == 0) {
                return;
            }
            root = buildSubTree(preorder, inorder);
        }

        private Node buildSubTree(String preorder, String inorder) {
            if (preorder.length() != inorder.length()) {
                return null;
            }
            if (preorder.length() == 0) {
                return null;
            }
            char currentValue = preorder.charAt(0);

            int currentIndexInorder = inorder.indexOf(currentValue);

            Node leftChild = null;
            if (currentIndexInorder > 0) {
                String leftInorder = inorder.substring(0, currentIndexInorder);
                String leftPreorder = preorder.substring(1, currentIndexInorder+1);
                leftChild = buildSubTree(leftPreorder, leftInorder);
            }

            Node rightChild = null;
            if (currentIndexInorder < preorder.length()-1) {
                String rightInorder = inorder.substring(currentIndexInorder+1);
                String rightPreorder = preorder.substring(currentIndexInorder+1);
                // BUG1: copy from leftxxx section and forget to delete some params
                rightChild = buildSubTree(rightPreorder, rightInorder);
            }

            return new Node(currentValue, leftChild, rightChild);
        }

        public String postorder() {
            return postorder(root);
        }

        public String postorder(Node currentNode) {
            String returnString = "";
            if (currentNode == null) {
                return returnString;
            }
            if (currentNode.left != null) {
                returnString += this.postorder(currentNode.left);
            }
            if (currentNode.right != null) {
                returnString += this.postorder(currentNode.right);
            }
            returnString += currentNode.value;
            return returnString;
        }

    }

    public static void main (String args[]) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            String line = reader.readLine();
            if (line == null || line.length() == 0) {
                break;
            }
            StringTokenizer tokenizer = new StringTokenizer(line);
            Tree tree = new Tree();
            tree.build(tokenizer.nextToken(), tokenizer.nextToken());
            System.out.println(tree.postorder());
        }
    }
}
