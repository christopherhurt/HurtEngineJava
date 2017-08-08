package shaders;

import java.util.List;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import cameras.Camera;
import lights.DirectionalLight;
import lights.LightController;
import lights.PointLight;
import lights.Spotlight;
import maths.Mat4f;
import objects.GameObject;
import renderEngine.Material;
import renderEngine.Mesh;
import renderEngine.Model;

public class GameObjectShader extends Shader<GameObject> {
	
	public static final String VERTEX_SHADER_FILE = "src/shaderPrograms/gameObjectVS.glsl";
	public static final String FRAGMENT_SHADER_FILE = "src/shaderPrograms/gameObjectFS.glsl";
	
	private Camera cam;
	
	private int projectionLoc;
	private int viewLoc;
	private int transformLoc;
	private int drawInstancedLoc;
	
	private int camLocLoc;
	private int numDLightsLoc;
	private int numPLightsLoc;
	private int numSLightsLoc;
	
	private int usesLightingLoc;
	private int usesLightMapLoc;
	private int usesNormalMapLoc;
	private int usesDepthMapLoc;
	
	private int ambientFactorLoc;
	private int diffuseFactorLoc;
	private int specularFactorLoc;
	private int shininessLoc;
	
	private int displacementFactorLoc;
	private int wrapDisplacedTextureLoc;
	
	private int diffuseMapLoc;
	private int lightMapLoc;
	private int normalMapLoc;
	private int depthMapLoc;
	
	private int directionalLightColorLocs[];
	private int directionalLightIntensityLocs[];
	private int directionalLightIsOnLocs[];
	private int directionalLightDirectionLocs[];
	
	private int pointLightColorLocs[];
	private int pointLightIntensityLocs[];
	private int pointLightIsOnLocs[];
	private int pointLightPositionLocs[];
	private int pointLightAttenuationLocs[];
	
	private int spotlightColorLocs[];
	private int spotlightIntensityLocs[];
	private int spotlightIsOnLocs[];
	private int spotlightPositionLocs[];
	private int spotlightDirectionLocs[];
	private int spotlightCosineInnerCutoffLocs[];
	private int spotlightCosineOuterCutoffLocs[];
	
