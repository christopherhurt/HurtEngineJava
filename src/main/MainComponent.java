package main;

import org.lwjgl.opengl.GL11;

import cameras.Camera;
import cameras.FirstPersonCam;
import display.Disp;
import gameObjects.GameObject;
import gameObjects.GameObjectQuad;
import gameObjects.GameObjectShader;
import guis.GUI;
import guis.GUIShader;
import lights.Spotlight;
import maths.Vec2f;
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
		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glFrontFace(GL11.GL_CW);
		GL11.glCullFace(GL11.GL_BACK);
		Mesh cube = Meshes.CUBE;
		Material material = new Material("bricks2");
		material.setNormalMap("bricks2_normal");
		material.setDepthMap("bricks2_disp", 0.2f, true);
		material.setAmbient(0.1f);
		material.setDiffuse(0.7f);
		material.setSpecular(0.7f, 16);
		new Spotlight(new Vec3f(0, 0, 0), new Vec3f(0, 0, -1), 30, 35, new Vec3f(1, 1, 1), 1, true);
//		new DirectionalLight(new Vec3f(0, 0, -1), new Vec3f(1f, 1f, 1.0f), 0.5f, true);
//		new PointLight(new Vec3f(0, 0, 0), 5, new Vec3f(1, 1, 1), 1, true);
		Model model = new Model(cube, material);
		GameObject object = new GameObject(model, new Vec3f(0, 0, 0), new Vec3f(0, 0, 0), new Vec3f(1, 1, 1));
		GameObject object2 = new GameObject(model, new Vec3f(-1, 1, -3), new Vec3f(0, 0, 0), new Vec3f(0.5f, 0.5f, 0.5f));
//		Camera cam = new ThirdPersonCam(new Vec3f(0, 0, 0), 3, 45, 0, 70, 0.01f, 1000);
		Camera cam = new FirstPersonCam(new Vec3f(0, 0, 0), new Vec3f(0, 0, -1), 70, 0.001f, 1000);
		GameObjectShader shader = new GameObjectShader(cam);
		Handler<GameObject> handler = new Handler<>(shader);
		handler.add(object);
		handler.add(object2);
		float rotX = 0;
		
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
		
		Model quad = new Model(new GameObjectQuad(), material);
		
		int sideLength = 50;
		float quadScale = 3;
		float depth = 20;
		for(int i = 0; i < sideLength; i++){
			float offset = -(quadScale / 2 * (sideLength - 1));
			for(int j = 0; j < sideLength; j++){
				Vec3f pos = new Vec3f(offset + j * quadScale, offset + i * quadScale, -depth);
				Vec3f rot = new Vec3f(0, 0, 0);
				Vec3f scale = new Vec3f(quadScale, quadScale, quadScale);
				GameObject derQuad = new GameObject(quad, pos, rot, scale);
				handler.add(derQuad);
			}
		}
		
		Handler<GUI> guiHandler = new Handler<>(new GUIShader());
		GUI gui = new GUI("transparentRed", new Vec2f(-0.5f, -0.5f), new Vec2f(1, 1));
		GUI gui2 = new GUI("bricks2_disp", new Vec2f(0, 0), new Vec2f(1, 1));
		guiHandler.add(gui2);
		guiHandler.add(gui);
		float angle = 0;
		float delta = 0.05f;
		
		while(Disp.isOpen()){
			Disp.clear();
			object2.setRot(new Vec3f(0, 0, rotX++));
			Time.calcTimePassed();
			LinearInterpolator.updateValues(); // TODO: create one hidden update method
			cam.update(); // TODO: TEMP
			Particles.update();
			handler.render();
			gui.setPosition(new Vec2f((float) Math.cos(angle) - 0.5f, (float) Math.sin(angle) - 0.5f));
			angle += delta;
			guiHandler.render(); // needs to be AFTER game object handler
			Disp.update();
			System.gc(); // TODO: find fix to buffer garbage collection?
		}
		
		handler.deleteAll();
		Disp.close();
	}
	
	public static void main(String args[]){
		init();
	}
	
}
