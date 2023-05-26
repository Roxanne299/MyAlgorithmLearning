package com.zgy;


import java.io.*;
import java.util.*;

class Main{
    public static int N = 55;
    public static int[][][] dp = new int[2*N][N][N];
    public static int[][] g = new int[N][N];
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args){
        int m = sc.nextInt(),n = sc.nextInt();
        for(int i = 1;i <= m;i++)
            for(int j = 1;j <= n;j++)
                g[i][j] = sc.nextInt();

        for(int k = 2;k <= m + n;k++)
            for(int i1 = 1;i1 <= m;i1++)
                for(int i2 = 1;i2 <= m;i2++)
                {
                    int j1 = k - i1,j2 = k - i2;
                    if(j1 >= 1 && j2 >= 1 && j1 <= n && j2 <= n){
                        int t = g[i1][j1];
                        if(i1 != i2) t += g[i2][j2];
                        dp[k][i1][i2] = Math.max(dp[k-1][i1][i2] + t,dp[k][i1][i2]);
                        dp[k][i1][i2] = Math.max(dp[k-1][i1-1][i2] + t,dp[k][i1][i2]);
                        dp[k][i1][i2] = Math.max(dp[k-1][i1][i2-1] + t,dp[k][i1][i2]);
                        dp[k][i1][i2] = Math.max(dp[k-1][i1-1][i2-1] + t,dp[k][i1][i2]);
                    }
                }

        System.out.print(dp[n+m][m][n]);
    }
}