package renderEngine;

import org.lwjgl.opengl.GL11;

import utilities.Resources;

public class Material {
	
	private int diffuseMap;
	private int lightMap;
	private int normalMap;
	private int depthMap;
	
	private float ambient;
	private float diffuse;
	private float specular;
	private int shininess;
	
	private float displacementFactor;
	private boolean wrapDisplacedTexture;
	
	private boolean usesLighting;
	private boolean usesLightMap;
	private boolean usesNormalMap;
	private boolean usesDepthMap;
	
	public Material(String diffuseMapFile){
		diffuseMap = Resources.loadTexture(diffuseMapFile);
		lightMap = -1;
		normalMap = -1;
		depthMap = -1;
		
		ambient = 0;
		diffuse = 0;
		specular = 0;
		shininess = 0;
		
		displacementFactor = 0;
		wrapDisplacedTexture = true;
		
		usesLighting = false;
		usesLightMap = false;
		usesNormalMap = false;
		usesDepthMap = false;
	}
	
	public Material setDiffuseMap(String diffuseMapFile){
		deleteTexture(diffuseMap);
		diffuseMap = Resources.loadTexture(diffuseMapFile);
		return this;
	}
	
	public Material setLightMap(String lightMapFile){
		deleteTexture(lightMap);
		lightMap = Resources.loadTexture(lightMapFile);
		usesLightMap = true;
		usesLighting = true;
		return this;
	}
	
	public Material setNormalMap(String normalMapFile){
		deleteTexture(normalMap);
		normalMap = Resources.loadTexture(normalMapFile);
		usesNormalMap = true;
		usesLighting = true;
		return this;
	}
	
	public Material setDepthMap(String depthMapFile, float displacementFactor, boolean wrapDisplacedTexture){
		deleteTexture(depthMap);
		depthMap = Resources.loadTexture(depthMapFile);
		this.displacementFactor = displacementFactor;
		this.wrapDisplacedTexture = wrapDisplacedTexture;
		usesDepthMap = true;
		usesLighting = true;
		return this;
	}
	
	public Material setAmbient(float ambient){
		this.ambient = ambient;
		usesLighting = true;
		return this;
	}
	
	public Material setDiffuse(float diffuse){
		this.diffuse = diffuse;
		usesLighting = true;
		return this;
	}
	
	public Material setSpecular(float specular, int shininess){
		this.specular = specular;
		this.shininess = shininess;
		usesLighting = true;
		return this;
	}
	
	public void delete(){
		deleteTexture(diffuseMap);
		deleteTexture(lightMap);
		deleteTexture(normalMap);
		deleteTexture(depthMap);
	}
	
	private void deleteTexture(int texture){
		GL11.glDeleteTextures(texture);
	}
	
	public int getDiffuseMap(){
		return diffuseMap;
	}
	
	public int getLightMap(){
		return lightMap;
	}
	
	public int getNormalMap(){
		return normalMap;
	}
	
	public int getDepthMap(){
		return depthMap;
	}
	
	public boolean usesDepthMap(){
		return usesDepthMap;
	}
	
	public float getAmbient(){
		return ambient;
	}
	
	public float getDiffuse(){
		return diffuse;
	}
	
	public float getSpecular(){
		return specular;
	}
	
	public int getShininess(){
		return shininess;
	}
	
	public float getDisplacementFactor(){
		return displacementFactor;
	}
	
	public boolean getWrapDisplacedTexture(){
		return wrapDisplacedTexture;
	}
	
	public boolean usesLighting(){
		return usesLighting;
	}
	
	public boolean usesLightMap(){
		return usesLightMap;
	}
	
	public boolean usesNormalMap(){
		return usesNormalMap;
	}
	
}
