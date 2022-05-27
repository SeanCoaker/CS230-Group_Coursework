/**
 * BSTNode.java - BSTNode that represents a node in the Binary Search Tree
 * @author Group 10 - Pirates of the CoFo
 * @version 1.0.0
 * BSTNode class as a part of CS-230 Assignment 2
 */

public class BSTNode {
    private String user;
    private int score;
    private BSTNode l;
    private BSTNode r;

    /**
     * Constructor for the BSTNode class
     * @param user Username of the profile
     * @param score Highscore of the user
     */
    public BSTNode(String user, int score) {
        this.user = user;
        this.score = score;
        this.l = null;
        this.r = null;
    }

    /**
     * Gets the user's score
     * @return score
     */
    public int getScore() {
        return score;
    }

    /**
     * Gets the username bound to the profile
     * @return user
     */
    public String getUser() {
        return user;
    }

    /**
     * Gets the left child from a specific node
     * @return Left child
     */
    public BSTNode getL() {
        return l;
    }

    /**
     * @param l Sets the left child of a node
     */
    public void setL(BSTNode l) {
        this.l = l;
    }

    /**
     * Gets the right child from a specific node
     * @return Right child
     */
    public BSTNode getR() {
        return r;
    }

    /**
     * @param r Sets the right child of a node
     */
    public void setR(BSTNode r) {
        this.r = r;
    }
}