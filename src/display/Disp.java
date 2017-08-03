package display;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

import maths.Vec3f;

public class Disp {
	
	public static final int WIDTH = 1280, HEIGHT = 720, FPS_CAP = 120;
	public static final String TITLE = "Hurt Game Engine";
	
	public static void create(Vec3f color){		
		try {
			Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
			Display.setTitle(TITLE);
			Display.create();
			GL11.glClearColor(color.getX(), color.getY(), color.getZ(), 1);
			GL11.glEnable(GL11.GL_DEPTH_TEST);
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
	}
	
	public static boolean isOpen(){
		return !Display.isCloseRequested();
	}
	
	public static void clear(){
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
	}
	
	public static void update(){
		Display.sync(FPS_CAP);
		Display.update();
	}
	
	public static void close(){
		Display.destroy();
	}
	
}
