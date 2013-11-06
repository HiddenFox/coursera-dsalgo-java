package com.dsalgo.linearlist;


import java.io.*;
import java.util.*;

/**
 * http://dsalgo.openjudge.cn/dsmoochw1/1/
 * -------------------------------------------------
 * 总时间限制: 1000ms 内存限制: 65536kB
 *
 * *****描述*****
 * Calculate a + b
 *
 * ***** 输入*****
 * Two integer a,,b (0 ≤ a,b ≤ 10)
 *
 * *****输出*****
 * Output a + b
 *
 * *****样例输入*****
 * 1 2
 *
 * *****样例输出*****
 * 3
 * -------------------------------------------------
 * Author: Frank Han (trendhanfeng@gmail.com)
 * Date: 13-11-6
 */
public class ABProblem {

	public static void main (String args[]) throws Exception {

//		Scanner cin = new Scanner(System.in);
//		int a = cin.nextInt(),b = cin.nextInt();
//		System.out.println(a+b);

		BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
		String line = stdin.readLine();
		StringTokenizer st = new StringTokenizer(line);
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		System.out.println(a+b);
	}
}
