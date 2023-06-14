module BloodBankProject {
	requires javafx.controls;
	requires javafx.fxml;
	requires java.sql;
	requires java.base;
    requires transitive javafx.graphics;
	
	opens BloodBankProject to javafx.graphics, javafx.fxml, javafx.base;
    
	exports BloodBankProject;
}
