CS2010: Data Structure and Algorithms - HT: Assignment 2

Deadline: 12 April 2019, midnight

Submission on TCD Webcat server

A Contest to Meet (ACM) is a reality TV contest that sets three contestants at three random city
intersections. In order to win, the three contestants need all to meet at any intersection of the city as
fast as possible.

The contestants may arrive at the intersections at different times, in which case, the first to arrive
can wait until the others arrive.
From an estimated walking speed for each one of the three contestants, ACM wants to determine the
minimum time that a live TV broadcast should last to cover their journey regardless of the contestants’
initial positions and the intersection they finally meet. You are hired to help ACM answer this question.

You may assume the following:
• Each contestant walks at a given estimated speed.
• The city is a collection of intersections in which some pairs are connected by one-way streets
that the contestants can use to traverse the city. Two intersections can be connected by two
one-way streets allowing travel in opposite directions of each other.

Input:
As input you are given the following parameters:
• A filename (String) containing the details of the road network, as follows:
o Line 1 = integer N representing the total number of intersections
o Line 2 = integer S representing the total number of streets in the city. All streets are
one-way, they connect two intersections, and there is at most one street
connecting any pair of intersections in any one direction (at most two streets, one
in each direction)
o Lines 3 onwards – each line represents a street and consists of 2 integers (2
intersections that the street is connecting) and a double (representing the street
length in kilometers), separated by a space.

A sample input file might look like:
3
2
1 2 0.5
2 3 0.75

Representing a city with 3 intersections and 2 streets, in which the length of the
street leading from intersection 1 to 2 is 0.5km, and the length of the street leading
from intersection 2 to 3 is 0.75.
• Three integers representing the average walking speed of each of the three contestants, in
meters per minute sA, sB, sC where (50 ≤ sA, sB, sC ≤ 100)
Assignment specification
You will solve the above problem using two different shortest path algorithms and discuss the
differences between their implementation and performance.

1. Dijkstra version
Download CompetitionDijkstra.java file.
Write a java class CompetitionDijkstra in CompetitionDijkstra.java file (please do not use custom
packages as web-cat will give an error) which should implement at least the following methods:

• CompetitionDijkstra (String filename, int sA, int sB, int sC) – constructor for this class should
take the four parameters as specified in the input, and create and populate the most
appropriate data structure in which to hold the city road network in this example. Walking
speeds should be stored in member variables.

• public int timeRequiredforCompetition()- this method should return an integer indicating
the minimum number of minutes that will pass before the three contestants can meet in the
city generated in your constructor, if they start to walk immediately after the show starts.
Remember that the contestants can start at any random (unknown) intersection and can
decide to meet at any random unknown intersection: you need to account for the worst case
scenario. The answer should be given rounding decimals to the next integer (e.g., 2.9
minutes rounds up to 3 minutes and 3.2 minutes rounds up to 4 minutes). If it is not possible
to run the given competition in a given city represented by the map you generated (i.e., if 
there are 2 random locations in a city between which no path exists), the method should
return -1. To implement this method you have to use Dijkstra’s shortest path algorithm.


2. Floyd-Warshall version
Write a java class CompetitionFloydWarshall in CompetitionFloydWarshall.java file. The class should
have the exact same functionality and the same Constructor parameters and method signature as
specified in part 1 but use Floyd-Warshall’s algorithm to generate shortest paths.



3. Testing
Write a java class CompetitionTests in CompetitionTests.java file, which should implement JUnit
tests for your CompetitionDijkstra and CompetitionFloydWarshall classes
Your goal is to write enough tests so that:

• Each method in in the classes is tested at least once,

• Each decision (that is, every branch of if-then-else, for, and other kinds of choices) in is
tested at least once,

• Each line of code in all classes is executed at least once from the tests.
The submission server will analyse your tests and code to determine if the above criteria have been
satisfied.
You are given 2 test files to test your implementations with (download from Blackboard).
You also might need to create additional test files yourself, to ensure your code is fully tested and
works for all graph characteristics. Please submit these additional files together with the rest of the
assignment in a zip file. List the names of the additional files you created in the comment at the top
of CompetitionTests file, and for each file explain the characteristics of the graph/which case does
this particular file test.


4. Performance discussion
    Please answer the following questions at the top of CompetitionTests file.
    1. Justify the choice of the data structures used in CompetitionDijkstra and
    CompetitionFloydWarshall
    2. Explain theoretical differences in the performance of Dijkstra and Floyd-Warshall algorithms
    in the given problem. Also explain how would their relative performance be affected by the
    density of the graph. Which would you choose in which set of circumstances and why? 
    For Fun (not marked)
    Implement the same competition using Bellman-Ford algorithm and compare the performance to
    Dijkstra and Floyd-Warshall for different type/size of input graphs. Download a sample file
    containing 1 milion vertices and 15 million edges from https://algs4.cs.princeton.edu/44sp/ and try
    to test the performance. Modify input files to contain negative weights and run the competition. You
    will need to extend the code to first detect negative cycles. Observe what happens to Dijkstra
    version.
