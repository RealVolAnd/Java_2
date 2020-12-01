package Homework_5;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        float[] arr = initArray();
        System.out.println("Processing tasks...");

        float resArray1[] = arrayProcessorSingleThread(arr);
        float resArray2[] = arrayProcessorDoubleThread(arr);

        System.out.println("Resulting arrays " + ((Arrays.equals(resArray1, resArray2)) ? "are" : "not are")+" equiv");
    }

    private static float[] initArray() {
        final int size = 10000000;
        float[] arr = new float[size];
        for (float f : arr) {
            f = 1f;
        }
        return arr;
    }


    /*
    Однопотоковый расчет массива
     */
    private static float[] arrayProcessorSingleThread(float[] arr) {

        long a = System.currentTimeMillis();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        System.out.println("1.Single thread exec time,ms: " + (System.currentTimeMillis() - a));

        return arr;
    }

    /*
    Расчет массива в 2 потока
     */
    private static float[] arrayProcessorDoubleThread(float[] arr) {
        final int h = arr.length / 2;
        float[] ar1 = new float[h];
        float[] ar2 = new float[h];

        long a = System.currentTimeMillis();

        System.arraycopy(arr, 0, ar1, 0, h);
        System.arraycopy(arr, h, ar2, 0, h);

        Runnable run1 = () -> {
            for (int i = 0; i < ar1.length; i++) {
                ar1[i] = (float) (ar1[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            }
        };
        Thread thr1 = new Thread(run1);
        thr1.start();

        Runnable run2 = () -> {
            for (int i = 0; i < ar2.length; i++) {
                ar2[i] = (float) (ar2[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            }
        };
        Thread thr2 = new Thread(run2);
        thr2.start();

        System.arraycopy(ar1, 0, arr, 0, h);
        System.arraycopy(ar2, 0, arr, h, h);
        System.out.println("2.Double thread exec time,ms: " + (System.currentTimeMillis() - a));

        return arr;
    }



}
