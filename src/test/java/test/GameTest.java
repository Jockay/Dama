package test;

import java.util.Arrays;

import model.*;

import org.junit.Test;

import static org.junit.Assert.*;

public class GameTest {
	int[][] table = null;
	
	int[][] newGameTable = new int[][]  { 
			{ 2, 3, 2, 3, 2, 3, 2, 3 },
			{ 3, 2, 3, 2, 3, 2, 3, 2 },
			{ 2, 3, 2, 3, 2, 3, 2, 3 },
			{ 3, 0, 3, 0, 3, 0, 3, 0 }, 
			{ 0, 3, 0, 3, 0, 3, 0, 3 },
			{ 3, 1, 3, 1, 3, 1, 3, 1 }, 
			{ 1, 3, 1, 3, 1, 3, 1, 3 },
			{ 3, 1, 3, 1, 3, 1, 3, 1 } };
	
	int[][] testTable1 = new int[][] { 
			{ 2, 3, 2, 3, 2, 3, 2, 3 },
			{ 3, 2, 3, 2, 3, 2, 3, 2 },
			{ 2, 3, 0, 3, 2, 3, 2, 3 },
			{ 3, 2, 3, 0, 3, 0, 3, 0 }, 
			{ 0, 3, 0, 3, 1, 3, 0, 3 },
			{ 3, 1, 3, 0, 3, 1, 3, 1 }, 
			{ 1, 3, 1, 3, 1, 3, 1, 3 },
			{ 3, 1, 3, 1, 3, 1, 3, 1 } };
	
	int[][] testDamaTable = new int[][] { 
			{ 21, 3, 2, 3, 2, 3, 2, 3 },
			{ 3, 2, 3, 2, 3, 2, 3, 2 },
			{ 2, 3, 2, 3, 2, 3, 2, 3 },
			{ 3, 0, 3, 0, 3, 0, 3, 0 }, 
			{ 0, 3, 0, 3, 0, 3, 0, 3 },
			{ 3, 1, 3, 1, 3, 1, 3, 11 }, 
			{ 1, 3, 1, 3, 1, 3, 1, 3 },
			{ 3, 1, 3, 1, 3, 1, 3, 1 } };
	
	@Test
	public void testIsDama() {
		Game game = new Game();
		game.setTable(newGameTable);
		assertEquals(false, game.isDama(new Coordinate(0, 0)));
		assertEquals(false, game.isDama(new Coordinate(0, 1)));
		
		game.setTable(testDamaTable);
		assertEquals(true, game.isDama(new Coordinate(0, 0)));
		assertEquals(true, game.isDama(new Coordinate(5, 7)));
	}
	
	@Test
	public void testIsGameOver() {
		Game game = new Game();
		assertEquals(false, game.isGameOver());
		game.gameOver();
		assertEquals(true, game.isGameOver());
		game.setGameOver(false);
	}
	
	@Test
	public void testGameOver() {
		Game game = new Game();
		game.gameOver();
		assertEquals(true, game.isGameOver());
		game.setGameOver(false);
	}
	
	@Test
	public void testStartNewGame() {
		Game game = new Game();
		int[][] testTable = new int[][] { 
				{ 2, 3, 2, 3, 2, 3, 2, 3 },
				{ 3, 2, 3, 2, 3, 2, 3, 2 },
				{ 2, 3, 2, 3, 2, 3, 2, 3 },
				{ 3, 0, 3, 0, 3, 0, 3, 0 }, 
				{ 0, 3, 1, 3, 0, 3, 0, 3 },
				{ 3, 0, 3, 1, 3, 1, 3, 1 }, 
				{ 1, 3, 1, 3, 1, 3, 1, 3 },
				{ 3, 1, 3, 1, 3, 1, 3, 1 } };
		game.setTable(testTable);
		game.switchPlayer();
		
		
		game.startNewGame();
		int[][] newGameTable = new int[][] { 
				{ 2, 3, 2, 3, 2, 3, 2, 3 },
				{ 3, 2, 3, 2, 3, 2, 3, 2 },
				{ 2, 3, 2, 3, 2, 3, 2, 3 },
				{ 3, 0, 3, 0, 3, 0, 3, 0 }, 
				{ 0, 3, 0, 3, 0, 3, 0, 3 },
				{ 3, 1, 3, 1, 3, 1, 3, 1 }, 
				{ 1, 3, 1, 3, 1, 3, 1, 3 },
				{ 3, 1, 3, 1, 3, 1, 3, 1 } };
		assertEquals(newGameTable, game.getTable());
	}
	
	@Test
	public void testSetTable() {
		Game game = new Game();
		int[][] testTable = new int[][] { 
				{ 1, 3, 1, 3, 1, 3, 1, 3 },
				{ 3, 1, 3, 1, 3, 1, 3, 1 },
				{ 1, 3, 0, 3, 1, 3, 1, 3 },
				{ 3, 1, 3, 0, 3, 0, 3, 0 }, 
				{ 0, 3, 0, 3, 2, 3, 0, 3 },
				{ 3, 2, 3, 0, 3, 2, 3, 2 }, 
				{ 2, 3, 2, 3, 2, 3, 2, 3 },
				{ 3, 2, 3, 2, 3, 2, 3, 2 }
		};
		game.setTable(testTable);
		assertEquals(testTable, game.getTable());
	}
	
