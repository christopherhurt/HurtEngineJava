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
	
	public float determinant(Vec2f vec){
		return this.x * vec.y - this.y * vec.x;
	}
	
	public void normalize(){
		float length = length();
		
		x = x / length;
		y = y / length;
	}
	
	public Vec2f normalized(){
		Vec2f normalizedVector = new Vec2f(x, y);
		normalizedVector.normalize();
		return normalizedVector;
	}
	
	public float getAngleBetween(Vec2f vec){
		float dot = this.dot(vec);
		float lengths = this.length() * vec.length();
		return (float) Math.toDegrees(Math.acos(dot / lengths));
	}
	
	public Vec2f sub(Vec2f vec){
		return new Vec2f(this.x - vec.x, this.y - vec.y);
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
