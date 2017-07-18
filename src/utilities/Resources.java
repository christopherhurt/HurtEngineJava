package utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

public class Resources {
	
private static final String RES_LOC = "res/";
	
	public static int loadTexture(String filePath){
		Texture texture = null;
		
		try {
			texture = TextureLoader.getTexture("png", new FileInputStream(RES_LOC + filePath + ".png"));
		} catch (FileNotFoundException e) {
			System.err.println("The texture you tried to load does not exist");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("Failed to load texture file");
			e.printStackTrace();
		}
		
		return texture.getTextureID();
	}
	
}
