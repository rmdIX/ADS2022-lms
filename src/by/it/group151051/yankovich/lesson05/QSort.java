package by.it.group151051.yankovich.lesson05;

import java.util.Random;

public class QSort {
    static int divide(int a[], int start, int end) {
        int pivot = a[end];
        int i = (start - 1);

        for (int j = start; j <= end - 1; j++) {
            if (a[j] < pivot) {
                i++;
                int t = a[i];
                a[i] = a[j];
                a[j] = t;
            }
        }
        int t = a[i + 1];
        a[i + 1] = a[end];
        a[end] = t;
        return (i + 1);
    }

    static void quick(int a[], int start, int end) {
        if (start < end) {
            int p = divide(a, start, end);
            quick(a, start, p - 1);
            quick(a, p + 1, end);
        }
    }

    static int divideSeg(A_QSort.Segment a[], int start, int end) {
        A_QSort.Segment pivot = a[end];
        int i = (start - 1);

        for (int j = start; j <= end - 1; j++) {
            if (a[j].compareTo(pivot) == -1) {
                i++;
                A_QSort.Segment t = a[i];
                a[i] = a[j];
                a[j] = t;
            }
        }
        A_QSort.Segment t = a[i + 1];
        a[i + 1] = a[end];
        a[end] = t;
        return (i + 1);
    }

    static void quickSeg(A_QSort.Segment[] a, int start, int end) {
        if (start < end) {
            int p = divideSeg(a, start, end);
            quickSeg(a, start, p - 1);
            quickSeg(a, p + 1, end);
        }
    }

    public static void main(String[] args) {
        A_QSort.Segment[] seg_arr = new A_QSort.Segment[10];
        Random rand = new Random();
        for (int i = 0; i < seg_arr.length; i++) {
            seg_arr[i] = new A_QSort.Segment(rand.nextInt(10), rand.nextInt(11, 20));
        }
        int count = 0;
        System.out.println("Random array:");
        for (A_QSort.Segment seg : seg_arr) {
            System.out.printf("Segment #%d start: %d, stop: %d\n", ++count, seg.start, seg.stop);
        }
        System.out.println(" ");
        quickSeg(seg_arr, 0, seg_arr.length-1);
        count = 0;
        System.out.println("Sorted array:");
        for (A_QSort.Segment seg : seg_arr) {
            System.out.printf("Segment #%d start: %d, stop: %d\n", ++count, seg.start, seg.stop);
        }
    }
}
//        int[] array = {5, 9, 6, 7, 11, 2, 3};
//        System.out.println("Source array:");
//        for (int number:
//             array) {
//            System.out.print(number + " ");
//        }
//        System.out.println();
//
//        quick(array, 0, array.length-1);
//
//        System.out.println("Sorted array:");
//        for (int number:
//                array) {
//            System.out.print(number + " ");
//        }
//        System.out.println();
