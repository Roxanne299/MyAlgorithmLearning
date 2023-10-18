package com.zgy;

import java.io.*;
import java.util.*;

class Main{
    public static int N = 1010;
    public static Operation[] operations = new Operation[N*2];
    public static Node[] tr = new Node[N*8];
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static List<Double> ys = new LinkedList<>();
    //给ys去重 返回去重后长度
    public static void unique(){
        int j = 0;
        for(int i = 0;i < ys.size();i++)
            if(!ys.get(j).equals(ys.get(i))) ys.set(++j,ys.get(i));

        for(int i = j+1;i < ys.size();i++) ys.remove(j);
    }

    public static void pushup(int u){
        if(tr[u].cnt > 0) return;
        if(tr[u].cnt == 0) tr[u].len = tr[u<<1].len + tr[u<<1|1].len;
    }

    public static void build(int u,int l,int r){
        tr[u] = new Node(l,r,0,0);
        if(l == r)  return;
        int mid = l + r >> 1;
        build(l<<1,l,mid);
        build(l<<1|1,mid+1,r);
        pushup(u);
    }

    public static void modify(int u,int l,int r,int k){
        if(tr[u].l >= l && tr[u].r <= r) {
            tr[u].cnt += k;
            tr[u].len = ys.get(r) - ys.get(l-1);
        }else{
            int mid = tr[u].l + tr[u].r >> 1;
            if(l <= mid) modify(u<<1,l,mid,k);
            if(r > mid) modify(u<<1|1,mid+1,r,k);
            pushup(u);
        }
    }

    public static double query(){
        return tr[1].len;
    }

    public static int find(double x){
        return Collections.binarySearch(ys,x);
    }
    public static void main(String[] args)throws Exception{
        int n = Integer.parseInt(br.readLine()),o = 1;
        for(int i = 1;i <= n;i++){
            String[] s1 = br.readLine().split(" ");
            double x1 = Double.parseDouble(s1[0]),y1 = Double.parseDouble(s1[1]),x2 = Double.parseDouble(s1[2]),y2 = Double.parseDouble(s1[3]);
            operations[o++] = new Operation(x1,y1,y2,1);
            operations[o++] = new Operation(x2,y1,y2,-1);
            ys.add(y1);
            ys.add(y2);
        }

        Arrays.sort(operations,1,o,new Comparator<Operation>(){
            @Override
            public int compare(Operation o1, Operation o2) {
                if(o1.x - o2.x > 0) return 1;
                else if(o1.x - o2.x == 0) return 0;
                else return -1;
            }
        });
        Collections.sort(ys);
        unique();

        //这里面减去1的原因是 我们一共有ys.size个y所以就会有ys.size-1个区间，也就是
        build(1,1,ys.size()-1);

        double res = 0;
        for(int i = 1;i <= 2*n;i++){
            if(i > 1) res += query() * (operations[i].x - operations[i-1].x);
            modify(1,find(operations[i].y1),find(operations[i].y2),operations[i].k);
        }
        System.out.println(res);
        ys.
        bw.flush();
    }

    static class Operation{
        double x;
        double y1;
        double y2;
        int k; //权值 x1 对应 1 x2 对应 -1
        public Operation(double x,double y1,double y2,int k){
            this.x = x;
            this.y1 = y1;
            this.y2 = y2;
            this.k = k;
        }
     }

     static class Node{
         int l;
         int r;
        int cnt;//本区间被覆盖了多少次
         double len;//本区间不重复被覆盖的长度
         public Node(int l,int r,int cnt,double len){
             this.l = l;
             this.r = r;
             this.cnt = cnt;
             this.len = len;
         }
     }
}