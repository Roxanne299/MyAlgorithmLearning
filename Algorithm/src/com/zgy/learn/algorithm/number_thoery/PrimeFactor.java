package com.zgy.learn.algorithm.number_thoery;

//��С����ֽ�������
public class PrimeFactor {
    public static void primeFactor(int n)
    {
        //n�д���sqrt(n)ֻ��һ�� �������Ļ� ��˾ͻ����n
        for(int i = 2;i <= n / i;i++)
        {
            int cnt = 0;
            if(n % i==0)
            {
                //Ϊʲôֱ�Ӵ�ǰ�����Գ� �Ͳ���ѭ�������� ��֤ȫ��������
                //��Ϊn�н�����i-1֮ǰ������������ ���Ե�i������ iֻ�����Լ������� �϶�������
                n /= i;
                cnt++;
            }
            System.out.println(i +' '+ cnt);
        }
        if(n > 1)
            System.out.println(n+' '+1);
    }
}
