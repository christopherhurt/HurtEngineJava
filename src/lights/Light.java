package lights;

import maths.Vec3f;

public abstract class Light {
	
	private Vec3f color;
	private float intensity;
	private boolean isOn;
	
	public Light(Vec3f color, float intensity, boolean isOn) {
		this.color = color;
		this.intensity = intensity;
		this.isOn = isOn;
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
	
	public boolean isOn(){
		return isOn;
	}
	
	public void toggleOn(boolean isOn){
		this.isOn = isOn;
	}
	
}
