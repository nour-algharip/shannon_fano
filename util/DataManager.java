package util;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by nour on 5/10/17.
 */

public class DataManager {
    public enum ORDER{
        DESC, ASC
    }
    //searches an arraylist by value and returning the corresponding key..
    private static int findKeyInArrayList(ArrayList<Character> list, char value){
        for (int i = 0;i<list.size();i++){
            if(list.get(i).equals(value)) return i;
        }
        return -1;
    }

    //prepare string data into 2 arraylist, one for chars and one for frequency
    //with same order..
    public static ArrayList<ArrayList> prepareData(String str, ORDER order){
        ArrayList<ArrayList> data;
        str = str.trim().toUpperCase();
        int[] asciiArray = new int[str.length()];

        //transform char into ascii..
        int i = 0;
        for(char c: str.toCharArray()){
            asciiArray[i] = (int) c;
            i++;
        }
        //sorting alphapetically
        asciiArray = bubbleSort(asciiArray);
        //calculating frequency
        ArrayList chars = new  ArrayList<>();
        ArrayList frequency = new  ArrayList<>();
        char c;
        int frequencyCounter = 0;
        char c_old = 0;
            for(int ascii: asciiArray){
            c = (char) ascii;
            if(c == c_old){
                frequencyCounter++;
            }else{
                if(frequencyCounter != 0){
                    frequency.add(frequencyCounter);
                    chars.add(c_old);
                    frequencyCounter = 0;
                }
                frequencyCounter++;
                c_old = c;
            }

        }
        if (frequencyCounter != 0) {
            frequency.add(frequencyCounter);
            chars.add(c_old);
        }
        //sorting by frequency in descend order..
        data  = DataManager.sortFrequency(chars, frequency);

        chars = data.get(0);
        frequency = data.get(1);
        if(order == ORDER.ASC){
            Collections.reverse(chars);
            Collections.reverse(frequency);
            data = new ArrayList();
            data.add(chars);
            data.add(frequency);
        }
        return data;
    }
    //sort char & frequecny arraylists according to frequency in descend order
    public static ArrayList<ArrayList> sortFrequency(ArrayList<Character> chars,
                                                     ArrayList<Integer>
            frequency){
        int n = chars.size();
        int tempFreq = 0;
        char tempChar;
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < (n - i); j++) {
                if (frequency.get(j - 1) < frequency.get(j)) {
                    tempFreq = frequency.get(j - 1);
                    tempChar = chars.get(j-1);
                    frequency.set(j - 1, frequency.get(j));
                    chars.set(j-1, chars.get(j));
                    frequency.set(j, tempFreq);
                    chars.set(j, tempChar);
                }

            }
        }
        ArrayList<ArrayList> result = new ArrayList<>();

        result.add(chars);
        result.add(frequency);
        return result;
    }
    //sorts array of integers into descending order
    /*
    ###
    @ copyright NINCOMPOOP
    ###
    */
    public static int[] bubbleSort(int[] numArray) {

        int n = numArray.length;
        int temp = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 1; j < (n - i); j++) {

                if (numArray[j - 1] > numArray[j]) {
                    temp = numArray[j - 1];
                    numArray[j - 1] = numArray[j];
                    numArray[j] = temp;
                }
            }
        }
        return numArray;
    }
    //finds the key at which the frequency summation is in close range on both sides
    public static int frequencyAvgKey(ArrayList<Integer> list, int sum){
        int s = 0;
        double mean = (double) sum / 2;
        double dif1;
        double dif2;
        for (int i = 0; i < list.size(); i++){
            s += list.get(i);
            if(s >= mean){
                dif1 = s- mean;
                if(i == 0) return i;
                dif2 = mean - list.get(i-1);
                if(dif1 > dif2){
                    return i-1;
                }else{
                    return i;
                }
            }
        }
        return 0;

    }
    //get summation of values in an integer arraylist
    public static int getSum(ArrayList<Integer> list){
        int sum = 0;
        for (int item : list){
            sum += item;
        }
        return sum;
    }

}
