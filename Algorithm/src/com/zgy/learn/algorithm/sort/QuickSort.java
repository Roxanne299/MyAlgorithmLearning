package com.zgy.learn.algorithm.sort;

public class QuickSort {
    public void swap(int[] a,int x,int y)
    {
        int temp = a[x];
        a[x] = a[y];
        a[y] = temp;
    }
    public void quick_sort1(int[] a,int l,int r){
        if(l >= r)   return;
        int i = l - 1,j = r + 1,x = a[l];
        while(i < j)
        {
            do i++; while(a[i] < x);
            do j--; while(a[j] > x);
            if(i < j) swap(a,i,j);
        }
        //这里的参数很重要。如果x选的是l，那么就不能用i作为参数，会有边界问题。为什么不是[l,j+1]?因为输入的是【1,2】，最后i=j=0，l-j+1的话会无限循环。
        quick_sort1(a,l,j);
        quick_sort1(a,j + 1,r);
    }
    public void quick_sort2(int[] a,int l,int r){
        if(l >= r)   return;
        int i = l - 1,j = r + 1,x = a[r];
        while(i < j)
        {
            do i++; while(a[i] < x);
            do j--; while(a[j] > x);
            if(i < j) swap(a,i,j);
        }
        //这里的参数很重要。如果x选的是r，那么就不能用j作为参数，会有边界问题。为什么不是【】l，i】?因为当i = 1,j = 0,那么就是0-1无限循环。
        quick_sort2(a,l,i-1);
        quick_sort2(a,i,r);
    }
}
