package util;

/**
 * Created by nour on 5/10/17.
 */

public class ConsoleStyle {
    public enum COLOR{
        green, yello, red, blue, white, purple,
    }
    //sets console background color
    public static void setBackground(COLOR color){
        switch (color){
            case red:
                System.out.print("\033[41;1m");
                break;
            case green:
                System.out.print("\033[42;1m");
                break;
            case yello:
                System.out.print("\033[43;1m");
                break;
            case blue:
                System.out.print("\033[44;1m");
                break;
            case white:
                System.out.print("\033[47;1m");

        }
    }
    //sets text color
    public static void setColor(COLOR color) {
        switch (color){
            case red:
                System.out.print("\033[31;1m");
                break;
            case green:
                System.out.print("\033[32;1m");
                break;
            case yello:
                System.out.print("\033[33;1m");
                break;
            case blue:
                System.out.print("\033[34;1m");
                break;

            case purple:
                System.out.print("\033[35;1m");
                break;
        }
    }
    //sets the console with default settings
    public static void reset() {
        System.out.print("\033[0;0m");
    }
    //Setting text bold
    public static void setBold(){
        System.out.print("\033[0;1m");
    }
    //Setting title style..
    public static void setTitle(String title, COLOR bgColor, COLOR textColor){
        setBackground(bgColor);
        setColor(textColor);
        System.out.println();
        String sign = "";
        for(int i=0; i<205; i++){
            sign += "=";
        }
        System.out.println(sign);
        System.out.print("\t\t\t\t\t\t\t\t\t\t\t\t");
        reset();
        setBold();
        setColor(textColor);
        System.out.print(title);
        reset();
        setBackground(bgColor);
        setColor(textColor);
        System.out.println();
        System.out.print(sign);
        reset();
        System.out.println();
        System.out.println();
    }
}
