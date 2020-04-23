package tester;

public class Tester {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("=========================================================");
		System.out.println("= Welcome to COMP250 Final Project of Winter 20 tester! =");
		System.out.println("= Credit goes to: @FilouMoteur and @AdamMigliore        =");
		System.out.println("=========================================================\n");
		
		System.out.println("=========================================================");
		System.out.println("=                  Testing MyHashTable                  =");
		System.out.println("=========================================================\n");
		HashMapTest.PutGetRemove();
		HashMapTest.Rehash();
		HashMapTest.Keys();
		HashMapTest.Values();
		HashMapTest.FastSort();

		System.out.println("=========================================================");
		System.out.println("=                    Testing Twitter                    =");
		System.out.println("=========================================================\n");
		TwitterTest.AddTweet();
		TwitterTest.LatestTweetByAuthor();
		TwitterTest.TweetsByDate();
		TwitterTest.TrendingTopics();
		
		System.out.println("=========================================================");
		System.out.println("=            Testing Algorithms complexity              =");
		System.out.println("=========================================================\n");
		AlgoComplexity.checkAlgorithmComplexityFastSort();
		AlgoComplexity.checkAlgorithmComplexityPut();
		AlgoComplexity.checkAlgorithmComplexityValues();
		
	}
}
