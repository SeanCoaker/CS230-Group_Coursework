/**
 * Moveable.java - Moveable superclass for enemies and player
 * @author Group 10 - Pirates of the CoFo
 * @version 1.0.0
 * Moveable class as a part of CS-230 Assignment 2
 */

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Moveable {
	protected int xPosition;
	protected int yPosition;

	/**
	 * Constructor for Moveable class
	 * @param xPosition X coordinate
	 * @param yPosition Y coordinate
	 */
	public Moveable(int xPosition, int yPosition){
		this.xPosition = xPosition;
		this.yPosition = yPosition;
	}

	/**
	 * Moves the entity upwards
	 */
	public void moveUp(){
		yPosition = yPosition - 1;
	}

	/**
	 * Moves the entity downwards
	 */
	public void moveDown(){
		yPosition = yPosition + 1;
	}

	/**
	 * Moves the entity to the left
	 */
	public void moveLeft(){
		xPosition = xPosition - 1;
	}

	/**
	 * Moves the entity to the right
	 */
	public void moveRight(){
		xPosition = xPosition + 1;
	}

	/**
	 * Gets the X coordinate
	 * @return xPosition
	 */
	public int getXPosition(){
		return xPosition;
	}

	/**
	 * Gets the Y coordinate
	 * @return yPosition
	 */
	public int getYPosition(){
		return yPosition;
	}

	/**
	 * Sets the X coordinate
	 * @param xPosition The X coordinate
	 */
	public void setXPosition(int xPosition){
		this.xPosition = xPosition;
	}

	/**
	 * Sets the Y coordinate
	 * @param yPosition The Y coordinate
	 */
	public void setYPosition(int yPosition){
		this.yPosition = yPosition;
	}

	//Needs to pass tile location moving to.
	/**
	 * Checks if a move is valid
	 * @param map The map
	 * @param xMove X coordinate move to be made
	 * @param yMove Y coordinate move to be made
	 * @return Whether a move is valid
	 */
	public boolean validMove(boolean[][] map, int xMove, int yMove){
		if(map[xPosition + xMove][yPosition + yMove] == true){
			return true;
		} else {
			return false;
		}

	}

	/**
	 * Coordinates of a valid move
	 * @param map The map
	 * @param xCoordinate The X coordinate
	 * @param yCoordinate The Y coordinate
	 * @return Whether a move is valid
	 */
	public boolean validMoveCoordinates(boolean[][] map, int xCoordinate, int yCoordinate){
		if(map[xCoordinate][yCoordinate] == true){
			return true;
		}else{
			return false;
		}

	}

}
