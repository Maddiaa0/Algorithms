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
    	
    }

    @Test
    public void testFWConstructor() {
    	String filename = "tinyEWD.txt";
		int contestantSpeed = 50;

		CompetitionDijkstra dijkstra = new CompetitionDijkstra(filename, contestantSpeed,
				contestantSpeed, contestantSpeed);
    }

    //TODO - more tests
    
}