package com.zgy.learn.algorithm.graph;


import java.io.*;
import java.util.*;
public class Kruskal {
    public static int N = 100010,M = 200010,n = 0,m = 0;
    public static Pair[] edge = new Pair[M];
    public static int[] find = new int[N];
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static int find(int x)
    {
        if(find[x] != x) find[x] = find(find[x]);
        return find[x];
    }
    public static void main(String[] args)throws Exception {
        String[] s = br.readLine().split(" ");
        n = Integer.parseInt(s[0]);m = Integer.parseInt(s[1]);
        for(int i = 1;i <= n;i++) find[i] = i;
        for(int i = 0;i < m;i++)
        {
            String[] s1 = br.readLine().split(" ");
            int a = Integer.parseInt(s1[0]),b = Integer.parseInt(s1[1]),x = Integer.parseInt(s1[2]);
            edge[i] = new Pair(a,b,x);
        }
        Arrays.sort(edge, 0,m,new Comparator<Pair>() {
            @Override
            public int compare(Pair o1, Pair o2) {
                return o1.x - o2.x;
            }
        });
        int res = 0,cnt = 1;
        for(int i = 0;i < m;i++)
        {
            int a = find(edge[i].a),b = find(edge[i].b);
            if(a != b) {
                find[a] = b;
                res += edge[i].x;
                cnt++;
            }
        }
        if(cnt != n ) System.out.println("impossible");
        else System.out.println(res);

    }
    static class Pair
    {
        int a;
        int b;
        int x;
        public Pair(int a,int b,int x)
        {
            this.a = a;
            this.b = b;
            this.x =x;
        }
    }
}
