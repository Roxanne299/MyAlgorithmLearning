package com.zgy;
import java.io.*;
import java.util.*;
/*
给定一个非负整数数列 a
，初始长度为 N
请在所有长度不超过 M
 的连续子数组中，找出子数组异或和的最大值。
子数组的异或和即为子数组中所有元素按位异或得到的结果。
注意：子数组可以为空。
输入格式
第一行包含两个整数 N,M
第二行包含 N
 个整数，其中第 i
 个为 ai
输出格式
输出可以得到的子数组异或和的最大值。
数据范围
对于 20%
 的数据，1≤M≤N≤100
对于 50%
 的数据，1≤M≤N≤1000
对于 100%
 的数据， 1≤M≤N≤105,0≤ai≤231?1

输入样例：
3 2
1 2 4
输出样例：
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