public class GameManager {
	private double deltaTime = 0.05;
	private double universalGravitationalConstant = 20000.0;
	private double timeSinceStart;
	private Body[] bodies;
	private GravitationCalculator gravitationCalculator;

	public GameManager() {
		timeSinceStart = 0.0;
		bodies = DataGenerator.get8ShapedStableStructure();
		gravitationCalculator = new GravitationCalculator(bodies, universalGravitationalConstant);
	}

	public Body[] getBodies() {
		return bodies;
	}

	public void updateAllBody() {
		updateAllAcceleration();
		updateAllVelocity();
		updateAllPosition();
	}

	private void updateAllAcceleration() {
		for(Body body : bodies) {
			body.updateAcceleration(gravitationCalculator);
		}
	}

	private void updateAllVelocity() {
		for(Body body : bodies) {
			body.updateVelocity(deltaTime);
		}
	}

	private void updateAllPosition() {
		for(Body body : bodies) {
			body.updatePosition(deltaTime);
		}
	}
}
