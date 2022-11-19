public class GravitationCalculator {
	private Body[] bodies;
	private double universalGravitationalConstant;

	public GravitationCalculator(Body[] bodies, double universalGravitationalConstant) {
		this.bodies = bodies;
		this.universalGravitationalConstant = universalGravitationalConstant;
	}

	public Vector2 calculateGravitation(Body exertedBody) {
		Vector2 gravitation = new Vector2(0.0, 0.0);
		for(Body exertingBody : bodies) {
			if(!exertedBody.equals(exertingBody)) {
				gravitation.selfAdd(calculateGravitationFrom(exertedBody, exertingBody));
			}
		}
		return gravitation;
	}

	private Vector2 calculateGravitationFrom(Body exertedBody, Body exertingBody) {
		Vector2 displacement = Vector2.subtract(
			exertingBody.getPosition(),
			exertedBody.getPosition());
		return displacement.getNormal().multiplyLambda(
				universalGravitationalConstant
				* exertedBody.getMass()
				* exertingBody.getMass()
				/ displacement.getLength()
				/ displacement.getLength());
	}
}
