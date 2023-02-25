package com.zgy.learn.algorithm.data_structure;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class SlidingWindow {
    //    ���������ȷ����������λ��ÿ��λ��ʱ�������е����ֵ����Сֵ��
    public static int N = 1000010;
    public static int[] q = new int[N],a = new int[N];
    public static int hh = 0,tt = -1;
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws Exception{
        String[] s1 = br.readLine().split(" ");
        int n = Integer.parseInt(s1[0]),windows = Integer.parseInt(s1[1]);
        String[] s2 = br.readLine().split(" ");
        for(int i = 0;i < n;i++) a[i] = Integer.parseInt(s2[i]);
        for(int i = 0;i < n;i++)
        {
            //�ƶ���ͷ ������Ϊ�ʼ�ǲ����ƶ��� ��Ҫ�ж� ���е�ͷ�����ʵ�����ϲ��ᱻɾ�� �����ɾ���� ��ʵҲ����ʵ��hh++���� ɾ���˾�˵�� ��С�ľ����¼ӵ� Ҳ����x
            if(hh <= tt && i - q[hh] + 1> windows) hh++;
            //�Ż����� ��ǰ���Ԫ�رȺ���� ��ô���Ͳ������� ��Ϊǰ����Ȼ���
            while(hh <= tt && a[q[tt]] > a[i]) tt--;
            q[++tt] = i;
            if(i >= windows - 1) System.out.print(a[q[hh]]+" ");
        }
        System.out.println();
        hh = 0;
        tt = -1;
        for(int i = 0;i < n;i++)
        {
            //�ƶ���ͷ ������Ϊ�ʼ�ǲ����ƶ��� ��Ҫ�ж� ���е�ͷ�����ʵ�����ϲ��ᱻɾ�� �����ɾ���� ��ʵҲ����ʵ��hh++���� ɾ���˾�˵�� ��С�ľ����¼ӵ� Ҳ����x
            if(hh <= tt && i - q[hh] + 1> windows) hh++;
            //�Ż����� ��ǰ���Ԫ�رȺ���С ��ô���Ͳ������� ��Ϊǰ����Ȼ���
            while(hh <= tt && a[q[tt]] < a[i]) tt--;
            q[++tt] = i;
            if(i >= windows - 1) System.out.print(a[q[hh]]+" ");
        }
    }

}
