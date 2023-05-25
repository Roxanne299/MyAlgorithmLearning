package com.zgy.learn.algorithm.data_structure;

public class SingleList {
    //单链表 主要用来存储树和图
    public static int N = 100010;
    //head表示头结点下标
    //e[i]表示节点i的值
    //ne[i]表示节点i的next指针是多少
    //idx 存储当前用到了哪个点
    public static int head,idx;
    public static int[] e = new int[N],ne = new int[N];

    //初始化
    public static void init()
    {
        head = -1;
        idx = 0;
    }

    //将x插入头结点
    public static void add_to_head(int x)
    {
        e[idx] = x;
        ne[idx] = head;
        head = idx;
        idx++;
    }

    //将x插到下标是k的后面
    public static void add(int x,int k)
    {
        e[idx] = x;
        ne[idx] = ne[k];
        ne[k] = idx;
        idx++;
    }

    //将下标是k的点后面的点删掉
    public static void remove(int k){
        ne[k] = ne[ne[k]];
    }



}
