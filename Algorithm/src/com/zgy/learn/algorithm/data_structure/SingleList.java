package com.zgy.learn.algorithm.data_structure;

public class SingleList {
    //������ ��Ҫ�����洢����ͼ
    public static int N = 100010;
    //head��ʾͷ����±�
    //e[i]��ʾ�ڵ�i��ֵ
    //ne[i]��ʾ�ڵ�i��nextָ���Ƕ���
    //idx �洢��ǰ�õ����ĸ���
    public static int head,idx;
    public static int[] e = new int[N],ne = new int[N];

    //��ʼ��
    public static void init()
    {
        head = -1;
        idx = 0;
    }

    //��x����ͷ���
    public static void add_to_head(int x)
    {
        e[idx] = x;
        ne[idx] = head;
        head = idx;
        idx++;
    }

    //��x�嵽�±���k�ĺ���
    public static void add(int x,int k)
    {
        e[idx] = x;
        ne[idx] = ne[k];
        ne[k] = idx;
        idx++;
    }

    //���±���k�ĵ����ĵ�ɾ��
    public static void remove(int k){
        ne[k] = ne[ne[k]];
    }



}
