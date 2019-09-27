package pers.lane.sort;

import java.util.Arrays;

public class QuickSort {
static int A[]= new int[10000];
public static void main(String[] args) {
	for(int i=0;i<10000;i++)
	{
		A[i]=(int)((Math.random()*10000)+1);
	}
	System.out.println("产生随机数组如下:");
	System.out.println(Arrays.toString(A));
	long start=System.currentTimeMillis();
	quicksort1(A,0,9999);
	long end=System.currentTimeMillis();
	System.out.println("快速排序耗时: "+(end-start)+"ms");
	System.out.println("快速排序后数组如下：");
	System.out.println(Arrays.toString(A));
}



public static int partition(int []A,int L,int R)
{
	//选最后一个数作为基准
	int i=L;
	int X=A[R];
	for(int j=L;j<R;j++)
	{
		if(A[j]<X)
				{
			        int temp=A[j];
			        A[j]=A[i];
			        A[i]=temp;
			         i++;
				}
		else {}
	}
	int temp=A[i];
	A[i]=X;
	A[R]=temp;
    return i;
	
}

public static void quicksort1(int[]A,int L,int R)
{
	if(L>=R) return;
	else
	{
		int p=partition(A,L,R);
		quciksort1(A,L,p-1);
		quciksort1(A,p+1,R);
	}
}
}