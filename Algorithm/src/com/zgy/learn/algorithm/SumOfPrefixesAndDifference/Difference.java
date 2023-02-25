package com.zgy.learn.algorithm.SumOfPrefixesAndDifference;

import java.io.*;

public class Difference {
//    //一维差分
//    public static int N = 100010;
//    public static int[] s = new int[N],a = new int[N];
//    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
//
//    public static void main(String[] args) throws Exception{
//        String[] s1 = br.readLine().split(" ");
//        int n = Integer.parseInt(s1[0]),q = Integer.parseInt(s1[1]);
//        String[] s2 = br.readLine().split(" ");
//        for(int i = 1;i <= n;i++)
//        {
//            s[i] = Integer.parseInt(s2[i - 1]);
//            //构造差分序列
//            a[i] = s[i] - s[i - 1];
//        }
//        while((q--)!=0)
//        {
//            String[] s3 = br.readLine().split(" ");
//            int l = Integer.parseInt(s3[0]),r = Integer.parseInt(s3[1]),x =  Integer.parseInt(s3[2]);
//            a[l ] += x;
//            a[r + 1] -= x;
//        }
//        for(int i = 1;i <= n; i++){
//            s[i] = a[i] + s[i - 1];
//            System.out.println(s[i]+" ");
//        }
//    }
    //二维差分
    public static int N = 1010;
    public static int[][] a = new int[N][N];
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void insert(int x1,int y1,int x2,int y2,int x)
    {
        a[x1][y1] += x;
        a[x1][y2+1]-=x;
        a[x2+1][y1]-=x;
        a[x2+1][y2+1]+=x;
    }
    public static void main(String[] args) throws Exception{
        String[] s1 = br.readLine().split(" ");
        int n = Integer.parseInt(s1[0]),m = Integer.parseInt(s1[1]),q = Integer.parseInt(s1[2]);
        for(int i = 1;i <= n;i++)
        {
            String[] s2 = br.readLine().split(" ");
            for(int j = 1;j <= m;j++)
                insert(i,j,i,j,Integer.parseInt(s2[j-1]));    //用插入来表示更简洁
        }
        while((q--)!=0)
        {
            String[] s3 = br.readLine().split(" ");
            int x1 = Integer.parseInt(s3[0]),y1 = Integer.parseInt(s3[1]),x2 = Integer.parseInt(s3[2]),y2 = Integer.parseInt(s3[3]),x = Integer.parseInt(s3[4]);
            insert(x1,y1,x2,y2,x);
        }
        for(int i = 1;i <=n;i++)
        {
            for(int j = 1;j <= m;j++)
            {
                a[i][j] = a[i][j] + a[i - 1][j] + a[i][j - 1] - a[i -1 ][j - 1];
                System.out.print(a[i][j] + " ");
            }
            System.out.println();
        }
    }
}
