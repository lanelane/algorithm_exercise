package pers.lane.algorithm.work;
import pers.lane.sort.*;
import java.util.ArrayList;

//����-���η�
public class Mode {
	static int A[]=new int[100];
public static void main(String[] args) {
	QuickSort qc=new QuickSort();
	qc.quicksort1(A,L,R);
}



public static int func(int[] A,int L, int R)//ȡ�м��������׼
{
	
	int mid=L+(R-L)/2;
	int count=0;

   int X=A[mid];
   int right=0;//���µ�һ��X
   int left = 0;//�������һ��X
   int i=0;//ָ��
//ɨ��A,��������X��ͬ��������X��Χ�����������������ܱ�֤X�ǻ����м䣬���Ի��ǵ��Ÿ���
  for(;i<A.length;i++)
  {
	  if(A[i]==X)
	  {
		 left=i-1;//���ε���ʼ
		 break;
	  }
  }
  while(i<A.length)
  {
	  i++;
	  if(A[i]!=X)
		  break;
  }
 right=i;//�Ұ�ε���ʼ
 if((left-L+1)>(right-left-1))//�����ߵ��������м����ĸ���ҪС�Ͳ���ɨ���������
		 {
	          func(A,L,left);
         }
 if((R-right+1)>(right-left-1))//����ұߡ��ߵ��������м����ĸ���ҪС�Ͳ���ɨ���ұ�����
 {
	 func(A,right,R);
 }
 if(((left-L+1)<(right-left-1))&&((R-right+1)<(right-left-1)))
 {
	 
 }
 
 
 
}





}
