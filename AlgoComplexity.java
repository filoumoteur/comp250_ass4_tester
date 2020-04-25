package tester;

import java.util.ArrayList;
import java.util.Random;

import project.MyHashTable;
import project.Tweet;
import project.Twitter;

import java.util.Arrays;

public class AlgoComplexity {
	static Random random = new Random();
	
	public static void main(String[] args)
	{
		//checkSorting(200, 30); // checks sorting order
		
		//checkAlgorithmComplexityFastSort(); // checks fastSort algorithm complexity
		//checkAlgorithmComplexitySlowSort(); // same engine, checks slowSort algorithm complexity
		//checkAlgorithmComplexityPut();
		//checkAlgorithmComplexityValues();
		//checkAlgorithmComplexityTwitterConstructor();
	}
	public static void checkAlgorithmComplexityValues()
	{
		System.out.printf("-> Checking algorithm complexity of values ");
		
		int run_count = 15;
		long runtime[] = new long[run_count];
	
		for(int x = 1; x<=run_count; x++)
		{
			runtime[x-1] = meanRunTimeValues(20, (int)Math.pow(1.5, x), 100);
			System.out.print(".");
			//System.out.println("run " + x + ": "+ runtime[x-1] + " us");
		}
		
		System.out.println("done!");
		System.out.printf("Average run time of values: ");
		System.out.println(checkAlgorithmComplexity(runtime) +  "\n");
	}
	public static long meanRunTimeValues(int numLoops, int numElements, int str_length)
	{
		long time_used[] = new long[numLoops];
		long time_in;
		MyHashTable<Integer, String> testHash;
		testHash = Utils.filledHashTable(numElements, str_length); 
		
		for(int x = 0; x<numLoops; x++)
		{
			//System.gc();
			//time_in = System.currentTimeMillis();
			time_in = System.nanoTime()/1000;
			testHash.values();
			//time_used[x] = System.currentTimeMillis() - time_in;
			time_used[x] = System.nanoTime()/1000 - time_in;
		}
		Arrays.sort(time_used, 0, time_used.length-1);
		return time_used[time_used.length/2];
	}
	