	@Test 
	public void testGetTableVal() {
		Game game1 = new Game();
		game1.setTable(testTable1);
		
		Game game2 = new Game();
		game2.setTable(testTable1);
		
		for (int i = 0; i < game1.getTable().length; i++)
			for (int j = 0; j < game1.getTable().length; j++)
				assertEquals(game1.getTableVal(new Coordinate(i, j)),
							 game2.getTableVal(new Coordinate(i, j)));
	}
	
	@Test
	public void testSetTableVal() {
		Game game1 = new Game();
		game1.setTable(testTable1);
		
		Game game2 = new Game();
		
		for (int i = 0; i < game1.getTable().length; i++)
			for (int j = 0; j < game1.getTable().length; j++)
				game2.setTableVal(new Coordinate(i, j), 
						game1.getTableVal(new Coordinate(i, j)));
		
		assertEquals(game1.getTable(), game2.getTable());		
	}
	
	@Test
	public void testIsHitable() {
		Game g = new Game();
		g.setTable(new int[][] {
				{ 2, 3, 0, 3, 0, 3, 0, 3 },
				{ 3, 1, 3, 0, 3, 0, 3, 0 },
				{ 0, 3, 0, 3, 0, 3, 0, 3 },
				{ 3, 0, 3, 0, 3, 0, 3, 0 },
				{ 0, 3, 0, 3, 0, 3, 0, 3 },
				{ 3, 0, 3, 0, 3, 0, 3, 0 },
				{ 0, 3, 0, 3, 0, 3, 0, 3 },
				{ 3, 0, 3, 0, 3, 0, 3, 0 } });
		assertEquals(null, g.isHitable(new Coordinate(1, 1), new Coordinate(0, 0)));
		
		g.setTable(new int[][] {
				{ 0, 3, 0, 3, 0, 3, 0, 3 },
				{ 3, 0, 3, 0, 3, 0, 3, 0 },
				{ 0, 3, 0, 3, 0, 3, 0, 3 },
				{ 3, 0, 3, 0, 3, 0, 3, 0 },
				{ 0, 3, 0, 3, 0, 3, 0, 3 },
				{ 3, 0, 3, 0, 3, 0, 3, 0 },
				{ 0, 3, 0, 3, 0, 3, 2, 3 },
				{ 3, 0, 3, 0, 3, 0, 3, 1 } });
		assertEquals(null, g.isHitable(new Coordinate(1, 1), new Coordinate(0, 0)));
		
		g.setTable(new int[][] {
				{ 0, 3, 0, 3, 0, 3, 0, 3 },
				{ 3, 2, 3, 0, 3, 0, 3, 0 },
				{ 0, 3, 1, 3, 0, 3, 0, 3 },
				{ 3, 0, 3, 0, 3, 0, 3, 0 },
				{ 0, 3, 0, 3, 0, 3, 0, 3 },
				{ 3, 0, 3, 0, 3, 0, 3, 0 },
				{ 0, 3, 0, 3, 0, 3, 0, 3 },
				{ 3, 0, 3, 0, 3, 0, 3, 0 } });
		assertEquals(new Coordinate(0, 0), 
				g.isHitable(new Coordinate(2, 2), new Coordinate(1, 1)));
		
		g.setTable(new int[][] {
				{ 0, 3, 0, 3, 0, 3, 0, 3 },
				{ 3, 0, 3, 2, 3, 0, 3, 0 },
				{ 0, 3, 1, 3, 0, 3, 0, 3 },
				{ 3, 0, 3, 0, 3, 0, 3, 0 },
				{ 0, 3, 0, 3, 0, 3, 0, 3 },
				{ 3, 0, 3, 0, 3, 0, 3, 0 },
				{ 0, 3, 0, 3, 0, 3, 0, 3 },
				{ 3, 0, 3, 0, 3, 0, 3, 0 } });
		assertEquals(new Coordinate(0, 4), 
				g.isHitable(new Coordinate(2, 2), new Coordinate(1, 3)));
		
		g.setTable(new int[][] {
			   // 0  1  2  3  4  5  6  7 
				{ 0, 3, 0, 3, 0, 3, 0, 3 }, // 0
				{ 3, 0, 3, 0, 3, 0, 3, 0 }, // 1
				{ 0, 3, 0, 3, 0, 3, 0, 3 }, // 2
				{ 3, 0, 3, 0, 3, 0, 3, 0 }, // 3
				{ 0, 3, 0, 3, 0, 3, 0, 3 }, // 4
				{ 3, 0, 3, 2, 3, 0, 3, 0 }, // 5
				{ 0, 3, 0, 3, 1, 3, 0, 3 }, // 6
				{ 3, 0, 3, 0, 3, 0, 3, 0 }  // 7
		});  
		assertEquals(new Coordinate(7, 5), 
				g.isHitable(new Coordinate(5, 3), new Coordinate(6, 4)));
		
		g.setTable(new int[][] {
				   // 0  1  2  3  4  5  6  7 
					{ 0, 3, 0, 3, 0, 3, 0, 3 }, // 0
					{ 3, 0, 3, 0, 3, 0, 3, 0 }, // 1
					{ 0, 3, 0, 3, 0, 3, 0, 3 }, // 2
					{ 3, 0, 3, 0, 3, 0, 3, 0 }, // 3
					{ 0, 3, 0, 3, 0, 3, 0, 3 }, // 4
					{ 3, 0, 3, 2, 3, 0, 3, 0 }, // 5
					{ 0, 3, 1, 3, 0, 3, 0, 3 }, // 6
					{ 3, 0, 3, 0, 3, 0, 3, 0 }  // 7
			});  
		assertEquals(new Coordinate(7, 1), 
				g.isHitable(new Coordinate(5, 3), new Coordinate(6, 2)));
		
		g.setTable(new int[][] {
				{ 2, 3, 0, 3, 0, 3, 0, 3 },
				{ 3, 2, 3, 0, 3, 0, 3, 0 },
				{ 0, 3, 1, 3, 0, 3, 0, 3 },
				{ 3, 0, 3, 0, 3, 0, 3, 0 },
				{ 0, 3, 0, 3, 0, 3, 0, 3 },
				{ 3, 0, 3, 0, 3, 0, 3, 0 },
				{ 0, 3, 0, 3, 0, 3, 0, 3 },
				{ 3, 0, 3, 0, 3, 0, 3, 0 } });
		assertEquals(null, 
				g.isHitable(new Coordinate(2, 2), new Coordinate(1, 1)));
			
		g.setTable(new int[][] {
				{ 0, 3, 0, 3, 2, 3, 0, 3 },
				{ 3, 0, 3, 2, 3, 0, 3, 0 },
				{ 0, 3, 1, 3, 0, 3, 0, 3 },
				{ 3, 0, 3, 0, 3, 0, 3, 0 },
				{ 0, 3, 0, 3, 0, 3, 0, 3 },
				{ 3, 0, 3, 0, 3, 0, 3, 0 },
				{ 0, 3, 0, 3, 0, 3, 0, 3 },
				{ 3, 0, 3, 0, 3, 0, 3, 0 } });
		assertEquals(null, 
				g.isHitable(new Coordinate(2, 2), new Coordinate(1, 3)));
		
		g.setTable(new int[][] {
				   // 0  1  2  3  4  5  6  7 
					{ 0, 3, 0, 3, 0, 3, 0, 3 }, // 0
					{ 3, 0, 3, 0, 3, 0, 3, 0 }, // 1
					{ 0, 3, 0, 3, 0, 3, 0, 3 }, // 2
					{ 3, 0, 3, 0, 3, 0, 3, 0 }, // 3
					{ 0, 3, 0, 3, 0, 3, 0, 3 }, // 4
					{ 3, 0, 3, 2, 3, 0, 3, 0 }, // 5
					{ 0, 3, 0, 3, 1, 3, 0, 3 }, // 6
					{ 3, 0, 3, 0, 3, 1, 3, 0 }  // 7
			});
		assertEquals(null, 
				g.isHitable(new Coordinate(5, 3), new Coordinate(6, 4)));
		
		g.setTable(new int[][] {
				   // 0  1  2  3  4  5  6  7 
					{ 0, 3, 0, 3, 0, 3, 0, 3 }, // 0
					{ 3, 0, 3, 0, 3, 0, 3, 0 }, // 1
					{ 0, 3, 0, 3, 0, 3, 0, 3 }, // 2
					{ 3, 0, 3, 0, 3, 0, 3, 0 }, // 3
					{ 0, 3, 0, 3, 0, 3, 2, 3 }, // 4
					{ 3, 0, 3, 0, 3, 0, 3, 1 }, // 5
					{ 0, 3, 0, 3, 0, 3, 0, 3 }, // 6
					{ 3, 0, 3, 0, 3, 0, 3, 0 }  // 7
			});
		assertEquals(null, 
				g.isHitable(new Coordinate(4, 6), new Coordinate(5, 7)));
		
		g.setTable(new int[][] {
				   // 0  1  2  3  4  5  6  7 
					{ 0, 3, 0, 3, 0, 3, 0, 3 }, // 0
					{ 3, 0, 3, 0, 3, 0, 3, 0 }, // 1
					{ 0, 3, 0, 3, 0, 3, 2, 3 }, // 2
					{ 3, 0, 3, 0, 3, 2, 3, 0 }, // 3
					{ 0, 3, 0, 3, 1, 3, 0, 3 }, // 4
					{ 3, 0, 3, 0, 3, 0, 3, 0 }, // 5
					{ 0, 3, 0, 3, 0, 3, 0, 3 }, // 6
					{ 3, 0, 3, 0, 3, 0, 3, 0 }  // 7
			});
		assertEquals(null, 
				g.isHitable(new Coordinate(4, 4), new Coordinate(3, 5)));
		
		g.setTable(new int[][] {
				   // 0  1  2  3  4  5  6  7 
					{ 0, 3, 0, 3, 0, 3, 0, 3 }, // 0
					{ 3, 0, 3, 0, 3, 0, 3, 0 }, // 1
					{ 0, 3, 0, 3, 0, 3, 2, 3 }, // 2
					{ 3, 0, 3, 0, 3, 2, 3, 0 }, // 3
					{ 0, 3, 0, 3, 1, 3, 2, 3 }, // 4
					{ 3, 0, 3, 0, 3, 0, 3, 0 }, // 5
					{ 1, 3, 0, 3, 0, 3, 0, 3 }, // 6
					{ 3, 0, 3, 0, 3, 0, 3, 0 }  // 7
			});
		assertEquals(null, 
				g.isHitable(new Coordinate(6, 0), new Coordinate(4, 6)));
		
		g.setTable(new int[][] {
				    { 2, 3, 2, 3, 2, 3, 2, 3  },
					{ 3, 2, 3, 2, 3, 2, 3, 2  },
					{ 2, 3, 2, 3, 1, 3, 2, 3  },
					{ 3, 0, 3, 0, 3, 0, 3, 0  },
					{ 0, 3, 0, 3, 0, 3, 0, 3  },
					{ 3, 1, 3, 1, 3, 0, 3, 1  },
					{ 1, 3, 1, 3, 1, 3, 1, 3  },
					{ 3, 1, 3, 1, 3, 1, 3, 1  }
			});
		assertEquals(new Coordinate(3, 5), 
				g.isHitable(new Coordinate(1, 3), new Coordinate(2, 4)));
		
		// alja fele jobbra
		g.setTable(new int[][] {
			    { 11, 3, 0, 3, 0, 3, 0, 3  },
				{ 3, 0, 3, 0, 3, 0, 3, 0  },
				{ 2, 3, 0, 3, 0, 3, 0, 3  },
				{ 3, 0, 3, 0, 3, 0, 3, 0  },
				{ 0, 3, 0, 3, 0, 3, 0, 3  },
				{ 3, 0, 3, 0, 3, 0, 3, 0  },
				{ 0, 3, 0, 3, 0, 3, 2, 3  },
				{ 3, 0, 3, 0, 3, 0, 3, 0  }
		});
		assertEquals(new Coordinate(7, 7), 
			g.isHitable(new Coordinate(0, 0), new Coordinate(6, 6)));
		
		g.setTable(new int[][] {
			    { 11, 3, 0, 3, 0, 3, 0, 3  },
				{ 3, 0, 3, 0, 3, 0, 3, 0  },
				{ 2, 3, 2, 3, 0, 3, 0, 3  },
				{ 3, 0, 3, 0, 3, 0, 3, 0  },
				{ 0, 3, 0, 3, 0, 3, 0, 3  },
				{ 3, 0, 3, 0, 3, 0, 3, 0  },
				{ 0, 3, 0, 3, 0, 3, 2, 3  },
				{ 3, 0, 3, 0, 3, 0, 3, 0  }
		});
		assertEquals(new Coordinate(3, 3), 
			g.isHitable(new Coordinate(0, 0), new Coordinate(2, 2)));
		
		g.setTable(new int[][] {
			    { 2, 3, 2, 3, 0, 3, 0, 3  },
				{ 3, 1, 3, 0, 3, 0, 3, 0  },
				{ 2, 3, 2, 3, 0, 3, 0, 3  },
				{ 3, 0, 3, 0, 3, 0, 3, 0  },
				{ 0, 3, 0, 3, 0, 3, 0, 3  },
				{ 3, 0, 3, 0, 3, 0, 3, 0  },
				{ 0, 3, 0, 3, 0, 3, 2, 3  },
				{ 3, 0, 3, 0, 3, 0, 3, 0  }
		});
		assertEquals(null,
			g.isHitable(new Coordinate(1, 1), new Coordinate(0, 0)));
		
		// DÁMA
		// alja fele jobbra
		g.setTable(new int[][] {
			    { 11, 3, 0, 3, 0, 3, 0, 3  },
				{ 3, 0, 3, 0, 3, 0, 3, 0  },
				{ 2, 3, 0, 3, 0, 3, 0, 3  },
				{ 3, 0, 3, 0, 3, 0, 3, 0  },
				{ 0, 3, 0, 3, 0, 3, 0, 3  },
				{ 3, 0, 3, 0, 3, 0, 3, 0  },
				{ 0, 3, 0, 3, 0, 3, 0, 3  },
				{ 3, 0, 3, 0, 3, 0, 3, 2  }
		});
		assertEquals(null, 
			g.isHitable(new Coordinate(0, 0), new Coordinate(7, 7)));
		
		// alja fele balra
		g.setTable(new int[][] {
			    { 0, 3, 0, 3, 0, 3, 11, 3  },
				{ 3, 0, 3, 0, 3, 0, 3, 0  },
				{ 2, 3, 0, 3, 2, 3, 0, 3  },
				{ 3, 0, 3, 0, 3, 0, 3, 0  },
				{ 0, 3, 0, 3, 0, 3, 0, 3  },
				{ 3, 2, 3, 0, 3, 0, 3, 0  },
				{ 0, 3, 0, 3, 0, 3, 0, 3  },
				{ 3, 0, 3, 0, 3, 0, 3, 0  }
		});
		assertEquals(new Coordinate(3, 3), 
			g.isHitable(new Coordinate(0, 6), new Coordinate(2, 4)));
		
		// alja fele balra
		g.setTable(new int[][] {
			    { 0, 3, 0, 3, 0, 3, 11, 3  },
				{ 3, 0, 3, 0, 3, 0, 3, 0  },
				{ 2, 3, 0, 3, 0, 3, 0, 3  },
				{ 3, 0, 3, 0, 3, 0, 3, 0  },
				{ 0, 3, 0, 3, 0, 3, 0, 3  },
				{ 3, 2, 3, 0, 3, 0, 3, 0  },
				{ 0, 3, 0, 3, 0, 3, 0, 3  },
				{ 3, 0, 3, 0, 3, 0, 3, 0  }
		});
		assertEquals(new Coordinate(6, 0), 
			g.isHitable(new Coordinate(0, 6), new Coordinate(5, 1)));
		
		g.setTable(new int[][] {
			    { 0, 3, 0, 3, 0, 3, 0, 3  },
				{ 3, 0, 3, 0, 3, 0, 3, 0  },
				{ 2, 3, 2, 3, 0, 3, 0, 3  },
				{ 3, 0, 3, 0, 3, 0, 3, 0  },
				{ 0, 3, 0, 3, 0, 3, 0, 3  },
				{ 3, 0, 3, 0, 3, 0, 3, 0  },
				{ 0, 3, 0, 3, 2, 3, 0, 3  },
				{ 3, 0, 3, 1, 3, 1, 3, 0  }
		});
		assertEquals(null,
			g.isHitable(new Coordinate(6, 4), new Coordinate(7, 3)));
		
		// teteje fele jobbra
		g.setTable(new int[][] {
				   // 0  1  2  3  4  5  6  7 
					{ 0, 3, 0, 3, 0, 3, 0, 3 }, // 0
					{ 3, 0, 3, 0, 3, 0, 3, 0 }, // 1
					{ 0, 3, 0, 3, 0, 3, 2, 3 }, // 2
					{ 3, 0, 3, 0, 3, 0, 3, 0 }, // 3
					{ 0, 3, 0, 3, 0, 3, 0, 3 }, // 4
					{ 3, 0, 3, 0, 3, 0, 3, 0 }, // 5
					{ 0, 3, 0, 3, 0, 3, 0, 3 }, // 6
					{ 3, 11, 3, 0, 3, 0, 3, 0 }  // 7
			});
		assertEquals(new Coordinate(1, 7), 
			g.isHitable(new Coordinate(7, 1), new Coordinate(2, 6)));
		
		// teteje fele balra
		g.setTable(new int[][] {
				   // 0  1  2  3  4  5  6  7 
					{ 0, 3, 0, 3, 0, 3, 0, 3 }, // 0
					{ 3, 2, 3, 0, 3, 0, 3, 11 }, // 1
					{ 0, 3, 0, 3, 0, 3, 0, 3 }, // 2
					{ 3, 0, 3, 0, 3, 0, 3, 0 }, // 3
					{ 0, 3, 0, 3, 0, 3, 0, 3 }, // 4
					{ 3, 0, 3, 0, 3, 0, 3, 0 }, // 5
					{ 0, 3, 0, 3, 0, 3, 0, 3 }, // 6
					{ 3, 0, 3, 0, 3, 0, 3, 11 }  // 7
			});
		assertEquals(new Coordinate(0, 0), 
			g.isHitable(new Coordinate(7, 7), new Coordinate(1, 1)));
		
		//P2
		// alja fele jobbra
				g.setTable(new int[][] {
					    { 21, 3, 0, 3, 0, 3, 0, 3  },
						{ 3, 0, 3, 0, 3, 0, 3, 0  },
						{ 2, 3, 0, 3, 0, 3, 0, 3  },
						{ 3, 0, 3, 0, 3, 0, 3, 0  },
						{ 0, 3, 0, 3, 0, 3, 0, 3  },
						{ 3, 0, 3, 0, 3, 0, 3, 0  },
						{ 0, 3, 0, 3, 0, 3, 1, 3  },
						{ 3, 0, 3, 0, 3, 0, 3, 0  }
				});
				assertEquals(new Coordinate(7, 7), 
					g.isHitable(new Coordinate(0, 0), new Coordinate(6, 6)));
				
				// alja fele jobbra
				g.setTable(new int[][] {
					    { 21, 3, 0, 3, 0, 3, 0, 3  },
						{ 3, 0, 3, 0, 3, 0, 3, 0  },
						{ 2, 3, 0, 3, 0, 3, 0, 3  },
						{ 3, 0, 3, 0, 3, 0, 3, 0  },
						{ 0, 3, 0, 3, 0, 3, 0, 3  },
						{ 3, 0, 3, 0, 3, 0, 3, 0  },
						{ 0, 3, 0, 3, 0, 3, 0, 3  },
						{ 3, 0, 3, 0, 3, 0, 3, 1  }
				});
				assertEquals(null, 
					g.isHitable(new Coordinate(0, 0), new Coordinate(7, 7)));
				
				// alja fele balra
				g.setTable(new int[][] {
					    { 0, 3, 0, 3, 0, 3, 21, 3  },
						{ 3, 0, 3, 0, 3, 0, 3, 0  },
						{ 2, 3, 0, 3, 1, 3, 0, 3  },
						{ 3, 0, 3, 0, 3, 0, 3, 0  },
						{ 0, 3, 0, 3, 0, 3, 0, 3  },
						{ 3, 1, 3, 0, 3, 0, 3, 0  },
						{ 0, 3, 0, 3, 0, 3, 0, 3  },
						{ 3, 0, 3, 0, 3, 0, 3, 0  }
				});
				assertEquals(new Coordinate(3, 3), 
					g.isHitable(new Coordinate(0, 6), new Coordinate(2, 4)));
				
				// alja fele balra
				g.setTable(new int[][] {
					    { 0, 3, 0, 3, 0, 3, 21, 3  },
						{ 3, 0, 3, 0, 3, 0, 3, 0  },
						{ 2, 3, 0, 3, 0, 3, 0, 3  },
						{ 3, 0, 3, 0, 3, 0, 3, 0  },
						{ 0, 3, 0, 3, 0, 3, 0, 3  },
						{ 3, 1, 3, 0, 3, 0, 3, 0  },
						{ 0, 3, 0, 3, 0, 3, 0, 3  },
						{ 3, 0, 3, 0, 3, 0, 3, 0  }
				});
				assertEquals(new Coordinate(6, 0), 
					g.isHitable(new Coordinate(0, 6), new Coordinate(5, 1)));
				
				// teteje fele jobbra
				g.setTable(new int[][] {
						   // 0  1  2  3  4  5  6  7 
							{ 0, 3, 0, 3, 0, 3, 0, 3 }, // 0
							{ 3, 0, 3, 0, 3, 0, 3, 0 }, // 1
							{ 0, 3, 0, 3, 0, 3, 1, 3 }, // 2
							{ 3, 0, 3, 0, 3, 0, 3, 0 }, // 3
							{ 0, 3, 0, 3, 0, 3, 0, 3 }, // 4
							{ 3, 0, 3, 0, 3, 0, 3, 0 }, // 5
							{ 0, 3, 0, 3, 0, 3, 0, 3 }, // 6
							{ 3, 21, 3, 0, 3, 0, 3, 0 }  // 7
					});
				assertEquals(new Coordinate(1, 7), 
					g.isHitable(new Coordinate(7, 1), new Coordinate(2, 6)));
				
				// teteje fele balra
				g.setTable(new int[][] {
						   // 0  1  2  3  4  5  6  7 
							{ 0, 3, 0, 3, 0, 3, 0, 3 }, // 0
							{ 3, 1, 3, 0, 3, 0, 3, 21 }, // 1
							{ 0, 3, 0, 3, 0, 3, 0, 3 }, // 2
							{ 3, 0, 3, 0, 3, 0, 3, 0 }, // 3
							{ 0, 3, 0, 3, 0, 3, 0, 3 }, // 4
							{ 3, 0, 3, 0, 3, 0, 3, 0 }, // 5
							{ 0, 3, 0, 3, 0, 3, 0, 3 }, // 6
							{ 3, 0, 3, 0, 3, 0, 3, 21 }  // 7
					});
				assertEquals(new Coordinate(0, 0), 
					g.isHitable(new Coordinate(7, 7), new Coordinate(1, 1)));
				
				g.setTable(new int[][] {
						   // 0  1  2  3  4  5  6  7 
							{ 0, 3, 11, 3, 0, 3, 0, 3 }, // 0
							{ 3, 2, 3, 0, 3, 0, 3, 21 }, // 1
							{ 2, 3, 0, 3, 2, 3, 0, 3 }, // 2
							{ 3, 0, 3, 0, 3, 0, 3, 0 }, // 3
							{ 0, 3, 0, 3, 0, 3, 0, 3 }, // 4
							{ 3, 0, 3, 0, 3, 0, 3, 0 }, // 5
							{ 0, 3, 0, 3, 0, 3, 0, 3 }, // 6
							{ 3, 0, 3, 0, 3, 0, 3, 21 }  // 7
					});
				assertEquals(new Coordinate(3, 5), 
					g.isHitable(new Coordinate(0, 2), new Coordinate(1, 1)));
				
				g.setTable(new int[][] {
						   // 0  1  2  3  4  5  6  7 
							{ 0, 3, 11, 3, 0, 3, 0, 3 }, // 0
							{ 3, 2, 3, 0, 3, 2, 3, 21 }, // 1
							{ 2, 3, 0, 3, 0, 3, 0, 3 }, // 2
							{ 3, 0, 3, 0, 3, 0, 3, 0 }, // 3
							{ 0, 3, 0, 3, 0, 3, 0, 3 }, // 4
							{ 3, 0, 3, 0, 3, 0, 3, 0 }, // 5
							{ 0, 3, 0, 3, 0, 3, 0, 3 }, // 6
							{ 3, 0, 3, 0, 3, 0, 3, 21 }  // 7
					});
				assertEquals(null, 
					g.isHitable(new Coordinate(0, 2), new Coordinate(1, 5)));
				
				g.setTable(new int[][] {
						   // 0  1  2  3  4  5  6  7 
							{ 0, 3, 11, 3, 0, 3, 0, 3 }, // 0
							{ 3, 2, 3, 0, 3, 2, 3, 21 }, // 1
							{ 2, 3, 0, 3, 0, 3, 0, 3 }, // 2
							{ 3, 0, 3, 0, 3, 2, 3, 0 }, // 3
							{ 0, 3, 0, 3, 0, 3, 0, 3 }, // 4
							{ 3, 0, 3, 0, 3, 0, 3, 0 }, // 5
							{ 0, 3, 0, 3, 0, 3, 0, 3 }, // 6
							{ 3, 0, 3, 0, 3, 0, 3, 21 }  // 7
					});
				assertEquals(new Coordinate(4, 6), 
					g.isHitable(new Coordinate(0, 2), new Coordinate(3, 5)));
				
				g.setTable(new int[][] {
						   // 0  1  2  3  4  5  6  7 
							{ 0, 3, 11, 3, 0, 3, 0, 3 }, // 0
							{ 3, 2, 3, 2, 3, 2, 3, 21 }, // 1
							{ 0, 3, 0, 3, 0, 3, 0, 3 }, // 2
							{ 3, 2, 3, 0, 3, 2, 3, 0 }, // 3
							{ 0, 3, 0, 3, 0, 3, 0, 3 }, // 4
							{ 3, 0, 3, 0, 3, 0, 3, 0 }, // 5
							{ 0, 3, 0, 3, 0, 3, 0, 3 }, // 6
							{ 3, 0, 3, 0, 3, 11, 3, 0 }  // 7
					});
				assertEquals(new Coordinate(3, 1), 
					g.isHitable(new Coordinate(7, 5), new Coordinate(3, 1)));
	}
	
	
	
