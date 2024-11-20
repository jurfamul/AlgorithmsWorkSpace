/**
 * 
 * Jurgen Famula
 * Programming Assignment Six
 * 10-30-17
 * GraphSearchDriver Class: This driver prompts the user to enter the name of the input file and the value of the source vertex for the breadth 
 * first search. The graph is then created using the UndirectedGraph class and the source vertex's value is used to carry out the breadth first search.
 * The values, distances, and paths of each vertex are then printed to standard output.
 * 
 */
import java.util.Scanner;

public class GraphSearchDriver {

	public static void main(String[] args) {
		
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Please enter the name of the input file: ");
		String fileName = keyboard.nextLine();
		System.out.println("please enter the source vertex for the search: ");
		int source = keyboard.nextInt();
		
		UndirectedGraph graph = new UndirectedGraph(fileName);
		graph.breathFirstSearch(source);
		
		for (int i = 0; i < graph.size(); i++)
		{
			graph.printPath(source, i);
		}
	}

}
