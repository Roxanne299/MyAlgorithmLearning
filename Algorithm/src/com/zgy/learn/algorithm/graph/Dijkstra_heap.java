package com.zgy.learn.algorithm.graph;

import java.io.*;
import java.util.*;

class Dijkstra_heap
{
    public static int N = 150010,idx = 0,n = 0,m = 0;
    public static int[] h = new int[N], e = new int[N],ne = new int[N],w = new int[N],stu = new int[N];
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void add(int a,int b,int x)
    {
        w[idx] = x;
        e[idx] = b;
        ne[idx] = h[a];
        h[a] = idx++;
    }

    public static int dijkstra(int x)
    {
        PriorityQueue<Pair> heap = new PriorityQueue<Pair>(new Comparator<Pair>(){
            public int compare(Pair o1,Pair o2)
            {
                return o1.a - o2.a;
            }
        });
        int[] d = new int[N];
        Arrays.fill(d,0x3f3f3f3f);
        d[1] = 0;
        heap.add(new Pair(0,1));
        while(!heap.isEmpty())
        {
            Pair u = heap.poll();
            int w1 = u.a, k = u.b;
            if(stu[k] == 1) continue;
            stu[k] = 1;
            for(int i = h[k];i != -1;i = ne[i])
            {
                if(d[e[i]] > w1 + w[i])
                {
                    d[e[i]] = w1 + w[i];
                    heap.add(new Pair(d[e[i]],e[i]));
                }
            }
        }
        if(d[n] == 0x3f3f3f3f) return -1;
        else return d[n];

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
        System.out.print(dijkstra(1));
    }
    static class Pair
    {
        int a;
        int b;
        public Pair(int a,int b)
        {
            this.a = a;
            this.b = b;
        }
    }
}