	/*@Test
	public void testDoHit() {
		Game g = new Game();
		
		g.setTable(new int[][] {
				{ 0, 3, 0, 3, 0, 3, 0, 3 },
				{ 3, 2, 3, 0, 3, 0, 3, 0 },
				{ 0, 3, 1, 3, 0, 3, 0, 3 },
				{ 3, 0, 3, 0, 3, 0, 3, 0 },
				{ 0, 3, 0, 3, 0, 3, 0, 3 },
				{ 3, 0, 3, 0, 3, 0, 3, 0 },
				{ 0, 3, 0, 3, 0, 3, 0, 3 },
				{ 3, 0, 3, 0, 3, 0, 3, 0 } });
		g.getPlayerA().setActual(true);
		g.getPlayerB().setActual(false);
		g.getPlayerA().getPlacedPieces().clear();
		g.getPlayerB().getPlacedPieces().clear();
		g.getPlayerA().setPlacedPieces(Arrays.asList(new Coordinate(2, 2)));
		g.getPlayerB().setPlacedPieces(Arrays.asList(new Coordinate(1, 1)));
		assertEquals(true, g.doHit());
		assertEquals(1, g.getTableVal(new Coordinate(0, 0)));
		assertEquals(0, g.getTableVal(new Coordinate(1, 1)));
		assertEquals(0, g.getTableVal(new Coordinate(2, 2)));
	}*/
	
