package com.zgy.learn.algorithm.graph;

import java.io.*;
import java.util.*;
public class Hungary {
    public static int N = 1010,M = 100010,idx = 0;
    public static int[] h = new int[N],ne= new int[M],e = new int[M],st = new int[N],match = new int[N];
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void add(int a,int b)
    {
        e[idx] = b;
        ne[idx] = h[a];
        h[a] = idx++;
    }
    public static boolean find(int x)
    {
        //遍历所有看对眼的
        for(int i = h[x];i != -1;i = ne[i])
        {
            int u = e[i];
            //如果没有访问过
            if(st[u] == 0)
            {
                st[u] = 1;
                //一般判断走到find中 那么就是劝别人分手了 原配会访问到stu[u] = 1 不考虑原配的妻子，考虑别人 如果不行的话那就要算了
                if(match[u] == 0 || find(match[u]))
                {
                    match[u] = x;
                    return true;
                }
            }
        }
        //是在找不到了
        return false;
    }
    public static void main(String[] args)throws Exception {
        String[] s = br.readLine().split(" ");
        int n1 = Integer.parseInt(s[0]),n2 = Integer.parseInt(s[1]),m = Integer.parseInt(s[2]);
        Arrays.fill(h,-1);
        while((m--)!=0)
        {
            String[] s1 = br.readLine().split(" ");
            int a = Integer.parseInt(s1[0]),b = Integer.parseInt(s1[1]);
            add(a,b);
        }

        int res = 0;
        for(int i = 1;i <= n1;i++)
        {
            //st数组是每一个左边的点访问过的女孩子
            Arrays.fill(st,0);
            if(find(i)) res++;
        }
        System.out.print(res);
    }
}

