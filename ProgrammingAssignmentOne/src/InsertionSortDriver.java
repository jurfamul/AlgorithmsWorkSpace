/**
 * 
 * Jurgen Famula
 * Programming Project one
 * InsertionSortDriver
 * 
 */
import java.util.ArrayList;

public class InsertionSortDriver {

	public static void main(String[] args) {
		
		ArrayList<String> fileName = new ArrayList<>();
		fileName.add("P1Example.txt"); // the example number set from the project page, used to test correctness. array index: 0
		fileName.add("P1Test1.txt"); // sample number set containing 1 thought 10 in reverse order. array index: 1
		fileName.add("P1Test2.txt"); // sample number set containing 1 thought 10 in reverse order and out of order duplicates. array index: 2
		fileName.add("P1Test3.txt"); // sample number set containing 50 random integers between -10 and 100. array index: 3
		
		InsertionSortMachine.sort(fileName.get(0));

	}

}
