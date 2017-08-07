package lights;

import maths.Vec2f;
import maths.Vec3f;
import utilities.HurtEngineException;

public class PointLight extends Light {
	
	private static final Vec2f[] ATTENUATION_VALUES = {
		new Vec2f(0.7f, 	1.8f),
		new Vec2f(0.35f, 	0.44f),
		new Vec2f(0.22f, 	0.2f),
		new Vec2f(0.14f, 	0.07f),
		new Vec2f(0.09f, 	0.032f),
		new Vec2f(0.07f, 	0.017f),
		new Vec2f(0.045f, 	0.0075f),
		new Vec2f(0.027f, 	0.0028f),
		new Vec2f(0.022f,	0.0019f),
		new Vec2f(0.014f, 	0.0007f)
	};
	
	private Vec3f position;
	private Vec2f attenuation;
	
	public PointLight(Vec3f position, int attenuation, Vec3f color, float intensity, boolean isOn) {
		super(color, intensity, isOn);
		this.position = position;
		setAttenuation(attenuation);
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
	
	public void setAttenuation(int attenuation){
		if(attenuation < 1 || attenuation > 10){
			throw new HurtEngineException("Attenuation value outside the range 1-10");
		}else{
			this.attenuation = ATTENUATION_VALUES[attenuation - 1];
		}
	}
	
}
