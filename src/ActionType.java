
public enum ActionType {

	OUT(70), WALK(4), SINGLE(15), DOUBLE(3), TRIPLE(3), HOMERUN(5);
	
	private final int weight;
	
	ActionType(int w){
		weight = w;
	}

	public int getWeight() {
		
		return weight;
	}
}
