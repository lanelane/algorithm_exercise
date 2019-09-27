package pers.lane.algorithm.work;

import java.util.Scanner;

//求解方法1 归并排序过程中求解
public class inversion {
static int count=0;//记录逆序对个数
static int[] A;
   public static void main(String[] args) {
	   System.out.println("请输入数组");
	   Scanner sc=new Scanner(System.in);
	   String str=sc.nextLine();
	   String[] s=str.trim().split(",");
	   int length=s.length;
	   A=new int[length];
	   for(int i=0;i<length;i++)
	   {
		   A[i]=Integer.valueOf(s[i]);
	   }
 System.out.println("逆序对如下：");
 mergesort(A,0,length-1);
System.out.println("\n逆序对个数: "+count);


}
	
public static void  merge(int []a,int L,int R,int mid)//合并过程
//mid是分界线，两个有序序列是[0,mid],[mid+1,R]
{
	
	int[] b=new int[100];
	int i=L;
	int j=mid+1;
	int k=0;
   while(i<=mid&&j<=R)
   {
	   if(a[i]>a[j])//这时产生逆序对
	   {
		   count=count+mid-i+1;
		   for(int h=0;h<(mid-i+1);h++)
		   {
			   System.out.print("("+a[i+h]+a[j]+")"+"  ");//打印逆序对
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

public static void mergesort(int [] a,int L,int R)//排序
{
	
	if(L==R) return;//base case 分成的子区间只有一个元素时候停止,此时L==R
	int mid=L+(R-L)/2;
	mergesort(a,L,mid);
	mergesort(a,mid+1,R);
	merge(a,L,R,mid);	
}
}


