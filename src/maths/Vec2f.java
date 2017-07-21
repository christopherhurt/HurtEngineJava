package maths;

public class Vec2f {
	
	private float x;
	private float y;
	
	public Vec2f(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public float length(){
		return (float) Math.sqrt(x * x + y * y);
	}
	
	public float dot(Vec2f vec){
		return this.x * vec.x + this.y * vec.y;
	}
	
	public float getAngleBetween(Vec2f vec){
		float dot = this.dot(vec);
		float lengths = this.length() * vec.length();
		return (float) Math.acos(dot / lengths);
	}
	
	public float getX() {
		return x;
	}
	
	public void setX(float x) {
		this.x = x;
	}
	
	public float getY() {
		return y;
	}
	
	public void setY(float y) {
		this.y = y;
	}
	
}
