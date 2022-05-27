/**
 * Inventory.java - Inventory class to store all items available on the map
 * @author Group 10 - Pirates of the CoFo
 * @version 1.0.0
 * Inventory class as a part of CS-230 Assignment 2
 */

import java.util.HashMap;

public class Inventory {
    private int fireBoots;
    private int flippers;
    private int tokens;
    private HashMap<String, Integer> keys = new HashMap<String, Integer>();

    /**
     * Constructor for the Inventory class
     */
    public Inventory() {
        this.fireBoots = 0;
        this.flippers = 0;
        this.tokens = 0;
        this.keys.put("red", 0);
        this.keys.put("blue", 0);
        this.keys.put("yellow", 0);
    }

    /**
     * Adds one FireBoot/Amulet of Hephaestus to the inventory
     */
    public void addFireBoots() {
        this.fireBoots ++;
    }

    /**
     * Gets the amount of FireBoots/Amulets of Hephaestus in the inventory
     * @return The amount of FireBoots/Amulets of Hephaestus
     */
    public int getFireBoots() {
        return this.fireBoots;
    }

    /**
     * Adds one Flipper/Amulet of Poseidon to the inventory
     */
    public void addFlippers() {
        this.flippers ++;
    }

    /**
     * Gets the amount of Flippers/Amulets of Poseidon in the invemtory
     * @return The amount of Flippers/Amulets of Poseidon
     */
    public int getFlippers() {
        return this.flippers;
    }

    /**
     * Adds one token to the inventory
     */
    public void addTokens() {
        this.tokens ++;
    }

    /**
     * Gets the number of tokens in the inventory
     * @return The number of tokens
     */
    public int getTokens() {
        return this.tokens;
    }

    /**
     * Adds one key of specified colour to the inventory
     * @param colour The colour of the key to add
     */
    public void addKey(String colour) {
        keys.replace(colour, keys.get(colour) + 1);
    }

    /**
     * Gets the number of keys of a specified colour in the inventory
     * @param colour The colour of the key
     * @return The number of keys for a specified colour
     */
    public int getKeys(String colour) {
        return this.keys.get(colour);
    }

    /**
     * Decides whether a player can walk on fire
     * @return The state of if the player has FireBoots/Amulets of Hephaestus
     */
    public boolean isMovableFireBoots() {
        if (getFireBoots() > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Decides whether a player can walk on water
     * @return The state of if the player has Flippers/Amulets of Posiedon
     */
    public boolean isMovableFlippers() {
        if (getFlippers() > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Decides if the player can use a token door
     * @param tokensRequired The amount of tokens the door needs to be opened
     * @return A state of if the door can be opened
     */
    public boolean isMovableTokens(int tokensRequired) {
        if (getTokens() >= tokensRequired) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Decides if the player can use a keyed door
     * @param colour The colour of the key
     * @return A state of whether the door can be opened
     */
    public boolean isMovableKeys(String colour) {
        if (keys.get(colour) > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Uses a key from the inventory
     * @param colour The colour of the key
     */
    public void useKey(String colour) { keys.replace(colour, keys.get(colour) - 1); }

    /**
     * Removes a specified amount of tokens from inventory
     * @param tokens The amount to delete
     */
    public void delTokens(int tokens) {
        this.tokens = this.tokens - tokens;
    }
}