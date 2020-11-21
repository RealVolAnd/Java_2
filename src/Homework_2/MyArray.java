package Homework_2;

public class MyArray {
    private String[][] myArray;

    public MyArray(int arraySize) {
        myArray = new String[arraySize][arraySize];
        for (int i = 0; i < arraySize; i++) {
            for (int j = 0; j <arraySize ; j++) {
                myArray[i][j] = String.valueOf(i)+j;
            }
        }
    }

    public String[][] getMyArray(){
        return myArray;
    }

    public String toString(){
        String result ="";
            for (int i = 0; i < myArray.length; i++) {
                    for (int j = 0; j < myArray[i].length; j++) {
                        result+="\t"+myArray[i][j];
                    }
                result+="\n";
            }
        return result;
    }

}
