package com.zgy.learn.algorithm.number_thoery;

//筛 1-n 质数 | 朴素筛法 | 线性筛法 | 埃氏筛法|
public class Prime {
    public static int N = 100010,cnt = 0;
    public static int[] prime = new int[N];
    public static boolean[] stu = new boolean[N];

    //朴素筛法 O(sqrt(n))
    public static boolean is_prime1(int n)
    {
        if(n < 2) return false;
        //为什么是 i <= n / i?
        //                   1. 如果是i * i <= n 当i很大的时候，i * i会溢出
        //                   2. 如果是i <= sqrt(n) 每次都要调用函数 浪费时间
        for(int i = 2;i <= n / i;i++)
            if(n % i == 0) return false;
        return true;
    }

    //埃氏筛法
    public static void prime2(int n)
    {
        for(int i = 2;i <= n;i++)
        {
            if(stu[i]){
                prime[cnt++] = i;
                //放在这里是优化版本 因为其实可以通过质数来筛 因为质数肯定是合数的因子
                for(int j = i + i;j <= n;j += i) stu[j] = false;
            }
            //放在这里是朴素版本的线性筛法
            //for(int j = i + i;j <= n;j += i) stu[j] = false;
        }
    }

    //线性筛法 每一个数都是被自己的最小质因子筛掉的
    //1. 当 i%prime[j]==0 时：因为j是从小到大枚举的 所以pj一定是i的最小质因子 当然pj也是i*pj的最小质因子
    //2. 当 i%prime[j]!=0 时：因为pj不是i的最小质因子 所以pj比i的最小质因子还小 所以pj是i*pj的最小质因子
    //3. 是否可以筛掉所有最小质因子呢？ 首先 每个合数x都会有自己的最小质因子pj 当i枚举到 x/pj 一定会被筛掉
    //4. 为什么循环只到 n/i 因为不然就超出了
    public static void prime3(int n)
    {
        for(int i = 2;i <= n;i++)
        {
            if(stu[i]) prime[cnt++] = i;
            for(int j = 0;prime[j] < n / i;j++)
            {
                stu[prime[j]*i] = false;
                if(i % prime[j]==0) break;
            }
        }
    }

}
