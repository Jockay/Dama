package model;

import gui.DamaGUI;

import java.util.*;

import javax.print.PrintService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class for game state.
 * 
 * @author jockay
 *
 */
public class Game {
	/** Logger object for logging. */
	private static Logger	logger = LoggerFactory.getLogger(DamaGUI.class);
	/** The game table. */
	private int[][] table;
	/** Is the game over */
	private boolean gameOver;
	/** The first player. */
	private Player a;
	/** The second player. */
	private Player b;
	/*{ 
				{ 1, 3, 1, 3, 1, 3, 1, 3 },
				{ 3, 1, 3, 1, 3, 1, 3, 1 },
				{ 1, 3, 1, 3, 1, 3, 1, 3 },
				{ 3, 0, 3, 0, 3, 0, 3, 0 }, 
				{ 0, 3, 0, 3, 0, 3, 0, 3 },
				{ 3, 2, 3, 2, 3, 2, 3, 2 }, 
				{ 2, 3, 2, 3, 2, 3, 2, 3 },
				{ 3, 2, 3, 2, 3, 2, 3, 2 } }
	 */
	/**
	 * Class constructor.
	 */
	public Game() {
		this.table = new int[][] { 
				{ 2, 3, 2, 3, 2, 3, 2, 3 },
				{ 3, 2, 3, 2, 3, 2, 3, 2 },
				{ 2, 3, 2, 3, 2, 3, 2, 3 },
				{ 3, 0, 3, 0, 3, 0, 3, 0 }, 
				{ 0, 3, 0, 3, 0, 3, 0, 3 },
				{ 3, 1, 3, 1, 3, 1, 3, 1 }, 
				{ 1, 3, 1, 3, 1, 3, 1, 3 },
				{ 3, 1, 3, 1, 3, 1, 3, 1 } };

		a = new Player("Dark Player", true);
		b = new Player("Light Player", false);
	}

	/** 
	 * Sets the game table.
	 * 
	 * @param table table to set as game table
	 */
	public void setTable(int[][] table) {
		this.table = table;
	}
	
	/**
	 * Gets a value of the table.
	 * 
	 * @param coord The coordinate of the value
	 * @return a value from the table specified by the coordinate
	 */
	public int getTableVal(Coordinate coord) {
		return this.table[coord.getX()][coord.getY()];
	}
	
	/**
	 * Sets a value on the game table specified by a coordinate.
	 * 
	 * @param coord The coordinate of the value on the table to set
	 * @param value The value to set
	 */
	public void setTableVal(Coordinate coord, int value) {
		this.table[coord.getX()][coord.getY()] = value;
	}

	/**
	 * Decides if the value on the specified coordinate is a dama.
	 * 
	 * @param coord	the coordinate to check for dama
	 * @return true if the value on the specified coordinate is dama, else returns false
	 */
	public boolean isDama(Coordinate coord) {
		if (getTableVal(coord) == 11 || getTableVal(coord) == 21)
			return true;
		return false;
	}
	
