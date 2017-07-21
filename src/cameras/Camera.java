package cameras;

import maths.Mat4f;

public abstract class Camera {
	
	protected float fov;
	
	protected final float near;
	protected final float far;
	
	public Camera(float fov, float near, float far){
		this.fov = fov;
		this.near = near;
		this.far = far;
	}
	
	public Mat4f projection(){
		return Mat4f.perspectiveProjection(near, far, fov);
	}
	
	public abstract Mat4f view();
	public abstract void update(); // TODO: temp?
	
}
