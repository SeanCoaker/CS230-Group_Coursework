/**
 * Token.java - Token class that hold the info of tokens on a map
 * @author Group 10 - Pirates of the CoFo
 * @version 1.0.0
 * Token class as a part of CS-230 Assignment 2
 */

public class Token
{
	private int x;
	private int y;

	/**
	 * Constructor for Token class
	 * @param x The X coordinate
	 * @param y The Y coordinate
	 */
	public Token( int x, int y )
	{
		this.x = x;
		this.y = y;
	}

	/**
	 * Get the X coordinate
	 * @return x
	 */
	public int getX()
	{
		return x;
	}

	/**
	 * Get the Y coordinate
	 * @return y
	 */
	public int getY()
	{
		return y;
	}

	/**
	 * Set the X coordinate
	 * @param x
	 */
	public void setX( int x )
	{
		this.x = x;
	}

	/**
	 * Set the Y coordinate
	 * @param y
	 */
	public void setY( int y )
	{
		this.y = y;
	}

	/**
	 * Turns to string
	 * @return The string
	 */
	public String toString()
	{
		return new String( "Token: (" + x + ',' + y + ") " );
	}
}
