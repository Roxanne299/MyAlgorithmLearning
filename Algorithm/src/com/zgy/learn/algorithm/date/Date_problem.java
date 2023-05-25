package com.zgy.learn.algorithm.date;

import java.io.*;
import java.util.*;

class Date_problem{
    public static Scanner sc = new Scanner(System.in);
    public static int[] m = {0,31,28,31,30,31,30,31,31,30,31,30,31};
    public static int is_leap(int year){
        if((year % 100 != 0 && year % 4 == 0)|| year % 400 == 0)
            return 1;
        else
            return 0;
    }

    public static int get_month_day(int year,int month){
        int res = m[month];
        if(month == 2)
        {
            return (res + is_leap(year));
        }
        else return res;

    }
    // 计算从1年1月1号到year年month月day号有多少天其中 1年1月1号 - 1年1月1号 有一天
    public static int get_total_day(int year,int month,int day){
        int res = 0;
        for(int i = 1;i < year;i++){
            res = res + 365 + is_leap(i);
        }
        for(int i = 1;i < month;i++){
            res += get_month_day(year,i);
        }
        res += day;
        return res;
    }
    public static void main(String[] args){
        while(sc.hasNext()){
            String s1 = sc.next();
            String s2 = sc.next();
            int year1 = Integer.parseInt(s1.substring(0,4)),year2 = Integer.parseInt(s2.substring(0,4));
            int month1 = Integer.parseInt(s1.substring(4,6)),month2 = Integer.parseInt(s2.substring(4,6));
            int day1 = Integer.parseInt(s1.substring(6,8)),day2 = Integer.parseInt(s2.substring(6,8));
            System.out.println(Math.abs(get_total_day(year1,month1,day1) - get_total_day(year2,month2,day2)) + 1);
        }

    }
}


