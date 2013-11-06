package com.dsalgo.linearlist;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * http://dsalgo.openjudge.cn/dsmoochw2/4/
 * -------------------------------------------------
 * 总时间限制: 1000ms 内存限制: 65536kB
 *
 * *****描述*****
 * 把M个同样的苹果放在N个同样的盘子里，允许有的盘子空着不放，问共有多少种不同的分法？（用K表示）5，1，1和1，5，1 是同一种分法。
 *
 * *****输入*****
 * 第一行是测试数据的数目t（0 <= t <= 20）。以下每行均包含二个整数M和N，以空格分开。1<=M，N<=10。
 *
 * *****输出*****
 * 对输入的每组数据M和N，用一行输出相应的K。
 *
 * *****样例输入*****
 * 1
 * 7 3
 *
 * *****样例输出*****
 * 8
 * -------------------------------------------------
 * Author: Frank Han (trendhanfeng@gmail.com)
 * Date: 13-11-6
 */
public class PutApple {

	public static int getCount (int apple, int plate, int max) {

		if (plate == 1 && apple > max) {
			return 0;
		}

		if (apple == 0 || plate == 1) {
			return 1;
		}

		int count = 0;
		for (int i=Math.min(apple, max); i>=0; i--) {
			int currentCount = getCount(apple-i, plate-1, i);
			if (currentCount == 0) {
				break;
			}
		 	count = count + currentCount;
		}
		return count;
	}

	public static void main (String args[]) throws Exception{
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer line = new StringTokenizer(reader.readLine());
		int round = Integer.parseInt(line.nextToken());

		for (int i=0; i<round; i++) {
			line = new StringTokenizer(reader.readLine());
			int apple = Integer.parseInt(line.nextToken());
			int plate = Integer.parseInt(line.nextToken());
			System.out.println(getCount(apple, plate, apple));
		}
	}
}