	public GameObjectShader(Camera cam){
		super(VERTEX_SHADER_FILE, FRAGMENT_SHADER_FILE);
		this.cam = cam;
		
		projectionLoc = getUniformLocation("projection");
		viewLoc = getUniformLocation("view");
		transformLoc = getUniformLocation("uniformTransform");
		drawInstancedLoc = getUniformLocation("drawInstanced");
		
		camLocLoc = getUniformLocation("camLoc");
		numDLightsLoc = getUniformLocation("numDLights");
		numPLightsLoc = getUniformLocation("numPLights");
		numSLightsLoc = getUniformLocation("numSLights");
		
		usesLightingLoc = getUniformLocation("material.usesLighting");
		usesLightMapLoc = getUniformLocation("material.usesLightMap");
		usesNormalMapLoc = getUniformLocation("material.usesNormalMap");
		usesDepthMapLoc = getUniformLocation("material.usesDepthMap");
		
		ambientFactorLoc = getUniformLocation("material.ambientFactor");
		diffuseFactorLoc = getUniformLocation("material.diffuseFactor");
		specularFactorLoc = getUniformLocation("material.specularFactor");
		shininessLoc = getUniformLocation("material.shininess");
		
		displacementFactorLoc = getUniformLocation("material.displacementFactor");
		wrapDisplacedTextureLoc = getUniformLocation("material.wrapDisplacedTexture");
		
		diffuseMapLoc = getUniformLocation("material.diffuseMap");
		lightMapLoc = getUniformLocation("material.lightMap");
		normalMapLoc = getUniformLocation("material.normalMap");
		depthMapLoc = getUniformLocation("material.depthMap");
		
		directionalLightColorLocs = new int[LightController.MAX_LIGHTS];
		directionalLightIntensityLocs = new int[LightController.MAX_LIGHTS];
		directionalLightIsOnLocs = new int[LightController.MAX_LIGHTS];
		directionalLightDirectionLocs = new int[LightController.MAX_LIGHTS];
		
		pointLightColorLocs = new int[LightController.MAX_LIGHTS];
		pointLightIntensityLocs = new int[LightController.MAX_LIGHTS];
		pointLightIsOnLocs = new int[LightController.MAX_LIGHTS];
		pointLightPositionLocs = new int[LightController.MAX_LIGHTS];
		pointLightAttenuationLocs = new int[LightController.MAX_LIGHTS];
		
		spotlightColorLocs = new int[LightController.MAX_LIGHTS];
		spotlightIntensityLocs = new int[LightController.MAX_LIGHTS];
		spotlightIsOnLocs = new int[LightController.MAX_LIGHTS];
		spotlightPositionLocs = new int[LightController.MAX_LIGHTS];
		spotlightDirectionLocs = new int[LightController.MAX_LIGHTS];
		spotlightCosineInnerCutoffLocs = new int[LightController.MAX_LIGHTS];
		spotlightCosineOuterCutoffLocs = new int[LightController.MAX_LIGHTS];
		
		for(int i = 0; i < LightController.MAX_LIGHTS; i++){
			directionalLightColorLocs[i] = getUniformLocation("dLights[" + i + "].light.color");
			directionalLightIntensityLocs[i] = getUniformLocation("dLights[" + i + "].light.intensity");
			directionalLightIsOnLocs[i] = getUniformLocation("dLights[" + i + "].light.isOn");
			directionalLightDirectionLocs[i] = getUniformLocation("dLights[" + i + "].direction");
			
			pointLightColorLocs[i] = getUniformLocation("pLights[" + i + "].light.color");
			pointLightIntensityLocs[i] = getUniformLocation("pLights[" + i + "].light.intensity");
			pointLightIsOnLocs[i] = getUniformLocation("pLights[" + i + "].light.isOn");
			pointLightPositionLocs[i] = getUniformLocation("pLights[" + i + "].position");
			pointLightAttenuationLocs[i] = getUniformLocation("pLights[" + i + "].attenuation");
			
			spotlightColorLocs[i] = getUniformLocation("sLights[" + i + "].light.color");
			spotlightIntensityLocs[i] = getUniformLocation("sLights[" + i + "].light.intensity");
			spotlightIsOnLocs[i] = getUniformLocation("sLights[" + i + "].light.isOn");
			spotlightPositionLocs[i] = getUniformLocation("sLights[" + i + "].position");
			spotlightDirectionLocs[i] = getUniformLocation("sLights[" + i + "].direction");
			spotlightCosineInnerCutoffLocs[i] = getUniformLocation("sLights[" + i + "].cosineInnerCutoff");
			spotlightCosineOuterCutoffLocs[i] = getUniformLocation("sLights[" + i + "].cosineOuterCutoff");
		}
		
		start();
		loadInt(diffuseMapLoc, 0);
		loadInt(lightMapLoc, 1);
		loadInt(normalMapLoc, 2);
		loadInt(depthMapLoc, 3);
		stop();
	}

	@Override
	public void bindAttributes() {
		bindAttribute(0, "pos");
		bindAttribute(1, "texCoords");
		bindAttribute(2, "norm");
		bindAttribute(3, "tang");
		bindAttribute(4, "instanceTransform");
	}
	
