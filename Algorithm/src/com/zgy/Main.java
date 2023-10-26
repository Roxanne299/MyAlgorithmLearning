package com.zgy;

import java.io.*;
import java.util.*;

class Main{
    public static int N = 110,V = 110;
    public static int[] v = new int[N];
    public static int[][] dp = new int[N][V],s = new int[N][V];
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args)throws Exception {
        String[] s1 = br.readLine().split(" ");
        int n = Integer.parseInt(s1[0]),v = Integer.parseInt(s1[1]);
        int k1 = Integer.parseInt(br.readLine());
        for(int i = 1;i <= n;i++){
            String[] s2 = br.readLine().split(" ");
            for(int j = 1;j <= v;j++){
                s[i][j] = Integer.parseInt(s2[j-1]);
            }

        }
        for(int i = 1;i <= n;i++) {
            for(int j = 1;j <= v;j++)
            {
                dp[i][j] = dp[i-1][j];
                for(int k = 1;k <= v;k++)
                    if(j >= k) dp[i][j] = Math.max(dp[i][j],dp[i-1][j-k]+s[i][k]);
            }

        }

        bw.write(String.valueOf(dp[n][v]));

        for(int i = n,j = v;i >= 1;i--){
            for(int k = 1;k <= v;k++){
                if(j >= k && dp[i][j] == dp[i-1][j - k] + s[i][k]){
                    bw.write(String.valueOf(i)+" "+String.valueOf(k)+"\n");
                    j-=k;
                }
            }
        }




        bw.flush();

    }
}