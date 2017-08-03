package utilities;

@SuppressWarnings("serial")
public class HurtEngineException extends RuntimeException {
	
	public HurtEngineException(){
		super((String) null);
	}
	
	public HurtEngineException(String message){
		super(message);
	}
	
}
