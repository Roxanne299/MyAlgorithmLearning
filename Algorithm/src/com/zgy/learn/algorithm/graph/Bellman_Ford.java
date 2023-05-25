package com.zgy.learn.algorithm.graph;


import java.io.*;
import java.util.*;

//���ں��и�Ȩֵ�ı� ʱ�临�Ӷ���nm n��ѭ��������������n���ߵ����· �����n��ѭ�����и��� �Ǿʹ��ڸ���
public class Bellman_Ford {
    public static int N = 510,M = 100000,n = 0,m = 0,k = 0;
    public static int[] d = new int[N];
    public static Pair[] edge = new Pair[M];
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public  static void bellman_ford()
    {
        Arrays.fill(d,0x3f3f3f3f);
        d[1] = 0;
        for(int i = 0;i < k;i++)
        {
            //��ֹ��ߵı߱�ǰ����µı߸��� �Ǿ���һ��ѭ��������������
            int[] copy = Arrays.copyOf(d,d.length);
            for(int j = 0;j < m;j++)
            {
                Pair u = edge[j];
                d[u.b] = Math.min(d[u.b],copy[u.a] + u.x);
            }
        }
    }
    public static void main(String[] args)throws Exception {
        String[] s1 = br.readLine().split(" ");
        n = Integer.parseInt(s1[0]);m = Integer.parseInt(s1[1]);k = Integer.parseInt(s1[2]);
        for(int i = 0;i < m;i++)
        {
            String[] s2 = br.readLine().split(" ");
            int a = Integer.parseInt(s2[0]),b = Integer.parseInt(s2[1]),x = Integer.parseInt(s2[2]);
            edge[i] = new Pair(a,b,x);
        }
        bellman_ford();
        //��Ϊ���ڸ��� �������ܱ����߸���
        if(d[n] > 0x3f3f3f3f/2) System.out.println("impossible");
        else System.out.println(d[n]);
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
            this.x = x;
        }
    }
}
