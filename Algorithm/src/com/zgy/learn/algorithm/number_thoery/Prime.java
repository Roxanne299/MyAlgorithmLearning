package com.zgy.learn.algorithm.number_thoery;

//ɸ 1-n ���� | ����ɸ�� | ����ɸ�� | ����ɸ��|
public class Prime {
    public static int N = 100010,cnt = 0;
    public static int[] prime = new int[N];
    public static boolean[] stu = new boolean[N];

    //����ɸ�� O(sqrt(n))
    public static boolean is_prime1(int n)
    {
        if(n < 2) return false;
        //Ϊʲô�� i <= n / i?
        //                   1. �����i * i <= n ��i�ܴ��ʱ��i * i�����
        //                   2. �����i <= sqrt(n) ÿ�ζ�Ҫ���ú��� �˷�ʱ��
        for(int i = 2;i <= n / i;i++)
            if(n % i == 0) return false;
        return true;
    }

    //����ɸ��
    public static void prime2(int n)
    {
        for(int i = 2;i <= n;i++)
        {
            if(stu[i]){
                prime[cnt++] = i;
                //�����������Ż��汾 ��Ϊ��ʵ����ͨ��������ɸ ��Ϊ�����϶��Ǻ���������
                for(int j = i + i;j <= n;j += i) stu[j] = false;
            }
            //�������������ذ汾������ɸ��
            //for(int j = i + i;j <= n;j += i) stu[j] = false;
        }
    }

    //����ɸ�� ÿһ�������Ǳ��Լ�����С������ɸ����
    //1. �� i%prime[j]==0 ʱ����Ϊj�Ǵ�С����ö�ٵ� ����pjһ����i����С������ ��ȻpjҲ��i*pj����С������
    //2. �� i%prime[j]!=0 ʱ����Ϊpj����i����С������ ����pj��i����С�����ӻ�С ����pj��i*pj����С������
    //3. �Ƿ����ɸ��������С�������أ� ���� ÿ������x�������Լ�����С������pj ��iö�ٵ� x/pj һ���ᱻɸ��
    //4. Ϊʲôѭ��ֻ�� n/i ��Ϊ��Ȼ�ͳ�����
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
