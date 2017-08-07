package objects;

import renderEngine.Model;

public class RenderObject {
	
	private Model model;
	
	public RenderObject(Model model){
		this.model = model;
	}
	
	public Model getModel(){
		return model;
	}
	
}
