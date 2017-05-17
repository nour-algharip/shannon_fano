import java.util.ArrayList;
import java.util.Scanner;

import algorithm.ShannonFano;

/**
 * Created by nour on 5/10/17.
 */

public class Main {

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);  // Reading from System.in
        System.out.println("Please enter a string to compress: ");
        //Scans the next line of the input as an String.
        String d = reader.nextLine();
        ShannonFano shannonFano = new ShannonFano(d);

        //Draws shannon coding map
        shannonFano.printTree();
        //printing calculations..
        shannonFano.printCalculations();
    }
}
