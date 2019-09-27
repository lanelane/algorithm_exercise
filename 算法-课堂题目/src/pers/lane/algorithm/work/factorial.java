package pers.lane.algorithm.work;

import java.util.Scanner;

//递归练习-阶乘
public class factorial {
public static void main(String[] args) {
	System.out.println("请输入计算阶乘的数字,不要超过20");
	Scanner sc=new Scanner(System.in);
	long n=sc.nextLong();
	System.out.println(func(n));
}


public static long func(long n)
{
	if(n==0) return 1;
	else if(n==1) return 1;
	else return n*func(n-1);
}
}
