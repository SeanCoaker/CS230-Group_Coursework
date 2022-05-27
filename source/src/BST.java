/**
 * BST.java - BST class that creates a Binary Search Tree for sorting leaderboard scores
 * @author Group 10 - Pirates of the CoFo
 * @version 1.0.0
 * BST class as a part of CS-230 Assignment 2
 */

import java.util.ArrayList;

public class BST {
    private BSTNode root;
    private ArrayList<BSTNode> users = new ArrayList<BSTNode>();

    /**
     * Constructor for the BST class which sets root to null,
     * creating an empty BST.
     */
    public BST() {
        root = null;
    }

    /**
     * Calls the insertUser method passing root in as first node
     * @param user The username for the profile being inserted
     * @param score The score achieved
     */
    public void insertUser(String user, int score) {
        if (root == null) {
            root = new BSTNode(user, score);
        } else {
            root = insertUser(root, user, score);
        }
    }

    /**
     * Inserts a user into the BST with their best time. If the times of the new
     * user being added is less than the times of node then we recursively add the
     * new node to the left, otherwise to the right.
     * @param node Node for the Binary Search Tree
     * @param user Player's username
     * @param times The player's times
     * @return Gets the node
     */
    private BSTNode insertUser(BSTNode node, String user, int times) {
        if (node == null) {
            return new BSTNode(user, times);
        } else {
            if (node.getScore() > times) {
                node.setL(insertUser(node.getL(), user, times));
            } else {
                node.setR(insertUser(node.getR(), user, times));
            }
        }
        return node;
    }

    /**
     * Calls printScore method passing in root as node
     * @return ArrayList of users sorted by time elapsed
     */
    public ArrayList<BSTNode> printScore() {
        if (root != null) {
            return printScore(root);
        } else {
            return null;
        }
    }

    /**
     * Method to traverse BST in order to return an array list
     * of users sorted by time elapsed on the level
     * @param n current node
     * @return ArrayList of users sorted by time elapsed
     */
    private ArrayList<BSTNode> printScore(BSTNode n) {
        if (n != null) {
            printScore(n.getL());
            users.add(n);
            printScore(n.getR());
        }
        return users;
    }
}