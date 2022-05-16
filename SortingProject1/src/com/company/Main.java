package com.company;

import java.util.Arrays;

import static com.company.SelectionSort.selectionSort;
import static com.company.Sorts.*;

public class Main {

    public static void main(String[] args) {
        int[] myVal = initValues();
        int[] copy = Arrays.copyOf(values, SIZE);
        printValues();
        System.out.println("values is sorted: " + isSorted());
        System.out.println();
        Sorts.comparison = 0;
        Sorts.swap = 0;
        System.out.println("Sort\t\tNumber ofSwaps\t\tNumber of Comparisons");
        selectionSort();
//        int[] sortedArray = selectionSort(myVal);
        printValues();
        System.out.println("Selection\t\t"+Sorts.swap+"\t\t"+Sorts.comparison);
        // reseting to zero
        Sorts.comparison = 0;
        Sorts.swap = 0;

//        values = Arrays.copyOf(copy, SIZE);
//
//        bubbleSort();
//
//        System.out.println("BubbleSort\t\t"+Sorts.swap+"\t\t"+Sorts.comparison);
//
//        Sorts.comparison = 0;
//
//        Sorts.swap = 0;
//
//        values = Arrays.copyOf(copy, SIZE);
//
//        shortBubble();
//
//        System.out.println("ShortBubbleSort\t\t"+Sorts.swap+"\t\t"+Sorts.comparison);
//
//        Sorts.comparison = 0;
//
//        Sorts.swap = 0;
//
//        values = Arrays.copyOf(copy, SIZE);
//
//        insertionSort();
//
//        System.out.println("InsertionSort\t\t"+Sorts.swap+"\t\t"+Sorts.comparison);
//
//        Sorts.comparison = 0;
//
//        Sorts.swap = 0;
//
//        values = Arrays.copyOf(copy, SIZE);
//
//        mergeSort(0, SIZE - 1);
//
//        System.out.println("MergeSort\t\t"+Sorts.swap+"\t\t"+Sorts.comparison);
//
//        Sorts.comparison = 0;
//
//        Sorts.swap = 0;
//
//        values = Arrays.copyOf(copy, SIZE);
//
//        quickSort(0, SIZE - 1);
//
//        System.out.println("QuickSort\t\t"+Sorts.swap+"\t\t"+Sorts.comparison);
//
//        Sorts.comparison = 0;
//
//        Sorts.swap = 0;
//
//        values = Arrays.copyOf(copy, SIZE);
//
//        heapSort();
//
//        System.out.println("HeapSort\t\t"+Sorts.swap+"\t\t"+Sorts.comparison);

       /*printValues();

       System.out.println("values is sorted: " + isSorted());

       System.out.println();*/

    }


    static int minIndex(int startIndex, int endIndex)
    {
        int indexOfMin = startIndex;
        for (int index = startIndex + 1; index <= endIndex; index++){
            comparison++;
            if (values[index] < values[indexOfMin])
                indexOfMin = index;
        }
        return indexOfMin;
    }

    static void selectionSort()
    {
        int endIndex = SIZE - 1;
        for (int current = 0; current < endIndex; current++)
            swap(current, minIndex(current, endIndex));
    }
}
