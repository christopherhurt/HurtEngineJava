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
	
	private int transformLoc;
	private int viewLoc;
	private int projectionLoc;
	
	private int camLocLoc;
	
	private int usesLightingLoc;
	private int usesLightMapLoc;
	private int usesNormalMapLoc;
	private int usesDepthMapLoc;
	
	private int ambientFactorLoc;
	private int diffuseFactorLoc;
	private int specularFactorLoc;
	private int shininessLoc;
	
	private int diffuseMapLoc;
	private int lightMapLoc;
	private int normalMapLoc;
	private int depthMapLoc;
	
	public GameObjectShader(Camera cam, int maxLights) { // TODO: move maxLights parameter?
		super(VERTEX_SHADER_FILE, FRAGMENT_SHADER_FILE);
		this.cam = cam;
		
		transformLoc = getUniformLocation("transform");
		viewLoc = getUniformLocation("view");
		projectionLoc = getUniformLocation("projection");
		
//		start();
//		loadInt(textureLoc, 0);
//		stop();
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
