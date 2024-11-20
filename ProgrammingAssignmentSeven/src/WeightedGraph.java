import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class WeightedGraph {
	
	private HashMap<Integer, Vertex> graph;
	private static int numVertices;
	private int maxWeight;
	
	public WeightedGraph(String inputFile)
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
		maxWeight = 0;
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
			int edgeWeight = inputStream.nextInt();
			
			if (edgeWeight > maxWeight)
			{
				maxWeight = edgeWeight;
			}
			
			if (graph.containsKey(firstVertex))
			{
				graph.get(firstVertex).addAdjacentVertex(vertices.get(secondVertex));
				graph.get(firstVertex).addEdge(vertices.get(secondVertex), edgeWeight);
			}
			
			if (graph.containsKey(secondVertex))
			{
				graph.get(secondVertex).addAdjacentVertex(vertices.get(firstVertex));
				graph.get(secondVertex).addEdge(vertices.get(firstVertex), edgeWeight);
			}
		}
		
		maxWeight++;
	}
	
	public void PrimsMST(int rootValue)
	{
		if (rootValue >= numVertices || rootValue < 0)
		{
			System.out.println("Invalid Source error: the source does not exist");
			System.exit(0);
		}
		
		for (int i = 0; i < numVertices; i++)
		{
			graph.get(i).key = maxWeight;
		}
		
		graph.get(rootValue).key = 0;
		VertexQueue minSpanningTree = new VertexQueue(graph);
		
		while (!minSpanningTree.isEmpty())
		{
			Vertex current = minSpanningTree.extractMin();
			
			for (Vertex neighbor : current.adj)
			{
				if (minSpanningTree.contains(neighbor) && current.edgeWeights.get(neighbor) < neighbor.key)
				{
					neighbor.parent = current;
					neighbor.key = current.edgeWeights.get(neighbor);
				}
			}
		}
	}
	
	public void printMinSpanningTree()
	{
		for (int i = 0; i < numVertices; i++)
		{
			Vertex current = graph.get(i);
			
			if (current.parent == null)
			{
				System.out.println(current.value + " " + current.key + " " + -1);
			}
			else
			{
				System.out.println(current.value + " " + current.key + " " + current.parent.value);
			}
		}
		
	}
	
	private static class VertexQueue {

		private ArrayList<Vertex> queue;

		public VertexQueue(HashMap<Integer, Vertex> graph)
		{
			queue = new ArrayList<Vertex>();
			for (int i = 0; i < numVertices; i++)
			{
				queue.add(graph.get(i));
			}
			buildMinHeap();
		}

		public void insert(Vertex element) 
		{
			queue.add(element);
			decreaseKey(queue.get(queue.indexOf(element)), element);
		}

		public Vertex minimum() 
		{
			return queue.get(0);
		}

		public Vertex extractMin() 
		{
			if (queue.isEmpty())
			{
				System.out.println("Error: the heap is empty.");
				System.exit(0);
			}

			Vertex minVertex = queue.get(0);
			queue.set(0, queue.get(queue.size() - 1));
			queue.remove(queue.size() - 1);
			if (!queue.isEmpty())
			{
				minHeapify(queue.get(0));
			}
			return minVertex;
		}
		
		public boolean isEmpty()
		{
			return queue.isEmpty();
		}
		
		public boolean contains(Vertex element)
		{
			return queue.contains(element);
		}

		private void decreaseKey(Vertex element, Vertex key) 
		{
			queue.set(queue.indexOf(element), key);
			int i = queue.indexOf(key);
			while (i > 0 && queue.get(i / 2).key > queue.get(i).key)
			{
				Vertex swap = queue.get(i / 2);
				queue.set(i / 2, queue.get(i));
				queue.set(i, swap);
				i /= 2;
			}
		}

		private void minHeapify(Vertex element) 
		{
			int elementIndex = queue.indexOf(element);
			int smallestIndex = 0;

			if (elementIndex == -1)
			{
				System.out.println("The vertex does not exisit in the queue.");
				System.exit(0);
			}

			int leftIndex = 2 * elementIndex + 1;
			int rightIndex = 2 * elementIndex + 2;

			if (leftIndex < queue.size() && queue.get(leftIndex).key < element.key)
			{
				smallestIndex = leftIndex;
			}
			else if (leftIndex < queue.size() && queue.get(leftIndex).key == element.key 
					&& queue.get(leftIndex).key < element.key)
			{
				smallestIndex = leftIndex;
			}
			else
			{
				smallestIndex = elementIndex;
			}

			if (rightIndex < queue.size() && queue.get(rightIndex).key < queue.get(smallestIndex).key)
			{
				smallestIndex = rightIndex;
			}
			else if (rightIndex < queue.size() && queue.get(rightIndex).key == queue.get(smallestIndex).key && 
					queue.get(rightIndex).key < queue.get(smallestIndex).key)
			{
				smallestIndex = rightIndex;
			}

			if (smallestIndex != elementIndex)
			{
				Vertex key = queue.get(elementIndex);
				queue.set(elementIndex, queue.get(smallestIndex));
				queue.set(smallestIndex, key);
				minHeapify(queue.get(smallestIndex));
			}

		}

		private void buildMinHeap() 
		{
			for (int i = queue.size() / 2; i >= 0; i--)
			{
				minHeapify(queue.get(i));
			}
		}

	}

	private static class Vertex {

		private int value;
		private HashMap<Vertex, Integer> edgeWeights;
		private int key;
		private Vertex parent; 
		private ArrayList<Vertex> adj;

		public Vertex(int v)
		{
			value = v;
			edgeWeights = new HashMap<>();
			key = 0;
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

		public void addEdge(Vertex adjacent, int weight)
		{
			edgeWeights.put(adjacent, weight);
		}
		
		public String toString()
		{
			return "" + value;
		}
	}

}
