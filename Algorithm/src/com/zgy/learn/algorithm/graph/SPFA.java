package com.zgy.learn.algorithm.graph;

//����㷨��ʵ���Ƕ� bellman-ford�㷨�����Ż� ʹ�ö����Ż� ֻ�б�С�ĵ�ż�������������±�ĵ� ֻ�б�С�˸��²�������
import java.io.*;
import java.util.*;

class SPFA
{
    public static int N = 150010,idx = 0,n = 0,m = 0;
    public static int[] h = new int[N], e = new int[N],ne = new int[N],w = new int[N],stu = new int[N],d = new int[N];
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void add(int a,int b,int x)
    {
        w[idx] = x;
        e[idx] = b;
        ne[idx] = h[a];
        h[a] = idx++;
    }

    public static void spfa(int x)
    {
        Arrays.fill(d,0x3f3f3f3f);
        int[] queue = new int[N];
        int tt = -1,hh = 0;
        d[1] = 0;
        queue[++tt] =  1;
        stu[1] = 1;
        while(hh <= tt)
        {
            int u = queue[hh++];
            stu[u] = 0;
            for(int i = h[u];i != -1;i = ne[i])
            {
                if(d[e[i]] > d[u] + w[i])
                {
                    d[e[i]] = d[u] + w[i];
                    //������ڶ������� �����ظ��� ��Ϊ����Ҫ����Ǳ����µĵ���Ը��º���� �ڶ�������Ϳ��Ը���
                    if(stu[e[i]] == 0)
                    {
                        stu[e[i]] = 1;
                        queue[++tt] = e[i];
                    }
                }
            }
        }
        if(d[n] > 0x3f3f3f3f/2)System.out.println("impossible");
        else System.out.println(d[n]);

    }
    public static void main(String[] args)throws Exception
    {
        String[] s1 = br.readLine().split(" ");
        n = Integer.parseInt(s1[0]);m = Integer.parseInt(s1[1]);
        Arrays.fill(h,-1);
        for(int i = 0;i < m;i++)
        {
            String[] s2 = br.readLine().split(" ");
            int a = Integer.parseInt(s2[0]),b = Integer.parseInt(s2[1]),x = Integer.parseInt(s2[2]);
            add(a,b,x);
        }
        spfa(1);
    }
}
