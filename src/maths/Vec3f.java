package maths;

public class Vec3f {
	
	private float x;
	private float y;
	private float z;
	
	public Vec3f(float x, float y, float z){
		this.x = x;
		this.y = y;
		this.z = z;
	}
	// TODO: all methods return new Vec3f????
	public Vec3f scale(float c){
		return new Vec3f(c * x, c * y, c * z);
	}
	
	public float length(){
		return (float) Math.sqrt(x * x + y * y + z * z);
	}
	
	public Vec3f normalized(){
		float length = length();
		return new Vec3f(x / length, y / length, z / length);
	}
	
	public float dot(Vec3f vec){
		return this.x * vec.x + this.y * vec.y + this.z * vec.z;
	}
	
	public Vec3f cross(Vec3f vec){
		float crossX = 	 this.y * vec.z - vec.y * this.z;
		float crossY = -(this.x * vec.z - vec.x * this.z);
		float crossZ =   this.x * vec.y - vec.x * this.y;
		
		return new Vec3f(crossX, crossY, crossZ);
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
	
}
