import java.util.Scanner;
public class SafeInput {
    public static String getNonZeroLenString(Scanner pipe, String prompt) {

        String retString = "";
        do {

            System.out.print("\n" + prompt + ": ");
            retString = pipe.nextLine();

        } while(retString.length() == 0);

        return retString;
    }
    public static int getInt(Scanner pipe, String prompt) {

        int userNum = 0;

        System.out.print("\n" + prompt + ": ");

        do {
            pipe.nextLine();
            System.out.print("\n" + prompt + ": ");
        } while (!pipe.hasNextInt());

        userNum = pipe.nextInt();

        return userNum;
    }
    public static double getDouble(Scanner pipe, String prompt) {

        double userNum = 0;

        System.out.print("\n" + prompt + ": ");

        do {
            pipe.nextLine();
            System.out.print("\n" + prompt + ": ");
        } while (!pipe.hasNextDouble());

        userNum = pipe.nextDouble();

        return userNum;
    }
    public static int getRangedInt(Scanner pipe, String prompt, int low, int high) {

        int userNum = 0;
        int temp = 0;
        boolean isNum = false;

        System.out.print("\n" + prompt + " [" + low + " - " + high + "]: ");

        do {
            if(pipe.hasNextInt()) {
                temp = pipe.nextInt();
                if(temp >= low && temp <= high) {
                    userNum = temp;
                    isNum = true;
                }
            } if(!isNum) {
                pipe.nextLine();
                System.out.print("\n" + prompt + " [" + low + " - " + high + "]: ");
            }

        } while (!isNum);

        return userNum;
    }
    public static double getRangedDouble(Scanner pipe, String prompt, double low, double high) {

        double userNum = 0;
        double temp = 0;
        boolean isNum = false;

        System.out.print("\n" + prompt + " [" + low + " - " + high + "]: ");

        do {
            if (pipe.hasNextDouble()) {
                temp = pipe.nextDouble();
                if (temp >= low && temp <= high) {
                    userNum = temp;
                    isNum = true;
                }
            }
            if (!isNum) {
                pipe.nextLine();
                System.out.print("\n" + prompt + " [" + low + " - " + high + "]: ");
            }
        } while (!isNum);

        return userNum;
    }
    public static boolean getYNConfirm(Scanner pipe, String prompt) {

        String choice = "";

        do {

            System.out.print("\n" + prompt + "[Y/N]: ");
            choice = pipe.nextLine();

        } while(!choice.equalsIgnoreCase("y") && !choice.equalsIgnoreCase("n"));

        return choice.equalsIgnoreCase("y");

    }

    public static String getRegExString(Scanner pipe, String prompt, String regEx) {

        String patMatch = "";
        boolean isDone = false;
        String trash = "";

        do {
            System.out.println(prompt + ": [" + regEx + "]: ");
            if(pipe.hasNext()) {
                if(pipe.next().matches(regEx)) {
                    patMatch = pipe.nextLine();
                    isDone = true;
                } else {
                    trash = pipe.nextLine();
                    System.out.println("Please ensure input matches the pattern");
                    pipe.nextLine();
                }
            } else {
                System.out.println(prompt + ": [" + regEx + "]: ");
                pipe.nextLine();
            }
        } while(!isDone || trash.length() > 0);

        return patMatch;
    }
    public static void prettyHeader(String msg) {

        int len = 60;
        int cent = ((len - msg.length()) / 2) - 2;
        String centMsg = String.format("%" + (cent + msg.length()) + "s", msg);

        for(int i = 0; i < 61; i++) {
            System.out.print("*");
        }

        System.out.println();

        for(int i = 0; i < 3; i++) {
            System.out.print("*");
        }

        System.out.print(centMsg);

        for(int i = 0; i < 17; i++) {
            System.out.print(" ");
        }

        for(int i = 0; i < 3; i++) {
            System.out.print("*");
        }

        System.out.println();

        for(int i = 0; i < 61; i++) {
            System.out.print("*");
        }
    }
}
