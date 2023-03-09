package com.zgy.learn.algorithm.graph;

//最近公共祖先
import java.io.*;
import java.util.*;


class Main{
    public static int N = 40010,M = 2 * N,idx,root,n;
    public static int[] h = new int[N],e = new int[M],ne = new int[M],stu = new int[N],d = new int[N];
    public static int[][] f = new int[N][16];
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));


    //dfs容易爆栈，使用bfs计算每一个节点的d数组(d[i]：第i个节点的深度是多少，定义d[root] = 1)
    //并且计算每一个节点的f数组(f[i][j]:第i个节点向上移动2^j次会移动到第f[i][j]个节点 迭代式 f[i][j] = f[f[i][j-1]][j-1] 其实就是向上移动两个2^(j-1)
    public static void bfs(int root){
        d[root] = 1;
        int[] q = new int[N];
        int tt = -1,hh = 0;
        q[++tt] = root;
        stu[root] = 1;
        //约定 如果 f[i][j] 出界了，那么值为0 这也是为什么设置在每个子节点里面更新每个点的f数组 根节点不管怎么移动都会出界
        while(hh <= tt){
            int u = q[hh++];
            for(int i = h[u];i != -1;i = ne[i]){
                //因为是无向图，防止子节点访问到父节点循环你需要判断一个节点 访问过的就不访问
                if(stu[e[i]] == 0){
                    stu[e[i]] = 1;

                    d[e[i]] = d[u] + 1;
                    q[++tt] = e[i];
                    //更新f数组 向上移动一次就是自己的父节点
                    f[e[i]][0] = u;
                    //这里要注意是从小到大循环，因为需要用到小的数组 并且需要设置f[e[i]][0]初值，不然不好更新
                    //f[f[e[i]][j-1]][j-1]一定被更新过 因为f[e[i]][j-1]都是自己的父节点 父节点一定在队列之前被更新过 没被更新过就是0那就是没有
                    //并且f[e[i]][j-1]一定也被更新过 因为数组是从小到大循环的 前面的一定被更新过
                    for(int j = 1;j <= 15;j++)
                        f[e[i]][j] = f[f[e[i]][j-1]][j-1];
                }


            }
        }
    }

    //返回两者的最近公共祖先
    public static int lcd(int a,int b){
        if(d[a] < d[b]){
            int temp = a;
            a = b;
            b = temp;
        }
        //现将在底下的那个点提高到和在上面的点一样的深度 这里的循环处理也很巧妙 我们其实就是每次都是向上跳一个a b之间深度差距的二进制的最大值
        //比如a b之间差距3 那么就跳2 差距5 就跳4 差距20就跳16
        //每次跳完更新跳之后两点之间深度差距 再调一个深度差距二进制的最大值 这样一定会跳到相同高度的
        //这里的循环很巧妙 如果我跳出界了 那么f[a][i] = 0 d[0] = 0 < d[1-n] 那就是不会进入循环 也就能保证每次都是调一个二进制最大值
        for(int i = 15;i >= 0;i--)
            if(d[f[a][i]] >= d[b])
                a = f[a][i];
        //发现两个是一个点 那就是b是a的祖先
        if(a == b) return b;
        //如果在同一层但是不是同一个点 那么就要两个一起跳 直到跳到公共祖先下面一个点
        //这里面也是一个很巧妙的一个点 判断条件是f[a][i] != f[b][i] 也就是两个点的下一步不会是一个点 当跳出界 两者一定是0 那么就不会进入判断条件
        //同样可以保证每次跳的点是到根节点距离二进制的最大一个1的值 如果满足f[a][i] == f[b][i]条件（除了跳出界的情况）
        //那肯定就是只用跳一次 1. 只剩下跳一次就可以满足判断条件 f[a][0] == f[b][0] 那么就不会进入循环 正好循环结束
        //2. 只剩下2次就可以满足条件 f[a][1] == f[b][1] 那么不会进入循环 下一次循环 f[a][0] != f[b][0] 循环结束还是剩下一次
        // 3. 剩下 2^n + 1 ci 就可以满足条件f[a][2^n + 1] == f[b][2^n + 1] 15-1次循环可以解决2^n 但是剩下1 不会进入循环 跳出循环还是剩1次奥
        //4. 剩下2^n 次就可以满足条件f[a][2^n] == f[b][2^n] 15-0次循环可以解决2^n - 1 跳出循环还是剩1次奥 (1000 = 111 + 1) 当 j = 3不进入条件 后面都进入
        for(int i = 15;i >= 0;i--){
            if(f[a][i] != f[b][i]){
                a = f[a][i];
                b = f[b][i];
            }
        }
        return f[a][0];

    }

    public static void insert(int a,int b){
        e[idx] = b; ne[idx] = h[a];h[a] = idx++;
    }

    public static void main(String[] args)throws Exception{
        n = Integer.parseInt(br.readLine());
        Arrays.fill(h,-1);
        for(int i = 0;i < n;i++){
            String[] s1 = br.readLine().split(" ");
            int a = Integer.parseInt(s1[0]),b = Integer.parseInt(s1[1]);
            if(b == -1) {root = a; continue;}
            insert(a,b);
            insert(b,a);
        }
        bfs(root);
        int k = Integer.parseInt(br.readLine());
        while((k--)!=0){
            String[] s2= br.readLine().split(" ");
            int a = Integer.parseInt(s2[0]),b = Integer.parseInt(s2[1]);
            if(lcd(a,b) == a) bw.write("1\n");
            else if(lcd(a,b) == b) bw.write("2\n");
            else bw.write("0\n");
        }
        bw.flush();
    }

}