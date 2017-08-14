package particles;

import java.util.List;
import java.util.Map;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import renderEngine.Material;
import renderEngine.Mesh;
import renderEngine.Model;
import renderEngine.Shader;

public class ParticleShader extends Shader<Particle> {
	
	private static final String VERTEX_SHADER_FILE = "src/shaderPrograms/particleVS.glsl";
	private static final String FRAGMENT_SHADER_FILE = "src/shaderPrograms/particleFS.glsl";
	
	private int atlasLoc;
	
	public ParticleShader() {
		super(VERTEX_SHADER_FILE, FRAGMENT_SHADER_FILE);
		
		atlasLoc = getUniformLocation("atlas");
		
		start();
		loadInt(atlasLoc, 0);
		stop();
	}
	
	@Override
	public void bindAttributes() {
		bindAttribute(0, "pos");
		bindAttribute(1, "texCoords");
		bindAttribute(2, "transformView");
		bindAttribute(6, "totalTextures");
		bindAttribute(7, "currentTexture");
		bindAttribute(8, "transitionAmount");
	}
	
	@Override
	public void prepareShaderRender(Map<Model, List<Particle>> objects) { // TODO: sort systems here, careful with HUD systems
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
		GL20.glEnableVertexAttribArray(2);
		GL20.glEnableVertexAttribArray(3);
		GL20.glEnableVertexAttribArray(4);
		GL20.glEnableVertexAttribArray(5);
		GL20.glEnableVertexAttribArray(6);
		GL20.glEnableVertexAttribArray(7);
		GL20.glEnableVertexAttribArray(8);
	}
	
	@Override
	public void prepareObjectRender(Particle object) {
		// Empty
	}
	
	@Override
	public void prepareInstancedRender(List<Particle> instances, float[] instancedData) {
		// TODO: sort all instances (careful with HUD particles), then store their instance data
	}
	
	@Override
	public void finishModelRender(Model model) {
		GL20.glDisableVertexAttribArray(8);
		GL20.glDisableVertexAttribArray(7);
		GL20.glDisableVertexAttribArray(6);
		GL20.glDisableVertexAttribArray(5);
		GL20.glDisableVertexAttribArray(4);
		GL20.glDisableVertexAttribArray(3);
		GL20.glDisableVertexAttribArray(2);
		GL20.glDisableVertexAttribArray(1);
		GL20.glDisableVertexAttribArray(0);
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
		GL30.glBindVertexArray(0);
	}
	
	@Override
	public void finishShaderRender() {
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glDisable(GL11.GL_BLEND);
	}
	
}
