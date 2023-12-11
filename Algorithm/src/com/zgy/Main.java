package com.zgy;
import java.io.*;
import java.nio.Buffer;
import java.util.*;

class Main{
    public static int N = (int)1e7+10;
    public static int[] stu = new int[N],f = new int[N],a = new int[N];
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args)throws Exception {
        int n = Integer.parseInt(br.readLine());
        String[] s1 = br.readLine().split(" ");
        for(int i = 1;i <= n;i++) a[Integer.parseInt(s1[i-1])]++;

        for(int i = 2;i < N;i++){
            if(stu[i] == 1) continue;
            for(int j = i;j < N;j+=i){
                stu[j] = 1;
                f[i] += a[j];
            }
        }

        for(int i = 1;i < N;i++) f[i] += f[i-1];

        int m = Integer.parseInt(br.readLine());
        for(int i = 1;i <= m;i++){
            String[] s2 = br.readLine().split(" ");
            int l = Integer.parseInt(s2[0]),r = Integer.parseInt(s2[1]);
            System.out.println(f[r] - f[l-1]);
        }
    }
}