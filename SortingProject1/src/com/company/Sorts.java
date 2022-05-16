package com.company;
    import java.util.*;
    import java.text.DecimalFormat;

    public class Sorts {

        static final int SIZE = 5000; // size of array to be sorted

        static int[] values = new int[SIZE]; // values to be sorted

        static int swap = 0;

        static int comparison = 0;

        static int[] initValues()
        // Initializes the values array with random integers from 0 to 99.
        {
            Random rand = new Random();
            for (int index = 0; index < SIZE; index++)
                values[index] = Math.abs(rand.nextInt()) % 10000;
            return values;
        }

        static public boolean isSorted()
        // Returns true if the array values are sorted and false otherwise.
        {
            boolean sorted = true;
            for (int index = 0; index < (SIZE - 1); index++)
                if (values[index] > values[index + 1])
                    sorted = false;
            return sorted;
        }

        static public void swap(int index1, int index2)
        {
            swap++;
            int temp = values[index1];
            values[index1] = values[index2];
            values[index2] = temp;
        }

        static public void printValues()
        {
            int value;
            DecimalFormat fmt = new DecimalFormat("00");
            System.out.println("The values array is:");
            for (int index = 0; index < SIZE; index++) {
                value = values[index];
                if (((index + 1) % 10) == 0)
                    System.out.println(fmt.format(value));
                else
                    System.out.print(fmt.format(value) + " ");
            }
            System.out.println();
        }
    }