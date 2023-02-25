package com.zgy.learn.algorithm.graph;

import java.util.Arrays;

//只需要考虑有向图就好了 1. 邻接矩阵：开一个二维数组存储图（不能存储重复边） 比较浪费空间 是河稠密图
// 2.邻接表：其实就是单链表每一个节点上都开了一个表 和拉链法的哈希表差不多
public class Graph {
    //因为含有N-1条向边 所以M = 2 * N
    public static int N = 100010, M = N * 2,idx = 0;
    public static int[] h = new int[N],e = new int[M],ne = new int[M],stu = new int[N];

    //添加一条值从a 指向 b的边
    public static void add(int a,int b)
    {
        e[idx] = b;
        ne[idx] = h[a];
        h[a] = idx++;
    }

    //图的深度搜索
    public static void dfs(int u)
    {
        stu[u]  = 1;//标记一下这个点访问过
        for(int i = h[u];h[i] != -1;i = ne[u])
        {
            int j = e[i];
            if(stu[j] == 0) dfs(j);
        }
    }

    //图的宽度优先搜索
    public static void bfs(int u)
    {

    }

    public static void main(String[] args) {
        Arrays.fill(h,-1);
    }

}
