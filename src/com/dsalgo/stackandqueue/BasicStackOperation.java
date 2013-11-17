package com.dsalgo.stackandqueue;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * http://dsalgo.openjudge.cn/dsmoochw3/2/
 * -------------------------------------------------
 * 2:栈的基本操作
 * -------------------------------------------------
 * 总时间限制: 1000ms 内存限制: 1000kB
 *
 * *****描述*****
 * 栈是一种重要的数据结构，它具有push k和pop操作。push k是将数字k加入到栈中，pop则是从栈中取一个数出来。
 * 栈是后进先出的：把栈也看成横向的一个通道，则push k是将k放到栈的最右边，而pop也是从栈的最右边取出一个数。
 *
 * 假设栈当前从左至右含有1和2两个数，则执行push 5和pop操作示例图如下：
 * push 5          pop
 * 栈   1 2  ------->  1 2 5 ------>  1 2
 *
 * 现在，假设栈是空的。给定一系列push k和pop操作之后，输出栈中存储的数字。若栈已经空了，仍然接收到pop操作，则输出error。
 *
 * ***** 输入*****
 * 第一行为m，表示有m组测试输入，m<100。
 * 每组第一行为n，表示下列有n行push k或pop操作。（n<150）
 * 接下来n行，每行是push k或者pop，其中k是一个整数。
 * （输入保证同时在栈中的数不会超过100个）
 *
 * *****输出*****
 * 对每组测试数据输出一行。
 * 该行内容在正常情况下，是栈中从左到右存储的数字，数字直接以一个空格分隔，如果栈空，则不作输出；
 * 但若操作过程中出现栈已空仍然收到pop，则输出error。
 *
 * *****样例输入*****
 * 2
 * 4
 * push 1
 * push 3
 * pop
 * push 5
 * 1
 * pop
 *
 * *****样例输出*****
 * 1 5
 * error
 *
 * -------------------------------------------------
 * Author: Frank Han (trendhanfeng@gmail.com)
 * Date: 13-11-17
 */
public class BasicStackOperation {

	public static class MyStack {
		private int[] elements;
		private int length;

		public MyStack() {
			elements = new int[200];
			length = 0;
		}

		public int size() {
			return length;
		}

		public boolean push(int number) {
			if (length < 200) {
				elements[length++] = number;
				return true;
			} else {
				return false;
			}
		}

		public boolean pop() {
			if (length > 0) {
			    length --;
				return true;
			} else {
				return false;
			}
		}

		@Override
		public String toString() {
			String strReturn = "";
			for (int i=0; i<length; i++) {
				strReturn += elements[i] + " ";
			}
			return strReturn;
		}
	}

	public static void main (String args[]) throws Exception {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int m = Integer.parseInt(reader.readLine());

		String currentOperation;
		int currentNumber;
		boolean isSuccess;
		for (int i=0; i<m; i++) {
			isSuccess = true;
			int n = Integer.parseInt(reader.readLine());

			MyStack stack = new MyStack();

			for (int j=0; j<n; j++) {
				StringTokenizer st = new StringTokenizer(reader.readLine());
				if (isSuccess) {
					currentOperation = st.nextToken();
					if (currentOperation.equals("push")) {
						currentNumber = Integer.parseInt(st.nextToken());
						stack.push(currentNumber);
					} else {
						isSuccess = stack.pop();	//不能break，命令要读完
					}
				}
			}

			if (isSuccess) {
				if (stack.size() > 0)
				System.out.println(stack.toString());
			} else {
				System.out.println("error");
			}
		}
	}

}
