package Starter_code;

import java.util.ArrayList;
import java.util.Random;
import java.util.Arrays;

public class stress_test {
	static Random random = new Random();
	
	public static void main(String[] args)
	{
		checkSorting(200, 30); // checks sorting order
		checkAlgorithmComplexityFastSort(); // checks fastSort algorithm complexity
		//checkAlgorithmComplexitySlowSort(); // same engine, checks slowSort algorithm complexity
    }
	
	public static void checkAlgorithmComplexityFastSort()
	{
		System.out.printf("-> Checking algorithm complexity of fastSort ");
		
		int run_count = 150;
		long runtime[] = new long[run_count];

		for(int x = 1; x<=run_count; x++)
		{
			runtime[x-1] = meanRunTimeFastSort(5, 100*x, 100);
			System.out.print(".");
			//System.out.println("run " + x + ": "+ runtime[x-1] + " ms");
		}
		System.out.println("done!");
		System.out.printf("Average run time of fastSort: ");
		System.out.println(checkAlgorithmComplexity(runtime) +  "\n");
	}

	public static void checkAlgorithmComplexitySlowSort()
	{
		System.out.printf("-> Checking algorithm complexity of slowSort ");
		
		int run_count = 35;
		long runtime[] = new long[run_count];

		for(int x = 1; x<=run_count; x++)
		{
			runtime[x-1] = meanRunTimeSlowSort(5, 100*x, 100);
			System.out.printf(".");
			//System.out.println("run " + x + ": "+ runtime[x-1] + " ms");
		}
		System.out.println("done!");
		System.out.printf("Average run time of slowSort: ");
		System.out.println(checkAlgorithmComplexity(runtime) +  "\n");
	}
	
	public static long meanRunTimeFastSort(int numLoops, int numElements, int str_length)
	{
		long time_used[] = new long[numLoops];
		long time_in;
		MyHashTable<Integer, String> testHash;
		testHash = fillHashTable(numElements, str_length); 
		
		for(int x = 0; x<numLoops; x++)
		{
			time_in = System.currentTimeMillis();
			ArrayList<Integer> sortedKey = MyHashTable.fastSort(testHash);
			time_used[x] = System.currentTimeMillis() - time_in;
		}
		Arrays.sort(time_used, 0, time_used.length-1);
		return time_used[time_used.length/2];
	}
	
	public static long meanRunTimeSlowSort(int numLoops, int numElements, int str_length)
	{
		long time_used[] = new long[numLoops];
		long time_in;
		MyHashTable<Integer, String> testHash;
		testHash = fillHashTable(numElements, str_length); 
		
		for(int x = 0; x<numLoops; x++)
		{
			time_in = System.currentTimeMillis();
			ArrayList<Integer> sortedKey = MyHashTable.slowSort(testHash);
			time_used[x] = System.currentTimeMillis() - time_in;
		}
		Arrays.sort(time_used, 0, time_used.length-1);
		return time_used[time_used.length/2];
	}
	
	public static String checkAlgorithmComplexity(long time[]) // least squares method
	{
		int min_index;
		long ls[] = new long[4];
		long time_sorted[] = new long[time.length];
		long mean;
		double a;
		String[] complexity = {"O(1)", "O(n)", "O(n*log(n))", "O(n^2)"};
		
		for(int x = 0; x<time.length; x++)
			time_sorted[x] = time[x];
		Arrays.sort(time_sorted, 0, time_sorted.length-1);
		mean = time_sorted[time_sorted.length/2];
		
		// O(1): b
		// b is approximated with the mean of the time values
		ls[0] = 0;
		for(int x = 0; x<time.length; x++)
			ls[0] += Math.pow(time[x] - mean, 2);
		
		// O(n): a*n
		a = (time_sorted[time_sorted.length-1] - time_sorted[0])/(time_sorted.length);
		ls[1] = 0;
		for(int x = 0; x<time.length; x++)
			ls[1] += Math.pow(time[x] - (a*x), 2);
		
		// O(n*log(n)): a*log
		a = time_sorted[time_sorted.length-1] / (Math.log(time_sorted.length)) / time_sorted.length;
		ls[2] = 0;
		for(int x = 0; x<time.length; x++)
			ls[2] += Math.pow(time[x] - (a*(x+1)*Math.log(x+1)), 2);
		
		// O(n^2): a*n^2 
		a = time_sorted[time_sorted.length-1] / Math.pow(time_sorted.length, 2);
		ls[3] = 0;
		for(int x = 0; x<time.length; x++)
			ls[3] += Math.pow(time[x] - (a*(x+1)*(x+1)), 2);
		
		min_index = 0;
		for(int x= 1; x<ls.length; x++)
		{
			if(ls[x] < ls[min_index])
				min_index = x;
		}	
		return complexity[min_index];
	}
	
	public static void checkSorting(int entries, int str_length)
	{
		System.out.printf("-> Checking fastSort output order ...");
		
		boolean sortingError = false;
		MyHashTable<Integer, String> testHash;
		
		testHash = fillHashTable(200, 10); 
    	ArrayList<Integer> sortedKey = MyHashTable.slowSort(testHash);
    	
    	for(int x = 0; x<sortedKey.size(); x++)
    	{
    		if((x+1) < sortedKey.size())
    			if(testHash.get(sortedKey.get(x)).compareTo(testHash.get(sortedKey.get(x+1))) < 0)
    			{
    				System.out.println("Error in sorting at index" + x);
    				sortingError = true;
    			}
    	}
    	System.out.printf("done!\n");
    	if(sortingError)
    		System.out.println("Error in the sorted array. Check previous lines to see the position of the error(s)\n");
    	else
    		System.out.println("No error found in the sorted array\n");
	}
	
	public static MyHashTable<Integer, String> fillHashTable(int entries, int str_length)
	{
		MyHashTable<Integer, String> table = new MyHashTable<Integer, String>(10);
		
		for(int x = 0; x<entries; x++)
			table.put(random.nextInt(), randomString(str_length));
		
		return table;
	}
	
	public static String randomString(int length)
	{
		String result = "";
		for(int x = 0; x<length; x++)
			result = result + (char)(random.nextInt(26) + 'a');
		
		return result;
	}
	
}
