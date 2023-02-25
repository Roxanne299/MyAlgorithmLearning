package com.zgy.learn.algorithm.graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

//朴素版本的Prim 用于 稠密图
public class Prim {
    public static int N = 510,n = 0,m = 0;
    public static int[][] g = new int[N][N];
    public static int[] d = new int[N],stu = new int[N];
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static int prim()
    {
        int res = 0;
        Arrays.fill(d,0x3f3f3f3f);
        d[1] = 0;
        for(int i = 0;i < n;i++)
        {
            int m = -1;
            for(int j = 1;j <= n;j++)
            {
                if(stu[j] == 0 && (m == -1 || d[m] > d[j])) m = j;
            }
            if(i != 0 && d[m] == 0x3f3f3f3f) return -0x3f3f3f3f;
            stu[m] = 1;
            res += d[m];//如果存在负的自环 那么可能dm会被更新 要在更新之前假如结果
            for(int j = 1;j <= n;j++) d[j] = Math.min(d[j],g[m][j]);
        }
        return res;
    }
    public static void main(String[] args)throws Exception {
        String[] s = br.readLine().split(" ");
        n = Integer.parseInt(s[0]);m = Integer.parseInt(s[1]);
        for(int i = 1;i <= n;i++ ) Arrays.fill(g[i],0x3f3f3f3f);
        while((m--)!=0)
        {
            String[] s1 = br.readLine().split(" ");
            int a = Integer.parseInt(s1[0]),b = Integer.parseInt(s1[1]),x = Integer.parseInt(s1[2]);
            g[a][b] = g[b][a] = Math.min(x,g[a][b]);
        }
        int res = prim();
        if(res == -0x3f3f3f3f) System.out.println("impossible");
        else System.out.println(res);

    }

}
