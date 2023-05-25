package com.zgy.learn.algorithm.data_structure;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Heap {
    public static int N = 100010,size = 0,idx=0;
    public static int[] h = new int[N],hp = new int[N],ph = new int[N];
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void heap_swap(int a,int b)
    {
        //h[i] 代表堆中第i个值 hp[i]代表堆中第i个值是第几个插入的 ph[i]代表第i个插入的值在堆中是第几个
        //因为需要计算修改第i个插入的值，所以需要有一个数组ph存放第插入堆的顺序，但是修改之后还有up down操作，会涉及堆内元素交换，所以相应ph也需要交换，只知道需要修改的对内元素是第几个插入的 却不知道交换的是第几个插入，所以需要一个hp维护对内元素是第几个插入的。
        int temp = ph[hp[a]];
        ph[hp[a]] = ph[hp[b]];
        ph[hp[b]] = temp;

        temp = hp[a];
        hp[a] = hp[b];
        hp[b] = temp;

        temp = h[a];
        h[a] = h[b];
        h[b] = temp;
    }
    //向上调整操作只用和父节点比较，知道遇见比他小的父节点或者已经到头结点那就可以停下来
    public static int up(int u)
    {
        while(u/2 > 0 && h[u/2] > h[u])
        {
            heap_swap(u,u/2);
            u /= 2;
        }
        return u;
    }
    //向下调整操作每次和两个子节点比较
    public static int down(int u)
    {
        int p = u;
        if(u*2 <=size && h[u] > h[u*2]) p = 2*u;
        if(u*2+1 <= size && h[p] > h[u*2+1]) p = 2 * u + 1;
        if(p != u)
        {
            heap_swap(p,u);
            return down(p);
        }
        return p;
    }
    public static void main(String[] args) throws Exception{
        int n = Integer.parseInt(br.readLine());
        while((n--)!=0)
        {
            String[] s = br.readLine().split(" ");
            if(s[0].equals("I"))
            {
                //插入操作很简单，在堆最后面加上在up
                h[++size] = Integer.parseInt(s[1]);
                int k = up(size);
                ph[++idx] = k;
                hp[k] = idx;
            }
            else if(s[0].equals("PM")) System.out.println(h[1]);
            else if (s[0].equals("DM")) {
                //删除最小值 将堆最后一个数替换到第一个 再down
                h[1] = h[size--];
                down(1);
            } 
            else if (s[0].equals("D")) {
                //删除第k个插入数字 只用把最后一个数字将第k个插入的数字替换 在进行up down操作
                int k = Integer.parseInt(s[1]);
                heap_swap(ph[k],size--);
                up(ph[k]);
                down(ph[k]);
            } else if (s[0].equals("C")) {
                int a = Integer.parseInt(s[1]),b = Integer.parseInt(s[2]);
                h[ph[a]] = b;
                down(ph[a]);
                up(ph[a]);
            }
        }
    }
}
