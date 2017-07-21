package cameras;

import maths.Mat4f;

public abstract class Camera {
	
	protected float fov;
	protected boolean canMove; // TODO: do something with this, add boolean controls for all camera functions
	
	protected final float near;
	protected final float far;
	
	public Camera(float fov, float near, float far){
		this.fov = fov;
		this.near = near;
		this.far = far;
		canMove = true;
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
