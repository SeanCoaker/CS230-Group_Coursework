/**
 * SmartEnemy.java - SmartEnemy class that controls the smart targeting enemy
 * @author Group 10 - Pirates of the CoFo
 * @version 1.0.0
 * SmartTarget class as a part of CS-230 Assignment 2
 */

import java.util.LinkedList;
import java.util.Queue;
import java.io.*;
import java.util.*;

public class SmartEnemy extends Enemy{

	private boolean gotAMove;
	private int xPositionToMoveTo;
	private int yPositionToMoveTo;
	private Queue<Integer> alreadyCheckedXCoordinates;
	private Queue<Integer> alreadyCheckedYCoordinates;

	/**
	 * Constructor for the SmartEnemy class
	 * @param xPosition X coordinates
	 * @param yPosition Y coordinates
	 */
	public SmartEnemy(int xPosition, int yPosition) {
		super(xPosition, yPosition);
		gotAMove = false;
		xPositionToMoveTo = xPosition;
		yPositionToMoveTo = yPosition;
	}

	/**
	 * Calculates the next map
	 * @param map The map
	 * @param thePlayer The player
	 */
	public void calculateNextMove(boolean[][] map, Player thePlayer){
		gotAMove = false;
		xPositionToMoveTo = xPosition;
		yPositionToMoveTo = yPosition;
		alreadyCheckedXCoordinates = new LinkedList<>();
		alreadyCheckedYCoordinates = new LinkedList<>();
		alreadyCheckedXCoordinates.add(thePlayer.xPosition);
		alreadyCheckedYCoordinates.add(thePlayer.yPosition);

		Queue<Integer> xCoordinates = new LinkedList<>();
		Queue<Integer> yCoordinates = new LinkedList<>();
		xCoordinates.add(thePlayer.getXPosition());
		yCoordinates.add(thePlayer.getYPosition());
		if(this.xPosition == thePlayer.xPosition && this.yPosition == thePlayer.yPosition){
			//make no move (on same tile as player)
		}else{
			checkTiles(map, xCoordinates, yCoordinates);
		}
		this.xPosition = xPositionToMoveTo;
		this.yPosition = yPositionToMoveTo;

	}

	/**
	 * Checks the tiles
	 * @param map The map
	 * @param xCoordinates The X coordinates
	 * @param yCoordinates The Y coordinates
	 */
	public void checkTiles(boolean[][] map, Queue<Integer> xCoordinates, Queue<Integer> yCoordinates){
		Queue<Integer> newXCoordinates = new LinkedList<>();
		Queue<Integer> newYCoordinates = new LinkedList<>();
		int c = xCoordinates.size();

		for(int i = 0; i < c; i++){
			int currentX = xCoordinates.peek();
			int currentY = yCoordinates.peek();
			xCoordinates.remove();
			yCoordinates.remove();

			if(checkEnemyCanMoveOnAboveCell(map, currentX, currentY) == true && gotAMove == false){
				if(this.xPosition == currentX && this.yPosition == currentY - 1){
					//enemy is above
					//set enemy's next move to below coordinates
					xPositionToMoveTo = currentX;
					yPositionToMoveTo = currentY;
					gotAMove = true;
				}else{
					if(alreadyCheckedCell(currentX, currentY - 1) == false){
						newXCoordinates.add(currentX);
						newYCoordinates.add(currentY - 1);
						alreadyCheckedXCoordinates.add(currentX);
						alreadyCheckedYCoordinates.add(currentY - 1);
					}
				}
			}
			if(checkEnemyCanMoveOnBelowCell(map, currentX, currentY) == true && gotAMove == false){
				if(this.xPosition == currentX && this.yPosition == currentY + 1){
					//enemy is below
					//set enemy's next move to up coordinates
					xPositionToMoveTo = currentX;
					yPositionToMoveTo = currentY;
					gotAMove = true;
				}else{
					if(alreadyCheckedCell(currentX, currentY + 1) == false){
						newXCoordinates.add(currentX);
						newYCoordinates.add(currentY + 1);
						alreadyCheckedXCoordinates.add(currentX);
						alreadyCheckedYCoordinates.add(currentY + 1);
					}
				}
			}
			if(checkEnemyCanMoveOnLeftCell(map, currentX, currentY) == true && gotAMove == false){
				if(this.xPosition == currentX - 1 && this.yPosition == currentY){
					//enemy is left
					//set enemy's next move to right coordinates
					xPositionToMoveTo = currentX;
					yPositionToMoveTo = currentY;
					gotAMove = true;
				}else{
					if(alreadyCheckedCell(currentX - 1, currentY) == false){
						newXCoordinates.add(currentX - 1);
						newYCoordinates.add(currentY);
						alreadyCheckedXCoordinates.add(currentX - 1);
						alreadyCheckedYCoordinates.add(currentY);
					}
				}
			}
			if(checkEnemyCanMoveOnRightCell(map, currentX, currentY) == true && gotAMove == false){
				if(this.xPosition == currentX + 1 && this.yPosition == currentY){
					//enemy is right
					//set enemy's next move to left coordinates
					xPositionToMoveTo = currentX;
					yPositionToMoveTo = currentY;
					gotAMove = true;
				}else{
					if(alreadyCheckedCell(currentX + 1, currentY) == false){
						newXCoordinates.add(currentX + 1);
						newYCoordinates.add(currentY);
						alreadyCheckedXCoordinates.add(currentX + 1);
						alreadyCheckedYCoordinates.add(currentY);

					}
				}
			}
		}
		if(newXCoordinates.isEmpty() == false && newYCoordinates.isEmpty() == false){
			checkTiles(map, newXCoordinates, newYCoordinates);
		}
	}

