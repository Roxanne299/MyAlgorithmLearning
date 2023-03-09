package com.zgy.learn.algorithm.dfs_and_bfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

//×ßÃÔ¹¬
public class BFS {
    public static int N = 110,n = 0,m = 0;
    public static int[][] a = new int[N][N], d = new int[N][N];
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void bfs()
    {
        Pair[] q = new Pair[N * N];
        int hh = 0,tt = -1;
        d[0][0] = 0;
        a[0][0] = 1;
        q[++tt] = new Pair(0,0);
        while(tt >= hh)
        {
            Pair u = q[hh++];
            int x = u.x,y = u.y;
            int[] dx = {-1,0,1,0},dy = {0,1,0,-1};
            for(int i = 0;i < 4;i++)
            {
                int x1 =  x + dx[i],y1 =  y + dy[i];
                if(x1 >=0 && x1 <n && y1 >= 0 && y1 < m && a[x1][y1] == 0)
                {
                    d[x1][y1] = d[x][y] + 1;
                    a[x1][y1] = 1;
                    q[++tt] =(new Pair(x1,y1));
                }
            }
        }
    }
    public static void main(String[] args) throws Exception{
        String[] s1 = br.readLine().split(" ");
        n = Integer.parseInt(s1[0]);
        m = Integer.parseInt(s1[1]);
        for(int i = 0;i <n;i++)
        {
            String[] s2 = br.readLine().split(" ");
            for(int j = 0;j < m;j++)
                a[i][j] = Integer.parseInt(s2[j]);
        }
        bfs();
        System.out.println(d[n - 1][m - 1]);
    }
    static class Pair
    {
        int x;
        int y;
        public Pair(int x,int y)
        {
            this.x = x;
            this.y = y;
        }
    }
}
