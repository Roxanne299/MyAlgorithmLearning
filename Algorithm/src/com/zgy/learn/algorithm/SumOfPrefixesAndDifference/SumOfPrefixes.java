package com.zgy.learn.algorithm.SumOfPrefixesAndDifference;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class SumOfPrefixes {
//    public static int N = 100010;
//    public static int[] S = new int[N];
//    public static int[] a = new int[N];
//    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
//    //一维前缀和
//    public static int prefix_1(int l,int r)
//    {
//        return S[r] - S[l - 1];
//    }
//
//    public static void main(String[] args) throws Exception{
//        //读取一维数组
//        String[] s1 = br.readLine().split(" ");
//        int n = Integer.parseInt(s1[0]),m = Integer.parseInt(s1[1]);
//        String[] s = br.readLine().split(" ");
//        for(int i = 1; i <= n ;i++){
//            a[i] = Integer.parseInt(s[i-1]);
//            S[i] = a[i] + S[i-1];
//        }
//        while((m--)!=0)
//        {
//            String[] s2 = br.readLine().split(" ");
//            int l = Integer.parseInt(s2[0]),r = Integer.parseInt(s2[1]);
//            System.out.println(prefix_1(l,r));
//        }
//
//
//    }
    //二维前缀和

    public static int N = 1010;
    public static int[][] a = new int[N][N],s = new int[N][N];
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static int prefix_2(int l1,int r1,int l2,int r2)
    {
            return s[l2][r2] - s[l2][r1 - 1] - s[l1 - 1][r2] + s[l1 - 1][r1 - 1];
    }

    public static void main(String[] args) throws Exception{
        String[] s1 = br.readLine().split(" ");
        int n = Integer.parseInt(s1[0]),m = Integer.parseInt(s1[1]),q = Integer.parseInt(s1[2]);
        for (int i = 1;i <= n; i++)
        {
            String[] s2 = br.readLine().split(" ");
            for(int j = 1;j <= m;j++)
            {
                a[i][j] = Integer.parseInt(s2[i - 1]);
                s[i][j] = a[i][j] + s[i - 1][j] + s[i][j - 1] - s[i-1][j-1];
            }
        }
        while((q--)!=0)
        {
            String[] s3 = br.readLine().split(" ");
            int l1 = Integer.parseInt(s3[0]),l2 = Integer.parseInt(s3[2]),r1 = Integer.parseInt(s3[1]),r2  = Integer.parseInt(s3[3]);
            System.out.println(prefix_2(l1,r1,l2,r2));
        }
    }

}
