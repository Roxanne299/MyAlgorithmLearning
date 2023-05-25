package com.zgy.learn.algorithm.graph;
//���ҽ���ͼ�к��������� ��ô�Ͳ��Ƕ���ͼ������ͼ�����Խ�ͼ�����е��Ϊ�������ϣ�ͼ�еı߶�����������֮�� �����ڲ����ڱߣ�
//���ҽ���ͼ�к��������� ��ô�Ͳ��Ƕ���ͼ������ͼ�����Խ�ͼ�����е��Ϊ�������ϣ�ͼ�еı߶�����������֮�� �����ڲ����ڱߣ�
//���ҽ���ͼ�к��������� ��ô�Ͳ��Ƕ���ͼ������ͼ�����Խ�ͼ�����е��Ϊ�������ϣ�ͼ�еı߶�����������֮�� �����ڲ����ڱߣ�
import java.util.*;
import java.io.*;

public class Staining {
    public static int N = 100010,M = 200010,n = 0,m = 0,idx = 0;
    public static int[] h = new int[N],ne = new int[M],e = new int[M],color = new int[N];
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void add(int a,int b)
    {
        e[idx] = b;
        ne[idx] = h[a];
        h[a] = idx++;
    }
    public static boolean dfs(int x,int c)
    {
        color[x] = c;
        for(int i = h[x];i != -1;i = ne[i])
        {
            int u = e[i];
            if(color[u]==0)
            {
                if(!dfs(u,3-c)) return false;
            }
            else if(color[u] == c) return false;
        }
        return true;
    }

    public static void main(String[] args)throws Exception {
        Arrays.fill(h,-1);
        String[] s1 = br.readLine().split(" ");
        n = Integer.parseInt(s1[0]); m = Integer.parseInt(s1[1]);
        while((m--)!=0)
        {
            String[] s2 = br.readLine().split(" ");
            int a = Integer.parseInt(s2[0]),b = Integer.parseInt(s2[1]);
            add(a,b);
            add(b,a);
        }

        for(int i = 1;i <= n;i++)
            if(color[i] == 0)
                if(dfs(i,1)==false)
                {
                    System.out.println("No");
                    return;
                }
        System.out.println("Yes");
    }
}
