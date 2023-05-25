package com.zgy.learn.algorithm.number_thoery;

//ŷ������ fi(n) ��1-n��ͬn���ʵĸ����ĸ���
//����fi(6) = 2 (1,5)  gcd(n,x) = 1

//��� N = p1^k1 * p2^k2 * ... * pn^kn
// fi(N) = N * (1 - 1/p1) * (1 - 1/p2) * ... * (1 - 1/pn)
//�ݳ�ԭ��1.��ȥN��p1��p2...pn�ı���
//2. �����������������ı����ĸ���
//3........
// N - N/p1 - N/p2 - ... - N/pk + N/(p1*p2) + ... + N/(pk*pn) - (-1)^n*(N/(p1*p2*...*pn)) = N * (1 - 1/p1) * (1 - 1/p2) * ... * (1 - 1/pn)
public class EulerFunction {



//����ɸ����ŷ�������������1-n��ÿһ����ŷ������ ��ô����O(n*sqrt(n)) ����ɸ��ŷ���������Ա��O(n)
    public static int N = 100010,cnt=0;
    public static int[] euler = new int[N],prime = new int[N];
    public static boolean[] stu = new boolean[N];

    //������ɸ����˼·һ�� ÿ�����ֵ�ŷ�����������Լ�����С��������ŷ�����������µ�
    public static void get_euler(int n)
    {
        euler[1] = 1;
        for(int i = 2;i <= n;i++)
        {
            if(!stu[i]){
                //�������������ôeuler[i] = i-1;
                prime[cnt++] = i-1;
                euler[i] = euler[i-1];
            }
            for(int j = 0;prime[j] <= n/i;j++)
            {
                stu[i*prime[j]] = true;
                if(i % prime[j]==0){
                    //���pj��i�������� ���Բ���*(1-1/pj) ֻ��*pj
                    euler[i*prime[j]] = euler[i]*prime[j];
                    break;
                }else {
                    //���pj����i�������� ������*(1-1/pj)*pj
                    euler[i*prime[j]] = euler[i]*(prime[j]-1);
                }
            }
        }
    }
}
//ŷ��������a �� n���ʣ�����a^fi(n) mod n = 1
//���������a �� n���� ��nΪ��������ôa^(n-1) = 1(mod n)