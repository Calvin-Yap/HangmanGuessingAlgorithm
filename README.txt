 _____       _       _        __   __          
/  __ \     | |     (_)       \ \ / /          
| /  \/ __ _| |_   ___ _ __    \ V /__ _ _ __  
| |    / _` | \ \ / / | '_ \    \ // _` | '_ \ 
| \__/\ (_| | |\ V /| | | | |   | | (_| | |_) |
 \____/\__,_|_| \_/ |_|_| |_|   \_/\__,_| .__/ 
                                        | |    
                                        |_|    

Hangman Algorithm

The snowmanrunner file is the program to run the snowmanplayer

Inside the player is the guessing Algoritim 

How it works:

In the start of the project you are given a textfile that includes all the words in a dictionary.
Using that whenever you are given a word to guess, I begin to narrow down results starting with the size of the word.
Then I use a letter counter that runs through all the availible words and keeps a frequency count for each letter. It will then guess the most popular letter.
After guessing the letter two things happen:
	If the letter is in the word, then it will get its position and remove all other words from the pool that doesn't have the letter.
	If the letter is not in the word, then it will remove all words in the pool containing that letter.


This code will be updated further for better accuracy.
Currently it hits 19 misses for 10 rounds with it varrying from 1-5 more depending on the seed.