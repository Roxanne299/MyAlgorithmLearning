package com.zgy.learn.algorithm.dfs_and_bfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class NQueen {
        public static int N = 20,n = 0;
        public static char[][] a = new char[N][N];
        //分别是判断在该行是不是有元素 该正对角线是不是有元素 该返对角线是不是有元素
        //在枚举i行 j列的时候，正对角线（↘）是 第 n - i + j个 反对角线（↙）是 第 i + j + 1个
        public static int[] col = new int[N],dig = new int[N],udig = new int[N];
        public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        public static void dfs(int u)
        {
            if(u == n)
            {
                for(int i = 0;i < n;i++)
                {
                    for (int j = 0;j < n;j++) System.out.print(a[i][j]);
                    System.out.println();
                }
                System.out.println();

                return;
            }
            for(int i = 0;i < n;i++)
            {
                if(col[i] == 0 && dig[n - u + i]==0 && udig[u + i + 1] == 0)
                {
                    col[i] = dig[n - u + i] = udig[u + i + 1] = 1;
                    a[u][i] = 'Q';
                    dfs(u + 1);
                    col[i] = dig[n - u + i] = udig[u + i + 1] = 0;
                    a[u][i] = '.';
                }
            }
        }
        public static void main(String[] args) throws Exception{
            n = Integer.parseInt(br.readLine());
            for(int i = 0;i < n;i++) Arrays.fill(a[i],'.');
            dfs(0);
        }
    }

