package algorithm;
import java.util.ArrayList;

import util.ConsoleStyle;
import util.DataManager;


/**
 * Created by nour on 5/7/17.
 */
@SuppressWarnings("unchecked")
public class ShannonFano {

    private ArrayList<Integer> frequency;
    private ArrayList<Character> chars;
    private ArrayList<Integer> frequency_final = new ArrayList<>();
    private ArrayList<String> binarycode_final = new ArrayList<>();
    private int frequencyCount =0;
    public ShannonFano(String str){
        //gets the string length (frequecny count)
        frequencyCount = str.length();
        //prepares the given string and returns 2 arraylists on of which contains
        //characters and the other contains frequencies, both are in descending order..
        ArrayList<ArrayList> data = DataManager.prepareData(str, DataManager.ORDER.DESC);
        chars = (ArrayList<Character>) data.get(0);
        frequency = (ArrayList<Integer>) data.get(1);
        //sets the console title & printing input data
        ConsoleStyle.setTitle("Input Data", ConsoleStyle.COLOR.blue,
                ConsoleStyle.COLOR.white);
        ConsoleStyle.setBold();
        System.out.print("String: ");
        ConsoleStyle.reset();
        ConsoleStyle.setColor(ConsoleStyle.COLOR.blue);
        System.out.println(str);
        ConsoleStyle.reset();
        ConsoleStyle.setBold();
        System.out.println("\r\nCharacter            Frequency");
        ConsoleStyle.reset();
        for(int i = 0; i < chars.size(); i++){
            System.out.println("    "+chars.get(i) + "\t\t\t " + frequency.get(i));
        }

    }
    //printing shannon calculations..
    public void printCalculations() {
        //Set title with styles..
        ConsoleStyle.setTitle("Shannon Results", ConsoleStyle.COLOR.blue,
                ConsoleStyle.COLOR.white);
        //Enthropy result
        ConsoleStyle.setBold();
        System.out.print("Enthropy: ");
        ConsoleStyle.reset();
        ConsoleStyle.setColor(ConsoleStyle.COLOR.blue);
        System.out.println(calculateEnthropy());
        //Compression Ratio results
        ConsoleStyle.setBold();
        System.out.print("Compression Ratio: ");
        ConsoleStyle.reset();
        ConsoleStyle.setColor(ConsoleStyle.COLOR.blue);
        System.out.print(calculateCompressionRatio());
        ConsoleStyle.reset();
        System.out.println("\r\n\r\n\r\n");
    }
    //Draws shannon coding map
    public void printTree(){
        //Variable to be used inside while loop
        ArrayList<Integer> arraylist_current_frequencylist;
        ArrayList<Character> arraylist_current_charlist;
        ArrayList<Character> tmpChar_lower;
        ArrayList<Character> tmpChar_higher;
        ArrayList<Integer> tmpFreq_lower;
        ArrayList<Integer> tmpFreq_higher;
        int i;
        int count = 0;
        int level = 1;
        int sum;
        int avgFrequencyKey;
        boolean newLevel = false;
        String binaryString;
        String bin;
        //Add chars arraylist & frequecny arraylist to queue Arraylist
        ArrayList<Object> todo = new ArrayList<Object>(){{
            add(chars);
            add(frequency);
            add(0);
            add("");
        }};
        //Print title with styles
        ConsoleStyle.setTitle("Shannon Tree", ConsoleStyle.COLOR.blue,
                ConsoleStyle.COLOR.white);

        while (!todo.isEmpty()){
            count++;
            //Pop chars arraylist
            arraylist_current_charlist = (ArrayList<Character>) todo.remove(0);
            //pop frequency arraylist
            arraylist_current_frequencylist = (ArrayList<Integer>) todo.remove(0);
            //pop level
            level = (int) todo.remove(0);
            //pop parent code
            binaryString = (String) todo.remove(0);
            //setting tmp arraylists to split the currrent list
            tmpChar_lower = new ArrayList<>();
            tmpChar_higher = new ArrayList<>();
            tmpFreq_lower = new ArrayList<>();
            tmpFreq_higher = new ArrayList<>();

            //get current freuency count
            sum = DataManager.getSum(arraylist_current_frequencylist);
            //calculate the avg key at which we will split the arraylist
            //into semi-equal frequency arraylist
            avgFrequencyKey = DataManager
                    .frequencyAvgKey(arraylist_current_frequencylist, sum);
            //splitting current arraylist into two
            for (i = 0; i < arraylist_current_frequencylist.size(); i++){
                if(i <= avgFrequencyKey){
                    tmpChar_higher.add(arraylist_current_charlist.get(i));
                    tmpFreq_higher.add(arraylist_current_frequencylist.get(i));
                }else{
                    tmpChar_lower.add(arraylist_current_charlist.get(i));
                    tmpFreq_lower.add(arraylist_current_frequencylist.get(i));
                }
            }
            //Prinnt iteration count in bold
            ConsoleStyle.setBold();
            System.out.println("\r\n");
            System.out.print("Iteration: "+ count);
            ConsoleStyle.reset();
            //Print current node
            System.out.println();
            System.out.print("\t\tNode:  ");
            ConsoleStyle.setColor(ConsoleStyle.COLOR.green);
            //print chars in this node
            for(char cs : arraylist_current_charlist){
                System.out.print("["+cs+"]");
            }

            sum = 0;
            for(int freq : arraylist_current_frequencylist){
                sum += freq;
            }
            //print frequency sum for this node
            ConsoleStyle.setColor(ConsoleStyle.COLOR.yello);
            System.out.print(" <"+sum+">");
            ConsoleStyle.setColor(ConsoleStyle.COLOR.blue);
            //print tree level for this node
            System.out.print(" (level "+level+")");
            System.out.println();
            ConsoleStyle.reset();
            //setting new level flag to false
            newLevel = false;
            //If higher arraylist (left side) is not empty..
            if(!tmpChar_higher.isEmpty()) {
                //if higher arraylist is still larger than 1 character
                if (tmpChar_higher.size() > 1) {
                    //add both char and frequency arraylist into queue for
                    //further splitting
                    todo.add(tmpChar_higher);
                    todo.add(tmpFreq_higher);
                    todo.add(level+1);
                    todo.add(binaryString + "0");
                    //print node children info
                    System.out.print("\t\tLeft:  ");
                    ConsoleStyle.setColor(ConsoleStyle.COLOR.green);
                    for(char cs : tmpChar_higher){
                        System.out.print("[" +  cs + "]");
                    }
                    sum = 0;
                    for(int freq : tmpFreq_higher){
                        sum += freq;
                    }
                    ConsoleStyle.setColor(ConsoleStyle.COLOR.yello);
                    System.out.print(" <"+sum+">");
                    ConsoleStyle.reset();

                }
                //if higher arraylist contains only one char
                else {
                    //add character code and frequency to a final arraylist
                    //for further calculations..
                    binarycode_final.add(binaryString + "0");
                    frequency_final.add(tmpFreq_higher.get(0));
                    //printing node info with styles..
                    System.out.print("\t\tLeft:  [");
                    ConsoleStyle.setColor(ConsoleStyle.COLOR.green);
                    System.out.print(tmpChar_higher.get(0));
                    ConsoleStyle.setColor(ConsoleStyle.COLOR.yello);
                    System.out.print(" <" + tmpFreq_higher.get(0) + ">");

                    ConsoleStyle.setColor(ConsoleStyle.COLOR.purple);
                    System.out.print(" '" +binaryString+"0" + "'");
                    ConsoleStyle.reset();
                    System.out.print("]");
                }
            }
            System.out.println();
            //If lower arraylist (right side) is not empty..
            if(!tmpFreq_lower.isEmpty()) {
                //if lower arraylist is still larger than 1 character
                if (tmpChar_lower.size() > 1) {
                    //add both char and frequency arraylist into queue for further splitting
                    todo.add(tmpChar_lower);
                    todo.add(tmpFreq_lower);
                    todo.add(level+1);
                    todo.add(binaryString + "1");
                    //print node children info
                    System.out.print("\t\tRight: ");
                    ConsoleStyle.setColor(ConsoleStyle.COLOR.green);
                    for(char cs : tmpChar_lower){
                        System.out.print("[" +  cs + "]");
                    }
                    sum = 0;
                    for(int freq : tmpFreq_lower){
                        sum += freq;
                    }
                    ConsoleStyle.setColor(ConsoleStyle.COLOR.yello);
                    System.out.print(" <"+sum+">");
                    ConsoleStyle.reset();

                } else {
                    //add character code and frequency to a
                    //final arraylist for further calculations..
                    binarycode_final.add(binaryString + "1");
                    frequency_final.add(tmpFreq_lower.get(0));
                    //printing node info with styles..
                    System.out.print("\t\tRight: [");
                    ConsoleStyle.setColor(ConsoleStyle.COLOR.green);
                    System.out.print(tmpChar_lower.get(0));
                    ConsoleStyle.setColor(ConsoleStyle.COLOR.yello);
                    System.out.print(" <" + tmpFreq_lower.get(0) + ">");
                    ConsoleStyle.setColor(ConsoleStyle.COLOR.purple);
                    System.out.print(" '" +binaryString+1 + "'");
                    ConsoleStyle.reset();
                    System.out.print("]");
                }
            }
            System.out.println();
        }
        System.out.println();

    }
    //Calculate the compression Ratio
    public double calculateCompressionRatio(){
        int frequency;
        int sum = 0;
        String code;
        //number of bits before compression
        int noOfBitsBefore = frequencyCount*8;
        //number of binary bits after compression
        for(int i = 0; i< binarycode_final.size(); i++){
            frequency = frequency_final.get(i);
            code = binarycode_final.get(0);
            sum += frequency*code.length();
        }
        return (double) (noOfBitsBefore / sum);
    }
    //Calculate enthropy..
    public double calculateEnthropy(){
        double result = 0;
        double LogPi;
        double PI;
        double summation = 0;
        //frequency iterator
        for (int freq: frequency){
            PI = (double) freq/frequencyCount ;
            //Converting Java.log (ln) to Log2
            LogPi = Math.log(1/(PI)) / Math.log(2);
            summation = PI*LogPi;
            result+= summation;
        }
        return result;

    }
}
