package com.zgy;


import java.io.*;
import java.util.*;

class Main{
    public static int N = 20,n,l,r,x,res;
    public static int[] a = new int[N],stu = new int[N],k1 = new int[N];
    public static Scanner sc = new Scanner(System.in);

    public static void dfs(temp t,int k,int res1){
        System.out.println(res1);
        int max = t.max,min = t.min,cnt = t.cnt;
        if((cnt > 1 && max - min < x) || ( cnt + n - k) < 2 || (cnt > 0 && !(res1 >= l && res1 <= r)) ) return ;
        if(k == n){
            res ++;
            return;
        }
        for(int i = k;i < n;i++){
            if(stu[i] == 0){
                stu[i] = 1;
                dfs(t,k+1,res1);
                k1[i] = 1;
                dfs(new temp(Math.max(a[i],max),Math.min(min,a[i]),cnt+1),k+1,res1+a[i]);
                k1[i] = 0;
                stu[i] = 0;
            }
        }
    }

    public static void main(String[] args){
        n = sc.nextInt();l = sc.nextInt();r = sc.nextInt();x = sc.nextInt();
        for(int i = 0;i < n;i++) a[i] = sc.nextInt();
        dfs(new temp(0,(int)1e9 + 1,0),0,0);
        System.out.print(res);
    }

    static class temp{
        int max;
        int min;
        int cnt;
        public temp(int max,int min,int cnt){
            this.min = min;
            this.max = max;
            this.cnt = cnt;
        }
    }

}