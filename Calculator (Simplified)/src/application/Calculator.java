package application;

import java.util.Arrays;

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
			//System.out.println(s);
			String[] st = s.split("[ + * // - ]");
			String sa = st[0];
			double a = Double.parseDouble(sa);
			//System.out.println(sa);
			sa = st[1];
			//System.out.println(sa);
			double b = Double.parseDouble(sa);
			double c;
			int i = 0;
			while ((s.charAt(i) < 58 && s.charAt(i) > 47) || s.charAt(i) == '.' || s.charAt(i) == ' ')
				i++;
			sa = s.charAt(i) + "";
			//System.out.println(sa);
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
