import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Random;
import java.util.concurrent.ExecutionException;

/**
 * A glossary program.
 *
 * @author Johan Birgersson
 */

public class Assignment4 {

    /**
     * main executes run method.
     * @param args not used
     */

    public static void main(String[] args) { run(); }

    /**
     * Method user_interface gives the user instructions on the program.
     */
    public static void user_interface(){
        System.out.println("**GLOSÖVNING - ENGELSKA**\nSkriv det engelska ordet. Avsluta programmet genom att skriva Q");
    }

    /**
     * run method takes gives word from swedish glossary -> <br>
     * takes user input -> checks with english glossary if correct(and to what extent correct). <br>
     * And keeps score of tally.
     */
    public static void run() {
        user_interface();
        Scanner scanner = new Scanner(System.in);
        String user_answer = "";
        boolean quit = false;

        GlossaryArrays glossaryArrays = new GlossaryArrays();
        String[] Swe_words = glossaryArrays.getSwe_words();
        String[] Eng_words = glossaryArrays.getEng_words();


        try {
            while (!quit) {

                int i = 0;
                int attempts = 0;
                int score = 0;
                int wordlength= Eng_words[i].length();

                //
                for (i = 0; i == attempts; i++) {
                    System.out.println(Swe_words[i] + " = ");
                    user_answer = scanner.next().trim();


                    if (user_answer.equalsIgnoreCase("Q")) {
                        System.out.println("Du svarade på totalt " + attempts + " glosor och hade " + score + " rätt. Välkommen åter!");
                        quit = true;
                    } else if (user_answer.equalsIgnoreCase(Eng_words[i])) {
                        attempts++;
                        score++;
                        //if there have been same number of attempts as words in the glossary program is terminated
                        if (attempts == Swe_words.length) {
                            System.out.println("Korrekt!\nDu svarade på totalt " + attempts + " glosor och hade " + score + " rätt. Välkommen åter!");
                            quit=true;
                        } else {
                            System.out.println("Korrekt! " + score + " rätt av " + attempts + " ord.");

                        }

                    }

                    // if 50% of the letters are correct out says "Almost correct".
                    else if (user_answer.compareToIgnoreCase(Eng_words[i].substring(0,wordlength)) >= 0.5*wordlength) {
                        attempts++;
                        if (attempts == Swe_words.length) {
                            System.out.println("Nästan korrekt. Rätt svar är " + Eng_words[i] + ".\nDu svarade på totalt " + attempts + " glosor och hade " + score + " rätt. Välkommen åter!");
                            quit=true;
                        } else {
                            System.out.println("Nästan korrekt. Rätt svar är " + Eng_words[i] + ".");
                        }

                    }


                    else {
                        attempts++;
                        if (attempts == Swe_words.length) {
                            System.out.println("Ej korrekt. Rätt svar är " + Eng_words[i] + ".\nDu svarade på totalt " + attempts + " glosor och hade " + score + " rätt. Välkommen åter!");
                            quit=true;
                        } else {
                            System.out.println("Ej korrekt. Rätt svar är " + Eng_words[i] + ".");
                        }
                    }
                }
            }
        }
        catch (Exception u)  {
            return;


        }

    }
}



