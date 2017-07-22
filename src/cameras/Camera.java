package cameras;

import maths.Mat4f;
import maths.Vec3f;

public abstract class Camera {
	
	protected static final Vec3f Y_AXIS = new Vec3f(0, 1, 0);
	
	private final float near;
	private final float far;
	
	protected float fov;
	protected boolean canMove; // TODO: do something with this, add boolean controls for all camera functions
	
	public Camera(float fov, float near, float far){
		this.fov = fov;
		this.near = near;
		this.far = far;
		canMove = true;
	}
	
	protected void rotateX(Vec3f vec, Vec3f up, float theta){
		Vec3f right = Y_AXIS.cross(vec);
		right.normalize();
		
		vec.rotate(-theta, right);
		vec.normalize();
		
		up.set(vec.cross(right));
		up.normalize();
	}
	
	protected void rotateY(Vec3f vec, Vec3f up, float theta){
		Vec3f right = vec.cross(Y_AXIS);
		right.normalize();
		
		vec.rotate(-theta, Y_AXIS);
		vec.normalize();
		
		up.set(right.cross(vec));
		up.normalize();
	}
	
	public Mat4f projection(){
		return Mat4f.perspectiveProjection(near, far, fov);
	}
	
	public void allowCameraMovement(boolean canMove){ // TODO: change method/parameter/field names?
		this.canMove = canMove;
	}
	
	public abstract Mat4f view();
	public abstract void update(); // TODO: temp?
	
}
