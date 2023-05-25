package com.zgy.learn.algorithm.graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Map;

//时间复杂度是O(n*n) 适用于稠密图
public class Dijkstra {
    public static int N = 510,n = 0,m = 0;
    public static int[][] g = new int[N][N];
    public static int[] d = new int[N],stu = new int[N];
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static int dijskra(int x)
    {
        Arrays.fill(d,0x3f3f3f3f);
        d[1] = 0;
        for(int i = 1;i <= n;i++)
        {
            int k = -1;
            for(int j = 1;j <= n;j++)
                if(stu[j] == 0 && (k == -1 || d[k] > d[j])) k = j;
            stu[k] = 1;
            for(int j = 1;j <= n;j++) d[j] = Math.min(d[j],d[k] + g[k][j]);
        }
        if(d[n]!=0x3f3f3f3f) return d[n];
        return -1;
    }
    public static void main(String[] args)throws Exception {
        String[] s1 = br.readLine().split(" ");
        n = Integer.parseInt(s1[0]);
        m = Integer.parseInt(s1[1]);
        for(int i = 1;i <= n;i++) Arrays.fill(g[i],0x3f3f3f3f);
        while((m--)!=0)
        {
            String[] s2 = br.readLine().split(" ");
            int a = Integer.parseInt(s2[0]),b = Integer.parseInt(s2[1]), x = Integer.parseInt(s2[2]);
            g[a][b] = Math.min(g[a][b],x);
        }
        System.out.println(dijskra(1));
    }
}