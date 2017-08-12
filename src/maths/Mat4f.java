package maths;

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;

import display.Disp;

public class Mat4f {
	
	private float[][] m;
	
	public Mat4f(){
		m = new float[4][4];
	}
	
	public Mat4f mul(Mat4f mat){
		Mat4f product = new Mat4f();
		
		for(int r = 0; r < 4; r++){
			for(int c = 0; c < 4; c++){
				product.m[r][c] = 	this.m[r][0] * mat.m[0][c] +
									this.m[r][1] * mat.m[1][c] +
									this.m[r][2] * mat.m[2][c] +
									this.m[r][3] * mat.m[3][c];
			}
		}
		
		return product;
	}
	
	public Vec4f mul(Vec4f vec){
		float vX = vec.getX();
		float vY = vec.getY();
		float vZ = vec.getZ();
		float vW = vec.getW();
		
		float x = m[0][0] * vX + m[0][1] * vY + m[0][2] * vZ + m[0][3] * vW;
		float y = m[1][0] * vX + m[1][1] * vY + m[1][2] * vZ + m[1][3] * vW;
		float z = m[2][0] * vX + m[2][1] * vY + m[2][2] * vZ + m[2][3] * vW;
		float w = m[3][0] * vX + m[3][1] * vY + m[3][2] * vZ + m[3][3] * vW;
		
		return new Vec4f(x, y, z, w);
	}
	
	public float value(int r, int c){
		return m[r][c];
	}
	
	public void put(int r, int c, float value){
		m[r][c] = value;
	}
	
	public FloatBuffer toBuffer(){
		FloatBuffer buffer = BufferUtils.createFloatBuffer(16);
		
		for(int c = 0; c < 4; c++){
			for(int r = 0; r < 4; r++){
				buffer.put(m[r][c]);
			}
		}
		
		buffer.flip();
		return buffer;
	}
	
	////////////////////////////////////////////////////////////////////////////
	
	public static Mat4f identity(){
		Mat4f identity = new Mat4f();
		
		for(int r = 0; r < 4; r++){
			for(int c = 0; c < 4; c++){
				if(r == c){
					identity.m[r][c] = 1;
				}else{
					identity.m[r][c] = 0;
				}
			}
		}
		
		return identity;
	}
	
	public static Mat4f translation(Vec3f pos){
		Mat4f translation = identity();
		
		translation.m[0][3] = pos.getX();
		translation.m[1][3] = pos.getY();
		translation.m[2][3] = pos.getZ();
		
		return translation;
	}
	
	public static Mat4f rotationX(float theta){
		Mat4f rotationX = identity();
		double rads = Math.toRadians(theta);
		
		rotationX.m[1][1] = (float)  Math.cos(rads);
		rotationX.m[2][1] = (float)  Math.sin(rads);
		rotationX.m[1][2] = (float) -Math.sin(rads);
		rotationX.m[2][2] = (float)  Math.cos(rads);
		
		return rotationX;
	}
	
	public static Mat4f rotationY(float theta){
		Mat4f rotationY = identity();
		double rads = Math.toRadians(theta);
		
		rotationY.m[0][0] = (float)  Math.cos(rads);
		rotationY.m[2][0] = (float) -Math.sin(rads);
		rotationY.m[0][2] = (float)  Math.sin(rads);
		rotationY.m[2][2] = (float)  Math.cos(rads);
		
		return rotationY;
	}

	public static Mat4f rotationZ(float theta){
		Mat4f rotationZ = identity();
		double rads = Math.toRadians(theta);
		
		rotationZ.m[0][0] = (float)  Math.cos(rads);
		rotationZ.m[1][0] = (float)  Math.sin(rads);
		rotationZ.m[0][1] = (float) -Math.sin(rads);
		rotationZ.m[1][1] = (float)  Math.cos(rads);
		
		return rotationZ;
	}
	
	public static Mat4f scale(Vec3f scale){
		Mat4f sc = identity();
		
		sc.m[0][0] = scale.getX();
		sc.m[1][1] = scale.getY();
		sc.m[2][2] = scale.getZ();
		
		return sc;
	}
	
	public static Mat4f transform(Vec3f pos, Vec3f rot, Vec3f scale){
		Mat4f trans = translation(pos);
		Mat4f rotX = rotationX(rot.getX());
		Mat4f rotY = rotationY(rot.getY());
		Mat4f rotZ = rotationZ(rot.getZ());
		Mat4f sc = scale(scale);
		
		return trans.mul(rotZ).mul(rotX).mul(rotY).mul(sc);
	}
	
	public static Mat4f perspectiveProjection(float near, float far, float fov){
		Mat4f projection = new Mat4f();
		
		float aspectRatio = (float) Disp.WIDTH / Disp.HEIGHT;
		float denominator = (float) Math.tan(Math.toRadians(fov / 2));
		
		projection.m[0][0] = 1 / (denominator * aspectRatio);
		projection.m[1][1] = 1 / denominator;
		projection.m[2][2] = (near + far) / (near - far);
		projection.m[2][3] = 2 * near * far / (near - far);
		projection.m[3][2] = -1;
		projection.m[3][3] = 0;
		
		return projection;
	}
	
	public static Mat4f orthographicProjection(float near, float far, float left, float right, float bottom, float top){
		Mat4f projection = new Mat4f();
		
		float aspectRatio = (float) Disp.WIDTH / Disp.HEIGHT;
		
		projection.m[0][0] =  2 / ((right - left) * aspectRatio);
		projection.m[1][1] =  2 / (top - bottom);
		projection.m[2][2] = -2 / (far - near);
		projection.m[3][3] = 1;
		projection.m[0][3] = -(right + left) / (right - left);
		projection.m[1][3] = -(top + bottom) / (top - bottom);
		projection.m[2][3] = -(far + near) 	 / (far - near);
		
		return projection;
	}
	
	public static Mat4f view(Vec3f pos, Vec3f right, Vec3f up, Vec3f direction){
		Mat4f view = new Mat4f();
		
		view.m[0][0] = right.getX();
		view.m[0][1] = right.getY();
		view.m[0][2] = right.getZ();
		view.m[1][0] = up.getX();
		view.m[1][1] = up.getY();
		view.m[1][2] = up.getZ();
		view.m[2][0] = direction.getX();
		view.m[2][1] = direction.getY();
		view.m[2][2] = direction.getZ();
		view.m[0][3] = -pos.dot(right);
		view.m[1][3] = -pos.dot(up);
		view.m[2][3] = -pos.dot(direction);
		view.m[3][3] = 1;
		
		return view;
	}
	
	public static Mat4f transformView(Mat4f view, Vec3f pos, Vec3f scale){
		Mat4f transform = translation(pos);
		
		for(int r = 0; r < 3; r++){
			for(int c = 0; c < 3; c++){
				float value = view.value(c, r);
				transform.put(r, c, value);
			}
		}
		
		float xValue = transform.value(0, 0);
		float yValue = transform.value(1, 1);
		float zValue = transform.value(2, 2);
		
		transform.put(0, 0, scale.getX() * xValue);
		transform.put(1, 1, scale.getY() * yValue);
		transform.put(2, 2, scale.getZ() * zValue);
		
		return view.mul(transform);
	}
	
}
