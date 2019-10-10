package application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Controller implements Initializable {
	@FXML
	public TextField tx1;
	@FXML
	public Label l1;
	public Calculator cal = new Calculator();
	public void EqualPressed() {
		String s = tx1.getText();
		l1.setText(tx1.getText()+"="+cal.equal(s));
		tx1.setText("0");
	}

	public void buttonPressed(Event e) {
		Button b = (Button) e.getSource();
		String s = b.getId();
		System.out.println(s);
		if (tx1.getText().charAt(0) == '0')
			tx1.setText(s);

		else
			tx1.setText(tx1.getText() + s);
	}
	public void ACPressed () {
		tx1.setText("0");
	}
	public void delPressed () {
		String s = tx1.getText();
		int sz = s.length()-1;
		if(sz ==0)tx1.setText("0");
		else 
		tx1.setText(s.substring(0,sz));
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}

}
