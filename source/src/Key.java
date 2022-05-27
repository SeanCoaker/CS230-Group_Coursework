/**
 * Maintain the info for a key in a map.
 */
/**
 * Key.java - Key class that maintains the info for a key in a map
 * @author Group 10 - Pirates of the CoFo
 * @version 1.0.0
 * Key class as a part of CS-230 Assignment 2
 */
public class Key
{
	private int x;
	private int y;
	EnumIniInfo colour;

	/**
	 * The constructor.
	 * @param x the x.
	 * @param y the y.
	 * @param colour the colour.
	 * Should be one of the RED, YELLOW and BLUE.
	 */
	public Key( int x, int y, EnumIniInfo colour )
	{
		this.x = x;
		this.y = y;
		this.colour = colour;
	}

	/**
	 * Get x.
	 * @return x
	 */
	public int getX()
	{
		return x;
	}

	/**
	 * Get y.
	 * @return y
	 */
	public int getY()
	{
		return y;
	}

	/**
	 * Get colour.
	 * @return colour
	 */
	public EnumIniInfo getColour()
	{
		return colour;
	}

	/**
	 * Set x.
	 * @param x
	 */
	public void setX( int x )
	{
		this.x = x;
	}

	/**
	 * Set y.
	 * @param y
	 */
	public void setY( int y )
	{
		this.y = y;
	}

	/**
	 * Set colour.
	 * @param colour the colour.
	 * Should be one of the RED, YELLOW, GREEN and BLUE.
	 */
	public void setColour( EnumIniInfo colour )
	{
		this.colour = colour;
	}

	/**
	 * toString.
	 * @return the string
	 */
	public String toString()
	{
		return new String( "Key: (" + x + ',' + y + ") " + colour );
	}
}
