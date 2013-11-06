package com.dsalgo.linearlist;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * http://dsalgo.openjudge.cn/dsmoochw2/3/
 * -------------------------------------------------
 * 总时间限制: 5000ms 内存限制: 65536kB
 *
 * *****描述*****
 * 给出N个范围在[0, 65535]的整数，编程支持以下的操作：
 * （1）修改操作：C d，所有的数都增加d。如果超过65535，把结果模65536。 0 <= d <= 65535
 * （2）查询操作：Q i，统计在N个正整数中有多少个整数其对应的二进制形式的第i位二进制位为非0。0 <= i <= 15。并且最低位i为0。
 * 最后，输出所有查询操作的统计值。
 *
 * *****输入*****
 * 输入的第一行为两个正整数N,M,其中N为操作的整数的个数，而M为具体有多少个操作。
 * 输入的第二行为N个正整数，为进行操作的N个正整数。
 * 下面有M行，分别表示M个操作。 N<=100000,M<=200000
 *
 * *****输出*****
 * 输出所有查询操作Q的统计值，每一个查询操作统计结果输出为一行。
 *
 * *****样例输入*****
 * 3 5
 * 1 2 4
 * Q 1
 * Q 2
 * C 1
 * Q 1
 * Q 2
 *
 * *****样例输出*****
 * 1
 * 1
 * 2
 * 1
 *
 * *****提示*****
 * 只输出查询操作Q的统计值。
 * -------------------------------------------------
 * Author: Frank Han (trendhanfeng@gmail.com)
 * Date: 13-11-6
 */
public class BitQuery {

	static class IntArray {
		public int[] array;

		public void add (int number) {
			for (int i=0; i<array.length; i++) {
				array[i] = (array[i]+number) % 65536;
			}
		}

		public int query (int number) {
			int count = 0;

			int mask = 1 << number;

			for (int i=0; i<array.length; i++) {
				if ((array[i] & mask) != 0) {
					count++;
				}
			}

			return count;
		}
	}

	public static void main (String args[]) throws Exception{
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer line = new StringTokenizer(reader.readLine());
		int n = Integer.parseInt(line.nextToken());
		int m = Integer.parseInt(line.nextToken());

		IntArray intArray = new IntArray();
		intArray.array = new int[n];


		line = new StringTokenizer(reader.readLine());
		for (int i=0; i<n; i++) {
			String token = line.nextToken();
			intArray.array[i] = Integer.parseInt(token);
		}

		for (int i=0; i<m; i++) {
			line = new StringTokenizer(reader.readLine());
			String operator = line.nextToken();
			int number = Integer.parseInt(line.nextToken());

			if (operator.equals("C")) {
				intArray.add(number);
			} else if (operator.equals("Q")) {
				System.out.println(intArray.query(number));
			}
		}
	}

}
