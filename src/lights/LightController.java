package lights;

import java.util.ArrayList;
import java.util.List;

import utilities.HurtEngineException;

public class LightController {
	
	public static final int MAX_LIGHTS = 62;
	
	private static final List<DirectionalLight> dLights = new ArrayList<>();
	private static final List<PointLight> 		pLights = new ArrayList<>();
	private static final List<Spotlight> 		sLights = new ArrayList<>();
	
	private static int numDLights = 0;
	private static int numPLights = 0;
	private static int numSLights = 0;
	
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
	
	public static void removeLight(DirectionalLight light){
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
	
	public static List<DirectionalLight> getDirectionalLights(){
		return dLights;
	}
	
	public static List<PointLight> getPointLights(){
		return pLights;
	}
	
	public static List<Spotlight> getSpotlights(){
		return sLights;
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
		
		if(totalLights > MAX_LIGHTS){
			throw new HurtEngineException("Exceeded maximum of " + MAX_LIGHTS + " total lights");
		}
	}
	
}
