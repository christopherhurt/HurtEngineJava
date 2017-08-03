package lights;

import maths.Vec3f;

public class DirectionalLight extends Light {
	
	private Vec3f direction;
	
	public DirectionalLight(Vec3f direction, Vec3f color, float intensity, boolean isOn){
		super(color, intensity, isOn);
		this.direction = direction;
		LightController.addLight(this);
	}
	
	public Vec3f getDirection(){
		return direction;
	}
	
	public void setDirection(Vec3f direction){
		this.direction = direction;
	}
	
}
