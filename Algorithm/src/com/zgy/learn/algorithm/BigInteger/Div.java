package com.zgy.learn.algorithm.BigInteger;

import java.io.*;
import java.util.*;

class Div{
    public static Scanner sc = new Scanner(System.in);
    // ���� �Ƿ�����C ��������������r��
    public static List<Integer> div(List<Integer> A,int B,int[] r1){
        List<Integer> C = new ArrayList<>();
        // ��λ
        int r = 0;
        // �Ӵ�Сѭ�� �Ӹ�λ��ʼ��
        for(int i = A.size() - 1;i >= 0;i--){
            r = r * 10 + A.get(i);
            C.add(r / B);
            r = r % B;
        }
        // �����������
        Collections.reverse(C);
        // ȥ��ǰ����
        while(C.size() > 1 && C.get(C.size() - 1) == 0) C.remove(C.size() - 1);
        r1[0] = r;
        return C;
    }

    public static void main(String[] args){
        String a = sc.next();
        int B = sc.nextInt();
        List<Integer> A = new ArrayList<>();
        for(int i = a.length() - 1;i >= 0;i--) A.add((int)a.charAt(i) - '0');
        int[] r = new int[2];//��������
        List<Integer> C = div(A,B,r);
        for(int i = C.size() - 1;i >= 0;i--) System.out.print(C.get(i));
        System.out.printf("\n%d",r[0]);
    }
}