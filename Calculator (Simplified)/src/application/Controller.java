package application;

import java.net.URL;
import java.util.ArrayList;
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
	public Boolean bool = false;
	public Calculator cal = new Calculator();
	public ArrayList<String> Ans ;
	History h = new History();
	public String saveLoc = "default.txt";
	private void addAns () {
		h.Add("default.txt",Ans);
	}
	public void EqualPressed() {
		Ans= new ArrayList<String>();
		String s = tx1.getText();
		Ans.add( s ) ;
		Ans.add( cal.equal(s) ) ;
		l1.setText(s + "=" + Ans.get(1));
		if(Ans.get(1).equals("Math Error")||Ans.get(1).equals("Input Error"))Ans.set(1,"0");
		addAns();
		tx1.setText("0");
	}
	public void AnsPressed () {tx1.setText(tx1.getText()+Ans.get(1));}
	public void buttonPressed(Event e) {
		Button b = (Button) e.getSource();
		String s = b.getId();
		System.out.println(s);
		if (tx1.getText().charAt(0) == '0')
			tx1.setText(s);

		else
			tx1.setText(tx1.getText() + s);
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

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}

}
