/* NAME: CALVIN YAP
 * STUDENT #: 500825267
 */
import java.util.*;
public class SnowmanPlayer {	
	//Global Variables
	public static List <String> listWord = new ArrayList <String>(); // List of all dictionary words
	public static List <Character> letters = new ArrayList <Character>(); // List of all usable letters
	public static List<String> secretWord = new ArrayList <String>(); // List of all possible hidden words
	public static int counter =0; // Counter to get the first time it runs
	/**
	 * This method returns the Last Name, and the First name of the Coder of this program.
	 * @return the name of the Author in String
	 */
	public static String getAuthor()
	{
	return "Yap, Calvin"; // Returns the name of the student
	}	
	/**
	 * This method runs once at the start of the program, called by SnowmanRunner. It will be used to initialize the words into an array list
	 * and assign the possible letters to another List
	 * @param words is an Array of string that all the possible words from the text file will be taken from
	 * @param minLength is the minimum length of all the possible words
	 * @param maxLength is the maximum length of all the possible words
	 * @param allowedChars is String of all possible letters that you can guess from (Lowercase 'a' - 'z')
	 */
	public static void startGame(String[] words, int minLength, int maxLength, String allowedChars)
	{
		listWord = (Arrays.asList(words)); // Assigns all the words in the text file to the arraylist of possible words
			// Add all the possible guesses as in the letters to the arraylist of letters
			for(int i = 0; i<allowedChars.length(); i++)
			{
				letters.add(allowedChars.charAt(i)); // Add each letter to the List
			}
	}	
	/**
	 * This method runs after every Word, called by SnowmanRunner. It will be used to reset the list every time there is a new word
	 * and will find words that only have the same length. It also resets the counter to get the first guess 
	 * @param length is the length of the hidden word
	 */
	public static void startNewWord(int length)
	{
		secretWord = new ArrayList <String>(); // Initialize secretWord which will store the hidden words according to length
		counter =0; // Reset Counter after every word
		for (int i = 0; i< listWord.size();i++)	// Adding the word according to the length of it
		{
			if (listWord.get(i).length() == length) // If the word is the same length
			{
				secretWord.add(listWord.get(i)); // Add it to the new list of possible words
			}
		}
	}
	/**
	 * Is called for every guess by SnowmanRunner, it asks which letter you would like to guess
	 * and after filtering the list and going through a frequency counter. It returns a char of the 
	 * most popular letter from all the possible letters
	 * @param pattern is a string that contains the secret word and at each unknown letter is a '*'
	 * @param previousGuesses is a String that contains all the guessed characters for each secret word
	 * @return          the character that the algorithm is guessing
	 */
	public static char guessLetter(String pattern, String previousGuesses)
	{
	filterList(pattern, previousGuesses); // Call method that filters the possible words	
	int [] letterFreq = new int[26]; // New array to hold the frequency counter
	counter ++;	// Increment counter to see which guess round it is
	int max=letterFreq[0]; // Initialize the max to be the first element
	char temp; // Temporary holder 
	int index = 0; // Index to see where the max element is at
		// Frequency counter
		for(int words = 0; words <secretWord.size();words++) // Going through all words in the list
		{
			for (int i =0; i< pattern.length();i++ ) // Since the list is sorted already to size all possible words have the same length
			{
				temp = secretWord.get(words).charAt(i); // Store in temporary holder
				letterFreq[temp -97]++;	// 'a' in ascii is 97 so if we subtract it from temp it will get us to the proper index of the letter, and it will add 1 to its counter array
			}
		}
		// Setting if the letter is already guess it will be 0 which takes away it's chances to be returned again
		for (int i = 0; i<previousGuesses.length();i++)
		{
			letterFreq[previousGuesses.charAt(i)-97] = 0; // Sets element to 0
		}
		// Finding max value
		for (int i =1; i<letterFreq.length;i++)
		{
			if (letterFreq[i] > max)
				{
					max = letterFreq[i]; // Setting largest num
					index = i; // Getting the index of largest num
				}
		}	
		if (counter == 1) // If it is the first letter guessed
			return 'a';// Lowers misses rate if first letter is 'a'
		else
			return (char)(index+'a'); // Type cast largest letter so it can be returned

	}
	/**
	 * This method is called by guessLetter and it's used to filter out words that can't be the secret word. It uses 2 Arrays to compare
	 * elements to the guessed and the haven't guessed pattern 
	 * @param pattern is a String that was passed on by guessLetter and is the word that contains the secret word
	 * the unknowns are covered as '*'
	 * @param previousGuesses is a String that contains all previous guessed characters that the program has made
	 */
	private static void filterList(String pattern,String previousGuesses)
	{
		char [] patternA = new char [pattern.length()]; // Array to hold the pattern elements
		char [] dictionA = new char [pattern.length()];	// Array to hold each term in the text file	
		for(int words = secretWord.size()-1;words>=0;words--) // Getting all the words, backwards loop to avoid going out of bounds
		{
			for (int index = dictionA.length-1;index>=0;index--) // Getting every letter in the word
			{	
				dictionA[index]=secretWord.get(words).charAt(index); // Store the word 
				patternA[index]=pattern.charAt(index); // Store the pattern
				if (patternA[index]== '*') // If the letter isn't found yet
				{
					if (previousGuesses.contains(""+dictionA[index])) // If it is a '*' check if the previous guess have that word 
					{
						secretWord.remove(words);// If it's in that means it can't be that word
						break;// Break out of loop to avoid Array out of bounds
					} 
				}
				else if(dictionA[index] != patternA[index]) // If the letter at the same index as the letter in pattern don't match it means it can't be that word 
				 	{
						secretWord.remove(words);// So remove it from the list
						break; // Break out of loop since the word is gone and if it keeps going there won't be a word to look in
				 	}
			}
		}	
	}
}


