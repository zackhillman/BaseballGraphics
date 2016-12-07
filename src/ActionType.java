
public enum ActionType {

	//Possible different events and their probability out of 100
	OUT(70), WALK(4), SINGLE(12), DOUBLE(6), TRIPLE(3), HOMERUN(5);
	
	private final int weight;// The ActionType's weight out of 100
	
	/**This is the "constructor" method
	 * @param w- the weight
	 */
	ActionType(int w){
		weight = w;
	}

	/**
	 * This method gets the ActionType's weight
	 * @return- the weight out of 100
	 */
	public int getWeight() {
		return weight;
	}
}