	/**
	 * Decides if the piece on b coordinate could be hit by 
	 * piece on the a coordinate.
	 * 
	 * @param a	the first piece, which wants to hit
	 * @param b the second piece, which is analized to hit
	 * @return true if hit is possible
	 */
	public Coordinate isHitable(Coordinate a, Coordinate b) {
		List<Integer> p1signs = new ArrayList<Integer>(Arrays.asList(1, 11));
		if(!isDama(a)) {  // ha nem dáma
			if(p1signs.contains(getTableVal(a))) { // P1
				if(b.getX() > a.getX() 
						|| b.getX() == 0
						|| b.getX() == 7
						|| b.getY() == 0
						|| b.getY() == 7)
					return null;
				
				if(b.getY() < a.getY() && Math.abs(a.getY() - b.getY()) == 1) {
					if(Math.abs(a.getX() - b.getX()) == 1) {
						Coordinate c = new Coordinate(b.getX() - 1, b.getY() - 1); 
//						System.out.println("Math.abs(a.getX() - b.getX()) == 1");
						if(getTableVal(c) == 0) return c;
						else return null;
					}
						
				} else if(b.getY() > a.getY() && Math.abs(a.getY() - b.getY()) == 1) {
					if(Math.abs(a.getX() - b.getX()) == 1) {
						Coordinate c = new Coordinate(b.getX() - 1, b.getY() + 1);
						if(getTableVal(c) == 0) return c;
						else return null;
					}
				} else if(b.getY() == a.getY() || b.getX() == a.getX()) {
					return null;
				}
			} else { // P2
				if(b.getX() < a.getX() 
						|| b.getX() == 0
						|| b.getX() == 7
						|| b.getY() == 0
						|| b.getY() == 7)
					return null;
				
				if(b.getY() < a.getY()  && Math.abs(a.getY() - b.getY()) == 1) {
					if(Math.abs(a.getX() - b.getX()) == 1) {
						if(b.getX() + 1 < 8) {
							Coordinate c = new Coordinate(b.getX() + 1, b.getY() - 1);
							if(getTableVal(c) == 0) return c;
						}
						else return null;
					}
				} else if(b.getY() > a.getY()  && Math.abs(a.getY() - b.getY()) == 1) {
					if(Math.abs(a.getX() - b.getX()) == 1) {
						Coordinate c = new Coordinate(b.getX() + 1, b.getY() + 1);
						if(getTableVal(c) == 0) return c;
						else return null;
					}
				} else if(b.getY() == a.getY() || b.getX() == a.getX()) {
					return null;
				}
			}
		} else { // ha dáma
			if(b.getX() == 7 
					|| b.getX() == 0
					|| b.getY() == 0
					|| b.getY() == 7)
				return null;
			
			if(p1signs.contains(getTableVal(a))) { // P1
				if(a.getX() < 7 && a.getY() > 0
						&& b.getX() > a.getX() && b.getY() < a.getY()) { // le balra
					int i, j;
					for(i = a.getX() + 1, j = a.getY() - 1; i < 7 && j > 0; i++, j--) {
						if(getTableVal(new Coordinate(i, j)) == 1
								|| getTableVal(new Coordinate(i, j)) == 11)
							break;
						Coordinate c = new Coordinate(i + 1, j - 1);
						if((getTableVal(new Coordinate(i, j)) == 2 
							|| getTableVal(new Coordinate(i, j)) == 21)
								&& getTableVal(c) == 0) {
							return c;
						}
					}
				} 
				if(a.getX() > 0 && a.getY() > 0
						&& b.getY() < a.getY() && b.getX() < a.getX()) { // fel balra
					int i, j;
					for(i = a.getX() - 1, j = a.getY() - 1; i > 0 && j > 0; i--, j--) {
						if(getTableVal(new Coordinate(i, j)) == 1
								|| getTableVal(new Coordinate(i, j)) == 11)
							break;
						Coordinate c = new Coordinate(i - 1, j - 1);
						if((getTableVal(new Coordinate(i, j)) == 2 
							|| getTableVal(new Coordinate(i, j)) == 21) 
								&& getTableVal(c) == 0) {
							return c;
						}
					}
				}
				if(a.getX() > 0 && a.getY() < 7
						&& b.getX() < a.getX() && b.getY() > a.getY()) { // fel jobbra
					int i, j;
					for(i = a.getX() - 1, j = a.getY() + 1; i > 0 && j < 7; i--, j++) {
						if(getTableVal(new Coordinate(i, j)) == 1
								|| getTableVal(new Coordinate(i, j)) == 11)
							break;
						Coordinate c = new Coordinate(i - 1, j + 1);
						if((getTableVal(new Coordinate(i, j)) == 2 
							|| getTableVal(new Coordinate(i, j)) == 21) 
								&& getTableVal(c) == 0) {
							return c;
						}
					}
				} 
				if(a.getY() < 7 && a.getX() < 7
						&& b.getX() > a.getX() && b.getY() > a.getX()) { // le jobbra
					int i, j;
					for(i = a.getX() + 1, j = a.getY() + 1; i < 7 && j < 7; i++, j++) {
						if(getTableVal(new Coordinate(i, j)) == 1
								|| getTableVal(new Coordinate(i, j)) == 11)
							break;
						Coordinate c = new Coordinate(i + 1, j + 1);
						if((getTableVal(new Coordinate(i, j)) == 2 
							|| getTableVal(new Coordinate(i, j)) == 21) 
								&& getTableVal(c) == 0) {
							return c;
						}
					}
				}
				return null;
			} else if(!p1signs.contains(getTableVal(a))) { // P2
				if(a.getX() < 7 && a.getY() > 0
						&& b.getX() > a.getX() && b.getY() < a.getY()) { // tábla alja fele balra
					int i, j;
					for(i = a.getX() + 1, j = a.getY() - 1; i < 7 && j > 0; i++, j--) {
						if(getTableVal(new Coordinate(i, j)) == 2
								|| getTableVal(new Coordinate(i, j)) == 21)
							break;
						Coordinate c = new Coordinate(i + 1, j - 1);
						if((getTableVal(new Coordinate(i, j)) == 1 
							|| getTableVal(new Coordinate(i, j)) == 11) 
							&& getTableVal(c) == 0) {
							return c;
						}
					}
				}
				if(a.getX() > 0 && a.getY() > 0
						&& b.getY() < a.getY() && b.getX() < a.getX()) { // tábla teteje fele balra
					
					int i, j;
					for(i = a.getX() - 1, j = a.getY() - 1; i > 0 && j > 0; i--, j--) {
						if(getTableVal(new Coordinate(i, j)) == 2
								|| getTableVal(new Coordinate(i, j)) == 21)
							break;
						Coordinate c = new Coordinate(i - 1, j - 1);
						if((getTableVal(new Coordinate(i, j)) == 1 
							|| getTableVal(new Coordinate(i, j)) == 11) 
							&& getTableVal(c) == 0) {
							return c;
						}
					}
				}
				if(a.getX() > 0 && a.getY() < 7
						&& b.getX() < a.getX() && b.getY() > a.getY()) { // tábla teteje fele jobbra
					int i, j;
					for(i = a.getX() - 1, j = a.getY() + 1; i > 0 && j < 7; i--, j++) {
						if(getTableVal(new Coordinate(i, j)) == 2
								|| getTableVal(new Coordinate(i, j)) == 21)
							break;
						Coordinate c = new Coordinate(i - 1, j + 1);
						if((getTableVal(new Coordinate(i, j)) == 1 
							|| getTableVal(new Coordinate(i, j)) == 11) 
								&& getTableVal(c) == 0) {
							return c;
						}
					}
				}
				if(a.getY() < 7 && a.getX() < 7
						&& b.getX() > a.getX() && b.getY() > a.getX()) { // tábla alja fele jobbra
					int i, j;
					for(i = a.getX() + 1, j = a.getY() + 1; i < 7 && j < 7; i++, j++) {
						if(getTableVal(new Coordinate(i, j)) == 2
								|| getTableVal(new Coordinate(i, j)) == 21)
							break;
						Coordinate c = new Coordinate(i + 1, j + 1);
						if((getTableVal(new Coordinate(i, j)) == 1 
							|| getTableVal(new Coordinate(i, j)) == 11) 
								&& getTableVal(c) == 0) {
							return c;
						}
					}
				}
				return null;
			}
		}
		return null;
	}
	
