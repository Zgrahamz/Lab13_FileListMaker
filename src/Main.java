import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.nio.file.*;

import static java.nio.file.StandardOpenOption.CREATE;

public class Main {
    public static void main(String[] args) {
        Scanner sca = new Scanner(System.in);
        String menuChoice;
        boolean quitChoice = false;
        boolean isDone = false;
        int listNum = 1;
        String userInput;
        int userIndex = 0;

        ArrayList<String> myArrList = new ArrayList<>();
        ArrayList<String> printedArrList = new ArrayList<>();

        ArrayList<String> recs = new ArrayList<>();
        recs.add("sjhsd");
        recs.add("dshkw");
        recs.add("fiwjcd");

        JFileChooser chooser = new JFileChooser();
        String rec = "";
        File selectedFile;
        boolean listSaved = true;
        boolean saveChoice = false;

        String prompt0 = "Please select a menu option: \nA - Add an item\nD - Delete an item\nI - Insert an item\nV - View the list\nQ - Quit" +
                "\nM - Move an item\nO - Open a saved list\nS - Save list\nC - Clear list";
        String prompt1 = "Enter a string to add to the list: ";
        String prompt2 = "Please enter the index of the item you would like to delete: ";
        String prompt3 = "Please enter the index of the item you would like to replace: ";
        String prompt4 = "Here is the current state of the list: ";
        String prompt5 = "Quit - are you sure?";
        String prompt6 = "Save changes?";

        do {

            System.out.println(prompt0 + "\n");
            menuChoice = sca.nextLine();

            if (menuChoice.equalsIgnoreCase("A")) {
                listNum = 1;
                System.out.println(prompt1 + "\n");
                userInput = sca.nextLine();
                myArrList.add(0, userInput);
                printedArrList.clear();
                for (int i = 0; i < myArrList.size(); i++) {
                    printedArrList.add(listNum + ". " + myArrList.get(i));
                    listNum++;
                }
                listSaved = false;
                System.out.println();
            }

            if (menuChoice.equalsIgnoreCase("D")) {
                listNum = 1;
                System.out.println(prompt2 + "\n");
                userIndex = sca.nextInt();
                myArrList.remove(userIndex - 1);
                printedArrList.clear();
                for (int i = 0; i < myArrList.size(); i++) {
                    printedArrList.add(listNum + ". " + myArrList.get(i));
                    listNum++;
                }
                listSaved = false;
                System.out.println();
            }

            if (menuChoice.equalsIgnoreCase("I")) {
                listNum = 1;
                System.out.println(prompt3 + "\n");
                userIndex = sca.nextInt();
                System.out.println(prompt1 + "\n");
                userInput = sca.nextLine();
                userInput = sca.nextLine();
                myArrList.add(userIndex - 1, userInput);
                printedArrList.clear();
                for (int i = 0; i < myArrList.size(); i++) {
                    printedArrList.add(listNum + ". " + myArrList.get(i));
                    listNum++;
                }
                listSaved = false;
                System.out.println();
            }

            if (menuChoice.equalsIgnoreCase("V")) {
                System.out.println(prompt4 + " ");
                System.out.print(printedArrList);
                System.out.println("\n");
            }

            if(menuChoice.equalsIgnoreCase("O")) {
                File workingDirectory = new File(System.getProperty("user.dir"));
                chooser.setCurrentDirectory(workingDirectory);
                selectedFile = chooser.getSelectedFile();
                try {


                    if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                        Path file = selectedFile.toPath();
                        InputStream in = new BufferedInputStream(Files.newInputStream(file, CREATE));
                        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                        int line = 0;
                        while (reader.ready()) {
                            rec = reader.readLine();
                            line++;
                            System.out.printf("\nLine %4d %-60s ", line, rec);
                        }
                        reader.close();
                        System.out.println("\n\nData file read!");
                    }
                } catch (FileNotFoundException e) {
                    System.out.println("File not found!");
                    e.printStackTrace();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                listSaved = false;
            }

            if(menuChoice.equalsIgnoreCase("S")) {
                File workingDirectory = new File(System.getProperty("user.dir"));
                Path file = Paths.get(workingDirectory.getPath() + "\\src\\data.txt");

                try {
                    OutputStream out = new BufferedOutputStream(Files.newOutputStream(file, CREATE));
                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));

                    for (String rec1 : recs) {
                        writer.write(rec1, 0, rec1.length());
                        writer.newLine();
                    }
                    writer.close();
                    System.out.println("Data file written");
                    System.out.println("List saved.");
                    listSaved = true;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (menuChoice.equalsIgnoreCase("C")) {
                myArrList.clear();
                System.out.println("List cleared");
                listSaved = false;
            }

            if (!listSaved) {
                SafeInput.getYNConfirm(sca, prompt6 + "\n");
                if(saveChoice) {
                    File workingDirectory = new File(System.getProperty("user.dir"));
                    Path file = Paths.get(workingDirectory.getPath() + "\\src\\data.txt");

                    try {
                        OutputStream out = new BufferedOutputStream(Files.newOutputStream(file, CREATE));
                        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));

                        for (String rec1 : recs) {
                            writer.write(rec1, 0, rec1.length());
                            writer.newLine();
                        }
                        writer.close();
                        System.out.println("Data file written");
                        System.out.println("List saved.");
                        listSaved = true;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            if (menuChoice.equalsIgnoreCase("Q")) {
                quitChoice = SafeInput.getYNConfirm(sca, prompt5 + "\n");
            }
            if (quitChoice) {
                isDone = true;
            }

        } while (!isDone);





    }
}