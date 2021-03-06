package com.dsalgo.linearlist;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * http://dsalgo.openjudge.cn/dsmoochw2/1/
 * -------------------------------------------------
 * 总时间限制: 1000ms 内存限制: 5000kB
 *
 * *****描述*****
 * 我们经常遇到两多项式相加的情况，在这里，我们就需要用程序来模拟实现把两个多项式相加到一起。
 * 首先，我们会有两个多项式，每个多项式是独立的一行，每个多项式由系数、幂数这样的多个整数对来表示。
 * 如多项式2x^20- x^17+ 5x^9- 7x^7+ 16x^5+ 10x^4 + 22x^2- 15
 * 对应的表达式为：2 20 -1 17 5 9 - 7 7 16 5 10 4 22 2 -15 0。
 *
 * 为了标记每行多项式的结束，在表达式后面加上了一个幂数为负数的整数对。
 * 同时输入表达式的幂数大小顺序是随机的。
 * 我们需要做的就是把所给的两个多项式加起来。
 *
 * *****输入*****
 * 输入包括多行。
 * 第一行整数n,表示有多少组的多项式需要求和。(1 < n < 100)
 * 下面为2n行整数，每一行都是一个多项式的表达式。表示n组需要相加的多项式。
 * 每行长度小于300。
 *
 * *****输出*****
 * 输出包括n行，每行为1组多项式相加的结果。
 * 在每一行的输出结果中，多项式的每一项用“[x y]”形式的字符串表示，x是该项的系数、y 是该项的幂数。
 * 要求按照每一项的幂从高到低排列，即先输出幂数高的项、再输出幂数低的项。
 * 系数为零的项不要输出。
 *
 * *****样例输入*****
 * 2
 * -1 17 2 20 5 9 -7 7 10 4 22 2 -15 0 16 5 0 -1
 *  2 19 7 7 3 17 4 4 15 10 -10 5 13 2 -7 0 8 -8
 * -1 17 2 23 22 2 6 8 -4 7 -18 0 1 5 21 4 0 -1
 * 12 7 -7 5 3 17 23 4 15 10 -10 5 13 5 2 19 9 -7
 *
 * *****样例输出*****
 * [ 2 20 ] [ 2 19 ] [ 2 17 ] [ 15 10 ] [ 5 9 ] [ 6 5 ] [ 14 4 ] [ 35 2 ] [ -22 0 ]
 * [ 2 23 ] [ 2 19 ] [ 2 17 ] [ 15 10 ] [ 6 8 ] [ 8 7 ] [ -3 5 ] [ 44 4 ] [ 22 2 ] [ -18 0 ]
 *
 * *****提示*****
 * 第一组样例数据的第二行末尾的8 -8，因为幂次-8为负数，所以这一行数据结束，8 -8不要参与计算。
 * -------------------------------------------------
 * Author: Frank Han (trendhanfeng@gmail.com)
 * Date: 13-11-6
 */
public class PolynomialAddition {

	static class Node {
		public int coefficient;
		public int exponent;
		public Node next;

		Node(int coefficient, int exponent){
			this.coefficient = coefficient;
			this.exponent = exponent;
			next = null;
		}
	}

	static class Polynomial {
		public Node head;

		public void addNode (int coefficient, int exponent) {
			if (head == null) {
				head = new Node(coefficient, exponent);
				return;
			}

			if (head.exponent < exponent) {
				Node added = new Node(coefficient, exponent);
				added.next = head;
				head = added;
				return;
			} else if (head.exponent == exponent) {
				head.coefficient += coefficient;
				return;
			}

			Node current = head;
			while (current.next != null) {
				if (current.next.exponent > exponent) {
					current = current.next;
				} else if (current.next.exponent < exponent) {
					Node added = new Node(coefficient, exponent);
					added.next = current.next;
					current.next = added;
					return ;
				} else {
					current.next.coefficient += coefficient;
					return ;
				}
			}
			current.next = new Node(coefficient, exponent);
		}
	}

