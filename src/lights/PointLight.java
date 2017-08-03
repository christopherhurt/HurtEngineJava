package lights;

import maths.Vec2f;
import maths.Vec3f;

public class PointLight extends Light {
	
	private Vec3f position;
	private Vec2f attenuation; // TODO: change to standardized float value?
	
	public PointLight(Vec3f position, Vec2f attenuation, Vec3f color, float intensity, boolean isOn) {
		super(color, intensity, isOn);
		this.position = position;
		this.attenuation = attenuation;
		LightController.addLight(this);
	}
	
	public Vec3f getPosition(){
		return position;
	}
	
	public void setPosition(Vec3f position){
		this.position = position;
	}
	
	public Vec2f getAttenuation(){
		return attenuation;
	}
	
	public void setAttenuation(Vec2f attenuation){
		this.attenuation = attenuation;
	}
	
}
