/**
 * 
 * Jurgen Famula
 * Programming Project 4
 * MultiplyDriver: Tests the run time of both the brute force and recursive multiplication algorithms over an increasing number of iterations and then prints the results
 * to the user.
 * 
 */
public class MultiplyDriver {

	public static void main(String[] args) {
		
		String[] fileNames = new String[9];
		
		for (int i = 2; i < 11; i++)
		{
			fileNames[i - 2] = "p4_" + (int)Math.pow(2, i) + ".txt";
		}
		
		int n = 4;
		
		for (int i = 0; i < fileNames.length; i++)
		{
			double bruteForceTime = 0;
			int iter = Math.max(4, 2048 / (2*n));
			int[] bruteForceArray = null;
			
			CpuTimer bruteForceTimer = new CpuTimer();
			for (int k = 0; k < iter; k++)
			{
				bruteForceArray = Multiply.bruteForce(fileNames[i]);
			}
			bruteForceTime = bruteForceTimer.getElapsedCpuTime();
			double avgBruteForceTime = bruteForceTime / iter;
			String bruteForceOutput = n + ", M, " + avgBruteForceTime + ", ";
			String bruteForceSolution = "";

			for (int j = 0; j < bruteForceArray.length; j++)
			{
				bruteForceSolution += bruteForceArray[j] + " ";
			}
			
			System.out.println(bruteForceOutput.concat(bruteForceSolution));
			n = n * 2;
		}
		
		System.out.println("");
	}
}
