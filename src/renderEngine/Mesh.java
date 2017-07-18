package renderEngine;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import utilities.Utilities;

public class Mesh {
	
	private int[] indices;
	private float[] vertices;
	private float[] texCoords;
	
	private int vao;
	private int indexVbo;
	private int vertexVbo;
	private int texCoordVbo;
	
	public Mesh(int[] indices, float[] vertices, float[] texCoords) {
		this.indices = indices;
		this.vertices = vertices;
		this.texCoords = texCoords;
		
		vao = GL30.glGenVertexArrays();
		indexVbo = GL15.glGenBuffers();
		vertexVbo = GL15.glGenBuffers();
		texCoordVbo = GL15.glGenBuffers();
		
		loadAllData();
	}
	
	private void loadAllData(){
		GL30.glBindVertexArray(vao);
		
		FloatBuffer vertices = Utilities.arrayToBuffer(this.vertices);
		IntBuffer indices = Utilities.arrayToBuffer(this.indices);
		FloatBuffer texes = Utilities.arrayToBuffer(this.texCoords);
		
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, indexVbo);
		GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, indices, GL15.GL_STATIC_DRAW);
		
		loadVbo(0, vertexVbo, vertices, 3);
		loadVbo(1, texCoordVbo, texes, 2);
		
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
		GL30.glBindVertexArray(0);
	}
	
	private void loadVbo(int attribute, int vbo, FloatBuffer buffer, int dataSegmentLength){
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vbo);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
		GL20.glVertexAttribPointer(attribute, dataSegmentLength, GL11.GL_FLOAT, false, 0, 0);
	}
	
	public void delete(){
		// TODO
	}
	
	public int[] getIndices() {
		return indices;
	}
	
	public float[] getVertices() {
		return vertices;
	}
	
	public float[] getTexCoords() {
		return texCoords;
	}
	
	public int getVao(){
		return vao;
	}
	
	public int getIndexVbo(){
		return indexVbo;
	}
	
	public int getVertexVbo(){
		return vertexVbo;
	}
	
	public int getTexCoordVbo(){
		return texCoordVbo;
	}
	
}
