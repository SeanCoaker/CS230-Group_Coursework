/**
 * Save.java - Save class that saves current game progress
 * @author Group 10 - Pirates of the CoFo
 * @version 1.0.0
 * Save class as a part of CS-230 Assignment 2
 */

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

public class Save {

    private String[][] map;
    private EnumIniInfo[][] theLevel;
    private int level;
    private int time;
    private String username;
    private File file;
    private File fileInv;
    private HashMap<EnumIniInfo,String> firstInfoMap;
    private ArrayList<String> doors = new ArrayList<>();
    private ArrayList<String> teles = new ArrayList<>();
    private ArrayList<String> keys = new ArrayList<>();

    private StraightLineEnemy[] sl;
    private WallFollowingEnemy[] wf;
    private DumbEnemy[] de;
    private SmartEnemy[] se;

    private Inventory inv;

    private String player;

    /**
     * Constructor for the Save class
     * @param theLevel The level
     * @param username Player's username
     * @param level Level number
     * @param p The player
     * @param sl Straight Line Enemy
     * @param wf Wall Following Enemy
     * @param de Dumb Targeting Enemy
     * @param se Smart Targeting Enemy
     * @param inv Inventory
     * @param time Time in seconds
     */
    public Save(EnumIniInfo[][] theLevel, String username, int level, Player p, StraightLineEnemy[] sl,
                WallFollowingEnemy[] wf, DumbEnemy[] de, SmartEnemy[] se, Inventory inv, int time) {
        this.theLevel = theLevel;
        this.username = username;
        this.level = level;
        this.player = ("Start(" + p.getXPosition() + "," + p.getYPosition() + ")");
        this.sl = sl;
        this.wf = wf;
        this.de = de;
        this.se = se;
        this.inv = inv;
        this.time = time;
        createFirstHashMap();
        buildStringMapToGetDoors();

        for(int i = 0; i < map.length; i++) {
            for(int j = 0; j < map[i].length; j++) {
                if(map[i][j] == "RD") {
                    doors.add("Door(" + i + "," + j + ")(red)");
                }
            }
        }

        for(int i = 0; i < map.length; i++) {
            for(int j = 0; j < map[i].length; j++) {
                if(map[i][j] == "BD") {
                    doors.add("Door(" + i + "," + j + ")(blue)");
                }
            }
        }

        for(int i = 0; i < map.length; i++) {
            for(int j = 0; j < map[i].length; j++) {
                if(map[i][j] == "YD") {
                    doors.add("Door(" + i + "," + j + ")(yellow)");
                }
            }
        }

        for(int i = 0; i < map.length; i++) {
            for(int j = 0; j < map[i].length; j++) {
                if(map[i][j] == "T1") {
                    doors.add("Door(" + i + "," + j + ")(1)");
                }
            }
        }

        for(int i = 0; i < map.length; i++) {
            for(int j = 0; j < map[i].length; j++) {
                if(map[i][j] == "T3") {
                    doors.add("Door(" + i + "," + j + ")(3)");
                }
            }
        }

        for(int i = 0; i < map.length; i++) {
            for(int j = 0; j < map[i].length; j++) {
                if(map[i][j] == "T5") {
                    doors.add("Door(" + i + "," + j + ")(5)");
                }
            }
        }

        for(int i = 0; i < map.length; i++) {
            for(int j = 0; j < map[i].length; j++) {
                if(map[i][j] == "Tele") {
                    teles.add("Teleporter(" + i + "," + j + ")");
                }
            }
        }

        for(int i = 0; i < map.length; i++) {
            for(int j = 0; j < map[i].length; j++) {
                if(map[i][j] == "RK") {
                    teles.add("Key(" + i + "," + j + ")(red)");
                }
            }
        }

        for(int i = 0; i < map.length; i++) {
            for(int j = 0; j < map[i].length; j++) {
                if(map[i][j] == "BK") {
                    teles.add("Key(" + i + "," + j + ")(blue)");
                }
            }
        }

        for(int i = 0; i < map.length; i++) {
            for(int j = 0; j < map[i].length; j++) {
                if(map[i][j] == "YK") {
                    teles.add("Key(" + i + "," + j + ")(yellow)");
                }
            }
        }

        rotateArray90CounterClockwise(map);
    }

