/**
 * DumbEnemy.java - DumbEnemy class that controls the dumb targeting enemy
 * @author Group 10 - Pirates of the CoFo
 * @version 1.0.0
 * DumbEnemy class as a part of CS-230 Assignment 2
 */

public class DumbEnemy extends Enemy{

	/**
	 * Constructor for the DumbEnemy class
	 * @param xPosition X coordinate
	 * @param yPosition Y coordinate
	 */
	public DumbEnemy(int xPosition, int yPosition){
		super(xPosition, yPosition);
	}

	/**
	 * Calculates the next available move
	 * @param map The map
	 * @param thePlayer The player
	 */
	public void calculateNextMove(boolean[][] map, Player thePlayer){ //Needs the map so doesn't go through walls, doors, water etc...
		int playerX = thePlayer.getXPosition();
		int playerY = thePlayer.getYPosition();
		if(Math.abs(this.xPosition - playerX) > Math.abs(this.yPosition - playerY)) {
			if(playerX > this.xPosition) {
				if(validMove(map, +1, 0)){
					this.xPosition = this.xPosition +1;
				}
			}else {
				if(validMove(map, -1, 0)){
					this.xPosition = this.xPosition - 1;
				}
			}
		}else if(Math.abs(this.xPosition - playerX) < Math.abs(this.yPosition - playerY)) {
			if(playerY > this.yPosition) {
				if(validMove(map, 0, 1)){
					this.yPosition = this.yPosition + 1;
				}
			}else {
				if(validMove(map, 0,-1)){
					this.yPosition = this.yPosition - 1;
				}
			}
		}else{
			//X coordinate is equal to player
			if(playerY > this.yPosition) {
				if(validMove(map, 0, 1)){
					this.yPosition = this.yPosition + 1;
				}
			}else if (playerY < this.yPosition) {
				if(validMove(map, 0, -1)){
					this.yPosition = this.yPosition - 1;
				}
			}else{
				//Do nothing. Y and X both don't move
			}
		}
	}

}