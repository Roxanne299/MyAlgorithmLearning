package com.zgy.learn.algorithm.dfs_and_bfs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;

//n 个数字全排列 深搜其实运用的就是栈的数据结构
public class DFS {
    public static int N = 10,n = 0;
    public static int[] path = new int[N],stu = new int[N];
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void dfs(int u)
    {
        if(u == n)
        {
            for(int i = 0;i < n;i++) System.out.print(path[i] + " ");
            System.out.println();
            return;
        }

        for(int i = 0;i < n;i++)
        {
            if(stu[i] == 0)
            {
                stu[i] = 1;
                path[u] = i + 1;
                dfs(u + 1);
                stu[i] = 0;
            }
        }
    }
    public static void main(String[] args) throws Exception{
        n = Integer.parseInt(br.readLine());
        dfs(0);
    }
}
