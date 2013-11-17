package com.dsalgo.stackandqueue;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * http://dsalgo.openjudge.cn/dsmoochw3/3/
 * -------------------------------------------------
 * 3:stack or queue
 * -------------------------------------------------
 * 总时间限制: 1000ms 内存限制: 65535kB
 *
 * *****描述*****
 * 栈和队列都是常用的线性结构，它们都提供两个操作：
 * Push：加入一个元素。
 * Pop：弹出一个元素。
 * 不同的是，栈是”先进后出”，而队列则是”先进先出”。
 * 给出一个线性结构的进出顺序，判定这个结构是栈还是队列。
 *
 * ***** 输入*****
 * 第一行输入一个整数t，代表有t组测试数据
 * 对于每组测试数据，第一行输入一个整数n，代表操作的次数。
 * 随后输入n行，每行包含两个整数 type val。
 * 当type = 1时，表示该次操作为push操作，val表示进入的数字。当type=2时，表示该次操作为pop操作，val代表出来的数字。
 * 3<=n<=2000
 *
 * *****输出*****
 * 每组测试数据输出一行。
 * 输出改组数据对应的线性结构，”Stack” 或者 “Queue”。
 * 题目保证是栈或者队列的一种。
 *
 * *****样例输入*****
 * 2
 * 6
 * 1 1
 * 1 2
 * 1 3
 * 2 3
 * 2 2
 * 2 1
 * 4
 * 1 1
 * 1 2
 * 2 1
 * 2 2
 *
 * *****样例输出*****
 * Stack
 * Queue
 *
 * -------------------------------------------------
 * Author: Frank Han (trendhanfeng@gmail.com)
 * Date: 13-11-17
 */
public class StackOrQueue {

	/**
	 * 链表的节点
	 */
	public static class Node {
		public int value;
		public Node next;

		public Node(int value) {
			this.value = value;
			this.next = null;
		}
	}

	/**
	 * 用链表实现的队列
	 */
	public static class LinkStack {
		public Node head;
		public Node tail;

		public LinkStack() {
			this.head = null;
			this.tail = null;
		}

		public void enQueue(int value) {
			Node current = new Node(value);
			if (head == null) {
				head = current;
				tail = current;
			} else {
				tail.next = current;
				tail = tail.next;
			}
		}

		public boolean deQueue(int value) {
			if (head != null) {
				Node previous = head;

				if (head.next != null) {
					head = head.next;
					previous.next = null;
				} else {
					head = null;
					tail = null;
				}

				if (previous.value == value) {
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		}
	}

	public static void main (String args[]) throws Exception {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(reader.readLine());

		int currentOperation;
		int currentNumber;
		boolean isQueue;
		StringTokenizer st;
		for (int i=0; i<t; i++) {
			isQueue = true;
			int n = Integer.parseInt(reader.readLine());

			LinkStack stack = new LinkStack();

			for (int j=0; j<n; j++) {
				st = new StringTokenizer(reader.readLine());
				if (isQueue) {
					currentOperation = Integer.parseInt(st.nextToken());
					currentNumber = Integer.parseInt(st.nextToken());
					if (currentOperation == 1) {
						stack.enQueue(currentNumber);
					} else {
						isQueue = stack.deQueue(currentNumber);
					}
				}
			}

			System.out.println(isQueue ? "Queue" : "Stack");
		}
	}

}
