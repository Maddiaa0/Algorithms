import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Iterator;


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
 * 
 * 
 * @author Sean Cheetham
 */

public class CompetitionDijkstra {
	
	
	String filename = "";
	int sA = 0;
	int sB = 0;
	int sC = 0;
	int noOfIntersections = 0;
	int noOfStreets = 0;
	boolean validFile = true;
	
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
    	
    	
    	
    }
    
    /*
    public static void main(String[] args) {
    	CompetitionDijkstra lil = new CompetitionDijkstra("tinyEWD.txt", 50, 50 ,50);
    	System.out.print(lil.timeRequiredforCompetition());
    	System.out.println();
    	
    	CompetitionDijkstra big = new CompetitionDijkstra("1000EWD.txt", 50, 50 ,50);
    	System.out.print(big.timeRequiredforCompetition());
    	System.out.println();
    	
    	CompetitionDijkstra A = new CompetitionDijkstra("input-A.txt", 50, 50 ,50);
    	System.out.print(A.timeRequiredforCompetition());
    	System.out.println();
    	
    	//input-K.txt with speed = [76,73,81] should return 220
    	CompetitionDijkstra K = new CompetitionDijkstra("input-K.txt", 76,73,81);
    	System.out.print(K.timeRequiredforCompetition());
    	System.out.println();
    	
    	//input-L.txt with speed = [63,77,95] should return 127
    	CompetitionDijkstra L = new CompetitionDijkstra("input-L.txt", 63,77,95);
    	System.out.print(L.timeRequiredforCompetition());
    	System.out.println();
    	
    	//input-J.txt with speed = [98,70,84] should return -1
    	CompetitionDijkstra J = new CompetitionDijkstra("input-J.txt", 98,70,84);
    	System.out.print(J.timeRequiredforCompetition());
    	System.out.println();
    	
    	//input-D.txt with speed = [50,80,60] should return 38
    	CompetitionDijkstra D = new CompetitionDijkstra("input-D.txt", 50,80,60);
    	System.out.print(D.timeRequiredforCompetition());
    	System.out.println();
		    			
    }
    */
    
    
    
    
    /**
    * @return int: minimum minutes that will pass before the three contestants can meet
     */
    public int timeRequiredforCompetition(){
    	
    	if (!validFile) {
    		return -1;
    	}
    	if (this.noOfIntersections == 0) {
    		return -1;
    	}
    	
    	//create WeightedGraph
    	//graph = createWeightedGraph();
    	graphM = createGraph();
    	
    	double[][] dist = new double[this.noOfIntersections][this.noOfIntersections];
    	
    	//initialise distance list to infinity
    	for (int i = 0; i < this.noOfIntersections; i++) {
    		for(int j = 0; j < this.noOfIntersections; j++) {
    			dist[i][j] = Double.POSITIVE_INFINITY;
    		}
    	}
    	
    	//make sure speeds are correct
    	if ((sA > 100 || sA < 50) || (sB > 100 || sB < 50) || (sC > 100 || sC < 50)) {
    		return -1;
    	}
    	
    	//get slowest speed
    	int slowestSpeed = 0;
    	if (this.sA < this.sB && this.sA < this.sC) {
    		slowestSpeed = sA;
    	} 
    	else if (this.sB < this.sA && this.sB < this.sC) {
    		slowestSpeed = this.sB;
    	}
    	else {
    		slowestSpeed = this.sC;
    	}
    	
    	/*
    	DijkstraAllPairsSP allPairs = new DijkstraAllPairsSP(graph);
    	for (int i = 0; i < this.noOfIntersections; i++) {
    		for(int j = 0; j < this.noOfIntersections; j++) {
    			dist[i][j] = allPairs.dist(i,j);
    		}
    	}
    	*/
    	
    	//get max distance
    	double maxDistance = dijkstra();
    	/*
    	for (int i = 0; i < this.noOfIntersections; i++) {
    		for(int j = 0; j < this.noOfIntersections; j++) {
    			if (dist[i][j] == Double.POSITIVE_INFINITY) {
    				return -1;
    			}
    			if (dist[i][j] > maxDistance) {
    				maxDistance = dist[i][j];
    			}
    		}
    	}
    	*/
    	
    	
    	if (maxDistance == -1) {
    		return -1;
    	}else {
    		return (int) ((Math.ceil((maxDistance * 1000)/slowestSpeed)));
    	}
    }
    
    private double dijkstra() {
		
   	 double currentMaxDist = 0;
        for (int startingIntersection = 0; startingIntersection< this.noOfIntersections; startingIntersection++) {
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
            
            
            while (queueSize > 0){
           	 //find shortest distance
           	 int indexOfShortest = getShortestPath(dist, marked);
           	 for (int j = 0; j < noOfIntersections; j++) {
           
           		 //compare
           		 if (((graphM[indexOfShortest][j] + dist[indexOfShortest]) < dist[j]) && (!marked[j])) {
           			 dist[j] = (graphM[indexOfShortest][j] + dist[indexOfShortest]);
           			 queueSize++;
           			 reached[j] = true;
           		 }
           	 }
           	 
           	 marked[indexOfShortest] = true;
           	 queueSize--;
            } ;
            //check if valid
            
            double max= getHighestValue(dist);
            if (max == Double.POSITIVE_INFINITY) {
           	 return -1;
            }
            if (max > currentMaxDist) {
           	 currentMaxDist = max;
            }	
        }  	
        
        return currentMaxDist;  
       }
       
       private double getHighestValue(double[] dist) {
   		double highest = 0;
   		for (int i = 0; i < dist.length; i++)
   			highest = (dist[i] > highest) ? dist[i] : highest;
   		return highest;
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
    
    
    
    
    
    private void parseFile(Scanner scannedFile) {
    	//this will read the scanned file, and make sense of the passed in file
    	if (validFile) {
    		graphString.clear();
    	
    		try {
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
    		}catch (Exception e) {
    			System.out.print(e);
    		}
    	}	
    }
    
    
    //method to open file
    private Scanner fileScanner(String fileName) {
    	try {
    		//use a file scanner and file.io to return the file
    		Scanner fileScan = new Scanner(new File(fileName));
    		return fileScan;
    	}catch (Exception e) {
    		System.out.println("Cannot open file");
    		validFile = false;
    		return null;
    	}
    }
    

    /*
    private EdgeWeightedGraph createWeightedGraph() {
    	
    	EdgeWeightedGraph returnObject = new EdgeWeightedGraph(this.noOfIntersections);
    	
    	for (int i = 0; i < this.graphString.size(); i++) {
    		
    		Scanner stringScanner = new Scanner(graphString.get(i));
    		
    		int from = stringScanner.nextInt();
    		int to = stringScanner.nextInt();
    		double weight = stringScanner.nextDouble();
    		
    		DirectedEdge addEdge = new DirectedEdge(from, to, weight);
    		returnObject.addEdge(addEdge);
    		stringScanner.close();
    	}	
    	return returnObject;
    	
    }
    */
    
    
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
    
    
   
    /*
    //All code below robbed from textbook
	public class DijkstraSP {
		private double[] distTo; 
		private DirectedEdge[] edgeTo;
		private IndexMinPQ<Double> pq;

		public DijkstraSP(EdgeWeightedGraph G, int s) {

			distTo = new double[G.V()];
			edgeTo = new DirectedEdge[G.V()];

			for (int v = 0; v < G.V(); v++)
				distTo[v] = Double.POSITIVE_INFINITY;
			distTo[s] = 0.0;

			// relax vertices in order of distance from s
			pq = new IndexMinPQ<Double>(G.V());
			pq.insert(s, distTo[s]);
			while (!pq.isEmpty()) {
				int v = pq.delMin();
				for (DirectedEdge e : G.adj(v))
					relax(e);
			}
		}
		// relax edge e and update pq if changed
		private void relax(DirectedEdge e) {
			int v = e.from(), w = e.to();
			if (distTo[w] > distTo[v] + e.weight()) {
				distTo[w] = distTo[v] + e.weight();
				edgeTo[w] = e;
				if (pq.contains(w))
					pq.decreaseKey(w, distTo[w]);
				else
					pq.insert(w, distTo[w]);
			}
		}

		public double distTo(int v) {
			return distTo[v];
		}
	}
	
	
	 public class DijkstraAllPairsSP {
			private DijkstraSP[] all;

			public DijkstraAllPairsSP(EdgeWeightedGraph G) {
				all = new DijkstraSP[G.V()];
				for (int v = 0; v < G.V(); v++)
					all[v] = new DijkstraSP(G, v);
			}

			public double dist(int s, int t) {
				validateVertex(s);
				validateVertex(t);
				return all[s].distTo(t);
			}

		
			private void validateVertex(int v) {
				int V = all.length;
			}
		}


	
	public class EdgeWeightedGraph {
		private final int V;
		private int E;
		private Bag<DirectedEdge>[] adj;

		@SuppressWarnings("unchecked")
		public EdgeWeightedGraph(int V) {
			this.V = V;
			this.E = 0;
			
			adj = (Bag<DirectedEdge>[]) new Bag[V];
			for (int v = 0; v < V; v++) {
				adj[v] = new Bag<DirectedEdge>();
			}
		}

		public int V() {
			return V;
		}

		public void addEdge(DirectedEdge e) {
			int v = e.from();
			adj[v].add(e);
			E++;
		}

		public Iterable<DirectedEdge> adj(int v) {
			return adj[v];
		}

		public Iterable<DirectedEdge> edges() {
			Bag<DirectedEdge> list = new Bag<DirectedEdge>();
			for (int v = 0; v < V; v++) {
				int selfLoops = 0;
				for (DirectedEdge e : adj(v)) {
					if (e.to() > v) {
						list.add(e);
					}
					// add only one copy of each self loop (self loops will be consecutive)
					else if (e.to() == v) {
						if (selfLoops % 2 == 0)
							list.add(e);
						selfLoops++;
					}
				}
			}
			return list;
		}
	}
	
	

	public class Bag<Item> implements Iterable<Item> {
		private Node<Item> first; // beginning of bag
		private int n; // number of elements in bag

		

		public Bag() {
			first = null;
			n = 0;
		}

		public void add(Item item) {
			Node<Item> oldfirst = first;
			first = new Node<Item>();
			first.item = item;
			first.next = oldfirst;
			n++;
		}

		public Iterator<Item> iterator() {
			return new ListIterators<Item>(first);
		}

		
	}
	
	//@SuppressWarnings("hiding")
	private class ListIterators<Item> implements Iterator<Item> {
		private Node<Item> current;

		public ListIterators(Node<Item> first) {
			current = first;
		}

		public boolean hasNext() {
			return current != null;
		}

		public Item next() {
			Item item = current.item;
			current = current.next;
			return item;
		}
	}
	private class Node<Item> {
		private Item item;
		private Node<Item> next;
	}

	public class DirectedEdge {
		private final int v;
		private final int w;
		private final double weight;

		public DirectedEdge(int v, int w, double weight) {
			this.v = v;
			this.w = w;
			this.weight = weight;
		}

		public int from() {
			return v;
		}

		public int to() {
			return w;
		}

		public double weight() {
			return weight;
		}
	}

	public class IndexMinPQ<Key extends Comparable<Key>> implements Iterable<Integer> {
		private int maxN; // maximum number of elements on PQ
		private int n; // number of elements on PQ
		private int[] pq; // binary heap using 1-based indexing
		private int[] qp; // inverse of pq - qp[pq[i]] = pq[qp[i]] = i
		private Key[] keys; // keys[i] = priority of i

		@SuppressWarnings("unchecked")
		public IndexMinPQ(int maxN) {
			this.maxN = maxN;
			n = 0;
			keys = (Key[]) new Comparable[maxN + 1]; // make this of length maxN??
			pq = new int[maxN + 1];
			qp = new int[maxN + 1]; // make this of length maxN??
			for (int i = 0; i <= maxN; i++)
				qp[i] = -1;
		}

		public boolean isEmpty() {
			return n == 0;
		}

		public boolean contains(int i) {
			return qp[i] != -1;
		}

		public void insert(int i, Key key) {
			n++;
			qp[i] = n;
			pq[n] = i;
			keys[i] = key;
			swim(n);
		}

		public int delMin() {
			int min = pq[1];
			exch(1, n--);
			sink(1);
			qp[min] = -1; // delete
			keys[min] = null; // to help with garbage collection
			pq[n + 1] = -1; // not needed
			return min;
		}

		public void decreaseKey(int i, Key key) {
			keys[i] = key;
			swim(qp[i]);
		}

		private boolean greater(int i, int j) {
			return keys[pq[i]].compareTo(keys[pq[j]]) > 0;
		}

		private void exch(int i, int j) {
			int swap = pq[i];
			pq[i] = pq[j];
			pq[j] = swap;
			qp[pq[i]] = i;
			qp[pq[j]] = j;
		}

		private void swim(int k) {
			while (k > 1 && greater(k / 2, k)) {
				exch(k, k / 2);
				k = k / 2;
			}
		}

		private void sink(int k) {
			while (2 * k <= n) {
				int j = 2 * k;
				if (j < n && greater(j, j + 1))
					j++;
				if (!greater(k, j))
					break;
				exch(k, j);
				k = j;
			}
		}

		@Override
		public Iterator<Integer> iterator() {
			return null;
		}
	}
	*/
    
}