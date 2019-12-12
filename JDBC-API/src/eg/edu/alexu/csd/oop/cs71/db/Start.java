package eg.edu.alexu.csd.oop.cs71.db;

import com.sun.xml.internal.ws.policy.EffectiveAlternativeSelector;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.util.Arrays;


public class Start extends Application {

    public static void main (String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("SQL Database");
        stage.setWidth(300);
        stage.setHeight(60);
        stage.setResizable(false);
        TextField textField =new TextField();
        HBox hBox = new HBox();

        Button commandLineInterface =new Button("Command Line Interface");
        Button GUIinterface =new Button("GUI Interface");

        hBox.getChildren().addAll(commandLineInterface,GUIinterface);
        hBox.setSpacing(50);
        commandLineInterface.setOnAction(e->{
            String[] arguments = new String[] {"123"};
            stage.close();
            try
            {
                Runtime.getRuntime().exec("cmd /c start cmd.exe /K \"java -cp Database-Management-System.jar eg.edu.alexu.csd.oop.cs71.db.CLI");
            }
            catch (Exception er)
            {
                System.out.println("HEY Buddy ! U r Doing Something Wrong ");
                er.printStackTrace();
            }
        });
        GUIinterface.setOnAction(e->{
            String[] arguments = new String[] {"123"};
            stage.close();
            Gui gui = new Gui();
            gui.start(new Stage());
        });
        Scene scene = new Scene(hBox);
        stage.setScene(scene);
        stage.show();
    }
}