package com.dsalgo.stackandqueue;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * http://dsalgo.openjudge.cn/dsmoochw3/4/
 * -------------------------------------------------
 * 4:中缀表达式的值
 * -------------------------------------------------
 * 总时间限制: 200ms 内存限制: 1024kB
 *
 * *****描述*****
 * 人们熟悉的四则运算表达式称为中缀表达式，例如(23+34*45/(5+6+7))。
 * 在程序设计语言中，可以利用堆栈的方法把中缀表达式转换成保值的后缀表达式（又称逆波兰表示法），
 * 并最终变为计算机可以直接执行的指令，得到表达式的值。
 * 给定一个中缀表达式，编写程序，利用堆栈的方法，计算表达式的值。
 *
 * ***** 输入*****
 * 第一行为测试数据的组数N
 * 接下来的N行，每行是一个中缀表达式。
 * 表达式中只含数字、四则运算符和圆括号，操作数都是正整数，数和运算符、括号之间没有空格。
 * 中缀表达式的字符串长度不超过600。
 *
 * *****输出*****
 * 对每一组测试数据输出一行，为表达式的值
 *
 * *****样例输入*****
 * 3
 * 3+5*8
 * (3+5)*8
 * (23+34*45/(5+6+7))
 *
 * *****样例输出*****
 * 43
 * 64
 * 108
 *
 * *****提示*****
 * 注意：运算过程均为整数运算（除法运算'/'即按照C++定义的int除以int的结果，测试数据不会出现除数为0的情况），输出结果也为整数（可能为负）。
 * 中间计算结果可能为负。
 *
 * -------------------------------------------------
 * Author: Frank Han (trendhanfeng@gmail.com)
 * Date: 13-11-17
 */
public class InfixExpression {

	public static class Element {
		public static final int TYPE_NUMBER = 0;
		public static final int TYPE_SYMBOL = 1;

		public int type;
		public int value;

		public Element(int type, int value) {
			this.type = type;
			this.value = value;
		}
	}

	public static class Expression {
		public Stack<Element> elements;

		public Expression (String expression) {
			this.elements = new Stack<Element>();
			int tempValue = 0;
			boolean isNumber = false;
			for (int i=0; i<expression.length(); i++) {
				switch (expression.charAt(i)){
					case '+' : case '-' : case '*' : case '/' : case '(' : case ')' :
						if(isNumber) {
							this.elements.push(new Element(Element.TYPE_NUMBER, tempValue));
							isNumber = false;
						}
						this.elements.push(new Element(Element.TYPE_SYMBOL, expression.charAt(i)));
						break;
					default:
						if(isNumber) {
							tempValue = tempValue*10 + expression.charAt(i)-48;
						} else {
							tempValue = expression.charAt(i)-48;
						}
						isNumber = true;
				}
			}
			if (isNumber) {
				this.elements.push(new Element(Element.TYPE_NUMBER, tempValue));
			}
		}

		public void toPostfix() {
			Stack<Element> finalStack = new Stack<Element>();
			Stack<Element> symbolStack = new Stack<Element>();

			Element currentElement;
			for (int i=0; i<elements.size(); i++) {
				currentElement = elements.get(i);
				if (currentElement.type == Element.TYPE_NUMBER) {
					finalStack.push(currentElement);
				} else {  //currentElement.type == Element.TYPE_SYMBOL
					if (currentElement.value == '+' || currentElement.value == '-') {
						while (!symbolStack.isEmpty()) {
							if (symbolStack.peek().value == '(') {
								break;
							}
							finalStack.push(symbolStack.pop());
						}
						symbolStack.push(currentElement);
					} else if (currentElement.value == '*' || currentElement.value == '/') {
						while (!symbolStack.isEmpty()) {
							if (symbolStack.peek().value != '*' && symbolStack.peek().value != '/') {
								break;
							}
							finalStack.push(symbolStack.pop());
						}
						symbolStack.push(currentElement);
					} else if (currentElement.value == '(') {
						symbolStack.push(currentElement);
					} else {   //currentElement.value == ')'
						while (!symbolStack.isEmpty()) {
							if (symbolStack.peek().value == '(') {
								symbolStack.pop();
								break;
							}
							finalStack.push(symbolStack.pop());
						}
					}
				}
			}

			while (!symbolStack.isEmpty()) {	//剩下的符号全部入栈
				finalStack.push(symbolStack.pop());
			}

			this.elements = finalStack;
		}

		public int getResult() {
			this.toPostfix();

			Stack<Element> tempStack = new Stack<Element>();

			for (Element e : elements) {
				if (e.type == Element.TYPE_NUMBER) {
					tempStack.push(e);
				} else {
					Element tempElement;
					int rightValue = tempStack.pop().value;
					int leftValue = tempStack.pop().value;
					switch (e.value) {
						case '+' :
							tempElement = new Element(Element.TYPE_NUMBER, leftValue+rightValue);
							tempStack.push(tempElement);
							break;
						case '-' :
							tempElement = new Element(Element.TYPE_NUMBER, leftValue-rightValue);
							tempStack.push(tempElement);
							break;
						case '*' :
							tempElement = new Element(Element.TYPE_NUMBER, leftValue*rightValue);
							tempStack.push(tempElement);
							break;
						case '/' :
							tempElement = new Element(Element.TYPE_NUMBER, leftValue/rightValue);
							tempStack.push(tempElement);
							break;
					}
				}
			}

			return tempStack.pop().value;
		}

		public String toString() {
			String strReturn = "";

			for (Element e : elements) {
				if (e.type == Element.TYPE_SYMBOL) {
					strReturn += (char)e.value + " ";
				} else {
					strReturn += e.value + " ";
				}
			}

			return strReturn;
		}
	}

	public static void main (String args[]) throws Exception {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(reader.readLine());

		for (int i=0; i<n; i++) {
			Expression expression = new Expression(reader.readLine());
			System.out.println(expression.getResult());
		}
	}
}
