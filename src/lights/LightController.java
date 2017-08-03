package lights;

import java.util.ArrayList;
import java.util.List;

import shaders.GameObjectShader;
import utilities.HurtEngineException;

public class LightController {
	
	private static final List<DirectionalLight> dLights = new ArrayList<>();
	private static final List<PointLight> 		pLights = new ArrayList<>();
	private static final List<Spotlight> 		sLights = new ArrayList<>();
	
	private static int maxLights = 4;
	private static int numDLights = 0;
	private static int numPLights = 0;
	private static int numSLights = 0;
	
	public static void loadLights(GameObjectShader shader){
		// TODO
	}
	
	public static void addLight(DirectionalLight light){
		dLights.add(light);
		numDLights++;
		ensureCapacity();
	}
	
	public static void addLight(PointLight light){
		pLights.add(light);
		numPLights++;
		ensureCapacity();
	}
	
	public static void addLight(Spotlight light){
		sLights.add(light);
		numSLights++;
		ensureCapacity();
	}
	
	public static void removeLight(DirectionalLight light){ // TODO: have this class API accessible?
		boolean removed = dLights.remove(light);
		
		if(removed){
			numDLights--;
		}
	}
	
	public static void removeLight(PointLight light){
		boolean removed = pLights.remove(light);
		
		if(removed){
			numPLights--;
		}
	}
	
	public static void removeLight(Spotlight light){
		boolean removed = sLights.remove(light);
		
		if(removed){
			numSLights--;
		}
	}
	
	public static int getMaxLights(){
		return maxLights;
	}
	
	public static void setMaxLights(int maxLights){
		LightController.maxLights = maxLights;
	}
	
	public static int getNumDirectionalLights(){
		return numDLights;
	}
	
	public static int getNumPointLights(){
		return numPLights;
	}
	
	public static int getNumSpotlights(){
		return numSLights;
	}
	
	private static void ensureCapacity(){
		int totalLights = numDLights + numPLights + numSLights;
		
		if(totalLights > maxLights){
			throw new HurtEngineException("Exceeded maximum of " + maxLights + " total lights");
		}
	}
	
}
