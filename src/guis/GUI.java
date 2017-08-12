package guis;

import maths.Mat4f;
import maths.Vec2f;
import maths.Vec3f;
import meshes.Meshes;
import renderEngine.Material;
import renderEngine.Model;
import renderEngine.RenderObject;

public class GUI extends RenderObject {
	
	private static final GUIQuad GUI_MESH = new GUIQuad();
	
	private Vec2f pos;
	private Vec3f rot;
	private Vec3f scale;
	
	public GUI(String textureFile, Vec2f position, Vec2f dimensions) {
		super(new Model(GUI_MESH, new Material(textureFile)));
		pos = new Vec2f(position.getX(), position.getY());
		rot = new Vec3f(0, 0, 0);
		scale = new Vec3f(dimensions.getX(), dimensions.getY(), 1);
	}
	
	public Mat4f transformation(){
		float width = scale.getX();
		float height = scale.getY();
		Vec3f position = new Vec3f(pos.getX() + width / 2, pos.getY() + height / 2, 0);
		
		return Mat4f.transform(position, rot, scale);
	}
	
	public Vec2f getPosition(){
		return new Vec2f(pos.getX(), pos.getY());
	}
	
	public void setPosition(Vec2f position){
		pos.setX(position.getX());
		pos.setY(position.getY());
	}
	
	public Vec2f getDimensions(){
		return new Vec2f(scale.getX(), scale.getY());
	}
	
	public void setDimensions(Vec2f dimensions){
		scale.setX(dimensions.getX());
		scale.setY(dimensions.getY());
	}
	
}
