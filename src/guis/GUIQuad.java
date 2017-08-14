package guis;

import meshes.Quad;

public class GUIQuad extends Quad {
	
	public GUIQuad(){
		renderInstanced(16);
		createInstanceAttribute(2, 4, 0);
		createInstanceAttribute(3, 4, 4);
		createInstanceAttribute(4, 4, 8);
		createInstanceAttribute(5, 4, 12);
	}
	
}
