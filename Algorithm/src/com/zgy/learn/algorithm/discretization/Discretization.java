package com.zgy.learn.algorithm.discretization;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

//�ٶ���һ�����޳������ᣬ������ÿ�������ϵ������� 0��
//
//���ڣ��������Ƚ��� n �β�����ÿ�β�����ĳһλ�� x �ϵ����� c��
//
//������������ m ��ѯ�ʣ�ÿ��ѯ�ʰ����������� l �� r������Ҫ��������� [l,r] ֮����������ĺ͡�
public class Discretization {
    public static int N = 300010;//��ΪҪ�����ѯ��l��r�����в����һ��x ÿ������10^5
    public static int[] a = new int[N],s = new int[N];
    public static List<Paris> add =  new ArrayList<Paris>(),query = new ArrayList<Paris>();
    public static List<Integer> all = new ArrayList<Integer>();
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws Exception{
        String[] s1 = br.readLine().split(" ");
        int n = Integer.parseInt(s1[0]),m = Integer.parseInt(s1[1]);
        for(int i = 0; i < n;i++)
        {
            String[] s2 = br.readLine().split(" ");
            int index = Integer.parseInt(s2[0]),x = Integer.parseInt(s2[1]);
            add.add(new Paris(index,x));
            all.add(index);
        }
        for(int i = 0; i < m;i++)
        {
            String[] s3 = br.readLine().split(" ");
            int l = Integer.parseInt(s3[0]),r = Integer.parseInt(s3[1]);
            query.add(new Paris(l,r));
            all.add(l);
            all.add(r);
        }
        Collections.sort(all);
        int allSize = unique(all) + 1;
        for(int i = 1; i <=add.size();i++)
        {
            int addIndex = findX(all,add.get(i - 1).x);
            a[addIndex] += add.get(i - 1).y;
        }
        for(int i = 1;i <=allSize;i++) s[i] = s[i-1] + a[i];

        for(int i = 0; i < m;i++)
        {
            int l = findX(all,query.get(i).x);
            int r = findX(all,query.get(i).y);
            System.out.println(s[r] - s[l - 1]);
        }
    }
    //���ֲ��ҵ�һ����x������
    public static int findX(List<Integer> a,int x)
    {
        int l = 0,r = a.size() - 1;
        while(l < r)
        {
            int mid = (l + r + 1)/2;
            if(a.get(mid) > x) r = mid - 1;
            else l = mid;
        }
        //Ϊ�˸���ǰ׺�� �����±��һ
        return l + 1;
    }
    //����֮��ȥ�� �������һ���±�
    public static int unique(List<Integer> a)
    {
        int i = 0,j = 1;
        for(i = 0,j = 1;j < a.size();i++,j++)
        {
            while(j < a.size()&&a.get(i)==a.get(j)) j++;
            a.set(i+1,a.get(j));
        }
        int k = a.size() - i -1;
        while((k--)!=0)
            a.remove(i+1);

        return i ;
    }
    static class Paris{
        public int x;
        public int y;
        public Paris(int x,int y){
            this.x = x;
            this.y = y;
        }
        public int getX() {return x;}
        public void setX(int x) {this.x = x;}
        public int getY() {return y;}
        public void setY(int y) {this.y = y;}
    }
}
