package pers.lane.algorithm.work;
import pers.lane.sort.*;
import java.util.ArrayList;

//众数-分治法
public class Mode {
	static int A[]=new int[100];
public static void main(String[] args) {
	QuickSort qc=new QuickSort();
	qc.quicksort1(A,L,R);
}



public static int func(int[] A,int L, int R)//取中间的数做基准
{
	
	int mid=L+(R-L)/2;
	int count=0;

   int X=A[mid];
   int right=0;//记下第一个X
   int left = 0;//记下最后一个X
   int i=0;//指针
//扫描A,看有无与X相同的数，与X周围交换，但是这样不能保证X们还在中间，所以还是得排个序
  for(;i<A.length;i++)
  {
	  if(A[i]==X)
	  {
		 left=i-1;//左半段的起始
		 break;
	  }
  }
  while(i<A.length)
  {
	  i++;
	  if(A[i]!=X)
		  break;
  }
 right=i;//右半段的起始
 if((left-L+1)>(right-left-1))//如果左边的数，比中间数的个数要小就不用扫描左边了了
		 {
	          func(A,L,left);
         }
 if((R-right+1)>(right-left-1))//如果右边、边的数，比中间数的个数要小就不用扫描右边了了
 {
	 func(A,right,R);
 }
 if(((left-L+1)<(right-left-1))&&((R-right+1)<(right-left-1)))
 {
	 
 }
 
 
 
}





}
