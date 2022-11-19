public class Body {
	private Vector2 position;
	private Vector2 velocity;
	private Vector2 acceleration;
	private double mass;

	public Body(Vector2 position, Vector2 velocity, double mass) {
		setPosition(position);
		setVelocity(velocity);
		setAcceleration(new Vector2(0.0, 0.0));
		this.mass = mass;
	}

	private void setPosition(Vector2 position) {
		this.position = position;
	}

	private void setVelocity(Vector2 velocity) {
		this.velocity = velocity;
	}

	private void setAcceleration(Vector2 acceleration) {
		this.acceleration = acceleration;
	}

	public Vector2 getPosition() {
		return position;
	}

	public double getMass() {
		return mass;
	}

	public void updateAcceleration(
			GravitationCalculator gravitationCalculator) {
		Vector2 gravitation = gravitationCalculator.calculateGravitation(this);
		acceleration = gravitation.divideLambda(mass);
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
			Body body = (Body) object;
			return this.allEquals(body);
		}
	}

	private boolean allEquals(Body body) {
		return this.position.equals(body.position)
			&& this.velocity.equals(body.velocity)
			&& this.acceleration.equals(body.acceleration)
			&& DoubleEqualJudger.doubleEqualWithEpsilon(this.mass, body.mass);
	}

	public void updateVelocity(double deltaTime) {
		velocity.selfAdd(acceleration.multiplyLambda(deltaTime));
	}

	public void updatePosition(double deltaTime) {
		position.selfAdd(velocity.multiplyLambda(deltaTime));
	}
}
