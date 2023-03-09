package com.zgy.learn.algorithm.data_structure;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

//��һЩ10^9������ӳ�䵽10^5�����д洢����ϣ����ʹ�����࣬ģ����ѡ������Ǵ���10^5�ĵ�һ���������������Ժܺõı����ͻ
public class Hash {
//������
//    public static int N = 100003,idx = 0;
//    //h�����д�Ŷ�Ӧ������������ַ��e������֣�ne���e�е�������һ������
//    public static int[] h = new int[N],e = new int[N],ne = new int[N];
//    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//
//    public static void insert(int x)
//    {
//        int u = ((x % N) + N) % N;//��Ϊ��-3 % 4�� = -3 ������֤������������
//        e[idx] = x;
//        ne[idx] = h[u];
//        h[u] = idx++;
//    }
//
//    public static String find(int x)
//    {
//        int u = ((x % N) + N) % N;
//        for(int i = h[u];i != -1;i = ne[i])
//        {
//            if(e[i] == x) return "Yes";
//        }
//        return "No";
//
//    }
//    public static void main(String[] args)throws Exception {
//        int n = Integer.parseInt(br.readLine());
//        Arrays.fill(h,-1);
//        while((n--)!=0)
//        {
//            String[] s = br.readLine().split(" ");
//            if(s[0].equals("I")) insert(Integer.parseInt(s[1]));
//            else System.out.println(find(Integer.parseInt(s[1])));
//        }
//    }


//����Ѱַ��  һ��N�Ὺ������ ��200000 �������������200003

    public static int N = 200003,idx = 0;
    //h�����д�Ŷ�����
    public static long[] h = new long[N];
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    //find ����������Ҫ�� ����ǰ���ֲ����� ��ô�᷵�ص�ǰ�����ܲ���ĵط���������ھͷ����±�
    public static int find(int x)
    {
        int u = ((x % N) + N) % N,k = 0;
        while(h[k] != (long)1e9 + 1 && h[k] != (long)x)
        {

            k++;
            if(k == N) k = 0;
        }
        return k;

    }
    public static void main(String[] args)throws Exception {
        int n = Integer.parseInt(br.readLine());
        Arrays.fill(h,(long)1e9 + 1);
        while((n--)!=0)
        {
            String[] s = br.readLine().split(" ");
            int u = find(Integer.parseInt(s[1]));
            if(s[0].equals("I")) h[u] = Integer.parseInt(s[1]);
            else bw.write(h[u] != (long)1e9 + 1 ? "Yes": "No");
        }
        bw.flush();
    }
}
