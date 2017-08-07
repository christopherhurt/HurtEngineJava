package lights;

import maths.Vec3f;

public class Spotlight extends Light {
	
	private Vec3f position;
	private Vec3f direction;
	private float cosineInnerCutoff;
	private float cosineOuterCutoff;
	
	public Spotlight(Vec3f position, Vec3f direction, float innerConeAngle, float outerConeAngle, Vec3f color, float intensity, boolean isOn) {
		super(color, intensity, isOn);
		this.position = position;
		this.direction = direction;
		setInnerConeAngle(innerConeAngle);
		setOuterConeAngle(outerConeAngle);
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
	
	public float getCosineInnerCutoff(){
		return cosineInnerCutoff;
	}
	
	public void setCosineInnerCutoff(float cosineInnerCutoff){
		this.cosineInnerCutoff = cosineInnerCutoff;
	}
	
	public float getCosineOuterCutoff(){
		return cosineOuterCutoff;
	}
	
	public void setCosineOuterCutoff(float cosineOuterCutoff){
		this.cosineOuterCutoff = cosineOuterCutoff;
	}
	
	public float getInnerConeAngle() {
		return (float) Math.toDegrees(Math.acos(cosineInnerCutoff));
	}
	
	public void setInnerConeAngle(float innerConeAngle) {
		this.cosineInnerCutoff = (float) Math.cos(Math.toRadians(innerConeAngle));
	}
	
	public float getOuterConeAngle() {
		return (float) Math.toDegrees(Math.acos(cosineOuterCutoff));
	}
	
	public void setOuterConeAngle(float outerConeAngle) {
		this.cosineOuterCutoff = (float) Math.cos(Math.toRadians(outerConeAngle));
	}
	
}
