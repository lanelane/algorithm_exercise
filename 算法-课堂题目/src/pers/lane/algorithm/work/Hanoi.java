package pers.lane.algorithm.work;

import java.util.Scanner;

//�ݹ���ϰ-��ŵ������
/*
 * ��a���ӵ������ƶ���b�����Ͽ��Խ���c����
 */
public class Hanoi {
public static void main(String[] args) {
	System.out.println("������a�����ϵ����Ӹ�����");
	Scanner sc=new Scanner(System.in);
	int n=sc.nextInt();
	func(n,'a','b','c');
}




public static void func(int n,char a,char  b,char c)
{
	if(n==0) return;
	else {
	func(n-1,a,c,b);//n-1�����ӽ���b��a�ƶ���c
	move(a,b);//��1�����Ӵ�a�ƶ���b
	func(n-1,c,b,a);//�ƶ�ʣ�µ�����
	}
}



public static  void move(char m,char n)//��m�����ƶ���n����
{
	System.out.println("�����Ӵ�"+m+"�����Ƶ�"+n+"����");
	
}
}
