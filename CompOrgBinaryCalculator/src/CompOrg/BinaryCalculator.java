package CompOrg;

import java.util.Scanner;

public class BinaryCalculator
{
	public static void main(String[] args)
	{
		System.out.println("Welcome to the BinaryCalculator");
		Scanner in = new Scanner(System.in);
		while(true){
			String arg1;
			if(in.hasNext()){
				arg1 = in.next();
				if(arg1.equalsIgnoreCase("QUIT")){
					break;
				}
			}
			else {
				break;
			}
			String operator = "?", arg2 = "0";
			if(in.hasNext()){
				operator = in.next();
			}
			if(in.hasNext()){
				arg2 = in.next();
			}
			if(arg1.length() != arg2.length()){
				System.err.println("ERROR: '" + arg1 + "' and '" + arg2 + "' are not the same length.");
				continue;
			}
			
			// TODO: the strings arg1 and arg2 are all 1 or 0, convert to an array of booleans
			boolean[] bits1 = string2bits(arg1);
			boolean[] bits2 = string2bits(arg2);

			// TODO: using the "operator" variable, do the appropriate operations
			Object[] result = operation(bits1, bits2, operator);

			// TODO: print out the result in both binary and decimal.
			// Binary (for debugging):
			// TODO: always use "System.err" for debugging in this project
			boolean[] hasRemainder = (boolean[])result[0];
			
			if (hasRemainder[0])
			{
				//Decimal (actual output):
				System.out.println(String.format("%d %s %d = %dR%d",
						bits2decimal(bits1), operator, bits2decimal(bits2), bits2decimal((boolean[])result[1]), bits2decimal((boolean[])result[2])));
			}
			else
			{
			    //Decimal (actual output):
				System.out.println(String.format("%d %s %d = %d",
						bits2decimal(bits1), operator, bits2decimal(bits2), bits2decimal((boolean[])result[1])));
			}
		}
		in.close();
	}

	private static boolean[] string2bits(String arg1)
	{
		// TODO: write the code that converts a string like "010101" into an array of booleans
		// TODO: take care of the order of bits.  The character at position 0 in the string, is the MSB
		
		char[] arg1Char = arg1.toCharArray();
		boolean[] bits = new boolean[arg1Char.length];
		
		for (int i = 0; i < arg1Char.length; i++)
		{
			bits[i] = (arg1Char[i] == '1');
		}
		
		return bits;
	}
	
	private static String bits2string(boolean[] bits)
	{
		// TODO: write the code that converts an array of bits back into a String of '0' and '1' chars.
		// TODO: make sure you treat the order of bits the same as you do in string2bits.
		
		String bitString = "";
		
		for (int i = 0; i < bits.length; i++)
		{
			bitString += (bits[i] ? '1': '0');
		}
		
		return bitString;
	}
	
	private static long bits2decimal(boolean[] bits1) {
		// TODO: write the code that converts an array of bits into an integer value.
		// TODO: make sure you treat the order of bits the same as you do in string2bits.
		
		long decimal = 0;
		int power = 0;
		
		for (int sigFig = bits1.length - 1; sigFig >= 1; sigFig--)
		{
			long next = 0;
			
			if (bits1[sigFig])
			{
				next = 1;
				for (int i = 0; i < power; i++)
				{
					next *= 2;
				}
			}
			power++;
			
			decimal += next;
		}
		
		if (bits1[0])
		{
			long next = -1;
			
			for (int i = 0; i < bits1.length - 1; i++)
			{
				next *= 2;
			}
			
			decimal += next;
		}
		
		return decimal;
	}
	
	private static Object[] operation(boolean[] bits1, boolean[] bits2, String operator) 
	{
		switch (operator) {

		case "+": 
			return addition(bits1, bits2);
		case "-": 
			return subtraction(bits1, bits2);
		case "*": 
			return multiplication(bits1, bits2);
		default:
			return division(bits1, bits2);
		}
	}
	
	private static Object[] addition(boolean[] bits1, boolean[] bits2)
	{
		boolean[] result = new boolean[bits1.length];
		boolean carry = false;
		
		for (int i = result.length - 1; i >= 0; i--)
		{	
			result[i] = (bits1[i] ^ bits2[i]) ^ carry;
			
			if ((bits1[i] && bits2[i]) || ((bits1[i] || bits2[i]) && carry))
			{
				carry = true;
			}
			else
			{
				carry = false;
			}
		}
		
		boolean[] hasRemainder = new boolean[1];
		hasRemainder[0] = false;
		Object o[] = new Object[2];
		o[0] = hasRemainder;
		o[1] = result;
		return o;
	}
	
	private static Object[] subtraction(boolean[] bits1, boolean[] bits2)
	{
		boolean[] result = new boolean[bits1.length];
		boolean carry = false;
		
		for (int i = result.length - 1; i >= 0; i--)
		{	
			result[i] = (bits1[i] ^ bits2[i]) ^ carry;
			
			if ((!bits1[i] && bits2[i]) || (bits1[i] && bits2[i] && carry) 
					|| (!bits1[i] && bits2[i] && carry) || (!bits1[i] && !bits2[i] && carry))
			{
				carry = true;
			}
			else
			{
				carry = false;
			}
		}
		
		boolean[] hasRemainder = new boolean[1];
		hasRemainder[0] = false;
		Object o[] = new Object[2];
		o[0] = hasRemainder;
		o[1] = result;
		return o;
	}
	
