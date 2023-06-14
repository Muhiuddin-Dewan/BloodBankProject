package BloodBankProject;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class becomeDonor {
	 @FXML
	    private TextField nameField;

	    @FXML
	    private TextField bloodGroupField;

	    @FXML
	    private TextField addressField;

	    @FXML
	    private TextField mobileNumberField;

	    @FXML
	    public void submitForm() throws IOException {
	        String name = nameField.getText();
	        String bloodGroup = bloodGroupField.getText();
	        String address = addressField.getText();
	        String mobileNumber = mobileNumberField.getText();

	        // Insert the values into the database
	        insertIntoDatabase(name, bloodGroup, address, mobileNumber);

	        // Clear the form fields after submission
	        nameField.clear();
	        bloodGroupField.clear();
	        addressField.clear();
	        mobileNumberField.clear();
	    }
	    
	    private void insertIntoDatabase(String name, String bloodGroup, String address, String mobileNumber) throws IOException {
	        try {
	            // Establish a database connection
	            Connection connection = DriverManager.getConnection("jdbc:mysql://du-iit.cfwuk3um9mdt.us-east-1.rds.amazonaws.com:3306/bloodbank", "root", "12345678");

	            // Prepare the SQL statement
	            String sql = "INSERT INTO donors (name, blood_group, address, mobile_number) VALUES (?, ?, ?, ?)";
	            PreparedStatement statement = connection.prepareStatement(sql);
	            statement.setString(1, name);
	            statement.setString(2, bloodGroup);
	            statement.setString(3, address);
	            statement.setString(4, mobileNumber);

	            // Execute the statement
	            statement.executeUpdate();

	            // Close the connection and statement
	            statement.close();
	            connection.close();

	            System.out.println("Record inserted successfully.");
	            FXMLLoader loader = new FXMLLoader(getClass().getResource("userDashBoard.fxml"));
	            Pane root = loader.load();
	            UserDashBoard dashboard = loader.getController(); 
	            dashboard.initialize(); 
	            Scene scene = new Scene(root, 1000, 800);
	            Stage primaryStage = (Stage) nameField.getScene().getWindow();
	            primaryStage.setScene(scene);
	        } 
	        catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
}
