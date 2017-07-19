package cameras;

import maths.Mat4f;
import maths.Vec3f;

public class Cam {
	
	private Vec3f pos;
	private Vec3f direction;
	private Vec3f up;
	
	public Cam(){
		pos = new Vec3f(0, 0, 3);
		direction = pos;
		up = direction.cross(new Vec3f(1, 0, 0));
	}
	// TODO: TEMP lookAt cam test, add inputs
	float theta = 0.0f;;
	float radius = 3;
	public Mat4f view() {
		float camX = radius * (float) Math.sin(theta);
		float camZ = radius * (float) Math.cos(theta);
		pos = new Vec3f(camX, 0, camZ);
		direction = pos.normalized().scale((float) Math.abs(camX));
		theta += 0.01f;;
		System.out.println(direction.length());
		return Mat4f.lookAtView(pos, up.cross(direction).normalized(), up.normalized(), direction);
	}
	
}
