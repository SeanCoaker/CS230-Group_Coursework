/**
 * Main.java - Main class that launches the JavaFX program
 * @author Group 10 - Pirates of the CoFo
 * @version 1.0.0
 * Main class as a part of CS-230 Assignment 2
 */

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.File;

public class Main extends Application {

	private static final double MUSIC_VOLUME = 0.03;
	private static final double MUSIC_START = 37.7;
	private static final int SPLASH_WIDTH = 250;
	private static final int SPLASH_HEIGHT = 150;
	private static final int LOGIN_WIDTH = 750;
	private static final int LOGIN_HEIGHT = 500;
	private static final int PAUSE_DURATION = 4;

	// Artist - Ross Bugden - Music
	// Artist Link - https://www.youtube.com/channel/UCQKGLOK2FqmVgVwYferltKQ
	// Music Provided By Notchback Music (NBM)
	Media backgroundMusic = new Media(new File("./bin/PirateCrew.mp3").toURI().toString());
	MediaPlayer mediaPlayer = new MediaPlayer(backgroundMusic);

	public void start(Stage primaryStage) {

		// Load the custom font
		Font.loadFont(getClass().getResourceAsStream("SketchBones.ttf"), 10);
		Font.loadFont(getClass().getResourceAsStream("Treamd.ttf"), 10);

		try {

			// Create splash screen
			Pane splash1 = (Pane)FXMLLoader.load(getClass().getResource("splash.fxml"));
			Scene splash = new Scene(splash1, SPLASH_WIDTH, SPLASH_HEIGHT);

			// Create login screen
			Pane login1 = (Pane)FXMLLoader.load(getClass().getResource("login.fxml"));
			Scene login = new Scene(login1, LOGIN_WIDTH, LOGIN_HEIGHT);

			// Set stage as splash screen
			primaryStage.initStyle(StageStyle.UNDECORATED);
			primaryStage.setScene(splash);
			primaryStage.show();

			// Create new stage and set as login screen
			Stage loginStage = new Stage();
			loginStage.setScene(login);
			loginStage.initStyle(StageStyle.DECORATED);

			// Creates a PauseTransition to display the splash screen for
			// 4 seconds before hiding it and displaying the login screen
			PauseTransition pause = new PauseTransition(Duration.seconds(PAUSE_DURATION));
			pause.setOnFinished(e -> {
				primaryStage.hide();
				loginStage.show();

				startMusic();

				});

			pause.play();

		} catch(Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Plays background music after setting the volume and start time
	 */
	private void startMusic() {

		mediaPlayer.setVolume(MUSIC_VOLUME);
		mediaPlayer.setStartTime(Duration.seconds(MUSIC_START));
		mediaPlayer.play();
	}

	public static void main(String[] args) {
		launch(args);
	}

}