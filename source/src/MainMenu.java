/**
 * MainMenu.java - MainMenu class that allows users to advance to other screens
 * @author Group 10 - Pirates of the CoFo
 * @version 1.0.0
 * MainMenu class as a part of CS-230 Assignment 2
 */

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

public class MainMenu extends ShowWindow {

	private static final int LOGIN_WIDTH = 750;
	private static final int LOGIN_HEIGHT = 500;
	@FXML private Button btnQuit;
	@FXML private Button btnLeaderboards;
	@FXML private Button btnSelectLevel;
	@FXML private Button btnContinue;
	@FXML private Button btnDelete;
	@FXML private Label lblUsername;

	private Profile p = new Profile();

	/**
	 * Leaves the current screen and goes back to the login screen for
	 * another user to login.
	 */
	@FXML
	private void quit() {
		try {
			Pane login1 = (Pane)FXMLLoader.load(getClass().getResource("login.fxml"));
			Scene login = new Scene(login1, LOGIN_WIDTH, LOGIN_HEIGHT);
			Stage newWindow = new Stage();

			btnQuit.getScene().getWindow().hide();

			newWindow.setScene(login);
			newWindow.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Displays the leaderboards screen
	 */
	@FXML
	public void leaderboards() {
		btnLeaderboards.getScene().getWindow().hide();
		showLeaderboard(lblUsername.getText());
	}

	/**
	 * Displays the map
	 */
	@FXML
	public void showMap() {
		btnContinue.getScene().getWindow().hide();
		String user = lblUsername.getText();
		File file = new File("./bin/" + user + "0.txt");
		if(!file.exists()) {
			showMap(user, p.getLevelFromFile(user),
					"./bin/Level_" + p.getLevelFromFile(user) + ".txt");
		} else {
			showMap(user, p.getLevelFromFile(user),
					"./bin/" + user + "0.txt");
		}

	}

	/**
	 * Displays the selectLevel screen
	 */
	@FXML
	public void selectLevel() {
		btnSelectLevel.getScene().getWindow().hide();
		showLevelSelect(lblUsername.getText());
	}

	/**
	 * Shows the user's username at the top of the screen
	 * @param user username to be shown
	 */
	public void setLabel(String user) {
		this.lblUsername.setText(user);
	}

	/**
	 * Deletes the profile we're currently logged into from profiles.txt
	 */
	public void delete() throws IOException {
		p.deleteAccount(this.lblUsername.getText());
		quit();
	}
}
