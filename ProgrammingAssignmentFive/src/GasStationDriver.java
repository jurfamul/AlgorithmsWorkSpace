/**
 * 
 * 	Jurgen Famula
 * 	Programming assignment 5
 * 	10-23-17
 * 	GasStationDriver Class: This class runs the gas station simulation.
 * 
 **/
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class GasStationDriver {

	public static void main(String[] args) {
		
		String[] fileNames = new String[4];
		fileNames[0] = "sample.txt";
		fileNames[1] = "vehicle_3_25.txt";
		fileNames[2] = "vehicle_5_25.txt";
		fileNames[3] = "vehicle_5_100.txt";
		
		Scanner keyboard = new Scanner(System.in);
		
		System.out.print("Please enter the number of the data file you wish to use for this simulation (1 - 4): ");
		int fileNum = keyboard.nextInt() - 1;
		System.out.println("You have selected " + fileNames[fileNum] + ". Your results can be found in the .csv file: " + fileNames[fileNum] + ".csv");
		
		Scanner inputStream = null;

		try
		{
			inputStream = new Scanner(new FileInputStream(fileNames[fileNum]));
		}
		catch (FileNotFoundException e)
		{
			System.exit(0);
		}
		
		int numPumps = inputStream.nextInt();
		ArrayList<Vehicle> inputArray = new ArrayList<>();
		
		while (inputStream.hasNext())
		{
			inputArray.add(new Vehicle(inputStream.next(), inputStream.nextInt(), inputStream.nextInt()));
		}
		
		PrintWriter outputStream = null;
		
		try
		{
			outputStream = new PrintWriter(new FileOutputStream(fileNames[fileNum] + ".csv"));
		}
		catch (FileNotFoundException e)
		{
			System.out.println("cannot open file: " + fileNames[fileNum] + ".csv");
			System.exit(0);
		}
		
		int time = 0;
		ArrayList<Vehicle> stationQueue = new ArrayList<>();
		int custumerNum = 0;
		int custumersServed = 0;
		
		// This while loop conducts the simulation. Using the integers, custumerNum and custumersServed, to represent each car when it enters
		// and leaves the queue ensures that every element is examined the minimum number of times, once when it is placed in the queue and
		// once when it leaves. This decreases the complexity of the loop when compared to using a for loop that checks every element at every 
		// iteration of the while or when using the Arraylist's remove method, which would require the elements of the array to be shifted every
		// time one was removed.
		while (custumersServed < inputArray.size())
		{
			while (custumerNum < inputArray.size() && inputArray.get(custumerNum).getArrivalTime() == time)
			{
				CarQueue.insert(stationQueue, inputArray.get(custumerNum));
				custumerNum++;
			}
			
			for (int openPumps = numPumps; openPumps > 0; openPumps--)
			{
				if (custumersServed < inputArray.size())
				{
					Vehicle refueledCar = CarQueue.extractMin(stationQueue);
					String output = refueledCar + ", " + time;
					outputStream.println(output);					
					custumersServed++;
				}
			}
			
			time++;
		}
		
		outputStream.close();
		keyboard.close();
		inputStream.close();
	}

}
