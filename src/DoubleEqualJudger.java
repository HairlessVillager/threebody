public class DoubleEqualJudger {
	private static double DEFAULT_EPSILON = 1e-5;
	private double epsilon;

	public DoubleEqualJudger() {
		this.epsilon = DEFAULT_EPSILON;
	}

	public DoubleEqualJudger(double epsilon) {
		this.epsilon = epsilon;
	}

	public static boolean doubleEqualWithEpsilon(double doubleA, double doubleB, double epsilon) {
		return Math.abs(doubleA - doubleB) < epsilon;
	}

	public static boolean doubleEqualWithEpsilon(double doubleA, double doubleB) {
		return Math.abs(doubleA - doubleB) < DEFAULT_EPSILON;
	}
}
