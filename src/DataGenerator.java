public class DataGenerator {
	public static Body[] get8ShapedStableStructure() {
		double mass = 16.0;
		Body[] bodies = new Body[3];
		bodies[0] = new Body(
				new Vector2(194.000872, -48.617506),
				new Vector2(18.6481474, 17.2946292),
				mass);
		bodies[1] = new Body(
				new Vector2(-194.000872, 48.617506),
				new Vector2(18.6481474, 17.2946292),
				mass);
		bodies[2] = new Body(
				new Vector2(0.0, 0.0),
				new Vector2(-37.2962948, -34.5892584),
				mass);
		return bodies;
	}
}
