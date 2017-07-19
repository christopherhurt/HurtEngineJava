package cameras;

import org.lwjgl.input.Keyboard;

import maths.Mat4f;
import maths.Vec3f;

public class Cam {
	
	private Vec3f pos;
	private Vec3f direction;
	private Vec3f up;
	
	private float pitch;
	private float yaw;
	
	public Cam(){
		pos = new Vec3f(0, 0, 3);
		direction = new Vec3f(0, 0, 1);
		up = direction.cross(new Vec3f(1, 0, 0));
		
		pitch = 0;
		yaw = 0;
	}
	
	public Mat4f view() {
		float pitchRad = (float) Math.toRadians(pitch);
		float yawRad = (float) Math.toRadians(yaw);
		float cosPitch = (float) Math.cos(pitchRad);
		float sinPitch = (float) Math.sin(pitchRad);
		float cosYaw = (float) Math.cos(yawRad);
		float sinYaw = (float) Math.sin(yawRad);
		//direction = new Vec3f(cosPitch * cosYaw, sinPitch, cosPitch * sinYaw).normalized();
		//up = direction.cross(new Vec3f(1, 0, 0)).normalized();
		return Mat4f.lookAtView(pos, up.cross(direction), up, direction);
	}
	
	// TODO: TEMP
	public void update(){
		float speed = 0.05f;
		float rotation = 0.5f;
		if(Keyboard.isKeyDown(Keyboard.KEY_W))
			pos = pos.add(direction.scaled(-speed));
		if(Keyboard.isKeyDown(Keyboard.KEY_S))
			pos = pos.sub(direction.scaled(-speed));
		if(Keyboard.isKeyDown(Keyboard.KEY_D))
			pos = pos.add(up.cross(direction).scaled(speed));
		if(Keyboard.isKeyDown(Keyboard.KEY_A))
			pos = pos.sub(up.cross(direction).scaled(speed));
		if(Keyboard.isKeyDown(Keyboard.KEY_UP))
			pitch -= rotation;
		if(Keyboard.isKeyDown(Keyboard.KEY_DOWN))
			pitch += rotation;
		if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT))
			yaw += rotation;
		if(Keyboard.isKeyDown(Keyboard.KEY_LEFT))
			yaw -= rotation;
	}
	
}
