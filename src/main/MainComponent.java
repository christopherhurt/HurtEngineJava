package main;

import display.Disp;
import maths.Vec3f;

public class MainComponent {
	
	public static void init(){
		Disp.create(new Vec3f(1, 0, 0));
		
		while(Disp.isOpen()){
			Disp.clear();
			Disp.update();
		}
		
		Disp.close();
	}
	
	public static void main(String args[]){
		init();
	}
	
}
