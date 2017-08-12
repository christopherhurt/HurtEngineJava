package shaders;

import java.util.List;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import maths.Mat4f;
import objects.GUI;
import renderEngine.Material;
import renderEngine.Mesh;
import renderEngine.Model;

public class GUIShader extends Shader<GUI> {
	
	private static final String VERTEX_SHADER_FILE = "src/shaderPrograms/guiVS.glsl";
	private static final String FRAGMENT_SHADER_FILE = "src/shaderPrograms/guiFS.glsl";
	
	private int texLoc;
	
	public GUIShader() {
		super(VERTEX_SHADER_FILE, FRAGMENT_SHADER_FILE);
		
		texLoc = getUniformLocation("tex");
		
		start();
		loadInt(texLoc, 0);
		stop();
	}

	@Override
	public void bindAttributes() {
		bindAttribute(0, "pos");
		bindAttribute(1, "texCoords");
		bindAttribute(4, "transform");
	}

	@Override
	public void prepareShaderRender() {
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glDisable(GL11.GL_DEPTH_TEST);
	}

	@Override
	public void prepareModelRender(Model model) {
		Mesh mesh = model.getMesh();
		Material material = model.getMaterial();
		
		GL30.glBindVertexArray(mesh.getVao());
		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, material.getDiffuseMap());
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, mesh.getIndexVbo());
		GL20.glEnableVertexAttribArray(0);
		GL20.glEnableVertexAttribArray(1);
		GL20.glEnableVertexAttribArray(4);
		GL20.glEnableVertexAttribArray(5);
		GL20.glEnableVertexAttribArray(6);
		GL20.glEnableVertexAttribArray(7);
	}
	
	@Override
	public void prepareObjectRender(GUI object) {
		// Empty
	}
	
	@Override
	public void prepareInstancedRender(List<GUI> instances, float[] instancedData) {
		int pointer = 0;
		for(GUI gui : instances){
			Mat4f transform = gui.transformation();
			for(int c = 0; c < 4; c++){
				for(int r = 0; r < 4; r++){
					instancedData[pointer++] = transform.value(r, c);
				}
			}
		}
	}

	@Override
	public void finishModelRender(Model model) {
		GL20.glDisableVertexAttribArray(7);
		GL20.glDisableVertexAttribArray(6);
		GL20.glDisableVertexAttribArray(5);
		GL20.glDisableVertexAttribArray(4);
		GL20.glDisableVertexAttribArray(1);
		GL20.glDisableVertexAttribArray(0);
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
		GL30.glBindVertexArray(0);
	}
	
	@Override
	public void finishShaderRender(){
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glDisable(GL11.GL_BLEND); // TODO: fix to implement trasnparency
	}
	
}
