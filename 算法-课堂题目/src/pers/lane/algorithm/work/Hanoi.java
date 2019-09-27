package pers.lane.algorithm.work;

import java.util.Scanner;

//递归练习-汉诺塔问题
/*
 * 将a柱子的盘子移动到b柱子上可以借助c柱子
 */
public class Hanoi {
public static void main(String[] args) {
	System.out.println("请输入a柱子上的盘子个数：");
	Scanner sc=new Scanner(System.in);
	int n=sc.nextInt();
	func(n,'a','b','c');
}




public static void func(int n,char a,char  b,char c)
{
	if(n==0) return;
	else {
	func(n-1,a,c,b);//n-1个盘子借助b从a移动到c
	move(a,b);//将1个盘子从a移动到b
	func(n-1,c,b,a);//移动剩下的盘子
	}
}



public static  void move(char m,char n)//从m柱子移动到n柱子
{
	System.out.println("将盘子从"+m+"柱子移到"+n+"柱子");
	
}
}
