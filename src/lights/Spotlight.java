package lights;

import maths.Vec3f;

public class Spotlight extends Light {
	
	private Vec3f position;
	private Vec3f direction;
	private float cutoffAngle; // TODO: change to store cosine of cutoff angle
	
	public Spotlight(Vec3f position, Vec3f direction, float cutoffAngle, Vec3f color, float intensity) {
		super(color, intensity);
		this.position = position;
		this.direction = direction;
		this.cutoffAngle = cutoffAngle;
	}
	
	public Vec3f getPosition() {
		return position;
	}
	
	public void setPosition(Vec3f position) {
		this.position = position;
	}
	
	public Vec3f getDirection() {
		return direction;
	}
	
	public void setDirection(Vec3f direction) {
		this.direction = direction;
	}
	
	public float getCutoffAngle() {
		return cutoffAngle;
	}
	
	public void setCutoffAngle(float cutoffAngle) {
		this.cutoffAngle = cutoffAngle;
	}
	
}
