package application;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.application.Platform;
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
	public int index = 0;
	private Calculator cal = new Calculator();
	private ArrayList<String> Ans;
	private History h = new History();
	private String saveLoc = "default.txt";
	private ArrayList<String> arr = new ArrayList<String>();

	private void addAns() {
		h.Add("default.txt", Ans);
		arr.addAll(0,Ans);
		index = 0;
		System.out.println(index);
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
		System.out.println(index);
		String ans = ((arr.size() == 0) ? "0" : arr.get(index +1));
		if (bool)
			tx1.setText(s + ans);
		else {
			tx1.setText(ans);
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
		if (savePressed(id))
			save.getItems().add(m);
	}

	public Boolean savePressed(String id) {
		saveLoc = id;
		return h.Save(saveLoc);
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
		if (loadPressed(id))
			load.getItems().add(m);
	}

	public Boolean loadPressed(String id) {
		tx1.setText("0");
		bool = false;
		arr = h.Load(id);
		if (arr.isEmpty()) {
			index = -2;
			l1.setText("");
		} else {
			index = 0;
			l1.setText(arr.get(0) + "=" + arr.get(1));
		}
		return !arr.isEmpty();
	}

	public void prePressed() {
		tx1.setText("0");
		bool = false;
		if (index + 2 >= arr.size())
			return;
		else {
			index += 2;
			l1.setText(arr.get(index) + "=" + arr.get(index + 1));
		}
		System.out.println(index);
	}

	public void nextPressed() {
		tx1.setText("0");
		bool = false;
		if (index - 2 < 0)
			return;
		else {
			index -= 2;
			l1.setText(arr.get(index) + "=" + arr.get(index + 1));
		}
		System.out.println(index);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		File F = new File("default.txt");
		F.delete();
	}

}
