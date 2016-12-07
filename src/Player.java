
public class Player {

	private String name; //Holds the player's name
	
	private int outs;//Tracks number of outs
	private int walks;//Tracks number of walks
	private int singles;//Tracks number of singles
	private int doubles;//Tracks number of doubles
	private int triples;//Tracks number of triples
	private int homeruns;//Tracks number of homeruns
	
	/**
	 * This is the constructor method. It sets the name global variable to n
	 * @param n- the desired name of the player
	 */
	public Player(String n){
		name = n;
	}
	/**
	 * This method adds a specific hit to the player
	 * @param a- the ActionType to add
	 */
	public void addHit(ActionType a){
		switch(a){
			case OUT: outs++;
				break;
			case WALK: walks++;
				break;
			case SINGLE: singles++;
				break;
			case DOUBLE: doubles++;
				break;
			case TRIPLE: triples++;
				break;
			case HOMERUN: homeruns++;
				break;	
		}
	}
	
	/**
	 * This method converts the player to an array of objects with each statistic
	 * @return- the array of stats
	 */
	public Object[] toArray(){
		return new Object[]{name,outs,singles,doubles,triples,homeruns,walks};
	}
}