	public static void checkAlgorithmComplexityTwitterConstructor()
	{
		System.out.printf("-> Checking algorithm complexity of Twitter's constructor based on number of Tweets");
		
		int run_count = 20;
		long runtime[] = new long[run_count];
	
		for(int x = 1; x<=run_count; x++)
		{
			runtime[x-1] = meanRunTimeTwitterConstructorNtweets(10, (int)Math.pow(1.5, x));
			System.out.print(".");
			//System.out.println("run " + x + ": "+ runtime[x-1] + " us");
		}
		
		System.out.println("done!");
		System.out.printf("Average run time of Twitter's constructor N Tweet: ");
		System.out.println(checkAlgorithmComplexity(runtime) +  "\n");
		
		System.out.printf("-> Checking algorithm complexity of Twitter's constructor based on number of StopWords");
		for(int x = 1; x<=run_count; x++)
		{
			runtime[x-1] = meanRunTimeTwitterConstructorNstopWords(10, (int)Math.pow(1.7, x));
			System.out.print(".");
			//System.out.println("run " + x + ": "+ runtime[x-1] + " us");
		}
		
		System.out.println("done!");
		System.out.printf("Average run time of Twitter's constructor M stopWords: ");
		System.out.println(checkAlgorithmComplexity(runtime) +  "\n");
	}
	public static long meanRunTimeTwitterConstructorNtweets(int numLoops, int numElements)
	{
		long time_used[] = new long[numLoops];
		long time_in;
		ArrayList<Tweet> tweet = new ArrayList<Tweet>();
		ArrayList<String> stopWord = new ArrayList<String>();
		
		for(int x = 0; x<numElements; x++)
			tweet.add(Utils.randomTweet());
		
		for(int x = 0; x<numLoops; x++)
		{
			time_in = System.nanoTime()/1000;
			new Twitter(tweet, stopWord);
			time_used[x] = System.nanoTime()/1000 - time_in;
		}
		Arrays.sort(time_used, 0, time_used.length-1);
		return time_used[time_used.length/2];
	}
	public static long meanRunTimeTwitterConstructorNstopWords(int numLoops, int numElements)
	{
		long time_used[] = new long[numLoops];
		long time_in;
		ArrayList<Tweet> tweet = new ArrayList<Tweet>();
		ArrayList<String> stopWord = new ArrayList<String>();
		
		for(int x = 0; x<numElements; x++)
			stopWord.add(Utils.randomString(15));
		
		for(int x = 0; x<numLoops; x++)
		{
			time_in = System.nanoTime()/1000;
			new Twitter(tweet, stopWord);
			time_used[x] = System.nanoTime()/1000 - time_in;
		}
		Arrays.sort(time_used, 0, time_used.length-1);
		return time_used[time_used.length/2];
	}
	public static void checkAlgorithmComplexityTrending()
	{
		System.out.printf("-> Checking algorithm complexity of Trending ");
		
		int run_count = 15;
		long runtime[] = new long[run_count];
	
		for(int x = 1; x<=run_count; x++)
		{
			runtime[x-1] = meanRunTimeTrending(20, (int)Math.pow(1.7, x), (int)Math.pow(1.7, x));
			System.out.print(".");
			//System.out.println("run " + x + ": "+ runtime[x-1] + " us");
		}
		
		System.out.println("done!");
		System.out.printf("Average run time of Trending: ");
		System.out.println(checkAlgorithmComplexity(runtime) +  "\n");
	}
	public static long meanRunTimeTrending(int numLoops, int numElements, int stop_size)
	{
		long time_used[] = new long[numLoops];
		long time_in;
		Twitter twitter; 
		ArrayList<String> stopWords = new ArrayList<String>();
		
		for(int x = 0; x<stop_size; x++)
			stopWords.add(Utils.randomString(10));
		
		twitter = Utils.filledTwitter(numElements, stopWords);
		
		for(int x = 0; x<numLoops; x++)
		{
			time_in = System.nanoTime()/1000;
			twitter.trendingTopics();
			time_used[x] = System.nanoTime()/1000 - time_in;
		}
		Arrays.sort(time_used, 0, time_used.length-1);
		return time_used[time_used.length/2];
	}
	
	public static void checkAlgorithmComplexityDate()
	{
		System.out.printf("-> Checking algorithm complexity of latestByAuthor ");
		
		int run_count = 15;
		long runtime[] = new long[run_count];
	
		for(int x = 1; x<=run_count; x++)
		{
			runtime[x-1] = meanRunTimeDate(20, 20, (int)Math.pow(1.8, x));
			System.out.print(".");
			//System.out.println("run " + x + ": "+ runtime[x-1] + " us");
		}
		
		System.out.println("done!");
		System.out.printf("Average run time of LatestByAuthor: ");
		System.out.println(checkAlgorithmComplexity(runtime) +  "\n");
	}
	public static long meanRunTimeDate(int numLoops, int subLoops, int numElements)
	{
		long time_used[] = new long[numLoops];
		long time_in;
		Twitter twitter = Utils.filledTwitter(numElements, new ArrayList<String>()); 

		for(int x = 0; x<numLoops; x++)
		{
			time_in = System.nanoTime()/1000;
			for(int y= 0; y<subLoops; y++)
				twitter.tweetsByDate("2010-03-04");
			time_used[x] = System.nanoTime()/1000 - time_in;
		}
		Arrays.sort(time_used, 0, time_used.length-1);
		return time_used[time_used.length/2];
	}
	
