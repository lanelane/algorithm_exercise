package pers.lane.algorithm.work;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;

public class MyTimSort<T> {
//���� 
private static int[]a;

//�ϲ�����ʱ����
private static int[] tmp;
private static final int INITIAL_TEMP_LENGTH = 256;

//����ջ
private static int stackSize=0;
private static int[] runBase;
private static int[] runLength;
	

private MyTimSort(int []a)
{
	this.a=a;
	int len=a.length;
	//����ջ�Ĵ�С
	 int stackLen = (len < 120 ? 5 :len < 1542 ? 10 :   len < 119151 ? 24 : 40);
     runBase = new int[stackLen];
     runLength = new int[stackLen];
     
     //��ʱ����
     int[] newArray = new int[len < 2 * INITIAL_TEMP_LENGTH ?
             len >>> 1 : INITIAL_TEMP_LENGTH];
     tmp = newArray;
}
	
public static void main(String[] args) {
	int[]a= new int[100000];
	int[]b=new int[a.length];
	int[]c=new int[a.length];
	int[]d=new int[a.length];
	int[]e=new int[a.length];
	for(int i=0;i<a.length;i++)
	{
		a[i]=(int)(Math.random()*100+1);
	}
	System.arraycopy(a, 0, b, 0, a.length);
	System.arraycopy(a, 0, c, 0, a.length);
	System.arraycopy(a, 0, d, 0, a.length);
	System.arraycopy(a, 0, e, 0, a.length);
	System.out.println(Arrays.toString(a));
	MyTimSort mts=new MyTimSort(a);
	long start=System.nanoTime();
   mts.sort();
   long end=System.nanoTime();
   System.out.println("TimSort��ʱ:"+(end-start)+"ns");
   /*long start2=System.currentTimeMillis();
   quicksort1(b,0,b.length-1);
   long end2=System.currentTimeMillis();
   System.out.println("���ź�ʱ:"+(end2-start2)+"ms");
   long start3=System.currentTimeMillis();
   heapSort(c);
   long end3=System.currentTimeMillis();
   System.out.println("�������ʱ:"+(end3-start3)+"ms");   */
   long start4=System.nanoTime();
  mergesort(d,0,d.length-1);
  long end4=System.nanoTime();
   System.out.println("�鲢�����ʱ:"+(end4-start4)+"ns");   
   long start5=System.nanoTime();
   MyTimSort.binaryInsertSort(e, 0, 0, e.length-1);
   long end5=System.nanoTime();
   System.out.println("���ֲ��������ʱ:"+(end5-start5)+"ns");   
}

	
//����˳��Ƭ��,���س���
private static int findSequence(int[] a,int low, int high)
	{
		assert low<high;
		int j;
		//ֻ������Ԫ��
		if(low+1==high)
			return 2;//*����Ԫ�� ����Ҳ���õ���
		if (a[low]<a[low+1])//��������
		{
			for(j=low+1;j<high;j++)
			{
				if(a[j]<a[j+1]) {}
				else break;
			}
		}
		else//�ݼ�����
		{
			for(j=low+1;j<high;j++)
			{
				if(a[j]>a[j+1]) {}
				else break;
			}
			//�͵�����
			reverse(a,low,j);
		}
		return j-low+1;
}

	
//����
private static  void reverse(int[] a,int low,int high)
{
	int temp;
	for(int i=low;i<high;i++)
	{
		temp=a[i];
		a[i]=a[low+high-i];
		a[low+high-i]=temp;
	}	
}

//���ֲ�������
private static void binaryInsertSort(int []a,int start,int low,int high)
{
    int i,j;
    //����������������
    if(start==low)
    {
    start=low+1;
    }   
    for( i=start;i<=high;i++)
    {
    int n=a[i];
    int left=low;
    int right=i-1;
    while(left<=right)
    {
    	int mid=(left+right)/2;
       if(a[mid]>a[i])
       {
    	   right=mid-1;
       }
       else left=mid+1;    
    }
    //left����ĺ���
   System.arraycopy(a, left, a, left+1, i-left);
   a[left]=n;
   } 
}


//����minrun
private static int getMinRun(int n)
{
    assert n >= 0;
    int r = 0;      // Becomes 1 if any 1 bits are shifted off
    while (n >= 32) {
        r |= (n & 1);
        n >>= 1;
    }
    return n + r;
}

//sort

private static void sort()
{
	sort(0,a.length-1);
}

private static void sort(int low,int high)
{
	int leftLength = high - low+1;//ʣ�»�δ����ĳ���
	assert leftLength>0;
	
	//����<2�Ͳ�������
    if (leftLength< 2)
        return;

    // <32ֱ�Ӷ��ֲ�������
    if (leftLength < 32) {
    	int k=findSequence(a,low,high);
    	binaryInsertSort(a,k,low,high);
//    	System.out.println(Arrays.toString(a));
        return;
    }
    
    //>32 TimSort
   MyTimSort mts= new MyTimSort(a); //�½�TimSort���󣬱���ջ��״̬
   int minRun=getMinRun(leftLength);
   //��Ƭ+ѹջ
    do
    {	
    	int run=findSequence(a,low,high);//�����һ��run�ĳ���
//  	System.out.println(Arrays.toString(a));
    	if(run<minRun)//��Ҫ����
    	{
    		int l=leftLength<=minRun?leftLength:minRun;
    		binaryInsertSort(a,low+run,low,low+l-1);
    		run=l;
    	}
    	//ѹջ
        mts.push(low, run);
//    	System.out.println("��"+stackSize+"����Ƭ���ȣ�"+run);
//       System.out.println("��"+stackSize+"����ƬΪ:");
        //��ӡջ�з�Ƭ��Ԫ��
//       mts.printRun(stackSize-1);
        
        mts.checkMerge();
        
        low=low+run;//��һrun
        leftLength=leftLength-run;
    }while(leftLength!=0);
    
    //�ϲ�ջ��ʣ�µ�����run
   mts.mergeAll();  
}

//ѹջ 
private void push(int low,int length)
{
	this.runBase[stackSize]=low;
	this.runLength[stackSize]=length;
	stackSize++;	
}

//���ջ�������Ƿ���Ҫ�ϲ�
private void checkMerge()
{
  while(stackSize>1)//������2��run
  {
	  int k=stackSize-1;//����run
	  if(k>1&&runLength[k]+runLength[k-1]>=runLength[k-2])
	  {
		  if(runLength[k-2]<runLength[k])
		  {
			  mergeMid(k-2);//�ϲ�
		  }
		  else mergeMid(k-1);
	  }
	  else//k=1���߲���������1�� ֻ�ü������2
	  {
		  if(runLength[k-1]<=runLength[k])
			  mergeMid(k-1);
		  else break;
	  }
  }
	
}

private static int findFirst(int low1,int high1,int low2,int high2)
{
	int i=low1;
	int k=i;//���ղ��ҷ�Χ�����i
	int j=0;
	while(i<=high1)
	{
		if(a[i]<=a[low2])
		{
			k=i;
			i=(int) (i+Math.pow(2, j));
			j++;
		}
		else break;//�����ȣ������ԭ����֮��
	}
	if(i>high1) i=high1;
	if(i==low1) return low1;//�嵽��һ��λ��
	//���ֲ���
    int low=k; 
    int high=i;
	while(low<=high)
	{
		int mid=(low+high)/2;
		if(a[mid]==a[low2])
		{
			return (mid+1);//�����嵽֮���λ�ã������ȶ���
		}
		else if(a[mid]<a[low2])
		{
			low=mid+1;
		}
		else high=mid-1;
	}	
	return low;//�嵽low֮ǰ��λ����
}

//�����run�����һ��Ԫ�����ұ�run��ʲôλ��
private static int findLast(int low1,int high1,int low2,int high2)
{
	int i=high2;
	int k=high2;//���ղ��ҷ�Χ�����
	int j=0;
	while(i>=low2)
	{
		if(a[i]>=a[high1])
		{
			k=i;
			i=(int) (i-Math.pow(2, j));
			j++;
		}
		else break;//�����ȣ������ԭ����֮��
	}
	if(i<low2)
	{
		i=low2;
	}
	if(i==high2) return high2;//�嵽high2����
	if(i==k)/*!=high2 ֻ�е�=low2�����*/  return low2-1;// �嵽��ǰ��
	//���ֲ���
    int high=k; 
    int low=i;
	while(low<=high)
	{
		int mid=(low+high)/2;
		if(a[mid]==a[high1])
		{
			return mid;//�������ں���
		}
		else if(a[mid]<a[high1])
		{
			low=mid+1;
		}
		else high=mid-1;
	}	
	return high;//�嵽high֮���λ����
}

/* //�ϲ�
//���ұ�run�ĵ�һ��Ԫ�������run��ʲôλ��
//gallop
private static int findFirst(int low1,int high1,int low2,int high2)
{
	int i=low1;
	while(i<=high1)
	{
		if(a[i]<=a[low2])
		{
			i++;
		}
		else break;//�����ȣ������ԭ����֮��
	}
	return i;//�嵽i֮ǰ��λ����
}

//�����run�����һ��Ԫ�����ұ�run��ʲôλ��
private static int findLast(int low1,int high1,int low2,int high2)
{
	int i=high2;
	while(i>=low2)
	{
		if(a[i]>=a[high1])
		{
			i--;
		}
		else break;
	}
	return i;//�嵽i֮���λ���ϣ������Ȳ����ұ�run����֮ǰ
}
 */

//�ϲ�n��n+1,��������n���ǽ�С���Ǹ�
private static void mergeMid(int n)
{
	int low1=runBase[n];
	int high1=low1+runLength[n]-1;
	int low2=runBase[n+1];
	int high2=low2+runLength[n+1]-1;
//	int maxlen=runLength[n]>=tmp.length?runLength[n]:tmp.length;
	int[] temp=ensureTemp(runLength[n]);
//	System.out.println("��ʱ���鳤��"+temp.length);
	int f=findFirst(low1,high1,low2,high2);
	int l=findLast(low1,high1,low2,high2);

	System.arraycopy(a, f, temp, 0, high1-f+1);	
	a[f++]=a[low2++];
	int i=0;
	int j=low2;
	int k=f;//ԭ����ָ��
	while((i<=high1-f+1)&&(j<=l))
	{
		if(temp[i]<=a[j])
		{
			a[k]=temp[i];
			i++;
		}
		else
		{
			a[k]=a[j];
			j++;
		}
		k++;
	}
	if(i<=high1-f+1)
	{
		System.arraycopy(temp, i, a, k, high1-f+2-i);//high1-f+1-i+1
	}
	if(j<l)
	{
		//���ö���
	}
   
	//�޸Ĺ���ջ�ļ�¼
	int newLength=runLength[n]+runLength[n+1];
	runLength[n]=newLength;
	if(stackSize-n>2)//stackSize-1>n+1  �ϲ�����2���������޸�������run��λ�á�
	{
		runLength[n+1]=runLength[n+2];
		runBase[n+1]=runBase[n+2];		
	}
	stackSize--;
}

//�ϲ�ʣ�µ�����run
private void mergeAll()
{
//	System.out.println("zhan:"+stackSize);
	while(stackSize>1)
	{
		int k=stackSize-1;
		if(k>1&&runLength[k-2]<runLength[k])
		{
			mergeMid(k-2);
		}
		else mergeMid(k-1);
	}
}

//��ӡջ��run
/*
private void printRun(int n)
{
	for(int i=runBase[n];i<runBase[n]+runLength[n];i++)
	{
		System.out.print(a[i]);
	}
	System.out.println("");
}
*/

//ȷ����ʱ���鳤���㹻��
private static int[] ensureTemp(int minCapacity) {
    // �����ʱ���鳤�Ȳ���������Ҫ���¼�����ʱ���鳤�ȣ�
    // ������ȹ���ֱ�ӷ��ص�ǰ��ʱ����
    if (tmp.length < minCapacity) {
       /* int newSize = minCapacity;
        newSize |= newSize >> 1;
        newSize |= newSize >> 2;
        newSize |= newSize >> 4;
        newSize |= newSize >> 8;
        newSize |= newSize >> 16;
        newSize++;

        if (newSize < 0) // 
            newSize = minCapacity;
        else
            newSize = Math.min(newSize, a.length >>> 1);

        int[] newArray = new int[newSize];
        tmp = newArray;*/
    	int[] newArray=new int[minCapacity];
    	tmp=newArray;
    }
    return tmp;
}


//************************����
public static int partition(int []A,int L,int R)
{
	//ѡ���һ������Ϊ��׼
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
		quicksort1(A,L,p-1);
		quicksort1(A,p+1,R);
	}
}

