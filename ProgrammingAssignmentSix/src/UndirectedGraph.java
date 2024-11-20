/**
 * 
 * Jurgen Famula
 * Programming Assignment Six
 * 10-30-17
 * UndirectedGraph Class: This class contains the constructor and methods of the UndirectedGraph class as well as those for the Vertex subclass.
 * 
 */
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class UndirectedGraph {
	
	private HashMap<Integer, Vertex> graph;
	private int numVertices;
	
	/* The constructor for an UndirectedGraph object takes a string argument that contains the name of a data file that is formatted in accordance
	 * with the format listed in the project instructions. The constructor reads the number of vertices and creates a hashmap containing each vertex
	 * and using the vertex's integer value as the key. the constructor then reads in the vertex pairs for each edge in the input file and adds them
	 * to the corresponding vertex's ArrayList of adjacent vertices. The program will exit if an invalid file name is entered.
	 */
	public UndirectedGraph(String inputFile)
	{
		Scanner inputStream = null;

		try
		{
			inputStream = new Scanner(new FileInputStream(inputFile));
		}
		catch (FileNotFoundException e)
		{
			System.out.println("Error: could not open file: " + inputFile);
			System.exit(0);
		}
		
		numVertices = inputStream.nextInt();
		graph = new HashMap<>((int)(numVertices * 1.33));
		ArrayList<Vertex> vertices = new ArrayList<>();
		
		for (int vertex = 0; vertex < numVertices; vertex++)
		{
			vertices.add(new Vertex(vertex));
			graph.put(vertex, vertices.get(vertex));
		}
		
		while (inputStream.hasNext())
		{
			int firstVertex = inputStream.nextInt();
			int secondVertex = inputStream.nextInt();
			
			if (graph.containsKey(firstVertex))
			{
				graph.get(firstVertex).addAdjacentVertex(vertices.get(secondVertex));
			}
			
			if (graph.containsKey(secondVertex))
			{
				graph.get(secondVertex).addAdjacentVertex(vertices.get(firstVertex));
			}
		}
	}
	
	/* This method performs a breadth first search on the graph. The first for loop sets the distance values for every vertex to the total number of
	 * vertices in the graph plus one. The parent pointer for each vertex is set to null in the vertex constructor, so there is no need to set it here.
	 * The distance value of the source vertex is set to zero and it is added to the search queue. The source vertex is then removed from the queue
	 * and its adjacent vertices are "explored", its distance and parent pointer are changed to reflect their new parent, and added to the queue. 
	 * This is repeated until every vertex has been explored and queue is empty. The program will exit if an invalid source is entered.
	 */
	public void breathFirstSearch(int sourceVertex)
	{
		if (sourceVertex >= numVertices || sourceVertex < 0)
		{
			System.out.println("Invalid Source error: the source does not exist");
			System.exit(0);
		}
		
		int maxDistance = numVertices + 1;
		for (int i = 0; i < numVertices; i++)
		{
			graph.get(i).distance = maxDistance;
		}
		
		graph.get(sourceVertex).distance = 0;
		ArrayDeque<Vertex> searchQueue = new ArrayDeque<>();
		searchQueue.add(graph.get(sourceVertex));
		
		while (!searchQueue.isEmpty())
		{
			Vertex currentVertex = searchQueue.remove();
			for (Vertex neighbor : currentVertex.adj)
			{
				if (neighbor.distance == maxDistance)
				{
					neighbor.distance = currentVertex.distance + 1;
					neighbor.parent = currentVertex;
					searchQueue.add(neighbor);
				}
			}
		}
	}
	
	// This method takes the integer values of the source and "destination" vertices of the graph and prints the value of destination vertex,
	// the distance of that vertex from the source, and the path from the source to the destination, where each value is separated by a space.
	public void printPath(int sourceVertex, int destinationVertex)
	{
		Vertex source = graph.get(sourceVertex);
		Vertex destination = graph.get(destinationVertex);
		System.out.print(destination.value + " " + destination.distance + " ");
		recursivePrintPath(source, destination);
		System.out.println("");
	}
	
	// This recursive method prints the path from the source vertex to the destination vertex.
	private void recursivePrintPath(Vertex source, Vertex destination)
	{	
		if (source == destination)
		{
			System.out.print(source.value);
		}
		else if (destination.parent == null)
		{
			System.out.print("No path from vertex " + source.value + " to vertex " + destination.value);
		}
		else
		{
			recursivePrintPath(source, destination.parent);
			System.out.print("-" + destination.value);
		}
	}
	
	public int size()
	{
		return numVertices;
	}
	
	private static class Vertex {
		
		int value;
		int distance;
		Vertex parent; 
		ArrayList<Vertex> adj;
		
		public Vertex(int v)
		{
			value = v;
			distance = 0;
			parent = null;
			adj = new ArrayList<>();
		}
		
		public void addAdjacentVertex(Vertex adjacent)
		{
			if (!adj.contains(adjacent))
			{
				adj.add(adjacent);
			}
		}
	}
}
