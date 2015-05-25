package test;
import model.*;

import org.junit.Test;

import controller.DamaService;
import static org.junit.Assert.*;

public class DamaServiceTest {
	/*@Test
	public void testDownloadStateFromDatabase() {
		DamaService ds = new DamaService();
		Game g = new Game();
		Game g1 = g;
		ds.uploadGameState(g);
		g.setTable(new int[][]  { 
				{ 2, 3, 2, 3, 2, 3, 2, 3 },
				{ 3, 2, 3, 2, 3, 2, 3, 2 },
				{ 2, 3, 2, 3, 2, 3, 2, 3 },
				{ 3, 0, 3, 0, 3, 0, 3, 0 }, 
				{ 0, 3, 0, 3, 0, 3, 0, 3 },
				{ 3, 1, 3, 1, 3, 0, 3, 1 }, 
				{ 1, 3, 1, 3, 1, 3, 1, 3 },
				{ 3, 99999, 3, 0, 3, 1, 3, 1 } });
		ds.downloadGameState(g);
		assertEquals(g1, g);
	}
	
	@Test 
	public void testUploadToDatabase() {
		DamaService ds = new DamaService();
		Game g = new Game();
		Game g1 = g;
		g.setTable(new int[][]  { 
				{ 2, 3, 2, 3, 2, 3, 2, 3 },
				{ 3, 2, 3, 2, 3, 2, 3, 2 },
				{ 2, 3, 2, 3, 0, 3, 2, 3 },
				{ 3, 0, 3, 0, 3, 0, 3, 0 }, 
				{ 0, 3, 0, 3, 0, 3, 0, 3 },
				{ 3, 1, 3, 1, 3, 0, 3, 1 }, 
				{ 0, 3, 1, 3, 0, 3, 1, 3 },
				{ 3, 0, 3, 0, 3, 0, 3, 1 } });
		ds.uploadGameState(g);
		ds.downloadGameState(g1);
//		ds.closeDatabaseConnection();
		assertEquals(g1, g);
	}*/
}
