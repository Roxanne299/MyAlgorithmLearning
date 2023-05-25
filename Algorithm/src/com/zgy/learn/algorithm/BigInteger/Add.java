package com.zgy.learn.algorithm.BigInteger;
import java.io.*;
import java.util.*;
//大整数加法
public class Add {


        public static Scanner sc = new Scanner(System.in);

        public static List<Integer> add(List<Integer> A,List<Integer> B){
            List<Integer> C = new ArrayList<>();
            //表示进位
            int t = 0;
            for(int i = 0;i < A.size() || i < B.size();i++){
                if(i < A.size()) t += A.get(i);
                if(i < B.size()) t += B.get(i);
                C.add(t % 10);
                t = t / 10;

            }
            if(t > 0) C.add(t);
            return C;
        }

        public static void main(String[] args){
            String a = sc.next(),b = sc.next();
            List<Integer> A = new ArrayList<>(),B = new ArrayList<>();
            for(int i = 0;i < a.length();i++) A.add((int)a.charAt(a.length() - i - 1) - '0');
            for(int i = 0;i < b.length();i++) B.add((int)b.charAt(b.length() - i - 1) - '0');
            List<Integer> C = add(A,B);
            for(int i = C.size() - 1;i >= 0;i--) System.out.print(C.get(i));

        }

}
