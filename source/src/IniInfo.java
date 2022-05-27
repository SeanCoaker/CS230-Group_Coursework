/**
 * InilInfo.java - Main class that launches the JavaFX program
 * @author Group 10 - Pirates of the CoFo
 * @version 1.0.0
 * I class as a part of CS-230 Assignment 2
 */

/**
 * The info provide by a LvPreprocessor instance.
 * - Any IniInfo instance can be read either in int or in enum.
 */
public class IniInfo
{
	private int intMem;
	private EnumIniInfo enumMem;

	/**
	 * Read the info as an integer.
	 * @return see the description.
	 */
	public int asInt() { return this.intMem; }

	/**
	 * Read the info as an EnumIniInfo ( the enum way ).
	 * @return see the description.
	 */
	public EnumIniInfo asEnum() { return this.enumMem; }

	/**
	 * Construct as int
	 * @param i the integer to store.
	 */
	public IniInfo( int i )
	{
		this.intMem = i;
		this.enumMem = EnumIniInfo.UNDEFINED;
	}

	/**
	 * Construct as int
	 * @param e the EnumIniInfo to store.
	 */
	public IniInfo( EnumIniInfo e )
	{
		this.intMem = 0;
		this.enumMem = e;
	}

	/**
	 * Check how to read the info
	 * @return
	 * - true: it should be read as an enum.
	 * - false: it should be read as an integer.
	 */
	public boolean isEnum()
	{
		return enumMem != EnumIniInfo.UNDEFINED;
	}

	/**
	 * To String
	 * @return toString
	 */
	public String toString()
	{
		if ( ! isEnum() ) {
			return String.valueOf( intMem );
		} else {
			return enumMem.toString();
		}
	}
}
