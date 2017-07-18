package renderEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.lwjgl.opengl.GL11;

import objects.RenderObject;
import shaders.Shader;

public class Handler<T extends RenderObject> {
	
	private Shader<T> shader;
	private Map<Model, List<T>> objects;
	
	public Handler(Shader<T> shader){
		this.shader = shader;
		objects = new HashMap<>();
	}
	
	public void render(){
		shader.start();
		shader.prepareShaderRender();
		
		for(Model model : objects.keySet()){
			shader.prepareModelRender(model);
			List<T> list = objects.get(model);
			
			for(T object : list){
				shader.prepareObjectRender(object);
				GL11.glDrawElements(GL11.GL_TRIANGLES, object.getModel().getMesh().getIndices().length, GL11.GL_UNSIGNED_INT, 0);
			}
			
			shader.finishModelRender();
		}
		
		shader.stop();
	}
	
	public void add(T object){
		Model model = object.getModel();
		
		if(objects.containsKey(model)){
			List<T> list = objects.get(model);
			list.add(object);
		}else{
			List<T> list = new ArrayList<T>();
			list.add(object);
			objects.put(model, list);
		}
	}
	
	public void remove(T object){
		// TODO
	}
	
	public void delete(){
		// TODO
	}
	
}
