package com.zgy;

import java.io.*;
import java.util.*;
// n 为奇数的时候需要找到第一个以及最后一个的数字才可以递推出来
// n 为偶数的时候需要找到第二个以及倒数第二个才可以退出来
// 第一个数字总是只会出现一次的 想要分辨第一个和倒数第一个才是重点
class Main{
    public static int N = 1000010;
    public static int[] l_r = new int[N],r_l = new int[N],a = new int[200010],index = new int[100003],value = new int[100003];
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    //
    public static int find(int l){
        int x = l % 100003;
        for(;index[x] != -2 && value[x] != l;) x++;
        value[x] = l;
        return x;
    }

    public static void main(String[] args)throws Exception{
        Arrays.fill(l_r,-1);
        Arrays.fill(r_l,-1);
        Arrays.fill(value,-1);
        Arrays.fill(index,-2);
        int n = Integer.parseInt(br.readLine());
        for(int i = 1;i <=n;i++){
            String[] s = br.readLine().split(" ");
            int l = Integer.parseInt(s[0]),r = Integer.parseInt(s[1]);
            index[find(l)]++;
            index[find(r)]--;
            if(l == 0) a[2] = r;
            if(r == 0) a[n - 1] = l;
            l_r[l] = r;
            r_l[r] = l;
        }
        for(int i = 0;i < 100003;i++) {
            if(index[i] ==-1) a[1] = value[i];
            if(index[i] == -3) a[n] = value[i];
        }

        if(n % 2 == 0){
            // 正向传播
            for(int i = a[2],j = 4;l_r[i] != -1 && l_r[i] != 0;i = l_r[i],j+=2) a[j] = l_r[i];
            // 反向传播
            for(int i = a[n-1],j = n-3;r_l[i] != -1 && r_l[i] != 0;i = r_l[i],j-=2) a[j] = r_l[i];
        }
        else{
            // 正向传播
            for(int i = a[1],j = 3;l_r[i] != -1 && l_r[i] != 0;i = l_r[i],j+=2) a[j] = l_r[i];
            // 反向传播
            for(int i = a[n],j = n-2;r_l[i] != -1 && r_l[i] != 0;i = r_l[i],j-=2) a[j] = r_l[i];
        }




        for(int i = 1;i <=n;i++) bw.write(a[i]+" ");
        bw.flush();
    }
}