/**
 * 
 * Jurgen Famula
 * Programming Project 4
 * Multiply class: Multiplies two arrays of ints as if they where a single integer in base 256 and returns an int array containning the product.
 * 
 */
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Multiply {
	
	public static int[] bruteForce(String fileName)
	{
		Scanner inputStream = null;

		try
		{
			inputStream = new Scanner(new FileInputStream(fileName));
		}
		catch (FileNotFoundException e)
		{
			System.exit(0);
		}
		
		int inputSize = inputStream.nextInt();
		
		int[] firstNum = new int[inputSize];
		
		for (int i = 0; i < inputSize; i++)
		{
			firstNum[i] = inputStream.nextInt();
		}
		
		int[] secondNum = new int[inputSize];
		
		for (int j = 0; j < inputSize; j++)
		{
			secondNum[j] = inputStream.nextInt();
		}
		
		return bruteForceCalculation(firstNum, secondNum, inputSize);
	}
	
	public static int[] bruteForceCalculation(int[] firstNum, int[] secondNum, int inputSize)
	{
		int[] product = new int[2 * inputSize];
		
		for (int k = 0; k < product.length; k++)
		{
			product[k] = 0;
		}
		
		int productDigit = 0;
		int firstDigit = 0;
		int secondDigit = 0;
		int currentProduct = 0;
		
		for (firstDigit = firstNum.length - 1; firstDigit >= 0 ; firstDigit--)
		{
			int carry = 0;
			
			for (secondDigit = secondNum.length - 1; secondDigit >= 0; secondDigit--)
			{
				currentProduct = firstNum[firstDigit]*secondNum[secondDigit] + product[firstDigit + secondDigit + 1] + carry;
				product[firstDigit + secondDigit + 1] = currentProduct % 256;
				carry = currentProduct / 256;
				
				productDigit = firstDigit + secondDigit;
				
				while (productDigit >= 0 && carry != 0)
				{
					currentProduct = product[productDigit] + carry;
					product[productDigit] = currentProduct % 256;
					carry = currentProduct / 256;
					productDigit--;
				}
			}
		}
		
		return product;
	}
	
	//public int[] divideAndConquer(int[] firstNum, int[] secondNum)
	//{
	//	
    //}

}
