package particles;

import java.util.ArrayList;
import java.util.List;

public class Particles {
	
	private static final List<ParticleSystem> systems = new ArrayList<>();
	
	public static void update(){
		// TODO
	}
	
	public static void addSystem(ParticleSystem system){
		systems.add(system);
	}
	
}
