package pers.lane.algorithm.work;
import java.util.Arrays;

import pers.lane.sort.QuickSort;

//众数-分治法
//不允许出现重复的众数
public class Mode {

public static void main(String[] args) {
	int [] A= new int[20];
	for(int i=0;i<20;i++)
	{
		A[i]=(int) (Math.random()*10+1);
	}
	QuickSort.quicksort1(A,0,A.length-1);
	System.out.println("随机生成的数组如下：");
	System.out.println(Arrays.toString(A));
	System.out.println("众数为:"+A[func(A,0,A.length-1)[0]]);
	System.out.println("众数的重数为: "+func(A,0,A.length-1)[1]);
	
//	int []B= {1, 1, 1, 1, 1, 1, 2, 3, 3, 4, 5, 6, 6, 6, 6, 8, 8, 10, 10, 10};
//	System.out.println("众数为: "+func(B,0,B.length-1)[1]);
}



public static int[] func(int[] A,int L, int R)//取中间的数做基准
{
	//不用显式出口，会越分越小的
	int [] B=new int[2];//辅助数组B，B[0]放pos，B[1]放max
	int pos;
	int mid=L+(R-L)/2;
	int pos2=0;//子序列得到的pos结果
	int count=0;//子序列得到的max结果
	int max=0;//最大重数；
   int X=A[mid];
   int right=0;//记下第一个X
   int left = 0;//记下最后一个X
   int i;
  for(i=L;i<=R;i++)
  {
	  if(A[i]==X)
	  {
		 left=i-1;//左半段的起始
		 break;
	  }
  }
  for(;i<=R;i++)
  {
	  if(A[i]!=X)
		  break;
  }
 right=i;//右半段的起始
 max=right-left-1;//中间数的重数
 pos=mid;
 if((left-L+1)>max)//如果左边的数，比中间数的个数要小就不用扫描左边了了
		 {
	          count=func(A,L,left)[1];
	          pos2=func(A,L,left)[0];
	          if(max<count)
	          {
	        	  pos=pos2;
	        	  max=count;
	          }
         }
 if((R-right+1)>max)//如果右边、边的数，比中间数的个数要小就不用扫描右边了了
 {
	 count=func(A,right,R)[1];
	 pos2=func(A,right,R)[0];
	 if(max<count)
	 {
		 pos=pos2;
		 max=count;
	 }
 }
         B[0]=pos;
         B[1]=max;
          return B;
}





}
