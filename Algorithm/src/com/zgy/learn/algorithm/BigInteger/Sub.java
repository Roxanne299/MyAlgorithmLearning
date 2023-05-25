package com.zgy.learn.algorithm.BigInteger;

import java.io.*;
import java.util.*;

class Sub{
    public static Scanner sc = new Scanner(System.in);
    // 判断A 是否大于等于 B
    public static boolean cmp(List<Integer> A,List<Integer> B){
        if(A.size() > B.size()) return true;
        else if(A.size() < B.size()) return false;
        if(A.size() == B.size())
            for(int i = A.size() - 1;i >= 0;i--)
                if(A.get(i) < B.get(i)) return false;
                else if(A.get(i) > B.get(i)) return true;

        // 相等的话返回true
        return true;
    }

    // 要保证 A > B
    public static List<Integer> sub(List<Integer> A,List<Integer> B){
        List<Integer> C = new ArrayList<>();
        //表示借位
        int t = 0;
        for(int i = 0;i < A.size();i++){
            t = A.get(i) - t;
            if(i < B.size()) t = t - B.get(i);
            // 当t < 0 的时候 (t+10)%10 = t + 10 反之 t = 0
            C.add((t+10)%10);
            if(t < 0) t = 1;
            else t = 0;
        }
        // 要注意去掉前导零 但是要保留一位
        while(C.size() > 1 && C.get(C.size() - 1) == 0) C.remove(C.size() - 1);
        return C;
    }

    public static void main(String[] args){
        String a = sc.next(),b = sc.next();
        List<Integer> A = new ArrayList<>(),B = new ArrayList<>();
        for(int i = 0;i < a.length();i++) A.add((int)a.charAt(a.length() - i - 1) - '0');
        for(int i = 0;i < b.length();i++) B.add((int)b.charAt(b.length() - i - 1) - '0');
        if(cmp(A,B)){
            List<Integer> C = sub(A,B);
            for(int i = C.size() - 1;i >= 0;i--) System.out.print(C.get(i));
        }else{
            List<Integer> C = sub(B,A);
            System.out.print("-");
            for(int i = C.size() - 1;i >= 0;i--) System.out.print(C.get(i));
        }


    }
}
