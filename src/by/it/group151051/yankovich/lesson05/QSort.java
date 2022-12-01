package by.it.group151051.yankovich.lesson05;

public class QSort {
    static int divide (int a[], int start, int end)
    {
        int pivot = a[end];
        int i = (start - 1);

        for (int j = start; j <= end - 1; j++)
        {
            if (a[j] < pivot)
            {
                i++;
                int t = a[i];
                a[i] = a[j];
                a[j] = t;
            }
        }
        int t = a[i+1];
        a[i+1] = a[end];
        a[end] = t;
        return (i + 1);
    }
    static void quick(int a[], int start, int end)
    {
        if (start < end)
        {
            int p = divide(a, start, end);
            quick(a, start, p - 1);
            quick(a, p + 1, end);
        }
    }

    public static void main(String[] args){
        int[] array = {5, 9, 6, 7, 11, 2, 3};
        System.out.println("Source array:");
        for (int number:
             array) {
            System.out.print(number + " ");
        }
        System.out.println();

        quick(array, 0, array.length-1);

        System.out.println("Sorted array:");
        for (int number:
                array) {
            System.out.print(number + " ");
        }
        System.out.println();
    }
}
