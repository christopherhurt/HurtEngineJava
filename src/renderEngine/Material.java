package renderEngine;

import utilities.Resources;

public class Material {
	
	private int texture;
	private int normalMap;	
	
	private float ambient;
	private float diffuse;
	private float specular;
	private int shininess;
	
	private boolean usesNormalMap;
	private boolean usesLighting;
	
	public Material(String textureFile){
		texture = Resources.loadTexture(textureFile);
		normalMap = 0;
		ambient = 1;
		diffuse = 0;
		specular = 0;
		shininess = 0;
		usesNormalMap = false;
		usesLighting = false;
	}
	
	public Material setNormalMap(String normalMapFile){
		normalMap = Resources.loadTexture(normalMapFile);
		usesNormalMap = true;
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
	
	public int getTexture(){
		return texture;
	}
	
	public int getNormalMap(){
		return normalMap;
	}
	
	public boolean usesNormalMap(){
		return usesNormalMap;
	}
	
	public boolean usesLighting(){
		return usesLighting;
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
	
}