	@Test
	public void testIsMoveable() {
		Game g = new Game();
		g.setTable(new int[][] { 
		   // 0  1  2  3  4  5  6  7 
			{ 0, 3, 0, 3, 0, 3, 0, 3 }, // 0
			{ 3, 0, 3, 2, 3, 0, 3, 0 }, // 1
			{ 0, 3, 0, 3, 0, 3, 0, 3 }, // 2
			{ 3, 0, 3, 2, 3, 0, 3, 0 }, // 3
			{ 1, 3, 1, 3, 0, 3, 0, 3 }, // 4
			{ 3, 1, 3, 0, 3, 1, 3, 0 }, // 5
			{ 0, 3, 0, 3, 0, 3, 0, 3 }, // 6
			{ 3, 0, 3, 0, 3, 0, 3, 1 }  // 7
		});
		
		// Player 1
		assertEquals(true,  g.isMoveable(new Coordinate(7, 7), new Coordinate(6, 6))); // 1, 0
		assertEquals(true,  g.isMoveable(new Coordinate(5, 5), new Coordinate(4, 6))); // 1, 0 jobb fel
		assertEquals(true,  g.isMoveable(new Coordinate(5, 5), new Coordinate(4, 4))); // 1, 0 bal fel
		assertEquals(false, g.isMoveable(new Coordinate(7, 7), new Coordinate(4, 5))); // 1, 3 érvénytelen
		assertEquals(false, g.isMoveable(new Coordinate(6, 5), new Coordinate(6, 6))); // 3, 1 vissza
		assertEquals(false, g.isMoveable(new Coordinate(5, 5), new Coordinate(0, 0))); // 1, 0 messze
		assertEquals(false, g.isMoveable(new Coordinate(5, 1), new Coordinate(4, 2))); // 1, 1 jobb fel
		assertEquals(false, g.isMoveable(new Coordinate(5, 1), new Coordinate(4, 0))); // 1, 1 bal fel
		// Player 2
		assertEquals(true,  g.isMoveable(new Coordinate(1, 3), new Coordinate(2, 4))); // 2, 0 jobb le
		assertEquals(true,  g.isMoveable(new Coordinate(1, 3), new Coordinate(2, 2))); // 2, 0 bal le
		assertEquals(false, g.isMoveable(new Coordinate(1, 3), new Coordinate(0, 2))); // 2, 0 vissza
		assertEquals(false, g.isMoveable(new Coordinate(6, 5), new Coordinate(6, 6)));
		assertEquals(false, g.isMoveable(new Coordinate(1, 3), new Coordinate(0, 2)));
		assertEquals(false, g.isMoveable(new Coordinate(3, 3), new Coordinate(0, 0))); // 2, 0 vissza
		
		g.setTable(new int[][] { 
				   // 0  1  2  3   4  5   6  7 
					{ 0, 3, 0, 3,  0, 3,  0, 3 }, // 0
					{ 3, 0, 3, 21, 3, 0,  3, 0 }, // 1
					{ 0, 3, 0, 3,  0, 3,  0, 3 }, // 2
					{ 3, 0, 3, 2,  3, 0,  3, 0 }, // 3
					{ 0, 3, 0, 3,  0, 3,  0, 3 }, // 4
					{ 3, 0, 3, 0,  3, 11, 3, 0 }, // 5
					{ 0, 3, 0, 3,  0, 3,  0, 3 }, // 6
					{ 3, 0, 3, 0,  3, 0,  3, 11 }  // 7
				});
		assertEquals(true,  g.isMoveable(new Coordinate(1, 3), new Coordinate(5, 7)));
		assertEquals(false, g.isMoveable(new Coordinate(1, 3), new Coordinate(3, 7)));
		assertEquals(true,  g.isMoveable(new Coordinate(5, 5), new Coordinate(3, 7)));
		assertEquals(false, g.isMoveable(new Coordinate(5, 5), new Coordinate(5, 3)));
	}
	
