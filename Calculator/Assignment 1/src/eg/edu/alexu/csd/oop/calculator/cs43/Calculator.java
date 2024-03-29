package eg.edu.alexu.csd.oop.calculator.cs43;

public class Calculator implements Calculators {
	public double add(double a, double b) {
		return a + b;
	}

	@Override
	public double subtract(double a, double b) {
		return a - b;
	}

	@Override
	public double divide(double a, double b) {
		double c;
		try {
			c = a / b;
			if(Double.isNaN(c))throw new ArithmeticException();
		} catch (ArithmeticException e) {
			return Double.POSITIVE_INFINITY;
		}

		return c;
	}

	@Override
	public double multiply(double a, double b) {
		return a * b;
	}

	public static void main(String[] args) {
	}

	@Override
	public String equal(String s) {
		try {
			String[] st = s.split("[ + * // - ]+");
			if(!(st.length==2))return "Input Error";
			String sa = st[0];
			double a = Double.parseDouble(sa);
			sa = st[1];
			double b = Double.parseDouble(sa);
			double c;
			int i = 0;
			while ((s.charAt(i) < 58 && s.charAt(i) > 47) || s.charAt(i) == '.' || s.charAt(i) == ' ')
				i++;
			sa = s.charAt(i) + "";
			if (sa.equals("+"))
				c = add(a, b);
			else if (sa.equals("-"))
				c = subtract(a, b);
			else if (sa.equals("*"))
				c = multiply(a, b);
			else if (sa.equals("/"))
				c = divide(a, b);
			else
				return "Error,Operation not defined";
			if (c == Double.POSITIVE_INFINITY)
				return "Math Error";
			else
				return Double.toString(c);
		} catch (Exception e) {
			return "Input Error";
		}
	}
}

