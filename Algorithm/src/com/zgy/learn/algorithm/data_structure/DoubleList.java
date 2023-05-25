package com.zgy.learn.algorithm.data_structure;

public class DoubleList {
    //e[N]代表这个节点的值
    //l[N]代表左边下标
    //r[N]代表右边下标
    //idx 代表当前下标
    public static int N = 100010;
    public static int idx;
    public static int[] e = new int[N],l = new int[N],r = new int[N];

    //初始化 0为左端点 1为右端点
    public static void init()
    {
        l[1] = 0;
        r[0] = 1;
        idx = 2;
    }

    //在k后面插入 想在k左边插入 那就是add(l[k],x)
    public static void add(int k,int x)
    {
        e[idx] = x;
        r[idx] = r[k];
        l[idx] = k;
        l[r[k]] = idx;
        r[k] = idx;
        idx++;
    }

    public static void remove(int k)
    {
        r[l[k]] = r[k];
        l[r[k]] = l[k];
    }
}
