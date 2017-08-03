package lights;

import maths.Vec3f;

public abstract class Light { // TODO: add controller for on/off
	
	private Vec3f color;
	private float intensity;
	
	public Light(Vec3f color, float intensity) {
		this.color = color;
		this.intensity = intensity;
	}
	
	public Vec3f getColor() {
		return color;
	}
	
	public void setColor(Vec3f color) {
		this.color = color;
	}
	
	public float getIntensity() {
		return intensity;
	}
	
	public void setIntensity(float intensity) {
		this.intensity = intensity;
	}
	
}
