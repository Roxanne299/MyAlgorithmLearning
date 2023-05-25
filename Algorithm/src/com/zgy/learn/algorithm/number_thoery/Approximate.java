package com.zgy.learn.algorithm.number_thoery;

import java.util.Arrays;

public class Approximate {
    public static int N = 100010,cnt = 0;
    public static int[] a = new int[N];

    //�Գ��� ��Լ��
    public static void approximate(int n)
    {
        for (int i = 1;i <= n/2;i++)
        {
            if(n % i == 0)
            {
                a[cnt++] = i;
                if(n/i != i) a[cnt++] = n/i;
            }
        }
        Arrays.sort(a,0,cnt);
    }

    //Լ������
    // ���n���Էֽ�������� n = p1^k1 * p2^k2 *������* pn^kn ��ôԼ���������� (k1 + 1)*(k2 + 1)*...*(kn + 1) int��Լ�����ĸ�������1500������

    //Լ��֮��
    //���n���Էֽ�������� n = p1^k1 * p2^k2 *������* pn^kn ��ôԼ��֮�;��� (p1^0 + p1^1 + ... p1^k1)*(p2^0 + p2^1 + ... p2^k2)*...*(pn^0 + pn^1 + ... pn^kn)

    //���Լ�� ŷ����÷�/շת�����
    // if d|a and d|b then d|(a*x+b*y)
    //(a,b) = (b,a mod b) ()��ʾ���Լ������
    //a mod b = a - (a/b) * b = a - c*b
    //(a,b) = (b,a - c*b)���� �� b=0 ��a�������Լ��

}
