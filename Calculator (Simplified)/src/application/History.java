package application;

import java.io.BufferedWriter;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class History {
	public ArrayList<String> Add(String destination, ArrayList<String> s) {
		ArrayList<String> arr = new ArrayList<String>();
		arr.addAll(s);
		try {
			File F = new File(destination);
			arr.addAll(read(destination));
			F.delete();
			write(destination,arr);
			return arr;
		} catch (Exception e) {
			return arr;
		}

	}

	public ArrayList<String> Load(String destination) {
		ArrayList<String> arr = new ArrayList<String>();
		try {
			arr=read(destination);
			File F = new File("default.txt");
			F.delete();
			write("default.txt",arr);
			return arr;
		} catch (Exception e) {
			return arr;
		}

	}

	public ArrayList<String> Save(String destination) {
		ArrayList<String> arr = new ArrayList<String>();
		try {
			arr = read("default.txt");
			write(destination,arr);
			return arr;
		} catch (Exception e) {
			return arr;
		}

	}

	private ArrayList<String> read (String destination){
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
		}
		catch (Exception e) {return arr;}
		return arr;
	}
	private ArrayList<String> write (String destination, ArrayList<String> arr){
		try {
			File F = new File(destination);
			F.createNewFile();
			FileWriter fw = new FileWriter(F);
			BufferedWriter bw = new BufferedWriter(fw);
			int sz = arr.size();
			bw.append(arr.get(0));
			for (int i = 1; i < sz && i < 10; i++) {
				bw.append(System.lineSeparator());
				bw.append(arr.get(i));
			}
			bw.close();
		}
		catch (Exception e) {return arr;}
		return arr;
	}

	/*public static void main(String[] args) {
		String[] s = { "3+3", "6" };
		History h = new History();
		ArrayList<String> arr = h.Add("1.txt", s);
		for (int i = 0; i < arr.size(); i++)
			System.out.println(arr.get(i));
		s[0] = "2+2";
		s[1] = "4";
		arr = h.Add("1.txt", s);
		for (int i = 0; i < arr.size(); i++)
			System.out.println(arr.get(i));
	}*/

}
