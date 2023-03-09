package com.zgy.learn.algorithm.number_thoery;


//扩展欧几里得算法 解决下面的定理
//裴蜀定理：对于任意正整数 a,b 那么一定存在非零整数 x,y 使得a*x + b*y = (a,b)(最大公约数) 也是a和b的最大公约数给定一个xy能凑出来最小的整数
//ax + by = d => (a,b)|d

import java.io.*;
import java.util.*;

public class ExtentionEuclid {
    static int[] x = new int[1],y = new int[1];
    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    public static int exgcd(int a,int b,int[] x,int[] y) {
        if(b == 0) {
            x[0] = 1;
            y[0] = 0;
            return a;
        }
        int d=exgcd(b, a%b, x, y);
        int temp = x[0];
        x[0] = y[0];
        y[0] = (temp - a/b * y[0]);
        return d;
    }
    public static void main(String[] args) throws Exception {
        int n = Integer.parseInt(in.readLine());
        while (n--!=0) {
            String[] s = new String[2];
            s = in.readLine().split(" ");
            x[0] = 0;y[0] = 0;
            exgcd(Integer.parseInt(s[0]), Integer.parseInt(s[1]), x, y);
            System.out.println(x[0]+" "+y[0]);
        }
    }

}
