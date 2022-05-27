/**
 * SelectLevel.java - SelectLevel class that allows users to select a level to play
 * @author Group 10 - Pirates of the CoFo
 * @version 1.0.0
 * SelectLevel class as a part of CS-230 Assignment 2
 */

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SelectLevel extends ShowWindow {
    @FXML private Button btnPlay;
    @FXML private Button btnBack;
    @FXML private ListView lstLevel;
    private String username;

    /**
     * Displays the level map for the selected level
     */
    @FXML
    public void play() {
        btnPlay.getScene().getWindow().hide();
        showMap(this.username, lstLevel.getSelectionModel().getSelectedIndex() + 1,
                "./bin/Level_" + (lstLevel.getSelectionModel().getSelectedIndex() + 1) + ".txt");
    }

    /**
     * Returns to the main menu screen
     */
    @FXML
    public void back() {
        btnBack.getScene().getWindow().hide();
        showMainMenu(this.username);
    }

    /**
     * Updates the level selection list
     * @param username Profile's username
     */
    public void updateLevelList(String username) {
        this.username = username;
        Profile p = new Profile();
        ArrayList<Integer> levels = new ArrayList<>();
        for (int i = 1; i <= p.getLevelFromFile(username); i++) {
            levels.add(i);
        }
        lstLevel.setItems(FXCollections.observableArrayList(levels));
    }
}