package com.zgy.learn.algorithm.data_structure;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

//在O(1)的时间复杂度完成1. 合并两个集合 2. 判断两个元素是不是在一个集合
//使用树维护 每个节点的变量都是根结点的编号
public class MergeQuerySet
{
    public static int N = 100010;
    public static int[] p = new int[N];
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    //返回x的祖宗节点 加上路径压缩
    public static int find(int x)
    {
        if(p[x] != x) p[x] = find(p[x]);//路径压缩操作
        return p[x];
    }
    public static void main(String[] args) throws Exception{
        String[] s1 = br.readLine().split(" ");
        int n = Integer.parseInt(s1[0]),q = Integer.parseInt(s1[1]);
        for(int i = 1;i <= n;i++) p[i] = i; //初始化并查集 每个节点的祖宗都是自己 也就是每个节点都是一个单独集合
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
