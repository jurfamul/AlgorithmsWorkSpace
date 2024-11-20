/**
 * 
 * Jurgen Famula
 * Programming Project 2
 * SortingAlgorithms class: This class contains the public static methods: isSorted, randomArray, insertionSort, and mergeSort. It also contains the
 * private static methods: copyArray, recursiveMergeSort, and merge. 
 * 
 */
import java.util.ArrayList;
import java.util.Random;

public class SortingAlgorithms {
	
	// tests if a given ArrayList of integer is sorted by brute force. returns true if the given array is sorted in increasing order.
	public static boolean isSorted(ArrayList<Integer> input)
	{
		int length = input.size();
		
		for (int i = 0; i < length - 1; i++)
		{
			if (input.get(i) > input.get(i + 1))
			{
				return false;
			}
		}
		
		return true;
	}
	
	// randomArray(int size): creates an array of the given size containing random integers
	public static ArrayList<Integer> randomArray(int size)
	{
		ArrayList<Integer> randArray = new ArrayList<>(size);
		Random rand = new Random();
		int randInt = 0;

		for (int i = 0; i < size; i++)
		{
			if (rand.nextBoolean())
			{
				randInt = rand.nextInt();
			}
			else
			{
				randInt = rand.nextInt() * -1;
			}

			randArray.add(randInt);
		}

		return randArray;
	}
		
	// This method takes in an ArrayList of integers, copies the ArrayList using the copyArray method, sorts the copy using the insertion sort algorithm,
	// and then returns the sorted copy.
	public static ArrayList<Integer> insertionSort(ArrayList<Integer> input)
	{
		ArrayList<Integer> outputArray = copyArray(input);
		int length = outputArray.size();
		
		for (int i = 0; i < length; i++)
		{
			int key = outputArray.get(i);
			int j = i - 1;
			
			while(j > -1 && outputArray.get(j) > key)
			{
				outputArray.set(j + 1, outputArray.get(j));
				j--;
			}

			outputArray.set(j + 1, key);
		}
		
		return outputArray;
	}
	
	// this method takes in an ArrayList of integers, copies the array, finds the first and last index of the array, makes the initial call to the recursiveMergeSort
	// method, and finally returns the sorted copy of the original array.
	public static ArrayList<Integer> mergeSort(ArrayList<Integer> inputArray)
	{
		ArrayList<Integer> outputArray = copyArray(inputArray);
		int startingIndex = 0;
		int lastIndex = outputArray.size() - 1;
		recursiveMergeSort(outputArray, startingIndex, lastIndex);
		return outputArray;
	}
	
	/* copyArray is used in both insertionSort and mergeSort to create a new ArrayList
	 * object containing the same elements as the input ArrayList, both methods then use this new ArrayList to perform the sorting operation and then
	 * return the Array. */
	private static ArrayList<Integer> copyArray(ArrayList<Integer> inputArray)
	{
		int length = inputArray.size();
		ArrayList<Integer> outputArray = new ArrayList<>(length);
		
		for (int i = 0; i < length; i++)
		{
			outputArray.add(inputArray.get(i));
		}
		
		return outputArray;
	}
	
	// this recursive method takes the starting and last indexes of the input array and proceeds to "split" the array by finding the midpoint of the 
	// array and making the recursive call using both the front and back halves of the array. This continues until the array is split into sub sections
	// of size 2 which each contain two elements. The merge method is then called by each recursive call sitting on the stack.
	private static void recursiveMergeSort(ArrayList<Integer> input, int startingIndex, int lastIndex)
	{
		if (startingIndex < lastIndex)
		{
			int midpoint = ((startingIndex + lastIndex) / 2);
			recursiveMergeSort(input, startingIndex, midpoint);
			recursiveMergeSort(input, midpoint + 1, lastIndex);
			merge(input, startingIndex, midpoint, lastIndex);
		}
	}
	
	// This method takes the startingIndex, midpoint, and lastIndex parameters given by the recursiveMergeSort method and creates two new sub arrays
	// containing the elements housed between the given indexes in the input array. The values with each sub array are compared and placed back in the
	// input array in order of least to greatest. This is repeated for all recursive calls on the stack. The final output of the final call of this
	// method is the sorted array.
	private static void merge(ArrayList<Integer> input, int startingIndex, int midpoint, int lastIndex)
	{
		int leftLength = midpoint - startingIndex + 1;
		int rightLength = lastIndex - midpoint;
		
		ArrayList<Integer> left = new ArrayList<>(leftLength);
		ArrayList<Integer> right = new ArrayList<>(rightLength);
		
		for (int i = 0; i < leftLength; i++)
		{
			left.add(input.get(startingIndex + i));
		}
		
		for (int j = 0; j < rightLength; j++)
		{
			right.add(input.get(midpoint + j + 1));
		}
		
		int lPointer = 0;
		int rPointer = 0;
		int inputPointer = startingIndex;
		
		while (lPointer < leftLength && rPointer < rightLength)
		{
			if (left.get(lPointer) <= right.get(rPointer))
			{
				input.set(inputPointer, left.get(lPointer));
				lPointer++;
				inputPointer++;
			}
			else
			{
				input.set(inputPointer, right.get(rPointer));
				rPointer++;
				inputPointer++;
			}
		}
		while (lPointer < leftLength)
		{
			input.set(inputPointer, left.get(lPointer));
			lPointer++;
			inputPointer++;
		}
		while (rPointer < rightLength)
		{
			input.set(inputPointer, right.get(rPointer));
			rPointer++;
			inputPointer++;
		}
	}

}
