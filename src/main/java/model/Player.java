package model;

import java.util.*;

/**
 * Class for a player.
 * 
 * @author jockay
 *
 */
public class Player {
	/** Name of the player. */
	private String name;
	/** Color of the player. */
	private String color;
	/** Decides if the player is actual. */
	private boolean actual;
	/** Decides if the player won. */
	private boolean won;
	/** Decides if the player is in mandatory hit. */
	private boolean inMandatoryHit;
	/** The list of possibla player signs. */
	private List<Integer> signs;
	/** The list of the placed pieces by the player. */
	private List<Coordinate> placedPieces;
	
	/** 
	 * Class constructor.
	 * 
	 * @param name player name
	 * @param starter decides if the player is starter
	 */
	public Player(String name, boolean starter) {
		this.name = name;
		this.won = false;
		this.actual = starter;
		/*new int[][] { 
		 * 	   // 0  1  2  3  4  5  6  7
				{ 2, 3, 2, 3, 2, 3, 2, 3 }, // 0
				{ 3, 2, 3, 2, 3, 2, 3, 2 }, // 1
				{ 2, 3, 2, 3, 2, 3, 2, 3 }, // 2
				{ 3, 0, 3, 0, 3, 0, 3, 0 }, // 3
				{ 0, 3, 0, 3, 0, 3, 0, 3 }, // 4
				{ 3, 1, 3, 1, 3, 1, 3, 1 }, // 5 
				{ 1, 3, 1, 3, 1, 3, 1, 3 }, // 6
				{ 3, 1, 3, 1, 3, 1, 3, 1 } }*/ // 7 
		if(starter) {
			this.placedPieces = new ArrayList<Coordinate>(Arrays.asList(
					new Coordinate(6, 0), 
					new Coordinate(5, 1), new Coordinate(7, 1),
					new Coordinate(6, 2),
					new Coordinate(5, 3), new Coordinate(7, 3),
					new Coordinate(6, 4),
					new Coordinate(5, 5), new Coordinate(7, 5),
					new Coordinate(6, 6),
					new Coordinate(5, 7), new Coordinate(7, 7)
					));
			this.signs = new ArrayList<Integer>(Arrays.asList(1, 11));
			this.color = "light";
		} else {
			this.placedPieces = new ArrayList<Coordinate>(Arrays.asList(
					new Coordinate(0, 0), new Coordinate(2, 0),
					new Coordinate(1, 1),
					new Coordinate(0, 2), new Coordinate(2, 2),
					new Coordinate(1, 3), 
					new Coordinate(0, 4), new Coordinate(2, 4),
					new Coordinate(1, 5),
					new Coordinate(0, 6), new Coordinate(2, 6),
					new Coordinate(1, 7)
					));
			this.signs = new ArrayList<Integer>(Arrays.asList(2, 21));
			this.color = "dark";
		}
		
	}
	
	/**
	 * Returns the name of the player.
	 * 
	 * @return the name of the player
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Sets the name of the player.
	 * 
	 * @param name the name of the player
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Returns true if the player is actual. 
	 *  
	 * @return true if the player is actual
	 */
	public boolean isActual() {
		return actual;
	}
	
	/**
	 * Sets the players actuality.
	 * 
	 * @param actual decides if the player is actual
	 */
	public void setActual(boolean actual) {
		this.actual = actual;
	}

	/**
	 * Returns a list of placed pieces by the player.
	 * 
	 * @return list of placed pieces by the player
	 */
	public List<Coordinate> getPlacedPieces() {
		return placedPieces;
	}

	/**
	 * Sets a list of placed pieces.
	 * 
	 * @param placedFigures list of pieces to set
	 */
	public void setPlacedPieces(List<Coordinate> placedFigures) {
		this.placedPieces = placedFigures;
	}
	
	/**
	 * Returns the value of won attribute. True if player won.
	 * 
	 * @return value of won attribute
	 */
	public boolean isWon() {
		return won;
	}
	
	/**
	 * Sets the value if won attribute.
	 * 
	 * @param won decides if the player won
	 */
	public void setWon(boolean won) {
		this.won = won;
	}	

	/**
	 * Returns true if the player is in mandatory hit.
	 * 
	 * @return true if the player is in mandatory hit
	 */
	public boolean isInMandatoryHit() {
		return inMandatoryHit;
	}

	/**
	 * Sets the player mandatory hit attribute.
	 * 
	 * @param inMandatoryHit decides if the player is in mandatory hit
	 */
	public void setInMandatoryHit(boolean inMandatoryHit) {
		this.inMandatoryHit = inMandatoryHit;
	}
	
	/**
	 * Returns the number of placed pieces on the table.
	 * 
	 * @return size of the list contains placed pieces by player
	 */
	public int getPiecesNumber() {
		return this.placedPieces.size();
	}
	
	/**
	 * Returns a list of possible signs of the player.
	 * 
	 * @return a list of possible signs of the player
	 */
	public List<Integer> getSigns() {
		return signs;
	}
	
	/**
	 * Returns the color of th player.
	 * 
	 * @return the color of the player
	 */
	public String getColor() {
		return color;
	}

	/**
	 * Sets the coor of the player.
	 * 
	 * @param color the color of the player to set
	 */
	public void setColor(String color) {
		this.color = color;
	}
	
	/**
	 * Returns true if the player is light, false if dark.
	 * 
	 * @return true if the player is light, false if dark
	 */
	public boolean isStarter() {
		return color.equals("light") ? true : false;
	}
	
	/**
	 * Removes a coordinate from place pieces list of the player.
	 * 
	 * @param c coordinate to remove
	 */
	public void removeCoordinateFromList(Coordinate c) {
		this.placedPieces.remove(c);
	}
	
	/**
	 * Adds coordinate to placed pieces list of the player.
	 * 
	 * @param c coordinate to add
	 */
	public void addCoordinateToList(Coordinate c) {
		this.placedPieces.add(c);
	}
}
