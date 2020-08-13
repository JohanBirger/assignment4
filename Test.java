import javax.xml.namespace.QName;
import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.stream.Collectors;

import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;

public class Test {

    Scanner sc= new Scanner(System.in);
    //System.out.println("Vad heter filmen?: ");
    String filmName = sc.next();
    String nameOfTextFile = "/"+filmName;
    String nameOfFile = "/"+filmName+".m4v";
    String fileProps = "This was added using newOutputStream";

    String databaseDirPath="/Users/Johan Birgersson/IdeaProjects/test/databaseDirPath";
    String infoTextSourceDir = "/Users/Johan Birgersson/IdeaProjects/test/testDir/testFile";
    final int BUFFERSIZE = 4 * 1024;
    String sourceFilePath = "/Users/Johan Birgersson/IdeaProjects/test/testDir/testMovie.m4v";
    String outputMVFilePath = databaseDirPath + nameOfFile;
    String outputTXTFilePath= databaseDirPath + nameOfTextFile;


    byte[] buffer = new byte[BUFFERSIZE];

    public static void main(String[] args) {
        Test ui = new Test();
        try {
            ui.UI();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //create new file textInfo in databasedirectory - make method
    private void addTextInfo(){
        System.out.print("Titel: ");
        String title = sc.nextLine().trim();
        //int reviewScore = getNumberInput(_scanner, 1, 5, "Betyg (1 - 5): ");

        byte data[] = fileProps.getBytes();
        Path newTextFilePath = Paths.get(outputTXTFilePath);

        try (OutputStream out = new BufferedOutputStream(
                Files.newOutputStream(newTextFilePath, CREATE, APPEND)
        )) {
            out.write(data, 0, data.length);
        } catch (IOException x) {
            System.err.println(x);
        }
    }



    //read existing file in databasedirectory - make method
    private void readFile() throws IOException {
        Path infoTextPath = Paths.get(infoTextSourceDir);
        String fileContent = new String(Files.readAllBytes(infoTextPath));
        System.out.println(fileContent);
    }


    //take file from userpath and add to database - make method
    private void addMovieFile() throws IOException {
        FileInputStream fileIn = new FileInputStream(new File(sourceFilePath));
        FileOutputStream fileOut = new FileOutputStream(new File(outputMVFilePath));
        System.out.println("Movie and Review added to " +databaseDirPath);


        while(fileIn.available() != 0) {
            fileIn.read(buffer);
            fileOut.write(buffer);
        }
    }

    public void UI() throws IOException{
        System.out.println("Välkommen vad vill du göra - addMovieFile, addTextInfo, readFile?");
        String input = sc.nextLine().trim();

        if(input.equalsIgnoreCase("addMovieFile")){
            try {
                addMovieFile();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        else if(input.equalsIgnoreCase("addTextInfo")){
            addTextInfo();
        }
        else if(input.equalsIgnoreCase("readFile")){
            try {
                readFile();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        else{
            System.out.println("Not a valid option");
            return;
        }
    }

}




                                                                                                        