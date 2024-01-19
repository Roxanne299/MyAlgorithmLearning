package com.zgy;
import java.io.*;
import java.util.*;

class Main{
    public static int N = 10010;
    public static int[] find = new int[N],w = new int[N],v = new int[N];
    public static int[][] g = new int[N][3],dp = new int[N][N];
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static int find(int x){
        if(find[x] != x) find[x] = find(find[x]);
        return find[x];
    }

    public static void main(String[] args)throws Exception{
        String[] s1 = br.readLine().split(" ");
        int n = Integer.parseInt(s1[0]),m = Integer.parseInt(s1[1]),w1 = Integer.parseInt(s1[2]);
        for(int i = 1;i <= n;i++){
            find[i] = i;
            String[] s2 = br.readLine().split(" ");
            g[i][1] = Integer.parseInt(s2[0]);
            g[i][2] = Integer.parseInt(s2[1]);
        }

        for(int i = 1;i <= m;i++){
            String[] s2 = br.readLine().split(" ");
            int a = Integer.parseInt(s2[0]);
            int b = Integer.parseInt(s2[1]);
            int pa = find(a),pb = find(b);
            if(a != b){
                find[pa] = pb;
                w[pb] += w[pa];
                v[pb] += v[pa];
            }
        }

        for(int i = 1;i <= n;i ++){
            if(find[i] == i){
                for(int j = 1;j <= w1;j++){
                    dp[i][j] = Math.max(dp[i-1][j],dp[i-1][j - v[i]] + w[i]);
                }
            }
        }

        System.out.print(dp[n][w1]);

    }
}