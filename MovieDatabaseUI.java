package Inlämning5;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;



/**
 * A command line user interface for a movie database.
 */
public class MovieDatabaseUI {
    private Scanner _scanner;
    String outputFilePath= "./moviedatabase.txt";

    /**
     * Construct a MovieDatabaseUI.
     */
    public MovieDatabaseUI() {

    }
    /**
     * Start the movie database UI.
     */
    public void startUI() throws IOException {
        _scanner = new Scanner(System.in);
        int input;
        boolean quit = false;

        System.out.println("** FILMDATABAS **");

        while(!quit) {
            input = getNumberInput(_scanner, 1, 4, getMainMenu());

            switch(input) {
                case 1: searchTitel(); break;
                case 2: searchReviewScore(); break;
                case 3: addMovie(); break;
                case 4: quit = true;
            }
        }
        //Close scanner to free resources
        _scanner.close();
    }


    /**
     * Get input and translate it to a valid number.
     *
     * @param scanner the Scanner we use to get input
     * @param min the lowest correct number
     * @param max the highest correct number
     * @param message message to user
     * @return the chosen menu number
     */
    private int getNumberInput(Scanner scanner, int min, int max, String message) {
        int input = -1;

        while(input < 0) {
            System.out.println(message);
            try {
                input = Integer.parseInt(scanner.nextLine().trim());
            }
            catch(NumberFormatException nfe) {
                input = -1;
            }
            if(input < min || input > max) {
                System.out.println("Ogiltigt värde.");
            }
        }
        return input;
    }

    /**
     * Get search string from user, search title in the movie
     * database and present the search result.
     */
    private void searchTitel() throws IOException {
        System.out.print("Ange sökord: ");
        String title = _scanner.nextLine().trim();
        Scanner sc;
        //call to search movie database based on input

        File file = new File(outputFilePath);

        try{
            sc = new Scanner(file);

            while (sc.hasNextLine()){
                final String lineFromFile=sc.nextLine();
                final String line= lineFromFile.toLowerCase();
                //dividing the string into title and review score
                int lastIndex = line.length()-1;
                String onlyTitle = lineFromFile.substring(0,lastIndex);
                String onlyScore = lineFromFile.substring(lastIndex,lastIndex+1);
                if(lineFromFile.contains(title.toLowerCase())) {
                    System.out.println("Titel: "+onlyTitle + "Betyg: "+ onlyScore+"/5.");
                }
            }
        }catch(Exception e){
            return;

        }

        sc.close();

    }


    /**
     * Get search string from user, search review score in the movie
     * database and present the search result.
     */
    private void searchReviewScore() {
        int review = getNumberInput(_scanner, 1, 5, "Ange minimibetyg (1 - 5): ");
        Scanner sc;
        File file = new File(outputFilePath);

        try{
            sc = new Scanner(file);

            while (sc.hasNextLine()){
                final String lineFromFile=sc.nextLine();
                //dividing the string into title and review score
                int lastIndex = lineFromFile.length()-1;
                String onlyTitle = lineFromFile.substring(0,lastIndex);
                String stringScore = lineFromFile.substring(lastIndex,lastIndex+1);
                int intScore = Integer.parseInt(stringScore);
                if(intScore >=review) {
                    System.out.println("Titel: "+onlyTitle + "Betyg: "+ intScore+"/5.");
                }

            }

        }catch(Exception e){
            return;

        }
    }


    /**
     * Get information from user on the new movie and add
     * it to the database.
     *
     */
    private void addMovie() throws IOException {
        System.out.print("Titel: ");
        String title = _scanner.nextLine().trim();
        int reviewScore = getNumberInput(_scanner, 1, 5, "Betyg (1 - 5): ");

        Path newTextFilePath = Paths.get(outputFilePath);

        if (!Files.exists(newTextFilePath)) {
            Files.createFile(newTextFilePath);
        }

        List<String> allLines = Files.readAllLines(newTextFilePath);

        allLines.add(title+"\t"+reviewScore);

        Files.write(newTextFilePath, allLines);

        if(Files.exists(newTextFilePath)) {
            System.out.println(title + " lades till i databasen med betyget " + reviewScore + ".");
        }
    }


    /**
     * Return the main menu text.
     *
     * @return the main menu text
     */
    private String getMainMenu() {
        return  "-------------------\n" +
                "1. Sök på titel\n" +
                "2. Sök på betyg\n" +
                "3. Lägg till film\n" +
                "-------------------\n" +
                "4. Avsluta";
    }






}


