package boids;

import java.awt.Point;

public class Vector {
	public double x;
	public double y;

	public Vector() {
		x = 0;
		y = 0;
	}

	public Vector(double ix, double iy) {
		x = ix;
		y = iy;
	}

	public void normalize(double factor) {
		if (x != 0 && y != 0) {
			double normFactor = Point.distance(0, 0, x, y);
			x = factor * x / normFactor;
			y = factor * y / normFactor;
		}
	}
}
