package pers.lane.algorithm.work;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Nonboringsequences {
    static Map<Integer, Integer> map=new HashMap<Integer, Integer>();
    static int N;
    static int[] arr;
	static int[] A;//辅助数组A,放在某个值之前重复出现的值
	static int[]B;

public static void main(String[] args) {
		System.out.println("请输入数组");
		Scanner sc=new Scanner(System.in);
		String str=sc.nextLine();
		String[] s=str.trim().split(" ");
		N=s.length;	
		arr=new int[N];
		 A=new int[N];
		 B=new int[N];
		 for(int i=0;i<N;i++)
			{
				arr[i]=Integer.valueOf(s[i]);//自动拆箱
			}
		 sc.close();			
		 helparr(N);
		 if(func(0,N-1))
			 System.out.println("这是不无聊序列");
		 else System.out.println("这是无聊序列");
	  /*for(int m: A)
	   {
		   System.out.println(m);
	   }
	   for(int m: B)
	   {
		   System.out.println(m);
	   }*/
}
	

//**************************
	public static void helparr(int n)//辅助数组
	{
		for(int i=0;i<n;i++)
		{
			if(!map.containsKey(arr[i]))
			{
				A[i]=-1;
			}
			else
			{
				A[i]=map.get(arr[i]);
			}
			map.put(arr[i], i);
		}
		map.clear();
		for(int i=n-1;i>=0;i--)
		{
			if(!map.containsKey(arr[i]))
			{
				B[i]=n+1;
			}
			else
			{
				B[i]=map.get(arr[i]);
			}
			map.put(arr[i], i);
		}
	}


	
	public static boolean func(int left,int right)
	{
		if (left >= right) return true;
		int i = left, j = right;
		while (i <= j)
		{
			if (A[i]<left&&B[i]>right)//从左边往后找
				return func(left, i - 1) && func(i + 1, right);
			i++;
			if (A[j]<left&&B[j]>right)//从右边往前找
				return func(left, j - 1) && func(j + 1, right);
			j--;
		}
        return false;

}
}



