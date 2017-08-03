package lights;

import maths.Vec2f;
import maths.Vec3f;

public class PointLight extends Light {
	
	private Vec3f position;
	private Vec2f attenuation;
	
	public PointLight(Vec3f position, Vec2f attenuation, Vec3f color, float intensity) {
		super(color, intensity);
		this.position = position;
		this.attenuation = attenuation;
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
