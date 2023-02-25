package com.zgy.learn.algorithm.double_pointer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.Buffer;
/*������������������������� A �� B���Լ�һ��Ŀ��ֵ x��
�����±�� 0 ��ʼ��
����������� A[i]+B[j]=x ������ (i,j)��
���ݱ�֤��Ψһ�⡣
 */
public class ArrayPointSum {
    public static int N = 100010;
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int[] a1 = new int[N],a2 = new int[N];

    public static void main(String[] args) throws  Exception{
        String[] s1 = br.readLine().split(" ");
        int n = Integer.parseInt(s1[0]),m = Integer.parseInt(s1[1]),x = Integer.parseInt(s1[2]);
        String[] s2 = br.readLine().split(" ");
        String[] s3 = br.readLine().split(" ");
        for(int i = 0;i < n; i++) a1[i] = Integer.parseInt(s2[i]);
        for(int i = 0;i < m; i++) a2[i] = Integer.parseInt(s3[i]);

        for(int i = 0,j = m - 1; i < n; i++)
        {
            while(j >= 0 && a1[i] + a2[j] > x) j--;
            if(a1[i] + a2[j] == x)
            {
                System.out.print(i+" "+j);
                break;
            }
        }

    }
}
