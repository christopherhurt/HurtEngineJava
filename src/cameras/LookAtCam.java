package cameras;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import maths.Mat4f;
import maths.Vec3f;

public class LookAtCam extends Camera {
	
	private static final Vec3f Y_AXIS = new Vec3f(0, 1, 0);
	
	private Vec3f viewPos;
	private Vec3f direction;
	private Vec3f up;
	
	public LookAtCam(Vec3f viewPos, float distance, float pitch, float yaw, float fov, float nearPlane, float farPlane){
		super(fov, nearPlane, farPlane);
		this.viewPos = viewPos;
		direction = new Vec3f(0, 0, 1);
		up = new Vec3f(0, 1, 0);
		pitch(pitch);
		rotate(yaw);
		direction.setLength(distance);
	}
	
	@Override
	public Mat4f view() {
		Vec3f right = up.cross(direction);
		right.normalize();
		return Mat4f.view(viewPos.add(direction), right, up, direction.normalized());
	}
	
	private void rotateX(float theta){
		Vec3f horizAxis = Y_AXIS.cross(direction);
		horizAxis.normalize();
		
		direction.rotate(theta, horizAxis);
		
		up = direction.cross(horizAxis);
		up.normalize();
	}
	
	private void rotateY(float theta){
		Vec3f horizAxis = direction.cross(Y_AXIS);
		horizAxis.normalize();
		
		direction.rotate(theta, Y_AXIS);
		
		up = horizAxis.cross(direction);
		up.normalize();
	}
	
	public void move(Vec3f dPos){
		viewPos = viewPos.add(dPos);
	}
	
	public void zoom(float distance){
		direction.setLength(direction.length() - distance);
	}
	
	public void pitch(float angle){
		rotateX(-angle);
	}
	
	public void rotate(float angle){
		rotateY(angle);
	}
	
	// TODO: TEMP?
	@Override
	public void update(){
		float speed = 0.07f;
		float rotation = 1f;
		float zoom = 3;
		
		if(Keyboard.isKeyDown(Keyboard.KEY_W))
			move(new Vec3f(0, 0, -speed));
		if(Keyboard.isKeyDown(Keyboard.KEY_S))
			move(new Vec3f(0, 0, speed));
		if(Keyboard.isKeyDown(Keyboard.KEY_D))
			move(new Vec3f(speed, 0, 0));
		if(Keyboard.isKeyDown(Keyboard.KEY_A))
			move(new Vec3f(-speed, 0, 0));
		if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT))
			rotate(rotation);
		if(Keyboard.isKeyDown(Keyboard.KEY_LEFT))
			rotate(-rotation);
		if(Keyboard.isKeyDown(Keyboard.KEY_UP))
			pitch(rotation);
		if(Keyboard.isKeyDown(Keyboard.KEY_DOWN))
			pitch(-rotation);
		int wheel = Mouse.getDWheel();
		if(wheel > 0)
			zoom(zoom);
		if(wheel < 0)
			zoom(-zoom);
	}
	
}
