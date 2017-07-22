package utilities;

import java.util.ArrayList;
import java.util.List;

public class LinearInterpolator {
	
	private static final List<LerpedFloat> values = new ArrayList<>();
	
	public static void addValue(LerpedFloat value){
		values.add(value);
	}
	
	public static void updateValues(){
		for(int i = 0; i < values.size(); i++){
			LerpedFloat value = values.get(i);
			if(value.markedForDeletion()){
				values.remove(i);
				i--;
			}else{
				value.increment();
			}
		}
	}
	
}
