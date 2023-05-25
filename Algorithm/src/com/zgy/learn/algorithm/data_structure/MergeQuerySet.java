package com.zgy.learn.algorithm.data_structure;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

//��O(1)��ʱ�临�Ӷ����1. �ϲ��������� 2. �ж�����Ԫ���ǲ�����һ������
//ʹ����ά�� ÿ���ڵ�ı������Ǹ����ı��
public class MergeQuerySet
{
    public static int N = 100010;
    public static int[] p = new int[N];
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    //����x�����ڽڵ� ����·��ѹ��
    public static int find(int x)
    {
        if(p[x] != x) p[x] = find(p[x]);//·��ѹ������
        return p[x];
    }
    public static void main(String[] args) throws Exception{
        String[] s1 = br.readLine().split(" ");
        int n = Integer.parseInt(s1[0]),q = Integer.parseInt(s1[1]);
        for(int i = 1;i <= n;i++) p[i] = i; //��ʼ�����鼯 ÿ���ڵ�����ڶ����Լ� Ҳ����ÿ���ڵ㶼��һ����������
        while((q--)!=0)
        {

            String[] s2 = br.readLine().split(" ");
            int x = Integer.parseInt(s2[1]),y = Integer.parseInt(s2[2]);
            if(s2[0].equals("M"))
                p[find(y)] = find(x);
            else {
                if(find(x) == find(y))
                    System.out.println("Yes");
                else
                    System.out.println("No");
            }
        }
    }
}
