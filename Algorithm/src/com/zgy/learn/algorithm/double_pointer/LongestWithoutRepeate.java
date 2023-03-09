package com.zgy.learn.algorithm.double_pointer;
import java.io.*;

public class LongestWithoutRepeate {
    //最长不重复子序列
    public static int N = 100010;
    public static int[] a = new int[N],s = new int[N];
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws Exception{
        int n = Integer.parseInt(br.readLine());
        String[] s1 = br.readLine().split(" ");
        for(int i = 0; i < n; i++) a[i] = Integer.parseInt(s1[i]);
        int res = 0;
        for(int i = 0,j = 0; i < n; i++)
        {
            s[a[i]]++;
            while(j <= i && s[a[i]] > 1)
            {
                s[a[j]]--;
                j++;
            }
            res = Math.max(res,i - j + 1);
        }
        System.out.println(res);
    }
}
