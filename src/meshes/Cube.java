package meshes;

import renderEngine.Mesh;

public class Cube extends Mesh { // TODO: make package-only class?
	
	private static final int[] indices = {
		 0,  1,  2,  2,  3,  0,
		 4,  5,  6,  6,  7,  4,
		 8,  9, 10, 10, 11,  8,
		12, 13, 14, 14, 15, 12,
		16, 17, 18, 18, 19, 16,
		20, 21, 22, 22, 23, 20
	};
	
	private static final float[] vertices = {
		-0.5f,  0.5f,  0.5f,
		 0.5f,  0.5f,  0.5f,
		 0.5f, -0.5f,  0.5f,
		-0.5f, -0.5f,  0.5f,
		
		-0.5f,  0.5f, -0.5f,
		-0.5f,  0.5f,  0.5f,
		-0.5f, -0.5f,  0.5f,
		-0.5f, -0.5f, -0.5f,
		
		 0.5f,  0.5f, -0.5f,
		-0.5f,  0.5f, -0.5f,
		-0.5f, -0.5f, -0.5f,
		 0.5f, -0.5f, -0.5f,
		
		 0.5f,  0.5f,  0.5f,
		 0.5f,  0.5f, -0.5f,
		 0.5f, -0.5f, -0.5f,
		 0.5f, -0.5f,  0.5f,
		
		-0.5f,  0.5f, -0.5f,
		 0.5f,  0.5f, -0.5f,
		 0.5f,  0.5f,  0.5f,
		-0.5f,  0.5f,  0.5f,
		
		-0.5f, -0.5f,  0.5f,
		 0.5f, -0.5f,  0.5f,
		 0.5f, -0.5f, -0.5f,
		-0.5f, -0.5f, -0.5f
	};
	
	private static final float[] texCoords = {
		0, 1,
		1, 1,
		1, 0,
		0, 0,
		
		0, 1,
		1, 1,
		1, 0,
		0, 0,
		
		0, 1,
		1, 1,
		1, 0,
		0, 0,
		
		0, 1,
		1, 1,
		1, 0,
		0, 0,
		
		0, 1,
		1, 1,
		1, 0,
		0, 0,
		
		0, 1,
		1, 1,
		1, 0,
		0, 0
	};

	private static final float[] normals = {
		 0,  0,  1,
		 0,  0,  1,
		 0,  0,  1,
		 0,  0,  1,
		 
		-1,  0,  0,
		-1,  0,  0,
		-1,  0,  0,
		-1,  0,  0,
		
		 0,  0, -1,
		 0,  0, -1,
		 0,  0, -1,
		 0,  0, -1,
		 
		 1,  0,  0,
		 1,  0,  0,
		 1,  0,  0,
		 1,  0,  0,
		 
		 0,  1,  0,
		 0,  1,  0,
		 0,  1,  0,
		 0,  1,  0,
		 
		 0, -1,  0,
		 0, -1,  0,
		 0, -1,  0,
		 0, -1,  0
	};

	private static final float[] tangents = {
		 1,  0,  0,
		 1,  0,  0,
		 1,  0,  0,
		 1,  0,  0,
		
		 0,  0,  1,
		 0,  0,  1,
		 0,  0,  1,
		 0,  0,  1,
		
		-1,  0,  0,
		-1,  0,  0,
		-1,  0,  0,
		-1,  0,  0,
		
		 0,  0, -1,
		 0,  0, -1,
		 0,  0, -1,
		 0,  0, -1,
		
		 1,  0,  0,
		 1,  0,  0,
		 1,  0,  0,
		 1,  0,  0,
		
		 1,  0,  0,
		 1,  0,  0,
		 1,  0,  0,
		 1,  0,  0,
	};
	
	public Cube(){
		super(indices, vertices, texCoords, normals, tangents);
		renderInstanced(16);
	}
	
}
