package renderEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL31;

import utilities.HurtEngineException;

public class Handler<T extends RenderObject> {
	
	private Shader<T> shader;
	private Map<Model, List<T>> objects;
	
	public Handler(Shader<T> shader){
		this.shader = shader;
		objects = new HashMap<>();
	}
	
	public void render(){
		shader.start();
		shader.prepareShaderRender(objects);
		
		for(Model model : objects.keySet()){
			shader.prepareModelRender(model);
			List<T> list = objects.get(model);
			Mesh mesh = model.getMesh();
			
			if(mesh.getDrawInstanced()){
				float[] instancedData = new float[list.size() * mesh.getInstanceDataSize()];
				shader.prepareInstancedRender(list, instancedData);
				mesh.updateInstancedVbo(instancedData);
				
				if(mesh.getDrawStrips()){
					GL31.glDrawElementsInstanced(GL11.GL_TRIANGLE_STRIP, mesh.getIndices().length, GL11.GL_UNSIGNED_INT, 0, list.size());
				}else{
					GL31.glDrawElementsInstanced(GL11.GL_TRIANGLES, mesh.getIndices().length, GL11.GL_UNSIGNED_INT, 0, list.size());
				}
			}else{
				for(T object : list){
					shader.prepareObjectRender(object);
					
					if(mesh.getDrawStrips()){
						GL11.glDrawElements(GL11.GL_TRIANGLE_STRIP, mesh.getIndices().length, GL11.GL_UNSIGNED_INT, 0);
					}else{
						GL11.glDrawElements(GL11.GL_TRIANGLES, mesh.getIndices().length, GL11.GL_UNSIGNED_INT, 0);
					}	
				}
			}
			
			shader.finishModelRender(model);
		}
		
		shader.finishShaderRender();
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
		Model model = object.getModel();
		List<T> list = objects.get(model);
		
		if(list != null && list.contains(object)){
			list.remove(object);
		}else{
			throw new HurtEngineException("Tried to remove a GameObject that was never added to the handler");
		}
	}
	
	public void deleteAll(){
		for(Model model : objects.keySet()){
			model.delete();
		}
		shader.delete();
	}
	
}
