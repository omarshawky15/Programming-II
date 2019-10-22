package eg.edu.alexu.csd.oop.calculator.cs43;

import java.io.BufferedWriter;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class History implements IHistory {
	/* (non-Javadoc)
	 * @see application.IHistory#Add(java.lang.String, java.util.ArrayList)
	 */
	@Override
	public Boolean Add(String destination, ArrayList<String> s) {
		ArrayList<String> arr = new ArrayList<String>();
		arr.addAll(s);
		try {
			File F = new File(destination);
			arr.addAll(read(destination));
			F.delete();
			write(destination, arr);
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	/* (non-Javadoc)
	 * @see application.IHistory#Load(java.lang.String)
	 */
	@Override
	public ArrayList<String> Load(String destination) {
		ArrayList<String> arr = new ArrayList<String>();
		arr = read(destination);
		File F = new File("default.txt");
		F.delete();
		write("default.txt", arr);
		return arr;
	}

	/* (non-Javadoc)
	 * @see application.IHistory#Save(java.lang.String)
	 */
	@Override
	public Boolean Save(String destination) {
		ArrayList<String> arr = new ArrayList<String>();
		arr = read("default.txt");
		return write(destination, arr);
	}

	private ArrayList<String> read(String destination) {
		ArrayList<String> arr = new ArrayList<String>();
		try {
			File F = new File(destination);
			F.createNewFile();
			FileReader fr = new FileReader(F);
			BufferedReader br = new BufferedReader(fr);
			String temp = br.readLine();
			while (temp != null) {
				arr.add(temp);
				temp = br.readLine();
			}
			br.close();
		} catch (Exception e) {
			new Alert(AlertType.ERROR, "Error , Invalid File").show();
		}
		return arr;
	}

	private Boolean write(String destination, ArrayList<String> arr) {
		try {
			File F = new File(destination);
			F.createNewFile();
			FileWriter fw = new FileWriter(F);
			BufferedWriter bw = new BufferedWriter(fw);
			int sz = arr.size();
			if ((int)sz == 0) {
				bw.close();
				return false;
			}
			bw.append(arr.get(0));
			for (int i = 1; i < sz && i < 10; i++) {
				bw.append(System.lineSeparator());
				bw.append(arr.get(i));
			}
			bw.close();
		} catch (Exception e) {
			new Alert(AlertType.ERROR, "Error , Invalid File").show();
			return false;
		}
		return true;
	}

}
