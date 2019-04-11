import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


/*
 * A Contest to Meet (ACM) is a reality TV contest that sets three contestants at three random
 * city intersections. In order to win, the three contestants need all to meet at any intersection
 * of the city as fast as possible.
 * It should be clear that the contestants may arrive at the intersections at different times, in
 * which case, the first to arrive can wait until the others arrive.
 * From an estimated walking speed for each one of the three contestants, ACM wants to determine the
 * minimum time that a live TV broadcast should last to cover their journey regardless of the contestants’
 * initial positions and the intersection they finally meet. You are hired to help ACM answer this question.
 * You may assume the following:
 *     Each contestant walks at a given estimated speed.
 *     The city is a collection of intersections in which some pairs are connected by one-way
 * streets that the contestants can use to traverse the city.
 *
 * This class implements the competition using Floyd-Warshall algorithm
 */

public class CompetitionFloydWarshall {
	
	//Class variables
	private String filename = "";
	private int sA = 0;
	private int sB = 0;
	private int sC = 0;
	
	//for what the file returns
	private int noOfIntersections = 0;
	private int noOfStreets = 0;
	private ArrayList<String> graphString = new ArrayList<String>();
	
	
	//2D grpah for the thing to work on
	double[][] twoDGraph;
	

    /**
     * @param filename: A filename containing the details of the city road network
     * @param sA, sB, sC: speeds for 3 contestants
     */
    CompetitionFloydWarshall (String filename, int sA, int sB, int sC){

    	//set initialisers
    	this.filename = filename;
    	this.sA = sA;
    	this.sB = sB;
    	this.sC = sC;
    	
    	//read the file (user defined functions below
    	try {
    		parseFile(fileScanner(filename));
    	
	    	//TODO: convert arraylist to just array
	    	twoDGraph = create2DGraph(this.noOfIntersections, this.noOfStreets, graphString);
	    	floydWarshallConstruction(twoDGraph, noOfIntersections);
    	}
    	catch(Exception e) {
    		System.out.print("Constructor failed, invalid input");
    		filename = "Invalid file name";
    	}
    }
    
    
    /*
     * 
     * For TESTING AND STUFF, REMOVE AT END
     */
    /*
    public static void main(String[] args) {
    	
    	CompetitionFloydWarshall tiny = new CompetitionFloydWarshall("tinyEWD.txt", 100, 80, 77);
    	System.out.println(tiny.timeRequiredforCompetition());
    	
    	CompetitionFloydWarshall big = new CompetitionFloydWarshall("1000EWD.txt", 50, 50, 50);
    	System.out.println(big.timeRequiredforCompetition());
    	
    }
    */
    

    /**
     * @return int: minimum minutes that will pass before the three contestants can meet
     */
    public int timeRequiredforCompetition(){
    	//make sure speeds are correct
    	if ((this.sA < 50 || this.sA > 100) || (this.sB <50 || this.sB > 100) || (this.sC < 50 || this.sB > 100)) {
    		return -1;
    	}
    	
    	//get slowest speed
    	int slowestSpeed = 0;
    	if (this.sA < this.sB && this.sA < this.sC) {
    		slowestSpeed = sC;
    	} 
    	else if (this.sB < this.sA && this.sB < this.sC) {
    		slowestSpeed = this.sB;
    	}
    	else {
    		slowestSpeed = this.sC;
    	}
    	
    	//get the shortest distance from the graph
    	double maxDistance = 0;
    	for (double[] array : twoDGraph) {
            for (double dist : array) {
                if (maxDistance < dist)
                    maxDistance = dist;
            }
        }

    	//the distance in kilometers * the speed    	
        return (int) Math.ceil((maxDistance * 1000) / slowestSpeed);
    }
   
    
    
    /*
     * Floyd-Marsall algorithm 
     */
    private void floydWarshallConstruction(double[][] twoDGraph, int noOfIntersections) {
    	
    	//basic implementation of floydWarshall
    	for(int k = 0; k< noOfIntersections; k++) {
    		for(int i = 0; i < noOfIntersections; i++) {
    			for(int j = 0; j < noOfIntersections; j++) {
    				//comparision, see if shorter
    				if (twoDGraph[i][j] > twoDGraph[i][k] + twoDGraph[k][j]) {
    					//change the entry
    					twoDGraph[i][j] = twoDGraph[i][k] + twoDGraph[k][j];
    				}
    			}
    		}
    	}
    	
    }
    
    
    private double[][] create2DGraph(int noOfIntersections, int noOfStreets, ArrayList<String> graphString) {
    	
    	//new graph to populate with data from graphString array
    	double[][] graph = new double[noOfIntersections][noOfIntersections];
    	
    	//init all to -1
    	for (int i = 0; i<noOfIntersections; i++) {
    		for(int j = 0; j<noOfIntersections; j++) {
    			graph[i][j] = Double.POSITIVE_INFINITY;
    		}
    	}
    	//init vertexes to 0
    	for (int i = 0; i < noOfIntersections; i++) {
    		graph[i][i] = 0;
    	}
    	
    	for (int i = 0; (i< graphString.size()); i++) {
    		//scan each line of the graphString and put into array
    		Scanner lineReader = new Scanner(graphString.get(i));
    		int street = lineReader.nextInt();
    		int connectingStreet = lineReader.nextInt();
    		double distance = lineReader.nextDouble();
    		
    		graph[street][connectingStreet] = distance;
    		lineReader.close();
    	}
    	
    	return graph;
    }
    
    
    private void parseFile(Scanner scannedFile) {
    	//this will read the scanned file, and make sense of the passed in file
    	graphString.clear();
    	
    	//read each line of the readin file place into corresponding variables
    	if (scannedFile.hasNextInt()) {
    		this.noOfIntersections = scannedFile.nextInt();
    	}
    	if (scannedFile.hasNextInt()) {
    		this.noOfStreets = scannedFile.nextInt();
    		//skip to the next line to prevent an empty read
    		scannedFile.nextLine();
    	}
    	//read the rest of the file
    	while (scannedFile.hasNextLine()){ 
	   		graphString.add(scannedFile.nextLine());
    	}
    		
    }
    
    
    //method to open file
    private static Scanner fileScanner(String fileName) {
    	try {
    		//use a file scanner and file.io to return the file
    		Scanner fileScan = new Scanner(new File(fileName));
    		return fileScan;
    	}catch (Exception e) {
    		System.out.println("Cannot open file");
    		return null;
    	}
    }

}