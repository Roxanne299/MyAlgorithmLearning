package com.zgy.learn.algorithm.number_thoery;

//欧拉函数 fi(n) 求1-n中同n互质的个数的个数
//比如fi(6) = 2 (1,5)  gcd(n,x) = 1

//如果 N = p1^k1 * p2^k2 * ... * pn^kn
// fi(N) = N * (1 - 1/p1) * (1 - 1/p2) * ... * (1 - 1/pn)
//容斥原理：1.减去N中p1，p2...pn的倍数
//2. 加上所有两个质数的倍数的个数
//3........
// N - N/p1 - N/p2 - ... - N/pk + N/(p1*p2) + ... + N/(pk*pn) - (-1)^n*(N/(p1*p2*...*pn)) = N * (1 - 1/p1) * (1 - 1/p2) * ... * (1 - 1/pn)
public class EulerFunction {



//线性筛法求欧拉函数如果是求1-n中每一个的欧拉函数 那么就是O(n*sqrt(n)) 线性筛法欧拉函数可以变成O(n)
    public static int N = 100010,cnt=0;
    public static int[] euler = new int[N],prime = new int[N];
    public static boolean[] stu = new boolean[N];

    //和线性筛法的思路一样 每个数字的欧拉函数是由自己的最小质因数的欧拉函数来更新的
    public static void get_euler(int n)
    {
        euler[1] = 1;
        for(int i = 2;i <= n;i++)
        {
            if(!stu[i]){
                //如果是质数，那么euler[i] = i-1;
                prime[cnt++] = i-1;
                euler[i] = euler[i-1];
            }
            for(int j = 0;prime[j] <= n/i;j++)
            {
                stu[i*prime[j]] = true;
                if(i % prime[j]==0){
                    //如果pj是i的质因数 所以不用*(1-1/pj) 只用*pj
                    euler[i*prime[j]] = euler[i]*prime[j];
                    break;
                }else {
                    //如果pj不是i的质因数 所以用*(1-1/pj)*pj
                    euler[i*prime[j]] = euler[i]*(prime[j]-1);
                }
            }
        }
    }
}
//欧拉定理：若a 与 n互质，则有a^fi(n) mod n = 1
//费马定理：如果a 与 n互斥 且n为质数，那么a^(n-1) = 1(mod n)