    /**
     * Saves progress to a file
     */
    public void save() {
        file = new File("./bin/" + username + "0.txt");
        PrintWriter out;

        try {
            if (file.createNewFile()) {
                out = new PrintWriter(new FileOutputStream(file, true));

                for(int i = map.length - 1; i >= 0; i--) {
                    for(int j = 0; j < map[i].length; j++) {
                        if (map[i][j] == "RD") {
                            out.append("?");
                        } else if (map[i][j] == "BD") {
                            out.append("?");
                        } else if (map[i][j] == "YD") {
                            out.append("?");
                        } else if (map[i][j] == "T1") {
                            out.append("?");
                        } else if (map[i][j] == "T3") {
                            out.append("?");
                        } else if (map[i][j] == "T5") {
                            out.append("?");
                        } else if (map[i][j] == "RK") {
                            out.append("?");
                        } else if (map[i][j] == "YK") {
                            out.append("?");
                        } else if (map[i][j] == "BK") {
                            out.append("?");
                        } else if (map[i][j] == "Tele") {
                            out.append("?");
                        } else {
                            out.append((map[i][j]));
                        }
                    }
                    out.append("\n");
                }
                out.append("\n");

                for(String elem : doors) {
                    out.append(elem);
                    out.append("\n");
                }

                for(String elem : teles) {
                    out.append(elem);
                    out.append("\n");
                }

                for(String elem : keys) {
                    out.append(elem);
                    out.append("\n");
                }

                out.append("\n");
                out.append(this.player + "\n");

                for(StraightLineEnemy elem : sl) {
                    out.append("Straight(" + elem.getXPosition() + "," + elem.getYPosition() + ")(");
                    if(elem.getxVelocity() == 0 && elem.getyVelocity() == -1) {
                        out.append("up)");
                    } else if(elem.getxVelocity() == 0 && elem.getyVelocity() == 1) {
                        out.append("down)");
                    } else if(elem.getxVelocity() == -1 && elem.getyVelocity() == 0) {
                        out.append("left)");
                    } else if(elem.getxVelocity() == 1 && elem.getyVelocity() == 0) {
                        out.append("right)");
                    }
                    out.append("\n");
                }

                for(WallFollowingEnemy elem : wf) {
                    out.append("WallF(" + elem.getXPosition() + "," + elem.getYPosition() + ")");
                    out.append("\n");
                }

                for(DumbEnemy elem : de) {
                    out.append("Dumb(" + elem.getXPosition() + "," + elem.getYPosition() + ")");
                    out.append("\n");
                }

                for(SmartEnemy elem : se) {
                    out.append("Smart(" + elem.getXPosition() + "," + elem.getYPosition() + ")");
                    out.append("\n");
                }
                out.close();
                saveInv();
            } else {
                file.delete();
                save();
            }
        } catch (IOException e) {
            System.out.println("Error saving file");
            System.exit(0);
        }
    }

