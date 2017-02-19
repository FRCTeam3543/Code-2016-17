package org.usfirst.frc3543.Team3543Robot.util.unused;

public class XYPoint {
	public double x = 0.0;
	public double y = 0.0;		
	
	public XYPoint() {
		
	}
	
	public XYPoint(double xx, double yy) {
		this();
		this.x = xx;
		this.y = yy;
	}
	
	public double distance(XYPoint to) {
		return Math.sqrt(Math.pow(this.x - to.x, 2) + Math.pow(this.y - to.y, 2));
	}
	
	public XYPoint dup() {
		return new XYPoint(this.x, this.y);
	}
	
	public XYPoint add(double x, double y) {
		this.x += x;
		this.y += y;
		return this;
	}
	
	public XYPoint mult(double scalar) {
		this.x *= scalar;
		this.y *= scalar;
		return this;
	}
	
	public XYPoint add(double scalar) {
		this.x += scalar;
		this.y += scalar;
		return this;
	}
	
	public XYPoint add(XYPoint p) {
		this.x += p.x;
		this.y += p.y;
		return this;
	}
	
	@Override
	public String toString() {
		return String.format("%.3f\t%.3f", this.x, this.y);
	}
}