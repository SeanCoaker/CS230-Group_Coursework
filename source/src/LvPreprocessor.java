/**
 * LvPreprocessor.java - lvPreprocessor class
 * @author Group 10 - Pirates of the CoFo
 * @version 1.0.0
 * LvPreprocessor class as a part of CS-230 Assignment 2
 */

import java.util.Scanner;
import java.util.ArrayList;
import java.lang.System;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;


/**
 * An instance of this class reads a level file and stores the info so that it is
 * easier to get info of entities of the level with the instance.
 */
public class LvPreprocessor
{
	//`Each elem contains the raw info of a row of the grid.
	ArrayList<String> gridRows;

	//`Each elem contains the raw info of a complex tile.
	ArrayList<String> complexTileLines;

	//`Each elem contains the raw info of a moveable entities.
	ArrayList<String> movEnLines;

	//`The level number.
	int lvNum;

	/**
	 * Generate the info required to construct a tile of a map.
	 * - Also see this.getMapSize()
	 * @param x horizantal position for the tile.
	 * @param y vertical position for the tile.
	 * @return An array of IniInfo.
	 * - The first element is always the type of the tile ( in Enum ).
	 * - If the array has more than one elements, the extra things will be the
	 *   details for the tile.
	 * - The following are the list of formats for all the tiles:
	 *   * VOID_CELL, (x, in int), (y, in int)
	 *   * WALL, (x, in int), (y, in int)
	 *   * FLOOR, (x, in int), (y, in int)
	 *   * TOKEN, (x, in int), (y, in int)
	 *   * LAVA, (x, in int), (y, in int)
	 *   * WATER, (x, in int), (y, in int)
	 *   * WATER_BOOTS, (x, in int), (y, in int)
	 *   * FIRE_BOOTS, (x, in int), (y, in int)
	 *   * FINISH, (x, in int), (y, in int)
	 *   * COLOUR_DOOR, (x, in int), (y, in int), (Colour e.g. RED, BLUE etc)
	 *   * TOKEN_DOOR, (x, in int), (y, in int), (Number of tokens, in int)
	 *   * KEY, (x, in int), (y, in int), (Colour e.g. RED, BLUE etc)
	 *   * TELEPORTER, (x, in int), (y, in int), (x, in int), (y, in int)
	 */
	public IniInfo[] getTileInfo( int x, int y )
	{
		final String TILEKEY = gridRows.get( y ).substring( x, x + 1 );
		final EnumIniInfo HEAD = INFO_MAP.get( TILEKEY );
		IniInfo[] info = new IniInfo[0];
		if ( HEAD != EnumIniInfo.COMPLEX_CELL ) {
			//`Simple cell case:
			info = new IniInfo[3];
			info[0] = new IniInfo( HEAD );
			info[1] = new IniInfo( x );
			info[2] = new IniInfo( y );
		} else {
			//`Complex cell case:
			int i = 0;
			int n = complexTileLines.size();
			boolean found = false;
			//`Find the detail for the complex tile.
			while ( i < n && !found ) {
				info = str2infoArray( complexTileLines.get( i ) );
				//`To judge if a detail if for the current tile, just check the
				// coordinate in it.
				if ( info[1].asInt() == x && info[2].asInt() == y ) {
					found = true;
				}
				++i;
			}
			//`Now the detail for the tile should be found.
			if ( !found ) {
				String err = new String( "No detail for tile at ( " );
				err += String.valueOf( x ) + ", " + String.valueOf( y ) + " )";
				System.out.println( "Filereader: " + err );
				System.exit(1);
			}
			//`Deal with Door case:
			if ( info[0].asEnum() == EnumIniInfo.DOOR ) {
				if ( info[3].isEnum() ) {
					//`Colour door.
					info[0] = new IniInfo( EnumIniInfo.COLOUR_DOOR );
				} else {
					//`Token door.
					info[0] = new IniInfo( EnumIniInfo.TOKEN_DOOR );
				}
			}
		}
		return info;
	}

