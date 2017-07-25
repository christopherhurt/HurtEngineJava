package cameras;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import maths.Mat4f;
import maths.Vec2f;
import maths.Vec3f;

public class ThirdPersonCam extends Camera {
	
	private static final float DEFAULT_CAM_SPEED = 0.07f;
	private static final float DEFAULT_ROTATION_SPEED = 1;
	private static final float DEFAULT_ZOOM_SPEED = 3;
	
	private float moveSpeed;
	private float rotationSpeed;
	private float zoomSpeed;
	
	private Vec3f viewPos;
	private Vec3f direction;
	private Vec3f up;
	private float distance;
	
	private Vec3f playerDirection;
	private boolean syncedWithPlayer;
	
	public ThirdPersonCam(Vec3f viewPos, float distance, float pitch, float yaw, float fov, float nearPlane, float farPlane){
		super(fov, nearPlane, farPlane);
		this.viewPos = viewPos;
		this.distance = distance;
		
		moveSpeed = DEFAULT_CAM_SPEED;
		rotationSpeed = DEFAULT_ROTATION_SPEED;
		zoomSpeed = DEFAULT_ZOOM_SPEED;
		
		direction = new Vec3f(0, 0, 1);
		up = new Vec3f(0, 1, 0);
		playerDirection = new Vec3f(direction);
		pitch(pitch);
		rotateCam(yaw);
		movePlayerToCam();
		syncedWithPlayer = true;
	}
	
	@Override
	public Mat4f view() {
		Vec3f right = up.cross(direction);
		right.normalize();
		return Mat4f.view(getLocation(), right, up, direction);
	}
	
	public void moveForward(float distance){
		viewPos = viewPos.sub(playerDirection.scaled(distance));
	}
	
	public void moveRight(float distance){
		Vec3f playerRight = Y_AXIS.cross(playerDirection);
		viewPos = viewPos.add(playerRight.scale(distance));
	}
	
	public void zoomIn(float distance){
		this.distance -= distance;
	}
	
	public void pitch(float angle){
		rotateX(direction, up, angle);
	}
	
	public void rotateCam(float angle){
		if(!syncedWithPlayer){
			Vec3f temp = new Vec3f(playerDirection);
			movePlayerToCam();
			rotatePlayer(angle);
			moveCamToPlayer();
			playerDirection = temp;
		}
	}
	
	public void rotatePlayer(float angle){
		rotateY(playerDirection, new Vec3f(Y_AXIS), angle);
		if(syncedWithPlayer){
			moveCamToPlayer();
		}
	}
	
	public void syncWithPlayer(boolean sync){
		if(sync){
			moveCamToPlayer();
		}
		syncedWithPlayer = sync;
	}
	
	private void moveCamToPlayer(){
		float yValue = direction.getY();
		Vec2f dirProj = new Vec2f(direction.getX(), direction.getZ());
		float projLength = dirProj.length();
		direction = new Vec3f(playerDirection.getX() * projLength, yValue, playerDirection.getZ() * projLength).normalized();
		Vec3f right = Y_AXIS.cross(direction).normalized();
		up = direction.cross(right).normalized();
	}
	
	private void movePlayerToCam(){
		playerDirection = new Vec3f(direction.getX(), 0, direction.getZ()).normalized();
	}
	
	public Vec3f getPlayerDirection(){
		return playerDirection;
	}
	
	@Override
	public Vec3f getLocation(){
		return viewPos.add(direction.scaled(distance));
	}
	
	public void setMoveSpeed(float moveSpeed){
		this.moveSpeed = moveSpeed;
	}
	
	public void setRotationSpeed(float rotationSpeed){
		this.rotationSpeed = rotationSpeed;
	}
	
	public void setZoomSpeed(float zoomSpeed){
		this.zoomSpeed = zoomSpeed;
	}
	
	// TODO: TEMP?
	@Override
	public void update(){
		if(Keyboard.isKeyDown(Keyboard.KEY_W))
			moveForward(moveSpeed);
		if(Keyboard.isKeyDown(Keyboard.KEY_S))
			moveForward(-moveSpeed);
		if(Keyboard.isKeyDown(Keyboard.KEY_D))
			moveRight(moveSpeed);
		if(Keyboard.isKeyDown(Keyboard.KEY_A))
			moveRight(-moveSpeed);
		if(Keyboard.isKeyDown(Keyboard.KEY_E))
			rotatePlayer(rotationSpeed);
		if(Keyboard.isKeyDown(Keyboard.KEY_Q))
			rotatePlayer(-rotationSpeed);
		if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT))
			rotateCam(rotationSpeed);
		if(Keyboard.isKeyDown(Keyboard.KEY_LEFT))
			rotateCam(-rotationSpeed);
		if(Keyboard.isKeyDown(Keyboard.KEY_UP))
			pitch(rotationSpeed);
		if(Keyboard.isKeyDown(Keyboard.KEY_DOWN))
			pitch(-rotationSpeed);
		int wheel = Mouse.getDWheel();
		if(wheel > 0)
			zoomIn(zoomSpeed);
		if(wheel < 0)
			zoomIn(-zoomSpeed);
		if(Keyboard.isKeyDown(Keyboard.KEY_SPACE)){
			syncWithPlayer(!syncedWithPlayer);
			System.out.println(syncedWithPlayer);
		}
	}
	
}