	public static void checkAlgorithmComplexityLatest()
	{
		System.out.printf("-> Checking algorithm complexity of latestByAuthor ");
		
		int run_count = 15;
		long runtime[] = new long[run_count];
	
		for(int x = 1; x<=run_count; x++)
		{
			runtime[x-1] = meanRunTimeLatest(20, 20, (int)Math.pow(1.5, x));
			System.out.print(".");
			//System.out.println("run " + x + ": "+ runtime[x-1] + " us");
		}
		
		System.out.println("done!");
		System.out.printf("Average run time of LatestByAuthor: ");
		System.out.println(checkAlgorithmComplexity(runtime) +  "\n");
	}
	public static long meanRunTimeLatest(int numLoops, int subLoops, int numElements)
	{
		long time_used[] = new long[numLoops];
		long time_in;
		Twitter twitter = Utils.filledTwitter(numElements, new ArrayList<String>()); 
		twitter.addTweet(new Tweet("Felix", "2027-03-03 03:35:38", "Hi, Adam I hope your exams are going well, take care"));
		
		for(int x = 0; x<numLoops; x++)
		{
			time_in = System.nanoTime()/1000;
			for(int y= 0; y<subLoops; y++)
				twitter.latestTweetByAuthor("Felix");
			time_used[x] = System.nanoTime()/1000 - time_in;
		}
		Arrays.sort(time_used, 0, time_used.length-1);
		return time_used[time_used.length/2];
	}
	
	public static void checkAlgorithmComplexityAddTweet()
	{
		System.out.printf("-> Checking algorithm complexity of addTweet ");
		
		int run_count = 15;
		long runtime[] = new long[run_count];
	
		for(int x = 1; x<=run_count; x++)
		{
			runtime[x-1] = meanRunTimeAddTweet(20, 20, (int)Math.pow(1.5, x));
			System.out.print(".");
			//System.out.println("run " + x + ": "+ runtime[x-1] + " us");
		}
		
		System.out.println("done!");
		System.out.printf("Average run time of values: ");
		System.out.println(checkAlgorithmComplexity(runtime) +  "\n");
	}
	public static long meanRunTimeAddTweet(int numLoops, int subLoops, int numElements)
	{
		long time_used[] = new long[numLoops];
		long time_in;
		ArrayList<String> miaw = new ArrayList<String>();
		Twitter twitter = Utils.filledTwitter(numElements, miaw); 
		ArrayList<Tweet> tweet = new ArrayList<Tweet>();
		
		for(int x= 0;x<subLoops; x++)
			tweet.add(Utils.randomTweet());
		
		for(int x = 0; x<numLoops; x++)
		{
			time_in = System.nanoTime()/1000;
			for(int y= 0; y<subLoops; y++)
				twitter.addTweet(tweet.get(y));
			time_used[x] = System.nanoTime()/1000 - time_in;
		}
		Arrays.sort(time_used, 0, time_used.length-1);
		return time_used[time_used.length/2];
	}
	public static void checkAlgorithmComplexityPut() // can't make sense of this method. How should a O(1) function grow?
	{
		System.out.printf("-> Checking algorithm complexity of MyHashTable.put ");
		
		int run_count = 20;
		long runtime[] = new long[run_count];

		for(int x = 0; x<4; x++) // seems to fix the first redundant pts in the collection of timing..
			meanRunTimePut(20, 200);
		
		for(int x = 1; x<=run_count; x++)
		{
			runtime[x-1] = meanRunTimePut(20, 200);
			System.out.print(".");
			//System.out.println("run " + x + ": "+ runtime[x-1] + " us");
		}
		System.out.println("done!");
		System.out.printf("Average run time of MyHashTable.put: ");
		System.out.println(checkAlgorithmComplexity(runtime) +  "\n");
	}
	public static long meanRunTimePut(int numLoops, int subLoops)
	{
		long time_used[] = new long[numLoops];
		long time_in;
		MyHashTable<Integer, String> testHash = new MyHashTable<Integer, String>(subLoops*10);
		
		for(int y = 0; y<numLoops; y++)
		{
			time_in = System.nanoTime()/1000;
			for(int x = 0; x<subLoops; x++)
				testHash.put(random.nextInt(), "ABC" + x + y);	
			
			time_used[y] = System.nanoTime()/1000 - time_in;
		}
		Arrays.sort(time_used, 0, time_used.length-1);
		return time_used[time_used.length/2];
	}
	
