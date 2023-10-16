package com.zgy.learn.algorithm.data_structure;

import java.util.List;

//线段树模板
public class SegmentTree {
    public static int N = 200010;
    public static Node[] node = new Node[4*N];

    public static void pushup(int u){
        node[u].max = Math.max(node[u<<1].max,node[u<<1|1].max);
    }

    public static void build(int u,int l,int r){
        node[u] = new Node(l,r,0);
        if(node[u].l == node[u].r) return;
        int mid = (l + r) >> 1;
        build(u<<1,l,mid);
        build(u<<1|1,mid+1,r);
        pushup(u);
    }

    public static int query(int u,int l,int r){
        int mid = (node[u].l + node[u].r) >> 1,v = 0;
        if(node[u].l >= l && node[u].r <= r) return node[u].max;
        if(l <= mid) v = query(u<<1,l,r);
        if(r > mid) v = Math.max(v,query(u<<1|1,l,r));
        return v;
    }
    /*query 不是这么写的
        public static int query(int u,int l,int r){
        int mid = (l + r) >> 1,v = 0;
        if(node[u].l >= l && node[u].r <= r) return node[u].max;
        if(node[u].l <= mid) v = query(u<<1,l,r);
        if(node[u].r > mid) v = Math.max(v,query(u<<1|1,l,r));
        return v;
    }*/

    public static void modify(int u,int x,int v){
        if(node[u].l == x && node[u].r == x) node[u].max = v;
        else{
            int mid = (node[u].l + node[u].r) >> 1;
            if(x <= mid) modify(u<<1,x,v);
            else modify(u<<1|1,x,v);
            pushup(u);
        }
    }

    static class Node{
        int l;
        int r;
        int max;
        public Node(int l,int r,int max){
            this.l = l;
            this.r = r;
            this.max = max;
        }
    }

}