	/**
	 * Generate the info required to construct a Moveable entity.
	 * - Also see this.getMovEnQuan()
	 * @param idx the index mapping to an entity.
	 * @return An array of IniInfo.
	 * - The first element is always the type of the tile ( in Enum ).
	 * - If the array has more than one elements, the extra things will be the
	 *   details for the entity.
	 * - The following are the list of formats for all the moveable entities:
	 *   * PLAYER, (x, in int), (y, in int)
	 *   * STRAIGHT_LINE_ENEMY, (x, in int), (y, in int), (direc, e.g. UP etc)
	 *   * WALL_FOLLOWING_ENEMY, (x, in int), (y, in int), (direc, e.g. UP etc)
	 *   * DUMB_TARGETING_ENEMY, (x, in int), (y, in int)
	 *   * SMART_TARGETING_ENEMY, (x, in int), (y, in int)
	 */
	public IniInfo[] getMovEnInfo( int idx )
	{
		return str2infoArray( movEnLines.get( idx ) );
	}

	/**
	 * Get the size of the map to be constructed.
	 * @return the size. An integer array of 2
	 * - where 1st is the width and 2nd is the height.
	 */
	public int[] getMapSize() {
		int y = gridRows.size();
		int x = gridRows.get(1).length();
		int[] mapSize = new int[2];
		mapSize[0] = x;
		mapSize[1] = y;
		return mapSize;
	}

	/**
	 * Get the quantity of the moveable entities involved.
	 * - Inc. the player.
	 * @return the quantity.
	 */
	public int getMovEnQuan()
	{
		return movEnLines.size();
	}

	/**
	 * Get the level number.
	 * @return the number.
	 */
	public int getLvNum()
	{
		return lvNum;
	}

	/**
	 * The constructor.
	 * @param fnm the file name.
	 */
	public LvPreprocessor( String fnm )
	{
		try {
			//`Create the scanner.
			Scanner fileIn = new Scanner( new File ( fnm ) );
			//`Complete this.gridRows.
			this.gridRows = getNonBlankLines( fileIn );
			//`Complete this.complexTileLines.
			this.complexTileLines = getNonBlankLines( fileIn );
			//`Complete this.movEnLines.
			this.movEnLines = getNonBlankLines( fileIn );
			//`Complete this.lvNum.
			Integer tmp = new Integer( fnm.replaceAll( "[^\\p{Digit}]", "" ) );
			this.lvNum = tmp.intValue();
			//`Close the Scanner.
			fileIn.close();
		} catch ( FileNotFoundException e ) {
			System.out.println( "File does not exist: " + fnm );
			System.exit(1);
		}
	}

	/**
	 * To String
	 * @return toString
	 */
	public String toString()
	{
		String result = new String ( "Level: " + String.valueOf( lvNum ) );
		result += "\n\n";
		for ( String elem : gridRows ) {
			result += elem + '\n';
		}
		result += '\n';
		for ( String elem : complexTileLines ) {
			result += elem + "    ->    ";
			for ( IniInfo celem : str2infoArray( elem ) ) {
				result += celem.toString() + ' ';
			}
			result += '\n';
		}
		result += '\n';
		for ( String elem : movEnLines ) {
			result += elem + "    ->    ";
			for ( IniInfo celem : str2infoArray( elem ) ) {
				result += celem.toString() + ' ';
			}
			result += '\n';
		}
		result += '\n';
		return result;
	}

	//`Read a list of continuously non-blank lines from a scanner.
	// - It will never stop reading until a blank line is found or there is no
	//   more line to read.
	// - Arg scnr: the scanner.
	// - Return: the result. Each element includes one line.
	private static ArrayList<String> getNonBlankLines( Scanner scnr )
	{
		ArrayList<String> result = new ArrayList<String>();
		final String blank = new String( "^\\p{Blank}*$" );
		String line = new String( "+" );
		while ( scnr.hasNextLine() && ! line.matches( blank ) ) {
			line = scnr.nextLine();
			if ( ! line.matches( blank ) ) {
				result.add( line );
			}
		}
		return result;
	}