	public static void checkAlgorithmComplexityFastSort()
	{
		System.out.printf("-> Checking algorithm complexity of fastSort ");
		
		int run_count = 17;
		long runtime[] = new long[run_count];

		for(int x = 1; x<=run_count; x++)
		{
			runtime[x-1] = meanRunTimeFastSort(5, (int)Math.pow(2, x), 100);
			System.out.print(".");
			//System.out.println("run " + x + ": "+ runtime[x-1] + " us");
		}
		System.out.println("done!");
		System.out.printf("Average run time of fastSort: ");
		System.out.println(checkAlgorithmComplexity(runtime) +  "\n");
	}

	public static void checkAlgorithmComplexitySlowSort()
	{
		System.out.printf("-> Checking algorithm complexity of slowSort ");
		
		int run_count = 12;
		long runtime[] = new long[run_count];

		for(int x = 1; x<=run_count; x++)
		{
			runtime[x-1] = meanRunTimeSlowSort(5, (int)Math.pow(2, x), 100);
			System.out.printf(".");
			//System.out.println("run " + x + ": "+ runtime[x-1] + " us");
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
			time_in = System.nanoTime()/1000;
			MyHashTable.fastSort(testHash);
			time_used[x] = System.nanoTime()/1000 - time_in;
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
			time_in = System.nanoTime()/1000;
			MyHashTable.slowSort(testHash);
			time_used[x] = System.nanoTime()/1000 - time_in;
		}
		Arrays.sort(time_used, 0, time_used.length-1);
		return time_used[time_used.length/2];
	}
	
	public static String checkAlgorithmComplexity(long y[]) // least squares method
	{
		int min_index;
		long ls[] = new long[4];
		long y_sorted[] = new long[y.length];
		long x[] = new long[y.length];
		double a, b;
		String[] complexity = {"O(1)", "O(n)", "O(n*log(n))", "O(n^2)"};
		
		for(int i = 0; i<y.length; i++)
		{
			y_sorted[i] = y[i];
			x[i] = (long)Math.pow(2, i);
		}
		Arrays.sort(y_sorted, 0, y_sorted.length-1);
		
		// O(1): b
		// b is approximated with the mean of the time values
		ls[0] = 0;
		b = y_sorted[y_sorted.length/2];
		for(int i = 0; i<y.length; i++)
			ls[0] += Math.pow(y[i] - b, 2);
		
		// O(n): a*n + b
		b = y_sorted[0];
		a = (double)(y[y.length-1] - b)/(x[x.length-1]-x[0]);
		ls[1] = 0;
		for(int i = 0; i<y.length; i++)
			ls[1] += Math.pow(y[i] - (a*x[i]+b), 2);
		
		// O(n*log(n)): a*n*log(b*n)
		b = Math.pow(x[x.length-1], (double)y[y.length-2]*x[x.length-1]/(y[y.length-1]*x[x.length-2] - y[y.length-2]*x[x.length-1]));
		b = b/Math.pow(x[x.length-2], (double)y[y.length-1]*x[x.length-2]/(y[y.length-1]*x[x.length-2] - y[y.length-2]*x[x.length-1]));
		a = (double)y[y.length-2]/x[x.length-2]/Math.log(b*x[x.length-2]);
		ls[2] = 0;
		//System.out.println(b);
		for(int i = 0; i<y.length; i++)
			ls[2] += Math.pow(y[i] - (a*x[i]*Math.log(b*x[i])), 2);
		if(b <= .00001)
		ls[2] *= 10; // if b is really small, the curve fits an O(n)
			
		// O(n^2): a*n^2
		a = (double)(y[y.length-1]) / Math.pow(x[x.length-1], 2);
		ls[3] = 0;
		for(int i = 0; i<y.length; i++)
			ls[3] += Math.pow(y[i] - (a*x[i]*x[i]), 2);
		
		//System.out.println(Arrays.toString(ls));
		
		min_index = 0;
		for(int i = 1; i<ls.length; i++)
		{
			if(ls[i] < ls[min_index])
				min_index = i;
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
