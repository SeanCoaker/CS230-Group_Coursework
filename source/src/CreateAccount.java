/**
 * CreateAccount.java - CreateAccount class that creates a new Profile and adds to the system
 * @author Group 10 - Pirates of the CoFo
 * @version 1.0.0
 * CreateAccount class as a part of CS-230 Assignment 2
 */

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class CreateAccount extends ShowWindow {
    private static final int LOGIN_WIDTH = 750;
    private static final int LOGIN_HEIGHT = 500;
    private static final int MAIN_MENU_WIDTH = 400;
    private static final int MAIN_MENU_HEIGHT = 520;
    private Profile p = new Profile();

    @FXML private Button btnCreate;
    @FXML private Button btnBack;
    @FXML private PasswordField txtPassword;
    @FXML private PasswordField txtConfirm;
    @FXML private TextField txtUsername;

    /**
     * Event handler for when the back button is clicked. Takes user back to
     * login screen.
     * @param event Button click event
     */
    @FXML
    private void back(ActionEvent event) {
        try {
            Pane login1 = (Pane)FXMLLoader.load(getClass().getResource("login.fxml"));
            Scene login = new Scene(login1, LOGIN_WIDTH, LOGIN_HEIGHT);
            Stage newWindow = new Stage();

            btnBack.getScene().getWindow().hide();

            newWindow.setScene(login);
            newWindow.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Event handler for when the user clicks the create button. Adds an
     * account to the profiles file and then takes user to main menu
     * with the username of new account passed to main menu.
     * @param event Button click event
     */
    @FXML
    private void create(ActionEvent event) {
        try {
        	Pane mainMenu1 = (Pane)FXMLLoader.load(getClass().getResource("mainMenu.fxml"));
			Scene mainMenu = new Scene(mainMenu1, MAIN_MENU_WIDTH, MAIN_MENU_HEIGHT);

            if (createCheck(txtUsername.getText(), txtPassword.getText(), txtConfirm.getText())) {
                p.addAccount(txtUsername.getText(), txtPassword.getText());
                btnCreate.getScene().getWindow().hide();
                showMainMenu(txtUsername.getText());
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error: Cannot create account");
                alert.setHeaderText(null);
                alert.setContentText("Login credentials invalid. Please enter valid credentials");
                alert.showAndWait();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Checks if the username is valid and both passwords match
     * @param username The chosen username in the TextField
     * @param password The chosen password in the PasswordField
     * @param confirm The Password field for confirmation
     * @return Whether or not the credentials are valid
     */
    private boolean createCheck(String username, String password , String confirm) {
        if (!username.isEmpty() && !password.isEmpty() && password.equals(confirm)) {
            if (!p.existAccount(username)) {
                return true;
            }
        }
        return false;
    }
}