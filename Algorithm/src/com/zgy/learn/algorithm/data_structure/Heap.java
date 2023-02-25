package com.zgy.learn.algorithm.data_structure;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Heap {
    public static int N = 100010,size = 0,idx=0;
    public static int[] h = new int[N],hp = new int[N],ph = new int[N];
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void heap_swap(int a,int b)
    {
        //h[i] ������е�i��ֵ hp[i]������е�i��ֵ�ǵڼ�������� ph[i]�����i�������ֵ�ڶ����ǵڼ���
        //��Ϊ��Ҫ�����޸ĵ�i�������ֵ��������Ҫ��һ������ph��ŵڲ���ѵ�˳�򣬵����޸�֮����up down���������漰����Ԫ�ؽ�����������ӦphҲ��Ҫ������ֻ֪����Ҫ�޸ĵĶ���Ԫ���ǵڼ�������� ȴ��֪���������ǵڼ������룬������Ҫһ��hpά������Ԫ���ǵڼ�������ġ�
        int temp = ph[hp[a]];
        ph[hp[a]] = ph[hp[b]];
        ph[hp[b]] = temp;

        temp = hp[a];
        hp[a] = hp[b];
        hp[b] = temp;

        temp = h[a];
        h[a] = h[b];
        h[b] = temp;
    }
    //���ϵ�������ֻ�ú͸��ڵ�Ƚϣ�֪����������С�ĸ��ڵ�����Ѿ���ͷ����ǾͿ���ͣ����
    public static int up(int u)
    {
        while(u/2 > 0 && h[u/2] > h[u])
        {
            heap_swap(u,u/2);
            u /= 2;
        }
        return u;
    }
    //���µ�������ÿ�κ������ӽڵ�Ƚ�
    public static int down(int u)
    {
        int p = u;
        if(u*2 <=size && h[u] > h[u*2]) p = 2*u;
        if(u*2+1 <= size && h[p] > h[u*2+1]) p = 2 * u + 1;
        if(p != u)
        {
            heap_swap(p,u);
            return down(p);
        }
        return p;
    }
    public static void main(String[] args) throws Exception{
        int n = Integer.parseInt(br.readLine());
        while((n--)!=0)
        {
            String[] s = br.readLine().split(" ");
            if(s[0].equals("I"))
            {
                //��������ܼ򵥣��ڶ�����������up
                h[++size] = Integer.parseInt(s[1]);
                int k = up(size);
                ph[++idx] = k;
                hp[k] = idx;
            }
            else if(s[0].equals("PM")) System.out.println(h[1]);
            else if (s[0].equals("DM")) {
                //ɾ����Сֵ �������һ�����滻����һ�� ��down
                h[1] = h[size--];
                down(1);
            } 
            else if (s[0].equals("D")) {
                //ɾ����k���������� ֻ�ð����һ�����ֽ���k������������滻 �ڽ���up down����
                int k = Integer.parseInt(s[1]);
                heap_swap(ph[k],size--);
                up(ph[k]);
                down(ph[k]);
            } else if (s[0].equals("C")) {
                int a = Integer.parseInt(s[1]),b = Integer.parseInt(s[2]);
                h[ph[a]] = b;
                down(ph[a]);
                up(ph[a]);
            }
        }
    }
}