	//`Convert a line of raw info read from the level file to a array of
	// IniInfo ( not the grid part! ).
	// - Notice this just convert each item in a line to a corresponded IniInfo,
	//   but do not do the deeper checking, so e.g. "Door" just be converted to
	//   DOOR but not COLOUR_DOOR or TOKEN_DOOR.
	// - Arg str: the string to be converted.
	// - Return: the result array
	// - e.g.
	//
	//   "Key(3,4)(red)"
	//   "Door(9,2)(red)"
	//
	//   will be converted to a IniInfo array respectively:
	//
	//   { KEY, 3, 4, RED }
	//   { DOOR, 9, 2, RED }   BUT NOT { COLOUR_DOOR, 9, 2, RED }
	//
	private static IniInfo[] str2infoArray( String line )
	{
		final String num = new String( "\\p{Digit}+" );
		final String spliter = new String( "[(),]+" );
		final String[] words = line.split( spliter );
		IniInfo[] result = new IniInfo[ words.length ];
		for ( int i = 0, n = words.length; i < n; ++i ) {
			if ( words[i].matches( num ) ) {
				Integer tmp = new Integer( words[i] );
				result[i] = new IniInfo( tmp.intValue() );
			} else {
				result[i] = new IniInfo( INFO_MAP.get( words[i] ) );
			}
		}
		return result;
	}

	//`Maps from items in the level file to non-numeric IniInfo values.
	// - e.g. "W" -> WALL, "Key" -> KEY etc.
	private static final HashMap<String,EnumIniInfo> INFO_MAP;

	//`Initialise static attributes.
	static
	{
		//`Initialise INFO_MAP:
		HashMap<String,EnumIniInfo> infoMap;
		infoMap = new HashMap<String,EnumIniInfo>();

		infoMap.put( "0",          EnumIniInfo.VOID_CELL );
		infoMap.put( "W",          EnumIniInfo.WALL );
		infoMap.put( "F",          EnumIniInfo.FLOOR );
		infoMap.put( "T",          EnumIniInfo.TOKEN );
		infoMap.put( "L",          EnumIniInfo.LAVA );
		infoMap.put( "A",          EnumIniInfo.WATER );
		infoMap.put( "B",          EnumIniInfo.WATER_BOOTS );
		infoMap.put( "P",          EnumIniInfo.FIRE_BOOTS );
		infoMap.put( "G",          EnumIniInfo.FINISH );

		infoMap.put( "?",          EnumIniInfo.COMPLEX_CELL );

		infoMap.put( "Door",       EnumIniInfo.DOOR );
		infoMap.put( "Key",        EnumIniInfo.KEY );
		infoMap.put( "Teleporter", EnumIniInfo.TELEPORTER );

		infoMap.put( "Start",      EnumIniInfo.PLAYER );
		infoMap.put( "Straight",   EnumIniInfo.STRAIGHT_LINE_ENEMY );
		infoMap.put( "WallF",      EnumIniInfo.WALL_FOLLOWING_ENEMY );
		infoMap.put( "Dumb",       EnumIniInfo.DUMB_TARGETING_ENEMY );
		infoMap.put( "Smart",      EnumIniInfo.SMART_TARGETING_ENEMY );

		infoMap.put( "up",         EnumIniInfo.UP );
		infoMap.put( "down",       EnumIniInfo.DOWN );
		infoMap.put( "left",       EnumIniInfo.LEFT );
		infoMap.put( "right",      EnumIniInfo.RIGHT );

		infoMap.put( "red",        EnumIniInfo.RED );
		infoMap.put( "green",      EnumIniInfo.GREEN );
		infoMap.put( "blue",       EnumIniInfo.BLUE );
		infoMap.put( "yellow",     EnumIniInfo.YELLOW );

		INFO_MAP = infoMap;
	}
}
