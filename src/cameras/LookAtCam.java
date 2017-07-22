package cameras;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import maths.Mat4f;
import maths.Vec2f;
import maths.Vec3f;

public class LookAtCam extends Camera {
	
	private Vec3f viewPos;
	private Vec3f direction;
	private Vec3f up;
	private float distance;
	
	private Vec3f playerDirection;
	private boolean syncedWithPlayer;
	
	public LookAtCam(Vec3f viewPos, float distance, float pitch, float yaw, float fov, float nearPlane, float farPlane){
		super(fov, nearPlane, farPlane);
		this.viewPos = viewPos;
		this.distance = distance;
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
		return Mat4f.view(viewPos.add(direction.scaled(distance)), right, up, direction);
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
		if(Keyboard.isKeyDown(Keyboard.KEY_E))
			rotatePlayer(rotation);
		if(Keyboard.isKeyDown(Keyboard.KEY_Q))
			rotatePlayer(-rotation);
		if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT))
			rotateCam(rotation);
		if(Keyboard.isKeyDown(Keyboard.KEY_LEFT))
			rotateCam(-rotation);
		if(Keyboard.isKeyDown(Keyboard.KEY_UP))
			pitch(rotation);
		if(Keyboard.isKeyDown(Keyboard.KEY_DOWN))
			pitch(-rotation);
		int wheel = Mouse.getDWheel();
		if(wheel > 0)
			zoomIn(zoom);
		if(wheel < 0)
			zoomIn(-zoom);
		if(Keyboard.isKeyDown(Keyboard.KEY_SPACE)){
			syncWithPlayer(!syncedWithPlayer);
			System.out.println(syncedWithPlayer);
		}
	}
	
}
