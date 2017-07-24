package main;

import org.lwjgl.opengl.GL11;

import cameras.Camera;
import cameras.LookAtCam;
import display.Disp;
import maths.Vec3f;
import meshes.Cube;
import objects.GameObject;
import renderEngine.Handler;
import renderEngine.Material;
import renderEngine.Mesh;
import renderEngine.Model;
import shaders.GameObjectShader;
import utilities.LinearInterpolator;

public class MainComponent {
		
	public static void init(){
		Disp.create(new Vec3f(0, 0, 0));
		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glFrontFace(GL11.GL_CW);
		GL11.glCullFace(GL11.GL_BACK);
		Mesh cube = new Cube();
		Material material = new Material("brick");
		Model model = new Model(cube, material);
		GameObject object = new GameObject(model, new Vec3f(0, 0, 0), new Vec3f(0, 0, 0), new Vec3f(1, 1, 1));
		GameObject object2 = new GameObject(model, new Vec3f(-1, 1, -3), new Vec3f(0, 0, 0), new Vec3f(0.5f, 0.5f, 0.5f));
		Camera cam = new LookAtCam(new Vec3f(0, 0, 0), 3, 45, 0, 70, 0.01f, 1000);
//		Camera cam = new FPSCam(new Vec3f(0, 0, 0), new Vec3f(0, 0, -1), 70, 0.001f, 1000);
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
			object2.setRot(new Vec3f(0, 0, rotX++));
			LinearInterpolator.updateValues(); // TODO: create one hidden update method
			cam.update(); // TODO: TEMP
			handler.render();
			Disp.update();
			System.gc(); // TODO: find fix to buffer garbage collection?
		}
		
		Disp.close();
	}
	
	public static void main(String args[]){
		init();
	}
	
}
