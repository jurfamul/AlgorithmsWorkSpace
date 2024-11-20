import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class MaximumSubarray {
	
	public static ArrayList<Integer> bruteForceMethod(String fileName)
	{
		ArrayList<Integer> mainArray = importData(fileName);
		ArrayList<Integer> maxSubArray = null;
		int length = mainArray.size();
		int currentMaxSubTotal = mainArray.get(0);
		int currentSubTotal = 0;
		int subStartingIndex = 0;
		int subEndingIndex = 0;
		
		for (int startingIndex = 0; startingIndex < length; startingIndex++)
		{
			currentSubTotal = 0;
			for (int endingIndex = startingIndex; endingIndex < length; endingIndex++)
			{
				currentSubTotal += mainArray.get(endingIndex);
				
				if (currentSubTotal > currentMaxSubTotal)
				{
					subStartingIndex = startingIndex;
					subEndingIndex = endingIndex;
					currentMaxSubTotal = currentSubTotal;
				}
			}
		}
		
		int subArrayLength = subEndingIndex - subStartingIndex + 1;
		maxSubArray = new ArrayList<>(subArrayLength);
		
		for (int i = 0; i < subArrayLength; i++)
		{
			maxSubArray.add(mainArray.get(subStartingIndex + i));
		}
		
		return maxSubArray;
	}
	
	public static ArrayList<Integer> divideAndConquerMethod(String fileName)
	{
		ArrayList<Integer> mainArray = importData(fileName);
		ArrayList<Integer> maxSubArray = null;
		int length = mainArray.size();
		int[] subArrayIndexes = recursiveFindMaxSubArray(mainArray, 0, length - 1);
		int subArraySize = subArrayIndexes[1] - subArrayIndexes[0] + 1;
		maxSubArray = new ArrayList<>(subArraySize);
		
		for (int i = subArrayIndexes[0]; i < subArrayIndexes[1] + 1; i++)
		{
			maxSubArray.add(mainArray.get(i));
		}
		
		return maxSubArray;
	}
	
	public static ArrayList<Integer> KadanesMethod(String fileName)
	{
		ArrayList<Integer> mainArray = importData(fileName);
		ArrayList<Integer> maxSubArray = null;
		int length = mainArray.size();
		int maxSumStartingIndex = 0;
		int maxSumEndingIndex = 0;
		int runningSumStartingIndex = 0;
		int runningSumEndingIndex = 0;
		int currentMaxSum = 0;
		int runningMaxSum = 0;
		
		for (int i = 0; i < length; i++)
		{
			runningMaxSum += mainArray.get(i);
			runningSumEndingIndex++;
			
			if (runningMaxSum < 0)
			{
				runningMaxSum = 0;
				runningSumStartingIndex = i + 1;
			}
			
			if (runningMaxSum > currentMaxSum)
			{
				currentMaxSum = runningMaxSum;
				maxSumStartingIndex = runningSumStartingIndex;
				maxSumEndingIndex = runningSumEndingIndex;
			}
		}
		
		int subArraySize = maxSumEndingIndex - maxSumStartingIndex;
		maxSubArray = new ArrayList<>(subArraySize);
		
		for (int i = 0; i < subArraySize; i++)
		{
			maxSubArray.add(mainArray.get(maxSumStartingIndex + i));
		}
		
		return maxSubArray;
	}
	
	private static ArrayList<Integer> importData(String inputFile)
	{
		Scanner inputStream = null;

		try
		{
			inputStream = new Scanner(new FileInputStream(inputFile));
		}
		catch (FileNotFoundException e)
		{
			System.exit(0);
		}
		
		ArrayList<Integer> list = new ArrayList<>();
		
		while(inputStream.hasNextInt())
		{
			list.add(inputStream.nextInt());
		}
		
		return list;
	}
	
	private static int[] recursiveFindMaxSubArray(ArrayList<Integer> mainArray, int startingIndex, int endingIndex)
	{
		if (endingIndex == startingIndex)
		{
			int[] recursiveResults = new int[3];
			recursiveResults[0] = startingIndex;
			recursiveResults[1] = endingIndex;
			recursiveResults[2] = mainArray.get(startingIndex);
			return recursiveResults;
		}
		else
		{
			int mid = (startingIndex + endingIndex) / 2;
			int[] leftRecursion = recursiveFindMaxSubArray(mainArray, startingIndex, mid);
			int[] rightRecursion = recursiveFindMaxSubArray(mainArray, mid + 1, endingIndex);
			int[] crossSubArray = findCrossingSubArray(mainArray, startingIndex, mid, endingIndex);
			
			if (leftRecursion[2] >= rightRecursion[2] && leftRecursion[2] >= crossSubArray[2])
			{
				return leftRecursion;
			}
			else if (rightRecursion[2] >= leftRecursion[2] && rightRecursion[2] >= crossSubArray[2])
			{
				return rightRecursion;
			}
			else
			{
				return crossSubArray;
			}
		}
	}
	
	private static int[] findCrossingSubArray(ArrayList<Integer> mainArray, int starting, int mid, int ending)
	{
		int leftSum = mainArray.get(mid);
		int sum = 0;
		int maxLeft = mid;
		
		for (int i = mid; i >= starting; i--)
		{
			sum = sum + mainArray.get(i);
			
			if (sum > leftSum)
			{
				leftSum = sum;
				maxLeft = i;
			}
		}
		
		int rightSum = mainArray.get(mid + 1);
		sum = 0;
		int maxRight = mid + 1;
		
		for (int i = mid + 1; i <= ending; i++)
		{
			sum += mainArray.get(i);
			
			if (sum > rightSum)
			{
				rightSum = sum;
				maxRight = i;
			}
		}
		
		int finalSum = leftSum + rightSum;
		int[] crossSubArray = new int[3];
		crossSubArray[0] = maxLeft;
		crossSubArray[1] = maxRight;
		crossSubArray[2] = finalSum;
		return crossSubArray;
	}

}
