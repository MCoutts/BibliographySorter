/*
Name: Matthew Coutts
Class: CMPSC 463
Assignment: Stable Sorting with BibText files using comparator and comparable interfaces
Date: March 14th, 2021
 */



import javax.print.attribute.standard.DateTimeAtCreation;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;

public class CitationSorter {


	static ArrayList<String>CitationSorted = new ArrayList<>();

	/**
	 * // * @param bibText
	 * 
	 * @param
	 * @param //citationSorters
	 * @param input
	 */
	public int year; // holds our current year
	public String id; // the identifier of the bib
	public String other; // other information

	/**
	 *This writes a new File with the new sorted bib String
	 *
	 */
	public static void WriteToFile()
	{
		try {
			FileWriter myWriter = new FileWriter("Sorted Bib_.bib");
			for(int i = 0; i < CitationSorted.size(); i++) {
				myWriter.write(String.valueOf(CitationSorted.get(i) + "\n"));
			}
			myWriter.close();
			System.out.println("Successfully wrote to the file.");
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}

	/**
	 * Takes in an Citation Sorter obj as comparator, it compares two citation sorter objects to see
	 * which is lesser of the two.
	 * @param comparator
	 * @param v
	 * @param w
	 * @return
	 */
	private static boolean less(Comparator<CitationSorter> comparator, CitationSorter v, CitationSorter w) {
		return comparator.compare(v, w) < 0;
	}

	/**
	 * Swaps the two citations if they are < or > of each other
	 * @param citations
	 * @param i
	 * @param j
	 */
	private static void change(CitationSorter[] citations, int i, int j) {
		CitationSorter swap = citations[i];
		citations[i] = citations[j];
		citations[j] = swap;
	}

	/**
	 * The insertion sort function takes in a CiteSort array obj and Comparator Citation sorter
	 * It traverses through the length of the CitationSorter array and then determines if they are < or > another
	 * @param citeArr
	 * @param comparator
	 * @throws IOException
	 */
	private static void insertionSort(CitationSorter[] citeArr, Comparator<CitationSorter> comparator) throws IOException {

		int N = citeArr.length;
		for (int i = 0; i < citeArr.length; i++) {
			for (int j = i; j > 0 && less(comparator, citeArr[j], citeArr[j - 1]); j--) {
				change(citeArr, j, j - 1);

			}
		}
	}


	/**
	 * YearSort takes in a Citation sorter comparator, it compares the two years and returns
	 * the difference
	 */
	public static class YearSort implements Comparator<CitationSorter> {
		public int compare(CitationSorter x, CitationSorter y) {
			return x.year - y.year; // returns the difference between the values
		}
	}

	/**
	 *  Returns YearSort Obj
	 */
	private static Comparator<CitationSorter> YearSorting() {
		return new YearSort();
	}

	/**
	 * IDSORT takes in a Citation sorter comparator, it compares the two IDs and returns
	 * 	 * the difference
	 *
	 */
	public static class IDSort implements Comparator<CitationSorter> {
		public int compare(CitationSorter x, CitationSorter y) {
			// returns the compare to value between the two ID's
			return x.id.compareTo(y.id);
		}
	}

	/**
	 * @return
	 */
	public static Comparator<CitationSorter> IDSort() {
		return new IDSort();
	}


	/** this function is used to get the year from the imported array.
	 * @param strArray
	 * @return
	 */
	public static int getYear(String strArray) {
		int begin = strArray.indexOf("year");
		int ending = strArray.indexOf("year") + 10;

		if (begin != -1) {
			return Integer.valueOf(strArray.substring(begin + 6, ending));
		} else {
			return 2021;
		}
	}

	/** this function is used to get the ID from the imported array.
	 * @param strArray
	 * @return
	 */
	public static String getID(String strArray) {
		int begin = strArray.indexOf("{") + 1;
		int ending = strArray.indexOf(",");

		return strArray.substring(begin, ending);

	}

	/**
	 * This sorts by ID by taking in the citation obj and implementing our insertionSort
	 * @param citations
	 * @return
	 * @throws IOException
	 */
	public static CitationSorter[] sortByID(CitationSorter[] citations) throws IOException {
		System.out.println(" ");
		System.out.println("Sorting by Identifier: ------");
		System.out.println(" ");

		insertionSort(citations, CitationSorter.IDSort());

		for (int i = 0; i < citations.length-1; i++) {
			System.out.println(citations[i].other);
		}

		CitationSorted.add("\nSorting by Identifier\n");
		for (int i = 0; i < citations.length; i++) {
			CitationSorted.add(citations[i].other);
		}

		return citations;
	}

	/**
	 * This sorts by Year by taking in the citation obj and implementing our insertionSort
	 * @param citations
	 * @throws IOException
	 */
	public static void sortByYear(CitationSorter[] citations) throws IOException {
		System.out.println(" ");
		System.out.println("Sorting by Year: ------");
		System.out.println(" ");

		insertionSort(citations, CitationSorter.YearSorting());

		for (int i = 0; i < citations.length; i++) {
			System.out.println(citations[i].other);
		}
		CitationSorted.add("\nSorting by Year\n");
		for (int i = 0; i < citations.length; i++) {
			CitationSorted.add(citations[i].other);
		}

	}

}