	@Test
	public void testTurnToDama() {
		Game g = new Game();
		g.setTable(new int[][]  { 
				{ 0, 3, 0, 3, 2, 3, 2, 3 },
				{ 3, 1, 3, 0, 3, 0, 3, 2 },
				{ 0, 3, 0, 3, 0, 3, 0, 3 },
				{ 3, 0, 3, 0, 3, 0, 3, 0 }, 
				{ 0, 3, 0, 3, 0, 3, 0, 3 },
				{ 3, 1, 3, 1, 3, 1, 3, 1 }, 
				{ 1, 3, 1, 3, 1, 3, 1, 3 },
				{ 3, 1, 3, 1, 3, 1, 3, 1 } });
		g.move(new Coordinate(1, 1), new Coordinate(0, 0), g.getActualPlayer());
		g.turnToDama(new Coordinate(0, 0));
		assertEquals(11, g.getTableVal(new Coordinate(0, 0)));
		
		g.setTable(new int[][]  { 
				{ 0, 3, 0, 3, 2, 3, 2, 3 },
				{ 3, 1, 3, 0, 3, 0, 3, 2 },
				{ 0, 3, 0, 3, 0, 3, 0, 3 },
				{ 3, 0, 3, 0, 3, 0, 3, 0 }, 
				{ 0, 3, 0, 3, 0, 3, 0, 3 },
				{ 3, 1, 3, 1, 3, 0, 3, 0 }, 
				{ 1, 3, 1, 3, 1, 3, 2, 3 },
				{ 3, 1, 3, 1, 3, 0, 3, 0 } });
		g.move(new Coordinate(6, 6), new Coordinate(7, 7), g.getActualPlayer());
		g.turnToDama(new Coordinate(7, 7));
		assertEquals(21, g.getTableVal(new Coordinate(7, 7)));
	}
	
