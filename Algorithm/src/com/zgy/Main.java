package com.zgy;


import java.io.*;
import java.util.*;

class Main{
    public static int N = 1000010,C = 1000010;
    public static int[] cnt = new int[2 * N],pre = new int[2 * N];
    public static long[][] dp = new long[2 * N][10];
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args)throws Exception{
        String[] s = br.readLine().split(" ");
        int n = Integer.parseInt(s[0]),c = Integer.parseInt(s[1]);
        String[] s1 = br.readLine().split(" ");
        for(int i = 1;i <= n;i++){
            int t = Integer.parseInt(s1[i-1]);
            cnt[t+1]++;
            cnt[t+c+1]++;
        }
        //预处理C(a,b)
        dp[0][0] = 1;
        for(int i = 1;i <= n;i++){
            for(int j = 1;j <= 3;j++){
                dp[i][j] = dp[i-1][j] + dp[i-1][j-1];
            }
        }
        //求前缀和
        for(int i = 1;i <= 2 * c;i++){
            pre[i] = cnt[i];
            pre[i] += pre[i-1];
        }
        long res = dp[n][3];
        for(int i = 1;i <= c;i++){
            //以i为左端点，一个
            res -= cnt[i] * dp[pre[i + c/2] - pre[i]][2];
            if(cnt[i] >= 2)
                res -= dp[cnt[i]][2] * dp[pre[i + c/2] - pre[i]][1];
            if(cnt[i] >= 3)
                res -= dp[cnt[i]][3];
        }

        System.out.print(res);
    }
}

