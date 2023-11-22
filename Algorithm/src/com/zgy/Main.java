package com.zgy;
import java.io.*;
import java.util.*;

class Main{
    public static int N = 40,res;
    public static int[][] c = new int[N][N];
    public static ArrayList<Integer> a = new ArrayList<>();
    public static Scanner sc = new Scanner(System.in);

    //初始化排列
    public static void init(){
        //初始化
        for(int i = 0;i < N;i++) c[i][0] = 1;
        for(int i = 1;i < N;i++){
            for(int j = 1;j < N;j++){
                c[i][j] = c[i-1][j] + c[i-1][j-1];
            }
        }
    }

    public static int dp(int x1,int k,int b){
        //数位dp模板
        //1. 边界问题
        if(x1 == 0) return 0;

        //2. 数字转换成B进制
        while(x1 > 0){
            a.add(x1%b);
            x1/= b;
        }

        //res 就是结果 last 代表之前填了多少 1
        int res = 0,last = 0;

        //3. 进行数位dp 从高位到低位循环每一位
        for(int i = a.size()-1;i >= 0;i--){
            int x = a.get(i);
            //树的左边是 0 - x-1,右边是 x-1
            //先处理树左边 只有x-1>=0 也就是x > 0才有的搞
            if(x > 0){
                //如果左子树取0 后面的位数里面可以任选位置填写k-last个1
                res += c[i][k-last];
                //想要左子树可以取1 那么就是x-1 > 0
                if(x > 1){
                    //如果左子树取1 后面的位数里面可以任选位置填写k-last-1个1
                    //左子树取1 右子树只能>1不存在直接break
                    if(k - last >= 1){
                        res += c[i][k-last-1];
                        last++;
                    }
                    break;
                }else{
                    //右子树取1的情况
                    last++;
                    if(last > k) break;
                }

            }

            //还有一种情况就是到了最右边的子树 也就是最后一位数前面都是1的情况
            //为什么是k==last? 1. 如果第0为是0的时候 我们应该是填完了1 k==last
            //                 2. 如果第0位>=1的时候 也就是在左子树或者右子树填1在上面都会加上1,last在上面的代码会加上。
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