/**
 * 
 * Jurgen Famula
 * Programming Project 2
 * RunTimeDriver: Tests the run time of both the insertion and merge sort algorithms over an increasing number of iterations and then prints the results
 * to the user.
 * 
 */
import java.util.ArrayList;

public class RunTimeDriver {

	public static void main(String[] args) {
		
		for (int n = 8; n < 8193; n = n*2)
		{
			ArrayList<Integer> unsortedArray = SortingAlgorithms.randomArray(n);
			// the variable names for the final output arrays are declared here and initialized to null
			ArrayList<Integer> insertionArray = null;
			ArrayList<Integer> mergeArray = null;
			
			double mergeTime = 0;
			double insertionTime = 0;
			int iter = Math.max(4, 8192/n);
			
			CpuTimer insertionTimer = new CpuTimer();
			for (int i = 0; i < iter; i++)
			{
				insertionArray = SortingAlgorithms.insertionSort(unsortedArray);
			}
			insertionTime = insertionTimer.getElapsedCpuTime();
			double avgInsertionTime = insertionTime / iter;
			boolean insertionCorrectness = SortingAlgorithms.isSorted(insertionArray);
			if (!insertionCorrectness)
			{
				System.out.println("Insertion Sorting error");
				System.exit(0);
			}
			
			CpuTimer mergeTimer = new CpuTimer();
			for (int i = 0; i < iter; i++)
			{
				mergeArray = SortingAlgorithms.mergeSort(unsortedArray);
			}
			mergeTime = mergeTimer.getElapsedCpuTime();
			double avgMergeTime = mergeTime / iter;
			boolean mergeCorrectness = SortingAlgorithms.isSorted(mergeArray);
			if (!mergeCorrectness)
			{
				System.out.println("Merge Sorting error");
				System.exit(0);
			}
			
			// output: prints the number of elements in the array (n) and the average time each insertion sort and merge sort call
			System.out.println("Avg. times for n = " + n + ": Average insertion sort time: " + (double) avgInsertionTime + " sec., Average merge sort time: " 
									+ (double) avgMergeTime + " sec.");
		}

	}

}
