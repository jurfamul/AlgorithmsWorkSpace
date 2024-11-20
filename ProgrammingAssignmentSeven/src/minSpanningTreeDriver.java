import java.util.Scanner;

public class minSpanningTreeDriver {

	public static void main(String[] args) {
		
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Please enter the name of the input file: ");
		String fileName = keyboard.nextLine();
		System.out.println("please enter the root vertex for the minimum spanning Tree: ");
		int root = keyboard.nextInt();
		
		WeightedGraph graph = new WeightedGraph(fileName);
		graph.PrimsMST(root);
		graph.printMinSpanningTree();

	}

}
