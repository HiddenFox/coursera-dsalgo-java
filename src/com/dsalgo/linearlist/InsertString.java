package com.dsalgo.linearlist;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * http://dsalgo.openjudge.cn/dsmoochw2/2/
 * -------------------------------------------------
 * 总时间限制: 1000ms 内存限制: 65536kB
 *
 * *****描述*****
 * 有两个字符串str和substr，str的字符个数不超过10，substr的字符个数为3。（字符个数不包括字符串结尾处的'\0'。）将substr插入到str中ASCII码最大的那个字符后面，若有多个最大则只考虑第一个。
 *
 * *****输入*****
 * 输入包括若干行，每一行为一组测试数据，格式为
 * str substr
 *
 * *****输出*****
 * 对于每一组测试数据，输出插入之后的字符串。
 *
 * *****样例输入*****
 * abcab eee
 * 12343 555
 *
 * *****样例输出*****
 * abceeeab
 * 12345553
 * -------------------------------------------------
 * Author: Frank Han (trendhanfeng@gmail.com)
 * Date: 13-11-6
 */
public class InsertString {

	public static void main (String args[]) throws Exception {
		BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));

		while (true) {
			String line = stdin.readLine();
			StringTokenizer st = new StringTokenizer(line);
			String a = st.nextToken();
			String b = st.nextToken();

			int maxPosition = 0;
			for (int i = 1; i < a.length(); i++) {
				if (a.charAt(i) > a.charAt(maxPosition)) {
					maxPosition = i;
				}
			}
			System.out.println(a.substring(0, maxPosition+1) + b + a.substring(maxPosition+1));
		}
	}
}
