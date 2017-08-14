package particles;

import maths.Mat4f;
import maths.Vec3f;
import renderEngine.Model;
import renderEngine.RenderObject;
import utilities.Time;

public class Particle extends RenderObject {
	
	private static final Vec3f NO_ROTATION_VECTOR = new Vec3f(0, 0, 0);
	
	private Vec3f pos;
	private Vec3f vel;
	private Vec3f scale;
	private int totalTextures;
	private int currentTexture;
	private float transitionAmount;
	private float transitionSpeed;
	private boolean gravityAffected;
	private boolean hudParticle;
	private boolean isDead;
	
	public Particle(Model model, Vec3f pos, Vec3f vel, float scale, int totalTextures, float transitionSpeed, boolean gravityAffected, boolean hudParticle) {
		super(model);
		this.pos = pos;
		this.vel = vel;
		this.scale = new Vec3f(scale, scale, 1);
		this.totalTextures = totalTextures;
		this.transitionSpeed = transitionSpeed;
		this.gravityAffected = gravityAffected;
		this.hudParticle = hudParticle;
		currentTexture = 0;
		transitionAmount = 0;
		isDead = false;
	}
	
	public void update(){
		if(gravityAffected){
			vel.setY(vel.getY() - Particles.getGravity() * Time.getDelta());
		}
		pos = pos.add(vel.scaled(Time.getDelta()));
		
		transitionAmount += transitionSpeed * Time.getDelta();
		if(transitionAmount >= 1){
			transitionAmount %= 1;
			currentTexture++;
			isDead = currentTexture >= totalTextures;
		}
	}
	
	public Mat4f transformViewMatrix(Mat4f view){
		return hudParticle ? Mat4f.transform(pos, NO_ROTATION_VECTOR, scale) : Mat4f.transformView(view, pos, scale);
	}
	
	public Vec3f getPosition(){
		return pos;
	}
	
	public int getTotalTextures(){
		return totalTextures;
	}
	
	public int getCurrentTexture(){
		return currentTexture;
	}
	
	public float getTransitionAmount(){
		return transitionAmount;
	}
	
	public boolean isDead(){
		return isDead;
	}
	
	public boolean isHudParticle(){
		return hudParticle;
	}
	
}