	@Test
	public void testMove() {
		Game g = new Game();
		g.setTable(new int[][]  {
			   // 0  1  2  3  4  5  6  7 
				{ 2, 3, 2, 3, 2, 3, 2, 3 }, // 0
				{ 3, 2, 3, 2, 3, 2, 3, 2 }, // 1
				{ 2, 3, 2, 3, 2, 3, 2, 3 }, // 2
				{ 3, 0, 3, 0, 3, 0, 3, 0 }, // 3
				{ 0, 3, 0, 3, 0, 3, 0, 3 }, // 4
				{ 3, 1, 3, 1, 3, 1, 3, 1 }, // 5
				{ 1, 3, 1, 3, 1, 3, 1, 3 }, // 6
				{ 3, 1, 3, 1, 3, 1, 3, 1 } }); // 7
		assertEquals(true, g.move(new Coordinate(5, 5), new Coordinate(4, 6), g.getActualPlayer()));
		assertEquals(0, g.getTableVal(new Coordinate(5, 5)));
		assertEquals(1, g.getTableVal(new Coordinate(4, 6)));
		
		g.setTable(new int[][]  {
				   // 0  1  2  3  4  5  6  7 
					{ 2, 3, 2, 3, 2, 3, 2, 3 }, // 0
					{ 3, 2, 3, 2, 3, 2, 3, 2 }, // 1
					{ 2, 3, 2, 3, 2, 3, 2, 3 }, // 2
					{ 3, 0, 3, 0, 3, 0, 3, 0 }, // 3
					{ 0, 3, 0, 3, 0, 3, 0, 3 }, // 4
					{ 3, 1, 3, 1, 3, 1, 3, 1 }, // 5
					{ 1, 3, 1, 3, 1, 3, 1, 3 }, // 6
					{ 3, 1, 3, 1, 3, 1, 3, 1 } }); // 7
		assertEquals(false, g.move(new Coordinate(5, 5), new Coordinate(3, 3), g.getActualPlayer()));
		assertEquals(1, g.getTableVal(new Coordinate(5, 5)));
		assertEquals(0, g.getTableVal(new Coordinate(3, 3)));
	}
	
