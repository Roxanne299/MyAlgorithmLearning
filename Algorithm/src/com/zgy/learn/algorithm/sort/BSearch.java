package com.zgy.learn.algorithm.sort;

public class BSearch {
    // 数组按照从小到大排序，二分找到第一个大于3的数字
    public int b_serach1(int[] a,int l,int r)
    {
        while(l < r)
        {
            int mid = (l + r)/2;
            if(a[mid] > 3) r = mid;
            else l = mid + 1;
        }
        return l;
    }
    // 数组按照从小到大排序，二分找到最后一个小与3的数字
    public int b_serach2(int[] a,int l,int r)
    {
        while(l < r)
        {
            int mid = (l + r + 1)/2;
            if(a[mid] < 3) l = mid;
            else r = mid - 1;
        }
        return l;
    }
}
