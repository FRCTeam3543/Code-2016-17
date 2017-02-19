package org.usfirst.frc3543.Team3543Robot.util.unused;

/**
 * Tool for calculating a Cubic Bezier
 * 
 * Useful for motion planning, except that it doesn't include orientations
 * 
 * Adapted from http://devmag.org.za/2011/04/05/bzier-curves-a-tutorial/
 * 
 * @author MK
 *
 */
public class CubicBezierPathCalculator extends PathCalculator {

	XYPoint point1;
	XYPoint controlPoint1;
	
	XYPoint point2;
	XYPoint controlPoint2;
	
	public CubicBezierPathCalculator(XYPoint p1, XYPoint controlPoint1, XYPoint controlPoint2, XYPoint p2) {
		this.point1 = p1;
		this.point2 = p2;
		this.controlPoint1 = controlPoint1;
		this.controlPoint2 = controlPoint2;
	}
	
	/**
	 * 
	 * @param t
	 * @param p0 - first point
	 * @param p1 - first control point
	 * @param p2 - second control point
	 * @param p3 - second point
	 * @return point at t
	 */
	XYPoint calculatePoint(double t, XYPoint p0, XYPoint p1, XYPoint p2, XYPoint p3) {
		double u = 1 - t;
		double tt = t*t;
		double uu = u*u;
		double uuu = uu * u;
		double ttt = tt * t;
		
		return p0.dup()
					.mult(uuu) //first term
					.add(p1.dup().mult(t * uu * 3))
					.add(p2.dup().mult(tt * u * 3))
					.add(p3.dup().mult(ttt));
		
//		p += 3 * uu * t * p1; //second term
//		p += 3 * u * tt * p2; //third term
//		p += ttt * p3; //fourth term
		 
//		return p;
	}
	
	public void calculate(XYPoint[] curve) {
		double t = 1 / (double) curve.length;
		for (int i=0; i<curve.length; i++) {
			curve[i] = calculatePoint(t * i, this.point1, this.controlPoint1, this.controlPoint2, this.point2);
		}		
	}
	
	public static void main(String [] args) {
		if (args.length < 8) {
			System.err.println("You need to pass 8 args: x1, y1, cp1x, cp1y, cp2x, cp2y, x2, y2");
		}
		else {
			XYPoint p0 = new XYPoint(Double.parseDouble(args[0]), Double.parseDouble(args[1]));
			XYPoint p1 = new XYPoint(Double.parseDouble(args[2]), Double.parseDouble(args[3]));
			XYPoint p2 = new XYPoint(Double.parseDouble(args[4]), Double.parseDouble(args[5]));
			XYPoint p3 = new XYPoint(Double.parseDouble(args[6]), Double.parseDouble(args[7]));
			int steps = 20;
			if (args.length > 8) {
				steps = Integer.parseInt(args[8]);
			}
			CubicBezierPathCalculator calc = new CubicBezierPathCalculator(p0, p1, p2, p3);
			XYPoint[] output = new XYPoint[steps];
			calc.calculate(output);
			for (int i=0; i<output.length; i++) {
				System.out.println(output[i]);
			}
		}
	}
}
