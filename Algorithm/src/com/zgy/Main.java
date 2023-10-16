package com.zgy;

import java.io.*;
import java.util.*;

class Main{
    public static int N = 100010;
    public static int[] a = new int[N];
    public static Node[] tr = new Node[4*N];
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void pushup(int u){
        tr[u].sum = tr[u<<1].sum + tr[u<<1|1].sum;
    }

    public static void build(int u,int l,int r){
        tr[u] = new Node(l,r,0,0);
        if(l == r){
            tr[u].sum = a[l];
        }else{
            int mid = l + r >> 1;
            build(u<<1,l,mid);
            build(u<<1|1,mid+1,r);
            pushup(u);
        }
    }

    public static void pushdown(int u){
        if(tr[u].add != 0){
            tr[u<<1].sum += (long)(tr[u<<1].r - tr[u<<1].l + 1) * tr[u].add;
            tr[u<<1].add += tr[u].add;
            tr[u<<1|1].sum += (long)(tr[u<<1|1].r - tr[u<<1|1].l + 1) * tr[u].add;
            tr[u<<1|1].add += tr[u].add;
            tr[u].add = 0;
        }

    }

    public static void modify(int u,int l,int r,int d){
        if(tr[u].l >= l && tr[u].r <= r){
            tr[u].sum += d * (long)(tr[u].r - tr[u].l + 1);
            tr[u].add += d;
        }else{
            //分裂的时候必须pushdown
            pushdown(u);
            int mid = tr[u].l + tr[u].r >> 1;
            if(l <= mid){
                modify(u<<1,l,r,d);
            }
            if(r > mid){
                modify(u<<1|1,l,r,d);
            }
            pushup(u);
        }
    }

    public static long query(int u,int l,int r){

        if(tr[u].l >= l && tr[u].r <= r) return tr[u].sum;
        else{
            //分裂之前pushdown
            pushdown(u);
            int mid = tr[u].l + tr[u].r >> 1;
            long res = 0;
            if(l <= mid) res += query(u<<1,l,r);
            if(r > mid) res += query(u<<1|1,l,r);
            return res;
        }
    }
    public static void main(String[] args)throws Exception{
        String[] s1 = br.readLine().split(" ");
        int n = Integer.parseInt(s1[0]),m = Integer.parseInt(s1[1]);
        String[] s2 = br.readLine().split(" ");
        for(int i = 1;i <= n;i++) a[i] = Integer.parseInt(s2[i-1]);

        Arrays.fill(tr,1,4*n,new Node(0,0,0,0));
        build(1,1,n);
        for(int i = 1;i <= m;i++){
            String[] s3 = br.readLine().split(" ");
            if(s3[0].equals("C")){
                int l = Integer.parseInt(s3[1]),r = Integer.parseInt(s3[2]),d = Integer.parseInt(s3[3]);
                modify(1,l,r,d);
            }else{
                int l = Integer.parseInt(s3[1]),r = Integer.parseInt(s3[2]);
                System.out.println(query(1,l,r));
            }
        }
    }

    static class Node{
        int l;
        int r;
        int sum;
        int add;// 懒标记
        public Node(int l,int r,int sum,int add){
            this.l = l;
            this.r = r;
            this.sum = sum;
            this.add = add;
        }
    }
}