package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;

public class Controller implements Initializable {
	@FXML
	public Label tx1;
	String[] sym = {"SUBTRACT","ADD" , "MULTIPLY" , "DIVIDE"};
	public void numberPressed(KeyEvent e) {
		KeyCombination keys =new KeyCodeCombination(KeyCode.DIGIT9, KeyCombination.SHIFT_ANY);
		String s = e.getCode().getName();
		System.out.println(s);
		Boolean b = false;
		if (e.getCode().isDigitKey()) {
			b = true;
		} else {
			int sz = sym.length;
			for (int i = 0; i < sz; i++) {
				if (s.equals(sym[i])) {
					b = true;
					break;
				}
			}
		}
		if (b) {
			String stx = tx1.getText();
			if (stx.equals("0"))
				tx1.setText(s+"");
			else
				tx1.setText(stx+s);
		} else
			return;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}

}
