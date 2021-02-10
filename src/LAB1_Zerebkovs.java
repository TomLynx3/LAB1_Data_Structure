import java.io.*;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class LAB1_Zerebkovs {
    static String red = "\u001B[31m";
    static String cyan= "\u001B[36m";
    static String reset ="\u001B[0m";
    static String green = "\u001B[32m";
    static String magenta ="\u001B[35m";


    public static void main (String[] args){
       boolean closeProgramm = false;


        //У меня без указание пути не работало

        int[] readArray= readArray("/Users/tomlynx/Desktop/RTU/strukturas/LAB1_Zerebkovs/src/source1.txt");


        if(readArray ==null){
            int arraySize=0;
            System.out.println("source.txt Nav atrasts");

            Scanner userInput = new Scanner(System.in);

           do{
               System.out.println("Ievadiet masīva izmēru,Jābūt pozitīvam veselam skatilim un nepārsniedzot 10000");
               if(userInput.hasNextInt()){
                   arraySize = userInput.nextInt();
                   if(arraySize >0 && arraySize<10000){

                    int[] array = randomArray(arraySize,10000,-10000);

                    showArray(array,getArrayMaxValueIndex(array),getArrayMinValueIndex(array));
                    writeOnFile(array,"result.txt");
                    double averageValue = getArrayAverage(array);
                    if(averageValue> 0){
                        System.out.println("Massīva videja vērtība ir: " +green +averageValue );

                    }else{
                        System.out.println("Massīva videja vērtība ir: " +red +averageValue );

                    }
                       System.out.print(reset);
                    boolean isAscending = isAscending(array);
                    if(isAscending){
                        System.out.println("Masīvs"+red+" ir "+reset+"sakortots augoša secība");
                    }else {
                        System.out.println("Masīvs"+red+" nav "+reset+"sakortots augoša secība");
                    }

                    System.out.println("Masīva minimāla vērtiba atrodas pozicija: "+magenta+ getArrayMinValueIndex(array));
                       System.out.print(reset);
                    System.out.println("Masīva maksimāla vērtiba atrodas pozicija: "+ magenta+getArrayMaxValueIndex(array));
                       System.out.print(reset);


                       System.out.println("Sakt no sākuma? Ja-1 Ne-0?");

                       if(userInput.nextInt() !=1){
                           closeProgramm = true;

                           userInput.close();

                       }

                   }else {
                       System.out.println("Kļūda, Skaitlis ir negatīvs vai parsniedz 10000");
                       System.out.println("Meigini vēlreiz");
                   }
               }else{
                   System.out.println("Kļūda skaitlis nav vesels");
                   System.out.println("Meigini vēlreiz");
                   userInput.next();
               }

           }while(!closeProgramm);

        }else {
            showArray(readArray,getArrayMaxValueIndex(readArray),getArrayMinValueIndex(readArray));
            writeOnFile(readArray,"result.txt");
            double averageValue = getArrayAverage(readArray);
            if(averageValue >0){
                System.out.println("Massīva videja vērtība ir: "+green+averageValue );
            }else{
                System.out.println("Massīva videja vērtība ir: "+red+averageValue );
            }

            System.out.print(reset);
            boolean isAscending = isAscending(readArray);
            if(isAscending){
                System.out.println("Masīvs"+red+" ir "+reset+"sakortots augoša secība");
            }else {
                System.out.println("Masīvs"+red+" nav "+reset+"sakortots augoša secība");

            }

            System.out.println("Masīva minimāla vērtiba atrodas pozicija: "+magenta+ getArrayMinValueIndex(readArray));
            System.out.print(reset);
            System.out.println("Masīva maksimāla vērtiba atrodas pozicija: "+magenta+ getArrayMaxValueIndex(readArray));
            System.out.print(reset);
        }

    }

    static int [] readArray (String path) {
        try {
            Scanner file = new Scanner( new File(path) );
            int[] array = new int[10000];
            int i = 0;
            while( file.hasNextInt() ) array[i++] = file.nextInt();
            file.close();
            return Arrays.copyOf(array, i);
        }
        catch (FileNotFoundException e) {
            return null;
        }

    }


    static void showArray( int[] array,int min,int max ) {

        for(int i = 0; i < array.length; i++) {
            if(i==min || i==max){
                System.out.format("%7d"+cyan+"*", array[i]);
                System.out.print(reset);
            }else{
                System.out.format("%7d ", array[i]);
            }

            if (i % 10 == 9) System.out.println();
        }

        if (array.length % 10 != 0) System.out.println();
    }

    static void writeOnFile(int [] array,String fileName){
        try{
            PrintWriter file = new PrintWriter(new FileWriter(fileName));
            for(int i = 0 ; i <array.length;i++){
                file.println(array[i]);

            }
            file.close();
        }catch (IOException e){
         e.printStackTrace();
        }

    }

    static double getArrayAverage(int [] array){
        int sum = 0;

        for(int i =0;i<array.length;i++){
            sum += array[i];
        }
        return sum/array.length;
    }

    static boolean isAscending(int array[]){

        for(int i=0;i<array.length-1;i++){

            if(array[i]>array[i+1]){
                return  false;

            }
        }
        return true;
    }



    static int[] randomArray(int arraySize,int max,int min){
        int[] array = new int[arraySize];
        Random randomNumber = new Random();

        for(int i = 0;i<array.length;i++){
            array[i]= randomNumber.nextInt((max - min) + 1) + min;
        }

        return array;
    }



    static int getArrayMinValueIndex(int [] array){
        int value=array[0];
        int index=0;
        for(int i=0;i<array.length;i++){
           if(value>array[i]){
             value =array[i];
             index=i;
           }
        }
        return index;
    }
    static int getArrayMaxValueIndex(int [] array){
        int value=array[0];
        int index =0;
        for(int i=0;i<array.length;i++){
            if(value<array[i]){
                value =array[i];
                index = i;
            }
        }
        return index;
    }
}
