package BloodBankProject;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDashBoard {
	@FXML
	private Pane rootPane;

	@FXML
	private TableView<User> tableView;

	@FXML
	private TableColumn<User, Integer> idColumn;

	@FXML
	private TableColumn<User, String> nameColumn;

	@FXML
	private TableColumn<User, String> bloodGroupColumn;

	@FXML
	private TableColumn<User, String> addressColumn;

	@FXML
	private TableColumn<User, String> mobileNumberColumn;
		
	@FXML
	public void initialize() {
	    // Set up table columns
	    idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
	    nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
	    bloodGroupColumn.setCellValueFactory(new PropertyValueFactory<>("bloodGroup"));
	    addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
	    mobileNumberColumn.setCellValueFactory(new PropertyValueFactory<>("mobileNumber"));

	    // Load user data from the database and populate the table
	    loadUserData();
	}
	
	@FXML
	public void home() {
	    try {
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("BloodBankProject.fxml"));
	        Pane root = loader.load();
	        Scene scene = new Scene(root, 600, 600);
	        Stage primaryStage = (Stage) rootPane.getScene().getWindow();
	        primaryStage.setScene(scene);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	private void loadUserData() {
	    // SQL query to select user data
	    String query = "SELECT * FROM donors";

	    try {
	        // Establish a database connection
	    	Connection connection = DriverManager.getConnection("jdbc:mysql://du-iit.cfwuk3um9mdt.us-east-1.rds.amazonaws.com:3306/bloodbank", "root", "12345678");

	        // Prepare the SQL statement
	        PreparedStatement statement = connection.prepareStatement(query);

	        // Execute the query and get the result set
	        ResultSet resultSet = statement.executeQuery();

	        // Clear existing table data
	        tableView.getItems().clear();

	        // Populate the table with user data
	        while (resultSet.next()) {
	            int id = resultSet.getInt("ID");
	            String name = resultSet.getString("name");
	            String bloodGroup = resultSet.getString("blood_group");
	            String address = resultSet.getString("address");
	            String mobileNumber = resultSet.getString("mobile_number");

	            User user = new User(id, name, bloodGroup, address, mobileNumber);
	            tableView.getItems().add(user);
	        }

	        // Close the database resources
	        resultSet.close();
	        statement.close();
	        connection.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

}
