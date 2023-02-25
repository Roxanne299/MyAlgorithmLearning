package com.zgy;
import java.io.*;
import java.util.*;
/*
����һ���Ǹ��������� a
����ʼ����Ϊ N
�������г��Ȳ����� M
 �������������У��ҳ����������͵����ֵ��
����������ͼ�Ϊ������������Ԫ�ذ�λ���õ��Ľ����
ע�⣺���������Ϊ�ա�
�����ʽ
��һ�а����������� N,M
�ڶ��а��� N
 �����������е� i
 ��Ϊ ai
�����ʽ
������Եõ������������͵����ֵ��
���ݷ�Χ
���� 20%
 �����ݣ�1��M��N��100
���� 50%
 �����ݣ�1��M��N��1000
���� 100%
 �����ݣ� 1��M��N��105,0��ai��231?1

����������
3 2
1 2 4
���������
6
 */
class Main{
    public static int N = 1510,cnt = 0;
    public static int[] prime = new int[N],k = new int[N];
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void getApproximate(int a,int b){

        for(int i = 2;i <= a / i;i++){
            int temp = 0;
            if(a % i == 0 ){
                prime[cnt] = i;
                a /= i;
                temp++;
            }
            if(temp != 0) k[cnt++] = temp * b;
        }
        if(a > 1){
            prime[cnt] = a;
            k[cnt++] = b;
        }
    }
    public static void main(String[] args)throws Exception {
        String[] s = br.readLine().split(" ");
        int a = Integer.parseInt(s[0]),b = Integer.parseInt(s[1]);
        getApproximate(a,b);
        int res = 1;
        for(int i = 0;i < cnt;i++){
            int temp = 0,u = 1;
            for(int j = 0;j <= k[i];j++){
                temp = (temp + u)%9901;
                u = (u * prime[i]) % 9901;
            }
            res = (res * temp) % 9901;
        }
        System.out.print(res);
    }

}