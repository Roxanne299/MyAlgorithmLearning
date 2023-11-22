package com.zgy;
import java.io.*;
import java.util.*;

class Main{
    public static int N = 40,res;
    public static int[][] c = new int[N][N];
    public static ArrayList<Integer> a = new ArrayList<>();
    public static Scanner sc = new Scanner(System.in);

    //��ʼ������
    public static void init(){
        //��ʼ��
        for(int i = 0;i < N;i++) c[i][0] = 1;
        for(int i = 1;i < N;i++){
            for(int j = 1;j < N;j++){
                c[i][j] = c[i-1][j] + c[i-1][j-1];
            }
        }
    }

    public static int dp(int x1,int k,int b){
        //��λdpģ��
        //1. �߽�����
        if(x1 == 0) return 0;

        //2. ����ת����B����
        while(x1 > 0){
            a.add(x1%b);
            x1/= b;
        }

        //res ���ǽ�� last ����֮ǰ���˶��� 1
        int res = 0,last = 0;

        //3. ������λdp �Ӹ�λ����λѭ��ÿһλ
        for(int i = a.size()-1;i >= 0;i--){
            int x = a.get(i);
            //��������� 0 - x-1,�ұ��� x-1
            //�ȴ�������� ֻ��x-1>=0 Ҳ����x > 0���еĸ�
            if(x > 0){
                //���������ȡ0 �����λ�����������ѡλ����дk-last��1
                res += c[i][k-last];
                //��Ҫ����������ȡ1 ��ô����x-1 > 0
                if(x > 1){
                    //���������ȡ1 �����λ�����������ѡλ����дk-last-1��1
                    //������ȡ1 ������ֻ��>1������ֱ��break
                    if(k - last >= 1){
                        res += c[i][k-last-1];
                        last++;
                    }
                    break;
                }else{
                    //������ȡ1�����
                    last++;
                    if(last > k) break;
                }

            }

            //����һ��������ǵ������ұߵ����� Ҳ�������һλ��ǰ�涼��1�����
            //Ϊʲô��k==last? 1. �����0Ϊ��0��ʱ�� ����Ӧ����������1 k==last
            //                 2. �����0λ>=1��ʱ�� Ҳ������������������������1�����涼�����1,last������Ĵ������ϡ�
            if(i == 0 && k == last) res++;
        }

        return res;
    }

    public static void main(String[] args){
        int l = sc.nextInt(),r = sc.nextInt(),k = sc.nextInt(),b = sc.nextInt();

        init();
        System.out.print(dp(r,k,b) - dp(l-1,k,b));


    }
}