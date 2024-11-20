/**
 * 
 * 	Jurgen Famula
 * 	Programming assignment 5
 * 	10-23-17
 *  Vehicle	Class: This class contains the constructor and getter methods for the Vehicle class of objects. Each object consists of an id String,
 *  a arrival time integer which dictates when the object is added to the queue, and refuelPriority integer that represents the vehicles 
 *  importance in the queue.
 * 
 **/
public class Vehicle {
	
	private String vehicleID;
	private int arrivalTime;
	private int refuelPriority;
	
	public Vehicle(String id, int time, int priority)
	{
		vehicleID = id;
		arrivalTime = time;
		refuelPriority = priority;
	}

	public String getVehicleID() 
	{
		return vehicleID;
	}

	public int getArrivalTime() 
	{
		return arrivalTime;
	}

	public int getRefuelPriority() 
	{
		return refuelPriority;
	}
	
	public String toString()
	{
		return vehicleID + ", " + arrivalTime;
	}

}
