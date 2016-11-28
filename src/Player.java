
public class Player {

	private String name;
	
	private int outs;
	private int walks;
	private int singles;
	private int doubles;
	private int triples;
	private int homeruns;
	
	
	public Player(String n){
		name = n;
	}
	
	public void doEvent(ActionType a){
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
	
	public String toString(){
		String str = "\nName: " + name;
		str += "\nOuts: " + outs;
		str += "\nWalks: " + walks;
		str += "\nSingles: " + singles;
		str += "\nDoubles: " + doubles;
		str += "\nTriples: " + triples;
		str += "\nHomeruns: " + homeruns;
		
		return str;
	
		
	}
	
	public String getName(){
		return name;
	}
	
	public Object[] toArray(){
		return new Object[]{name,outs,singles,doubles,triples,homeruns,walks};
	}
}
