package com.zgy.learn.algorithm.high_level.DP.Monotone_queue_optimization;
import java.io.*;
import java.util.*;

//���������������˼��
public class AcWing_1091_����������� {
    public static int N = 1010,n,m, P = 998244353;
    public static int[][] g = new int[N][N], row_max = new int[N][N],row_min = new int[N][N],res_max = new int[N][N],res_min = new int[N][N];
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args)throws Exception{
        String[] s1 = br.readLine().split(" ");
        int n = Integer.parseInt(s1[0]),m = Integer.parseInt(s1[1]),a = Integer.parseInt(s1[2]),b = Integer.parseInt(s1[3]);
        for(int i = 0;i < n;i++){
            String[] s2 = br.readLine().split(" ");
            for(int j = 0;j < m;j++){
                g[i][j] = Integer.parseInt(s2[j]);
            }
        }

        //����Ԥ�����ÿһ�� ����Ϊb�Ķ����е����ֵ����Сֵ row_max[i][j] ���� ��i��j ~ j + b - 1 �� �����ֵ
        for(int i = 0;i < n;i++){
            //һά��������(��i��)
            int tt = -1,hh = 0;
            int[] q = new int[N];
            for(int j = 0;j < m;j++){
                while(tt >= hh && j - q[hh] > b - 1) hh++;
                while(tt >= hh && g[i][q[tt]] < g[i][j]) tt--;
                q[++tt] = j;
                if(j >= b - 1) row_max[i][j - b + 1] = g[i][q[hh]];
            }
        }

        //����Ԥ�����ÿһ�� ����Ϊb�Ķ����е����ֵ����Сֵ row_max[i][j] ���� ��i��j ~ j + b - 1 �е���Сֵ
        for(int i = 0;i < n;i++){
            //һά��������(��i��)
            int tt = -1,hh = 0;
            int[] q = new int[N];
            for(int j = 0;j < m;j++){
                while(tt >= hh && j - q[hh] > b - 1) hh++;
                while(tt >= hh && g[i][q[tt]] > g[i][j]) tt--;
                q[++tt] = j;
                if(j >= b - 1) row_min[i][j - b + 1] = g[i][q[hh]];
            }
        }


        //����Ԥ�����i��~��i + b-1����ϵ�һ��n��b�е����飬����ʹ�õ����������  ��i��~��i + b-1����������������a�����ֵ
        //��һ��ѭ������
        for(int i = 0;i <= m - b;i++)
        {
            int tt = -1,hh = 0;
            int[] q = new int[N];
            for(int j = 0;j < n;j++){
                while(tt >= hh && j - q[hh] > a - 1) hh++;
                while(tt >= hh && row_max[j][i] > row_max[q[tt]][i]) tt--;
                q[++tt] = j;
                if(j >= a - 1) res_max[j - a + 1][i] = row_max[q[hh]][i];
            }
        }



        //����Ԥ�����i��~��i + b-1����ϵ�һ��n��b�е����飬����ʹ�õ����������  ��i��~��i + b-1����������������a����Сֵ
        //��һ��ѭ������
        for(int i = 0;i <= m - b;i++)
        {
            int tt = -1,hh = 0;
            int[] q = new int[N];
            for(int j = 0;j < n;j++){
                while(tt >= hh && j - q[hh] > a - 1) hh++;
                while(tt >= hh && row_min[j][i] < row_min[q[tt]][i]) tt--;
                q[++tt] = j;
                if(j >= a - 1) res_min[j - a + 1][i] = row_min[q[hh]][i];
            }
        }

        int res = 0;
        for(int i = 0;i <= n - a;i++)
        {
            for(int j = 0;j <= m - b;j++){
                res = (int)(((long)res_min[i][j] * res_max[i][j] + res) % P);
            }
        }

        System.out.print(res);
    }

}



//��Ҫע����ǻ��������д�Ķ�����������±� ������ֵ�����ж��ǲ��ǳ���ע�����ڳ��Ȳ���q[tt] - q[hh] ��j - q[hh]
//����Ҫע�� ��������Ļ������ڵ�ʱ�� �ȽϵĲ���g���� �����ֵ�Ƚϵľ���row_max ����Сֵ���ǱȽ�row_min