    /**
     * Holds map translation info
     */
    public void createFirstHashMap() {
        firstInfoMap = new HashMap<EnumIniInfo,String>();

        firstInfoMap.put(EnumIniInfo.VOID_CELL, "0");
        firstInfoMap.put(EnumIniInfo.WALL, "W");
        firstInfoMap.put(EnumIniInfo.FLOOR , "F");
        firstInfoMap.put(EnumIniInfo.TOKEN, "T");
        firstInfoMap.put(EnumIniInfo.LAVA, "L");
        firstInfoMap.put(EnumIniInfo.WATER, "A");
        firstInfoMap.put(EnumIniInfo.WATER_BOOTS, "B");
        firstInfoMap.put(EnumIniInfo.FIRE_BOOTS, "P");
        firstInfoMap.put(EnumIniInfo.FINISH, "G");

        firstInfoMap.put(EnumIniInfo.COMPLEX_CELL, "?");

        firstInfoMap.put(EnumIniInfo.DOOR, "Door");
        firstInfoMap.put(EnumIniInfo.KEY, "Key");
        firstInfoMap.put(EnumIniInfo.TELEPORTER, "Tele");

        firstInfoMap.put(EnumIniInfo.UP, "up");
        firstInfoMap.put(EnumIniInfo.DOWN, "down");
        firstInfoMap.put(EnumIniInfo.LEFT, "left");
        firstInfoMap.put(EnumIniInfo.RIGHT, "right");

        firstInfoMap.put(EnumIniInfo.RED, "red");
        firstInfoMap.put(EnumIniInfo.GREEN, "green");
        firstInfoMap.put(EnumIniInfo.BLUE, "blue");
        firstInfoMap.put(EnumIniInfo.YELLOW, "yellow");

        firstInfoMap.put(EnumIniInfo.RED_DOOR, "RD");
        firstInfoMap.put(EnumIniInfo.BLUE_DOOR, "BD");
        firstInfoMap.put(EnumIniInfo.YELLOW_DOOR, "YD");

        firstInfoMap.put(EnumIniInfo.TOKEN_DOOR_1, "T1");
        firstInfoMap.put(EnumIniInfo.TOKEN_DOOR_3, "T3");
        firstInfoMap.put(EnumIniInfo.TOKEN_DOOR_5, "T5");

        firstInfoMap.put(EnumIniInfo.BLUE_KEY, "BK");
        firstInfoMap.put(EnumIniInfo.YELLOW_KEY, "YK");
        firstInfoMap.put(EnumIniInfo.RED_KEY, "RK");
    }

    /**
     * Turns a map to text to read doors
     */
    public void buildStringMapToGetDoors() {
        map = new String[theLevel.length][theLevel[0].length];
        for(int i = 0; i < theLevel.length; i++) {
            for(int j = 0; j < theLevel[i].length; j++) {
                map[i][j] = firstInfoMap.get(theLevel[i][j]);
            }
        }
    }


    /**
     * Rotates the read map
     * REFERENCE: https://javabypatel.blogspot.com/2016/11/rotate-matrix-by-90-degree-java.html
     * REFERENCE: Jayesh Patel
     * @param array Map
     */
    private void rotateArray90CounterClockwise(String[][] array) {

        int totalRowsOfRotatedArray = array[0].length;
        int totalColsOfRotatedArray = array.length;

        String[][] rotatedArray = new String[totalRowsOfRotatedArray][totalColsOfRotatedArray];

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                rotatedArray[(totalRowsOfRotatedArray-1)-j][i] = array[i][j];
            }
        }
        map = rotatedArray;
    }

    /**
     * Saves the player's inventory
     */
    public void saveInv() {
        File fileInv = new File("./bin/" + this.username + "0_Inv.txt");
        PrintWriter out;

        try {
            if (fileInv.createNewFile()) {
                out = new PrintWriter(new FileOutputStream(fileInv, true));

                out.append("Item_Red," + inv.getKeys("red") + ",\n" );
                out.append("Item_Blue," + inv.getKeys("blue") + ",\n" );
                out.append("Item_Yellow," + inv.getKeys("yellow") + ",\n" );
                out.append("Item_Tokens," + inv.getTokens() + ",\n");
                out.append("Item_FireBoots," + inv.getFireBoots() +  ",\n");
                out.append("Item_Flippers," + inv.getFlippers() + ",\n" );
                out.append("Time," + this.time + ",\n" );
                out.append("Level," + this.level + ",\n" );

                out.close();
            } else {
                fileInv.delete();
                save();
            }
        } catch(IOException e) {
            System.out.println("Error in save");
            System.exit(0);
        }

    }

}
