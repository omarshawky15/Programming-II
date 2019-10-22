package eg.edu.alexu.csd.oop.calculator.cs43;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Main extends Application {
	@Override
	public void start(Stage stage)throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("CalculatorGui.fxml"));
		Scene sc1 = new Scene(root);
		stage.setScene(sc1);
		stage.setResizable(false);
		stage.setMinHeight(400);stage.setMinWidth(350);
		stage.setTitle("RE Calculator");
		setUserAgentStylesheet(STYLESHEET_CASPIAN);
		stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}