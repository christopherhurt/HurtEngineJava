package shaders;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import cameras.Camera;
import lights.DirectionalLight;
import maths.Vec3f;
import objects.GameObject;
import renderEngine.Material;
import renderEngine.Mesh;
import renderEngine.Model;

public class GameObjectShader extends Shader<GameObject> {
	
	public static final String VERTEX_SHADER_FILE = "src/shaderPrograms/gameObjectVS.glsl";
	public static final String FRAGMENT_SHADER_FILE = "src/shaderPrograms/gameObjectFS.glsl";
	
	private Camera cam;
	
	private DirectionalLight light; // TODO: temp light
	
	private int textureLoc;
	private int transformLoc;
	private int viewLoc;
	private int projectionLoc;
	
	// TODO: temp uniform locations for lighting testing
	private int lightDirLoc;
	private int camLocLoc;
	private int usesLightingLoc;
	private int ambientFactorLoc;
	private int diffuseFactorLoc;
	private int specularFactorLoc;
	private int shininessLoc;
	private int lightColorLoc;
	private int lightIntensityLoc;
	
	public GameObjectShader(Camera cam) {
		super(VERTEX_SHADER_FILE, FRAGMENT_SHADER_FILE);
		this.cam = cam;
		light = new DirectionalLight(new Vec3f(0, 0, -1), new Vec3f(1, 1, 1), 1);
		
		textureLoc = getUniformLocation("tex");
		transformLoc = getUniformLocation("transform");
		viewLoc = getUniformLocation("view");
		projectionLoc = getUniformLocation("projection");
		
		lightDirLoc = getUniformLocation("lightDir");
		camLocLoc = getUniformLocation("camLoc");
		usesLightingLoc = getUniformLocation("usesLighting");
		ambientFactorLoc = getUniformLocation("ambientFactor");
		diffuseFactorLoc = getUniformLocation("diffuseFactor");
		specularFactorLoc = getUniformLocation("specularFactor");
		shininessLoc = getUniformLocation("shininess");
		lightColorLoc = getUniformLocation("lightColor");
		lightIntensityLoc = getUniformLocation("lightIntensity");
		
		start();
		loadInt(textureLoc, 0);
		loadVec3f(lightDirLoc, light.getDirection());
		loadVec3f(lightColorLoc, light.getColor());
		loadFloat(lightIntensityLoc, light.getIntensity());
		stop();
	}

	@Override
	public void bindAttributes() {
		bindAttribute(0, "pos");
		bindAttribute(1, "texCoords");
		bindAttribute(2, "norm");
	}
	
	@Override
	public void prepareShaderRender(){
		loadVec3f(camLocLoc, cam.getLocation());
		loadMat4f(viewLoc, cam.view());
		loadMat4f(projectionLoc, cam.projection());
	}

	@Override
	public void prepareModelRender(Model model) {
		Mesh mesh = model.getMesh();
		Material material = model.getMaterial();
		
		GL30.glBindVertexArray(mesh.getVao());
		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, material.getTexture());
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, mesh.getIndexVbo());
		GL20.glEnableVertexAttribArray(0);
		GL20.glEnableVertexAttribArray(1);
		GL20.glEnableVertexAttribArray(2);
		
		loadBoolean(usesLightingLoc, material.usesLighting());
		loadFloat(ambientFactorLoc, material.getAmbient());
		loadFloat(diffuseFactorLoc, material.getDiffuse());
		loadFloat(specularFactorLoc, material.getSpecular());
		loadInt(shininessLoc, material.getShininess());
	}

	@Override
	public void prepareObjectRender(GameObject object){
		loadMat4f(transformLoc, object.transformation());
	}
	
	@Override
	public void finishModelRender() {
		GL20.glDisableVertexAttribArray(2);
		GL20.glDisableVertexAttribArray(1);
		GL20.glDisableVertexAttribArray(0);
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
		GL30.glBindVertexArray(0);
	}

}
