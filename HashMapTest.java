package tester;

import java.util.ArrayList;
import project.MyHashTable;

public class HashMapTest {
	private final static int NUMBER_OF_ENTRIES = 200;

	public static void PutGetRemove() throws AssertEqualsException {
		System.out.println("-> Cheking good working of MyHashTable.put, get and remove");
		
		MyHashTable<Integer, String> table = new MyHashTable<Integer, String>(10);
		boolean test_failed = false;
		final int nloops = NUMBER_OF_ENTRIES;
		
		/// PUT SECTION
		
		System.out.println("put a null key");
		// Test 1: enter a key that is null
		assertNull(table.put(null, "test1"));
		
		// Test 2: enter a value that is null
		System.out.println("Put a null value");
		assertNull(table.put(2, null));
		
		table = new MyHashTable<Integer, String>(10);
		
		for(int x = 0; x<nloops; x++) // fill the table with nloops elements. As the table was empty, put should always return null
		{
			if(table.put(x*10, "abc" + x) != null) // 10*x is there to create collisions in the hashtable
				test_failed = true;
		}
		
		if(test_failed)
			System.out.println("MyHashTable.put returns non-null value when it should return null value");
		else
			System.out.println("MyHashTable.put correctly returns null value when adding a different key");
		
		if(table.size() == nloops)
			System.out.println("MyHashTable.put correctly stores the size");
		else
			System.out.println("MyHashTable.put incorrectly stores the size!"+ table.size());
		
		
		test_failed = false;
		for(int x = 0; x<nloops; x++) // change the stored elements. Put should always return the old element at the given key
		{
			if(!table.put(x*10, "cde" + x).equals("abc" + x))
				test_failed = true;
		}
		if(test_failed)
			System.out.println("MyHashTable.put doesn't return the old value when overwriting a key");
		else
			System.out.println("MyHashTable.put correctly returns the old value when overwriting a key");
		
		if(table.size() == nloops)
			System.out.println("MyHashTable.put correctly stores the size when overwriting elements");
		else
			System.out.println("MyHashTable.put incorrectly stores the size when overwriting elements!");
		
		/// GET SECTION
		
		test_failed = false;
		for(int x = 0; x<nloops; x++) // test that the new elements are actually there
		{
			if(!table.get(x*10).equals("cde" + x))
				test_failed = true;
		}
		if(test_failed)
			System.out.println("MyHashTable.get doesn't return the stored value at a given key");
		else
			System.out.println("MyHashTable.get correctly returns value stored at a given key");
		
		test_failed = false;
		for(int x = 0; x<nloops; x++) // check that getting a non-existant key returns null
		{
			if(table.get(x*10+1) != null)
				test_failed = true;
		}
		if(test_failed)
			System.out.println("MyHashTable.get doesn't return null to a non-existant key\n");
		else
			System.out.println("MyHashTable.get correctly returns null to a non-existant key\n");
		
		// Test 1: get a null key
		System.out.println("Test get with a null key");
		assertNull(table.get(null));
		
		/// REMOVE SECTION
		
		// Test 1: remove a null key
		System.out.println("Test removing a null key");
		assertNull(table.remove(null));
		
		// Test 2: remove a non-existing key
		System.out.println("Test removing a non-existing key");
		assertNull(table.remove(nloops+1));
		
		boolean remove_returns_wrong = false;
		boolean remove_size_wrong = false;
		boolean remove_doesnt_removes = false;
		for(int x = 0; x<nloops; x++) // check that removing a key actually removes it
		{
			if(table.remove(x*10).equals("cde" + x) == false)
				remove_returns_wrong = true;
			if(table.size() != nloops-x-1)
				remove_size_wrong = true;
			if(table.get(x*10) != null)
				remove_doesnt_removes = true;
		}
		if(remove_returns_wrong)
			System.out.println("Remove should return the removed value!");
		else
			System.out.println("Remove correctly returns the removed value.");
		
		if(remove_size_wrong)
			System.out.println("Remove should reduce the size of the table!");
		else
			System.out.println("Remove correctly reduces the size of the table.");
		
		if(remove_doesnt_removes)
			System.out.println("Remove should actually remove the entry!");
		else
			System.out.println("Remove correctly removes the entry.");
	}

