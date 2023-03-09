package com.zgy.learn.algorithm.data_structure;

import java.beans.Introspector;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MonotonicStack {
    //Ѱ��������ÿһ��������ߵ�һ������С�ģ�˼�룺ջ�ڴ洢������������е����֣���������д���ai > aj ���� i < j ��ôai���Բ������
    public static int N = 100010,tt;
    public static int[] stk = new int[N];
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws Exception{
        int n = Integer.parseInt(br.readLine());
        String[] s = br.readLine().split(" ");
        for(int i = 0;i < n;i++)
        {
            int x = Integer.parseInt(s[i]);
            while((tt != 0)&&(x <= stk[tt])) tt--;
            if(tt != 0) System.out.print(stk[tt]+" ");
            else System.out.print("-1 ");
            stk[++tt] = x;
        }
    }
}
