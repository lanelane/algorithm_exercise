package pers.lane.sort;
import java.util.Arrays;

public class StraightInsertSort {
	static  int A[]=new int[10000];
	public static void main(String[] args) {
		for(int i=0;i<10000;i++) {
			A[i]=(int)((Math.random())*10000);
		}
	System.out.println("随机生成的A数组如下:");
	System.out.println(Arrays.toString(A));
	long start=System.currentTimeMillis();//开始计时
     insertsort(A);
     long end=System.currentTimeMillis();
     System.out.println("直接插入排序耗时:"+(end-start)+"ms");
     System.out.println("直接插入排序后的结果如下:");
     System.out.println(Arrays.toString(A));
}

	public static void insertsort(int[] A){
		int i=0,j;
		int key;
		for(j=1;j<A.length;j++)
		{
			key=A[j];	
		for(i=j;i>0;i--)
		{
			if(key<A[i-1])
			{
				A[i]=A[i-1];//后移1
			}
			else break;
		}
		A[i]=key;
	}	
}
	}

