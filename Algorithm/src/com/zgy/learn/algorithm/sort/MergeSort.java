package com.zgy.learn.algorithm.sort;

public class MergeSort {
    final int N = 1000;
    int[] temp = new int[N];
    public void merge_sort(int[] a,int l,int r)
    {
        if(l <= r) return ;
        int mid = (l + r) >> 1;
        merge_sort(a,l,mid);
        merge_sort(a,mid + 1,r);

        int k = 0,i = l,j = mid + 1;
        while(i <= mid && j <= r)
        {
            if(a[i]<a[j]) temp[k++] = a[i++];
            else temp[k++] = a[j++];
            while(i <= mid) temp[k++] = a[i++];
            while(i <= r) temp[k++] = a[j++];

            for(i = l,k = 0;i <= r;i++,k++) a[i] = temp[k];
        }
    }
}
