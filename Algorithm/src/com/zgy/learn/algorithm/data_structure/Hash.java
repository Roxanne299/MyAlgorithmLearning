package com.zgy.learn.algorithm.data_structure;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

//将一些10^9的数字映射到10^5数组中存储，哈希函数使用求余，模数的选择最好是大于10^5的第一个质数，这样可以很好的避免冲突
public class Hash {
//拉链法
//    public static int N = 100003,idx = 0;
//    //h数组中存放对应余数的拉链地址，e存放数字，ne存放e中的数字下一个坐标
//    public static int[] h = new int[N],e = new int[N],ne = new int[N];
//    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//
//    public static void insert(int x)
//    {
//        int u = ((x % N) + N) % N;//因为（-3 % 4） = -3 这样保证求余数是正数
//        e[idx] = x;
//        ne[idx] = h[u];
//        h[u] = idx++;
//    }
//
//    public static String find(int x)
//    {
//        int u = ((x % N) + N) % N;
//        for(int i = h[u];i != -1;i = ne[i])
//        {
//            if(e[i] == x) return "Yes";
//        }
//        return "No";
//
//    }
//    public static void main(String[] args)throws Exception {
//        int n = Integer.parseInt(br.readLine());
//        Arrays.fill(h,-1);
//        while((n--)!=0)
//        {
//            String[] s = br.readLine().split(" ");
//            if(s[0].equals("I")) insert(Integer.parseInt(s[1]));
//            else System.out.println(find(Integer.parseInt(s[1])));
//        }
//    }


//开放寻址法  一般N会开成两倍 离200000 最近的质数就是200003

    public static int N = 200003,idx = 0;
    //h数组中存放对数字
    public static long[] h = new long[N];
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    //find 方法是最主要的 当当前数字不存在 那么会返回当前数字能插入的地方，如果存在就返回下标
    public static int find(int x)
    {
        int u = ((x % N) + N) % N,k = 0;
        while(h[k] != (long)1e9 + 1 && h[k] != (long)x)
        {

            k++;
            if(k == N) k = 0;
        }
        return k;

    }
    public static void main(String[] args)throws Exception {
        int n = Integer.parseInt(br.readLine());
        Arrays.fill(h,(long)1e9 + 1);
        while((n--)!=0)
        {
            String[] s = br.readLine().split(" ");
            int u = find(Integer.parseInt(s[1]));
            if(s[0].equals("I")) h[u] = Integer.parseInt(s[1]);
            else bw.write(h[u] != (long)1e9 + 1 ? "Yes": "No");
        }
        bw.flush();
    }
}
