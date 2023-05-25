package com.zgy.learn.algorithm.BigInteger;

import java.io.*;
import java.util.*;

class Div{
    public static Scanner sc = new Scanner(System.in);
    // 这里 是返回商C 但是余数在数组r中
    public static List<Integer> div(List<Integer> A,int B,int[] r1){
        List<Integer> C = new ArrayList<>();
        // 进位
        int r = 0;
        // 从大到小循环 从高位开始除
        for(int i = A.size() - 1;i >= 0;i--){
            r = r * 10 + A.get(i);
            C.add(r / B);
            r = r % B;
        }
        // 将结果反过来
        Collections.reverse(C);
        // 去掉前导零
        while(C.size() > 1 && C.get(C.size() - 1) == 0) C.remove(C.size() - 1);
        r1[0] = r;
        return C;
    }

    public static void main(String[] args){
        String a = sc.next();
        int B = sc.nextInt();
        List<Integer> A = new ArrayList<>();
        for(int i = a.length() - 1;i >= 0;i--) A.add((int)a.charAt(i) - '0');
        int[] r = new int[2];//余数数组
        List<Integer> C = div(A,B,r);
        for(int i = C.size() - 1;i >= 0;i--) System.out.print(C.get(i));
        System.out.printf("\n%d",r[0]);
    }
}