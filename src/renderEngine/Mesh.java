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
	private float[] normals;
	private float[] tangents;
	
	private int vao;
	private int indexVbo;
	private int vertexVbo;
	private int texCoordVbo;
	private int normalsVbo;
	private int tangentsVbo;
	
	private boolean drawStrips;
	
	public Mesh(int[] indices, float[] vertices, float[] texCoords, float[] normals, float[] tangents){
		this.indices = indices;
		this.vertices = vertices;
		this.texCoords = texCoords;
		this.normals = normals;
		this.tangents = tangents;
		
		drawStrips = false;
		
		vao = GL30.glGenVertexArrays();
		indexVbo = GL15.glGenBuffers();
		vertexVbo = GL15.glGenBuffers();
		texCoordVbo = GL15.glGenBuffers();
		normalsVbo = GL15.glGenBuffers();
		tangentsVbo = GL15.glGenBuffers();
		
		loadAllData();
	}
	
	private void loadAllData(){
		GL30.glBindVertexArray(vao);
		
		IntBuffer indices = Utilities.arrayToBuffer(this.indices);
		FloatBuffer vertices = Utilities.arrayToBuffer(this.vertices);
		FloatBuffer texCoords = Utilities.arrayToBuffer(this.texCoords);
		FloatBuffer normals = Utilities.arrayToBuffer(this.normals);
		FloatBuffer tangents = Utilities.arrayToBuffer(this.tangents);
		
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, indexVbo);
		GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, indices, GL15.GL_STATIC_DRAW);
		
		loadVbo(0, vertexVbo, vertices, 3);
		loadVbo(1, texCoordVbo, texCoords, 2);
		loadVbo(2, normalsVbo, normals, 3);
		loadVbo(3, tangentsVbo, tangents, 3);
		
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
		GL15.glDeleteBuffers(indexVbo);
		GL15.glDeleteBuffers(vertexVbo);
		GL15.glDeleteBuffers(texCoordVbo);
		GL15.glDeleteBuffers(normalsVbo);
		GL15.glDeleteBuffers(tangentsVbo);
		GL30.glDeleteVertexArrays(vao);
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
	
	public float[] getNormals(){
		return normals;
	}
	
	public float[] getTangents(){
		return tangents;
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
	
	public int getNormalsVbo(){
		return normalsVbo;
	}
	
	public int getTangentsVbo(){
		return tangentsVbo;
	}
	
	public void setDrawStrips(boolean drawStrips){
		this.drawStrips = drawStrips;
	}
	
	public boolean getDrawStrips(){
		return drawStrips;
	}
	
}
