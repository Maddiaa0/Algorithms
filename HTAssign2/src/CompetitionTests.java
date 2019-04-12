import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CompetitionTests {

    @Test
    public void testDijkstraConstructor() {
    	
    	//Test doesn't fail
    	String filename = "tinyEWD.txt";
		int contestantSpeed = 90;

		CompetitionFloydWarshall floydWarshall = new CompetitionFloydWarshall(filename, contestantSpeed,
				contestantSpeed, contestantSpeed);
		
		//for invalid file 
		int result1 = new CompetitionFloydWarshall("", contestantSpeed,contestantSpeed, contestantSpeed).timeRequiredforCompetition();
    	assertEquals("invalid filename should return -1", result1, -1);
    	
    	int result2 = new CompetitionFloydWarshall(filename, 50 , 50, 50).timeRequiredforCompetition();
    	assertEquals("valid file should return 38", result2, 38);
    	
    }

    @Test
    public void testFWConstructor() {
    	String filename = "tinyEWD.txt";
		int contestantSpeed = 50;

		CompetitionDijkstra dijkstra = new CompetitionDijkstra(filename, contestantSpeed,
				contestantSpeed, contestantSpeed);
		
		//for invalid file 
		int result1 = new CompetitionDijkstra("", contestantSpeed,contestantSpeed, contestantSpeed).timeRequiredforCompetition();
    	assertEquals("invalid filename should return -1", result1, -1);
    	
    	int result2 = new CompetitionDijkstra(filename, 50 , 50, 50).timeRequiredforCompetition();
    	assertEquals("valid file should return 38", result2, 38);
    	
    }

    //TODO - more tests
    
}