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
	
}
