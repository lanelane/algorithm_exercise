package pers.lane.algorithm.work;

import java.util.Scanner;

//�ݹ���ϰ-쳲���������
/*
 * 쳲���������ָ��������һ������ 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233��377��610��987��1597��2584��4181��6765��10946��17711��28657...
������дӵ�3�ʼ��ÿһ�����ǰ����֮�͡�
 */
public class Fibonacci {
public static void main(String[] args) {
	System.out.println("��������֪����쳲��������е�����");
	Scanner sc=new Scanner(System.in);
	long n=sc.nextLong();
	System.out.println(func(n));
}




public static long  func(long n)//n�ǵ�i�i��1��ʼ
{
	if(n==1) return 1;
	else if(n==2) return 1;
	else return func(n-1)+func(n-2);
}
}
