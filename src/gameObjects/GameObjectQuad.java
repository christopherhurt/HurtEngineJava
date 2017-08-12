package gameObjects;

import meshes.Quad;

public class GameObjectQuad extends Quad {
	
	public GameObjectQuad(){
		renderInstanced(16);
		createInstanceAttribute(4, 4, 0);
		createInstanceAttribute(5, 4, 4);
		createInstanceAttribute(6, 4, 8);
		createInstanceAttribute(7, 4, 12);
	}
	
}
