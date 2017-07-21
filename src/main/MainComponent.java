package main;

import cameras.Camera;
import cameras.FPSCam;
import display.Disp;
import maths.Vec3f;
import objects.GameObject;
import renderEngine.Handler;
import renderEngine.Material;
import renderEngine.Mesh;
import renderEngine.Model;
import shaders.GameObjectShader;

public class MainComponent {
	
	private static final float[] vertices = {
		-0.5f, 0.5f, -0.5f,  // V0
		0.5f, 0.5f, -0.5f,   // V1
		-0.5f, -0.5f, -0.5f, // V2
		0.5f, -0.5f, -0.5f,  // V3
		-0.5f, 0.5f, 0.5f,   // V4
		0.5f, 0.5f, 0.5f,    // V5
		-0.5f, -0.5f, 0.5f,  // V6
		0.5f, -0.5f, 0.5f,   // V7
	};
	
	private static final float[] texCoords = {
		0, 0, // V0
		1, 0, // V1
		0, 1, // V2
		1, 1, // V3
		0, 0, // V4
		1, 0, // V5
		0, 1, // V6
		1, 1  // V7
	};
	
	private static final int[] indices = {
		0, 2, 1, 2, 3, 1, //F
		1, 3, 5, 3, 7, 5, //R
		5, 7, 6, 4, 5, 6, //B
		0, 4, 6, 0, 6, 2, //L
		1, 5, 4, 4, 0, 1, //T
		6, 7, 3, 6, 3, 2  //D
	};
		
	public static void init(){
		Disp.create(new Vec3f(0, 0, 0));
		
		Mesh mesh = new Mesh(indices, vertices, texCoords);
		Material material = new Material("brick");
		Model model = new Model(mesh, material);
		GameObject object = new GameObject(model, new Vec3f(0, 0, 0), new Vec3f(0, 0, 0), new Vec3f(1, 1, 1));
		GameObject object2 = new GameObject(model, new Vec3f(-1, 1, -3), new Vec3f(0, 0, 0), new Vec3f(0.5f, 0.5f, 0.5f));
//		Camera cam = new LookAtCam(new Vec3f(0, 0, 0), 3, 45, 0, 70, 0.001f, 1000);
		Camera cam = new FPSCam(new Vec3f(0, 0, 0), new Vec3f(0, 0, -1), 70, 0.001f, 1000);
		Handler<GameObject> handler = new Handler<>(new GameObjectShader(cam));
		handler.add(object);
		handler.add(object2);
		float rotX = 0;
		
		int numBlocks = 300;
		float range = 50;
		float maxSize = 3;
		for(int i = 0; i < numBlocks; i++){
			Vec3f pos = new Vec3f((float) Math.random() * range - range / 2, (float) Math.random() * range - range / 2, (float) Math.random() * range - range / 2);
			Vec3f rot = new Vec3f((float) Math.random() * 360, (float) Math.random() * 360, (float) Math.random() * 360);
			float size = (float) Math.random() * maxSize;
			Vec3f scale = new Vec3f(size, size, size);
			handler.add(new GameObject(model, pos, rot, scale));
		}
		
		while(Disp.isOpen()){
			Disp.clear();
//			object.setRot(new Vec3f(0, rotX++, 0));
			object2.setRot(new Vec3f(0, 0, rotX++));
			cam.update(); // TODO: TEMP
			handler.render();
			Disp.update();
		}
		
		Disp.close();
	}
	
	public static void main(String args[]){
		init();
	}
	
}
