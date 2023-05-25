package com.zgy.learn.algorithm.number_thoery;

import java.util.Arrays;

public class Approximate {
    public static int N = 100010,cnt = 0;
    public static int[] a = new int[N];

    //试除法 求约数
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

    //约数个数
    // 如果n可以分解成质因数 n = p1^k1 * p2^k2 *。。。* pn^kn 那么约数个数就是 (k1 + 1)*(k2 + 1)*...*(kn + 1) int中约数最多的个数就是1500个左右

    //约数之和
    //如果n可以分解成质因数 n = p1^k1 * p2^k2 *。。。* pn^kn 那么约数之和就是 (p1^0 + p1^1 + ... p1^k1)*(p2^0 + p2^1 + ... p2^k2)*...*(pn^0 + pn^1 + ... pn^kn)

    //最大公约数 欧几里得法/辗转相除法
    // if d|a and d|b then d|(a*x+b*y)
    //(a,b) = (b,a mod b) ()表示最大公约数计算
    //a mod b = a - (a/b) * b = a - c*b
    //(a,b) = (b,a - c*b)成立 当 b=0 那a就是最大公约数

}
