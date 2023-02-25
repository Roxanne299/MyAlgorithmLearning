package com.zgy.learn.algorithm.number_thoery;

//快速幂 能够快速算出来a^k mod p （1<a,p,k<10^9） O(logk)
//核心思路就是 算出来 a^2^0 mod p,a^2^1 mod p,a^2^2 mod p....,a^2^logk mod p 的结果
// a^k = a^2^x1 * a^2^x2 *...* a^2^xt = a^(2^x1 + 2^x2+...+2^xt)  其实就是把k表示成2进制 然后用算出来的值拼k
// a^2^i mod p= (a^2^(i-1) mod p)^2 mod p    (a * b) % p = (a % p * b % p) % p
//当p和n互质 a*p mod n = b*p mod n =>  a mod n = b mod n
public class FastPower {
    public static int N = 100010,M = 41;

    public static int[] a = new int[N],b = new int[N],p = new int[N],x = new int[M];

    public static int qmi(int a,int k,int p)
    {
        int res = 1;
        while(k!=0){
            if((k & 1) == 1) res = (int)((long)(res * a) % p);
            k>>=1;
            a = (int)((long)(a*a)%p);
        }
        return res;
    }

}
//逆元 若整数 b，m互质，并且对于任意的整数 a，如果满足 b|a，则存在一个整数 x，使得 a/b≡a×x(modm)，则称 x 为 b 的模 m 乘法逆元，记为 b?1(mod m)。
//b 存在乘法逆元的充要条件是 b 与模数 m 互质。当模数 m 为质数时，bm?2 即为 b 的乘法逆元。
// 如何求逆元呢？
// a/b = a*x (mod m) => a = a*x*b (mod m)  => 1 = b*x (mod m) 其实就是欧拉定理 和飞马定理