	/**
	 * Does a mandatory hit by the actual player.
	 * 
	 * @param ap actal player
	 * @param nap not actual player
	 */
	
	
	/**
	 * Decides wheter a piece can be moved from first position to second.
	 * 
	 * @param ca start position
	 * @param cb goal position
	 * @return true if move is possible, else returns false
	 */
	public boolean isMoveable(Coordinate ca, Coordinate cb) {
		List<Integer> p1signs = new ArrayList<Integer>(Arrays.asList(1, 11));
		List<Integer> p2signs = new ArrayList<Integer>(Arrays.asList(2, 21));
		int a = getTableVal(ca);
		int b = getTableVal(cb);
		boolean p1 = p1signs.contains(a); // Player 1 jelét vizsgáljuk
		boolean p2 = p2signs.contains(a); // Player 2 jelét vizsgáljuk
		
		if(a == 3 || b == 3) { return false; }
		
//		System.out.println(ca + " " + cb);
//		System.out.println("a = " + a);
//		System.out.println("b = " + b);
//		System.out.println("p1signs.contains(a) = " + p1);
//		System.out.println("p2signs.contains(a) = " + p2);
		
		if(!p1 && !p2) {
			return false;
		}
		
		if(!isDama(ca)) {
			if(p1) {
				if(ca.getX() > cb.getX()
						&& b == 0 
						&& ca.getX() - cb.getX() == 1
						&& Math.abs(ca.getY() - cb.getY()) == 1)
					return true;
				else 
					return false;
			}
			if(p2) {
				if(ca.getX() < cb.getX()
						&& b == 0 
						&& cb.getX() - ca.getX() == 1
						&& Math.abs(cb.getY() - ca.getY()) == 1) {
					return true;
				}
				else 
					return false;
			}
		} else {
//			System.out.println("Is dama.");
			if((Math.abs(ca.getX() - cb.getX()) 
				 == Math.abs(ca.getY() - cb.getY())
					&& b == 0) ) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}
	
	/**
	 * Moves piece from first coordinate to second.
	 * 
	 * @param ca start position
	 * @param cb goal position
	 * @return	true if move is possible, else returns false
	 */
	public boolean move(Coordinate ca, Coordinate cb, Player p) {
		List<Integer> p1signs = new ArrayList<Integer>(Arrays.asList(1, 11));
		if(isMoveable(ca, cb)) {
			//System.out.println(p.getPlacedPieces());
			p.getPlacedPieces().remove(ca);
			p.getPlacedPieces().add(cb);
			logger.info(getActualPlayer().getName() + " - move[" + ca + ", " + cb + "]\n");
//			System.out.println(p.getName()+" pieces: ");
//			int i = 0;
//			for (Coordinate c  : p.getPlacedPieces()) {
//				System.out.println(++i + ". " + c);
//			}
//			System.out.println(p.getPlacedPieces());
			setTableVal(cb, getTableVal(ca));
			setTableVal(ca, 0);
			
			if(!isDama(cb))
				turnToDama(cb);
			
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Decides if a (not dama) piece is on a position which on it has to turn a dama.
	 * 
	 * @param c position of the piece
	 * @return true if the piece stands on the last line of the opposite part if the field
	 */
	public boolean isInDamaPosition(Coordinate c) {
		List<Integer> p1signs = new ArrayList<Integer>(Arrays.asList(1, 11));
		if(p1signs.contains(getTableVal(c))) {
			if(c.getX() == 0)
				return true;
		} else {
			if(c.getX() == 7)
				return true;
		}
		return false;
	}
	
	/**
	 * Turns the (not dama) piece to dama.
	 * 
	 * @param coord place of the piece to turn to dama
	 */
	public void turnToDama(Coordinate coord) {
		if(getTableVal(coord) != 11 && getTableVal(coord) != 21 ) {
			if(isInDamaPosition(coord)) {
				if (getTableVal(coord) == 1) {
					setTableVal(coord, 11);
				} else if (getTableVal(coord) == 2) {
					setTableVal(coord, 21);
				}
			}
		}
	}
	
	/**
	 * Switches the players turn.
	 */
	public void switchPlayer() {
		if (this.a.isActual()) {
			this.a.setActual(false);
			this.b.setActual(true);
		} else {
			this.a.setActual(true);
			this.b.setActual(false);
		}
	}
	
	/**
	 * Returns the actual player.
	 * 
	 * @return the actual player
	 */
	public Player getActualPlayer() {
		return this.a.isActual() ? a : b;
	}
	
	/** 
	 * Returns not the actual player.
	 * 
	 * @return not the actual player
	 */
	public Player getNotActualPlayer() {
		return this.a.isActual() ? b : a;
	}
	
	/**
	 * Returns player A (starter).
	 * 
	 * @return starter player (player A)
	 */
	public Player getPlayerA() {
		return this.a;
	}
	
	/** 
	 * Returns player B.
	 * 
	 * @return player B (the second player)
	 */
	public Player getPlayerB() {
		return this.b;
	}

	/**
	 * Sets the game state to start state.
	 */
	public void startNewGame() {
		this.table = new int[][] { 
				{ 2, 3, 2, 3, 2, 3, 2, 3 },
				{ 3, 2, 3, 2, 3, 2, 3, 2 },
				{ 2, 3, 2, 3, 2, 3, 2, 3 },
				{ 3, 0, 3, 0, 3, 0, 3, 0 }, 
				{ 0, 3, 0, 3, 0, 3, 0, 3 },
				{ 3, 1, 3, 1, 3, 1, 3, 1 }, 
				{ 1, 3, 1, 3, 1, 3, 1, 3 },
				{ 3, 1, 3, 1, 3, 1, 3, 1 } };
		a = new Player("Dark Player", true);
		b = new Player("Light Player", false);
		setGameOver(false);
	}

	/**
	 * Returns true if the game is over.
	 * 
	 * @return true if the game is over.
	 */
	public boolean isGameOver() {	
		return this.gameOver == true ? true : false;
	}
	
	/**
	 * Sets the gameOver attribute true.
	 */
	public void gameOver() {
		this.gameOver = true;
	}
	
	/**
	 * Sets the gameOver attribute.
	 * 
	 * @param over boolean value to set
	 */
	public void setGameOver(boolean over) {
		this.gameOver = over;
	}
	
	/**
	 * Returns the game table (2D array).
	 * 
	 * @return the game table (2D array)
	 */
	public int[][] getTable() {
		return this.table;
	}
	
	/**
	 * Clears the table from peaces.
	 */
	public void clearTable() {
		setTable(new int[][] { 
				{ 0, 3, 0, 3, 0, 3, 0, 3 },
				{ 3, 0, 3, 0, 3, 0, 3, 0 },
				{ 0, 3, 0, 3, 0, 3, 0, 3 },
				{ 3, 0, 3, 0, 3, 0, 3, 0 }, 
				{ 0, 3, 0, 3, 0, 3, 0, 3 },
				{ 3, 0, 3, 0, 3, 0, 3, 0 }, 
				{ 0, 3, 0, 3, 0, 3, 0, 3 },
				{ 3, 0, 3, 0, 3, 0, 3, 0 } });
	}
	
	/**
	 * Prints the game table.
	 */
	public void pgt() {
		for (int i = 0; i < table.length; i++) {
			System.out.print("{ ");
			for (int j = 0; j < table.length; j++) {
				if(j == table.length - 1)
					System.out.print(table[i][j] + " ");
				else
					System.out.print(table[i][j] + ", ");
			}
			if(i == table.length - 1)
				System.out.println("}");
			else 
				System.out.println("},");
		}
	}
}
