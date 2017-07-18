package objects;

import renderEngine.Model;

public abstract class RenderObject {
	
	private Model model;

	public RenderObject(Model model) {
		super();
		this.model = model;
	}

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}
	
}
