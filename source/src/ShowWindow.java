/**
 * ShowWindow.java - ShowWindow is a superclass which supplies its subclasses
 * with methods for displaying certain windows and passing data into those windows.
 * @author Group 10 - Pirates of the CoFo
 * @version 1.0.0
 * ShowWindow class as a part of CS-230 Assignment 2
 */

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class ShowWindow {

    private Stage stage = new Stage(StageStyle.DECORATED);

    /**
     * Displays select level and shows a list of levels from 1 to the level
     * reached by the username passed in
     * @param username The username to be passed into select level
     * @return The selectLevel stage
     */
    public void showLevelSelect(String username) {
        FXMLLoader loader =  new FXMLLoader(
                getClass().getResource("selectLevel.fxml")
        );

        try {
            stage.setScene(new Scene((Pane) loader.load()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        SelectLevel controller = loader.<SelectLevel>getController();
        controller.updateLevelList(username);

        stage.show();
    }

    /**
     * Displays the leaderboard and carries the username of the user into the
     * leaderboard. The reason for this is so the username can be passed back
     * to the main menu with ease.
     * @param username The username passed into leaderboard
     * @return The leaderboard stage
     */
    public void showLeaderboard(String username) {
        FXMLLoader loader =  new FXMLLoader(
                getClass().getResource("leaderboards.fxml")
        );

        try {
            stage.setScene(new Scene((Pane) loader.load()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Leaderboards controller = loader.<Leaderboards>getController();
        controller.setUsername(username);

        stage.show();
    }

    /**
     * Displays the map and carries the username and level to be played into the map.
     * @param username The username and level to be passed into map
     * @return The map stage
     */
    public void showMap(String username, int level, String filePath) {
        Map m = new Map(username, level, filePath);
        Stage primaryStage = new Stage();
        m.start(primaryStage);
    }

    /**
     * Displays the main menu and carries the username into the main menu
     * @param username The username to be passed into main menu
     * @return The main menu stage
     */
    public Stage showMainMenu(String username) {
        FXMLLoader loader =  new FXMLLoader(
                getClass().getResource("mainMenu.fxml")
        );
        Stage stage = new Stage(StageStyle.DECORATED);

        try {
            stage.setScene(new Scene((Pane) loader.load()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        MainMenu controller = loader.<MainMenu>getController();
        controller.setLabel(username);

        stage.show();

        return stage;
    }

}
