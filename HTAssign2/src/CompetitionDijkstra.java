import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.PriorityQueue;


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
 * This class implements the competition using Dijkstra's algorithm
 */

public class CompetitionDijkstra {
	
	
	String filename = "";
	int sA = 0;
	int sB = 0;
	int sC = 0;
	int noOfIntersections = 0;
	int noOfStreets = 0;
	
	ArrayList<String> graphString;
	//EdgeWeightedGraph graph;
	double[][] graphM;	

    /**
     * @param filename: A filename containing the details of the city road network
     * @param sA, sB, sC: speeds for 3 contestants
    */
    CompetitionDijkstra (String filename, int sA, int sB, int sC){
    	
    	//set initialisers
    	this.filename = filename;
    	this.sA = sA;
    	this.sB = sB;
    	this.sC = sC;
    	
    	this.noOfIntersections = 0;
    	this.noOfStreets = 0;
    	
    	this.graphString = new ArrayList<String>();

    	
    	//file reading stuff
    	parseFile(fileScanner(filename));
    	
    	//create WeightedGraph
    	//graph = createWeightedGraph();
    	graphM = createGraph();
    	
    	
    	
    }
    
    /*
    public static void main(String[] args) {
    	CompetitionDijkstra lil = new CompetitionDijkstra("tinyEWD.txt", 50, 50 ,50);
    	System.out.print(lil.timeRequiredforCompetition());
    	
    	CompetitionDijkstra big = new CompetitionDijkstra("1000EWD.txt", 50, 50 ,50);
    	System.out.print(big.timeRequiredforCompetition());
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
    	
    	double maxDistance = 0;
    	for (int i = 0; i< this.noOfIntersections; i++) {
    		
    		double maxForInterSection = dijkstra(i);
    		
    		if (maxDistance == -1) {
    			return -1;
    		} else {
    			if (maxDistance < maxForInterSection) {
					maxDistance = maxForInterSection;
				} 
    		}
		
    		
    	}
    	
    	return (int) ((Math.ceil((maxDistance * 1000)/slowestSpeed)));
    	
    }
    
    
    
    
    private double dijkstra(int startingIntersection) {
		
		double currentMaxDist = 0;
		
			
		//initialise dijkstra parameters
		int queueSize = 1;
		double[] dist = new double[this.noOfIntersections];
		boolean[] marked = new boolean[this.noOfIntersections];
		boolean[] reached = new boolean[this.noOfIntersections];
		
		//initiliase all values
		for (int i = 0; i < this.noOfIntersections; i++) {
			dist[i] = Double.POSITIVE_INFINITY;
			marked[i] = false;
			reached[i] = false;
		}
		
		//set values for first node
		dist[startingIntersection] = 0;
		reached[startingIntersection] = true;

		
		do {
			//find shortest distance
			int indexOfShortest = getShortestPath(dist, marked);
			for (int j = 0; j < noOfIntersections; j++) {
				
				//compare
				if (((graphM[indexOfShortest][j] + dist[indexOfShortest]) < dist[j]) && (!marked[j])) {
					dist[j] = (graphM[indexOfShortest][j] + dist[indexOfShortest]);
					queueSize++;
					reached[j] = true;
					
					//find the next longest
					if (dist[j] > currentMaxDist) {
						currentMaxDist = dist[j];
					}
					
				}
			}
			marked[indexOfShortest] = true;
			queueSize--;
		} while (queueSize > 0);
		
		if (currentMaxDist == Double.POSITIVE_INFINITY) {
			return -1;
		}
		
		return currentMaxDist;	
    }
    
    //get the current smallest 
    private int getShortestPath(double[] dist, boolean[] marked) {
		int shortest = 0;
		for (int i = 1; i < dist.length; i++)
			if ((dist[i] < dist[shortest] && !marked[i]) || marked[shortest]) {
				 shortest = i;
			}
		return shortest;
	}

    
    
     
    private double[][] createGraph() {
    	//new graph to populate with data from graphString array
    	double[][] graph = new double[this.noOfIntersections][this.noOfIntersections];
    	
    	//init all to -1
    	for (int i = 0; i<this.noOfIntersections; i++) {
    		for(int j = 0; j<this.noOfIntersections; j++) {
    			graph[i][j] = Double.POSITIVE_INFINITY;
    		}
    	}
    	//init vertexes to 0
    	for (int i = 0; i < this.noOfIntersections; i++) {
    		graph[i][i] = 0;
    	}
    	
    	for (int i = 0; (i< this.graphString.size()); i++) {
    		//scan each line of the graphString and put into array
    		Scanner lineReader = new Scanner(this.graphString.get(i));
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

    
    /*
    private EdgeWeightedGraph createWeightedGraph() {
    	//all required data should be stored in member variables
    	//TODO: make them not stored in member varibales for efficiency
    	
    	EdgeWeightedGraph graph = new EdgeWeightedGraph(this.noOfIntersections, this.noOfStreets);
    	for (int i = 0; i < this.noOfStreets; i++) {
    		
    		//use a scanner to read arrayList of edges
    		//then make the edge type, and add to graph
    		Scanner edgeReader = new Scanner(graphString.get(i));
    		
    		int tail = edgeReader.nextInt();
    		int head = edgeReader.nextInt();
    		double distance = edgeReader.nextDouble();
    		
    		//create edge structure
    		DirectedEdge newEdge = new DirectedEdge(tail, head, distance);
    		System.out.println(newEdge.toString());
    		//add edge structure to graph
    		graph.addEdge(newEdge);
    		
    		edgeReader.close();
    	}
    	//graph is constucted
    	return graph;
    }
    
    //Classes  
   	//all below from text book
    class nodeDist{
    	int nodeNumber;
    	double distance;
    	
    	public nodeDist(int nodeNumber, double distance) {
    		this.nodeNumber = nodeNumber;
    		this.distance = distance;
    	}
    	public boolean isEqual(nodeDist comp) {
    		return (this == comp);
    	}
    }
    
    
    
     //Class for directed Graph to be used
     
    private class EdgeWeightedGraph{
    	
    	private int noOfVertices;
    	private int noOfEdges;
    	private Bag<DirectedEdge>[] adj;	 //adjacency list of vertices
    	private int[] indegree; 			//indegree of vertex i
    	
    	
    	//constructor
    	public EdgeWeightedGraph(int noOfVertices, int noOfEdges) {
    		//TODO: add check greater than 0
    		this.noOfVertices = noOfVertices;
    		this.noOfEdges = noOfEdges;
    		this.indegree = new int[noOfVertices];
    		
    		//create the adjancency list
    		adj = (Bag<DirectedEdge>[]) new Bag[noOfVertices];
    		for (int i = 0; i < noOfVertices; i++) {
    			adj[i] = new Bag<DirectedEdge>();
    		}
    	}
    	
    	//add an edge
    	public void addEdge(DirectedEdge edge) {
    		int tail = edge.tail;
    		int head = edge.head;
    		
    		adj[tail].add(edge);
    		indegree[head]++;
    		
    		//dont think i should be adding this as so
    		//noOfEdges++;
    	}
    	
    	
    	//getters
    	public int getNoOfEdges() {
    		return this.noOfEdges;
    	}
    	public int getNoOfVertices() {
    		return this.noOfVertices;
    	}
    	//number of edges going out of said vertex
    	public int outDegree(int vertice) {
    		return adj[vertice].size();
    	}
    	//number of edges going into said vertex
    	public int inDegree(int vertice) {
    		return this.indegree[vertice];
    	}
    	
    	public String listOfAdjacentVertices(int nodeNumber) {
    		String returnString = "";
    		
    		for (int i = 0; i< adj[nodeNumber].size(); i++) {
    			returnString += adj[nodeNumber].get(i) + ",";
    		}
    		
    		return returnString;
    		
    	}
    	
    }
    
    
    private class Bag<Item> {
    	private Node<Item> first;	//essentially head of list
    	private int n;				//number of items
    	
    	//linked list 
    	private class Node<Item>{
    		private Item item;
    		private Node<Item> next;
    	}
    	
    	//Initialise bag
    	public Bag() {
    		this.first = null;
    		this.n = 0;
    	}
    	
    	public int size() {
			// TODO Auto-generated method stub
			return this.n;
		}

		//check if empty
    	public boolean isEmpty() {
    		return (first == null);
    	}
    	
    	//add an item to the bag
    	public void add(Item item) {
    		//set old head 
    		Node<Item> current = first;
    		first = new Node<Item>();
    		first.item = item;
    		first.next = current;
    		//increase number of items
    		n++;
    	}
    	
    	//get an item from the bag
    	public Item get(int i) {
    		//get the item from the bag at int i
    		if (i<this.size()) {
    			int counter = 0;
    			Node<Item> temp = first;
    			while (counter != i) {
    				temp = temp.next;
    				counter++;
    			}
    			return temp.item;
    		} else {
    			return null;
    		}
    	}
    }
    
    
    
    
    
    //Class for Directed Graph Edges to be used in dikstras algotihm
    public class DirectedEdge {
    	//variables for the edge
    	private final int tail;
    	private final int head;
    	private final double weight;
    	
    	public DirectedEdge(int tail, int head, double weight){
    		//must be positive
    		this.head = head;
			this.tail = tail;
			this.weight = weight;
			
    		if (head < 0 && tail < 0 && weight < 0) {
    			this.head = head;
    			this.tail = tail;
    			this.weight = weight;
    		} else {
    			System.out.println("RIP IT BROKE");
    		}
    		
    	}
    	
    	public String toString() {
    		return (tail + "->" + head + " " + weight);
    	}
    	
    }
    */
}