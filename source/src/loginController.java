/**
 * loginController.java - loginController that controlls the features of the login screen
 * @author Group 10 - Pirates of the CoFo
 * @version 1.0.0
 * loginController class as a part of CS-230 Assignment 2
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Alert;

public class loginController extends ShowWindow implements Initializable {

	private static final int CREATE_ACCOUNT_WIDTH = 350;
	private static final int CREATE_ACCOUNT_HEIGHT = 300;
	private static final int TRANSITION_TIME = 1;
	private static final double TRANSITION_FROM_X = 1.0;
	private static final double TRANSITION_BY_X = 0.3;
	private static final double TRANSITION_BY_Y = 0.0;
	@FXML private Button btnPlay;
	@FXML private Button btnCreate;
	@FXML private Button btnRefresh;
	@FXML private Label motd;
	@FXML private ComboBox<String> cmbUsername = new ComboBox<String>();
	@FXML private PasswordField txtPassword = new PasswordField();


	/**
	 * Event handler for when the login button is clicked. Checks if password
	 * is matched with selected username, if so it allows login and
	 * opening of main menu window. Otherwise, an alert window is shown
	 * @param event Button click event
	 */
	@FXML
	private void login(ActionEvent event) {
		Profile p = new Profile();

		// Checks if the username field is not unselected
		if (cmbUsername.getValue() != null && !cmbUsername.getValue().isEmpty() &&
				!cmbUsername.getValue().equals("SELECT_USERNAME")) {
			// Checks if the password is correct for the selected username
			if (txtPassword.getText().equals(p.getPassword(cmbUsername.getValue()))) {
				btnPlay.getScene().getWindow().hide();
				showMainMenu(cmbUsername.getValue());
			} else {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("Error: Incorrect Password");
				alert.setHeaderText(null);
				alert.setContentText("Password is incorrect, please try again or create"
						+ " an account");
				alert.showAndWait();
			}
		} else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Error: Username not selected");
			alert.setHeaderText(null);
			alert.setContentText("Please select a username");
			alert.showAndWait();
		}
	}

	/**
	 * Action event for refreshing the message of the day
	 * @param event Refresh button click event
	 */
	@FXML
	private void refreshMOTD(ActionEvent event) {
		motd.setWrapText(true);
		motd.setText(solvePuzzle());

		ScaleTransition st = new ScaleTransition(Duration.seconds(TRANSITION_TIME), motd);
		st.setFromX(TRANSITION_FROM_X);
		st.setByX(TRANSITION_BY_X);
		st.setByY(TRANSITION_BY_Y);
		st.setCycleCount(Timeline.INDEFINITE);
		st.setAutoReverse(true);
		st.play();
		motd.setText(solvePuzzle());
	}

	/**
	 * Solves the puzzle for the MOTD
	 * @return The solved puzzle
	 */
	private String solvePuzzle() {
		String input;
		String sol = "";
		StringBuilder content;

		try {
			URL url = new URL("http://cswebcat.swan.ac.uk/puzzle");
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");

			try {
				BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
				content = new StringBuilder();

				while ((input = in.readLine()) != null) {
					content.append(input);
					content.append(System.lineSeparator());

					for (int i = 0; i < input.length(); i++) {
						if (i % 2 == 0) {
							char x = input.charAt(i);
							if (x == 'Z') {
								x = 'A';
							} else {
								x++;
							}
							sol = sol + x;
						} else {
							char x = input.charAt(i);
							if (x == 'A') {
								x = 'Z';
							} else {
								x--;
							}
							sol= sol+x;
						}

					}
				}
			} finally {

			}

			return getMOTD(sol);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Gets the message of the day
	 * @param sol The solution to the puzzle
	 * @return The message of the day
	 */
	private String getMOTD(String sol) {
		String input;
		StringBuilder content;

		try {
			URL url = new URL("http://cswebcat.swan.ac.uk/message?solution=" + sol);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");

			try {
				BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
				content = new StringBuilder();

				while ((input = in.readLine()) != null) {
					content.append(input);
					content.append(System.lineSeparator());
					return content.toString();
				}
			} finally {

			}
		}  catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Override's the initialize method in the Initializable class.
	// Method is run on login scene start up to populate combo box with usernames.
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		updateUsers();
		refreshMOTD(null);
	}

	/**
	 * Updates the users in the ComboBox
	 */
	public void updateUsers() {
		cmbUsername.getItems().clear();
		Profile p = new Profile();
		cmbUsername.setItems(FXCollections.observableArrayList(p.getUsers()));
	}

	/**
	 * Event handler for when the Create Account button is clicked. Opens the
	 * create account window.
	 * @param event Button click event
	 */
	@FXML
	private void createAccount(ActionEvent event) {
		try {
			Pane createAccount1 = (Pane)FXMLLoader.load(getClass().getResource("create.fxml"));
			Scene createAccount = new Scene(createAccount1, CREATE_ACCOUNT_WIDTH, CREATE_ACCOUNT_HEIGHT);
			Stage newWindow = new Stage();
			btnCreate.getScene().getWindow().hide();
			newWindow.setScene(createAccount);
			newWindow.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
