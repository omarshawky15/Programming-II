package eg.edu.alexu.csd.oop.calculator.cs43;
import java.util.ArrayList;

public interface IHistory {

	Boolean Add(String destination, ArrayList<String> s);

	ArrayList<String> Load(String destination);

	Boolean Save(String destination);

}