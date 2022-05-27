/**
 * Profile.java - Profile class for creating Profiles with usernames, passwords etc
 * @author Group 10 - Pirates of the CoFo
 * @version 1.0.0
 * Profile class as a part of CS-230 Assignment 2
 */

import java.io.*;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Profile {
    private BST bst = new BST();
    private static final int DEFAULT_TIME = 300;
    private Scanner in = null;

    private File file = new File("./bin/Profiles.txt");

    /**
     * Reads all users from the profiles file
     * @return A list of users
     */
    private ArrayList<String> readUsers() {
        ArrayList<String> users = new ArrayList<String>();

        setScanner();

        in.useDelimiter(",");

        while (in.hasNext()) {
            String fileUser = in.next();
            users.add(fileUser);
            in.nextLine();
        }
        in.close();
        return users;
    }

    /**
     * Adds an account to the profiles file with default times for each level
     * @param username A profile's chosen username
     * @param password A profile's chosen password
     * @throws IOException Error if file cannot be read
     */
    public void addAccount(String username, String password) {
        PrintWriter out = null;

        try {
            out = new PrintWriter(new FileOutputStream(file, true));
        }
        catch (FileNotFoundException e) {
            System.out.println("Profile source file not found in game files: \n");
            System.out.println("Suggested fix - Redownload game files.");
            System.exit(0);
        }

        out.append(username + "," + password + "," + 1 + "," + DEFAULT_TIME + ","+ DEFAULT_TIME + ","
                + DEFAULT_TIME + "," + DEFAULT_TIME + "," + DEFAULT_TIME + "," + "\n");
        out.close();
    }

    /**
     * Checks to see if an account exists in the profile file
     * @param username The profile's username
     * @return Whether or not the account exists
     */
    public boolean existAccount(String username) {
        setScanner();
        in.useDelimiter(",");

        while(in.hasNext()) {
            String fileUser = in.next();
            in.nextLine();
            if (username.equals(fileUser)) {
                return true;
            }
        }
        in.close();
        return false;
    }

    /**
     * Gets the users time for a specified level
     * @param level The level which is specified
     * @return BST of users
     */
    public ArrayList<BSTNode> getLeaderboard(int level) {
        setScanner();
        in.useDelimiter(",");

        while(in.hasNext()) {
            String fileUser = in.next();
            in.next();
            int fileScore = DEFAULT_TIME;
            for (int i = 0; i <= level; i++) {
                fileScore = in.nextInt();
            }
            in.nextLine();
            bst.insertUser(fileUser, fileScore);
        }
        in.close();
        return bst.printScore();
    }

    /**
     * Gets the users level reached
     * @param username The username which is specified
     * @return level reached by that user from file
     */
    public int getLevelFromFile(String username) {
        setScanner();
        in.useDelimiter(",");

        while(in.hasNext()) {
            String fileUser = in.next();
            in.next();
            int fileScore = in.nextInt();
            if (fileUser.equals(username)) {
                in.close();
                return fileScore;
            } else {
                in.nextLine();
            }
        }
        in.close();
        return 0;
    }

    /**
     * Sets the level the player has reached. To edit record it creates
     * a new file and drags records over from old file then appends the edited
     * record to the end of the file.
     * @param username user to be edited in file
     * @param level player has reached
     */
    public void setLevel(String username, int level) {
        FileWriter fw;
        BufferedWriter bw = null;
        PrintWriter pw = null;

        String filepath = "./bin/Profiles.txt";
        String tempFile = "./bin/temp.txt";
        File oldFile = new File(filepath);
        File newFile = new File(tempFile);
        Scanner in = null;
        String fileUser = "";
        String filePass = "";
        int fileLevel = 0;
        ArrayList<Integer> times = new ArrayList<>();

        String fileUserEdit = "";
        String filePassEdit = "";
        int fileLevelEdit = level;
        ArrayList<Integer> timesEdit = new ArrayList<>();

        try {
            fw = new FileWriter(tempFile, true);
            bw = new BufferedWriter(fw);
            pw = new PrintWriter(bw);
            in = new Scanner(file);
            in.useDelimiter(",");

        } catch(Exception e) {
            System.out.println("System Error");
            System.exit(0);
        }

        while (in.hasNext()){
            fileUser = in.next();
            if (fileUser.equals(username)) {
                fileUserEdit = username;
                filePassEdit = in.next();
                in.nextInt();
                while (in.hasNextInt()) {
                    timesEdit.add(in.nextInt());
                }
            } else {
                filePass = in.next();
                fileLevel = in.nextInt();
                while (in.hasNextInt()) {
                    times.add(in.nextInt());
                }
                pw.print(fileUser + "," + filePass + "," + fileLevel + ",");
                for (int i = 0; i < 5; i++) {
                    if (times.get(i) == null) {
                        pw.print(DEFAULT_TIME + ",");
                    } else {
                        pw.print(times.get(i) + ",");
                    }
                }
                pw.print("\n");
            }
            times.clear();
            in.nextLine();
        }
        pw.print(fileUserEdit + "," + filePassEdit + "," + fileLevelEdit + ",");
        for (int i = 0; i < 5; i++) {
            pw.print(timesEdit.get(i) + ",");
        }
        in.close();
        pw.flush();
        pw.close();
        oldFile.delete();
        File dump = new File(filepath);
        newFile.renameTo(dump);
    }

    /**
     * Gets the user's highscore for a specific level
     * @param username Username bound to a profile
     * @param level The level specified
     * @return The highscore of the user for the specified level
     */
    public int getHighscore(String username, int level) {
        int fileScore = DEFAULT_TIME;
        setScanner();
        in.useDelimiter(",");

        while(in.hasNext()) {
            String user = in.next();
            in.next();
            in.nextInt();
            if (user.equals(username)) {
                for (int i = 1; i < level; i++) {
                    in.nextInt();
                }
                fileScore = in.nextInt();
            }
            in.nextLine();
        }
        in.close();
        return fileScore;
    }

    /**
     * Sets the time the player has for a specific level. To edit record it creates
     * a new file and drags records over from old file then appends the edited
     * record to the end of the file.
     * @param username user to be edited in file
     * @param level level time to be edited
     * @param newScore new best time to add to file for a specified level
     */
    public void changeHighscore(String username, int level, int newScore) {
        FileWriter fw;
        BufferedWriter bw;
        PrintWriter pw = null;

        String filepath = "./bin/Profiles.txt";
        String tempFile = "./bin/temp.txt";
        File oldFile = new File(filepath);
        File newFile = new File(tempFile);
        Scanner in = null;
        String fileUser = "";
        String filePass = "";
        int fileLevel = 0;
        ArrayList<Integer> times = new ArrayList<>();

        String fileUserEdit = "";
        String filePassEdit = "";
        int fileLevelEdit = 0;
        ArrayList<Integer> timesEdit = new ArrayList<>();

        try {
            fw = new FileWriter(tempFile, true);
            bw = new BufferedWriter(fw);
            pw = new PrintWriter(bw);
            in = new Scanner(file);
            in.useDelimiter(",");

        } catch(Exception e) {
            System.out.println("System Error");
            System.exit(0);
        }

        while (in.hasNext()){
            fileUser = in.next();
            if (fileUser.equals(username)) {
                fileUserEdit = username;
                filePassEdit = in.next();
                fileLevelEdit = in.nextInt();
                while (in.hasNextInt()) {
                    timesEdit.add(in.nextInt());
                }
            } else {
                filePass = in.next();
                fileLevel = in.nextInt();
                while (in.hasNextInt()) {
                    times.add(in.nextInt());
                }
                pw.print(fileUser + "," + filePass + "," + fileLevel + ",");
                for (int i = 0; i < 5; i++) {
                    if (times.get(i) == null) {
                        pw.print(DEFAULT_TIME + ",");
                    } else {
                        pw.print(times.get(i) + ",");
                    }
                }
                pw.print("\n");
            }
            times.clear();
            in.nextLine();
        }
        pw.print(fileUserEdit + "," + filePassEdit + "," + fileLevelEdit + ",");
        for (int i = 0; i < level - 1; i++) {
            pw.print(timesEdit.get(i) + ",");
        }

        pw.print(newScore + ",");

        for (int i = level; i < 5; i++) {
            pw.print(timesEdit.get(i) + ",");
        }
        in.close();
        pw.flush();
        pw.close();
        oldFile.delete();
        File dump = new File(filepath);
        newFile.renameTo(dump);
    }

    /**
     * Gets a list of users
     * @return List of users
     */
    public ArrayList<String> getUsers() {
        return readUsers();
    }

    /**
     * Gets the password of a user
     * @param username Username bound to the profile
     * @return User's password
     */
    public String getPassword(String username) {
        String password = null;
        setScanner();
        in.useDelimiter(",");

        while (in.hasNext()) {
            if (in.next().equals(username)) {
                password = in.next();
                return password;
            }
            in.nextLine();
        }
        in.close();
        return password;
    }

    /**
     * Get's the user with the lowest overall time
     * @return username with lowest overall time
     */
    public String getCaptain() {
        setScanner();
        in.useDelimiter(",");

        while(in.hasNext()) {
            String fileUser = in.next();
            in.next();
            int totalTime = 0;
            for (int i = 0; i < 5; i++) {
                totalTime += in.nextInt();
            }
            in.nextLine();
            bst.insertUser(fileUser, totalTime);
        }
        in.close();
        return bst.printScore().get(0).getUser();
    }

    /**
     * Creates a scanner to be used throighout the class
     */
    public void setScanner() {
        try {
            this.in = new Scanner(file);
        }
        catch (FileNotFoundException e) {
            System.out.println("Profile source file not found in game files: \n");
            System.out.println("Suggested fix - Redownload game files.");
        }
    }

    /**
     * Deletes an account from profiles.txt. It creates a temp file and copies over
     * all records from profiles.txt which don't have the specified username. The temp
     * file is then rename to Profiles.txt
     * @param username user to be edited in file
     */
	public void deleteAccount(String username) throws IOException {
		 FileWriter fw;
	        BufferedWriter bw;
	        PrintWriter pw = null;

	        String filepath = "./bin/Profiles.txt";
	        String tempFile = "./bin/temp.txt";
	        File oldFile = new File(filepath);
	        File newFile = new File(tempFile);
	        Scanner in = null;
	        String fileUser = "";
	        String filePass = "";
	        int fileLevel = 0;
	        ArrayList<Integer> times = new ArrayList<>();

	        try {
	            fw = new FileWriter(tempFile, true);
	            bw = new BufferedWriter(fw);
	            pw = new PrintWriter(bw);
	            in = new Scanner(file);
	            in.useDelimiter(",");

	        } catch(Exception e) {
	            System.out.println("System Error");
	            System.exit(0);
	        }

	        while (in.hasNext()){
	            fileUser = in.next();
	            if (!fileUser.equals(username)) {
	                filePass = in.next();
	                fileLevel = in.nextInt();
	                while (in.hasNextInt()) {
	                    times.add(in.nextInt());
	                }
	                pw.print(fileUser + "," + filePass + "," + fileLevel + ",");
	                for (int i = 0; i < 5; i++) {
	                    pw.print(times.get(i) + ",");
	                }
	                pw.print("\n");
	            }
	            times.clear();
	            in.nextLine();
	        }
	        in.close();
	        pw.flush();
	        pw.close();
	        oldFile.delete();
	        File dump = new File(filepath);
	        newFile.renameTo(dump);

	}

}