package application;
	
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
		stage.setMinHeight(480);stage.setMinWidth(360);
		//lw shoft dyh yb2a lazm tt3lm 2zay t5lyk f7alk 
		stage.setTitle("5ra");
		stage.show();
		setUserAgentStylesheet(STYLESHEET_CASPIAN);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
