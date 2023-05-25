package com.zgy.learn.algorithm.data_structure;

import java.io.*;

public class KMP {
    //求每个模板串匹配字符串的开始的每个下标 使用KMP算法
    public static int N = 100010,M = 1000010;
    public static char[] p = new char[N],s = new char[M];
    public static int[] ne = new int[N];
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw  = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws Exception{
        int n = Integer.parseInt(br.readLine());
        String p1 = br.readLine();
        for(int i = 1;i <= n;i++) p[i] = p1.charAt(i - 1);
        int m = Integer.parseInt(br.readLine());
        String s1 = br.readLine();
        for(int i = 1;i <= m;i++) s[i] = s1.charAt(i - 1);

        //求next 双指针算法 ne数组表示模板p第i个位置其对应p[1,i] 满足前缀等于后缀最大的数字 ne数组从1开始 从2开始计算因为ne[1] = 0
        for(int i = 2,j = 0;i <= n;i++)
        {
            while(j != 0 && p[i] != p[j+1]) j = ne[j];
            if(p[i] == p[j+1]) j++;
            ne[i] = j;
        }
        //KMP
        for(int i = 1,j = 0;i <= m;i++ )
        {
            while(j != 0 && s[i] != p[j+1]) j = ne[j];
            if(s[i] == p[j+1]) j++;
            if(j == n)
            {
                bw.write( i - j + " ");
                j = ne[j];
            }
        }
        bw.flush();
    }
}
