# CSX42: Assignment 5
**Name: Shubham Patwa**

-----------------------------------------------------------------------

Following are the commands and the instructions to run ANT on your project.


Note: build.xml is present in [textdecorators/src](./textdecorators/src/) folder.

## Instruction to clean:

```commandline
ant -buildfile textdecorators/src/build.xml clean
```

Description: It cleans up all the .class files that were generated when you
compiled your code.

## Instructions to compile:

```commandline
ant -buildfile textdecorators/src/build.xml all
```
The above command compiles your code and generates .class files inside the BUILD folder.

## Instructions to run:

```commandline
ant -buildfile textdecorators/src/build.xml run -Dinput="input.txt" -Dmisspelled="misspelled.txt" -Dkeywords="keywords.txt" -Doutput="output.txt" -Ddebug="0"
```
Note: Arguments accept the absolute path of the files.

Debug options :

	case 4: debugLevel = DebugLevel.MOST_FREQ_WORD_DECORATOR; break;

	case 3: debugLevel = DebugLevel.KEYWORD_DECORATOR; break;
	
    case 2: debugLevel = DebugLevel.SPELL_CHECK_DECORATOR; break;
	
    case 1: debugLevel = DebugLevel.SENTENCE_DECORATOR; break;
	
    default: debugLevel = DebugLevel.NONE; break;


## Description:

Text Decorators 
• Design a program that accepts the following inputs.
o -Dinput - Input file containing the text to process. The valid characters in the input file are [a-zA-Z0-9\.,\s] where
• a-z - Lowercase alphabets.
• A-Z - Uppercase alphabets.
• \. - Period character.
• , - Comma.
• \s - Any whitespace character. Matches [\r\n\t\f\v].
o -Dmisspelled - Misspelled words file containing words, one per line, that are misspelled.
o -Dkeywords - Keywords file containing keywords, one per line.
o -Doutput - Output file to which the processed input is persisted.
o -Ddebug - Debug value.
• Design a class InputDetails that has datastructure(s) to store, retrieve and update sentences.
• Words in the input file are delimited by one or more spaces.
• Design the following decorators.
o Design a SentenceDecorator. Each sentence is separated by a "." (period) character. The SentenceDecorator prefixes the sentence with BEGIN_SENTENCE__ and suffixes the sentence with __END_SENTENCE. Note that the period character is not considered a part of the sentence.
o Design a SpellCheckDecorator. This decorator checks whether any of the words is a misspelled word. A word is misspelled if it is present in the file provided with the commandline option -Dmisspelled. Misspelled words are prefixed with SPELLCHECK_ and suffixed with _SPELLCHECK. Note: The algorithm should be case insensitive.
o Design a MostFrequentDecorator. This decorator adds the prefix MOST_FREQUENT_ and suffix _MOST_FREQUENT to all the occurrences of the most frequently occurring word in the entire input file. Note: The algorithm should be case insensitive. Note: Think of what data structure can be used here to facilitate efficient search of the most frequently occurring word..
o Design a KeywordDecorator. This decorator checks whether any of the words is a keyword. A word is a keyword if it is present in the file provided with the commandline option -Dkeywords. Keywords are prefixed with KEYWORD_ and suffixed with _KEYWORD. Note: The algorithm should be case insensitive.
Note: All decorators update the contents in-place. Hint: In SpellCheckDecorator and KeywordDecorator You can use String#indexOf(...) to search for words to make the program more robust.
• Assume that the input file is well formatted.
• Use loggers and debug values as used in the previous assignments. Each decorator will have its own debug value, that when set will result in that decorator writing the transformations made to a log.txt file. When writing the transformations to log.txt, prefix and suffix the log entry with the name of the decorator. Note that this is in addition to updating InputDetails in-place.
• Decorators are all derived types of AbstractTextDecorator (the name of the type should give enough information regarding its blueprint). AbstractTextDecorator declares a method called processInputDetails() that has no return type and no arguments.
• Decorators are instantiated by passing the decorator to wrap around and the InputDetails reference (the one to process) to the constructor.
• After processing input and applying all the decorators, the updated text in InputDetails should be persisted to the output file, the name of which will be provided via the commandline option -Doutput.
• The driver code should create the decorators, wrapping them as shown in the video. See below driver code snippet to get an understanding of how the decorators are instantiated.
• 		InputDetails inputD = new InputDetails(inputFilename, outputFilename);
• 		AbstractTextDecorator sentenceDecorator = new SentenceDecorator(null, inputD);
• 		AbstractTextDecorator spellCheckDecorator = new SpellCheckDecorator(sentenceDecorator, inputD);
• 		AbstractTextDecorator keywordDecorator = new KeywordDecorator(spellCheckDecorator, inputD);
• 		AbstractTextDecorator mostFreqWordDecorator = new MostFrequentWordDecorator(keywordDecorator, inputD);
• 
• 		mostFrequentWordDecorator.processInputDetails();
• 
• 		((FileDisplayInterface) inputD).writeToFile();
• 
• 		-------------------------------
• 
• 		public abstract class AbstractTextDecorator {
• 			public abstract void processInputDetails();
• 		}
• 
• 		public class SentenceDecorator extends AbstractTextDecorator {
• 			private AbstractTextDecorator atd;
• 			private InputDetails id;
• 
• 			public SentenceDecorator(AbstractTextDecorator atdIn, InputDetails idIn) {
• 				atd = atdIn;
• 				id = idIn;
• 			}
• 
• 			@Override
• 			public void processInputDetails() {
• 				// Decorate input details.
• 				
• 				// Forward to the next decorator, if any.
• 				if (null != atd) {
• 					atd.processInputDetails();
• 				}
• 			}
• 		}
	
