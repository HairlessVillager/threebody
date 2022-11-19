import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame {
	private GameManager gameManager = new GameManager();
	private final int windowWidth = 1080;
	private final int windowHeight = 720;
	private final int radiusOfBody = 20;
	private int counter = 0;

	public void launch() {
		this.setVisible(true);
		this.setSize(windowWidth, windowHeight);
		this.setLocationRelativeTo(null);
		this.setTitle(ThreeBody.getName());
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		while(true) {
			gameManager.updateAllBody();
			repaint();
			try {
				Thread.sleep(100);
			} catch(InterruptedException ex) {
				ex.printStackTrace();
			}
			counter++;
		}
	}

	public void paint(Graphics g) {
		g.setColor(Color.red);
		Body[] bodies = gameManager.getBodies();

		g.clearRect(0, 0, windowWidth, windowHeight);
		for(Body body : bodies) {
			Vector2 position = body.getPosition();
			g.fillOval((int)position.getX() + windowWidth / 2 - radiusOfBody, (int)position.getY() + windowHeight / 2 - radiusOfBody, 2 * radiusOfBody, 2 * radiusOfBody);
		}
	}
}
