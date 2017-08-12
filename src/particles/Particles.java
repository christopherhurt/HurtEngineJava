package particles;

import java.util.ArrayList;
import java.util.List;

public class Particles {
	
	private static final List<ParticleSystem> systems = new ArrayList<>();
	
	private static float gravity = 10;
	
	public static void update(){
		for(ParticleSystem system : systems){
			system.update();
		}
	}
	
	public static void addSystem(ParticleSystem system){
		systems.add(system);
	}
	
	public static void removeSystem(ParticleSystem system){
		systems.remove(system);
	}
	
	public static float getGravity(){
		return gravity;
	}
	
	public static void setGravity(float gravity){
		Particles.gravity = gravity;
	}
	
}
