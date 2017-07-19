package shaders;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import cameras.Cam;
import maths.Mat4f;
import objects.GameObject;
import renderEngine.Material;
import renderEngine.Mesh;
import renderEngine.Model;

public class GameObjectShader extends Shader<GameObject> {
	
	public static final String VERTEX_SHADER_FILE = "src/shaderPrograms/gameObjectVS.glsl";
	public static final String FRAGMENT_SHADER_FILE = "src/shaderPrograms/gameObjectFS.glsl";
	
	private Cam cam;
	
	private int textureLoc;
	private int transformLoc;
	private int viewLoc;
	private int projectionLoc;
	
	
	public GameObjectShader(Mat4f projection, Cam cam) {
		super(VERTEX_SHADER_FILE, FRAGMENT_SHADER_FILE);
		this.cam = cam;
		textureLoc = getUniformLocation("tex");
		transformLoc = getUniformLocation("transform");
		viewLoc = getUniformLocation("view");
		projectionLoc = getUniformLocation("projection");
		
		// TODO: move?
		start();
		loadInt(textureLoc, 0);
		loadMat4f(projectionLoc, projection);
		stop();
	}

	@Override
	public void bindAttributes() {
		bindAttribute(0, "pos");
		bindAttribute(1, "texCoords");
	}
	
	@Override
	public void prepareShaderRender(){
		loadMat4f(viewLoc, cam.view());
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
	}

	@Override
	public void prepareObjectRender(GameObject object){
		loadMat4f(transformLoc, object.transformation());
	}
	
	@Override
	public void finishModelRender() {
		GL20.glDisableVertexAttribArray(1);
		GL20.glDisableVertexAttribArray(0);
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
		GL30.glBindVertexArray(0);
	}

}
