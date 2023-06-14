package BloodBankProject;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BloodBankProject {

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;


    @FXML
    private void login() throws IOException {
        String email = emailField.getText();
        String password = passwordField.getText();

        // Validate the login credentials
        boolean isValid = validateLogin(email, password);

        if (isValid) {
            // Perform the necessary actions upon successful login
            System.out.println("Login successful!");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("userDashBoard.fxml"));
            Pane root = loader.load();
            UserDashBoard donor = loader.getController(); // Get the controller instance
            donor.initialize();
            Scene scene = new Scene(root, 1000, 800);
            Stage primaryStage = (Stage) emailField.getScene().getWindow();
            primaryStage.setScene(scene);
        } else {
            // Show an error message for invalid credentials
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login Error");
            alert.setHeaderText(null);
            alert.setContentText("Invalid email or password. Please try again.");
            alert.showAndWait();
        }
    }
    
    @FXML
    private void donorList() throws IOException {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("userDashBoard.fxml"));
            Pane root = loader.load();
            UserDashBoard donor = loader.getController(); // Get the controller instance
            donor.initialize();
            Scene scene = new Scene(root, 1000, 800);
            Stage primaryStage = (Stage) emailField.getScene().getWindow();
            primaryStage.setScene(scene);
    }
    
    @FXML
    private void bcmdnr() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("becomeDonor.fxml"));
        Pane root = loader.load();
        Scene scene = new Scene(root, 600, 400);
        Stage primaryStage = (Stage) emailField.getScene().getWindow();
        primaryStage.setScene(scene);
    }


    private boolean validateLogin(String email, String password) {
        try {
            // Connect to the MySQL database
            Connection connection = DriverManager.getConnection("jdbc:mysql://du-iit.cfwuk3um9mdt.us-east-1.rds.amazonaws.com:3306/bloodbank", "root", "12345678");

            // Prepare the SQL statement
            String sql = "SELECT * FROM admin WHERE email = ? AND password = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, email);
            statement.setString(2, password);

            // Execute the statement and check if any rows are returned
            ResultSet resultSet = statement.executeQuery();
            boolean isValid = resultSet.next();

            // Close the connection, statement, and result set
            resultSet.close();
            statement.close();
            connection.close();

            return isValid;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}