public class Vector2 {
	private double x;
	private double y;

	public Vector2(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	@Override
	public boolean equals(Object object) {
		if(this == object) {
			return true;
		} else if(object == null) {
			return false;
		} else if(this.getClass() != object.getClass()) {
			return false;
		} else {
			Vector2 vector2 = (Vector2) object;
			return this.allEquals(vector2);
		}
	}

	private boolean allEquals(Vector2 vector2) {
		return DoubleEqualJudger.doubleEqualWithEpsilon(this.x, vector2.x)
			&& DoubleEqualJudger.doubleEqualWithEpsilon(this.y, vector2.y);
	}

	public static Vector2 add(Vector2 vector2A, Vector2 vector2B) {
		double resultX = vector2A.x + vector2B.x;
		double resultY = vector2A.y + vector2B.y;
		return new Vector2(resultX, resultY);
	}
	public Vector2 add(Vector2 addedVector2) {
		double resultX = this.x + addedVector2.x;
		double resultY = this.y + addedVector2.y;
		return new Vector2(resultX, resultY);
	}
	public Vector2 selfAdd(Vector2 addedVector2) {
		this.x += addedVector2.x;
		this.y += addedVector2.y;
		return this;
	}

	public static Vector2 subtract(Vector2 vector2A, Vector2 vector2B) {
		double resultX = vector2A.x - vector2B.x;
		double resultY = vector2A.y - vector2B.y;
		return new Vector2(resultX, resultY);
	}
	public Vector2 subtract(Vector2 subtractedVector2) {
		double resultX = this.x - subtractedVector2.x;
		double resultY = this.y - subtractedVector2.y;
		return new Vector2(resultX, resultY);
	}
	public Vector2 selfSubtract(Vector2 subtractedVector2) {
		this.x -= subtractedVector2.x;
		this.y -= subtractedVector2.y;
		return this;
	}

	public Vector2 multiplyLambda(double lambda) {
		double resultX = this.x * lambda;
		double resultY = this.y * lambda;
		return new Vector2(resultX, resultY);
	}

	public Vector2 divideLambda(double lambda) {
		double resultX = this.x / lambda;
		double resultY = this.y / lambda;
		return new Vector2(resultX, resultY);
	}

	public static double dot(Vector2 vector2A, Vector2 vector2B) {
		return vector2A.x * vector2B.x + vector2A.y * vector2B.y;
	}

	public double getLength() {
		return Math.sqrt(x*x + y*y);
	}

	public Vector2 getNormal() {
		double resultX = this.x / this.getLength();
		double resultY = this.y / this.getLength();
		return new Vector2(resultX, resultY);
	}

	public Vector2 selfNormal() {
		this.x /= this.getLength();
		this.y /= this.getLength();
		return this;
	}
}