	/**
	 * Check an enemy can move above
	 * @param map The map
	 * @param xCoordinate The X coordinate
	 * @param yCoordinate The Y coordinate
	 * @return Whether the move is doable
	 */
	public boolean checkEnemyCanMoveOnAboveCell(boolean[][] map, int xCoordinate, int yCoordinate){
		if(map[xCoordinate][yCoordinate - 1] == false){
			return false;
		}else{
			return true;
		}
	}

	/**
	 * Check an enemy can move down
	 * @param map The map
	 * @param xCoordinate The X coordinate
	 * @param yCoordinate The Y coordinate
	 * @return Whether the move is doable
	 */
	public boolean checkEnemyCanMoveOnBelowCell(boolean[][] map, int xCoordinate, int yCoordinate){
		if(map[xCoordinate][yCoordinate + 1] == false){
			return false;
		}else{
			return true;
		}
	}


	/**
	 * Check an enemy can move Left
	 * @param map The map
	 * @param xCoordinate The X coordinate
	 * @param yCoordinate The Y coordinate
	 * @return Whether the move is doable
	 */
	public boolean checkEnemyCanMoveOnLeftCell(boolean[][] map, int xCoordinate, int yCoordinate){
		if(map[xCoordinate - 1][yCoordinate] == false){
			return false;
		}else{
			return true;
		}
	}


	/**
	 * Check an enemy can move Right
	 * @param map The map
	 * @param xCoordinate The x coordinate
	 * @param yCoordinate The x coordinate
	 * @return Whether the move is doable
	 */
	public boolean checkEnemyCanMoveOnRightCell(boolean[][] map, int xCoordinate, int yCoordinate){
		if(map[xCoordinate + 1][yCoordinate] == false){
			return false;
		}else{
			return true;
		}
	}

	/**
	 * Checks if a cell has already been checked
	 * @param x X coordiante
	 * @param y Y coordinate
	 * @return Whether the cell has been checked
	 */
	public boolean alreadyCheckedCell(Integer x, Integer y){
		int tempX;
		int tempY;

		int c = alreadyCheckedXCoordinates.size();
		boolean alreadyChecked = false;
		for(int i = 0; i < c; i++){
			//Tiles which have been completed aren't recognised as checked but are still in the queue...
			//Only recognises tiles which are checked twice in the same round.
			tempX = alreadyCheckedXCoordinates.remove();
			tempY = alreadyCheckedYCoordinates.remove();

			if(x == tempX && y == tempY){
				alreadyChecked = true;
			}
			alreadyCheckedXCoordinates.add(tempX);
			alreadyCheckedYCoordinates.add(tempY);
		}
		if(alreadyChecked == true){
			return true;
		}else{
			return false;
		}
	}

}