//������
private static void heapSort(int []a)
{
	initHeap(a);
	int len=a.length;
	//����
	while(len>0)
	{
		//�����Ѷ������һ����		
		int temp=a[len-1];
		a[len-1]=a[0];
		a[0]=temp;
		len--;
//		System.out.println(Arrays.toString(a));
		//���������
		adjustHeap(a,len);
	}
}

//��ʼ�������
private static void initHeap(int[]a)
{
	for(int i=0;i<a.length;i++)
	{
		int child=i;
		int father=(i-1)/2;
		while(a[child]>a[father])//���ڸ����
		{
			int temp=a[child];
			a[child]=a[father];
			a[father]=temp;
			child=father;
			father=(child-1)/2;
		}		
	}
}

//���������
private static void adjustHeap(int[] a,int n)
{
	//�������˺����ĺ�����
	int i=0;
	int left=1;
	int right=2;
	while(left<n)
	{
		int max;
	if(right<n)//���Һ���
	{
		max=a[left]>=a[right]?left:right;//ȡ���Һ��ӵ����ֵ
	}
	else max=left;
	if(a[i]<a[max])
	{
		int temp=a[i];
		a[i]=a[max];
		a[max]=temp;
		i=max;
		left=i*2+1;
		right=i*2+2;
	}
	else break;//a[i]�����Һ��ӵ�ֵ����
	}	
}

//�鲢����
public static void  merge(int []a,int L,int R,int mid)//�ϲ�����
//mid�Ƿֽ��ߣ���������������[0,mid],[mid+1,R]
{
	
	int[] b=new int[R-L+1];
	int i=L;
	int j=mid+1;
	int k=0;
 while(i<=mid&&j<=R)
 {
	   if(a[i]>a[j])
	   {	  
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