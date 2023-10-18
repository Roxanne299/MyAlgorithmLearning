package com.zgy;

import com.sun.javafx.image.IntPixelGetter;

import java.io.*;
import java.util.*;

class Main{

    public static int N = 100010,P;
    public static Node[] tr = new Node[4*N];
    public static int[] a = new int[N];
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void pushup(int u){
        tr[u].sum = (int)((long)tr[u<<1].sum + tr[u<<1|1].sum % P);
    }

    public static void build(int u,int l,int r){
        tr[u] = new Node(l,r,0,0,1);
        if(l == r){
            tr[u].sum = a[l];
        }else{
            int mid = l + r >> 1;
            build(u<<1,l,mid);
            build(u<<1|1,mid+1,r);
            pushup(u);
        }
    }


    public static void updateNode(Node u,int mul,int add){
        u.sum = (int)((long)mul * u.sum % P + (long)add * (u.r - u.l + 1) % P) % P;
        u.add = (int)((long)u.add * mul + add % P);
        u.mul = (int)((long)u.mul * mul % P);
    }
    public static void pushdown(int u){
        updateNode(tr[u<<1],tr[u].mul,tr[u].add);
        updateNode(tr[u<<1|1],tr[u].mul,tr[u].add);
        tr[u].add = 0;
        tr[u].mul = 1;
    }
    public static void modify(int u,int l,int r,int mul,int add){
        if(tr[u].l >= l && tr[u].r <= r) {
            updateNode(tr[u],mul,add);
        }else{
            pushdown(u);
            int mid = tr[u].l + tr[u].r >> 1;
            if(l <= mid) modify(u<<1,l,r,mul,add);
            if(r > mid) modify(u<<1|1,l,r,mul,add);
            pushup(u);
        }
    }


    public static int query(int u,int l,int r){
        if(tr[u].l >= l && tr[u].r <= r) return tr[u].sum;
        else{
            pushdown(u);
            int mid = tr[u].l + tr[u].r >> 1;
            int res = 0;
            if(l <= mid) res = (int)((long)res + query(u<<1,l,r) % P);
            if(r > mid) res = (int)((long)res + query(u<<1|1,l,r) % P);
            return res;
        }
    }

    public static void main(String[] args)throws Exception{
        String[] s1 = br.readLine().split(" ");
        int n = Integer.parseInt(s1[0]);
        P = Integer.parseInt(s1[1]);
        String[] s2 = br.readLine().split(" ");
        for(int i = 1;i <= n;i++) a[i] = Integer.parseInt(s2[i-1]);

        int m = Integer.parseInt(br.readLine());
        build(1,1,n);

        while((m--)!=0){
            String[] s3 = br.readLine().split(" ");
            int op = Integer.parseInt(s3[0]),l = Integer.parseInt(s3[1]),r =Integer.parseInt(s3[2]);
            if(op == 1){
                int d = Integer.parseInt(s3[3]);
                modify(1,l,r,d,0);
            }else if(op == 2){
                int d = Integer.parseInt(s3[3]);
                modify(1,l,r,1,d);
            }else{
                System.out.println(query(1,l,r));
            }
        }

     }

     static class Node{
        int l;
        int r;
        int sum;
        int add;//懒标记
        int mul;//懒标记

         public Node(int l, int r, int sum, int add, int mul) {
             this.l = l;
             this.r = r;
             this.sum = sum;
             this.add = add;
             this.mul = mul;
         }
     }
}