package pers.lane.algorithm.work;

import java.util.Scanner;

//�ݹ���ϰ-�׳�
public class factorial {
public static void main(String[] args) {
	System.out.println("���������׳˵�����,��Ҫ����20");
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
