package com.zgy.learn.algorithm.high_level.DP.Monotone_queue_optimization;
import java.io.*;
import java.util.*;

//类似理想的正方形思想
public class AcWing_1091_理想的正方形 {
    public static int N = 1010,n,m, P = 998244353;
    public static int[][] g = new int[N][N], row_max = new int[N][N],row_min = new int[N][N],res_max = new int[N][N],res_min = new int[N][N];
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args)throws Exception{
        String[] s1 = br.readLine().split(" ");
        int n = Integer.parseInt(s1[0]),m = Integer.parseInt(s1[1]),a = Integer.parseInt(s1[2]),b = Integer.parseInt(s1[3]);
        for(int i = 0;i < n;i++){
            String[] s2 = br.readLine().split(" ");
            for(int j = 0;j < m;j++){
                g[i][j] = Integer.parseInt(s2[j]);
            }
        }

        //首先预处理出每一行 长度为b的队列中的最大值和最小值 row_max[i][j] 表明 第i行j ~ j + b - 1 中 的最大值
        for(int i = 0;i < n;i++){
            //一维单调队列(第i行)
            int tt = -1,hh = 0;
            int[] q = new int[N];
            for(int j = 0;j < m;j++){
                while(tt >= hh && j - q[hh] > b - 1) hh++;
                while(tt >= hh && g[i][q[tt]] < g[i][j]) tt--;
                q[++tt] = j;
                if(j >= b - 1) row_max[i][j - b + 1] = g[i][q[hh]];
            }
        }

        //首先预处理出每一行 长度为b的队列中的最大值和最小值 row_max[i][j] 表明 第i行j ~ j + b - 1 中的最小值
        for(int i = 0;i < n;i++){
            //一维单调队列(第i行)
            int tt = -1,hh = 0;
            int[] q = new int[N];
            for(int j = 0;j < m;j++){
                while(tt >= hh && j - q[hh] > b - 1) hh++;
                while(tt >= hh && g[i][q[tt]] > g[i][j]) tt--;
                q[++tt] = j;
                if(j >= b - 1) row_min[i][j - b + 1] = g[i][q[hh]];
            }
        }


        //接着预处理第i列~第i + b-1列组合的一个n行b列的数组，竖向使用单调队列算出  第i列~第i + b-1列其中行数不超过a的最大值
        //第一层循环是列
        for(int i = 0;i <= m - b;i++)
        {
            int tt = -1,hh = 0;
            int[] q = new int[N];
            for(int j = 0;j < n;j++){
                while(tt >= hh && j - q[hh] > a - 1) hh++;
                while(tt >= hh && row_max[j][i] > row_max[q[tt]][i]) tt--;
                q[++tt] = j;
                if(j >= a - 1) res_max[j - a + 1][i] = row_max[q[hh]][i];
            }
        }



        //接着预处理第i列~第i + b-1列组合的一个n行b列的数组，竖向使用单调队列算出  第i列~第i + b-1列其中行数不超过a的最小值
        //第一层循环是列
        for(int i = 0;i <= m - b;i++)
        {
            int tt = -1,hh = 0;
            int[] q = new int[N];
            for(int j = 0;j < n;j++){
                while(tt >= hh && j - q[hh] > a - 1) hh++;
                while(tt >= hh && row_min[j][i] < row_min[q[tt]][i]) tt--;
                q[++tt] = j;
                if(j >= a - 1) res_min[j - a + 1][i] = row_min[q[hh]][i];
            }
        }

        int res = 0;
        for(int i = 0;i <= n - a;i++)
        {
            for(int j = 0;j <= m - b;j++){
                res = (int)(((long)res_min[i][j] * res_max[i][j] + res) % P);
            }
        }

        System.out.print(res);
    }

}



//需要注意的是滑动窗口中存的队列是数组的下标 而不是值并且判断是不是超过注定窗口长度不是q[tt] - q[hh] 是j - q[hh]
//还需要注意 再算竖向的滑动窗口的时候 比较的不是g数组 求最大值比较的就是row_max 求最小值就是比较row_min
