package com.zgy.learn.algorithm.data_structure;

public class DoubleList {
    //e[N]��������ڵ��ֵ
    //l[N]��������±�
    //r[N]�����ұ��±�
    //idx ����ǰ�±�
    public static int N = 100010;
    public static int idx;
    public static int[] e = new int[N],l = new int[N],r = new int[N];

    //��ʼ�� 0Ϊ��˵� 1Ϊ�Ҷ˵�
    public static void init()
    {
        l[1] = 0;
        r[0] = 1;
        idx = 2;
    }

    //��k������� ����k��߲��� �Ǿ���add(l[k],x)
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
