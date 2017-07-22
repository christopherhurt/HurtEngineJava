package utilities;

public class LerpedFloat {
	
	private final float startValue;
	private final float endValue;
	private final float increment;
	
	private float currentValue;
	private boolean isDone;
	private boolean markedForDeletion;
	
	public LerpedFloat(float startValue, float endValue, float lerpTime){
		this.startValue = startValue;
		this.endValue = endValue;
		
		increment = (endValue - startValue) / lerpTime; // TODO: sync with clock
		
		currentValue = startValue;
		isDone = true;
		markedForDeletion = false;
		
		LinearInterpolator.addValue(this);
	}
	
	public void start(){
		isDone = false;
		currentValue = startValue;
	}
	
	public void stop(){
		isDone = true;
	}
	
	public void delete(){
		isDone = true;
		markedForDeletion = true;
	}
	
	public void increment(){
		if(!isDone){
			currentValue += increment;
			if(currentValue >= endValue){
				currentValue = endValue;
				isDone = true;
			}
		}
	}
	
	protected boolean markedForDeletion(){
		return markedForDeletion;
	}
	
	public float getValue(){
		return currentValue;
	}
	
}
