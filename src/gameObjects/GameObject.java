package gameObjects;

import maths.Mat4f;
import maths.Vec3f;
import renderEngine.Model;
import renderEngine.RenderObject;

public class GameObject extends RenderObject {
	
	private Vec3f pos;
	private Vec3f rot;
	private Vec3f scale;
	
	public GameObject(Model model, Vec3f pos, Vec3f rot, Vec3f scale) {
		super(model);
		this.pos = pos;
		this.rot = rot;
		this.scale = scale;
	}
	
	public Mat4f transformation(){
		return Mat4f.transform(pos, rot, scale);
	}
	
	public Vec3f getPos() {
		return pos;
	}
	
	public void setPos(Vec3f pos) {
		this.pos = pos;
	}
	
	public Vec3f getRot() {
		return rot;
	}
	
	public void setRot(Vec3f rot) {
		this.rot = rot;
	}
	
	public Vec3f getScale() {
		return scale;
	}
	
	public void setScale(Vec3f scale) {
		this.scale = scale;
	}
	
}
