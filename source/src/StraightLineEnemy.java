/**
 * StraightLineEnemy.java - StraightLineEnemy class that controls the straight line enemy
 * @author Group 10 - Pirates of the CoFo
 * @version 1.0.0
 * StraightLineEnemy class as a part of CS-230 Assignment 2
 */

public class StraightLineEnemy extends Enemy{

	private int xVelocity;
	private int yVelocity;


	/**
	 * Constructor for the StraightLineEnemy class
	 * @param xPosition X coordinate
	 * @param yPosition Y coordinate
	 * @param xVelocity X move velocity
	 * @param yVelocity Y move velocity
	 */
	public StraightLineEnemy(int xPosition, int yPosition, int xVelocity, int yVelocity){
		super(xPosition, yPosition);
		this.xVelocity = xVelocity;
		this.yVelocity = yVelocity;
	}

	/**
	 * Checks the next move it can make
	 * @param map The map
	 */
	public void calculateNextMove(boolean[][] map){

		if(validMove(map, xVelocity, yVelocity) == true){
			this.xPosition = xPosition + xVelocity;
			this.yPosition = yPosition + yVelocity;
		}else{
			this.xVelocity = -xVelocity;
			this.yVelocity = -yVelocity;
			calculateNextMove(map);
		}
	}

	/**
	 * Gets the X velocity
	 * @return xVelocity
	 */
	public int getxVelocity() {
		return xVelocity;
	}

	/**
	 * Gets the Y velocity
	 * @return yVelocity
	 */
	public int getyVelocity() {
		return yVelocity;
	}
}
