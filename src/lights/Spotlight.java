package lights;

import maths.Vec3f;

public class Spotlight extends Light {
	
	private Vec3f position;
	private Vec3f direction;
	private float cosineCutoff;
	
	public Spotlight(Vec3f position, Vec3f direction, float cutoffAngle, Vec3f color, float intensity, boolean isOn) {
		super(color, intensity, isOn);
		this.position = position;
		this.direction = direction;
		setCutoffAngle(cutoffAngle);
		LightController.addLight(this);
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
		return (float) Math.toDegrees(Math.acos(cosineCutoff));
	}
	
	public void setCutoffAngle(float cutoffAngle) {
		this.cosineCutoff = (float) Math.cos(Math.toRadians(cutoffAngle));
	}
	
}
