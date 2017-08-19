package meshes;

import renderEngine.Mesh;

public class Cube extends Mesh {
	
	private static final int[] indices = {
		 0,  1,  2,  2,  3,  0, // Front
		 4,  5,  6,  6,  7,  4, // Left
		 8,  9, 10, 10, 11,  8, // Back
		12, 13, 14, 14, 15, 12, // Right
		16, 17, 18, 18, 19, 16, // Top
		20, 21, 22, 22, 23, 20  // Bottom
	};
	
	private static final float[] vertices = {
		// Front
		-0.5f,  0.5f,  0.5f,
		 0.5f,  0.5f,  0.5f,
		 0.5f, -0.5f,  0.5f,
		-0.5f, -0.5f,  0.5f,
		
		// Left
		-0.5f,  0.5f, -0.5f,
		-0.5f,  0.5f,  0.5f,
		-0.5f, -0.5f,  0.5f,
		-0.5f, -0.5f, -0.5f,
		
		// Back
		 0.5f,  0.5f, -0.5f,
		-0.5f,  0.5f, -0.5f,
		-0.5f, -0.5f, -0.5f,
		 0.5f, -0.5f, -0.5f,
		
		// Right
		 0.5f,  0.5f,  0.5f,
		 0.5f,  0.5f, -0.5f,
		 0.5f, -0.5f, -0.5f,
		 0.5f, -0.5f,  0.5f,
		
		// Top
		-0.5f,  0.5f, -0.5f,
		 0.5f,  0.5f, -0.5f,
		 0.5f,  0.5f,  0.5f,
		-0.5f,  0.5f,  0.5f,
		
		// Bottom
		-0.5f, -0.5f,  0.5f,
		 0.5f, -0.5f,  0.5f,
		 0.5f, -0.5f, -0.5f,
		-0.5f, -0.5f, -0.5f
	};
	
	private static final float[] texCoords = {
		// Front
		0, 1,
		1, 1,
		1, 0,
		0, 0,
		
		// Left
		0, 1,
		1, 1,
		1, 0,
		0, 0,
		
		// Back
		0, 1,
		1, 1,
		1, 0,
		0, 0,
		
		// Right
		0, 1,
		1, 1,
		1, 0,
		0, 0,
		
		// Top
		0, 1,
		1, 1,
		1, 0,
		0, 0,
		
		// Bottom
		0, 1,
		1, 1,
		1, 0,
		0, 0
	};

	private static final float[] normals = {
		// Front 
		 0,  0,  1,
		 0,  0,  1,
		 0,  0,  1,
		 0,  0,  1,
		
		// Left
		-1,  0,  0,
		-1,  0,  0,
		-1,  0,  0,
		-1,  0,  0,
		
		// Back
		 0,  0, -1,
		 0,  0, -1,
		 0,  0, -1,
		 0,  0, -1,
		
		// Right
		 1,  0,  0,
		 1,  0,  0,
		 1,  0,  0,
		 1,  0,  0,
		 
		// Top
		 0,  1,  0,
		 0,  1,  0,
		 0,  1,  0,
		 0,  1,  0,
		 
		// Bottom
		 0, -1,  0,
		 0, -1,  0,
		 0, -1,  0,
		 0, -1,  0
	};

	private static final float[] tangents = {
		// Front
		 1,  0,  0,
		 1,  0,  0,
		 1,  0,  0,
		 1,  0,  0,
		
		// Left
		 0,  0,  1,
		 0,  0,  1,
		 0,  0,  1,
		 0,  0,  1,
		
		// Back
		-1,  0,  0,
		-1,  0,  0,
		-1,  0,  0,
		-1,  0,  0,
		
		// Right
		 0,  0, -1,
		 0,  0, -1,
		 0,  0, -1,
		 0,  0, -1,
		
		// Top
		 1,  0,  0,
		 1,  0,  0,
		 1,  0,  0,
		 1,  0,  0,
		
		// Bottom
		 1,  0,  0,
		 1,  0,  0,
		 1,  0,  0,
		 1,  0,  0,
	};
	
	public Cube(){
		super(indices, vertices, texCoords, normals, tangents);
	}
	
}
