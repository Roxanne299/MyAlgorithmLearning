package com.zgy.learn.algorithm.graph;

import java.util.Arrays;

//ֻ��Ҫ��������ͼ�ͺ��� 1. �ڽӾ��󣺿�һ����ά����洢ͼ�����ܴ洢�ظ��ߣ� �Ƚ��˷ѿռ� �Ǻӳ���ͼ
// 2.�ڽӱ���ʵ���ǵ�����ÿһ���ڵ��϶�����һ���� ���������Ĺ�ϣ����
public class Graph {
    //��Ϊ����N-1����� ����M = 2 * N
    public static int N = 100010, M = N * 2,idx = 0;
    public static int[] h = new int[N],e = new int[M],ne = new int[M],stu = new int[N];

    //���һ��ֵ��a ָ�� b�ı�
    public static void add(int a,int b)
    {
        e[idx] = b;
        ne[idx] = h[a];
        h[a] = idx++;
    }

    //ͼ���������
    public static void dfs(int u)
    {
        stu[u]  = 1;//���һ���������ʹ�
        for(int i = h[u];h[i] != -1;i = ne[u])
        {
            int j = e[i];
            if(stu[j] == 0) dfs(j);
        }
    }

    //ͼ�Ŀ����������
    public static void bfs(int u)
    {

    }

    public static void main(String[] args) {
        Arrays.fill(h,-1);
    }

}
