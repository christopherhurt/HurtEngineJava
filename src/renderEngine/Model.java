package renderEngine;

public class Model {
	
	private Mesh mesh;
	private Material material;
	
	public Model(Mesh mesh, Material material) {
		this.mesh = mesh;
		this.material = material;
	}
	
	public void delete(){
		mesh.delete();
		material.delete();
	}
	
	public Mesh getMesh() {
		return mesh;
	}
	
	public Material getMaterial() {
		return material;
	}
	
}