	@Test
	public void testSwitchPlayer() {
		Game game = new Game();
		
		assertEquals(true, game.getPlayerA().isActual());
		assertEquals(false,  game.getPlayerB().isActual());
		game.switchPlayer();
		assertEquals(false, game.getPlayerA().isActual());
		assertEquals(true,  game.getPlayerB().isActual());
		game.switchPlayer();
		assertEquals(true,  game.getPlayerA().isActual());
		assertEquals(false, game.getPlayerB().isActual());
	}
	
	@Test
	public void testGetActualPlayer() {
		Game game = new Game();
		
		assertEquals(game.getPlayerA(), game.getActualPlayer());
		
		game.getPlayerA().setActual(false);
		game.getPlayerB().setActual(true);
		assertEquals(game.getPlayerB(), game.getActualPlayer());
		
		game.getPlayerA().setActual(true);
		game.getPlayerB().setActual(false);
		assertEquals(game.getPlayerA(), game.getActualPlayer());
	}
	
	@Test
	public void testGetNotActualPlayer() {
		Game game = new Game();
		
		assertEquals(game.getPlayerB(), game.getNotActualPlayer());
		
		game.getPlayerA().setActual(false);
		game.getPlayerB().setActual(true);
		assertEquals(game.getPlayerA(), game.getNotActualPlayer());
		
		game.getPlayerA().setActual(true);
		game.getPlayerB().setActual(false);
		assertEquals(game.getPlayerB(), game.getNotActualPlayer());
	}
	
	
}
