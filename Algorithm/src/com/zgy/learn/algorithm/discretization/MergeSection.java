package com.zgy.learn.algorithm.discretization;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MergeSection {
    public static int N = 100010;
    public static List<Paris> a = new ArrayList<Paris>();
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws  Exception{
        int n = Integer.parseInt(br.readLine());
        for(int i = 0;i < n;i++)
        {
            String[] s = br.readLine().split(" ");
            a.add(new Paris(Integer.parseInt(s[0]),Integer.parseInt(s[1])));
        }
        a.sort(new Comparator<Paris>() {
            @Override
            public int compare(Paris o1, Paris o2) {
                return o1.x - o2.x;
            }
        });
        int st = (int)-2e9,ed = (int) -2e9;
        List<Paris> res = new ArrayList<Paris>();
        for(int i = 0; i < n;i++)
        {
            Paris seg = a.get(i);
            if(ed < seg.x)
            {
                if(st!=-2e9) res.add(new Paris(st,ed)); //保证第一个区间更新之后再加进去 这里面更新的是上一次的
                st = seg.x;ed = seg.y;
            }
            ed = Math.max(ed,seg.y);
        }
        //最后一轮没加上
        if(st!=-2e9)  res.add(new Paris(st,ed));




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
