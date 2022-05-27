/**
 * Enemy.java - Enemy class that calculates moves and checks if it has killed the player
 * @author Group 10 - Pirates of the CoFo
 * @version 1.0.0
 * Enemy class as a part of CS-230 Assignment 2
 */
public class Enemy extends Moveable{

	/**
	 * Constructor for enemy class
	 * @param xPosition X coordinate
	 * @param yPosition Y coordinate
	 */
	public Enemy(int xPosition, int yPosition) {
		super(xPosition, yPosition);
	}


	/**
	 *	Required method to avoid errors
	 */
	public void calculateNextMove(){
		this.xPosition = xPosition;
		this.yPosition = yPosition;
	}


	/**
	 * Kills the player
	 * @param thePlayer Player
	 * @param theLevel Level details
	 * @return Whether or not the player has been killed
	 */
	public boolean checkKillPlayer(Player thePlayer, EnumIniInfo[][] theLevel){
        int playerX = thePlayer.getXPosition();
        int playerY = thePlayer.getYPosition();
        if (playerX == getXPosition() && playerY == getYPosition()){
            if(theLevel[thePlayer.xPosition][thePlayer.yPosition] == EnumIniInfo.LAVA || theLevel[thePlayer.xPosition][thePlayer.yPosition] == EnumIniInfo.WATER){
                return false;
            }else{
                return true;
            }
        }else{
            return false;
        }
    }
}