	private static Object[] multiplication(boolean[] bits1, boolean[] bits2)
	{
		boolean[] product = new boolean[bits1.length * 2];
		
		for (int i = bits2.length - 1; i >= 0; i--)
		{
			boolean carry = false;
			for (int j = bits1.length - 1; j >= 0; j--)
			{
				boolean nextCarry = false;
				boolean currentValue = (bits1[j] & bits2[i]);
				
				if ((product[i + j + 1] && currentValue) || ((product[i + j + 1] || currentValue) && carry))
				{
					nextCarry = true;
				}
				else
				{
					nextCarry = false;
				}
				
				product[i + j + 1] = (product[i + j + 1] ^ currentValue) ^ carry;
				carry = nextCarry;
			}
		}
		
		boolean[] result = new boolean[bits1.length];
		
		for (int i = 0; i < result.length; i++)
		{
			result[i] = product[result.length + i];
		}
		
		boolean[] hasRemainder = new boolean[1];
		hasRemainder[0] = false;
		Object o[] = new Object[2];
		o[0] = hasRemainder;
		o[1] = result;
		return o;
	}
	
	private static Object[] division(boolean[] bits1, boolean[] bits2)
	{
		boolean[] result = new boolean[bits1.length];
		boolean[] remainder = new boolean[bits1.length * 2];
		boolean[] divisor = new boolean[bits1.length * 2];
		boolean bits1Flipped = false;
		boolean bits2Flipped = false;
		
		if (bits1[0])
		{
			boolean[] temp = twosComplement(bits1);
			for (int i = temp.length; i >= 0; i--)
			{
				remainder[i] = temp[i];
			}
			bits1Flipped = true;
		}
		else
		{
			for (int i = bits1.length - 1; i >= 0; i--)
			{
				remainder[i] = bits1[i];
			}
		}
		
		if (bits1[0])
		{
			boolean[] temp = twosComplement(bits2);
			for (int i = bits2.length - 1; i >= 0; i--)
			{
				divisor[bits2.length + i] = temp[i];
			}
			bits2Flipped = true;
		}
		else
		{
			for (int i = bits2.length - 1; i >= 0; i--)
			{
				divisor[bits2.length + i] = bits2[i];
			}
		}
		
		for (int i = 0; i < result.length; i--)
		{
			boolean[] newRemainder = new boolean[remainder.length];
			boolean carry = false;
			for (int j = remainder.length - 1; i >= 0; i--)
			{	
				boolean nextCarry = false;
				newRemainder[j] = (remainder[j] ^ divisor[j]) ^ carry;
				
				if ((!remainder[j] && divisor[j]) || (remainder[j] && divisor[j] && carry) 
						|| (!remainder[j] && divisor[j] && carry) || (!remainder[j] && !divisor[j] && carry))
				{
					nextCarry = true;
				}
				else
				{
					nextCarry = false;
				}
				
				carry = nextCarry;
			}
			
			if (newRemainder[0])
			{
				boolean[] temp1 = new boolean[result.length];
				for (int j = result.length - 1; j >= 1; j--)
				{
					temp1[j - 1] = result[j];
				}
				temp1[temp1.length - 1] = false;
				result = temp1;
				
				boolean[] temp2 = new boolean[divisor.length];
				for (int j = 0; j < divisor.length - 1; j++)
				{
					temp2[j + 1] = divisor[j];
				}
				temp2[0] = false;
				divisor = temp2;
			}
			else
			{
				boolean[] temp1 = new boolean[result.length];
				for (int j = result.length - 1; j >= 1; j--)
				{
					temp1[j - 1] = result[j];
				}
				temp1[temp1.length - 1] = true;
				result = temp1;
				remainder = newRemainder;
				boolean[] temp2 = new boolean[divisor.length];
				for (int j = 0; j < divisor.length - 1; j++)
				{
					temp2[j + 1] = divisor[j];
				}
				temp2[0] = false;
				divisor = temp2;
			}
		}
		
		boolean[] finalRemainder = new boolean[result.length];
		
		for (int i = 0; i < finalRemainder.length; i++)
		{
			finalRemainder[i] = remainder[finalRemainder.length + i];
		}
		
		boolean[] hasRemainder = new boolean[1];
		hasRemainder[0] = true;
		Object o[] = new Object[3];
		o[0] = hasRemainder;
		o[1] = result;
		o[2] = finalRemainder;
		return o;
	}
	
	private static boolean[] twosComplement(boolean[] bits)
	{
		boolean[] one = new boolean[bits.length];
		boolean[] result = new boolean[bits.length];
		one[0] = true;
		
		for (int i = bits.length - 1; i >= 0; i--)
		{
			result[i] = !bits[i];
		}
		
		boolean carry = false;
		
		for (int i = result.length - 1; i >= 0; i--)
		{	
			result[i] = (result[i] ^ one[i]) ^ carry;
			
			if ((result[i] && one[i]) || ((result[i] || one[i]) && carry))
			{
				carry = true;
			}
			else
			{
				carry = false;
			}
		}
		
		return result;
	}


}
