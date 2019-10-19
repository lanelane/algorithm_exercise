package pers.lane.algorithm.DP;

import java.util.Arrays;

public class LCS {

public static void main(String[] args) {
	 char[]X= {'A','B','C','B','D','A','B'};
	 char[]Y= {'B','D','C','A','B','A'};
	 int[][]b=new int[X.length+1][Y.length+1];
	 int m=X.length;
	 int n=Y.length;
     System.out.println("最长公共子序列长度为:"+lcsLength(X,Y,b));//注意数组也是对象，传的是引用，b被修改了
    /* for(int i=0;i<=m;i++)
     {
    	 System.out.println(Arrays.toString(b[i]));
     }*/
     String s=new String();
     System.out.println("最长公共子序列有:");
     printLCS(X,Y,m,n,b,s);
}

//求LCS长度
private static int lcsLength(char []X,char[]Y,int[][] B)
{
	 //X 和Y的元素个数分别为m和n，但是数组长度为m+1和n+1
	int m=X.length;
	int n=Y.length;
	 int[][]C=new int[m+1][n+1];
	//初始化子序列长度为0的情况
    for(int i=0;i<=m;i++)
    	C[i][0]=0;
    for(int j=0;j<=n;j++)
    	C[0][j]=0;
    for(int i=1;i<=m;i++)
    {
    	for(int j=1;j<=n;j++)
    	{
    		if(X[i-1]==Y[j-1])// 注意i-1和j-1
    		{
    			C[i][j]=C[i-1][j-1]+1;
    			B[i][j]=1;		
    		}
    		else if(C[i-1][j]>C[i][j-1])
    		{
    			C[i][j]=C[i-1][j];
    			B[i][j]=2;
    		}
    		else if(C[i-1][j]<C[i][j-1])
    		{
    			C[i][j]=C[i][j-1];
    			B[i][j]=3;
    		}
    		else if(C[i-1][j]==C[i][j-1])
    		{
    			C[i][j]=C[i-1][j];
    			B[i][j]=4;
    		}
    	}
    }
    return C[m][n];
}
//输出LCS
private static void printLCS(char[]X,char[]Y,int i,int j,int[][]b,String s)
{
	if(i==0||j==0) //递归出口
	{
        StringBuilder s2=new StringBuilder(s);
        System.out.println(s2.reverse());
        return;
	}
    if(b[i][j]==1)
			{
                   s=s+X[i-1];
		          printLCS(X,Y,i-1,j-1,b,s);
			}
	else if(b[i][j]==2)
		printLCS(X,Y,i-1,j,b,s);
	else if(b[i][j]==3)
		printLCS(X,Y,i,j-1,b,s);
	else 
		{
		   printLCS(X,Y,i,j-1,b,s);
		   printLCS(X,Y,i-1,j,b,s);
		}
}


}
