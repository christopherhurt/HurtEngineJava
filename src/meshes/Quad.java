package meshes;

import renderEngine.Mesh;

public class Quad extends Mesh {
	
	private static final int[] indices = {
		0, 1, 2, 3
	};
	
	private static final float[] vertices = {
		-0.5f, -0.5f, 0,
		-0.5f,  0.5f, 0,
		 0.5f, -0.5f, 0,
		 0.5f,  0.5f, 0
	};
	
	private static final float[] texCoords = {
		0, 0,
		0, 1,
		1, 0,
		1, 1
	};

	private static final float[] normals = {
		0, 0, 1,
		0, 0, 1,
		0, 0, 1,
		0, 0, 1,
	};

	private static final float[] tangents = {
		1, 0, 0,
		1, 0, 0,
		1, 0, 0,
		1, 0, 0
	};
	
	public Quad(){
		super(indices, vertices, texCoords, normals, tangents);
		setDrawStrips(true);
	}
	
}
