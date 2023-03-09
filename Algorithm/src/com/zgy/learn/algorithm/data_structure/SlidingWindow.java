package com.zgy.learn.algorithm.data_structure;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class SlidingWindow {
    //    你的任务是确定滑动窗口位于每个位置时，窗口中的最大值和最小值。
    public static int N = 1000010;
    public static int[] q = new int[N],a = new int[N];
    public static int hh = 0,tt = -1;
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws Exception{
        String[] s1 = br.readLine().split(" ");
        int n = Integer.parseInt(s1[0]),windows = Integer.parseInt(s1[1]);
        String[] s2 = br.readLine().split(" ");
        for(int i = 0;i < n;i++) a[i] = Integer.parseInt(s2[i]);
        for(int i = 0;i < n;i++)
        {
            //移动对头 但是因为最开始是不用移动的 需要判断 队列的头结点其实基本上不会被删掉 如果被删掉了 其实也不会实现hh++操作 删掉了就说明 最小的就是新加的 也就是x
            if(hh <= tt && i - q[hh] + 1> windows) hh++;
            //优化就是 当前面的元素比后面大 那么他就不会有用 因为前面的先划走
            while(hh <= tt && a[q[tt]] > a[i]) tt--;
            q[++tt] = i;
            if(i >= windows - 1) System.out.print(a[q[hh]]+" ");
        }
        System.out.println();
        hh = 0;
        tt = -1;
        for(int i = 0;i < n;i++)
        {
            //移动对头 但是因为最开始是不用移动的 需要判断 队列的头结点其实基本上不会被删掉 如果被删掉了 其实也不会实现hh++操作 删掉了就说明 最小的就是新加的 也就是x
            if(hh <= tt && i - q[hh] + 1> windows) hh++;
            //优化就是 当前面的元素比后面小 那么他就不会有用 因为前面的先划走
            while(hh <= tt && a[q[tt]] < a[i]) tt--;
            q[++tt] = i;
            if(i >= windows - 1) System.out.print(a[q[hh]]+" ");
        }
    }

}
