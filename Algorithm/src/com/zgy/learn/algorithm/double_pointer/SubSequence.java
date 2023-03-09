package com.zgy.learn.algorithm.double_pointer;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class SubSequence {
    //еп╤овспРап
    public static int N = 100010;
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int[] a1 = new int[N],a2 = new int[N];

    public static void main(String[] args) throws  Exception{
        String[] s1 = br.readLine().split(" ");
        int n = Integer.parseInt(s1[0]),m = Integer.parseInt(s1[1]);
        String[] s2 = br.readLine().split(" ");
        String[] s3 = br.readLine().split(" ");
        for(int i = 0;i < n; i++) a1[i] = Integer.parseInt(s2[i]);
        for(int i = 0;i < m; i++) a2[i] = Integer.parseInt(s3[i]);

        int i = 0,j = 0;
        for(;j < m && i < n;j++)
        {
            if(i < n && a1[i] == a2[j]) i++;

        }
        if(i == n) System.out.println("Yes");
        else System.out.println("No");
    }


}
