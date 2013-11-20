package com.dsalgo.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * http://dsalgo.openjudge.cn/dsmoochw4/1/
 * -------------------------------------------------
 * 1:统计字符数
 * -------------------------------------------------
 * 总时间限制: 1000ms 内存限制: 65535kB
 *
 * *****描述*****
 * 判断一个由a-z这26个字符组成的字符串中哪个字符出现的次数最多
 *
 * ***** 输入*****
 * 第1行是测试数据的组数n，每组测试数据占1行，是一个由a-z这26个字符组成的字符串
 * 每组测试数据之间有一个空行，每行数据不超过1000个字符且非空
 *
 * *****输出*****
 * n行，每行输出对应一个输入。一行输出包括出现次数最多的字符和该字符出现的次数，中间是一个空格。
 * 如果有多个字符出现的次数相同且最多，那么输出ascii码最小的那一个字符
 *
 * *****样例输入*****
 * 2
 * abbccc
 *
 * adfadffasdf
 *
 * *****样例输出*****
 * c 3
 * f 4
 *
 * -------------------------------------------------
 * Author: Frank Han (trendhanfeng@gmail.com)
 * Date: 13-11-20
 */
public class CountChar {

	public static void main(String args[]) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int stringNumber = Integer.parseInt(reader.readLine());

		int[] counts;
		int maxIndex;
		for (int i=0; i < stringNumber; i++) {
			counts = new int [26];
			maxIndex = 0;

			for (char c : reader.readLine().toCharArray()) {
				counts[c-'a']++;
			}

			for (int j=1; j<26; j++) {
				if (counts[j] > counts[maxIndex]) {
					maxIndex = j;
				}
			}
			System.out.println((char)(maxIndex + 'a') + " " + counts[maxIndex]);

			if (i < stringNumber-1) {
				reader.readLine();
			}
		}
	}
}
