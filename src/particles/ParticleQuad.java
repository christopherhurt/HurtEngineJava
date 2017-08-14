package particles;

import meshes.Quad;

public class ParticleQuad extends Quad {
	
	public ParticleQuad(){
		renderInstanced(19);
		createInstanceAttribute(2, 4, 0);
		createInstanceAttribute(3, 4, 4);
		createInstanceAttribute(4, 4, 8);
		createInstanceAttribute(5, 4, 12);
		createInstanceAttribute(6, 1, 12);
		createInstanceAttribute(7, 1, 12);
		createInstanceAttribute(8, 1, 12);
	}
	
}
