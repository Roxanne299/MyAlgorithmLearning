package com.zgy.learn.algorithm.number_thoery;

//从小到大分解质因数
public class PrimeFactor {
    public static void primeFactor(int n)
    {
        //n中大于sqrt(n)只有一个 有两个的话 相乘就会大于n
        for(int i = 2;i <= n / i;i++)
        {
            int cnt = 0;
            if(n % i==0)
            {
                //为什么直接从前往后试除 就不会循环到合数 保证全是质数？
                //因为n中将所有i-1之前的质数除完了 所以当i被整除 i只能是自己的因子 肯定是质数
                n /= i;
                cnt++;
            }
            System.out.println(i +' '+ cnt);
        }
        if(n > 1)
            System.out.println(n+' '+1);
    }
}
