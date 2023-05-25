package com.zgy.learn.algorithm.BigInteger;

import java.io.*;
import java.util.*;
//高精度乘法
class Mul{
    public static Scanner sc = new Scanner(System.in);

    public static List<Integer> mul(List<Integer> A,int B){
        List<Integer> C = new ArrayList<>();
        // 进位
        int t = 0;
        for(int i = 0;i < A.size() || t > 0;i++){
            //这么判断的原因是 最后可以加上t 不用最后一个循环加t
            if(i < A.size()) t += B * A.get(i);
            C.add(t % 10);
            t = t / 10;
        }
        // 去掉前导零 因为如果B = 0 那么结果就是 A.size的0
        while(C.size() > 1 && C.get(C.size() - 1) == 0) C.remove(C.size() - 1);
        
        return C;
    }

    public static void main(String[] args){
        String a = sc.next();
        int B = sc.nextInt();
        List<Integer> A = new ArrayList<>();
        for(int i = a.length() - 1;i >= 0;i--) A.add((int)a.charAt(i) - '0');
        List<Integer> C = mul(A,B);
        for(int i = C.size() - 1;i >= 0;i--) System.out.print(C.get(i));
    }
}