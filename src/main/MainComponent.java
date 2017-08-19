package main;

import cameras.Camera;
import cameras.FirstPersonCam;
import display.Disp;
import gameObjects.GameObject;
import gameObjects.GameObjectShader;
import maths.Vec3f;
import meshes.Meshes;
import particles.Particles;
import renderEngine.Handler;
import renderEngine.Material;
import renderEngine.Mesh;
import renderEngine.Model;
import utilities.LinearInterpolator;
import utilities.Time;

public class MainComponent {
		
	public static void init(){
		Disp.create(new Vec3f(0.5f, 0.5f, 1.0f));
		Camera cam = new FirstPersonCam(new Vec3f(0, 0, 0), new Vec3f(0, 0, -1), 70, 0.001f, 1000);
		GameObjectShader shader = new GameObjectShader(cam);
		Handler<GameObject> handler = new Handler<>(shader);
		
		Mesh mesh = Meshes.CUBE;
		Material material = new Material("red");
		Model model = new Model(mesh, material);
		
		int numBlocks = 500;
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
			Disp.update();
			Time.calcTimePassed();
			LinearInterpolator.updateValues(); // TODO: create one hidden update method
			cam.update(); // TODO: TEMP
			Particles.update();
			handler.render();
			System.gc(); // TODO: find fix to buffer garbage collection?
		}
		
		handler.deleteAll();
		Disp.close();
	}
	
	public static void main(String args[]){
		init();
	}
	
}
