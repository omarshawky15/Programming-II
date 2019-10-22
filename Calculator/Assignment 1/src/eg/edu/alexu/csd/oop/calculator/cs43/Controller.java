package eg.edu.alexu.csd.oop.calculator.cs43;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;

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
	@FXML Menu hist ;
	public Boolean bool = false;
	public int index = 0;
	private Calculator cal = new Calculator();
	private ArrayList<String> Ans;
	private IHistory h = new History();
	private String saveLoc = "default.txt";
	private ArrayList<String> arr = new ArrayList<String>();
	private TextInputDialog tid = new TextInputDialog();

	private void addAns() {
		h.Add("default.txt", Ans);
		arr.addAll(0, Ans);
		index = 0;
		history();
	}

	public void EqualPressed() {
		Ans = new ArrayList<String>();
		String s = tx1.getText();
		String t = cal.equal(s);
		Ans.add(s);
		Ans.add(t);
		l1.setText(s + "=" + t);
		addAns();
		tx1.setText("0");
		bool = false;
	}

	public void AnsPressed() {
		String s = tx1.getText();
		String ans = ((arr.size() == 0) ? "0" : arr.get(index + 1));
		if(ans.equals("Math Error") ||ans.equals("Input Error") )ans = "0";
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

	private String add() {
		tid.setTitle("Add File Name");
		tid.setContentText("Enter File Name (eg:Example.txt)");
		Optional <String> c =tid.showAndWait();
		if(c.isPresent())
		return tid.getEditor().getText();
		else return "";
	}

	public void saveAction() {
		String id = add();
		if(id.equals(""))return;
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
		return;
	}

	public Boolean savePressed(String id) {
		saveLoc = id;
		return h.Save(saveLoc);
	}

	public void quickSavePressed() {
		h.Save(saveLoc);
	}

	public void loadAction() {
		String id = add();
		if(id.equals(""))return;
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
		if (loadPressed(id)) {
			load.getItems().add(m);
			history();
		}
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
		saveLoc = id;
		return !arr.isEmpty();
	}

	public void prePressed() {
		tx1.setText("0");
		bool = false;
		if (index + 2 >= arr.size() || index+2 >= 10)
			return;
		else {
			index += 2;
			l1.setText(arr.get(index) + "=" + arr.get(index + 1));
		}
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
	}

	public void aboutPressed() {

		Alert a = new Alert(AlertType.INFORMATION, "© 2019-2019. All rights reserved To RE Corporation");
		a.setTitle("About");
		a.setHeaderText("");
		a.show();
	}
	public void history () {
		int sz = arr.size();
		ObservableList<MenuItem> e = hist.getItems() ;
		e.clear();
		for( int i =0 ;i<sz && i<10 ; i+=2) {
			MenuItem x = new MenuItem(arr.get(i)+"="+arr.get(i+1));
			x.setId(Integer.toString(i));
			x.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					MenuItem y = (MenuItem)event.getSource();
					index = Integer.parseInt(y.getId());
					l1.setText(y.getText());
				}
				
			});
			e.add(x);
		}
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		File F = new File("default.txt");
		F.delete();
	}

}
