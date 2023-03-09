package com.zgy.learn.algorithm.data_structure;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

//一个高效存储和查找字符串集合的数据结构
public class Trie {
    public static int N = 100010;
    public static int[][] son = new int[N][26];
    public static int[] cnt = new int[N];
    public static int idx;
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void insert(char[] str)
    {
        int p = 0;//这里面p = 0表示根节点
        for(int i = 0; i < str.length;i++)
        {
            int x = str[i] - 'a';
            //判断下一个节点是否存在 不存在那么就新建一个节点 节点值为idx，每个字母都不一样除非有重叠的路径 每个节点的值就是下一个节点 二维数组的第一维就是节点
            if(son[p][x]==0) son[p][x] = ++idx;
            //循环中的p代表当前节点的字母
            p = son[p][x];
        }
        //以当前节点字母结尾的字符串加一
        cnt[p]++;
    }
    public static int query(char[] str)
    {
        //从根节点开始查询
        int p = 0;
        for(int i = 0; i < str.length;i++)
        {
            int x = str[i] - 'a';
            //如果当前节点没有该字母 那就是没有这个字符串
            if(son[p][x]==0) return 0;
            //转移到下一个字母节点
            p = son[p][x];
        }
        //如果是以该字母结尾 那么cnt值肯定不是0 如果有多个相同字符串 返回的是该字符串的个数
        return cnt[p];
    }
    public static void main(String[] args)throws Exception
    {
        int q = Integer.parseInt(br.readLine());
        while((q--) != 0)
        {
            String[] s = br.readLine().split(" ");
            //字符串判断不要用== 用equals方法
            if(s[0].equals("I"))
                insert(s[1].toCharArray());
            else
                System.out.println(query(s[1].toCharArray()));
        }
        bw.flush();
    }



}