	public static void main (String args[]) throws Exception {
		BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
		int count = Integer.parseInt(stdin.readLine());

		for (int i = 0; i < count; i++) {
			Polynomial polynomialA = new Polynomial();
			Polynomial polynomialB = new Polynomial();

			StringTokenizer token = new StringTokenizer(stdin.readLine());
			int coefficient, exponent;

			while (token.hasMoreElements()) {
				coefficient = Integer.parseInt(token.nextToken());
				exponent = Integer.parseInt(token.nextToken());
				polynomialA.addNode(coefficient, exponent);
			}

			token = new StringTokenizer(stdin.readLine());
			while (token.hasMoreElements()) {
				coefficient = Integer.parseInt(token.nextToken());
				exponent = Integer.parseInt(token.nextToken());
				polynomialB.addNode(coefficient, exponent);
			}

			Node nodeA = polynomialA.head;
			Node nodeB = polynomialB.head;

			int coefficientA = nodeA.coefficient;
			int exponentA = nodeA.exponent;
			int coefficientB = nodeB.coefficient;
			int exponentB = nodeB.exponent;

			String line = "";
			while (exponentA >=0 || exponentB >= 0) {
				if (exponentA < exponentB) {
					if (coefficientB != 0) {
						line += "[ " + coefficientB + " " + exponentB + " ] ";
					}
					nodeB = nodeB.next;
					coefficientB = nodeB.coefficient;
					exponentB = nodeB.exponent;
				} else if (exponentA > exponentB) {
					if (coefficientA != 0) {
						line += "[ " + coefficientA + " " + exponentA + " ] ";
					}
					nodeA = nodeA.next;
					coefficientA = nodeA.coefficient;
					exponentA = nodeA.exponent;
				} else {
					if (coefficientA + coefficientB != 0) {
						line += "[ " + (coefficientA + coefficientB) + " " + exponentA + " ] ";
					}
					nodeA = nodeA.next;
					coefficientA = nodeA.coefficient;
					exponentA = nodeA.exponent;
					nodeB = nodeB.next;
					coefficientB = nodeB.coefficient;
					exponentB = nodeB.exponent;
				}
			}
			if (line.length() > 0) {
				System.out.println(line.substring(0, line.length() - 1));
			} else {
				System.out.println();
			}
		}
	}

	/////////////////////下面使用了TreeMap，也可以成功///////////////
	/*
	static class DescendComparator implements Comparator<Integer>{
		@Override
		public int compare(Integer o1, Integer o2) {
			return o2.compareTo(o1);
		}
	}

	public static void main(String args[]) throws Exception {

		BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
		int count = Integer.parseInt(stdin.readLine());


		Comparator<Integer> descendComparator = new DescendComparator();
		for (int i = 0; i < count; i++) {
			Map polynomialMapA = new TreeMap<Integer, Integer>(descendComparator);
			Map polynomialMapB = new TreeMap<Integer, Integer>(descendComparator);

			StringTokenizer token = new StringTokenizer(stdin.readLine());
			int coefficient, exponent;

			while (token.hasMoreElements()) {
				coefficient = Integer.parseInt(token.nextToken());
				exponent = Integer.parseInt(token.nextToken());
				if (polynomialMapA.containsKey(exponent)) {
					coefficient += (Integer) polynomialMapA.get(exponent);
				}
				polynomialMapA.put(exponent, coefficient);
			}

			token = new StringTokenizer(stdin.readLine());
			while (token.hasMoreElements()) {
				coefficient = Integer.parseInt(token.nextToken());
				exponent = Integer.parseInt(token.nextToken());
				if (polynomialMapB.containsKey(exponent)) {
					coefficient += (Integer) polynomialMapB.get(exponent);
				}
				polynomialMapB.put(exponent, coefficient);
			}

			Set<Map.Entry<Integer, Integer>> exponentSetA = polynomialMapA.entrySet();
			Iterator<Map.Entry<Integer, Integer>> iterA = exponentSetA.iterator();
			Map.Entry<Integer, Integer> entryA = iterA.next();
			int coefficientA = entryA.getValue();
			int exponentA = entryA.getKey();

			Set<Map.Entry<Integer, Integer>> exponentSetB = polynomialMapB.entrySet();
			Iterator<Map.Entry<Integer, Integer>> iterB = exponentSetB.iterator();
			Map.Entry<Integer, Integer> entryB = iterB.next();
			int coefficientB = entryB.getValue();
			int exponentB = entryB.getKey();

			String line = "";
			while (exponentA >=0 || exponentB >= 0) {
				if (exponentA < exponentB) {
					if (coefficientB != 0) {
						line += "[ " + coefficientB + " " + exponentB + " ] ";
					}
					entryB = iterB.next();
					coefficientB = entryB.getValue();
					exponentB = entryB.getKey();
				} else if (exponentA > exponentB) {
					if (coefficientA != 0) {
						line += "[ " + coefficientA + " " + exponentA + " ] ";
					}
					entryA = iterA.next();
					coefficientA = entryA.getValue();
					exponentA = entryA.getKey();
				} else {
					if (coefficientA + coefficientB != 0) {
						line += "[ " + (coefficientA + coefficientB) + " " + exponentA + " ] ";
					}
					entryA = iterA.next();
					coefficientA = entryA.getValue();
					exponentA = entryA.getKey();
					entryB = iterB.next();
					coefficientB = entryB.getValue();
					exponentB = entryB.getKey();
				}
			}
			if (line.length() > 0) {
				System.out.println(line.substring(0, line.length() - 1));
			} else {
				System.out.println();
			}
		}
	} */
}
