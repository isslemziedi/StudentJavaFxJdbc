module JavaFxJdbc {

	requires javafx.controls;
	requires javafx.fxml;
	requires java.sql;

	opens application to javafx.base, javafx.graphics, javafx.fxml;
	exports application;
}
