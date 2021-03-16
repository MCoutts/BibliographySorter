/*
Name: Matthew Coutts
Class: CMPSC 463
Assignment: Stable Sorting with BibText files using comparator and comparable interfaces
Date: March 14th, 2021
This is the driver class to run the citation sorter

 */
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class driverClassCitation extends CitationSorter {


    //Our main program is here
    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {

        //this is our citation object here
        CitationSorter[] citationSorters;
        //getting the user input for the filename
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the name of the file: \n");
        String input = scanner.next();

        File file = new File(input); //assigning the input to a file obj

        //FileInfo holds the File's components
        String fileInformation = null;
        Scanner scan = new Scanner(new FileReader(file));

        int sortingType = 0;

        while (sortingType >= 1 || sortingType <= 4) {
            System.out.print("Do you want to sort by: \n _____________________" +
                    "\n #1.Identifier Only \n #2.Year Only " +
                    "\n #3. Identifier then year \n #4. Year then identifier \n");
            Scanner sortingChoice = new Scanner(System.in);
            sortingType = sortingChoice.nextInt();

            if (sortingType == 1 || sortingType == 2 || sortingType == 3 || sortingType == 4) {
                break;
            }
        }

        //traversing through the document until the end ofthe line and storing the information into another
        // String file
        while (scan.hasNextLine()) {
            fileInformation += scan.nextLine();
        }

        String[] strcite = fileInformation.split("@");
        citationSorters = new CitationSorter[strcite.length-1];


        for (int i = 1; i < strcite.length; i++) {

            CitationSorter citeElements = new CitationSorter();

            //System.out.print("###" + i);
            //System.out.print(strcite[i] + "\n");

            //here we extract the year/IDs from the strCite ArrayList ---> which contains the bib list
            citeElements.id = getID(strcite[i]);
            citeElements.year = getYear(strcite[i]);
            citeElements.other = strcite[i];

            //adding a citeObj to the citationSorterArrayList
            citationSorters[i - 1] = citeElements;

        }


        //#1. sort by identifier
        if (sortingType == 1) {

            sortByID(citationSorters);
            WriteToFile();


        }
        //#2 Sort by year
        if (sortingType == 2) {
            sortByYear(citationSorters);
            WriteToFile();

        }
        //#3. Sort by identifier then year
        if (sortingType == 3) {
            sortByID(citationSorters);
            WriteToFile();
            sortByYear(citationSorters);
            WriteToFile();

        }
        //#4. sort by year then identifier
        if (sortingType == 4) {
            sortByYear(citationSorters);
            WriteToFile();
            sortByID(citationSorters);
            WriteToFile();
        }

    }

}






