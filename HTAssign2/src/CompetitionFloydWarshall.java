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
    	parseFile(fileScanner(filename));
    	
    	//TODO: convert arraylist to just array
    	
    	/*
    	for( int i = 0; i < graphString.size(); i++) {
    		System.out.println(graphString.get(i));
    	}
    	*/
    	
    	twoDGraph = create2DGraph(this.noOfIntersections, this.noOfStreets, graphString);
    	
    	/*
    	//test, print out the array
    	for (int i = 0; i < noOfIntersections; i++) {
    		for(int j = 0; j<noOfIntersections; j++) {
    			System.out.print(twoDGraph[i][j] + " ");
    		}
    		System.out.println();
    	}
    	*/
    	
    	
    	floydWarshallConstruction(twoDGraph, noOfIntersections);
    	
    	/*
    	System.out.println();
    	for (int i = 0; i < noOfIntersections; i++) {
    		for(int j = 0; j<noOfIntersections; j++) {
    			System.out.print(twoDGraph[i][j] + " ");
    		}
    		System.out.println();
    	}
    	*/
    	
        //TODO
    }
    
    
    /*
     * 
     * For TESTING AND STUFF, REMOVE AT END
     */
    public static void main(String[] args) {
    	
    	CompetitionFloydWarshall tiny = new CompetitionFloydWarshall("tinyEWD.txt", 1, 1, 1);
    	tiny.timeRequiredforCompetition();
    	
    	CompetitionFloydWarshall big = new CompetitionFloydWarshall("1000EWD.txt", 50, 50, 50);
    	big.timeRequiredforCompetition();
    	
    }
    

    /**
     * @return int: minimum minutes that will pass before the three contestants can meet
     */
    public int timeRequiredforCompetition(){
    	
    	//pick random numbers for sA, sB, sC
    	/*
    	Random random = new Random();
    	this.sA = 50 + random.nextInt(50);
    	this.sB = 50 + random.nextInt(50);
    	this.sC = 50 + random.nextInt(50);
    	*/
    	//just use slowest speed
    	this.sA = 50;
    	this.sB = 50;
    	this.sC = 50;
    	
    	double maxDistance = 0;
    	
    	
    	//for each possible intersection, how long will it take for each to get there from a random spot
    	for (int k = 0; k < this.noOfIntersections; k++) {
    		//the meeting spot
    		for(int x = 0; x< this.noOfIntersections; x++) {
    			//contestant 1 starts at x
    			for(int y = 0; y< this.noOfIntersections; y++) {
    				//contestant 2 starts at y
    				for(int z = 0; z< this.noOfIntersections; z++) {
    				//contestant 3 starts at z
    					if (maxDistance < (this.twoDGraph[x][k] + this.twoDGraph[y][k] + this.twoDGraph[z][k])) {
    						maxDistance = (this.twoDGraph[x][k] + this.twoDGraph[y][k] + this.twoDGraph[z][k]);
    					}
    					
    				}
    			}
    		}
    	}
    	
    	//the distance in kilometers * the speed
    	System.out.println(Math.ceil(maxDistance * 1000) / 50);
    	
        return -1;
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