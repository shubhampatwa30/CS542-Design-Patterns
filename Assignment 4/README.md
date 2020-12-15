# CSX42: Assignment 4
**Name: Shubham Patwa**

-----------------------------------------------------------------------

Following are the commands and the instructions to run ANT on your project.


Note: build.xml is present in [arrayvisitors/src](./arrayvisitors/src/) folder.

## Instruction to clean:

```commandline
ant -buildfile arrayvisitors/src/build.xml clean
```

Description: It cleans up all the .class files that were generated when you
compiled your code.

## Instructions to compile:

```commandline
ant -buildfile arrayvisitors/src/build.xml all
```
The above command compiles your code and generates .class files inside the BUILD folder.

## Instructions to run:

```commandline
ant -buildfile arrayvisitors/src/build.xml run -Dinput1="input1.txt" -Dinput2="input2.txt" -Dcommonintsout="coutfile.txt" -Dmissingintsout1="moutfile1.txt" -Dmissingintsout2="moutfile2.txt" -Ddebug="0"
```
Program accepts 5 arguments

    First input file containing 2 digits integers per line :  -Dinput1 commandline option.

    Second input file containing 2 digits integers per line:  -Dinput2 commandline option.

    Output file to store results of applying CommonIntsVisitor : -Dcommonintsout commandline option.

    Output file to store results of applying MissingIntsVisitor1: -Dmissingintsout1 commandline option.

    Output file to store results of applying MissingIntsVisitor2: -Dmissingintsout2 commandline option.

    Debug value: -Ddebug commandline option.

Debug options :

    case 4: debugLevel = DebugLevel.MISSING_INTS_VISITOR

    case 3: debugLevel = DebugLevel.COMMON_INTS_VISITOR

    case 2: debugLevel = DebugLevel.POPULATE_MYARRAY_VISITOR

    case 1: debugLevel = DebugLevel.CONSTRUCTOR

    default: debugLevel = DebugLevel.NONE


## Description:

Project Description 

Program with visitors to determine features in two input files that have integers.  

• The input for the program consists of two separate input files, both with a positive 2 digit integer in each line. Assume that the input may contain duplicates in the individual files.
• Create an interface MyArrayI that defines an API for an Abstract Data Type (ADT) representing an array. In addition, MyArrayI is an Element.
• Define an ADT, MyArray, that implements MyArrayI and stores an internal array of integers. The internal array should be created with an initial capacity of 10 and increased by 50% in capacity each time an integer has to be added beyond the current capacity.
• Each MyArray object will store the integers from a single input file.
• Design a visitor, PopulateMyArrayVisitor, that reads from a file and populates MyArray one integer at a time. The visitor should use an instance of the FileProcessor to read from the input file one line at a time (one integer at a time). Check for the boundary condition that the input file does not exist or is empty. Throw an exception and exit if a string from the file cannot be converted to an integer. Other than that, assume the input file is well formed and no other exceptions need to be handled.
• The PopulateMyArrayVisitor can take the name of the input file in its constructor or have a setX(..) method for it. You need to apply this visitor once for each of the two input files to get two instances of MyArray that are populated with integers.
• Create an interface MyArrayListI that defines an API for a ADT representing an arraylist. In addition, MyArrayListI is an Element.
• Define an ADT, MyArrayList, that implements MyArrayListI and maintains an internal array of the MyArray objects.
• Design a visitor, CommonIntsVisitor, that determines the integers that are common in the two ADTs stored in MyArrayList, and stores those integers (without duplicates) in an appropriate data structure in Results.
• Design a visitor, MissingIntsVisitor that determines the 2 digit integers (between 00 and 99) that are missing in MyArray and stores them in an appropriate data structure in Results.
• The output files should contain a single integer per line.
• Use a singleton Logger and design your own debugging scheme.
• The driver should accept the input file names, output file names, and debug value, via the command line.
1. -Dinput1 - First input file containing 2 digits integers per line.
2. -Dinput2 - Second input file containing 2 digits integers per line.
3. -Dcommonintsout - Output file to store results of applying CommonIntsVisitor.
4. -Dmissingintsout1 - Output file to store results of applying MissingIntsVisitor1(first instance of MyArray)
5. -Dmissingintsout5 - Output file to store results of applying MissingIntsVisitor2(Second instance of MyArray)
6. -Ddebug - Debug value.
• The driver should do the following:
o Create required instances of Results.
o Create instance of FileProcessor.
o Create instances of the visitors.
o Create two instances of MyArray.
o Use the PopulateMyArrayVisitor to populate two instances of MyArray.
o Apply CommonIntsVisitor to determine common ints in ADTs stored in MyArrayList.
o Apply MissingIntsVisitor separately to each of the MyArray instances.
o Call appropriate methods in Result instances to print the output of each of the visitors.
• Helpers:
0. There is only a single visitor interface.
1. Unlike the traditional viistor pattern where there is just a single visit(...) method in the Visitor interface, you will need to overload the method here for each of the two visitors.
2. When calling methods on the ADTs/Elements make sure to cast to the appropriate interface and then call the method. For example, ADT methods should be called by casting to the interface that defines the API for the ADT.
3. No business logic should be written in the driver code.
4. ADT design
• Empty constructor.
• Explicit value constructor.
• Getters and Setters.
• Override toString.
• Empty finalize method.
• Override Clone.
5. Driver code
• Driver code should not include business logic.
• Simple and concise.

## Justification of Data Structures:
ADT have data structures as arrays and arrayList as a requirement of this assignment.
However, I have used sets to calculate the common Integers and missing Integers.

1) TreeSet is used so that the integers are already sorted in ascending order.

1) Sets do not have duplicate values. Hence, no fuss over checking duplicate values.

2) To find common elements in sets, we can use retainAll function . It has complexity O(n).

3) To find missing elements, I have used 'contains' method which has complexity of O(1). Hence, for finding all the missing elements, the time complexity will be O(n).

4) Sets have space complexity of O(n).

## Academic Honesty statement:

"I have done this assignment completely on my own. I have not copied
it, nor have I given my solution to anyone else. I understand that if
I am involved in plagiarism or cheating an official form will be
submitted to the Academic Honesty Committee of the Watson School to
determine the action that needs to be taken. "

Date: [7/16/2020]


