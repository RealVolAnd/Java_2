package Homework_2;
/*
Для упрощения не создается каой либо центральной системы, генерирующей ошибочные массивы.
Варианты массивов тестируются последовательно. Соответственно, прошу не ругать за hardcoding.))
Так же внесены искусственные задержки в выполнение Main Thread, для того, чтобы трейсы выводились на своих местах.
Видимо трассировка осуществляется в своем потоке, и если не вставлять задержку, трейсы выводятся в произвольном месте.
Метод вывода в консоль не синхронизирован.
 */

import Homework_1.Console;

public class Main {
    public static void main(String[] args) {
        MyArray myArray;
        long sum=0l;
/*
Проверяем массив без ошибок и получаем результат.
 */
        Console.printHeader("-------------------------------  GOOD ARRAY ------------------------------ ");
        myArray=new MyArray(4);
        try{

           System.out.println(myArray.toString());
           sum=testArray(myArray.getMyArray());
           Console.printHeaderNoNewLine("TEST PASSED. RESULT: "+sum);

        } catch(MyArrayException e){
            Console.printHeaderNoNewLine("ERROR TRACE: ");
            e.printStackTrace();
        }finally {
            myArray=null;
        }

/*
Проверяем массив, размерностью отличной от [4][4].
 */
        Console.printHeader("-------------------------------  BAD SIZES ARRAY ------------------------- ");
        myArray=new MyArray(5);
        try{

            System.out.println(myArray.toString());
            sum=testArray(myArray.getMyArray());
            Console.printHeaderNoNewLine("TEST PASSED. RESULT: "+sum);

        } catch(MyArrayException e){
            Console.printHeaderNoNewLine("ERROR TRACE: ");
            e.printStackTrace();
        }finally {
            myArray=null;
        }

        waitAMoment();
/*
Проверяем массив, содержащий символы вместо чисел.
 */
        Console.printHeader("-------------------------------  BAD CONTENT ARRAY ------------------------ ");
        myArray=new MyArray(4);
        myArray.getMyArray()[2][2]="One";
        try{

            System.out.println(myArray.toString());
            sum=testArray(myArray.getMyArray());
            Console.printHeaderNoNewLine("TEST PASSED. RESULT: "+sum);

        } catch(MyArrayException e){
            Console.printHeaderNoNewLine("ERROR TRACE: ");
            e.printStackTrace();
        }finally {
            myArray=null;
        }
        waitAMoment();
    }

    public static long testArray(String[][] testArray) throws MyArrayException{
        long result=0l;

        if (testArray.length!=4) throw new MyArraySizeException("Array sizes are ["+testArray.length+"]["+testArray.length+"]. Must be [4][4]");

        for (int i = 0; i < testArray.length; i++) {
            for (int j = 0; j <testArray.length ; j++) {
                try{
                    result+=Long.parseLong(testArray[i][j]);
                }catch (NumberFormatException e){
                    throw new MyArrayDataException("Array data is not a number in ["+i+"]["+j+"] = "+testArray[i][j],e);
                }

            }
        }

        return result;
    }

    private static void waitAMoment(){
        try {
            Thread.currentThread().sleep(300);
        }catch(Exception e){
            Console.printHeaderNoNewLine("Main Thread has some problems with sleep.....");
        }
    }
}