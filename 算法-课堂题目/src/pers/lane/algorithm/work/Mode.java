package pers.lane.algorithm.work;
import java.util.Arrays;

import pers.lane.sort.QuickSort;

//����-���η�
//����������ظ�������
public class Mode {

public static void main(String[] args) {
	int [] A= new int[20];
	for(int i=0;i<20;i++)
	{
		A[i]=(int) (Math.random()*10+1);
	}
	QuickSort.quicksort1(A,0,A.length-1);
	System.out.println("������ɵ��������£�");
	System.out.println(Arrays.toString(A));
	System.out.println("����Ϊ:"+A[func(A,0,A.length-1)[0]]);
	System.out.println("����������Ϊ: "+func(A,0,A.length-1)[1]);
	
//	int []B= {1, 1, 1, 1, 1, 1, 2, 3, 3, 4, 5, 6, 6, 6, 6, 8, 8, 10, 10, 10};
//	System.out.println("����Ϊ: "+func(B,0,B.length-1)[1]);
}



public static int[] func(int[] A,int L, int R)//ȡ�м��������׼
{
	//������ʽ���ڣ���Խ��ԽС��
	int [] B=new int[2];//��������B��B[0]��pos��B[1]��max
	int pos;
	int mid=L+(R-L)/2;
	int pos2=0;//�����еõ���pos���
	int count=0;//�����еõ���max���
	int max=0;//���������
   int X=A[mid];
   int right=0;//���µ�һ��X
   int left = 0;//�������һ��X
   int i;
  for(i=L;i<=R;i++)
  {
	  if(A[i]==X)
	  {
		 left=i-1;//���ε���ʼ
		 break;
	  }
  }
  for(;i<=R;i++)
  {
	  if(A[i]!=X)
		  break;
  }
 right=i;//�Ұ�ε���ʼ
 max=right-left-1;//�м���������
 pos=mid;
 if((left-L+1)>max)//�����ߵ��������м����ĸ���ҪС�Ͳ���ɨ���������
		 {
	          count=func(A,L,left)[1];
	          pos2=func(A,L,left)[0];
	          if(max<count)
	          {
	        	  pos=pos2;
	        	  max=count;
	          }
         }
 if((R-right+1)>max)//����ұߡ��ߵ��������м����ĸ���ҪС�Ͳ���ɨ���ұ�����
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
