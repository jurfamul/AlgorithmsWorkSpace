/**
 * 
 * Jurgen Famula
 * Programming Project one
 * InsertionSortMachine class: This class reads in a text file containing integers into an ArrayList, sorts the integers via insertion sort,
 * and then prints the sort list of integers
 * 
 */
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class InsertionSortMachine {
	
	public static void sort(String fileName)
	{
		ArrayList<Integer> list = importData(fileName);
		insertionSort(list);
		printArray(list);
	}
	
	private static ArrayList<Integer> importData(String inputFileName)
	{
		Scanner inputStream = null;

		try
		{
			inputStream = new Scanner(new FileInputStream(inputFileName));
		}
		catch (FileNotFoundException e)
		{
			System.exit(0);
		}
		
		ArrayList<Integer> unsortedList = new ArrayList<>();
		
		while(inputStream.hasNextInt())
		{
			unsortedList.add(inputStream.nextInt());
		}
		
		return unsortedList;
	}
	
	private static void insertionSort(ArrayList<Integer> data)
	{
		int length = data.size();

		for (int i = 1; i < length; i++)
		{
			int key = data.get(i);
			int j = i - 1;

			while(j > -1 && data.get(j) > key)
			{
				data.set(j + 1, data.get(j));
				j--;
			}

			data.set(j + 1, key);
		}
	}
	
	private static void printArray(ArrayList<Integer> data)
	{
		for (int i = 0; i < data.size(); i++)
		{
			System.out.println(data.get(i));
		}
	}
}
