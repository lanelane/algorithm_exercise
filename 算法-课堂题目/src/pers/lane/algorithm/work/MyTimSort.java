package pers.lane.algorithm.work;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;

public class MyTimSort<T> {
//数组 
private static int[]a;

//合并的临时数组
private static int[] tmp;
private static final int INITIAL_TEMP_LENGTH = 256;

//运行栈
private static int stackSize=0;
private static int[] runBase;
private static int[] runLength;
	

private MyTimSort(int []a)
{
	this.a=a;
	int len=a.length;
	//分配栈的大小
	 int stackLen = (len < 120 ? 5 :len < 1542 ? 10 :   len < 119151 ? 24 : 40);
     runBase = new int[stackLen];
     runLength = new int[stackLen];
     
     //临时数组
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
   System.out.println("TimSort耗时:"+(end-start)+"ns");
   /*long start2=System.currentTimeMillis();
   quicksort1(b,0,b.length-1);
   long end2=System.currentTimeMillis();
   System.out.println("快排耗时:"+(end2-start2)+"ms");
   long start3=System.currentTimeMillis();
   heapSort(c);
   long end3=System.currentTimeMillis();
   System.out.println("堆排序耗时:"+(end3-start3)+"ms");   */
   long start4=System.nanoTime();
  mergesort(d,0,d.length-1);
  long end4=System.nanoTime();
   System.out.println("归并排序耗时:"+(end4-start4)+"ns");   
   long start5=System.nanoTime();
   MyTimSort.binaryInsertSort(e, 0, 0, e.length-1);
   long end5=System.nanoTime();
   System.out.println("二分插入排序耗时:"+(end5-start5)+"ns");   
}

	
//查找顺序片段,返回长度
private static int findSequence(int[] a,int low, int high)
	{
		assert low<high;
		int j;
		//只有两个元素
		if(low+1==high)
			return 2;//*两个元素 降序也不用倒置
		if (a[low]<a[low+1])//递增序列
		{
			for(j=low+1;j<high;j++)
			{
				if(a[j]<a[j+1]) {}
				else break;
			}
		}
		else//递减序列
		{
			for(j=low+1;j<high;j++)
			{
				if(a[j]>a[j+1]) {}
				else break;
			}
			//就地逆置
			reverse(a,low,j);
		}
		return j-low+1;
}

	
//逆置
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

//二分插入排序
private static void binaryInsertSort(int []a,int start,int low,int high)
{
    int i,j;
    //如果本来就是无序的
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
    //left后面的后移
   System.arraycopy(a, left, a, left+1, i-left);
   a[left]=n;
   } 
}


//计算minrun
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
	int leftLength = high - low+1;//剩下还未排序的长度
	assert leftLength>0;
	
	//长度<2就不用排序
    if (leftLength< 2)
        return;

    // <32直接二分插入排序
    if (leftLength < 32) {
    	int k=findSequence(a,low,high);
    	binaryInsertSort(a,k,low,high);
//    	System.out.println(Arrays.toString(a));
        return;
    }
    
    //>32 TimSort
   MyTimSort mts= new MyTimSort(a); //新建TimSort对象，保存栈的状态
   int minRun=getMinRun(leftLength);
   //分片+压栈
    do
    {	
    	int run=findSequence(a,low,high);//计算出一个run的长度
//  	System.out.println(Arrays.toString(a));
    	if(run<minRun)//需要扩充
    	{
    		int l=leftLength<=minRun?leftLength:minRun;
    		binaryInsertSort(a,low+run,low,low+l-1);
    		run=l;
    	}
    	//压栈
        mts.push(low, run);
//    	System.out.println("第"+stackSize+"个分片长度："+run);
//       System.out.println("第"+stackSize+"个分片为:");
        //打印栈中分片的元素
//       mts.printRun(stackSize-1);
        
        mts.checkMerge();
        
        low=low+run;//下一run
        leftLength=leftLength-run;
    }while(leftLength!=0);
    
    //合并栈中剩下的所有run
   mts.mergeAll();  
}

//压栈 
private void push(int low,int length)
{
	this.runBase[stackSize]=low;
	this.runLength[stackSize]=length;
	stackSize++;	
}

