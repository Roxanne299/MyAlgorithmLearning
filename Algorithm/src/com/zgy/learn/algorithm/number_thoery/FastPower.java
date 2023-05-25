package com.zgy.learn.algorithm.number_thoery;

//������ �ܹ����������a^k mod p ��1<a,p,k<10^9�� O(logk)
//����˼·���� ����� a^2^0 mod p,a^2^1 mod p,a^2^2 mod p....,a^2^logk mod p �Ľ��
// a^k = a^2^x1 * a^2^x2 *...* a^2^xt = a^(2^x1 + 2^x2+...+2^xt)  ��ʵ���ǰ�k��ʾ��2���� Ȼ�����������ֵƴk
// a^2^i mod p= (a^2^(i-1) mod p)^2 mod p    (a * b) % p = (a % p * b % p) % p
//��p��n���� a*p mod n = b*p mod n =>  a mod n = b mod n
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
//��Ԫ ������ b��m���ʣ����Ҷ������������ a��������� b|a�������һ������ x��ʹ�� a/b��a��x(modm)����� x Ϊ b ��ģ m �˷���Ԫ����Ϊ b?1(mod m)��
//b ���ڳ˷���Ԫ�ĳ�Ҫ������ b ��ģ�� m ���ʡ���ģ�� m Ϊ����ʱ��bm?2 ��Ϊ b �ĳ˷���Ԫ��
// �������Ԫ�أ�
// a/b = a*x (mod m) => a = a*x*b (mod m)  => 1 = b*x (mod m) ��ʵ����ŷ������ �ͷ�����
