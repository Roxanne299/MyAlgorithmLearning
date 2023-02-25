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
        //�������п����۵�
        for(int i = h[x];i != -1;i = ne[i])
        {
            int u = e[i];
            //���û�з��ʹ�
            if(st[u] == 0)
            {
                st[u] = 1;
                //һ���ж��ߵ�find�� ��ô����Ȱ���˷����� ԭ�����ʵ�stu[u] = 1 ������ԭ������ӣ����Ǳ��� ������еĻ��Ǿ�Ҫ����
                if(match[u] == 0 || find(match[u]))
                {
                    match[u] = x;
                    return true;
                }
            }
        }
        //�����Ҳ�����
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
            //st������ÿһ����ߵĵ���ʹ���Ů����
            Arrays.fill(st,0);
            if(find(i)) res++;
        }
        System.out.print(res);
    }
}

