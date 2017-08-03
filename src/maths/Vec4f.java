package maths;

public class Vec4f {
	
	private float x;
	private float y;
	private float z;
	private float w;
	
	public Vec4f(float x, float y, float z, float w) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = w;
	}
	
	public float length(){
		return (float) Math.sqrt(x * x + y * y + z * z + w * w);
	}
	
	public float dot(Vec4f vec){
		return this.x * vec.x + this.y * vec.y + this.z * vec.z + this.w * vec.w;
	}
	
	public Vec4f add(Vec4f vec){
		return new Vec4f(this.x + vec.x, this.y + vec.y, this.z + vec.z, this.w + vec.w);
	}
	
	public Vec4f sub(Vec4f vec){
		return new Vec4f(this.x - vec.x, this.y - vec.y, this.z - vec.z, this.w - vec.w);
	}
	
	public Vec4f scale(float c){
		x = c * x;
		y = c * y;
		z = c * z;
		w = c * w;
		
		return this;
	}
	
	public Vec4f scaled(float c){
		return new Vec4f(c * x, c * y, c * z, c * w);
	}
	
	public void normalize(){
		float length = length();
		
		x = x / length;
		y = y / length;
		z = z / length;
		w = w / length;
	}
	
	public Vec4f normalized(){
		Vec4f normalizedVector = new Vec4f(x, y, z, w);
		normalizedVector.normalize();
		return normalizedVector;
	}
	
	public float getAngleBetween(Vec4f vec){
		float dot = this.dot(vec);
		float lengths = this.length() * vec.length();
		return (float) Math.toDegrees(Math.acos(dot / lengths));
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
	
	public float getZ() {
		return z;
	}
	
	public void setZ(float z) {
		this.z = z;
	}
	
	public float getW() {
		return w;
	}
	
	public void setW(float w) {
		this.w = w;
	}
	
}
