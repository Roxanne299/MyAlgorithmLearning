package com.zgy;

import java.util.*;
class Main{
    public static int N = 1000010,P= 1000003;
    public static int[] fact = new int[N],infact = new int[N],q = new int[N];
    public static Scanner sc = new Scanner(System.in);

    public static int qmi(int a,int b){
        int res = 1;
        while(b > 0){
            if((b & 1) == 1) res = (int)((long)res * a) % P;
            a = (int)((long)a * a) % P;
            b >>= 1;
        }
        return res;
    }

    public static void main(String[] args){
        int n = sc.nextInt(), k = sc.nextInt();
        for(int i = 1;i <= k;i++) q[i] = sc.nextInt();
        Arrays.fill(fact,1);
        Arrays.fill(infact,1);
        for(int i = 1;i <= 1000000;i++){
            fact[i] = (int)((long)fact[i-1] * i) % P;
            infact[i] = (int)((long)infact[i-1] * qmi(i,P-2)) % P;
        }
        int one = 0,yes = 0;
        q[0] = q[1];
        for(int i = 1;i <= k;i++){
            if(q[i-1] == q[i]) continue;
            else if(q[i-1] > q[i]){
                one = 1;
                yes = 1;
            }else yes = 1;
        }

        int a = k - yes,b = q[1] - one;
        for(int i = 1;i <= 100;i++) System.out.print(infact[i] + " ");
        int res = (int)((((long) fact[a] * infact[b]) % P) * infact[a - b] % P);
        System.out.print(res);


    }

}