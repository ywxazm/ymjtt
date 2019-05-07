package com.ymjtt.test.web.sort.test;

import org.junit.Test;

/**
 * @auther ywx
 * @date 2019/3/1 9:50
 **/
public class Sort {

    private static final int[] arr = {44,5,2,47,9};
    private static final int len = arr.length;

    //冒泡排序
    @Test
    public void test01() {
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    arr[j + 1] = arr[j + 1] ^ arr[j];
                    arr[j] = arr[j + 1] ^ arr[j];
                    arr[j + 1] = arr[j + 1] ^ arr[j];
                }
                print(arr);
            }
        }
    }

    //冒泡排序
    @Test
    public void test02() {

    }

    //冒泡排序
    @Test
    public void test03() {

    }

    //冒泡排序
    @Test
    public void test04() {

    }

    //冒泡排序
    @Test
    public void test05() {

    }

    //冒泡排序
    @Test
    public void test06() {

    }

    //冒泡排序
    @Test
    public void test07() {

    }

    //冒泡排序
    @Test
    public void test08() {

    }

    private void print(int[] arr) {
        System.out.print("[");
        for (int i : arr) {
            System.out.print(i);
            System.out.print(",");
        }
        System.out.println("]");
    }
}