//检查栈中序列是否需要合并
private void checkMerge()
{
  while(stackSize>1)//起码有2个run
  {
	  int k=stackSize-1;//顶层run
	  if(k>1&&runLength[k]+runLength[k-1]>=runLength[k-2])
	  {
		  if(runLength[k-2]<runLength[k])
		  {
			  mergeMid(k-2);//合并
		  }
		  else mergeMid(k-1);
	  }
	  else//k=1或者不满足条件1的 只用检查条件2
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
	int k=i;//最终查找范围的起点i
	int j=0;
	while(i<=high1)
	{
		if(a[i]<=a[low2])
		{
			k=i;
			i=(int) (i+Math.pow(2, j));
			j++;
		}
		else break;//如果相等，则插在原数据之后
	}
	if(i>high1) i=high1;
	if(i==low1) return low1;//插到第一个位置
	//二分查找
    int low=k; 
    int high=i;
	while(low<=high)
	{
		int mid=(low+high)/2;
		if(a[mid]==a[low2])
		{
			return (mid+1);//相等则插到之后的位置，保持稳定性
		}
		else if(a[mid]<a[low2])
		{
			low=mid+1;
		}
		else high=mid-1;
	}	
	return low;//插到low之前的位置上
}

//找左边run的最后一个元素在右边run的什么位置
private static int findLast(int low1,int high1,int low2,int high2)
{
	int i=high2;
	int k=high2;//最终查找范围的起点
	int j=0;
	while(i>=low2)
	{
		if(a[i]>=a[high1])
		{
			k=i;
			i=(int) (i-Math.pow(2, j));
			j++;
		}
		else break;//如果相等，则插在原数据之后
	}
	if(i<low2)
	{
		i=low2;
	}
	if(i==high2) return high2;//插到high2后面
	if(i==k)/*!=high2 只有等=low2的情况*/  return low2-1;// 插到最前面
	//二分查找
    int high=k; 
    int low=i;
	while(low<=high)
	{
		int mid=(low+high)/2;
		if(a[mid]==a[high1])
		{
			return mid;//相等则插在后面
		}
		else if(a[mid]<a[high1])
		{
			low=mid+1;
		}
		else high=mid-1;
	}	
	return high;//插到high之后的位置上
}

/* //合并
//找右边run的第一个元素在左边run的什么位置
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
		else break;//如果相等，则插在原数据之后
	}
	return i;//插到i之前的位置上
}

//找左边run的最后一个元素在右边run的什么位置
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
	return i;//插到i之后的位置上，如果相等插在右边run数据之前
}
 */

//合并n和n+1,不管怎样n都是较小的那个
private static void mergeMid(int n)
{
	int low1=runBase[n];
	int high1=low1+runLength[n]-1;
	int low2=runBase[n+1];
	int high2=low2+runLength[n+1]-1;
//	int maxlen=runLength[n]>=tmp.length?runLength[n]:tmp.length;
	int[] temp=ensureTemp(runLength[n]);
//	System.out.println("临时数组长度"+temp.length);
	int f=findFirst(low1,high1,low2,high2);
	int l=findLast(low1,high1,low2,high2);

	System.arraycopy(a, f, temp, 0, high1-f+1);	
	a[f++]=a[low2++];
	int i=0;
	int j=low2;
	int k=f;//原数组指针
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
		//不用动了
	}
   
	//修改关于栈的记录
	int newLength=runLength[n]+runLength[n+1];
	runLength[n]=newLength;
	if(stackSize-n>2)//stackSize-1>n+1  合并下面2个，则还需修改最上面run的位置。
	{
		runLength[n+1]=runLength[n+2];
		runBase[n+1]=runBase[n+2];		
	}
	stackSize--;
}

//合并剩下的所有run
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

//打印栈中run
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

//确保临时数组长度足够用
private static int[] ensureTemp(int minCapacity) {
    // 如果临时数组长度不够，那需要重新计算临时数组长度；
    // 如果长度够，直接返回当前临时数组
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


//************************快排
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
		quicksort1(A,L,p-1);
		quicksort1(A,p+1,R);
	}
}

//堆排序
private static void heapSort(int []a)
{
	initHeap(a);
	int len=a.length;
	//排序
	while(len>0)
	{
		//交换堆顶和最后一个数		
		int temp=a[len-1];
		a[len-1]=a[0];
		a[0]=temp;
		len--;
//		System.out.println(Arrays.toString(a));
		//调整大根堆
		adjustHeap(a,len);
	}
}

//初始化大根堆
private static void initHeap(int[]a)
{
	for(int i=0;i<a.length;i++)
	{
		int child=i;
		int father=(i-1)/2;
		while(a[child]>a[father])//大于父结点
		{
			int temp=a[child];
			a[child]=a[father];
			a[father]=temp;
			child=father;
			father=(child-1)/2;
		}		
	}
}

//调整大根堆
private static void adjustHeap(int[] a,int n)
{
	//调整顶端和他的孩子们
	int i=0;
	int left=1;
	int right=2;
	while(left<n)
	{
		int max;
	if(right<n)//有右孩子
	{
		max=a[left]>=a[right]?left:right;//取左右孩子的最大值
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
	else break;//a[i]比左右孩子的值都大
	}	
}

//归并排序
public static void  merge(int []a,int L,int R,int mid)//合并过程
//mid是分界线，两个有序序列是[0,mid],[mid+1,R]
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

public static void mergesort(int [] a,int L,int R)//排序
{
	
	if(L==R) return;//base case 分成的子区间只有一个元素时候停止,此时L==R
	int mid=L+(R-L)/2;
	mergesort(a,L,mid);
	mergesort(a,mid+1,R);
	merge(a,L,R,mid);	
}
}