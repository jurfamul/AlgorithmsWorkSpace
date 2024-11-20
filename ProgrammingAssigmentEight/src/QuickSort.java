/**
 * 
 * @author Jurgen Famula
 * Programming Assignment Eight
 * 11-13-17
 * QuickSort Class: This class uses an array list to implement the randomized quick sort algorithm. 
 *
 */
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class QuickSort {
	
	private ArrayList<String> sortingArray;
	private Random rng;
	
	public QuickSort(String inputFile)
	{
		Scanner inputStream = null;

		try
		{
			inputStream = new Scanner(new FileInputStream(inputFile));
		}
		catch (FileNotFoundException e)
		{
			System.out.println("Error: could not open file: " + inputFile);
			System.exit(0);
		}
		
		sortingArray = new ArrayList<>();
		rng = new Random();
		
		while (inputStream.hasNext())
		{
			String input = (String)inputStream.nextLine();
			sortingArray.add(input);
		}
		
		inputStream.close();
	}
	
	public void quickSort()
	{
		int low = 0;
		int high = sortingArray.size() - 1;
		recursiveQuickSort(low, high);
	}
	
	public void printArray()
	{
		for (int i = 0; i < sortingArray.size(); i++)
		{
			System.out.println(i + " " + sortingArray.get(i));
		}
	}
	
	public int getSize()
	{
		return sortingArray.size();
	}
	
	private void recursiveQuickSort(int low, int high)
	{
		if (low < high)
		{
			int split = randomizedPartition(low, high);
			recursiveQuickSort(low, split - 1);
			recursiveQuickSort(split + 1, high);
		}
	}
	
	//This method uses a random integer between the high and low indexes to choose a new pivot for each call of the recursiveQuickSort method
	private int randomizedPartition(int low, int high)
	{
		int randPivot = rng.nextInt(high);
		
		while (randPivot < low)
		{
			randPivot = rng.nextInt(high);
		}
		
		swap(randPivot, high);
		return partition(low, high);
	}
	
	// This method is executed after the randomizedPivot call and take in the low and high indexes from the sortingArray. This method uses these
	// indexes and the newly swapped random string at the high element. This method will iterate through the array, swapping the position of the
	// pivot every time there is an element in the array that has a greater unix code value then the pivot string. The split integer is also incremented.
	// After the for loop, the element at the index of split + 1 is swapped with the element at the high index and then the integer "split" is 
	// incremented and returned.
	private int partition(int low, int high)
	{
		String pivot = sortingArray.get(high);
		int split = low - 1;
		
		for (int i = low; i < high - 1; i++)
		{
			if (sortingArray.get(i).compareTo(pivot) < 1)
			{
				++split;
				swap(split, i);
			}
		}
		
		swap(split + 1, high);
		return split + 1;
	}
	
	private void swap(int firstIndex, int secondIndex)
	{
		String lowerString = sortingArray.get(firstIndex);
		sortingArray.set(firstIndex, sortingArray.get(secondIndex));
		sortingArray.set(secondIndex, lowerString);
	}

}
