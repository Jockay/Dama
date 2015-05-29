package model;

/**
 * Class for game table coordinates.
 * 
 * @author Jockay
 *
 */
public class Coordinate {
	/** <code>X</code> coodrinate. */
	private int x;
	/** <code>Y</code> coordinate. */
	private int y;

	/** Class constructor*/
	public Coordinate(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	/** 
	 * Returns <code>x</code> coordinate. 
	 * 
	 * @return <code>x</code> coordinate 
	 */
	public int getX() {
		return x;
	}

	/**
	 * Sets <code>X</code> coordinate.
	 * 
	 * @param x coordinate to set as <code>X</code>.
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Returns <code>Y</code> coordinate.
	 * 
	 * @return <code>Y</code> coordinate
	 */
	public int getY() {
		return y;
	}

	/**
	 * Sets <code>Y</code> coordinate.
	 * 
	 * @param y coordinate to set as <code>Y</code>
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * Returns a string representation of the object.
	 */
	@Override
	public String toString() {
		return "(" + x + ", " + y + ")";
	}
	
	/**
	 * Returns true if the parameter object is equals to <code>this</code> object.
	 * 
	 * @return true if the parameter object equals to <code>this</code> object
	 */
	@Override
	public boolean equals(Object obj) {
		Coordinate c = null;
		if(obj instanceof Coordinate) {
			c = (Coordinate)obj;
			if(this.getX() == c.getX() && this.getY() == c.getY())
				return true;
		} 
		return false;
	}
}
