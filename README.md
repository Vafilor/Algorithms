# Algorithms
Just me playing around with algorithms and the like.


#Genetic Algorithm for N-Queens Problem

To Compile
----------------
1. Open up a terminal/command line and navigation to src directory. 
2. Enter the command
  1. Windows: javac com\vafilor\algorithms\genetic\\*.java 
  2. Linuxy: javac com/vafilor/algorithms/genetic/*.java 
	

To Run
-----------------
1. Open up a terminal/command line and navigation to src directory. 
2. Enter the command:  **java com.vafilor.algorithms.genetic.Main 8 8**
	
* The first argument is the number of queens
* The second argument is the starting population, that is, how many states do you want to have in your "mating" pool.

Results
-----------------
The result is a string where the row position of each queen for each column is given.

e.g.

|&nbsp;|Columns|
|------|-------|
|&nbsp;|0 1 2 3|
|Row   |1 3 0 2|

Means the following arrangement, where the first row is 0 and the first column is 0, and x represents a queen.

|&nbsp;|0|1|2| 3|
|---|---|---|---|---|  
|0| | |X| |   
|1|X| | | |  
|2| | | |X|  
|3| |X| | |  


Comments
-------------
A genetic algorithm is probably not a good choice for the n-queens problem. I highly recommend you don't try to do more than 10 queens, unless you like waiting.

#Search Problems

To Compile
-----------------------
1. Open up a terminal/command line and navigation to src directory. 
2. Enter the command
  1. Windows: javac com\vafilor\search\*.java 
  2. Linuxy: javac com/vafilor/search/*.java 

To Run
-----------------
1. Open up a terminal/command line and navigation to src directory. 
2. Enter the command:  **java com.vafilor.search.ProblemSolver InputFiles/test1.txt
		
* The argument is the sample n-problem to solve in a textfile.
* The puzzle is expected to be solvable with the solution having the "blank" or 0 in the top left.

Results
------------------
This will output an estimate of the time taken to solve the problem and the action sequence to solve the problem in the 
provided input file.


