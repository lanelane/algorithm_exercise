package pers.lane.algorithm.work;

import java.util.Scanner;

//��ⷽ��1 �鲢������������
public class inversion {
static int count=0;//��¼����Ը���
static int[] A;
   public static void main(String[] args) {
	   System.out.println("����������");
	   Scanner sc=new Scanner(System.in);
	   String str=sc.nextLine();
	   String[] s=str.trim().split(",");
	   int length=s.length;
	   A=new int[length];
	   for(int i=0;i<length;i++)
	   {
		   A[i]=Integer.valueOf(s[i]);
	   }
 System.out.println("��������£�");
 mergesort(A,0,length-1);
System.out.println("\n����Ը���: "+count);


}
	
public static void  merge(int []a,int L,int R,int mid)//�ϲ�����
//mid�Ƿֽ��ߣ���������������[0,mid],[mid+1,R]
{
	
	int[] b=new int[100];
	int i=L;
	int j=mid+1;
	int k=0;
   while(i<=mid&&j<=R)
   {
	   if(a[i]>a[j])//��ʱ���������
	   {
		   count=count+mid-i+1;
		   for(int h=0;h<(mid-i+1);h++)
		   {
			   System.out.print("("+a[i+h]+a[j]+")"+"  ");//��ӡ�����
		   }
		   b[k]=a[j];
		   j++;
	   }
	   else
	   {
		   b[k]=a[i];
	       i++;
		}
	   k++;
   }
   if(i<=mid)
   {
	   
	  System.arraycopy(a, i, b, k, mid-i+1);
   }
   if(j<=R)
   {
	   System.arraycopy(a, j, b, k, R-j+1);
   }
  System.arraycopy(b,0, a, L, R-L+1);
}

public static void mergesort(int [] a,int L,int R)//����
{
	
	if(L==R) return;//base case �ֳɵ�������ֻ��һ��Ԫ��ʱ��ֹͣ,��ʱL==R
	int mid=L+(R-L)/2;
	mergesort(a,L,mid);
	mergesort(a,mid+1,R);
	merge(a,L,R,mid);	
}
}


