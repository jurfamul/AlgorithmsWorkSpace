import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class MaximumSubarrayDriver {

	public static void main(String[] args) {
		
		String[] fileNames = new String[6];
		fileNames[0] = "Data_8.txt";
		fileNames[1] = "Data_16.txt";
		fileNames[2] = "Data_64.txt";
		fileNames[3] = "Data_1024.txt";
		fileNames[4] = "Data_4096.txt";
		fileNames[5] = "Data_65536-1.txt";
		
		String header = "n, Algorithm, CPU seconds" + '\n';
		System.out.print(header);
		
		int[] elements = new int[6];
		elements[0] = 8;
		elements[1] = 16;
		elements[2] = 64;
		elements[3] = 1024;
		elements[4] = 4096;
		elements[5] = 65536;
		
		for (int i = 0; i < 6; i++)
		{
			double bruteForceTime = 0;
			double dAndCTime = 0;
			double kadanesTime = 0;
			int n = elements[i];
			int iterations = Math.max(4, 65536 / n);
			
			CpuTimer bruteForceTimer = new CpuTimer();
			for (int j = 0; j < iterations; j++)
			{
				ArrayList<Integer> bruteForce = MaximumSubarray.bruteForceMethod(fileNames[i]);
			}
			bruteForceTime = bruteForceTimer.getElapsedCpuTime();
			double avgBruteForceTime = bruteForceTime / iterations;
			String bruteForceOutput = n + ", BF, " + avgBruteForceTime + '\n';
			System.out.print(bruteForceOutput);

			CpuTimer dandcTimer = new CpuTimer();
			for (int j = 0; j < iterations; j++)
			{
				ArrayList<Integer> dandc = MaximumSubarray.divideAndConquerMethod(fileNames[i]);
			}
			dAndCTime = dandcTimer.getElapsedCpuTime();
			double avgDandCTime = dAndCTime / iterations;
			String dandCOutput = n + ", DAC, " + avgDandCTime + '\n';
			System.out.print(dandCOutput);
			
			CpuTimer kadanesTimer = new CpuTimer();
			for (int j = 0; j < iterations; j++)
			{
				ArrayList<Integer> kadanes = MaximumSubarray.KadanesMethod(fileNames[i]);
			}
			kadanesTime = kadanesTimer.getElapsedCpuTime();
			double avgKadanesTime = kadanesTime / iterations;
			String kadanesOutput = n + ", KAD, " + avgKadanesTime + '\n';
			System.out.print(kadanesOutput);
		}
	}

}
