package com.zgy;


import java.io.*;
import java.util.*;

class Main{
    public static int N = 110,x = 0;
    public static String t;
    public static int[] l = new int[N],r = new int[N];
    public static Scanner sc = new Scanner(System.in);

    public static void dfs(int parent,int leaf){
        if(t.charAt(x) == '#') return ;
        int k = (int)(t.charAt(x++) - 'a' + 1);
        if(leaf == 0){l[parent] = k; System.out.printf("l[%d] = %d\n",parent,k);}
        if(leaf == 1) {r[parent] = k;System.out.printf("r[%d] = %d\n",parent,k);}
        dfs(k,0);
        dfs(k,1);
    }
    public static void bfs(int x){
        if(x == -1) return;
        bfs(l[x]);
        System.out.print((char)('a' + x - 1)+" ");
        bfs(r[x]);
    }
    public static void main(String[] args)throws Exception{
        t = sc.nextLine();
        Arrays.fill(l,-1);
        Arrays.fill(r,-1);
        dfs(0,0);
        bfs(l[0]);
    }
}