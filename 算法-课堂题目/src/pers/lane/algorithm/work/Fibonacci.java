package pers.lane.algorithm.work;

import java.util.Scanner;

//递归练习-斐波那契数列
/*
 * 斐波那契数列指的是这样一个数列 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233，377，610，987，1597，2584，4181，6765，10946，17711，28657...
这个数列从第3项开始，每一项都等于前两项之和。
 */
public class Fibonacci {
public static void main(String[] args) {
	System.out.println("请输入想知道的斐波那契数列的项数");
	Scanner sc=new Scanner(System.in);
	long n=sc.nextLong();
	System.out.println(func(n));
}




public static long  func(long n)//n是第i项，i从1开始
{
	if(n==1) return 1;
	else if(n==2) return 1;
	else return func(n-1)+func(n-2);
}
}
