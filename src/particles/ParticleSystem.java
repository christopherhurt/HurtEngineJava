package particles;

import java.util.ArrayList;
import java.util.List;

import maths.Vec3f;
import renderEngine.Material;
import renderEngine.Model;
import utilities.Time;

public class ParticleSystem {
	
	private static final ParticleQuad PARTICLE_MESH = new ParticleQuad();
	
	private final Model particleModel;
	
	private List<Particle> particles;
	private int numTextures;
	private float transitionSpeed;
	private boolean gravityAffected;
	private boolean hudParticles;
	private float secondsPerParticle;
	private float spawnTimer;
	private Vec3f minSpawnPos;
	private Vec3f maxSpawnPos;
	private Vec3f minVel;
	private Vec3f maxVel;
	private float minSize;
	private float maxSize;
	
	public ParticleSystem(String atlasFile, int numTextures, float transitionSpeed, boolean gravityAffected, boolean hudParticles, float particlesPerSecond,
			Vec3f minSpawnPos, Vec3f maxSpawnPos, Vec3f minVel, Vec3f maxVel, float minSize, float maxSize){
		particleModel = new Model(PARTICLE_MESH, new Material(atlasFile));
		particles = new ArrayList<>();
		secondsPerParticle = 1 / particlesPerSecond;
		spawnTimer = 0;
		this.numTextures = numTextures;
		this.transitionSpeed = transitionSpeed;
		this.gravityAffected = gravityAffected;
		this.hudParticles = hudParticles;
		this.minSpawnPos = minSpawnPos;
		this.maxSpawnPos = maxSpawnPos;
		this.minVel = minVel;
		this.maxVel = maxVel;
		this.minSize = minSize;
		this.maxSize = maxSize;
	}
	
	public void update(){
		for(int i = 0; i < particles.size(); i++){
			Particle particle = particles.get(i);
			particle.update();
			
			if(particle.isDead()){
				particles.remove(particle);
				i--;
			}
			
			spawnTimer += secondsPerParticle * Time.getDelta();
			if(spawnTimer >= secondsPerParticle){
				spawnTimer %= secondsPerParticle;
				spawnParticle();
			}
		}
	}
	
	private void spawnParticle(){
		float posX = (float) (Math.random() * (maxSpawnPos.getX() - minSpawnPos.getX()) + minSpawnPos.getX());
		float posY = (float) (Math.random() * (maxSpawnPos.getY() - minSpawnPos.getY()) + minSpawnPos.getY());
		float posZ = (float) (Math.random() * (maxSpawnPos.getZ() - minSpawnPos.getZ()) + minSpawnPos.getZ());
		Vec3f pos = new Vec3f(posX, posY, posZ);
		
		float velX = (float) (Math.random() * (maxVel.getX() - minVel.getX()) + minVel.getX());
		float velY = (float) (Math.random() * (maxVel.getY() - minVel.getY()) + minVel.getY());
		float velZ = (float) (Math.random() * (maxVel.getZ() - minVel.getZ()) + minVel.getZ());
		Vec3f vel = new Vec3f(velX, velY, velZ);
		
		float scale = (float) (Math.random() * (maxSize - minSize) + minSize);
		
		Particle particle = new Particle(particleModel, pos, vel, scale, numTextures, transitionSpeed, gravityAffected, hudParticles);
		particles.add(particle);
	}
	
	public Model getParticleModel(){
		return particleModel;
	}
	
	public int getNumTextures(){
		return numTextures;
	}
	
	public boolean areHUDParticles(){
		return hudParticles;
	}
	
}
