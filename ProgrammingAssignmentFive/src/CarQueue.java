/**
 * 
 * 	Jurgen Famula
 * 	Programming assignment 5
 * 	10-23-17
 * 	CarQueue Class: The static class contains the methods that maintain the heap and min priority queue properties of the gas station simulation
 * 	queue. 
 * 
 **/
import java.util.ArrayList;

public class CarQueue {
	
	static void insert(ArrayList<Vehicle> queue, Vehicle element) 
	{
		queue.add(element);
		decreaseKey(queue, queue.get(queue.indexOf(element)), element);
	}

	static Vehicle minimum(ArrayList<Vehicle> queue) 
	{
		return queue.get(0);
	}

	static Vehicle extractMin(ArrayList<Vehicle> queue) 
	{
		if (queue.isEmpty())
		{
			System.out.println("Error: the heap is empty.");
			System.exit(0);
		}
		
		Vehicle minCar = queue.get(0);
		queue.set(0, queue.get(queue.size() - 1));
		queue.remove(queue.size() - 1);
		if (!queue.isEmpty())
		{
			minHeapify(queue, queue.get(0));
		}
		return minCar;
	}

	private static void decreaseKey(ArrayList<Vehicle> queue, Vehicle element, Vehicle key) 
	{
		queue.set(queue.indexOf(element), key);
		int i = queue.indexOf(key);
		while (i > 0 && queue.get(i / 2).getRefuelPriority() > queue.get(i).getRefuelPriority())
		{
			Vehicle swap = queue.get(i / 2);
			queue.set(i / 2, queue.get(i));
			queue.set(i, swap);
			i /= 2;
		}
	}
	
	private static void minHeapify(ArrayList<Vehicle> queue, Vehicle element) 
	{
		int elementIndex = queue.indexOf(element);
		int smallestIndex = 0;
		
		if (elementIndex == -1)
		{
			System.out.println("The car does not exisit in the queue.");
			System.exit(0);
		}
		
		int leftIndex = 2 * elementIndex + 1;
		int rightIndex = 2 * elementIndex + 2;
		
		if (leftIndex < queue.size() && queue.get(leftIndex).getRefuelPriority() < element.getRefuelPriority())
		{
			smallestIndex = leftIndex;
		}
		else if (leftIndex < queue.size() && queue.get(leftIndex).getRefuelPriority() == element.getRefuelPriority() 
					&& queue.get(leftIndex).getArrivalTime() < element.getArrivalTime())
		{
			smallestIndex = leftIndex;
		}
		else
		{
			smallestIndex = elementIndex;
		}
		
		if (rightIndex < queue.size() && queue.get(rightIndex).getRefuelPriority() < queue.get(smallestIndex).getRefuelPriority())
		{
			smallestIndex = rightIndex;
		}
		else if (rightIndex < queue.size() && queue.get(rightIndex).getRefuelPriority() == queue.get(smallestIndex).getRefuelPriority() && 
				queue.get(rightIndex).getArrivalTime() < queue.get(smallestIndex).getArrivalTime())
		{
			smallestIndex = rightIndex;
		}
		
		if (smallestIndex != elementIndex)
		{
			Vehicle key = queue.get(elementIndex);
			queue.set(elementIndex, queue.get(smallestIndex));
			queue.set(smallestIndex, key);
			minHeapify(queue, queue.get(smallestIndex));
		}
		
	}

	static void buildMinHeap(ArrayList<Vehicle> queue) 
	{
		for (int i = queue.size() / 2; i >= 0; i--)
		{
			minHeapify(queue, queue.get(i));
		}
	}

}
