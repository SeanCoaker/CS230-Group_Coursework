/**
 * WallFollowingEnemy.java - WallFollowingEnemy class that controls the wall following enemies
 * @author Group 10 - Pirates of the CoFo
 * @version 1.0.0
 * WallFollowingEnemy class as a part of CS-230 Assignment 2
 */

public class WallFollowingEnemy extends Enemy{

	private int xVector;
	private int yVector;
	private int previousX;
	private int previousY;

	/** Constructir for the WallFollowingEnemy class
	 * @param xPosition The X coordinate
	 * @param yPosition The Y coordinate
	 */
	public WallFollowingEnemy(int xPosition, int yPosition) {
		super(xPosition, yPosition);
		this.previousX = xPosition;
		this.previousY = yPosition;
	}

	/**
	 * Calculates the next move to take
	 * @param map The map
	 */
	public void calculateNextMove(boolean[][] map){
		if(checkWallAbove(map)){
			wallAbove(map);
		}else if(checkWallBelow(map)){
			wallBelow(map);
		}else if(checkWallLeft(map)){
			wallLeft(map);
		}else if(checkWallRight(map)){
			wallRight(map);
		}else if(checkWallAboveLeft(map)){
			wallAboveLeft(map);
		}else if(checkWallAboveRight(map)){
			wallAboveRight(map);
		}else if(checkWallBelowLeft(map)){
			wallBelowLeft(map);
		}else if(checkWallBelowRight(map)){
			wallBelowRight(map);
		}else{
			//There are no walls next to the enemy
		}
	}
	/** Move based on the wall being below
	 * @param map The map
	 */
	public void wallBelow(boolean[][] map){
		if(validMoveCoordinates(map, xPosition + 1, yPosition)){
			 previousX = xPosition;
			 moveRight();
		}else if(validMoveCoordinates(map, xPosition, yPosition - 1)){
			previousY = yPosition;
			moveUp();
		}
	}

	/** Move based on the wall being above
	 * @param map The map
	 */
	public void wallAbove(boolean[][] map){
		if(validMoveCoordinates(map, xPosition - 1, yPosition)){
			previousX = xPosition;
			moveLeft();
		}else if(validMoveCoordinates(map, xPosition, yPosition + 1)){
			previousY = yPosition;
			moveDown();
		}
	}

	/** Move based on the wall being to the left
	 * @param map The map
	 */
	public void wallLeft(boolean[][] map){
		if(validMoveCoordinates(map, xPosition, yPosition + 1)){
			previousY = yPosition;
			moveDown();
		}else if(validMoveCoordinates(map, xPosition + 1, yPosition)){
			previousX = xPosition;
			moveRight();
		}
	}

	/** Move based on the wall being to the right
	 * @param map The map
	 */
	public void wallRight(boolean[][] map){
		if(validMoveCoordinates(map, xPosition, yPosition - 1)){
			previousY = yPosition;
			moveUp();
		}else if(validMoveCoordinates(map, xPosition - 1, yPosition)){
			previousX = xPosition;
			moveLeft();
		}
	}

	/** Move based on the wall being diagonally above and left
	 * @param map The map
	 */
	public void wallAboveLeft(boolean[][] map){
		if(yPosition - 1 == previousY){
			if(validMoveCoordinates(map, xPosition - 1, yPosition)){
				previousX = xPosition;
				moveLeft();
			}
		}else{
			if(validMoveCoordinates(map, xPosition, yPosition - 1)){
				previousY = yPosition;
				moveUp();
			}
		}
	}

	/** Move based on the wall being diagonally above and right
	 * @param map The map
	 */
	public void wallAboveRight(boolean[][] map){
		if(xPosition + 1 == previousX){
			if(validMoveCoordinates(map, xPosition, yPosition - 1)){
				previousY = yPosition;
				moveUp();
			}
		}else{
			if(validMoveCoordinates(map, xPosition + 1, yPosition)){
				previousX = xPosition;
				moveRight();
			}
		}
	}

	/** Move based on the wall being diagonally below and left
	 * @param map
	 */
	public void wallBelowLeft(boolean[][] map){
		if(xPosition - 1 == previousX){
			if(validMoveCoordinates(map, xPosition, yPosition + 1)){
				previousY = yPosition;
				moveDown();
			}
		}else{
			if(validMoveCoordinates(map, xPosition - 1, yPosition)){
				previousX = xPosition;
				moveLeft();
			}
		}
	}

	/** Move based on the wall being diagonally below and right
	 * @param map The right
	 */
	public void wallBelowRight(boolean[][] map){
		if(yPosition + 1 == previousY){
			if(validMoveCoordinates(map, xPosition + 1, yPosition)){
				previousX = xPosition;
				moveRight();
			}
		}else{
			if(validMoveCoordinates(map, xPosition, yPosition + 1)){
				previousY = yPosition;
				moveDown();
			}
		}
	}


	/**
	 * Checks if a wall is above
	 * @param map The map
	 * @return Whether a wall is above
	 */
	public boolean checkWallAbove(boolean[][] map){
		if(map[xPosition][yPosition - 1] == false){
			return true;
		}else{
			return false;
		}
	}

	/** Checks if a wall is below
	 * @param map The map
	 * @return Whether a wall is below
	 */
	public boolean checkWallBelow(boolean[][] map){
		if(map[xPosition][yPosition + 1] == false){
			return true;
		}else{
			return false;
		}
	}

	/** Checks if a wall is to the left
	 * @param map The map
	 * @return Whether a wall is to the left
	 */
	public boolean checkWallLeft(boolean[][] map){
		if(map[xPosition - 1][yPosition] == false){
			return true;
		}else{
			return false;
		}
	}

	/**
	 * Checks if a wall is to the right
	 * @param map The map
	 * @return Whether a wall is to the right
	 */
	public boolean checkWallRight(boolean[][] map){
		if(map[xPosition + 1][yPosition] == false){
			return true;
		}else{
			return false;
		}
	}

	/**
	 * Checks if a wall is diagonally above and left
	 * @param map The map
	 * @return Whether the wall is diagonally above and left
	 */
	public boolean checkWallAboveLeft(boolean[][] map){
		if(map[xPosition - 1][yPosition - 1] == false){
			return true;
		}else{
			return false;
		}
	}

	/**
	 * Checks if a wall is diagonally above and right
	 * @param map The map
	 * @return Whether the wall is diagonally above and right
	 */
	public boolean checkWallAboveRight(boolean[][] map){
		if(map[xPosition + 1][yPosition - 1] == false){
			return true;
		}else{
			return false;
		}
	}

	/**
	 * Checks if a wall is diagonally below and left
	 * @param map The map
	 * @return Whether a wall is diagonally below and left
	 */
	public boolean checkWallBelowLeft(boolean[][] map){
		if(map[xPosition - 1][yPosition + 1] == false){
			return true;
		}else{
			return false;
		}
	}

	/**
	 * Checks if a wall is diagonally below and right
	 * @param map The map
	 * @return Whether the wall is diagonally below and right
	 */
	public boolean checkWallBelowRight(boolean[][] map){
		if(map[xPosition + 1][yPosition + 1] == false){
			return true;
		}else{
			return false;
		}
	}

	/**
	 * Gets the X vector
	 * @return xVector
	 */
	public int getxVector() {
		return xVector;
	}

	/**
	 * Gets the Y vector
	 * @return yVector
	 */
	public int getyVector() {
		return yVector;
	}
}
