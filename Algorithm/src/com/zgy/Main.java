package com.zgy;

import java.io.*;
import java.util.*;

import java.io.*;
import java.util.*;

class Main{
    public static int N = 100010,J = 110;
    public static int[] a = new int[N];
    public static int[][] dp = new int[N][3];//��ʾǰN�������л���ǰN�������޻���һ�죬ǰN�������޻�����1��
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args)throws Exception{
        String[] s1 = br.readLine().split(" ");
        int n = Integer.parseInt(s1[0]),j = Integer.parseInt(s1[1]);
        String[] s2 = br.readLine().split(" ");
        for(int i = 1;i <= n;i++) a[i] = Integer.parseInt(s2[i-1]);
        for(int i = 0;i <= n;i++){
            dp[i][1] = -0x3f3f3f3f;
        }
        for(int i = 1;i <= n;i++){
            //�����޻���һ��
            dp[i][0] = dp[i-1][2] + a[i];
            //�����޻�����һ��
            dp[i][1] = Math.max(dp[i-1][1],dp[i-1][0]);
            //�����л�
            dp[i][2] = Math.max(dp[i-1][1] + a[i],dp[i-1][2]);
        }


        System.out.println(Math.max(dp[n][0],dp[n][1]));
    }
}