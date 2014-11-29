package boids;

import java.awt.Point;
import java.util.List;

public class Agent {
	private double x;
	private double y;

	private Vector velocity;
	private Vector g_v;
	private Vector s_v;

	private double seperationFactor_a = 500;
	private double seperationFactor_b = 100000;
	private double seperationDistance = 25;
	private double goalFactor = 0.001;

	// The target the agent is trying to get to
	private double gx;
	private double gy;

	public Agent(int ix, int iy) {
		x = ix;
		y = iy;

		gx = x;
		gy = y;

		velocity = new Vector();
		g_v = new Vector();
		s_v = new Vector();
	}

	public void setGoal(int ibx, int iby) {
		gx = ibx;
		gy = iby;
	}

	public int getGoalX() {
		return (int) gx;
	}

	public int getGoalY() {
		return (int) gy;
	}

	public void calculate(List<Agent> allAgents) {
		// The seperation target
		s_v.x = 0;
		s_v.y = 0;

		for (Agent a : allAgents) {
			// Don't calculate anything based on yourself!
			if (a.equals(this)) {
				continue;
			}

			double x2 = a.getX();
			double y2 = a.getY();
			double distance = Point.distance(x, y, x2, y2);

			if (distance < seperationDistance) {
				s_v.x += (x - a.getX())
						/ ((distance / seperationFactor_a + Math.pow(distance,
								3) / seperationFactor_b));
				s_v.y += (y - a.getY())
						/ ((distance / seperationFactor_a + Math.pow(distance,
								3) / seperationFactor_b));
			}
		}

		s_v.x /= allAgents.size() - 1;
		s_v.y /= allAgents.size() - 1;

		// Calculate the final velocity from a combination of the factors
		// Goal vector
		g_v.x = gx - x;
		g_v.y = gy - y;
		// Sum vector
		velocity.x = s_v.x + goalFactor * g_v.x;
		velocity.y = s_v.y + goalFactor * g_v.y;
		// Normalize the velocity so the agents have a max speed
		velocity.normalize(1);
	}

	public void move() {
		x += velocity.x;
		y += velocity.y;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getDx() {
		return velocity.x;
	}

	public double getDy() {
		return velocity.y;
	}
}
