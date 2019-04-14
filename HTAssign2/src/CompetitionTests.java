import static org.junit.Assert.assertEquals;


/*
 * 1. Justify the choice of the data structures used in CompetitionDijkstra and CompetitionFloydWarshall
 * 
 * in Floyd Warshall the graph was represented with a 2D array.  So takes up N^2 space
 *
 * For CompetitionDijkstra I chose to represent the graph through another 2D array. The more efficient implementation of Dijkstra is to implement
 * it with an Adjacency list, rather than an adjacency matrix, as it eliminates wasted space.
 * I got this form of the algorithm to work in my competition dijkstra file, but I couldn't work out why it wouldn't compile in
 * webcat, despite compling fine in my own enviornment. So I decided an alternative approach which worked, all be it inefficiently.
 *
 *
 *
 * 2. Explain theoretical differences in the performance of Dijkstra and Floyd-Warshall algorithms in the
 * given problem. Also explain how would their relative performance be affected by the density of the graph.
 * Which would you choose in which set of circumstances and why?
 * 
 * Dijkstra computes the distances from one node too all other nodes, giving the distance from one node too all other nodes, with a time
 * complexity of time complexity O(EN^2 ), compared to Floyd Warshalls time complexity of O(N^3), due to its triple nested loops. 
 * Within space graphs, Dijkstra performs better, and uses less memory (when implemented with an adjacency list) compared to 
 * Floyd Warshall. But when densely populated graphs are used for both algorithms, Floyd-Warshall will more likely than 
 * not perform better, as it is very likely that links will exist, and in this scenario, it is actually possible that 
 * Floyd Warshall will take up less space than dijkstra. Floyd Warshall can also compute paths with negative weights, 
 * while dijkstra cannot. 
 * For circumstances where the graph is sparce (the most likely real world scenario) I would use Dijkstra, but for a dense
 * graph, or a graph with negative edges, I would use Floyd Warshall.
 */
 */

import org.junit.Test;

public class CompetitionTests {

    @Test
    public void testFWConstructor() {
    	
    	//Test doesn't fail
    	String filename = "tinyEWD.txt";
		int contestantSpeed = 90;

		CompetitionFloydWarshall floydWarshall = new CompetitionFloydWarshall(filename, contestantSpeed,
				contestantSpeed, contestantSpeed);
		
		//for invalid file 
		int result1 = new CompetitionFloydWarshall("", contestantSpeed,contestantSpeed, contestantSpeed).timeRequiredforCompetition();
    	assertEquals("invalid filename should return -1", result1, -1);
    	
    	int result2 = new CompetitionFloydWarshall(filename, 50 , 60, 70).timeRequiredforCompetition();
    	assertEquals("valid file should return 38", result2, 38);
    	
    	int result3 = new CompetitionFloydWarshall("input-F", 100, 20, 60).timeRequiredforCompetition();
    	assertEquals("not valid", result3, -1);
    	
    	int result4 = new CompetitionFloydWarshall(filename, 100, 20, 60).timeRequiredforCompetition();
    	assertEquals("not valid", result4, -1);
    	
    	int result5 = new CompetitionFloydWarshall(filename, 60, 50, 60).timeRequiredforCompetition();
    	assertEquals("Valid, b as slowest", result5, 38);
    	
    	int result6 = new CompetitionFloydWarshall(filename, 60, 60, 50).timeRequiredforCompetition();
    	assertEquals("valid, c as slowest", result6, 38);
    	
    	int result7 = new CompetitionFloydWarshall("input-J.txt", 60, 60, 50).timeRequiredforCompetition();
    	assertEquals("no intersections, fails", result7, -1);
    	
    }

    @Test
    public void testDijkstraConstructor() {
    	String filename = "tinyEWD.txt";
		int contestantSpeed = 50;

		CompetitionDijkstra dijkstra = new CompetitionDijkstra(filename, contestantSpeed,
				contestantSpeed, contestantSpeed);
		
		//for invalid file 
		int result1 = new CompetitionDijkstra("", contestantSpeed,contestantSpeed, contestantSpeed).timeRequiredforCompetition();
    	assertEquals("invalid filename should return -1", result1, -1);
    	
    	int result2 = new CompetitionDijkstra(filename, 50 , 60, 70).timeRequiredforCompetition();
    	assertEquals("valid file should return 38", result2, 38);
    	
    	int result3 = new CompetitionDijkstra("input-F", 100, 20, 60).timeRequiredforCompetition();
    	assertEquals("not valid", result3, -1);
    	
    	int result4 = new CompetitionDijkstra(filename, 100, 20, 60).timeRequiredforCompetition();
    	assertEquals("not valid", result4, -1);
    	
    	int result5 = new CompetitionDijkstra(filename, 60, 50, 60).timeRequiredforCompetition();
    	assertEquals("Valid, b as slowest", result5, 38);
    	
    	int result6 = new CompetitionDijkstra(filename, 60, 60, 50).timeRequiredforCompetition();
    	assertEquals("valid, c as slowest", result6, 38);
    	
    	int result7 = new CompetitionDijkstra("input-J.txt", 60, 60, 50).timeRequiredforCompetition();
    	assertEquals("no intersections, fails", result7, -1);
    	
    }

    //TODO - more tests
    
}