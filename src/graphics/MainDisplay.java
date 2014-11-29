package graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import boids.Agent;

public class MainDisplay extends JPanel implements MouseListener {
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		MainDisplay m = new MainDisplay();

		frame.setSize(640, 480);
		frame.setContentPane(m);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		m.addMouseListener(m);
	}

	private List<Agent> boids;

	public MainDisplay() {
		// Init agents!
		boids = new ArrayList<Agent>();
		Random r = new Random();
		for (int i = 0; i < 500; i++) {
			boids.add(new Agent(r.nextInt(640), r.nextInt(480)));
		}

		// Logic
		new Timer().scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				for (Agent a : boids) {
					a.calculate(boids);
					a.move();
				}
			}
		}, 0, 10);
		// Display
		new Timer().scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				repaint();
			}
		}, 0, 30);
	}

	@Override
	public void paint(Graphics g) {
		g.clearRect(0, 0, getWidth(), getHeight());
		for (Agent a : boids) {
			int x = (int) a.getX();
			int y = (int) a.getY();
			g.setColor(Color.RED);
			g.drawLine(x, y, a.getGoalX(), a.getGoalY());
			g.setColor(Color.BLACK);
			g.drawOval(x - 10, y - 10, 20, 20);
			g.setColor(Color.GREEN);
			g.drawLine(x, y, (int) (a.getX() + a.getDx() * 10),
					(int) (a.getY() + a.getDy() * 10));
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Random r = new Random();
		for (Agent a : boids) {
			a.setGoal(r.nextInt(getWidth()), r.nextInt(getHeight()));
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}
