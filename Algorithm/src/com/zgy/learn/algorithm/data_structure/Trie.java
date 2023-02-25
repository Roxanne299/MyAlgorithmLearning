package com.zgy.learn.algorithm.data_structure;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

//һ����Ч�洢�Ͳ����ַ������ϵ����ݽṹ
public class Trie {
    public static int N = 100010;
    public static int[][] son = new int[N][26];
    public static int[] cnt = new int[N];
    public static int idx;
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void insert(char[] str)
    {
        int p = 0;//������p = 0��ʾ���ڵ�
        for(int i = 0; i < str.length;i++)
        {
            int x = str[i] - 'a';
            //�ж���һ���ڵ��Ƿ���� ��������ô���½�һ���ڵ� �ڵ�ֵΪidx��ÿ����ĸ����һ���������ص���·�� ÿ���ڵ��ֵ������һ���ڵ� ��ά����ĵ�һά���ǽڵ�
            if(son[p][x]==0) son[p][x] = ++idx;
            //ѭ���е�p����ǰ�ڵ����ĸ
            p = son[p][x];
        }
        //�Ե�ǰ�ڵ���ĸ��β���ַ�����һ
        cnt[p]++;
    }
    public static int query(char[] str)
    {
        //�Ӹ��ڵ㿪ʼ��ѯ
        int p = 0;
        for(int i = 0; i < str.length;i++)
        {
            int x = str[i] - 'a';
            //�����ǰ�ڵ�û�и���ĸ �Ǿ���û������ַ���
            if(son[p][x]==0) return 0;
            //ת�Ƶ���һ����ĸ�ڵ�
            p = son[p][x];
        }
        //������Ը���ĸ��β ��ôcntֵ�϶�����0 ����ж����ͬ�ַ��� ���ص��Ǹ��ַ����ĸ���
        return cnt[p];
    }
    public static void main(String[] args)throws Exception
    {
        int q = Integer.parseInt(br.readLine());
        while((q--) != 0)
        {
            String[] s = br.readLine().split(" ");
            //�ַ����жϲ�Ҫ��== ��equals����
            if(s[0].equals("I"))
                insert(s[1].toCharArray());
            else
                System.out.println(query(s[1].toCharArray()));
        }
        bw.flush();
    }



}
