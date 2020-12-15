# CSX42: Assignment 1
## Name: Shubham Patwa

-----------------------------------------------------------------------
-----------------------------------------------------------------------


Following are the commands and the instructions to run ANT on your project.
#### Note: build.xml is present in wordPlay/src folder.

-----------------------------------------------------------------------
## Instruction to clean:

####Command: ant -buildfile wordPlay/src/build.xml clean

Description: It cleans up all the .class files that were generated when you
compiled your code.

-----------------------------------------------------------------------
## Instruction to compile:

####Command: ant -buildfile wordPlay/src/build.xml all

Description: Compiles your code and generates .class files inside the BUILD folder.

-----------------------------------------------------------------------
## Instruction to run:

####Command: ant -buildfile wordPlay/src/build.xml run -Dinput="input.txt" -Doutput="output.txt" -Dmetrics="metrics.txt"

Note: Arguments accept the absolute path of the files.

-----------------------------------------------------------------------
## Problem Statement:
-----------------------------------------------------------------------
Problem Statement: 
Assignment Goal: Develop a program, using Java, to process an input file containing sentences and also to calculate certain metrics.

An input file contains sentences, one per line. Each sentence contains words delimited by < space > character. Each sentence terminates with a period.
Each sentence is made up of alphanumeric words (characters in the set [a-zA-Z0-9]).
The program should process the input file word by word.
The program should do the following.
Rotate each word in a sentence to the right by x places where x is the index of the word in the sentence.
Note: Indexing starts from 1. So first word is rotated by 1 place, second by 2 places and so on.
Note: Only the characters of a word should be rotated. The order of words in the sentence should remain as is.
Note: The rotation should be case senstitive. An upper case character in the input should remain in upper case in the output and lower case character should remain in lower case.
Note: Period characters remain unchanged.
For example, consider the sentence "Welcome to the course.". As it is mentioned that indices start from 1, the index of "Welcome" is 1, "to" is 2, "the" is 3 and "course" is 4.
We therefore need to rotate "Welcome" by 1 position, "to" by 2 positions, "the" by 3 positons and "course" by 4 positions to the right.
After performing rotation, the sentence would now read "eWelcom to the urseco.". This rotated sentence is to be written to the output file.
Calculate the following metrics and write them to the metrics file (one metric per line).

AVG_NUM_WORDS_PER_SENTENCE - Average number of words per sentence. Round to 2 decimal places. Format: AVG_NUM_WORDS_PER_SENTENCE = < value >

AVG_WORD_LENGTH - Average length (number of characters) of a word in the input file. Round to 2 decimal places. Format: AVG_WORD_LENGTH = < value >

-----------------------------------------------------------------------
## Solution Description:
-----------------------------------------------------------------------
 
 The input file is processed one word at a time using the fileprocessor.
 The driver class calls the helper class. The helper class loops through the words, calls the wordRotator class for rotating the word.
 The processed word is then stored to Results class. This is done for each word, one word at a time.
 After all the processing is done, the helper class sends the parameters to the metrics class for calculating the metrics, which are again 
 stored in the Results class.
 After all this, the control is sent back to the driver with the Results class' object that was used for storing the data.
 Then, the driver calls the writeToFile and writeToStdOut methods.

 For storing the processed words, I have used linkedlist. 
 This is because we do not have to provide a specific length for linkedlist before declaring and thus, there is no wastage of
 space. The processed words can be added one by one without worrying about space.
 The time complexity of linkedList:

 add()  -> O(1)

 For getting the elements, if we iterate through the list one by one,which is what is needed, the time complexity will be O(1).

 Space complexity : O(n)


-----------------------------------------------------------------------
### Academic Honesty statement:
-----------------------------------------------------------------------

"I have done this assignment completely on my own. I have not copied
it, nor have I given my solution to anyone else. I understand that if
I am involved in plagiarism or cheating an official form will be
submitted to the Academic Honesty Committee of the Watson School to
determine the action that needs to be taken. "

Date: [6/8/2020]

-----------------------------------------------------------------------
### JAVA Version:
-----------------------------------------------------------------------
openjdk version "11.0.7" 2020-04-14

OpenJDK Runtime Environment AdoptOpenJDK (build 11.0.7+10)