	public static void Rehash() throws AssertEqualsException
	{
		MyHashTable<Integer, String> table = Utils.filledHashTable(NUMBER_OF_ENTRIES, 30);
		
		System.out.println("-> Checking MyHashTable.rehash()");
		// Test 1: check if rehash doubles the bucket size
		int initialBuckets = table.numBuckets();
		table.rehash();
		
		if(table.numBuckets() != 2*initialBuckets)
			System.out.println("Rehash doesn't double the buckets count");
		else
			System.out.println("Rehash correctly doubles the bucket count");
	}

	public static void Keys() throws Exception {
		int size = 5;
		String keys[] = new String[size];

		MyHashTable<String, Integer> myTable = new MyHashTable<String, Integer>(1);

		System.out.print("-> Checking MyHashTable.keys()");
		// Test 1: no keys
		System.out.print(".");
		assertEquals(0, myTable.keys().size());

		// Populate the table
		myTable = new MyHashTable<String, Integer>(1);
		for (int i = 0; i < size; i++) {
			String key = "SAMPLE" + i;
			Integer value = i;
			myTable.put(key, value);
			keys[i] = key;
		}

		// Test 2: make sure that all keys are present in the
		System.out.print(".");
		for (int i = 0; i < size; i++) {
			if (!myTable.keys().contains(keys[i])) {
				throw new Exception("Key: " + keys[i] + " is not present");
			}
		}

		// Test 3: adding the same key twice
		System.out.print(".");
		assertEquals(0, myTable.put("SAMPLE0", 5));
		for (int i = 0; i < size; i++) {
			if (!myTable.keys().contains(keys[i])) {
				throw new Exception("Key: " + keys[i] + " is not present");
			}
		}

		System.out.println("Done!");
		System.out.println("No errors with MyHashTable.keys()\n");
	}

	public static void Values() throws Exception {
		int size = 5;
		Integer values[] = new Integer[size];

		MyHashTable<String, Integer> myTable = new MyHashTable<String, Integer>(1);

		System.out.print("-> Checking MyHashTable.values()");
		// Test 1: no values
		System.out.print(".");
		assertEquals(0, myTable.values().size());

		// Populate the table
		myTable = new MyHashTable<String, Integer>(1);
		for (int i = 0; i < size; i++) {
			String key = "SAMPLE" + i;
			Integer value = i;
			myTable.put(key, value);
			values[i] = value;
		}

		// Test 2: make sure that all values are present in the
		System.out.print(".");
		for (int i = 0; i < size; i++) {
			if (!myTable.values().contains(values[i])) {
				throw new Exception("Value: " + values[i] + " is not present");
			}
		}

		// Test 3: adding the same key twice
		System.out.print(".");
		assertEquals(0, myTable.put("SAMPLE0", 5));
		values[0] = 5;
		for (int i = 0; i < size; i++) {
			if (!myTable.values().contains(values[i])) {
				throw new Exception("Value: " + values[i] + " is not present");
			}
		}

		System.out.println("Done!");
		System.out.println("No errors with MyHashTable.values()\n");
	}

	public static void FastSort() throws AssertEqualsException
	{
		System.out.println("-> Checking MyHashTable.fastSort()");
		// Test 1: sort an empty table
		System.out.println("Sort an empty table");
		MyHashTable<Integer, String> table = new MyHashTable<Integer, String>(1);
		assertEquals(0, MyHashTable.fastSort(table).size());
		// Test 2: check if all is sorted
		System.out.print("Checking the sorting order");
		table = Utils.filledHashTable(NUMBER_OF_ENTRIES, 30);
		ArrayList<Integer> slowList = MyHashTable.slowSort(table);
		ArrayList<Integer> fastList = MyHashTable.fastSort(table);
		for (int i = 0; i < fastList.size(); i++) {
			assertEquals(slowList.get(i), fastList.get(i));
		}

		System.out.println("Done!");
		System.out.println("No errors with MyHashTable.fastSort()\n");
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

/*
class AssertEqualsException extends Exception {
	private static final long serialVersionUID = 1L;

	public AssertEqualsException(String message) {
		super(message);
	}
}*/

