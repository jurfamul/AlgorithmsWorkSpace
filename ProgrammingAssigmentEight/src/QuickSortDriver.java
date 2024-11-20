/**
 * 
 * @author Jurgen Famula
 * Programming Assignment Eight
 * 11-13-17
 * QuickSortDriver: This class takes in the name of an input file, creates a new Quicksort array, sorts the array using the quick sort algorithm, and
 * then prints the array starting at the string with the smallest Unix code value and increasing to the maximum value in the final spot in the array.
 *
 */
public class QuickSortDriver {

	public static void main(String[] args) {
		
		String fileName = args[0];
		
		QuickSort test = new QuickSort(fileName);
		test.quickSort();
		test.printArray();
	}

}
