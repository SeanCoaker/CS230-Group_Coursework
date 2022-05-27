/**
 * Leaderboards.java - Leaderboards that displays ladders of players in order of top scores
 * @author Group 10 - Pirates of the CoFo
 * @version 1.0.0
 * Leaderboards class as a part of CS-230 Assignment 2
 */

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Leaderboards extends ShowWindow implements Initializable {

	@FXML private Button btnBack;
	@FXML private TableView tblLeaderboards;
	@FXML private TableColumn userCol;
	@FXML private TableColumn hsCol;
	@FXML private Label lblCaptain;
	@FXML private ComboBox cmbLevel;
	private String username;
	private Profile p = new Profile();

	/**
	 * Event handler for when the back button is clicked. Takes user back
	 * to main menu
	 * @param event Button click event
	 */
	@FXML
	private void back(ActionEvent event) {
		btnBack.getScene().getWindow().hide();
		showMainMenu(this.username);
	}

	/**
	 * Sets the username
	 * @param username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Upon initialization of this window a combo box is filled with levels
	 * from 1 to 10 and then we retrieve the captain(best player) and display
	 * it's username.
	 * @param url
	 * @param rb
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		ArrayList<Integer> levels = new ArrayList<>();
		for (int i = 1; i <= 5; i++) {
			levels.add(i);
		}
		cmbLevel.setItems(FXCollections.observableArrayList(levels));
		lblCaptain.setText(p.getCaptain());
	}

	/**
	 * Fills the table with names and scores based on the level selected
	 * by the user in the combo box.
	 * @param event Action event
	 */
	public void fillLeaderboard(ActionEvent event) {
		Profile p = new Profile();
		ObservableList<BSTNode> leaderList = FXCollections.observableArrayList
				(p.getLeaderboard(cmbLevel.getSelectionModel().getSelectedIndex() + 1));
		userCol.setCellValueFactory(new PropertyValueFactory<BSTNode, String>("user"));
		hsCol.setCellValueFactory(new PropertyValueFactory<BSTNode, Integer>("score"));
		tblLeaderboards.getColumns().setAll(userCol, hsCol);
		tblLeaderboards.setItems(leaderList);
	}

}
