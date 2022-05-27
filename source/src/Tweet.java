/**
 * Tweet.java - Tweet class that tweets to @treasur73043621 when a user gets a new highscore
 * @author Group 10 - Pirates of the CoFo
 * @version 1.0.0
 * Tweet class as a part of CS-230 Assignment 2
 */

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javafx.stage.Stage;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

public class Tweet {
	private Twitter twitter;
    private List<Status> statuses;
	public Tweet() {
		twitter = TwitterFactory.getSingleton();
		statuses = new ArrayList<Status>();
	}

	 /**
	 * Sets up the tweet with the contents
	 * @param username Player's username
	 * @param highscore The new highscore
	 * @param level The level the highscore was achieved on
	 * @throws TwitterException
	 */
	public void tweetSetup(String username, int highscore, int level) throws TwitterException {
		DecimalFormat df = new DecimalFormat("00");

	    Tweet tweet = new Tweet();
		String playerName = username;

		String tweetContent = playerName + " has reached a new highscore of " + df.format(highscore / 60) + ":" + df.format(highscore & 60) +
				" on level " + level + "! Wow!";

		tweet.sendTweet(tweetContent);

	}

    /**
     * Sends the tweet
     * @param tweetContent The contents of the tweet
     * @throws TwitterException
     */
    public void sendTweet(String tweetContent) throws TwitterException {
    	Status tweetStatus = twitter.updateStatus(tweetContent);
    	// Twitter account link - https://twitter.com/treasur73043621
    }
}