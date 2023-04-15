package si.csp;

public class Constraint {

	public final String X;
	public final String Y;
	public final String condition;

	public Constraint(String a, String b, String c) {
		X = a;
		Y = b;
		condition = c;
	}

	public Boolean test(Integer a, Integer b) {
		Boolean result = false;
		switch (condition) {
		case "==":
			result = a == b;
			break;
		case "!=":
			result = a != b;
			break;
		case ">":
			result = a > b;
			break;
		case ">=":
			result = a >= b;
			break;
		case "<":
			result = a < b;
			break;
		case "<=":
			result = a <= b;
			break;
		}
		return result;
	}

}
