package cameras;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import maths.Mat4f;
import maths.Vec3f;

public class FPSCam extends Camera {
	
	private Vec3f pos;
	private Vec3f direction;
	private Vec3f up;
	
	public FPSCam(Vec3f pos, Vec3f viewDir, float fov, float nearPlane, float farPlane){
		super(fov, nearPlane, farPlane);
		this.pos = pos;
		direction = viewDir;
		direction.normalize();
		direction.scale(-1);
		float facing = direction.getZ() > 0 ? 1 : -1;
		up = direction.cross(new Vec3f(facing, 0, 0));
		up.normalize();
	}
	
	@Override
	public Mat4f view() {
		Vec3f right = up.cross(direction);
		right.normalize();
		return Mat4f.view(pos, right, up, direction);
	}
	
	public void moveForward(float distance){
		Vec3f forward = direction.normalized().scale(-1);
		pos = pos.add(forward.scale(distance));
	}
	
	public void moveRight(float distance){
		Vec3f right = up.cross(direction).normalized();
		pos = pos.add(right.scale(distance));
	}
	
	public void pitch(float angle){
		rotateX(direction, up, -angle);
	}
	
	public void yaw(float angle){
		rotateY(direction, up, angle);
	}
	
	public void zoom(float amount){
		fov -= amount;
	}
	
	// TODO: TEMP?
	@Override
	public void update(){
		float speed = 0.07f;
		float rotation = 1f;
		float zoom = 3;
		
		if(Keyboard.isKeyDown(Keyboard.KEY_W))
			moveForward(speed);
		if(Keyboard.isKeyDown(Keyboard.KEY_S))
			moveForward(-speed);
		if(Keyboard.isKeyDown(Keyboard.KEY_D))
			moveRight(speed);
		if(Keyboard.isKeyDown(Keyboard.KEY_A))
			moveRight(-speed);
		if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT))
			yaw(rotation);
		if(Keyboard.isKeyDown(Keyboard.KEY_LEFT))
			yaw(-rotation);
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
