package application;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;

public class Controller implements Initializable {
	@FXML
	public TextField tx1;
	@FXML
	public Label l1;
	@FXML
	public Menu save;
	@FXML
	public TextField saveText;
	@FXML
	public Menu load;
	@FXML
	public TextField loadText;
	public Boolean bool = false;
	private Calculator cal = new Calculator();
	private ArrayList<String> Ans;
	private History h = new History();
	private String saveLoc = "default.txt";

	private void addAns() {
		h.Add("default.txt", Ans);
	}

	public void EqualPressed() {
		Ans = new ArrayList<String>();
		String s = tx1.getText();
		String t = cal.equal(s);
		Ans.add(s);
		Ans.add(t);
		l1.setText(s + "=" + t);
		if (t.equals("Math Error") || t.equals("Input Error"))
			Ans.set(1, "0");
		addAns();
		tx1.setText("0");
		bool = false;
	}

	public void AnsPressed() {
		String s = tx1.getText();
		if (bool)
			tx1.setText(tx1.getText() + Ans.get(1));
		else {
			tx1.setText(Ans.get(1));
			bool = true;
		}
	}

	public void buttonPressed(Event e) {
		Button b = (Button) e.getSource();
		String s = b.getId();
		System.out.println(s);
		if (bool)
			tx1.setText(tx1.getText() + s);
		else
			tx1.setText(s);
		bool = true;

	}

	public void ACPressed() {
		tx1.setText("0");
		bool = false;
	}

	public void delPressed() {
		String s = tx1.getText();
		int sz = s.length() - 1;
		if (sz == 0) {
			bool = false;
			tx1.setText("0");
		} else
			tx1.setText(s.substring(0, sz));
	}

	public void closePressed() {
		Platform.exit();
	}

	public void saveTextAction(ActionEvent e) {
		TextField tf = (TextField) e.getSource();
		String id = tf.getText();
		tf.setText("");
		MenuItem m = new MenuItem();
		m.setText(id);
		m.setId(id);
		m.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				MenuItem x = (MenuItem) event.getSource();
				String i = x.getId();
				savePressed(i);
			}
		});
		save.getItems().add(m);
		savePressed(id);
	}

	public void savePressed(String id) {
		saveLoc = id;
		h.Save(saveLoc);
	}

	public void loadTextAction(ActionEvent e) {
		TextField tf = (TextField) e.getSource();
		String id = tf.getText();
		tf.setText("");
		MenuItem m = new MenuItem();
		m.setText(id);
		m.setId(id);
		m.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				MenuItem x = (MenuItem) event.getSource();
				String i = x.getId();
				loadPressed(i);
			}
		});
		load.getItems().add(m);
		loadPressed(id);
	}

	public void loadPressed(String id) {
		h.Load(id);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		File F = new File("default.txt");
		try {
			F.delete();
		} catch (Exception e) {
		}
		;
	}

}
