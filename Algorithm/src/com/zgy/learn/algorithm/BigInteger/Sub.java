package com.zgy.learn.algorithm.BigInteger;

import java.io.*;
import java.util.*;

class Sub{
    public static Scanner sc = new Scanner(System.in);
    // �ж�A �Ƿ���ڵ��� B
    public static boolean cmp(List<Integer> A,List<Integer> B){
        if(A.size() > B.size()) return true;
        else if(A.size() < B.size()) return false;
        if(A.size() == B.size())
            for(int i = A.size() - 1;i >= 0;i--)
                if(A.get(i) < B.get(i)) return false;
                else if(A.get(i) > B.get(i)) return true;

        // ��ȵĻ�����true
        return true;
    }

    // Ҫ��֤ A > B
    public static List<Integer> sub(List<Integer> A,List<Integer> B){
        List<Integer> C = new ArrayList<>();
        //��ʾ��λ
        int t = 0;
        for(int i = 0;i < A.size();i++){
            t = A.get(i) - t;
            if(i < B.size()) t = t - B.get(i);
            // ��t < 0 ��ʱ�� (t+10)%10 = t + 10 ��֮ t = 0
            C.add((t+10)%10);
            if(t < 0) t = 1;
            else t = 0;
        }
        // Ҫע��ȥ��ǰ���� ����Ҫ����һλ
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
