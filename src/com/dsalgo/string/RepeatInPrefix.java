package com.dsalgo.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * http://dsalgo.openjudge.cn/dsmoochw4/4/
 * -------------------------------------------------
 * 4:前缀中的周期
 * -------------------------------------------------
 * 总时间限制: 3000ms 内存限制: 65536kB
 *
 * *****描述*****
 * 一个字符串的前缀是从第一个字符开始的连续若干个字符，例如"abaab"共有5个前缀，分别是a, ab, aba, abaa,  abaab。
 * 我们希望知道一个N位字符串S的前缀是否具有循环节。
 * 换言之，对于每一个从头开始的长度为 i （i 大于1）的前缀，是否由重复出现的子串A组成，即 AAA...A （A重复出现K次,K 大于 1）。
 * 如果存在，请找出最短的循环节对应的K值（也就是这个前缀串的所有可能重复节中，最大的K值）。
 *
 * ***** 输入*****
 * 输入包括多组测试数据。每组测试数据包括两行。
 * 第一行包括字符串S的长度N（2 <= N <= 1 000 000）。
 *  第二行包括字符串S。
 * 输入数据以只包括一个0的行作为结尾。
 *
 * *****输出*****
 * 对于每组测试数据，第一行输出 "Test case #“ 和测试数据的编号。
 * 接下来的每一行，输出前缀长度i和重复测数K，中间用一个空格隔开。前缀长度需要升序排列。
 * 在每组测试数据的最后输出一个空行。
 *
 * *****样例输入*****
 * 3
 * aaa
 * 12
 * aabaabaabaab
 * 0
 *
 * *****样例输出*****
 * Test case #1
 * 2 2
 * 3 3
 *
 * Test case #2
 * 2 2
 * 6 2
 * 9 3
 * 12 4
 *
 * -------------------------------------------------
 * Author: Frank Han (trendhanfeng@gmail.com)
 * Date: 13-11-24
 */
public class RepeatInPrefix {

	public static void main(String args[]) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		for (int circle = 1; ; circle++) {
			int number = Integer.parseInt(reader.readLine());

			if (number != 0) {
				String currentStr = reader.readLine();

				System.out.println("Test case #" + circle);
				int repeatIndex = -1;	//重复串的尾部位置
				for (int currentIndex = 1; currentIndex<number ; currentIndex++) {
					if (repeatIndex == -1) {
						if (currentIndex%2==1
								&& currentStr.substring(0, (currentIndex+1)/2).equals(currentStr.substring((currentIndex+1)/2, currentIndex+1))) {
							System.out.println(currentIndex+1 + " " + 2);
							repeatIndex = (currentIndex-1)/2;
						}
					} else {
						if ((currentIndex+1)%(repeatIndex+1) == 0) {
							if (currentStr.substring(0, repeatIndex+1).equals(currentStr.substring(currentIndex-repeatIndex, currentIndex+1))) {
								System.out.println(currentIndex+1 + " " + (currentIndex+1)/(repeatIndex+1));
							} else {
								repeatIndex = currentIndex;
							}
						}
					}
				}
				System.out.println();
			} else {
				break;
			}
		}
	}
}
