package tester;

import java.util.ArrayList;

import project.Tweet;
import project.Twitter;

public class TwitterTest {
	public static void AddTweet() throws AssertEqualsException {
		Twitter twitter = Utils.filledTwitter();

		System.out.print("-> Checking Twitter.addTweet()");
		// Test 1: add a null tweet
		System.out.print(".");
		twitter.addTweet(null);
		// Test 2: add a normal tweet
		System.out.print(".");
		Tweet tweet = new Tweet("Adam", "2017-03-03 03:35:38", "I love 250! 250 loves you too Adam.");
		twitter.addTweet(tweet);
		assertEquals(tweet, twitter.latestTweetByAuthor("Adam"));

		System.out.println("Done!");
		System.out.println("* Make sure that you cannot add a null Tweet in Twitter.addTweet()!");
		System.out.println("No errors with Twitter.addTweet()\n");
	}
	
	public static void LatestTweetByAuthor() throws AssertEqualsException {

		System.out.print("-> Checking Twitter.latestTweetByAuthor()");
		//Test 1: no tweets
		System.out.print(".");
		Twitter twitter = new Twitter( new ArrayList<Tweet>(), new ArrayList<String>());
		assertNull(twitter.latestTweetByAuthor("Adam"));
		//Test 2: null author
		twitter = Utils.filledTwitter();
		System.out.print(".");
		assertNull(twitter.latestTweetByAuthor(null));
		//Test 3: not an author
		System.out.print(".");
		assertNull(twitter.latestTweetByAuthor("Adam"));
		//Test 4: correct author
		System.out.print(".");
		Tweet tweet = new Tweet("Adam", "2017-03-03 03:35:38", "I love 250!");
		twitter.addTweet(tweet);
		assertEquals(tweet, twitter.latestTweetByAuthor("Adam"));
		
		System.out.println("Done!");
		System.out.println("No errors with Twitter.latestTweetByAuthor()\n");
	}
	
	public static void TweetsByDate() throws AssertEqualsException {
		System.out.print("-> Checking Twitter.tweetsByDate()");
		
		//Test 1: null date
		System.out.print(".");
		Twitter twitter = new Twitter( new ArrayList<Tweet>(), new ArrayList<String>());
		assertNull(twitter.tweetsByDate(null));
		//Test 2: date is non-existent
		twitter = Utils.filledTwitter();
		System.out.print(".");
		assertNull(twitter.tweetsByDate("1990-03-03"));
		//Test 3: normal query
		System.out.print(".");
		ArrayList<Tweet> myTweets = new ArrayList<>();
		myTweets.add(new Tweet("Adam", "2017-03-03 03:35:38", "1"));
		myTweets.add(new Tweet("Adam", "2017-03-03 03:36:38", "2"));
		myTweets.add(new Tweet("Adam", "2017-03-03 03:37:38", "3"));
		twitter = new Twitter( myTweets, new ArrayList<String>());
		assertEquals(myTweets, twitter.tweetsByDate("2017-03-03"));
		
		System.out.println("Done!");
		System.out.println("No errors with Twitter.tweetsByDate()\n");
	}
	
	public static void TrendingTopics() throws AssertEqualsException {
		ArrayList<Tweet> myTweets = new ArrayList<>();
		myTweets.add(new Tweet("Adam", "2017-03-03 03:35:38", "Hi, Adam Adam "));
		myTweets.add(new Tweet("Adam", "2017-03-03 03:36:38", "Hi, Felix Adam"));
		myTweets.add(new Tweet("Adam", "2017-03-03 03:37:38", "Hi, Felix Felix Adam"));
		Twitter twitter = new Twitter( myTweets, new ArrayList<String>());
		
		ArrayList<String> answerWithoutStop = new ArrayList<>();
		answerWithoutStop.add("hi"); //should be 3
		answerWithoutStop.add("adam"); //should be 3
		answerWithoutStop.add("felix");// should be 2
		
		ArrayList<String> stopWords = new ArrayList<>();
		stopWords.add("adam");
		
		ArrayList<String> answerWithStop = new ArrayList<>();
		answerWithStop.add("hi"); //should be 3
		answerWithStop.add("felix");// should be 2
		
		System.out.print("-> Checking Twitter.trendingTopics()");
		
		//Test 1: without stopwords
		System.out.print(".");
		assertEquals(answerWithoutStop, twitter.trendingTopics());
		
		//Test 2: with stopwords
		System.out.print(".");
		twitter = new Twitter( myTweets, stopWords);
		assertEquals(answerWithStop, twitter.trendingTopics());
		
		System.out.println("Done!");
		System.out.println("* Make sure to ignore case for a word");
		System.out.println("* Make sure that a word in a Tweet is only counted once");
		System.out.println("No errors with Twitter.trendingTopics()\n");
	}
	
	private static <T> boolean assertEquals(T expected, T actual) throws AssertEqualsException {

		if (expected.equals(actual)) {
			return true;
		}

		throw new AssertEqualsException(" Expected: " + expected + ", but got actual: " + actual);
	}

	private static <T> boolean assertNull(T actual) throws AssertEqualsException {

		if (actual == null) {
			return true;
		}

		throw new AssertEqualsException(" Expected: null" + ", but got actual: " + actual);
	}
}
