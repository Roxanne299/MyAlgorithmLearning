package com.zgy.learn.algorithm.graph;

//�����������
import java.io.*;
import java.util.*;


class Main{
    public static int N = 40010,M = 2 * N,idx,root,n;
    public static int[] h = new int[N],e = new int[M],ne = new int[M],stu = new int[N],d = new int[N];
    public static int[][] f = new int[N][16];
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));


    //dfs���ױ�ջ��ʹ��bfs����ÿһ���ڵ��d����(d[i]����i���ڵ������Ƕ��٣�����d[root] = 1)
    //���Ҽ���ÿһ���ڵ��f����(f[i][j]:��i���ڵ������ƶ�2^j�λ��ƶ�����f[i][j]���ڵ� ����ʽ f[i][j] = f[f[i][j-1]][j-1] ��ʵ���������ƶ�����2^(j-1)
    public static void bfs(int root){
        d[root] = 1;
        int[] q = new int[N];
        int tt = -1,hh = 0;
        q[++tt] = root;
        stu[root] = 1;
        //Լ�� ��� f[i][j] �����ˣ���ôֵΪ0 ��Ҳ��Ϊʲô������ÿ���ӽڵ��������ÿ�����f���� ���ڵ㲻����ô�ƶ��������
        while(hh <= tt){
            int u = q[hh++];
            for(int i = h[u];i != -1;i = ne[i]){
                //��Ϊ������ͼ����ֹ�ӽڵ���ʵ����ڵ�ѭ������Ҫ�ж�һ���ڵ� ���ʹ��ľͲ�����
                if(stu[e[i]] == 0){
                    stu[e[i]] = 1;

                    d[e[i]] = d[u] + 1;
                    q[++tt] = e[i];
                    //����f���� �����ƶ�һ�ξ����Լ��ĸ��ڵ�
                    f[e[i]][0] = u;
                    //����Ҫע���Ǵ�С����ѭ������Ϊ��Ҫ�õ�С������ ������Ҫ����f[e[i]][0]��ֵ����Ȼ���ø���
                    //f[f[e[i]][j-1]][j-1]һ�������¹� ��Ϊf[e[i]][j-1]�����Լ��ĸ��ڵ� ���ڵ�һ���ڶ���֮ǰ�����¹� û�����¹�����0�Ǿ���û��
                    //����f[e[i]][j-1]һ��Ҳ�����¹� ��Ϊ�����Ǵ�С����ѭ���� ǰ���һ�������¹�
                    for(int j = 1;j <= 15;j++)
                        f[e[i]][j] = f[f[e[i]][j-1]][j-1];
                }


            }
        }
    }

    //�������ߵ������������
    public static int lcd(int a,int b){
        if(d[a] < d[b]){
            int temp = a;
            a = b;
            b = temp;
        }
        //�ֽ��ڵ��µ��Ǹ�����ߵ���������ĵ�һ������� �����ѭ������Ҳ������ ������ʵ����ÿ�ζ���������һ��a b֮����Ȳ��Ķ����Ƶ����ֵ
        //����a b֮����3 ��ô����2 ���5 ����4 ���20����16
        //ÿ�����������֮������֮����Ȳ�� �ٵ�һ����Ȳ������Ƶ����ֵ ����һ����������ͬ�߶ȵ�
        //�����ѭ�������� ������������� ��ôf[a][i] = 0 d[0] = 0 < d[1-n] �Ǿ��ǲ������ѭ�� Ҳ���ܱ�֤ÿ�ζ��ǵ�һ�����������ֵ
        for(int i = 15;i >= 0;i--)
            if(d[f[a][i]] >= d[b])
                a = f[a][i];
        //����������һ���� �Ǿ���b��a������
        if(a == b) return b;
        //�����ͬһ�㵫�ǲ���ͬһ���� ��ô��Ҫ����һ���� ֱ������������������һ����
        //������Ҳ��һ���������һ���� �ж�������f[a][i] != f[b][i] Ҳ�������������һ��������һ���� �������� ����һ����0 ��ô�Ͳ�������ж�����
        //ͬ�����Ա�֤ÿ�����ĵ��ǵ����ڵ��������Ƶ����һ��1��ֵ �������f[a][i] == f[b][i]����������������������
        //�ǿ϶�����ֻ����һ�� 1. ֻʣ����һ�ξͿ��������ж����� f[a][0] == f[b][0] ��ô�Ͳ������ѭ�� ����ѭ������
        //2. ֻʣ��2�ξͿ����������� f[a][1] == f[b][1] ��ô�������ѭ�� ��һ��ѭ�� f[a][0] != f[b][0] ѭ����������ʣ��һ��
        // 3. ʣ�� 2^n + 1 ci �Ϳ�����������f[a][2^n + 1] == f[b][2^n + 1] 15-1��ѭ�����Խ��2^n ����ʣ��1 �������ѭ�� ����ѭ������ʣ1�ΰ�
        //4. ʣ��2^n �ξͿ�����������f[a][2^n] == f[b][2^n] 15-0��ѭ�����Խ��2^n - 1 ����ѭ������ʣ1�ΰ� (1000 = 111 + 1) �� j = 3���������� ���涼����
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