package com.zgy;


import java.io.*;
import java.util.*;

class Main {
    public static int N = 4010, idx = 0, n, m, root;
    public static int[] h = new int[N], e = new int[N], ne = new int[N], stu = new int[N], d = new int[N], p = new int[N];
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void insert(int a, int b) {
        e[idx] = b;
        ne[idx] = h[a];
        h[a] = idx++;
    }

    public static void bfs() {
        Pair[] q = new Pair[N];
        int hh = 0, tt = -1;
        q[++tt] = new Pair(root, 0);
        stu[root] = 1;
        while (hh <= tt) {
            Pair u = q[hh++];
            d[u.a] = u.d;
            for (int i = h[u.a]; i != -1; i = ne[i]) {
                if (stu[e[i]] == 0) {
                    p[e[i]] = u.a;
                    q[++tt] = new Pair(e[i], u.d + 1);
                    stu[e[i]] = 1;
                    System.out.println(e[i] + " ");
                }

            }
        }
    }
    public static void main(String[] args)throws Exception{
        n = Integer.parseInt(br.readLine());
        Arrays.fill(h,-1);
        Arrays.fill(d,0x3f3f3f3f);
        for(int i = 0;i < n;i++){
            String[] s1 = br.readLine().split(" ");
            int a = Integer.parseInt(s1[0]),b = Integer.parseInt(s1[1]);
            if(b == -1) {root = a; continue;}
            insert(a,b);
            insert(b,a);
        }
        bfs();
        int k = Integer.parseInt(br.readLine());
        while((k--)!=0){
            String[] s2= br.readLine().split(" ");
            int a = Integer.parseInt(s2[0]),b = Integer.parseInt(s2[1]);
            if(d[a]==0x3f3f3f3f || d[b] == 0x3f3f3f3f) System.out.println(0);
            if(d[a] > d[b]){
                while(a != b){
                    if(a == root) break;
                    a = p[a];
                }
                if(a == b) System.out.println(2);
                else System.out.println(0);
            }
            if(d[b] > d[a]) {
                while(a != b){
                    if(b == root) break;
                    b = p[b];
                }
                if(a == b) System.out.println(1);
                else System.out.println(0);
            }
        }
    }



    static class Pair {
        int a;
        int d;

        public Pair(int a, int d) {
            this.a = a;
            this.d = d;
        }
    }
}