• The decorators have to be processed in the following order.
1. MostFrequentWordsDecorator
2. KeywordDecorator
3. SpellCheckDecorator
4. SentenceDecorator
Input/Output
An example input, along with the corresponding output is given below. Input:
The Electors shall meet in their respective States and vote by Ballot for two Persons of whom one at least shall not be an Inhabitant of the same State with themselvs. And they shall make a List of all the Persons voted for and of the Number of Votes for each 3 which List they shall sign and sertify and transmit sealed to the Seat of the Government of the United States 3 directed to the President of the Senate .
Keywords:
government
persons
states
Misspelled Words:
themselvs
sertify
Output:
BEGIN_SENTENCE__MOST_FREQUENT_The_MOST_FREQUENT Electors shall meet in their respective KEYWORD_States_KEYWORD and vote by Ballot for two KEYWORD_Persons_KEYWORD of whom one at least shall not be an Inhabitant of MOST_FREQUENT_the_MOST_FREQUENT same State with SPELLCHECK_themselvs_SPELLCHECK__END_SENTENCE.BEGIN_SENTENCE__ And they shall make a List of all MOST_FREQUENT_the_MOST_FREQUENT KEYWORD_Persons_KEYWORD voted for and of MOST_FREQUENT_the_MOST_FREQUENT Number of Votes for each 3 which List they shall sign and SPELLCHECK_sertify_SPELLCHECK and transmit sealed to MOST_FREQUENT_the_MOST_FREQUENT Seat of MOST_FREQUENT_the_MOST_FREQUENT KEYWORD_Government_KEYWORD of MOST_FREQUENT_the_MOST_FREQUENT United KEYWORD_States_KEYWORD 3 directed to MOST_FREQUENT_the_MOST_FREQUENT President of MOST_FREQUENT_the_MOST_FREQUENT Senate __END_SENTENCE.


## Solution Description:

• Storing the input data: I have used 3 data structures for 3 files repectively.I considered list to store the data one by one.
I considered ArrayList and LinkedList for options to go with List. Finally, I implemented LinkedList, since they are better at manipulation than ArrayList, which
this is a major requirement for this assignment.
All of them are LinkedList.
• How the data is stored: Used String Builder and String
For sentences, since they need to be manipulated multiple times, I have used LinkedList<StringBuilder>, since they are mutable strings.
For keywords and misspelled words, I have used LinkedList<String>, since we do not need to change them at any time.
• Most_FREQ_WORD_DECORATOR algorithm data structure:
For calculation of Most_Freq_Word_Decorator, I have used HashMap<String,Integer> to store the frequency of each word.

• Complexity : 
• HashMaps have time complexity of O(1) for insertion as well as lookup.
• LinkedList offer O(1) time complexity for insert and removal at any position. Random access has worst complexity of O(n). But since, we have to iterate through the whole list for searching the word, there won't be a random access. 
• StringBuilder has O(n) worst case time complexity for insert.


## Academic Honesty statement:

"I have done this assignment completely on my own. I have not copied
it, nor have I given my solution to anyone else. I understand that if
I am involved in plagiarism or cheating an official form will be
submitted to the Academic Honesty Committee of the Watson School to
determine the action that needs to be taken. "

Date: [8/1/2020]


