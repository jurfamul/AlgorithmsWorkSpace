import java.util.ArrayList;
import java.util.Random;

public class RunTimes {

	//InsertionSort
	public static void InsertionSort(ArrayList<Integer> a){

		for (int j = 0 ; j < a.size(); j++ ) { 

			int key = a.get(j);
			int i = j - 1;

			while(i >= 0 && a.get(i) >= key){

				a.set(i+1, a.get(i));
				i = i-1;
				a.set(i+1, key);

			}	
		}

	}

	// Method that Generates a Random Array
	public ArrayList<Integer> RandomArray(int n){

		ArrayList<Integer> a1 = new ArrayList<>(); // Create a new array and fill it with random value
		Random r = new Random();
		for (int i = 0; i < n; ++i) {
			a1.add(r.nextInt());
		}

		return a1;
	}


	// Method that checks if the ArrayList is Sorted.
	public boolean IsSorted(ArrayList<Integer> a){

		for (int i = 0; i < a.size() - 1; i++)
        {
            if (a.get(i) > a.get(i+1))
            {
                return false;
            }
        }
        return true;
	}


	// MergeSort
	public void MergeSort(ArrayList a, int p, int r) {       

		if( p < r ) {

			int q = (p + r)/2;
			MergeSort(a,p,q);
			MergeSort(a,q + 1,r);

			// Calling Merge
			Merge(a,p,q,r); 
		}
	}

	public static void MergeSort(ArrayList<Integer> a)
	{
		int p = 0;
		int r = a.size()-1;
		MergeSortRecursive(a,p,r);
	}

	// MergeSort Recursive Method
	public static void MergeSortRecursive(ArrayList a, int p, int r) {       

		if( p < r ) {

			int q = (p + r)/2;
			MergeSortRecursive(a,p,q);
			MergeSortRecursive(a,q + 1,r);

			// Calling Merge
			Merge(a,p,q,r); 
		}
	}

	private static void Merge(ArrayList<Integer>arr, int p, int q, int r)
	{
		int n1 = q - p + 1;
		int n2 = r-q;

		ArrayList<Integer> left_arr = new ArrayList<>(n1);
		ArrayList<Integer> right_arr = new ArrayList<>(n2);

		for (int i = 0; i < n1; i++)
		{
			left_arr.add(arr.get(p+i));
		}

		for (int i = 0; i < n2; i++)
		{
			right_arr.add(arr.get(q+i+1));
		}

		int i = 0;
		int j = 0;
		int k = p;

		while(i < n1 && j < n2)
		{
			if (left_arr.get(i) <= right_arr.get(j))
			{
				arr.set(k,left_arr.get(i));
				i++;
			}
			else
			{
				arr.set(k,right_arr.get(j));
				j++;
			}
			k++;
		}

		if (i == n1)
		{
			for (int z = j; z < right_arr.size(); z++)
			{
				arr.set(k,right_arr.get(z));
				k++;
			}
		}
		else if (j == n2 )
		{
			for (int z = i; z < left_arr.size(); z++)
			{
				arr.set(k,left_arr.get(z));
				k++;
			}
		}

	}

	public static void main(String[] args) {

		RunTimes r = new RunTimes();

		for(int n = 8; n <= 8192; n= n*2){

			ArrayList<Integer> list = r.RandomArray(n); // Creataing Random Array
			ArrayList<Integer> a2 = new ArrayList(list); // Creating copy of the Array

			double i = 0,j = 0;
			int iter = Math.max(4,8192/n);

			//Timing of Insertion Sort
			CpuTimer timer1 = new CpuTimer();
			InsertionSort(a2);
			i = timer1.getElapsedCpuTime();

			//Timing of Merge Sort
			CpuTimer timer2 = new CpuTimer();
			MergeSort(a2); 
			j = timer2.getElapsedCpuTime();

			// Printing output size and Average Times
			System.out.print("Avg. times for n = " + n + " . Insertion sort: " + i/iter + " , Merge Sort: " + j/iter + " , Sorted? " + r.IsSorted(a2) );
			System.out.println();

		}

	}	
}
	   
		
