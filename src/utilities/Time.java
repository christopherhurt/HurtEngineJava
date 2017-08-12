package utilities;

public class Time {
	
	private static long lastTime = System.nanoTime();
	private static double delta = 0;
	
	private static int fps = 0;
	private static double secondCounter = 0;
	
	public static void calcTimePassed(){
		long thisTime = System.nanoTime();
		delta = (double) (thisTime - lastTime) / 1000000000;
		lastTime = thisTime;
		calcFps();
	}
	
	private static void calcFps(){
		fps++;
		secondCounter += delta;
		if(secondCounter >= 1){
			fps = 0;
			secondCounter %= 1;
		}
	}
	
	public static float getDelta(){
		return (float) delta;
	}
	
	public static int getFps(){ // TODO: move to display class?
		return fps;
	}
	
}
