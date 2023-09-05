package com.zgy;


import java.io.*;
import java.util.*;

class Main{
    public static int N = 500010;
    public static long res = 0;
    public static int[] a = new int[N],temp = new int[N];
    public static Scanner sc = new Scanner(System.in);


    public static void main(String[] args){
        String s = sc.next();
        int mn = 0,ln = 0,sn = 0,l = 0,m = 0;
        for(int i = 1;i <= s.length();i++){
            char temp = s.charAt(i-1);
            if(temp == 'L') ln++;
            if(temp == 'S') sn++;
            if(temp == 'M') mn++;
        }

        for(int i = 1;i <= s.length();i++){
            if(i <= ln && s.charAt(i) != 'L') l ++;
            if(i > ln && i <= ln + mn && s.charAt(i) != 'M') m++;
        }
        int res = 0;
        for(int i = ln+1;i <= ln + mn;i++){
            if(s.charAt(i) == 'L') res --;
        }
        System.out.print(l + res + m);

    }
}