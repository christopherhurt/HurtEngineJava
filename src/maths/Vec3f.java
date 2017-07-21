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
	
	public Vec3f scale(float c){
		x = c * x;
		y = c * y;
		z = c * z;
		
		return this;
	}
	
	public float length(){
		return (float) Math.sqrt(x * x + y * y + z * z);
	}
	
	public void setLength(float length){
		normalize();
		x *= length;
		y *= length;
		z *= length;
	}
	
	// TODO: return this for each???
	public void normalize(){
		float length = length();
		
		x = x / length;
		y = y / length;
		z = z / length;
	}
	
	// TODO: two normalize methods??
	public Vec3f normalized(){
		Vec3f normalizedVector = new Vec3f(x, y, z);
		normalizedVector.normalize();
		return normalizedVector;
	}
	
	// TODO: add TO here or create new Vec3f????
	public Vec3f add(Vec3f vec){
		return new Vec3f(this.x + vec.x, this.y + vec.y, this.z + vec.z);
	}
	
	public Vec3f sub(Vec3f vec){
		return new Vec3f(this.x - vec.x, this.y - vec.y, this.z - vec.z);
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
	
	public void rotate(float theta, Vec3f axis){
		float sinHalfAngle = (float) Math.sin(Math.toRadians(theta / 2));
		float cosHalfAngle = (float) Math.cos(Math.toRadians(theta / 2));
		
		float rX = axis.getX() * sinHalfAngle;
		float rY = axis.getY() * sinHalfAngle;
		float rZ = axis.getZ() * sinHalfAngle;
		float rW = cosHalfAngle;
		
		Quaternion rotation = new Quaternion(rX, rY, rZ, rW);
		Quaternion conjugate = rotation.conjugate();
		Quaternion w = rotation.mul(this).mul(conjugate);
		
		x = w.getX();
		y = w.getY();
		z = w.getZ();
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
	
	private class Quaternion extends Vec4f {

		public Quaternion(float x, float y, float z, float w) {
			super(x, y, z, w);
		}
		
		public Quaternion mul(Quaternion quat){
			float qX =  this.getX() * quat.getW() + this.getY() * quat.getZ() - this.getZ() * quat.getY() + this.getW() * quat.getX();
			float qY = -this.getX() * quat.getZ() + this.getY() * quat.getW() + this.getZ() * quat.getX() + this.getW() * quat.getY();
			float qZ = 	this.getX() * quat.getY() - this.getY() * quat.getX() + this.getZ() * quat.getW() + this.getW() * quat.getZ();
			float qW = -this.getX() * quat.getX() - this.getY() * quat.getY() - this.getZ() * quat.getZ() + this.getW() * quat.getW();
			
			return new Quaternion(qX, qY, qZ, qW);
		}

		public Quaternion mul(Vec3f vec){
			float qW = -this.getX() * vec.getX() - this.getY() * vec.getY() - this.getZ() * vec.getZ();
			float qX = this.getW() * vec.getX() + this.getY() * vec.getZ() - this.getZ() * vec.getY();
			float qY = this.getW() * vec.getY() + this.getZ() * vec.getX() - this.getX() * vec.getZ();
			float qZ = this.getW() * vec.getZ() + this.getX() * vec.getY() - this.getY() * vec.getX();
			
			return new Quaternion(qX, qY, qZ, qW);
		}

		public Quaternion conjugate(){
			return new Quaternion(-this.getX(), -this.getY(), -this.getZ(), this.getW());
		}

	}
	
}