	@Override
	public void prepareShaderRender(){
		loadMat4f(projectionLoc, cam.projection());
		loadMat4f(viewLoc, cam.view());
		
		int numDLights = LightController.getNumDirectionalLights();
		int numPLights = LightController.getNumPointLights();
		int numSLights = LightController.getNumSpotlights();
		
		List<DirectionalLight> dLights = LightController.getDirectionalLights();
		List<PointLight> pLights = LightController.getPointLights();
		List<Spotlight> sLights = LightController.getSpotlights();
		
		loadVec3f(camLocLoc, cam.getLocation());
		loadInt(numDLightsLoc, numDLights);
		loadInt(numPLightsLoc, numPLights);
		loadInt(numSLightsLoc, numSLights);
		
		for(int i = 0; i < numDLights; i++){
			DirectionalLight light = dLights.get(i);
			loadVec3f(directionalLightColorLocs[i], light.getColor());
			loadFloat(directionalLightIntensityLocs[i], light.getIntensity());
			loadBoolean(directionalLightIsOnLocs[i], light.isOn());
			loadVec3f(directionalLightDirectionLocs[i], light.getDirection());
		}
		
		for(int i = 0; i < numPLights; i++){
			PointLight light = pLights.get(i);
			loadVec3f(pointLightColorLocs[i], light.getColor());
			loadFloat(pointLightIntensityLocs[i], light.getIntensity());
			loadBoolean(pointLightIsOnLocs[i], light.isOn());
			loadVec3f(pointLightPositionLocs[i], light.getPosition());
			loadVec2f(pointLightAttenuationLocs[i], light.getAttenuation());
		}
		
		for(int i = 0; i < numSLights; i++){
			Spotlight light = sLights.get(i);
			loadVec3f(spotlightColorLocs[i], light.getColor());
			loadFloat(spotlightIntensityLocs[i], light.getIntensity());
			loadBoolean(spotlightIsOnLocs[i], light.isOn());
			loadVec3f(spotlightPositionLocs[i], light.getPosition());
			loadVec3f(spotlightDirectionLocs[i], light.getDirection());
			loadFloat(spotlightCosineInnerCutoffLocs[i], light.getCosineInnerCutoff());
			loadFloat(spotlightCosineOuterCutoffLocs[i], light.getCosineOuterCutoff());
		}
	}
	
	@Override
	public void prepareModelRender(Model model) {
		Mesh mesh = model.getMesh();
		Material material = model.getMaterial();
		
		GL30.glBindVertexArray(mesh.getVao());
		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, material.getDiffuseMap());
		GL13.glActiveTexture(GL13.GL_TEXTURE1);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, material.getLightMap());
		GL13.glActiveTexture(GL13.GL_TEXTURE2);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, material.getNormalMap());
		GL13.glActiveTexture(GL13.GL_TEXTURE3);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, material.getDepthMap());
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, mesh.getIndexVbo());
		GL20.glEnableVertexAttribArray(0);
		GL20.glEnableVertexAttribArray(1);
		GL20.glEnableVertexAttribArray(2);
		GL20.glEnableVertexAttribArray(3);
		
		boolean drawInstanced = mesh.getDrawInstanced();
		if(drawInstanced){
			GL20.glEnableVertexAttribArray(4);
			GL20.glEnableVertexAttribArray(5);
			GL20.glEnableVertexAttribArray(6);
			GL20.glEnableVertexAttribArray(7);
		}
		loadBoolean(drawInstancedLoc, drawInstanced);
		
		loadBoolean(usesLightingLoc, material.usesLighting());
		loadBoolean(usesLightMapLoc, material.usesLightMap());
		loadBoolean(usesNormalMapLoc, material.usesNormalMap());
		loadBoolean(usesDepthMapLoc, material.usesDepthMap());
		
		loadFloat(ambientFactorLoc, material.getAmbient());
		loadFloat(diffuseFactorLoc, material.getDiffuse());
		loadFloat(specularFactorLoc, material.getSpecular());
		loadFloat(shininessLoc, material.getShininess());
		
		loadFloat(displacementFactorLoc, material.getDisplacementFactor());
		loadBoolean(wrapDisplacedTextureLoc, material.getWrapDisplacedTexture());
	}

	@Override
	public void prepareObjectRender(GameObject object){
		loadMat4f(transformLoc, object.transformation());
	}
	
	@Override
	public void prepareInstancedRender(List<GameObject> instances, float[] instancedData){
		int pointer = 0;
		for(GameObject object : instances){
			Mat4f transform = object.transformation();
			for(int c = 0; c < 4; c++){
				for(int r = 0; r < 4; r++){
					instancedData[pointer++] = transform.value(r, c);
				}
			}
		}
	}
	
	@Override
	public void finishModelRender(Model model) {
		if(model.getMesh().getDrawInstanced()){
			GL20.glDisableVertexAttribArray(7);
			GL20.glDisableVertexAttribArray(6);
			GL20.glDisableVertexAttribArray(5);
			GL20.glDisableVertexAttribArray(4);
		}
		GL20.glDisableVertexAttribArray(3);
		GL20.glDisableVertexAttribArray(2);
		GL20.glDisableVertexAttribArray(1);
		GL20.glDisableVertexAttribArray(0);
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
		GL30.glBindVertexArray(0);
	}

}
