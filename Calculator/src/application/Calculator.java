package application;

public class Calculator implements Calculators {
	public double add (double a , double b) {
		return a+b ;
	}
	@Override
	public double subtract(double a, double b) {
		return a-b;
	}
	@Override
	public double divide(double a, double b) {
		double c;
		try {c = a/b ;}
		catch(ArithmeticException e) {
			return Double.POSITIVE_INFINITY;
		}
		
		return c;
	}
	@Override
	public double multiply(double a, double b) {
		return a*b;
	}
	public static void main(String[] args) {
	}

}
