package renderEngine;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.opengl.GL33;

import utilities.HurtEngineException;
import utilities.Utilities;

public class Mesh {
	
	public static final int MAX_INSTANCES = 10000;
	
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
	private int instancedVbo;
	
	private boolean drawStrips;
	private boolean drawInstanced;
	
	private int instanceDataSize;
	private FloatBuffer instancesBuffer;
	
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
		
		instancedVbo = -1;
		drawInstanced = false;
		instanceDataSize = -1;
		instancesBuffer = null;
		
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
	
	public void updateInstancedVbo(float[] data){
		if(drawInstanced){
			instancesBuffer.clear();
			instancesBuffer.put(data);
			instancesBuffer.flip();
			GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, instancedVbo);
			GL15.glBufferData(GL15.GL_ARRAY_BUFFER, instancesBuffer.capacity() * 4, GL15.GL_STREAM_DRAW);
			GL15.glBufferSubData(GL15.GL_ARRAY_BUFFER, 0, instancesBuffer);
			GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
		}else{
			throw new HurtEngineException("Tried to update instanced vbo without setting Mesh to render instanced");
		}
	}
	
	public void delete(){
		GL15.glDeleteBuffers(indexVbo);
		GL15.glDeleteBuffers(vertexVbo);
		GL15.glDeleteBuffers(texCoordVbo);
		GL15.glDeleteBuffers(normalsVbo);
		GL15.glDeleteBuffers(tangentsVbo);
		GL15.glDeleteBuffers(instancedVbo);
		GL30.glDeleteVertexArrays(vao);
	}
	
	public Mesh renderInstanced(int instanceDataSize){
		if(!drawInstanced){
			drawInstanced = true;
			instancedVbo = GL15.glGenBuffers();
			this.instanceDataSize = instanceDataSize;
			instancesBuffer = BufferUtils.createFloatBuffer(instanceDataSize * MAX_INSTANCES);
			
			GL30.glBindVertexArray(vao);
			GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, instancedVbo);
			GL15.glBufferData(GL15.GL_ARRAY_BUFFER, instanceDataSize * MAX_INSTANCES * 4, GL15.GL_STREAM_DRAW);
			createInstanceAttribute(4, 4, 0);
			createInstanceAttribute(5, 4, 4);
			createInstanceAttribute(6, 4, 8);
			createInstanceAttribute(7, 4, 12);
			GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
			GL30.glBindVertexArray(0);
			
			return this;
		}else{
			throw new HurtEngineException("Already set mesh to be instanced rendered");
		}
	}
	
	private void createInstanceAttribute(int attribute, int dataSize, int offset){
		GL20.glVertexAttribPointer(attribute, dataSize, GL11.GL_FLOAT, false, instanceDataSize * 4, offset * 4);
		GL33.glVertexAttribDivisor(attribute, 1);
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
	
	public boolean getDrawStrips(){
		return drawStrips;
	}
	
	public Mesh setDrawStrips(boolean drawStrips){
		this.drawStrips = drawStrips;
		return this;
	}
	
	public boolean getDrawInstanced(){
		return drawInstanced;
	}
	
	public int getInstanceDataSize(){
		return instanceDataSize;
	}
	
}
