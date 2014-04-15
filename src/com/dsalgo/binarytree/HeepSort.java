package com.dsalgo.binarytree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * http://dsalgo.openjudge.cn/binarytree/1/
 *
 * Author: Frank Han (trendhanfeng@gmail.com)
 * Date: 14-4-16
 */
public class HeepSort {

    public static class Heap {
        public int values[];
        public int index;

        public Heap() {
            values = new int[100000];
            // BUG1: index and array length error
            index = -1;
        }

        public void add(int added) {
            index++;
            int cursor = index;

            // BUG2: (cursor-1)/2==0, int(-0.5)==0
            while (cursor-1 >= 0) {
                if (values[(cursor-1)/2] > added) {
                    values[cursor]  = values[(cursor-1)/2];
                    cursor = (cursor-1)/2;
                } else {
                    break;
                }
            }
            values[cursor] = added;
        }

        public int pop() {
            int return_value = values[0];
            int cursor = 0;
            while (cursor*2+1 <= index) {
                if (cursor*2+1 == index) {
                    if (values[cursor*2+1] < values[index]) {
                        values[cursor] = values[cursor*2+1];
                        cursor = cursor*2+1;
                    }
                    break;
                }
                if (cursor*2+2 <= index) {
                    if (values[index] <= values[cursor*2+1] && values[index] <= values[cursor*2+2]) {
                        break;
                    } else if (values[cursor*2+1] < values[index] && values[cursor*2+1] <= values[cursor*2+2]){
                        values[cursor] = values[cursor*2+1];
                        cursor = cursor*2+1;
                    } else if (values[cursor*2+2] < values[index] && values[cursor*2+1] > values[cursor*2+2]){
                        values[cursor] = values[cursor*2+2];
                        cursor = cursor*2+2;
                    }
                }
            }
            values[cursor] = values[index];
            index--;
            return return_value;
        }
    }

    public static void main (String args[]) throws Exception {
        System.out.println((int)-0.5);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int round = Integer.parseInt(reader.readLine());

        for (int i = 0; i < round; i++) {
            int operation_count = Integer.parseInt(reader.readLine());
            Heap heap = new Heap();

            for (int j = 0; j < operation_count; j++) {
                StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
                if ("1".equals(tokenizer.nextToken())) {
                    int number = Integer.parseInt(tokenizer.nextToken());
                    heap.add(number);
                } else {
                    System.out.println(heap.pop());
                }
            }
        }
    }
}
